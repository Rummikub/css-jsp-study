package com.sist.temp;

public class MainClass {

	public static void main(String[] args) {
		MainDAO dao=new MainDAO();
		StudentVO vo=dao.studentInfoData(3);
		System.out.println("이름:"+vo.getName());
		System.out.println("국어:"+vo.getKor());
		System.out.println("수학:"+vo.getMath());
		System.out.println("영어:"+vo.getEng());
	}
}
