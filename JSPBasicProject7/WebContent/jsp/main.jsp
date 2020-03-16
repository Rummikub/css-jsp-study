<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%-- JSP action tag 
 1. include 	; JSP 안 특정부분 다른 JSP첨부 => 모듈화   : pageContext.include("fileName")  (동적변수)

 2. forward	: request계속 유지 (MVC)
 3. useBean : 메모리 할당  ~ 아이디와 객체명이 동일해야 함 :  <jsp:useBean id="vo" class="com.sist.dao.MemeberVO">  ~~~~ Member vo=new MemberVO();
 4. setProperty	:setXxx()에 값을 채움 ==> VO,DTO,Bean(JSP)
  	Bean 
  	1) data관리 빈 = 데이터 모아 관리 (읽기/쓰기) : getter/ setter
  	2) data 액션 빈 = VO값을 설정하는 클래스 ~ DAO
 	<java BEAN>
  	public class SawonVO {
  		private int sabun;
  		private String name;
  		private String dept; 		
  		getter/ setter
  	}
  	=> SawonVO vo=new SawonVO();
  		vo.setSabun(1);
  		...
  				=> <jsp:useBean id="vo" class="SawonVO">
  							vo.setSabun(1);
  							<jsp:setProperty name="vo" property="sabun" value="1"/>
  						</jsp:useBean>
  						
  				==?> 코딩이 더 길어지는걸!
  				String sabun=request.getParameter("sabun");
  				
  				SawonVO vo=new SawonVO();
  				vo.setSabun(Integer.parseInt(sabun));
 				
 				<jsp:useBean id="vo" class="SawonVO">
  					<jsp:setProperty name="vo" property="*"/>  ==> 모든 값의 parameter를 채워라
  				</jsp:useBean>
  		★ @include의 단점 : 합쳐질 때 소스 그대로 첨부되기 때문에 공통변수 오류가 자주 남 (정적변수) ; 파일 하나만 씀  
  		=> 특히 ,변수선언 할 때 문제임
  		
 --%>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>