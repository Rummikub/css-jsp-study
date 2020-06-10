/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.50
 * Generated at: 2020-03-19 04:41:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.util.*;

public final class diary_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.text.SimpleDateFormat");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("  ");
      out.write("\r\n");
      out.write("  ");

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
  
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>diary</title>\r\n");
      out.write(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n");
      out.write("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n");
      out.write("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(" .row {\r\n");
      out.write(" \tmargin: 0px auto;\r\n");
      out.write(" \twidth: 900px;\r\n");
      out.write(" }\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<!-- java메소드 change()생성 -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function change() {\r\n");
      out.write("\t\tvar f=document.frm;\r\n");
      out.write("\t\tf.submit();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"container\">  <!-- SESSION~~~~~~~~~~~~~~  -->\r\n");
      out.write("\t\t<h1 class=\"text-center\">");
      out.print(session.getAttribute("name"));
      out.write('(');
      out.print(session.getAttribute("id"));
      out.write(")&nbsp;");
      out.print(sy );
      out.write('년');
      out.write(' ');
      out.print(sm );
      out.write("월 일정</h1>\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<form method=\"POST\" name=\"frm\" action=\"diary.jsp\">\r\n");
      out.write("\t\t\t<table class=\"table\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"text-left\">\r\n");
      out.write("\t\t\t\t\t\t<select name=\"year\" onchange=\"change()\">\r\n");
      out.write("\t\t\t\t\t\t\t");
 	
										for(int i=2020; i<=2030; i++)
										{
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<option ");
      out.print(i==year?"selected":"" );
      out.write('>');
      out.print(i );
      out.write("</option>\r\n");
      out.write("\t\t\t\t\t\t\t");

										}
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select>년도 &nbsp;\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<select name=\"month\" onchange=\"change()\">\r\n");
      out.write("\t\t\t\t\t\t\t");

										for(int i=1;i<=12;i++)
										{
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<option ");
      out.print(i==month?"selected":"" );
      out.write('>');
      out.print( i );
      out.write("</option>\r\n");
      out.write("\t\t\t\t\t\t\t");
	
										}
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select>월\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<table class=\"table table-bordered\">\r\n");
      out.write("\t\t\t\t<tr class=\"danger\" height=\"35\">\r\n");
      out.write("\t\t\t\t\t");

					
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
					
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th class=\"text-center\"><h3><font color=\"");
      out.print(color);
      out.write('"');
      out.write('>');
      out.print(s );
      out.write("</font></h3></th>\r\n");
      out.write("\t\t\t\t\t");

									k++;
								}
					
      out.write("\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t");
		
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
				
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");

												       // week만큼 빈 공백 두기
														for(int j=0;j<week;j++)
														{
												
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td height=\"140\" class=\"text-left\" valign=\"top\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 
														}
												
      out.write("\r\n");
      out.write("\t\t\t\t");

									}
									//오늘 날짜 체크
									String back="white";
									if(i==day)
										back="success";
				
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<td height=\"140\" class=\"text-left ");
      out.print(back );
      out.write("\" valign=\"top\"><h4><font color=\"");
      out.print(color);
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<!--!!!!!!!!!SESSION얼마나 유지하는지 알아보는 법  _insert-->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"diary_insert.jsp\">");
      out.print(i );
      out.write("</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t</font></h4></td>\r\n");
      out.write("\t\t\t\t");
			// 2주차 줄바꾸기
								week++;
								if(week>6)
								{ 
									week=0;		
				
      out.write("\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t");
 
								}
							}
				
      out.write("\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
