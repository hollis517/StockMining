package org.a805.tools;

import java.util.List;

public class PageView<T> {
	/** ��ҳ���� * */
	private List<T> resultlist;
	/** ��ҳ�� * */
	private int totalpage;
	/** ��ǰҳ * */
	private int currentpage = 1;
	/** �ܼ�¼�� * */
	private int totalrecord;
	/** ��ǰ��¼ * */
	private int currentrecord;
	/** ÿҳ��ʾ��¼�� * */
	private int pagesize = 10;
	

	public PageView(int currentpage, int pagesize) {
		this.currentpage = currentpage;
		this.pagesize = pagesize;
		setCurrentrecord();
	}

	
	public List<T> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}

	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	
	public int getCurrentrecord() {
		return currentrecord;
	}
	public void setCurrentrecord() {
		this.currentrecord = (this.currentpage - 1) * this.pagesize;
	}
	
	

	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage() {
		this.totalpage = this.totalrecord % this.pagesize == 0 ? this.totalrecord
				/ this.pagesize
				: this.totalrecord / this.pagesize + 1;
	}

	
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage();
	}




}
