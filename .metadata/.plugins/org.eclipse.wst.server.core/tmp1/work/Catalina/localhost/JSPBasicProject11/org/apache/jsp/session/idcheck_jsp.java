/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.50
 * Generated at: 2020-03-18 07:46:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.sist.dao.*;

public final class idcheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      com.sist.dao.DiaryDAO dao = null;
      dao = (com.sist.dao.DiaryDAO) _jspx_page_context.getAttribute("dao", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (dao == null){
        dao = new com.sist.dao.DiaryDAO();
        _jspx_page_context.setAttribute("dao", dao, javax.servlet.jsp.PageContext.PAGE_SCOPE);
      }
      out.write('\r');
      out.write('\n');

		String id=request.getParameter("id");
		int temp=0;
		int count=0;
		//id가 존재한다면
		if(id!=null)
		{
			count=dao.idcheck(id);
			temp=1;
		}
		//"null"

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".row{\r\n");
      out.write("\tmargin: 0px auto;\r\n");
      out.write("\twidth: 320px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("/* 중복체크나 주소값 넣어주는 것은 자바스크립트에서 처리해줘야함 = request초기화 */\r\n");
      out.write("function idcheck()\r\n");
      out.write("{\r\n");
      out.write("\t\tvar id=document.frm.id.value;\r\n");
      out.write("\t\tif(id.trim()==\"\")\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\t\tdocument.frm.id.focus();\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tdocument.frm.submit();\r\n");
      out.write("}\r\n");
      out.write("/*join으로 값 보내지는 함수  중복 체크에서 값을 넘기면 회원가입에서 값을 받는다  */\r\n");
      out.write("function ok()\r\n");
      out.write("{\r\n");
      out.write("\topener.frm.id.value=document.frm.id.value;\r\n");
      out.write("\tself.close();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t<h3 class=\"text-center\">아이디중복체크</h3>\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<table class=\"table\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t<form method=post action=\"idcheck.jsp\" name=\"frm\">\r\n");
      out.write("\t\t\t\t입력:<input type=text name=id size=15 value=\"");
      out.print(id!=null?id:"");
      out.write("\">\r\n");
      out.write("\t\t\t\t<input type=button value=\"검색\" class=\"btn btn-sm btn-primary\" onclick=\"idcheck()\">\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"text-center\">\r\n");
      out.write("\t\t\t\t");

							if (temp==0)
							{
				
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<font color=gray>ID를 입력하세요</font>\r\n");
      out.write("\t\t\t\t");
			
							}
				
      out.write("\r\n");
      out.write("\t\t\t\t");
 
						if(count==0 && temp==1)
						{
				
      out.write("\r\n");
      out.write("\t\t\t\t\t\t <font color=blue>");
      out.print(id );
      out.write(" = 사용가능한 아이디입니다</font>\r\n");
      out.write("\t\t\t\t");

						}
						else if(count==1 && temp==1)
						{
					
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<font color=red>");
      out.print(id );
      out.write("= 사용 불가한 아이디입니다</font>\r\n");
      out.write("\t\t\t\t\t");

							
						}
					
      out.write("\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t");

						 if(count==0 && temp==1)
						 {
				
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"text-center\">\r\n");
      out.write("\t\t\t\t\t\t<input type=button value=\"확인\" class=\"btn btn-sm btn-danger\" onclick=\"ok()\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t");

						 }
				
      out.write("\r\n");
      out.write("\t\t\t</table>\t\t\t\r\n");
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