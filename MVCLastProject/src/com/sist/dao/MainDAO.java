package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;
public class MainDAO {
		private static SqlSessionFactory ssf;
		static 
		{
			ssf=CreateSqlSessionFactory.getSsf();
		}
		
		public static List<CategoryVO> mainImageData()
		{   //★ 초기값 꼭 줘야 함 (정석)
			SqlSession session=null;
			List<CategoryVO> list=new ArrayList<CategoryVO>();
			try {
				session=ssf.openSession();  									//getconnection 부분
				list=session.selectList("mainImageData");		// preparedstatement에 값 싣기
			} catch (Exception e) {
												//★에러잡기
				System.out.println("mainImageData()"+e.getMessage());
			}finally {
				//★ session의 값이 있으면 닫아라 
				if(session!=null)
				session.close();
			}
			return list;
		}
		
		public static  int recipeCount()
		{
			int count=0;
			// ★ 초기값 안주면 오류난다고 함
			SqlSession session=null;
			try {
				session=ssf.openSession(); 								// getConnection()
				count=session.selectOne("recipeCount");			//recipeCount라는 메서드 호출
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if(session!=null)
					session.close();
				
			}
			return count;
		}
		
		public static List<RecipeVO> mainRecipeData()
		{
			SqlSession session=null;
			List<RecipeVO> list=new ArrayList<RecipeVO>();
			try
			{
				session=ssf.openSession();
				list=session.selectList("mainRecipeData");
			} catch(Exception e)
			{
				System.out.println("문제상황입니다"+e.getMessage());
			}finally
			{
				if(session!=null)
					session.close();
			}
			return list;
		}
		
		public static FoodVO mainFoodDetailData(int no)
		{
			SqlSession session=null;
			FoodVO vo=new FoodVO();
			try {
				session=ssf.openSession();
				vo=session.selectOne("mainFoodDetailData",no);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if(session!=null)
					session.close();
			}
			return vo;
		}
	}
