package main;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * ����һ�����̵Ĵ�������
 * 
 * @author cylong
 * @since 2014 / 5 / 15 3:54 AM
 */
class CountLines {
	
	public static void main(String[] args){
		CountLines count = new CountLines("src");
		System.out.println("codeSum:     " + count.codeSum);
		System.out.println("blankSum:    " + count.blankSum);
		System.out.println("commentSum:  " + count.commentSum);
		System.out.println("Sum:         " + (count.codeSum + count.blankSum + count.commentSum));
	}
	
	/** �������� */
	int codeSum = 0;
	/** �հ����� */
	int blankSum = 0;
	/** ע������ */
	int commentSum = 0;

	public CountLines(String filePath) {
		try {
			getAllLines(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int getAllLines(String filePath) throws FileNotFoundException {
		try {
			File file = new File(filePath);
			if (file.isDirectory()) {// �ж��Ƿ����ļ���
				File[] codeFiles = file.listFiles();
				int s = codeFiles.length;
				for (int i = 0; i < s; i++) {
					getAllLines(codeFiles[i].getPath());
				}
			} else {
				if (file.getName().endsWith(".java")) {// �ж��Ƿ���java�ļ�
					// ���java�ļ�����
//					System.out.println(file.getName());
					codeSum += getOneFileLines(file)[0];
					blankSum += getOneFileLines(file)[1];
					commentSum += getOneFileLines(file)[2];
				}
			}
		} catch (FileNotFoundException e4) {
			e4.printStackTrace();
		} catch (@SuppressWarnings("hiding") IOException e1) {
			e1.printStackTrace();
		}
		return codeSum;
	}

	public int[] getOneFileLines(File file) {
		int codeSum = 0;// ��������
		int blankSum = 0;// �հ�����
		int commentSum = 0;// ע������
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.matches("^[\\s&&[^\\n]]*$")) {
					blankSum++;
				} else if (line.startsWith("//")) {
					commentSum++;
				} else if (line.startsWith("/*")) {
					commentSum++;
				} else if (line.startsWith("*")) {
					commentSum++;
				} else if (line.endsWith("*/")) {
					commentSum++;
				} else {
					codeSum++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new int[] { codeSum, blankSum, commentSum };
	}
}