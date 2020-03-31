package com.sist.news;

import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.net.*;

//데이터 가져오는 부분
public class NewsManager {

			public List<Item> newsAllData(String fd)
			{
				 List<Item> list=new ArrayList<Item>();
				 try {
					 		URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query="
					 				+ URLEncoder.encode(fd,"UTF-8"));
					 																			//루트
					 		JAXBContext jb=JAXBContext.newInstance(rss.class);
					 		Unmarshaller un=jb.createUnmarshaller();
					 		rss rss=(rss)un.unmarshal(url);
					 		list=rss.getChannel().getItem();
					 
				} catch (Exception e) {	}
				 return list;
			}
}
