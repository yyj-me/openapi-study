/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.42
 * Generated at: 2014-09-12 04:38:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class input_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("    \n");
      out.write("    \n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n");
      out.write("\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n");
      out.write("<meta name=\"description\" content=\"description\"/>\n");
      out.write("<meta name=\"keywords\" content=\"keywords\"/> \n");
      out.write("<meta name=\"author\" content=\"author\"/> \n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"../default.css\"/>\n");
      out.write("<title>Blackbox</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"main\">\n");
      out.write("\t<div class=\"gfx\"><h1>Terminator Factory</h1></div>\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/menu.jsp", out, false);
      out.write("\n");
      out.write("\t<div class=\"content\">\n");
      out.write("\t\t<div class=\"item\">\n");
      out.write("\t\t\t<h1>Regist New Terminator Model </h1>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<form id=\"f1\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("\t\t\t<table width=\"100%\" >\n");
      out.write("\t\t\t  <tr>\n");
      out.write("\t\t\t    <td>Model Code </td>\n");
      out.write("\t\t\t    <td><input type=\"text\" name=\"mcode\" value=\"T1000\"></input></td> \n");
      out.write("\t\t\t  </tr>\n");
      out.write("\t\t\t  <tr>\n");
      out.write("\t\t\t    <td>Model Name </td>\n");
      out.write("\t\t\t    <td><input type=\"text\" name=\"mname\" value=\"T1000 Model\"></input></td> \n");
      out.write("\t\t\t  </tr>\n");
      out.write("\t\t\t  <tr>\n");
      out.write("\t\t\t    <td>Model Description </td>\n");
      out.write("\t\t\t    <td><textarea name=\"mdesc\" cols=\"50\"  rows=\"10\">The T-1000 is a fictional nanomorph mimetic poly-alloy (liquid metal) assassin and the main antagonist in Terminator 2: Judgment Day.[1] The T-1000 is portrayed primarily by Robert Patrick; however, being a shape-shifter, the T-1000 is played by other actors in some scenes of the film. In Terminator 2, the T-1000 is presented as a technological leap over the \"800 Series\" Terminator (Arnold Schwarzenegger).[2] Described by Allmovie as \"one of the most memorable roles in one of the most memorable films of the decade\",[3] Patrick's portrayal of the T-1000 earned him nominations for Best Villain and Best Supporting Actor at the 1992 MTV and Saturn Awards[4] and was ranked #39 in the Online Film Critics Society's \"Top 100 Villains of All Time\" in 2002    \n");
      out.write("\t\t\t        </textarea></td> \n");
      out.write("\t\t\t  </tr>\t\t\t  \t\t\t\n");
      out.write("\t\t\t  <tr>\n");
      out.write("\t\t\t    <td>Model Image </td>\n");
      out.write("\t\t\t    <td><input type=\"file\" name=\"uploadFile\"></input></td> \n");
      out.write("\t\t\t  </tr>\n");
      out.write("\t\t\t  <tr>\n");
      out.write("\t\t\t    <td> </td>\n");
      out.write("\t\t\t    <td> <input type=\"submit\" value=\"regist\"></input> <input type=\"reset\" ></input>   </td> \n");
      out.write("\t\t\t  </tr>\t\t\t  \t\t\t\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"footer\">&copy; 2006 <a href=\"index.html\">Sitename.com</a>. Design by <a href=\"http://arcsin.se\">Arcsin</a></div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
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
