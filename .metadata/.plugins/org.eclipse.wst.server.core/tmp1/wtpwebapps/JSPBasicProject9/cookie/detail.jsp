<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"></jsp:useBean>
<%
		String no=request.getParameter("no");
		FoodHouseBean vo=dao.foodDetailData(Integer.parseInt(no));
		
		// ★쿠키 생성, 번호가 넘어와야 DAO랑 연결해서 데이터 뿌려주기가 가능하지
		Cookie cookie=new Cookie("food"+no,no);
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <%--   Googlevis 에서 가져옴  --%>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable(<%=vo.getTag()%>);

        var options = {
          title: '리뷰 통계',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<table class="table">
						<tr>
							<%    // 이미지 잘라서 가져오기 ^ 기준으로 4~5개 정도 ! 
										StringTokenizer st=new StringTokenizer(vo.getImage(),"^");
										while(st.hasMoreTokens())
										{
							%>
											<td class="text-center"><img src="<%=st.nextToken()%>" width="98%"></td>			
							<%
										}
							%>
							
							<!-- 
							<c:forTokens items="${vo.image}" delims="^" var="img">
								<td>
									<img src="${img}" width=100%>
								</td>
							</c:forTokens>
							  -->
							
						</tr>
						
						<tr>
							<td class="text-center" colspan=5>
									<h3><%=vo.getTitle() %>&nbsp;<span style="color;#FC6"><%=vo.getScore() %></span></h3>
							</td>	
						</tr>
							<tr>
							<td class="text-right" width="15%">주소</td>
							<td colspan="4">
								<%
										String temp=vo.getAddress();

										String a1=temp.substring(0,temp.indexOf("지"));
										String a2=temp.substring(temp.indexOf("지")+3);
								%>
								<%=a1 %><br>
								<sub style="color:gray"><%=a2 %></sub>
							</td>
						</tr>	<tr>
							<td class="text-right" width="15%">전화번호</td>
							<td colspan=4><%=vo.getTel() %></td>
						</tr>	<tr>
							<td class="text-right" width="15%">음식종류</td>
							<td colspan=4><%=vo. getType()%></td>
						</tr>	
						<tr>
							<td class="text-right" width="15%">가격대</td>
							<td colspan=4><%=vo.getPrice() %></td>
						</tr>
						
						<tr>
							<td class="text-center" colspan=5>
								<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
							</td>
						</tr>
						
							<tr>
							<td class="text-right" colspan=5>
								<a href="#" class="btn btn-sm btn-danger">찜</a>
								<a href="#" class="btn btn-sm btn-success">예약</a>
								<a href="category.jsp" class="btn btn-sm btn-info">목록</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
   	
   	
</body>
</html>