<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"></jsp:useBean>
 <%
 		List<CategoryBean> list=dao.categoryListData();
 		
 		//★쿠키 읽기 (출력은 detail.jsp)
 		//image하나씩 가져와서 뿌려주기 위해 list<String>잡아도 됨
 		List<FoodHouseBean> fList=new ArrayList<FoodHouseBean>();
 		Cookie[] cookies=request.getCookies();
 		for(int i=0;i<cookies.length;i++)
 		{
 												//"food"+no =key  ==> 차후에는 id *(member)
 			if(cookies[i].getName().startsWith("food"))
 			{
 				String no=cookies[i].getValue();
 				FoodHouseBean vo=dao.foodDetailData(Integer.parseInt(no));
 				fList.add(vo);
 			}
 		}
 %>
  
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>망고플레이트 따라하기</title>
<!-- BS PANEL에서 받아옴 -->
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<h1 class="text-left">믿고 보는 맛집 리스트</h1>
		<div class="row">	
			<%
					for(CategoryBean vo:list)
					{
			%>
						<div class="col-md-3">
						   <div class="panel panel-warning">
						      <div class="panel-heading">
						      <%=vo.getTitle() %><br>
						      <sub><%=vo.getSubject()  %></sub>
						      </div>
						      <div class="panel-body">
						      	<a href="food.jsp?cateno=<%=vo.getCateno()%>"><img src="<%=vo.getPoster() %>" width=100%></a>
						      </div>
						    </div>
						</div>
			<%
					}
			%>
		</div>
		
		<!-- 쿠키 출력  -->
		<hr>
			<div class="row">
				<h3>최근 방문한 맛집</h3>
			<%
						int j=0;
						for(int i=fList.size()-1;i>=0;i--)
						{
								FoodHouseBean vo=fList.get(i);
								if(j>5)
										break;
			%>
								<div class="col-md-2">
										    <div class="thumbnail">
										      <a href="detail.jsp?no=<%=vo.getNo()%>">
										        <img src="<%=vo.getImage() %>" alt="Lights" style="width:250px; height:150px	">
										      	 <div class="caption">
										          <p><%=vo.getTitle() %></p>
								       			 </div>
								    		 </a>
								    </div>
								</div>
			<%
								j++;
						}
			%>
			</div>
	</div>
</body>
</html>