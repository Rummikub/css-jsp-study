/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.50
 * Generated at: 2020-03-30 08:23:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.sist.dao.*;
import java.util.*;

public final class movie_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("com.sist.dao");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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
      out.write("    \r\n");
      out.write("    ");
      com.sist.dao.MovieDAO dao = null;
      dao = (com.sist.dao.MovieDAO) _jspx_page_context.getAttribute("dao", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (dao == null){
        dao = new com.sist.dao.MovieDAO();
        _jspx_page_context.setAttribute("dao", dao, javax.servlet.jsp.PageContext.PAGE_SCOPE);
      }
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    ");

    			String strPage=request.getParameter("page");
    			if(strPage==null)
    			 	strPage="1";
    			int curpage=Integer.parseInt(strPage);
    			
    			List<MovieBean> list=dao.movieListData(curpage);
    			int totalpage=dao.movieTotalPage();
    			
    			//strPage 1, 6, 11 ...
    			final int BLOCK=5;
    			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
    			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
    			// endpage 가 31라면 
    			if(endPage>totalpage)
    						endPage=totalpage;
    
      out.write("\r\n");
      out.write("    \r\n");
      out.write("  <!-- 페이지만 만들어 볼거야  -->\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>MOVIE</title>\r\n");
      out.write(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n");
      out.write("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n");
      out.write("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t<h1 class=text-center>영화목록</h1>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t");

							for(MovieBean vo: list)
							{
				
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-md-4\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t   <div class=\"panel panel-danger\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      <div class=\"panel-heading\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      ");

														String temp=vo.getTitle();
										      			if(temp.length()>22)
										      				temp=temp.substring(0,22)+"...";
										      
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      ");
      out.print(temp );
      out.write("\t<br>\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      </div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      <div class=\"panel-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      \t<img src=\"");
      out.print(vo.getPoster() );
      out.write("\" width=280px height=220px>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      </div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t");

							}
				
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"row\" style=\"text-align:center\">\r\n");
      out.write("\t\t\t\t<ul class=\"pagination\">\r\n");
      out.write("\t\t\t\t\t\t <li><a href=\"movie.jsp?page=1\">&lt;&lt;</a></li>\r\n");
      out.write("\t\t\t\t\t\t");

									//1opage넘어가면 바뀌어야 됨
									if(curpage>BLOCK)
									{
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t <li><a href=\"movie.jsp?page=");
      out.print(startPage-1);
      out.write("\">&lt;</a></li>\r\n");
      out.write("\t\t\t\t\t\t");
				
									}
						
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");

								 		for(int i=startPage; i<=endPage; i++)
								 		{
								 				if(curpage==i)
								 				{
							
      out.write("\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t <li class=\"active\"><a href=\"movie.jsp?page=");
      out.print(i );
      out.write('"');
      out.write('>');
      out.print(i );
      out.write("</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t");
				
								 				}
								 				else
								 				{
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t<li><a href=\"movie.jsp?page=");
      out.print(i );
      out.write('"');
      out.write('>');
      out.print(i );
      out.write("</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t");
 
								 				}
								 		}	
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t");

										if(endPage<totalpage)
										{
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"movie.jsp?page=");
      out.print(endPage+1 );
      out.write("\">&gt;</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t");

										}						
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"movie.jsp?page=");
      out.print(totalpage );
      out.write("\">&gt;&gt;</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li> <a href=\"#\">");
      out.print(curpage );
      out.write(" page/ ");
      out.print(totalpage);
      out.write(" pages </a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
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
