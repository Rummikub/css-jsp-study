package com.sist.temp;
import java.util.*;
import java.io.*;
public class MainClass {
//applicationContext.xml에서 bean코딩한것을 안하려고 
	
	
	
	// 앞으로 내가 만든 모델은 ~ package 단위로 등록 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			componentScan("com.sist.model");
	}

	public static List<String> componentScan(String pack)
	{
			List<String> list=new ArrayList<String>();
			try {
				String path="C:\\webDev\\webStudy\\MVCProject4\\src";
				path=path+"\\"+pack.replace(".", "\\");
				File dir=new File(path);
				File[] files=dir.listFiles();
				for(File f:files)
				{
					//System.out.println(f.getName());
					String ext=f.getName().substring(f.getName().lastIndexOf(".")+1);  // .이후 첫번째 자리 = j
					
					//** .java 만 읽어와라
					if(!ext.equals("java"))
						continue; //그만해라
					
					// ** Model(I)+추상클래스는 new객체 생성하면 안되니까 처리
					//** VO도 메모리 할당을 하지 않음 _ 사용자정의 데이터형
					
					String p=pack+"."+f.getName().substring(0,f.getName().lastIndexOf("."));
					System.out.println(p);
				}
			} catch (Exception e) {}
			
			return list;
	}
}
