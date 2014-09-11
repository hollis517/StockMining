package org.a805.service.impl.corpgovern;

import org.a805.service.corpgovern.GetIndustryAvgService;
import org.a805.service.corpgovern.GuiyueService;

import com.hibernate.dao.TCompanyIndexDAO;
import com.hibernate.entity.TCompanyIndexId;

public class GuiyueServiceImpl implements GuiyueService {
	private TCompanyIndexDAO companyIndexDAO;
	private GetIndustryAvgService industryAvgService;

	public TCompanyIndexDAO getCompanyIndexDAO() {
		return companyIndexDAO;
	}

	public void setCompanyIndexDAO(TCompanyIndexDAO companyIndexDAO) {
		this.companyIndexDAO = companyIndexDAO;
	}


	public GetIndustryAvgService getIndustryAvgService() {
		return industryAvgService;
	}

	public void setIndustryAvgService(GetIndustryAvgService industryAvgService) {
		this.industryAvgService = industryAvgService;
	}

	/**
	 * ��ƽ��ֵ��Լ
	 */
	public String GuiyueByAverage(String stockId, String indexId,
			String indexDate, int weight) {
		// TODO Auto-generated method stub
		int geneLenth = 1;
		
		TCompanyIndexDAO companyIndexDAO = new TCompanyIndexDAO();
		industryAvgService = new GetIndustryAvgServiceImpl();

		// ��ָ��Ļ���Ƭ��
		String geneSegment = "";
		// ָ��ֵ
		double indexValue = 0.0;
		// ָ��ƽ��ֵ
		double indexAverageValue;

		// ��һ�� ��ȡָ��ֵ�͹�Լ��׼--ƽ��ֵ
		// ���ò�ѯÿ��ֵ������
		TCompanyIndexId tCompanyIndexId = new TCompanyIndexId();
		tCompanyIndexId.setIndexDate("2004-00-00");
		tCompanyIndexId.setStockId(stockId);
		tCompanyIndexId.setIndexId(indexId);
		// ��ȡָ��ֵ
		if (companyIndexDAO.findById(tCompanyIndexId) != null) {
			indexValue = companyIndexDAO.findById(tCompanyIndexId)
					.getIndexValue();
		}
		else{
			//��ָ��û��ֵ����0  ��ʱ��ôд
			indexValue = 0.0;
		}
		// ��ȡƽ��ֵ
		indexAverageValue = 0.0;
		indexAverageValue = industryAvgService.getIndustryAvg(stockId, indexId, indexDate);


		

		// �ڶ��� �Ƚ�ָ��ֵ��ƽ��ֵ����Լ�ó�����ʱ�λ���
		String tempGene = null;
		if (indexValue >= indexAverageValue)
			tempGene = "1";
		else
			tempGene = "0";

		// ������ ��Ȩ�صó���ָ������ʱ�λ���
		for (int i = 0; i < weight; i++) {
			geneSegment += tempGene;
		}
		// ����16λ����ಹ0
		while (geneSegment.length() < geneLenth) {
			geneSegment = "0" + geneSegment;
		}
		

		return geneSegment;
	}

	public static void main(String a[]) {
		GuiyueServiceImpl test = new GuiyueServiceImpl();
		String geneSegment = null;
		geneSegment = test.GuiyueByAverage("000002", "000005", "2004-00-00", 2);
		System.out.println(geneSegment);

	}


}
