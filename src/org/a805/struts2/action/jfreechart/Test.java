package org.a805.struts2.action.jfreechart;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;

public class Test extends ActionSupport{
	 private JFreeChart chart;//���������������chart������������������


	    public JFreeChart getChart()//getChart()�����Ǳ���ģ�setChart()���Բ�д.
	    {                            //��action�е�chart���Ե�get�����У�����chart����Ȼ���������plot�������ɫ���Լ�legend��ɫ������
	    
	        chart = ChartFactory.createBarChart("��Ȥͳ�ƽ��", "��Ŀ", "���", this
	                .getDataset(), PlotOrientation.VERTICAL, false, false, false);
	        
	        
	        chart.setTitle(new TextTitle("��Ȥͳ�ƽ��",new Font("����",Font.BOLD,22)));
	        
	        CategoryPlot plot = (CategoryPlot)chart.getPlot();
	        
	        CategoryAxis categoryAxis = plot.getDomainAxis();
	        
	        categoryAxis.setLabelFont(new Font("����",Font.BOLD,22));
	        
	        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//���ýǶ�
	        
	        NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
	        
	        numberAxis.setLabelFont(new Font("����",Font.BOLD,22));
	        
	        
	      
	        CategoryAxis domainAxis=plot.getDomainAxis();
	         //ˮƽ�ײ��б�
	         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));
	         //ˮƽ�ײ�����
	         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));
	         //��ֱ����
	         ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
	         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
	          
	        
	        
	        return chart;
	    }


	    @Override
	    public String execute() throws Exception
	    {
	        return SUCCESS;
	    }

	  

	    @SuppressWarnings("unchecked")
	    private CategoryDataset getDataset() //�õ����ݼ���
	    {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	        dataset.setValue(10,"a","������Ա");
	        dataset.setValue(20,"b","�г���Ա");
	        dataset.setValue(40,"c","������Ա");
	        dataset.setValue(15,"d","������Ա");

	        return dataset;
	    }

}
