package org.a805.tools;

import org.a805.service.impl.corpgovern.GuiyueServiceImpl;

public class NumberFormatTransfer {
	/**
	 * ������ת�˽���
	 * 
	 * @param m
	 * @return
	 */
	public static String ch2ch8(String m) {
		if (m.equals(""))
			return "";
		String s;
		s = ch2ch10(m);
		s = ch10ch8(s);
		return s;
	}

	/**
	 * ������תʮ����
	 * 
	 * @param m
	 * @return
	 */
	public static String ch2ch10(String m) {
		if (m.equals(""))
			return "";
		int i, j, len, zhi = 0;
		String shu, ss;
		char ch[] = m.toCharArray();
		len = m.length();
		for (i = 0; i < len; i++) {
			if (ch[i] != '0' && ch[i] != '1')
				return "��������";
			ss = String.valueOf(ch[i]);
			j = Integer.valueOf(ss);
			zhi = zhi + j * (int) Math.pow(2, len - i - 1);
		}
		shu = Integer.toString(zhi);
		return shu;
	}

	/**
	 * ������תʮ������
	 * 
	 * @param m
	 * @return
	 */
	public static String ch2ch16(String m) {
		if (m.equals(""))
			return "";
		String s;
		s = ch2ch10(m);
		s = ch10ch16(s);
		return s;
	}

	/**
	 * �˽���ת������
	 * 
	 * @param m
	 * @return
	 */
	public static String ch8ch2(String m) {
		if (m.equals(""))
			return "";
		String s;
		s = ch8ch10(m);
		s = ch10ch2(s);
		return s;
	}

	/**
	 * �˽���תʮ����
	 * 
	 * @param m
	 * @return
	 */
	public static String ch8ch10(String m) {
		if (m.equals(""))
			return "";
		int i, j, len, zhi = 0;
		String shu, ss;
		char ch[] = m.toCharArray();
		len = m.length();
		for (i = 0; i < len; i++) {
			if (ch[i] < '0' || ch[i] > '7')
				return "��������";
			ss = String.valueOf(ch[i]);
			j = Integer.valueOf(ss);
			zhi = zhi + j * (int) Math.pow(8, len - i - 1);
		}
		shu = Integer.toString(zhi);
		return shu;
	}

	/**
	 * �˽���תʮ������
	 * 
	 * @param m
	 * @return
	 */
	public static String ch8ch16(String m) {
		if (m.equals(""))
			return "";
		String s;
		s = ch8ch10(m);
		s = ch10ch16(s);
		return s;
	}

	/**
	 * ʮ����ת������
	 * 
	 * @param m
	 * @return
	 */
	public static String ch10ch2(String m) {
		if (m.equals(""))
			return "";
		try {
			Integer.valueOf(m);
		} catch (Exception e) {
			return "��������";
		}
		int i, j;
		String shu = "", ss = "";
		j = Integer.valueOf(m);
		if (j < 0)
			return "��������";
		if (j == 0)
			return "0";
		while (j != 0) {
			i = j % 2;
			shu = Integer.toString(i);
			shu = shu.concat(ss);
			ss = shu;
			j = j / 2;
		}
		return shu;
	}

	/**
	 * ʮ����ת�˽���
	 * 
	 * @param m
	 * @return
	 */
	public static String ch10ch8(String m) {
		if (m.equals(""))
			return "";
		try {
			Integer.valueOf(m);
		} catch (Exception e) {
			return "��������";
		}
		int i, j;
		String shu = "", ss = "";
		j = Integer.valueOf(m);
		if (j < 0)
			return "��������";
		if (j == 0)
			return "0";
		while (j != 0) {
			i = j % 8;
			shu = Integer.toString(i);
			shu = shu.concat(ss);
			ss = shu;
			j = j / 8;
		}
		return shu;
	}

	/**
	 * ʮ����תʮ������
	 * 
	 * @param m
	 * @return
	 */
	public static String ch10ch16(String m) {
		if (m.equals(""))
			return "";
		try {
			Integer.valueOf(m);
		} catch (Exception e) {
			return "��������";
		}
		int i, j;
		String shu = "", ss = "";
		j = Integer.valueOf(m);
		if (j < 0)
			return "��������";
		if (j == 0)
			return "0";
		while (j != 0) {
			i = j % 16;
			if (i == 10)
				shu = "A";
			else if (i == 11)
				shu = "B";
			else if (i == 12)
				shu = "C";
			else if (i == 13)
				shu = "D";
			else if (i == 14)
				shu = "E";
			else if (i == 15)
				shu = "F";
			else
				shu = Integer.toString(i);
			shu = shu.concat(ss);
			ss = shu;
			j = j / 16;
		}
		return shu;
	}

	/**
	 * ʮ������ת������
	 * 
	 * @param m
	 * @return
	 */
	public static String ch16ch2(String m) {
		if (m.equals(""))
			return "";
		String s;
		s = ch16ch10(m);
		s = ch10ch2(s);
		return s;
	}

	/**
	 * ʮ������ת�˽���
	 * 
	 * @param m
	 * @return
	 */
	public static String ch16ch8(String m) {
		if (m.equals(""))
			return "";
		String s;
		s = ch16ch10(m);
		s = ch10ch8(s);
		return s;
	}

	/**
	 * ʮ������תʮ����
	 * 
	 * @param m
	 * @return
	 */
	public static String ch16ch10(String m) {
		if (m.equals(""))
			return "";
		int i, j, len, zhi = 0;
		String shu, ss;
		char ch[] = m.toCharArray();
		len = m.length();
		for (i = 0; i < len; i++) {
			if (ch[i] < '0' || (ch[i] > '9' && ch[i] < 'A')
					|| (ch[i] > 'F' && ch[i] < 'a') || ch[i] > 'f')
				return "��������";
			if (ch[i] == 'a' || ch[i] == 'A')
				j = 10;
			else if (ch[i] == 'b' || ch[i] == 'B')
				j = 11;
			else if (ch[i] == 'c' || ch[i] == 'C')
				j = 12;
			else if (ch[i] == 'd' || ch[i] == 'D')
				j = 13;
			else if (ch[i] == 'e' || ch[i] == 'E')
				j = 14;
			else if (ch[i] == 'f' || ch[i] == 'F')
				j = 15;
			else {
				ss = String.valueOf(ch[i]);
				j = Integer.valueOf(ss);
			}
			zhi = zhi + j * (int) Math.pow(16, len - i - 1);
		}
		shu = Integer.toString(zhi);
		return shu;
	}

	/**
	 * �����ƴ�תʮ�����ƴ�
	 * 
	 * @param m
	 * @return
	 */
	public static String gene2gene16(String gene2) {
		String gene16 = "";
		for (int i = 0; i + 4 <= gene2.length(); i = i + 4) {
			String temp = gene2.substring(i, i + 4);
			gene16 = gene16 + ch2ch16(temp);
		}
		return gene16;
	}

	/**
	 * ʮ�����ƴ�ת�����ƴ�
	 * 
	 * @param m
	 * @return
	 */
	public static String gene16gene2(String gene16) {
		String gene2 = "";
		for (int i = 0; i < gene16.length(); i = i + 1) {
			String temp = gene16.substring(i, i + 1);
			String tempResult = ch16ch2(temp);
			// ����4λ�Ĳ�����λ
			while (tempResult.length() < 4) {
				tempResult = "0" + tempResult;
			}
			gene2 = gene2 + tempResult;
		}
		return gene2;
	}

	public static void main(String a[]) {

		System.out.println(gene16gene2("1112"));

	}
}
