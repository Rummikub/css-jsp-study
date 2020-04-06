package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;
public class RecipeDAO {
/*	
 * 			request	  		   						request	
 * 							Service(request)							 recipeListData(request)
 * 요청 ---------> DispatcherServlet   -------> Model <-----------------------------------> DAO 
 * 							request를 받는다 <----------------------------------DAO받은 값을 req에 추가
 * 						==================request						request.setAttribute()
 *   							|  forward(request)
 *   						==> JSP로 req전송
 *   									--> req에 담긴 데이터 출력 ---> 메모리에 출력 ---> 사용자 브라우저에서 읽기
 */
		private static SqlSessionFactory ssf;
		static
		{
			ssf=CreateSqlSessionFactory.getSsf();			
		}
		
		// paging
		public static List<RecipeVO> recipeListData(Map map) 
		{
			SqlSession session=null;
			List<RecipeVO> list=new ArrayList<RecipeVO>();
			try
			{
				session=ssf.openSession();
				list=session.selectList("recipeListData",map);
			}catch (Exception ex)
			{
				System.out.println(ex.getMessage());
			}finally
			{
				if(session!=null)
					session.close();
			}
			return list;
		}
		
		public static int recipeTotalPage()
		{
			SqlSession session=null;
			int total=0;
			try
			{
				session=ssf.openSession();
				total=session.selectOne("recipeTotalPage");
			}catch (Exception ex)
			{
				System.out.println(ex.getMessage());
			}finally
			{
				if(session!=null)
					session.close();
			}
			return total;
		}
		
		// paging
		public static List<ChefVO> chefListData(Map map) 
		{
			SqlSession session=null;
			List<ChefVO> list=new ArrayList<ChefVO>();
			try
			{
				session=ssf.openSession();
				list=session.selectList("chefListData",map);
			}catch (Exception ex)
			{
				System.out.println(ex.getMessage());
			}finally
			{
				if(session!=null)
					session.close();
			}
			return list;
		}
		
		//c
		public static int chefTotalPage()
		{
			SqlSession session=null;
			int total=0;
			try
			{
				session=ssf.openSession();
				total=session.selectOne("chefTotalPage");
			}catch (Exception ex)
			{
				System.out.println(ex.getMessage());
			}finally
			{
				if(session!=null)
					session.close();
			}
			return total;
		}
}
