package com.sist.controller;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.*;



public class HandlerMapping {
		// 이 값을 ("base-package")  xml에서 읽어올 수 있게 함
		
		/* mvc project 4 의 MainClass  run 결과값 
		com.sist.model.BoardModel
		com.sist.model.HomeModel
		com.sist.model.JoinModel
		com.sist.model.MainModel
		com.sist.model.Model
		com.sist.model.MovieModel
		com.sist.model.MyPageModel
		com.sist.model.ReserveModel
		 */
		private  List<String> list= new ArrayList<String>();
															// ★★★★★★
		public HandlerMapping(String path, String defaultPath)
		{
				try
				{
					 SAXParserFactory spf= SAXParserFactory.newInstance();
					 SAXParser sp=spf.newSAXParser();
					 XMLParser xp=new XMLParser();
					 sp.parse(new File(path),xp);
					 //package List
					 List<String> pList=xp.getList();
					 
					 //** ComponentScan으로 넘기자
					 ComponentScan cs=new ComponentScan();
					 for(String pack:pList)
					 {																				// ★★★★★★
						 List<String> fNames=cs.getClassNameRead(pack,defaultPath);
						 for(String name:fNames)
						 {
							 	list.add(name);  // ** DispatcherRequest가 읽어갈 list의미
						 }
					 }
					 
				}catch(Exception ex) {}
					 
		}
		 public List<String>getList() 
		 {
			 return list;
		 }
}
