package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class FoodDAO {

			// 파싱결과 저장공간 
			private static SqlSessionFactory ssf;
			static
			{
				try {
					//XML읽기
					Reader reader=Resources.getResourceAsReader("config.xml");
					//XML저장
					ssf=new SqlSessionFactoryBuilder().build(reader);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
			
			// 1단계 목록 가져오기
			public static List<CategoryVO> categoryListData()
			{
				//.Conn
				SqlSession session=ssf.openSession();
				List<CategoryVO> list=session.selectList("categoryListData");
				session.close();
				return list;
			}
			
			// 2단계 화면 출력단
			public static List<FoodVO> middleListData(int cno)
			{
				SqlSession session=ssf.openSession();
				List<FoodVO> list=session.selectList("middleListData",cno);
				session.close();
				return list;
			}
			
			//3단계 상세보기 출력단
			public static FoodVO detailData(int no)
			{
				SqlSession session=ssf.openSession();
				FoodVO  list=session.selectOne("detailData",no);
				session.close();
				return list;
			}
			
			
			
			
}
