package com.sist.xml;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
/*
		XML 제작, 데이터 읽기 등은 없다 *실무에서! 
 
 							FOR 구조 파악
 							태그명을 잘 맞춰줘야 값을 가져오겠지
 */
public class SawonXML {

		public static void main(String[] args) {
			
			//호출
			SawonXML xml=new SawonXML();
			xml.xmlParser();
		}
		
		public void xmlParser()
		{
			try {
					//파서: xml의 데이터를 읽어오는 것을 말함
				DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
				DocumentBuilder db=dbf.newDocumentBuilder();
				
				//문서 저장
				Document doc=db.parse(new File("C:\\webDev\\webStudy\\JSPIntermediateProject4\\WebContent\\WEB-INF\\sawon.xml"));
				
				//테이블 읽기 ; 최상위 태그 불러오기
				Element table=doc.getDocumentElement();
				System.out.println(table.getTagName()); // sawon 테이블명
				
				
				// list들 (row)읽기
				NodeList list=table.getElementsByTagName("list");
				System.out.println(list.getLength());  // list 몇개?      
				
				for(int i=0;i<list.getLength();i++)
				{
					list=table.getElementsByTagName("name");
					String name=list.item(i).getFirstChild().getNodeValue();
					System.out.println("name="+name);
					
					list=table.getElementsByTagName("addr");
					String addr=list.item(i).getFirstChild().getNodeValue();
					System.out.println("addr="+addr);
					
					list=table.getElementsByTagName("sex");
					String sex=list.item(i).getFirstChild().getNodeValue();
					System.out.println("sex="+sex);
				}
				
			  } catch (Exception e) {
					e.printStackTrace();	
			}
		}
}
