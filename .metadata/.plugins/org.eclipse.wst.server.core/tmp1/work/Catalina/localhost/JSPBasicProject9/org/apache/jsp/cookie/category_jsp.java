/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.50
 * Generated at: 2020-03-17 04:43:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.sist.dao.*;
import java.util.*;

public final class category_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\r');
      out.write('\n');
      com.sist.dao.FoodDAO dao = null;
      dao = (com.sist.dao.FoodDAO) _jspx_page_context.getAttribute("dao", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (dao == null){
        dao = new com.sist.dao.FoodDAO();
        _jspx_page_context.setAttribute("dao", dao, javax.servlet.jsp.PageContext.PAGE_SCOPE);
      }
      out.write('\r');
      out.write('\n');
      out.write(' ');

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
 
      out.write("\r\n");
      out.write("  \r\n");
      out.write(" <!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>망고플레이트 따라하기</title>\r\n");
      out.write("<!-- BS PANEL에서 받아옴 -->\r\n");
      out.write(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n");
      out.write("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n");
      out.write("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"container-fluid\">\r\n");
      out.write("\t\t<h1 class=\"text-left\">믿고 보는 맛집 리스트</h1>\r\n");
      out.write("\t\t<div class=\"row\">\t\r\n");
      out.write("\t\t\t");

					for(CategoryBean vo:list)
					{
			
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-md-3\">\r\n");
      out.write("\t\t\t\t\t\t   <div class=\"panel panel-warning\">\r\n");
      out.write("\t\t\t\t\t\t      <div class=\"panel-heading\">\r\n");
      out.write("\t\t\t\t\t\t      ");
      out.print(vo.getTitle() );
      out.write("<br>\r\n");
      out.write("\t\t\t\t\t\t      <sub>");
      out.print(vo.getSubject()  );
      out.write("</sub>\r\n");
      out.write("\t\t\t\t\t\t      </div>\r\n");
      out.write("\t\t\t\t\t\t      <div class=\"panel-body\">\r\n");
      out.write("\t\t\t\t\t\t      \t<a href=\"food.jsp?cateno=");
      out.print(vo.getCateno());
      out.write("\"><img src=\"");
      out.print(vo.getPoster() );
      out.write("\" width=100%></a>\r\n");
      out.write("\t\t\t\t\t\t      </div>\r\n");
      out.write("\t\t\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t");

					}
			
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 쿠키 출력  -->\r\n");
      out.write("\t\t<hr>\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<h3>최근 방문한 맛집</h3>\r\n");
      out.write("\t\t\t");

						int j=0;
						for(int i=fList.size()-1;i>=0;i--)
						{
								FoodHouseBean vo=fList.get(i);
								if(j>5)
										break;
			
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-md-2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t    <div class=\"thumbnail\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      <a href=\"detail.jsp?no=");
      out.print(vo.getNo());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t        <img src=\"");
      out.print(vo.getImage() );
      out.write("\" alt=\"Lights\" style=\"width:250px; height:150px\t\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t      \t <div class=\"caption\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t          <p>");
      out.print(vo.getTitle() );
      out.write("</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t       \t\t\t </div>\r\n");
      out.write("\t\t\t\t\t\t\t\t    \t\t </a>\r\n");
      out.write("\t\t\t\t\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t");

								j++;
						}
			
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
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