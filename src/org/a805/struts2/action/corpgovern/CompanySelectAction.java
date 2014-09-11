package org.a805.struts2.action.corpgovern;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.a805.service.corpgovern.CompanySelectService;
import org.apache.struts2.ServletActionContext;

import com.hibernate.entity.TCompany;
import com.hibernate.entity.TIndex;
import com.opensymphony.xwork2.ActionSupport;

public class CompanySelectAction extends ActionSupport {
	private TCompany company;
	private CompanySelectService companySelectService;

	public TCompany getCompany() {
		return company;
	}

	public void setCompany(TCompany company) {
		this.company = company;
	}



	public CompanySelectService getCompanySelectService() {
		return companySelectService;
	}

	public void setCompanySelectService(CompanySelectService companySelectService) {
		this.companySelectService = companySelectService;
	}

	public void queryCompany() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		
		String classificationOfCsrc = request.getParameter("classificationOfCsrc");
		String classificationOfGics = request.getParameter("classificationOfGics");
		String area = request.getParameter("area");
		
		TCompany tcompany = new TCompany();
		tcompany.setClassificationOfCsrc(classificationOfCsrc);
		tcompany.setClassificationOfGics(classificationOfGics);
		tcompany.setArea(area);
		

		List<TCompany> listTCompany = companySelectService.queryCompany(tcompany);

		printCompanyQueryResult(listTCompany);
	}

	/**
	 * ѡ��˾
	 */
	public void selectCompany() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setCharacterEncoding("UTF-8");
		// ����ҳ�洫���Ĳ���
		String[] companys = request.getParameterValues("left");

		// ��ѡ��Ʊid�ļ���
		TreeSet<String> selectedStockId = new TreeSet<String>();
		// ��ȡ��ѡ��Ʊid
		if (session.getAttribute("selectedStockId") != null)
			selectedStockId = (TreeSet<String>) session
					.getAttribute("selectedStockId");

		// ��ӱ���ѡ��Ĺ�Ʊid
		for (int i = 0; i < companys.length; i++) {
			selectedStockId.add(companys[i]);
		}
		// ��������µ�session
		session.setAttribute("selectedStockId", selectedStockId);

		printSelectedCompanyHTML(selectedStockId);
	}
	/**
	 * ȡ��ѡ��Ĺ�˾
	 */
	public void unSelectCompany() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setCharacterEncoding("UTF-8");
		// ����ҳ�洫���Ĳ���
		String[] companys = request.getParameterValues("right");
		// ��ȡ��ѡ��Ʊid
		TreeSet<String> selectedStockId = (TreeSet<String>) session
		.getAttribute("selectedStockId");
		// ɾ������ѡ��Ĺ�Ʊid
		for (int i = 0; i < companys.length; i++) {
			selectedStockId.remove(companys[i]);
		}
		// ��������µ�session
		session.setAttribute("selectedStockId", selectedStockId);

		printSelectedCompanyHTML(selectedStockId);
	}
	/**
	 * ���ѡ��Ĺ�˾
	 */
	public void clearAllCompany() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setCharacterEncoding("UTF-8");
		
		// ��ȡ��ѡ��Ʊid
		TreeSet<String> selectedStockId = (TreeSet<String>) session
		.getAttribute("selectedStockId");
		// ���ѡ��Ĺ�Ʊid
		selectedStockId.clear();
		
		// ��������µ�session
		session.setAttribute("selectedStockId", selectedStockId);
		
		printSelectedCompanyHTML(selectedStockId);
		
	}
	/**
	 * ˢ��
	 */
	public void refreshCompany() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setCharacterEncoding("UTF-8");
		
		// ��ȡ��ѡ��Ʊid
		TreeSet<String> selectedStockId = (TreeSet<String>) session
		.getAttribute("selectedStockId");
		
		printSelectedCompanyHTML(selectedStockId);
		
	}
	/**
	 * ��̬���ɲ�ѯ�����HTML����
	 * @param selectedStockId
	 */
	public void printCompanyQueryResult(List<TCompany> listTCompany){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response
					.getWriter()
					.write(
							"<select name='left' id='left' size='20' multiple class='selectStyle'>");
			for (TCompany company : listTCompany) {
				response.getWriter().write(
						"<option value='" + company.getStockId().toString() + "'>" + company.getStockId().toString() + "   " + company.getStockShortName().toString()
								+ "</option>");
			}
			response.getWriter().write("</select>");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ��̬����ѡ������HTML����
	 * @param selectedStockId
	 */
	public void printSelectedCompanyHTML(TreeSet<String> selectedStockId){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response
					.getWriter()
					.write(
							"<select name='right' id='right' size='20' multiple class='selectStyle'>");
			for (String stockId : selectedStockId) {
				response.getWriter().write(
						"<option value='" + stockId + "'>" + stockId + "   " + companySelectService.findCompanyById(stockId).getStockShortName().toString()
								+ "</option>");
			}
			response.getWriter().write("</select>");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
