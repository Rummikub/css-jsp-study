package com.sist.controller;

// applicationContext.xml을 읽어오고 싶어요
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
//SAX Parsing
import org.xml.sax.helpers.DefaultHandler;

/*
 		Spring XML파싱 순서
 		1) applicationContext.xml 읽기 
 		 
 		 package를 읽음 ====> 갖고오기 위해서 XMLParser  사용

  		2) ComponentScan =>파일명 변경(com.sist.*)\
  		
  		3) 클래스 관리자 => HandlerMapping (메모리 할당된 클래스를 갖고 있음)
  		
  		4) DispatcherServlet <=> HandlerMapping  서로통신 
 
 		5) ViewResolver :JSP찾기
 		
 		6) Model을 통해서 데이터를 JSP로 전송
 		
 		7) forward / sendredirect 
 */


import java.util.*;
public class XMLParser extends DefaultHandler{

		private List<String> list=new ArrayList<String>();
		// Getter
		public List<String> getList() {
			return list;
		}
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				
			try
			{
				if(qName.equals("context:component-scan")) // 이 tag가 열렸으면 
				{
					String pack=attributes.getValue("base-package"); // 이  안에 있는 com.sist.model을 호출해라
					
					System.out.println(pack);
					
					list.add(pack);
				}
			}catch(Exception ex){}
		}
		
}
