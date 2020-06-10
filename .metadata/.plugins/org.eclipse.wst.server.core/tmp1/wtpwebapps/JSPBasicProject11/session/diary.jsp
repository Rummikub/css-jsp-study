<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
 
  <%--
  			
  				session : 서버에서 사용자 일부 정보 저장해주는데 클라이언트 마다 한 개 배정되고 request를 이용해  얻어올 수 있다
  				관련된 기능 
  				1) 저장 : setAttribute (key,value ) --> Object   | cookie (key,value) --> String
  				2) 저장된 값 읽기 : Object getAttribute ("key") => 형변환 필수 (제네릭스 없음)
  				3) 기간 설정 : 1800초, 30분이 기본값  => setMaxactiveInterval(60) / min

  				5) 삭제  : removeAttribute("key")   | 전체 > invalidate()
  				
  				cookie(본인 컴) + session(서버) = 임시저장 개념임
  				JSP -> java -> class -> 출력
  				
  				MVC 
  				
  				클라이언트의 요청 (데이터 전송 ) 	-- Controller(Servlet)  -- Model(java) -- View(JSP;html)  
  				즉, 데이터 처리를 먼저 하고 결과값만 뿌려주는 형식
  --%>
  <%
  			Date date=new Date();
  			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d"); //08 8진법 인식해서 문제 생김 MM
  			String today=sdf.format(date);
  			
  			StringTokenizer st=new StringTokenizer(today,"-");
  			
  			String strYear=st.nextToken();
  			String strMonth=st.nextToken();
  			String strDay=st.nextToken();
  			
  			String  sy=request.getParameter("year");
  			
  			if(sy==null)
  				sy=strYear;
  			String sm=request.getParameter("month");
  			if(sm==null)
  				sm=strMonth;
  			
  			int year=Integer.parseInt(sy);
  			int month=Integer.parseInt(sm);
  			int day=Integer.parseInt(strDay);
  			
  			//1일의 요일을 알아야 달력을 출력할 수 있다
  			//1년 1월 1일 ~ 19년 12월 31일 = 총 날 수
  			// +1 ; 1일자 %7 ; 나머지값
  			int total=(year-1)*365
  			+ (year-1)/4
  			- (year-1)/100
  			+(year-1)/400;
  			
  			// 전달 +1 = 금달 1일2020 2월 29일 총합 +1 = 3월1일자
  			int[] lastDay={31,28,31,30,31,30,31,31,30,31,30,31};
  			
  			if((year%4==0 && year/100!=0)||(year%400==0))
  				lastDay[1]=29;
  			else
  				lastDay[1]=28;
  			
  			for(int i=0;i<month-1;i++)
  			{
  				total+=lastDay[i];
  			}
  			
  			//+1일
  			total++;
  			
  			//각 달의 1일자 구함
  			int week=total%7; 
  			
  			String[] strWeek={"일","월","화","수","목","금","토"};
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>diary</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
 .row {
 	margin: 0px auto;
 	width: 900px;
 }
</style>

<!-- java메소드 change()생성 -->
<script type="text/javascript">
function change() {
		var f=document.frm;
		f.submit();
}
</script>
</head>
<body>
	<div class="container">  <!-- SESSION~~~~~~~~~~~~~~  -->
		<h1 class="text-center"><%=session.getAttribute("name")%>(<%=session.getAttribute("id")%>)&nbsp;<%=sy %>년 <%=sm %>월 일정</h1>
		<div class="row">
			<form method="POST" name="frm" action="diary.jsp">
			<table class="table">
				<tr>
					<td class="text-left">
						<select name="year" onchange="change()">
							<% 	
										for(int i=2020; i<=2030; i++)
										{
							%>
										<option <%=i==year?"selected":"" %>><%=i %></option>
							<%
										}
							%>
						</select>년도 &nbsp;
						
						<select name="month" onchange="change()">
							<%
										for(int i=1;i<=12;i++)
										{
							%>
											<option <%=i==month?"selected":"" %>><%= i %></option>
							<%	
										}
							%>
						</select>월
					</td>
				</tr>
			</table>
			</form>
			
			<table class="table table-bordered">
				<tr class="danger" height="35">
					<%
					
								String color="";
								int k=0;
								for(String s:strWeek)
								{
									if(k==0)
										color="red";
									else if(k==6)
										color="blue";
									else 
										color="black";
					%>
										<th class="text-center"><h3><font color="<%=color%>"><%=s %></font></h3></th>
					<%
									k++;
								}
					%>
				</tr>
				
				<%		
							color="black";
							// 이번달 출력 0부터 시작하니까 -1
							for(int i=1;i<=lastDay[month]-1;i++)
							{
									if(week==0)
										color="red";
									else if(week==6)
										color="blue";
									else 
										color="black";
									
									if(i==1)
									{
				%>
												<tr>
												<%
												       // week만큼 빈 공백 두기
														for(int j=0;j<week;j++)
														{
												%>
															<td height="140" class="text-left" valign="top">&nbsp;</td>
												<% 
														}
												%>
				<%
									}
									//오늘 날짜 체크
									String back="white";
									if(i==day)
										back="success";
				%>
								<td height="140" class="text-left <%=back %>" valign="top"><h4><font color="<%=color%>">
								
								<!--!!!!!!!!!SESSION얼마나 유지하는지 알아보는 법  _insert-->
									<a href="diary_insert.jsp"><%=i %></a>
								</font></h4></td>
				<%			// 2주차 줄바꾸기
								week++;
								if(week>6)
								{ 
									week=0;		
				%>	
									</tr>
									<tr>
				<% 
								}
							}
				%>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>