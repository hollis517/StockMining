package org.a805.service.impl.corpgovern;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class AgglomerateClustering {
	private int[] entity;

	/**
	 * ��ʼ����������ÿ��ʵ�嵥����Ϊһ��
	 * 
	 * @param totalEntities
	 */
	public void init(int totalEntities) {
		// �����±��1��ʼ������0���Ա������ı�ʶ���
		entity = new int[totalEntities + 1];
		// ÿ��ʵ���ʼ����������Ϊһ��
		for (int i = 1; i <= totalEntities; i++) {
			entity[i] = i;
		}
	}

	/**
	 * ���ຯ������ָ����������ϲ�Ϊһ��
	 * 
	 * @param index1
	 * @param index2
	 */
	public boolean merge(int index1, int index2) {
		Set<Integer> categories = this.getCategory();
		// ��������û�в����е���ţ����ش��󣬺ϲ�ʧ��
		if (!categories.contains(Integer.valueOf(index1))
				|| !categories.contains(Integer.valueOf(index2))) {
			return false;
		}
		//�ϲ�����������ͬ��ֱ�ӷ��سɹ�
		if (index1 == index2) {
			return true;
		}

		// ��úϲ��������У���С���Ǹ���ż���ʹ�ã��ϴ������������ʹ��index1ʼ�ս�С
		if (index1 > index2) {
			int temp = index1;
			index1 = index2;
			index2 = temp;
		}
		// �����������index2�Ļ���index1����ʾ��������Ϊһ�࣬���ʹ�ý�С��index1
		for (int i = 1; i <= entity.length - 1; i++) {
			if (entity[i] == index2) {
				entity[i] = index1;
			}
		}
		return true;

	}

	/**
	 * �õ�Ŀǰ�����
	 * 
	 * @return
	 */
	public Set<Integer> getCategory() {
		Set<Integer> categories = new TreeSet<Integer>();
		for (int i = 1; i <= entity.length - 1; i++) {
			categories.add(Integer.valueOf(entity[i]));
		}
		return categories;

	}

	/**
	 * �õ�Ŀǰ�������
	 * 
	 * @return
	 */
	public int getCategoryNumbers() {
		Set<Integer> categories = new TreeSet<Integer>();
		for (int i = 1; i <= entity.length - 1; i++) {
			categories.add(Integer.valueOf(entity[i]));
		}
		return categories.size();

	}

	/**
	 * �õ������õ����ݽṹ
	 * 
	 * @return
	 */
	public int[] getEntities() {
		return entity;
	}

	/**
	 * ���ؾ�����
	 * 
	 * @return
	 */
	public Map<String, Set<Integer>> getResult() {
		// �õ����
		Set<Integer> categories = getCategory();
		// Я�������Map
		Map<String, Set<Integer>> result = new TreeMap<String, Set<Integer>>();
		// ��ÿ������½�һ����������TreeSet
		for (Integer i : categories) {
			result.put(String.valueOf(i), new TreeSet<Integer>());
		}
		// ����entity����ÿ��Ԫ�ع鵽��Ӧ�����Ҳ����Set
		for (int i = 1; i <= entity.length - 1; i++) {
			Set<Integer> tempSet = result.get(String.valueOf(entity[i]));
			tempSet.add(Integer.valueOf(i));
			result.put(String.valueOf(entity[i]), tempSet);
		}

		return result;
	}

	// �����õ�������
	public static void main(String a[]) {
		AgglomerateClustering test = new AgglomerateClustering();
		test.init(100);
		test.merge(2, 5);
		test.merge(3, 6);
		for (int i = 10; i < 50; i++) {
			test.merge(2, i);
		}

		// ��ӡ������
		Map<String, Set<Integer>> result = test.getResult();
		Iterator it = result.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Set<Integer>> map = (Map.Entry<String, Set<Integer>>) it
					.next();
			System.out.print(map.getKey() + "     ");

			Set<Integer> set = map.getValue();
			for (Integer i : set) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

}
