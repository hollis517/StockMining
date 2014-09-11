package org.a805.service.impl.corpgovern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.a805.service.corpgovern.ClusteringService;
import org.a805.tools.Distance;

public class ClusteringServiceImpl implements ClusteringService {
	public static final int ERROR = -1;

	/**
	 * ��ຯ�������ڲ���������ͬ������֮��ľ���
	 */
	public int calculateDIstance(String genome1, String genome2) {
		// TODO Auto-generated method stub
		// �����������Ĳ��������ش���
		if (genome1 == null || genome2 == null)
			return ERROR;
		// ���������鳤�Ȳ�ƥ�䣬���ش���
		if (genome1.length() != genome2.length())
			return ERROR;

		// ��࿪ʼ

		// ������ĸ��ƣ����ڲ���
		String temp1 = genome1;
		String temp2 = genome2;
		// ���αȽϵ�һλ�ַ�
		String operator1 = "";
		String operator2 = "";
		// ��ž���ı���
		int count = 0;

		while (temp1.length() != 0) {
			// ȡÿ��������ĵ�һλ�ַ�
			operator1 = temp1.substring(0, 1);
			operator2 = temp2.substring(0, 1);
			// ����ȡ���ַ��޳�
			temp1 = temp1.substring(1);
			temp2 = temp2.substring(1);
			// �ȽϾ���
			if (!operator1.equals(operator2)) {
				count++;
			}
		}

		return count;
	}

	/**
	 * ����ʵ�ֺ���
	 */
	public Map<String, Map<String, String>> ClusteringOfAgglomerate(
			Map<String, String> map, int classes) {
		// TODO Auto-generated method stub
		// ���칫˾id��ʱ�λ�������ŵ�1 2 3�����Ĺ�����ͨ��Mapʵ��
		Map<String, String> mapOfStockId = new TreeMap<String, String>();
		Map<String, String> mapOfGenome = new TreeMap<String, String>();

		// ��ʼ��1��2��3�����빫˾id��ʱ�λ���Ĺ���
		Iterator<Map.Entry<String, String>> it1 = map.entrySet().iterator();
		for (int i = 1; it1.hasNext(); i++) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it1
					.next();
			mapOfStockId.put(String.valueOf(i), entry.getKey());
			mapOfGenome.put(String.valueOf(i), entry.getValue());
		}

		// ���湹�������������

		// �����ά����Ҳ����ѡ��˾�ĸ���
		int mapSize = map.size();
		int[][] distanceMatrix = new int[mapSize + 1][mapSize + 1];
		List<Distance> distanceList = new ArrayList<Distance>();

		for (int i = 1; i <= mapSize; i++) {
			for (int j = 1; j <= mapSize; j++) {
				if (i <= j)
					continue;
				String genome1 = mapOfGenome.get(String.valueOf(i));
				String genome2 = mapOfGenome.get(String.valueOf(j));
				int distance = calculateDIstance(genome1, genome2);
				// �������ʽ���
				distanceMatrix[i][j] = distance;
				// ˳������ʽ���
				distanceList.add(new Distance(i, j, distance));

			}
		}
		//��˳��������ֵ��������
		
		//ʹ��java�Դ���Comparator�ӿڽ�������
		 Comparator<Distance> comparator = new Comparator<Distance>(){
			 public int compare(Distance distance1,Distance distance2){
				 if(distance1.getValue()<distance2.getValue()){
					 return -1;
				 }
				return 1;
			 }
		 };
		
		 Collections.sort(distanceList, comparator);
		
		

		// ��ʼ��˳�����
		AgglomerateClustering clustering = new AgglomerateClustering();
		clustering.init(mapSize);

		// ����ľ������

		for (Distance instance : distanceList) {
			if (clustering.getCategoryNumbers() <= classes) {
				break;
			}
			clustering.merge(instance.getIndex1(), instance.getIndex2());

		}

		/*
		 * Ч�ʺܵ͵ľ����㷨
		 * 
		 * int index1 = 1, index2 = 1; int minimum = Integer.MAX_VALUE; while
		 * (clustering.getCategoryNumbers() > classes) { minimum =
		 * Integer.MAX_VALUE;
		 * 
		 * for (int i = 1; i <= mapSize; i++) { for (int j = 1; j <= mapSize;
		 * j++) { if (i <= j) continue; if (distanceMatrix[i][j] < minimum) {
		 * minimum = distanceMatrix[i][j]; index1 = i; index2 = j;
		 * distanceMatrix[i][j] = Integer.MAX_VALUE; } } }
		 * clustering.merge(index1, index2); }
		 */

		// ת����������ݸ�ʽ
		// ���ܾ���Ľ��
		Map<String, Set<Integer>> resultOfAbstract = clustering.getResult();
		// �����������ո�ʽ
		Map<String, Map<String, String>> finalResult = new TreeMap<String, Map<String, String>>();

		Iterator<Map.Entry<String, Set<Integer>>> it2 = resultOfAbstract
				.entrySet().iterator();
		for (int i = 1; it2.hasNext(); i++) {
			Map.Entry<String, Set<Integer>> entry = (Map.Entry<String, Set<Integer>>) it2
					.next();

			Set<Integer> set = entry.getValue();
			Map<String, String> mapOfClass = new TreeMap<String, String>();

			// ��SET�еľ�����һһת��Ϊ��˾id�Ͷ�Ӧ��ʱ�λ���
			for (Integer j : set) {
				mapOfClass.put(mapOfStockId.get(String.valueOf(j)), mapOfGenome
						.get(String.valueOf(j)));
			}

			finalResult.put(String.valueOf(i), mapOfClass);

		}

		// ����̨��ӡ
		/*
		 * for (int i = 1; i <= mapSize; i++) { for (int j = 1; j <= mapSize;
		 * j++) { if (i < j) continue; System.out.print(distanceMatrix[i][j] + "
		 * "); } System.out.println(); }
		 */

		return finalResult;
	}

	public static void main(String a[]) {
		ClusteringServiceImpl test = new ClusteringServiceImpl();
		System.out.println(test.calculateDIstance("1", "2"));

	}

}
