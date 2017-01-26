package com.shenkar.view;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

public class secondTag extends SimpleTagSupport
{
 public void doTag() throws JspException, IOException
 {
	 JspWriter out = getJspContext().getOut();
 	out.print("<p>This project implements MVC-Programing using Hibernate as modle,Servelet as the controller and JSP pages as the view. </p>");
 }
}