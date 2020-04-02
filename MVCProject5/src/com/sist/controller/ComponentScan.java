package com.sist.controller;
import java.io.*;
import java.util.*;
// java 파일 읽어와서 class로 모아줌
public class ComponentScan {
	
	
																// 프로젝트마다 path가 변경될테니까 --> xml.web의 경로만 바꿔주면 됨
	public List<String> getClassNameRead(String pack,String path)
	{
					List<String> list=new ArrayList<String>();
					
					try
					{
						//String path="C:\\webDev\\webStudy\\MVCProject5\\src";
						//com.sist.model을 \\로 바꿔야 경로명을 읽어올 수 있지
						path=path+"\\"+pack.replace(".", "\\");
						//무슨파일이 있는지 확인해야지 (dir= 디렉토리 - 폴더명) 
						File dir=new File(path);
						File[] files=dir.listFiles();
					
								//java파일이 아닌것들은 제거
								for (File f:files)
								{
											//getName() 파일명
											String name=f.getName();  
											//***********substring안쓰고 가져오기***********
													if(name.endsWith("java"))
													{
														String s=pack+"."+name.substring(0,name.indexOf("."));
														//f.getName() = BoardModel   | name= BoardModel.java 
														//s="com.sist.model"+ "." + "BoardModel"
														list.add(s);
														
													}
								
								}
				
					}catch(Exception ex){}
					
						return list;
	}
	
}
