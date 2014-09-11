package org.a805.tools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
* ������֤��ͼƬ
*/
public class MakeCertPic {
	 //��֤��ͼƬ�п��Գ��ֵ��ַ��������Ը�����Ҫ�޸�
	 private char mapTable[]={
	   '0','1','2','3','4','5','6','7','8','9'
	 };
	/** ���ܣ����ɲ�ɫ��֤��ͼƬ
	 ����weidthΪ����ͼƬ�Ŀ�ȣ�����heightΪ����ͼƬ�ĸ߶ȣ�����osΪҳ��������
	*/
	 public String getCertPic(int width,int height,OutputStream os){
	  if(width<=0)
	   width=60;
	  if(height<=0)
	   height=17;
	  BufferedImage image= new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	  //��ȡͼ��������
	  Graphics g = image.getGraphics();
	  
	  //�趨������ɫ
	  g.setColor(Color.WHITE);
	  g.fillRect(0,0,width,height);
	  //���߿�
	  g.setColor(new Color(156,233,244));
	  g.drawRect(0,0,width+1,height+1);
	  //�����������֤��
	  String strEnsure = "";
	  //4����4Ϊ��֤�룬���Ҫ��������λ����֤�룬��Ӵ���ֵ
	  for(int i = 0;i<4;++i){
	   strEnsure += mapTable[(int) (mapTable.length*Math.random())];
	  }
	  //����֤����ʾ��ͼ���У����Ҫ���ɸ���λ����֤�룬����drawString���
	  g.setColor(Color.BLUE);
	  g.setFont(new Font("Atlantic Inline",Font.BOLD,16));
	  String str = strEnsure.substring(0,1);
	  g.drawString(str,8,13);
	  g.setColor(Color.BLACK);
	  str = strEnsure.substring(1,2);
	  g.drawString(str, 20, 13);
	  g.setColor(Color.RED);
	  str = strEnsure.substring(2,3);
	  g.drawString(str, 35,13);
	  g.setColor(Color.MAGENTA);
	  str = strEnsure.substring(3,4);
	  g.drawString(str, 45, 13);
	  //�������2�����ŵ�
	  Random rand = new Random();
	  for(int i=0; i<10; i++){
	   int x = rand.nextInt(width);
	   int y = rand.nextInt(height);
	   //g.drawOval(x,y,1,1);
	  }
	  //�ͷ�ͼ��������
	  g.dispose();
	  try{
	   //���ͼ�ε�ҳ��
	   ImageIO.write(image, "JPEG", os);
	   
	  }catch (IOException e){
	   return "";
	  }
	  return strEnsure;
	 }

}