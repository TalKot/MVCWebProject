package com.shenkar.view;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
public class firstTag extends SimpleTagSupport
{
 public void doTag() throws JspException, IOException
 {
	 JspWriter out = getJspContext().getOut();
 	out.print("<h1>Java Web application that implements MVC architecture that allows the private end user to manage his personal TO-DO list.</h1>");
 }
}