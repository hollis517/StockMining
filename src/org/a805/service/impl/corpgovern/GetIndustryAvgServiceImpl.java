package org.a805.service.impl.corpgovern;

import java.util.List;

import org.a805.service.corpgovern.GetIndustryAvgService;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.hibernate.dao.TCompanyDAO;
import com.hibernate.dao.TIndustryIndexAverageDAO;
import com.hibernate.entity.TCompany;
import com.hibernate.entity.TIndustryIndexAverage;
import com.hibernate.entity.TIndustryIndexAverageId;

public class GetIndustryAvgServiceImpl implements GetIndustryAvgService {
	private TIndustryIndexAverageDAO industryIndexAverageDAO;
	private TCompanyDAO companyDAO;

	public TIndustryIndexAverageDAO getIndustryIndexAverageDAO() {
		return industryIndexAverageDAO;
	}

	public void setIndustryIndexAverageDAO(
			TIndustryIndexAverageDAO industryIndexAverageDAO) {
		this.industryIndexAverageDAO = industryIndexAverageDAO;
	}

	public TCompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(TCompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public double getIndustryAvg(String stockId, String indexId,
			String indexDate) {
		// TODO Auto-generated method stub
		TIndustryIndexAverageDAO industryIndexAverageDAO = new TIndustryIndexAverageDAO();
		TCompanyDAO companyDAO = new TCompanyDAO();

		String industryId = "";
		double average = 0.0;
		// ���ҳ��ù�˾���ڵ���ҵ
		Criteria criteria1 = companyDAO.getCriteria();
		criteria1.add(Restrictions.like("stockId", stockId, MatchMode.EXACT));
		List<TCompany> listTCompany = (List<TCompany>) companyDAO
				.findByCriteria(criteria1);

		try {
			industryId = listTCompany.get(0).getClassificationOfCsrc();
			industryId = industryId.substring(0, 1);
		} catch (Exception e) {
			// û��ȡ��������ҵ��������ΪC��ҵ
			industryId = "C";
		}

		// ȥ�ù�˾����ҵƽ��ֵ
		TIndustryIndexAverageId id = new TIndustryIndexAverageId();
		id.setIndustryId(industryId);
		id.setIndexId(indexId);
		id.setIndexDate(indexDate);

		TIndustryIndexAverage industryIndexAverage = (TIndustryIndexAverage) industryIndexAverageDAO
				.findById(id);

		try {
			average = industryIndexAverage.getIndexValue();
		} catch (Exception e) {
			// û��ȡ����ҵƽ��ֵ��������Ϊ1
			average = 1.0;
		}

		return average;
	}

	public static void main(String a[]) {

		GetIndustryAvgServiceImpl test = new GetIndustryAvgServiceImpl();
		System.out.println(test
				.getIndustryAvg("000002", "000002", "2004-00-00"));

	}

}
