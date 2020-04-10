package com.sist.recipe.model;
// DB연결해서 값 뿌리기
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.RecipeDAO;

import java.util.*;
import com.sist.vo.*;

@Controller
public class RecipeModel {

		@RequestMapping("recipe/recipe.do")
		public String recipe_recipe(HttpServletRequest request, HttpServletResponse response)
		{ 
			// 요청 (req) 처리 (기준은 늘 main)  =>DAO
			String page=request.getParameter("page");
			if(page==null)
					page="1";
			int curpage=Integer.parseInt(page);
			int rowSize=20;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			//Map
			Map map=new HashMap();
			map.put("start", start);
			map.put("end", end);
			
			List<RecipeVO> list=RecipeDAO.recipeListData(map);
			
			
			// 넘치는 글자 자르기
			for(RecipeVO vo :list)
			{
				String title=vo.getTitle();
				if(title.length()>10)
				{
					title=title.substring(0,10).concat("...");
					vo.setTitle(title);
				}
			}
			
			int totalpage=RecipeDAO.recipeTotalPage();
			
			// 1, 11,21,....
			final int BLOCK=10;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			// 85 ( 81~90)
			int allPage=totalpage;
			if(endPage>allPage)
				endPage=allPage;
			
			request.setAttribute("list", list);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("BLOCK", BLOCK);
			request.setAttribute("startPage",startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("allPage", allPage);
			request.setAttribute("main_jsp", "../recipe/recipe.jsp");
			return  "../main/main.jsp";
		}
		
		
		@RequestMapping("recipe/chef.do")
		public String recipe_chef(HttpServletRequest request, HttpServletResponse response)
		{
			String page=request.getParameter("page");
			if(page==null)
					page="1";
			int curpage=Integer.parseInt(page);
			int rowSize=30;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			//Map
			Map map=new HashMap();
			map.put("start", start);
			map.put("end", end);
			
			List<ChefVO> list=RecipeDAO.chefListData(map);
			int totalpage=RecipeDAO.chefTotalPage();
			
			final int BLOCK=5;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			// 85 ( 81~90)
			int allPage=totalpage;
			if(endPage>allPage)
				endPage=allPage;
			
			request.setAttribute("list", list); 
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("BLOCK", BLOCK);
			request.setAttribute("startPage",startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("allPage", allPage);
			request.setAttribute("main_jsp","../recipe/chef.jsp");
			return "../main/main.jsp";
		}
		
		@RequestMapping("recipe/recipe_detail.do")
		public String recipe_detail(HttpServletRequest request,HttpServletResponse response)
		{
			String no=request.getParameter("no");
			//DAO
			int count=RecipeDAO.recipeCount(Integer.parseInt(no));
			if(count!=0)	
			{
				RecipeDetailVO vo=RecipeDAO.recipeDetailData(Integer.parseInt(no));
				vo.setFoodmake(vo.getFoodmake().replace("\n", "@"));
				request.setAttribute("vo", vo);
			}
			
			request.setAttribute("count",count);
			request.setAttribute("main_jsp", "../recipe/recipe_detail.jsp");
			return "../main/main.jsp";
		}
		
		@RequestMapping("recipe/chef_detail.do")
		public String recipe_chef_detail(HttpServletRequest request,HttpServletResponse response)
		{
			String page=request.getParameter("page");
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			int rowSize=20;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			String chef_name=request.getParameter("name");  //chef_detail.do?name <-- req
			//POST ; setCharacterEncoding | GET ; 그냥 가도 됨.
			System.out.println("chef_name="+chef_name);
			
			//DAO와 연결 (3개를 DAO랑 주고받아야되니까 MAP)
			Map map=new HashMap();
			map.put("start", start);
			map.put("end", end);
			map.put("chef", chef_name);
			
			List<RecipeVO> list=RecipeDAO.chefDetailData(map);
			// 넘치는 글자 자르기
			for(RecipeVO vo :list)
			{
				String title=vo.getTitle();
				if(title.length()>10)
				{
					title=title.substring(0,10).concat("...");
					vo.setTitle(title);
				}
			}
			int totalpage=RecipeDAO.chefDataTotalPage(chef_name);
			
			request.setAttribute("list", list);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("chef", chef_name);
			
			//메인에 출력을 해줘야 되는거면, include (아니면, 실행하는 jsp로 넘겨주면 된다..) ★★★★★
			request.setAttribute("main_jsp", "../recipe/chef_detail.jsp");
			return "../main/main.jsp";
		}
		
		@RequestMapping("recipe/recipe_find.do")
		public String recipe_find(HttpServletRequest request,HttpServletResponse response)
		{
			String[] data={
					"전체","밑반찬","메인반찬","국/탕","찌개",
					"초스피드","손님접대","밥/죽/떡","술안주","면/만두",
					"일상","빵","다이어트","디저트","샐러드",
					"양식","김치/젓갈","도시락","간식","돼지고기",
					"영양식","과자","양념","차/음료/술","닭고기",
					"야식","채소류","볶음","스프","소고기",
					"해물류","푸드스타일","육류","달걀/유제",
					"조림","이유식","무침","해장","명절",
					"버섯류","가공식품류","과일류","튀김","끓이기",
					"찜","비빔","밀가루","건어물류","절임",
					"굽기","삶기","회","쌀","콩/견과류",
					"곡류","데치기","퓨전"
			};
			//말풍선 도움말 만들기
			request.setAttribute("data", data);
			// 여기는 화면만 띄워주는 곳이야. recipe_find.jsp를 봐
			request.setAttribute("main_jsp", "../recipe/recipe_find.jsp");
			return "../main/main.jsp";
		}
		
		//include안되는 파일, ajax로 find_ok에서 읽은결과를  find에서 호출하는 방식으로 코딩
		@RequestMapping("recipe/recipe_find_ok.do")
		public String recipe_recipe_find_ok(HttpServletRequest request,HttpServletResponse response)
		{
			String[] data={
					"전체","밑반찬","메인반찬","국/탕","찌개",
					"초스피드","손님접대","밥/죽/떡","술안주","면/만두",
					"일상","빵","다이어트","디저트","샐러드",
					"양식","김치/젓갈","도시락","간식","돼지고기",
					"영양식","과자","양념","차/음료/술","닭고기",
					"야식","채소류","볶음","스프","소고기",
					"해물류","푸드스타일","육류","달걀/유제",
					"조림","이유식","무침","해장","명절",
					"버섯류","가공식품류","과일류","튀김","끓이기",
					"찜","비빔","밀가루","건어물류","절임",
					"굽기","삶기","회","쌀","콩/견과류",
					"곡류","데치기","퓨전"
			};
			//사용자가 번호를 보내줘야 겠지 (getParameter)
			String no=request.getParameter("no");
			//DAO연결
			List<RecipeVO> list=RecipeDAO.recipeFindData(data[Integer.parseInt(no)-1].replace("/","|"));
			for(RecipeVO vo :list)
			{
				String title=vo.getTitle();
				if(title.length()>12)
				{
					title=title.substring(0,9).concat("...");
					vo.setTitle(title);
				}
			}
			
			request.setAttribute("list", list);
			return "../recipe/recipe_find_ok.jsp";
		}
}
