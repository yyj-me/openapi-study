/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.42
 * Generated at: 2017-06-21 06:19:27 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class viewModel3_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\r\n");
      out.write("\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<meta name=\"description\" content=\"_your description goes here_\" />\r\n");
      out.write("<meta name=\"keywords\" content=\"_your,keywords,goes,here_\" />\r\n");
      out.write("<meta name=\"author\" content=\"_your name goes here_  / Original design: Andreas Viklund - http://andreasviklund.com/\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"andreas05.css\" />\r\n");
      out.write("<title>Sara And John</title>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-1.6.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function doJob() {\r\n");
      out.write("\tvar mcode = $(\"#mcode\").val();\r\n");
      out.write("\t$(\"#mcodeSpan\").html(\"Model List<br>\");\r\n");
      out.write("\tif(mcode != 'ALL') {\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype:\"get\",\r\n");
      out.write("\t\t\turl:\"http://tFactory.com:8000/tFactory/model/open/jsonp/\" + mcode,\r\n");
      out.write("\t\t\tcontentType: \"plain/text; charset=utf-8\",\r\n");
      out.write("\t\t\tdataType:\"jsonp\",\r\n");
      out.write("\t\t\tjsonp: \"callback\",\r\n");
      out.write("\t\t\terror:function(xhr, status, error) {\r\n");
      out.write("\t\t\t\talert(\"error : \" + status);\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsuccess:function(data) {\r\n");
      out.write("\t\t\t\t$(\"#mcodeSpan\").html(\"- Model Code : \" + data.mcode + \"<br/> - Model Name : \" + data.mname);\r\n");
      out.write("\t\t\t\t$(\"#mdesc\").text(data.mdesc);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\t\t\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\t$.getJSON(\"http://tFactory.com:8000/tFactory/model/open/jsonp/all?callback=?\", function(data) {\r\n");
      out.write("\t\t\t$(\"#mcodeSpan\").html(\"\");\r\n");
      out.write("\t\t\t$(\"#mdesc\").text(\"\");\r\n");
      out.write("\t\t\t$.each(data, function(i, data) {\r\n");
      out.write("\t\t\t\t$(\"#mcodeSpan\").append(\"- \" + data.mcode + \"<br>\");\r\n");
      out.write("\t\t\t});\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"title\"><h1>John connor's Site</h1></div>\r\n");
      out.write("<div id=\"container\">\r\n");
      out.write("<div id=\"sidebar\">\r\n");
      out.write("\r\n");
      out.write("<h2>Site menu</h2>\r\n");
      out.write("<a class=\"menu\" href=\"main.do\">Main page</a>\r\n");
      out.write("<a class=\"menu\" href=\"viewModel.do\">Connect TFactory</a>\r\n");
      out.write("<a class=\"menu\" href=\"viewModel2.do\">Connect TFactory2</a>\r\n");
      out.write("<a class=\"menu\" href=\"viewModel3.do\">Connect TFactory3</a>\r\n");
      out.write("<a class=\"menu\" href=\"#\">Sample</a>\r\n");
      out.write("<a class=\"menu\" href=\"#\">Sample</a>\r\n");
      out.write("<a class=\"menu\" href=\"#\">Sample</a>\r\n");
      out.write("<a class=\"menu\" href=\"#\">Sample</a>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"main\">\r\n");
      out.write("<h2>andreas05 - the simple solution!</h2>\r\n");
      out.write("<img src=\"dragonmini.png\" width=\"125\" height=\"125\" alt=\"Dragon screenshot\" />\r\n");
      out.write("<p>Model Code : <input type=\"text\" id=\"mcode\" value=\"ALL\"></input><a href=\"javascript:doJob();\">VIEW</a></p>\r\n");
      out.write("<p><strong><span id=\"mcodeSpan\"></span></strong>\r\n");
      out.write("   <div id=\"mdesc\">\r\n");
      out.write("   </div>\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("<h2>An open source design!</h2>\r\n");
      out.write("<p>This design is released as open source, which means that you can make any changes you may want to, and use the page in any way you want to. Have fun! If you want more designs to choose from, you can visit <a href=\"http://oswd.org/userinfo.phtml?user=Andreas\">my page</a> at OSWD.org, or download my other designs directly: <a href=\"http://www.oswd.org/download.phtml/andreas01.zip?id=2199\">01</a> |\r\n");
      out.write("<a href=\"http://www.oswd.org/download.phtml/andreas02.zip?id=2204\">02</a> |\r\n");
      out.write("<a href=\"http://www.oswd.org/download.phtml/andreas03.zip?id=2340\">03</a> |\r\n");
      out.write("<a href=\"http://www.oswd.org/download.phtml/andreas04.zip?id=2346\">04</a></p>\r\n");
      out.write("\r\n");
      out.write("<p class=\"credits\">&copy; 2005 Your name | Design by <a href=\"http://andreasviklund.com\">Andreas Viklund</a></p>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"footer\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
