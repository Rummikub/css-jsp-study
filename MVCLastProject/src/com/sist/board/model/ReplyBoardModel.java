package com.sist.board.model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller //메모리 할당
public class ReplyBoardModel {
	
	@RequestMapping("reply/list.do")
	public String reply_list(HttpServletRequest request, HttpServletResponse response)
	{
		//요청한 데이터를 가지고 옴 ==> DAO연결
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=15;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		//=>DAO로 전송
		List<BoardVO> list=ReplyBoardDAO.replyListData(map);
		int totalpage=ReplyBoardDAO.replyTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		/*
		 		class Model  ==> Spring에서의 request
		 		{
		 			HttpServletRequest request;
		 			public Model(HttpServletRequest request)
		 			{
		 				this.request=request;
		 			}
		 			public void addAttribute(String key,Object obj)
		 			{
		 				request.setAttribute(key,obj);
		 			}
		 		}
		 		
		 		Model model=new Model(request);
		 		model.addAttribute("list",list)  == request.setAttribute를 스프링에서 코딩하는 방법
		 		}
		 		
		 		DispatcherServlet = Front Controller
		 		Model = Controller
		 		View = JSP
		 		Model = request
		 */
		request.setAttribute("main_jsp", "../reply/list.jsp");
		return "../main/main.jsp";
	}

	
	@RequestMapping ("reply/detail.do")			
				// detail -> 값출력 req실어서 값을 보내주는 용도
			public String replyDetailData(HttpServletRequest request,HttpServletResponse response)
			{
					String no=request.getParameter("no");
					BoardVO vo=ReplyBoardDAO.replyDetailData(Integer.parseInt(no));
					
					//detail.jsp로 전송
					request.setAttribute("vo", vo);
					request.setAttribute("main_jsp","../reply/detail.jsp");
					return "../main/main.jsp";
					
			}
	
	
	@RequestMapping("reply/insert.do")
	public String replyInsert(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("main_jsp","../reply/insert.jsp");
		return "../main/main.jsp";
		
}
	@RequestMapping ("reply/insert_ok.do")
	// insert -> _ok 화면이동  (model = 비즈니스로직 , 모든 기능 코딩은 자바에 있음)
	public String replyInsert_ok(HttpServletRequest request, HttpServletResponse response)
	{    
		  try
		  {
			request.setCharacterEncoding("UTF-8");
			
		  } catch (Exception ex) {}
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			
			BoardVO vo=new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			ReplyBoardDAO.replyInsert(vo);
					   
		 
		  return "redirect:../reply/list.do";
	}
	
	@RequestMapping ("reply/update.do")
	  public String boardUpdateData(HttpServletRequest request,HttpServletResponse response)
	   {
		 try
		 {
		   String no=request.getParameter("no");
		   BoardVO vo=ReplyBoardDAO.boardUpdateData(Integer.parseInt(no));
		   
		   request.setAttribute("vo", vo);
		   request.setAttribute("main_jsp", "../reply/update.jsp");
		   
		 }catch (Exception ex) {}
		 return "../main/main.jsp";
	    }
	
	@RequestMapping ("reply/update_ok.do")
	public String boardUpdate(HttpServletRequest request, HttpServletResponse response)
	{    
		  try
		  {
			  
			request.setCharacterEncoding("UTF-8");
		  } catch (Exception ex) {}
			String no=request.getParameter("no");
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			
			BoardVO vo=new BoardVO();
			vo.setNo(Integer.parseInt(no))	;
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
	
		 /* if(vo.getPwd().equals("pwd"))*/
			ReplyBoardDAO.boardUpdate(vo);
		
			// detail로 가야되는 이유.. 내용이 바뀔 수 있음...
		  return "redirect: ../reply/detail.do?no="+no;

	
	}
	
	@RequestMapping("reply/password_check.do")
	public String reply_pwd_check(HttpServletRequest request, HttpServletResponse response)
	{    
		
			
			String no=request.getParameter("no");
			String pwd=request.getParameter("pwd");
			
			String db_pwd=ReplyBoardDAO.replyGetPwd(Integer.parseInt(no));
			int res=0;
			if(db_pwd.equals(pwd))
			{
				res=1;
			}else
			{
				res=0;
			}
			request.setAttribute("result", res);
	
		 /* if(vo.getPwd().equals("pwd"))
			ReplyBoardDAO.boardUpdate(vo);  이렇게 하는게 아니라.... update.jsp에서 ajax를 얹어줄건데 DB랑 연동도 해야됨*/
		  
		  return "../reply/password_check.jsp";

	
	}
	//화면을 띄워야 되면 main으로 리턴
	@RequestMapping("reply/reply.do")
	public String reply_reply(HttpServletRequest request,HttpServletResponse response)
	{
		String pno=request.getParameter("no");// detail.jsp에서 넘어온 번호 처리
		request.setAttribute("pno", pno);// reply.jsp로 보냄 (pno:글번호)
		request.setAttribute("main_jsp", "../reply/reply.jsp");
		return "../main/main.jsp";
	}
	
	//DB연동
	@RequestMapping("reply/reply_ok.do")
	public String reply_reply_ok(HttpServletRequest request, HttpServletResponse response)
	{
		//받아온 값을 DAO로 연결
		try{
			
			request.setCharacterEncoding("UTF-8");
			
		  } catch (Exception ex) {}
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			//vo에 넘길것은 아니고.. 윗번호를 받아오는 용도
			String pno=request.getParameter("pno");

			BoardVO vo=new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);

			//DAO연결 ==> mapper로 이동하겠지  메소드를 만든 후에 다시 돌아와서 | 메소드 호출하고 매개변수 담아주면 끝.
			ReplyBoardDAO.replyReplyInsert(Integer.parseInt(pno),vo);
			
		return "redirect:../reply/list.do"; //reply_list 메소드를 재호출하기 위해 or not, reply_list의 코딩을 또 반복하게 되니까 안됨
		//.do -> 메소드 호출
	}
	
	@RequestMapping("reply/delete.do")
	public String reply_delete(HttpServletRequest request,HttpServletResponse response)
	{	//비밀번호를 입력하는 창을 띄워라
		String no=request.getParameter("no");
		request.setAttribute("no",no);
		request.setAttribute("main_jsp", "../reply/delete.jsp"); 
		return "../main/main.jsp"; 
	}
	//★★★★★★★★정석 JSP★★★★★★★★ 
	@RequestMapping("reply/delete_ok.do")
	public String reply_delete_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		
		//DAO연결 (처리해줘야 되잖아)
		boolean bCheck=ReplyBoardDAO.replyDelete(Integer.parseInt(no), pwd);
		request.setAttribute("bCheck", bCheck);
		return "../reply/delete_ok.jsp"; //비밀번호 여부에따라 처리해야되니까 일로 보냈다는데....★★★★★★★★★★★★ 
		//★★★★★★★★isLogin에서는 AJAX로 처리해버렸다고 하네용.. MemberModel공부를 해야겠네
	}
}


