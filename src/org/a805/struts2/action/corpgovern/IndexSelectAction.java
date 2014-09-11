package org.a805.struts2.action.corpgovern;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.a805.service.corpgovern.IndexSelectService;
import org.apache.struts2.ServletActionContext;

import com.hibernate.entity.TCompany;
import com.hibernate.entity.TIndex;
import com.opensymphony.xwork2.ActionSupport;

public class IndexSelectAction extends ActionSupport {
	private TIndex index;
	private IndexSelectService indexSelectService;




	public TIndex getIndex() {
		return index;
	}

	public void setIndex(TIndex index) {
		this.index = index;
	}



	public IndexSelectService getIndexSelectService() {
		return indexSelectService;
	}

	public void setIndexSelectService(IndexSelectService indexSelectService) {
		this.indexSelectService = indexSelectService;
	}

	public void queryIndex() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		
		String indexType = request.getParameter("indexType");
		
		TIndex tIndex = new TIndex();
		tIndex.setIndexType(indexType);
		

		List<TIndex> listTIndex = indexSelectService.queryIndex(tIndex);
		
		printIndexQueryResult(listTIndex);

		
	}
	
	/**
	 * ѡ��ָ��
	 */
	public void selectIndex() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setCharacterEncoding("UTF-8");
		// ����ҳ�洫���Ĳ���
		String[] indexes = request.getParameterValues("left");

		// ��ѡ��Ʊid�ļ���
		TreeSet<String> selectedIndexId = new TreeSet<String>();
		// ��ȡ��ѡ��Ʊid
		if (session.getAttribute("selectedIndexId") != null)
			selectedIndexId = (TreeSet<String>) session
					.getAttribute("selectedIndexId");

		// ��ӱ���ѡ��Ĺ�Ʊid
		for (int i = 0; i < indexes.length; i++) {
			selectedIndexId.add(indexes[i]);
		}
		// ��������µ�session
		session.setAttribute("selectedIndexId", selectedIndexId);

		printSelectedIndexHTML(selectedIndexId);
	}
	
	/**
	 * ȡ��ѡ���ָ��
	 */
	public void unSelectIndex() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setCharacterEncoding("UTF-8");
		// ����ҳ�洫���Ĳ���
		String[] indexes = request.getParameterValues("right");
		// ��ȡ��ѡ��Ʊid
		TreeSet<String> selectedIndexId = (TreeSet<String>) session
		.getAttribute("selectedIndexId");
		// ɾ������ѡ��Ĺ�Ʊid
		for (int i = 0; i < indexes.length; i++) {
			selectedIndexId.remove(indexes[i]);
		}
		// ��������µ�session
		session.setAttribute("selectedIndexId", selectedIndexId);

		printSelectedIndexHTML(selectedIndexId);
	}
	
	/**
	 * ���ѡ���ָ��
	 */
	public void clearAllIndex() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setCharacterEncoding("UTF-8");
		
		// ��ȡ��ѡ��Ʊid
		TreeSet<String> selectedIndexId = (TreeSet<String>) session
		.getAttribute("selectedIndexId");
		// ���ѡ��Ĺ�Ʊid
		selectedIndexId.clear();
		
		// ��������µ�session
		session.setAttribute("selectedIndexId", selectedIndexId);
		
		printSelectedIndexHTML(selectedIndexId);
		
	}
	/**
	 * ˢ��
	 */
	public void refreshIndex() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setCharacterEncoding("UTF-8");
		
		// ��ȡ��ѡ��Ʊid
		TreeSet<String> selectedIndexId = (TreeSet<String>) session
		.getAttribute("selectedIndexId");
		
		printSelectedIndexHTML(selectedIndexId);
		
	}
	/**
	 * ��̬���ɲ�ѯ�����HTML����
	 * @param selectedStockId
	 */
	public void printIndexQueryResult(List<TIndex> listTIndex){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response
					.getWriter()
					.write(
							"<select name='left' id='left' size='20' multiple class='selectStyle'>");
			for (TIndex index : listTIndex) {
				response.getWriter().write(
						"<option value='" + index.getIndexId().toString() + "'>" + index.getIndexId().toString()+ "   " + index.getIndexName().toString()
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
	public void printSelectedIndexHTML(TreeSet<String> selectedIndexId){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response
					.getWriter()
					.write(
							"<select name='right' id='right' size='20' multiple class='selectStyle'>");
			for (String indexId : selectedIndexId) {
				response.getWriter().write(
						"<option value='" + indexId + "'>" + indexId + "   " + indexSelectService.findIndexById(indexId).getIndexName().toString()
								+ "</option>");
			}
			response.getWriter().write("</select>");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
