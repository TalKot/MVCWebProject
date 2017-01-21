package com.shenkar.controller;

import com.shenkar.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.catalina.Session;
import org.apache.log4j.BasicConfigurator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ProgramController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ProgramController.class);

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher dispatcher = null;
		String path = request.getParameter("action");//checking the next URL from form
		System.out.println("");
		System.out.println("the path before change is - "+path);
		
		if (path==null)
			{
				path = request.getPathInfo();//check URL from browser
				System.out.println("Path before change - "+path);
				if (path.contains("ChangingTasks"))path = "/ChangingTasks";
				else if (path.contains("AddingTasks"))path = "/AddingTasks";
				else if (path.contains("DeleteTasks"))path = "/DeleteTasks";
				else if (path.contains("Register"))path = "/Register";
				else if (path.contains("LoginForm"))path = "/LoginForm";
				else if (path.contains("UserTask"))path = "/UserTask";
				else if (path.contains("clientList"))path = "/clientList";
				else if (path.contains("DeleteAccount"))path = "/DeleteAccount";
			}
		else{
				if(path.equals("Delete"))path = "/DeleteTasks";
				else if (path.equals("SignIn"))path = "/Register";
				else if (path.equals("Login"))path = "/UserTask";
				else if (path.contains("DeleteAccount"))path = "/DeleteAccount";
				else if (path.contains("AddingTasks"))path = "/AddingTasks";
				else if (path.contains("DeleteTasks"))path = "/DeleteTasks";
				else if (path.contains("Register"))path = "/Register";
				else if (path.contains("LoginForm"))path = "/LoginForm";
				else if (path.contains("UserTask"))path = "/UserTask";
				else if (path.contains("clientList"))path = "/clientList";
				else if (path.contains("ChangingTasks"))path = "/ChangingTasks";
			} 
			
		System.out.println("The path after change is - "+path);
		System.out.println("");
		log.info("The path after change is - "+path);
		HttpSession session = request.getSession();


		switch (path) 
		{
		default:case "/LoginForm":
					dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");
					dispatcher.forward(request, response);
					break;
		case "/Register":
					String firstName = (String)request.getParameter("FirstName");
					String lastName = (String)request.getParameter("LastName");
					String password = (String)request.getParameter("Password");
					String Email = (String)request.getParameter("Email");
					int phoneNumer = Integer.parseInt(request.getParameter("PhoneNumber"));
					int id = Integer.parseInt(request.getParameter("UserID"));
					session.setAttribute("thisUser", id);
					User userReg = new User(firstName, lastName, id, phoneNumer, Email, password);
					HibernateToDoListDAO.Instance().addUser(userReg);		
					request.setAttribute("MyUser",userReg);
					request.setAttribute("TasksLists",new ArrayList<Task>()); //HibernateToDoListDAO.Instance().getTasksForUser(userReg.getId()));
					request.setAttribute("TasksListsClosed",new ArrayList<Task>()); 

		case "/UserTask":			
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					String Pawword = request.getParameter("Password");				
					int userid = Integer.parseInt(request.getParameter("UserID"));
					log.info("Clinet number - "+ userid+" has connected to his account.");
					if (HibernateToDoListDAO.Instance().CheckUserInDB(userid,Pawword)==false)
					{
						log.debug("Clinet number - "+ userid+" is not in the db.");
						dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");				
						request.setAttribute("RequestDeleteAnswer","User ID or Password are wrong");
						dispatcher.forward(request, response);
						break;
					}
					User user = HibernateToDoListDAO.Instance().getUser(userid,Pawword);
					request.setAttribute("MyUser",user);
					log.info("Clinet number - "+ userid+" is in the db.");
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(user.getId()));
					request.setAttribute("TasksListsClosed",HibernateToDoListDAO.Instance().getTasksForUserClosed(user.getId())); 
					log.info("Clinet number - "+ userid+" after adding 2 lists of tasks - changing page to UserTask.");
					dispatcher.forward(request, response);
					break;				
			case "/DeleteAccount":
					log.info("delete account section");
					dispatcher = getServletContext().getRequestDispatcher("/LoginForm.jsp");
					String Pawword1 = request.getParameter("Password");				
					int userid1 = Integer.parseInt(request.getParameter("UserID"));
					if(HibernateToDoListDAO.Instance().CheckUserInDB(userid1, Pawword1)==true)
					{
						for(Task tsk:HibernateToDoListDAO.Instance().getTasksForUser(userid1))
						{
							HibernateToDoListDAO.Instance().deleteTask(tsk.getTaskNumber());
						}
						String str = HibernateToDoListDAO.Instance().deleteUser(userid1, Pawword1);
						log.info("Clinet number - "+ userid1+" was deleted.");
						request.setAttribute("RequestDeleteAnswer",str);
					}
					else{
						request.setAttribute("RequestDeleteAnswer","User Cannot Be Found!");
					}
					dispatcher.forward(request, response);
					break;
			case "/AddingTasks":
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					String taskName1 = (String)request.getParameter("taskname");
					String taskDescription1 = (String)request.getParameter("taskdescription");
					session = request.getSession();
					int thisUser  = (int) session.getAttribute("userid");
					Task newTask = new Task(thisUser, taskName1, taskDescription1,"Open");
					HibernateToDoListDAO.Instance().addTask(newTask);
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(thisUser));
					request.setAttribute("MyUser",HibernateToDoListDAO.Instance().getUserWithourPassword(thisUser));
					request.setAttribute("TasksListsClosed",HibernateToDoListDAO.Instance().getTasksForUserClosed(thisUser)); 
					dispatcher.forward(request, response);
					break;	
			case "/clientList":
					dispatcher = getServletContext().getRequestDispatcher("/clientList.jsp");
					List vec = HibernateToDoListDAO.Instance().getUsers();
					request.setAttribute("UsersList", vec);
					log.info("Accessed admin space - got full client list.");
					dispatcher.forward(request, response);
					break;
			case "/ChangingTasks":
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					int taskNumber = Integer.parseInt((String)request.getParameter("taskNumber"));
					String taskName = (String)request.getParameter("taskname");
					String description = (String)request.getParameter("taskdescription");
					HibernateToDoListDAO.Instance().updateTask(taskNumber, taskName, description);
					log.info(taskNumber+" was changed.");

					session = request.getSession();
					int thisUser1  = (int) session.getAttribute("userid");
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(thisUser1));
					request.setAttribute("MyUser",HibernateToDoListDAO.Instance().getUserWithourPassword(thisUser1));
					request.setAttribute("TasksListsClosed",HibernateToDoListDAO.Instance().getTasksForUserClosed(thisUser1)); 

					dispatcher.forward(request, response);
					break;	
			case "/DeleteTasks":
					dispatcher = getServletContext().getRequestDispatcher("/UserTask.jsp");
					int taskNumber1 = Integer.parseInt((String)request.getParameter("taskNumber"));
					HibernateToDoListDAO.Instance().ChangeStatus(taskNumber1);
					log.info(taskNumber1+" was cahgned to complete or done.");
					session = request.getSession();
					int thisUser11  = (int) session.getAttribute("userid");
					request.setAttribute("TasksLists", HibernateToDoListDAO.Instance().getTasksForUser(thisUser11));
					request.setAttribute("MyUser",HibernateToDoListDAO.Instance().getUserWithourPassword(thisUser11));
					request.setAttribute("TasksListsClosed",HibernateToDoListDAO.Instance().getTasksForUserClosed(thisUser11)); 
					dispatcher.forward(request, response);
					break;				
			}
		
		/*
			//adding the user-agent to the DB also		
			String strUserAgent = request.getHeader("User-Agent");
			if(strUserAgent.contains("Chrome"))
			{
				strUserAgent = "Chrome";
			}
			else if(strUserAgent.contains("OS"))
			{
				strUserAgent = "Safari";
			}
			else if(strUserAgent.contains("Firefox"))
			{
				strUserAgent = "Firefox";
			}
			else strUserAgent = "Explorer/Edge";
	
			addTask2DB(request.getRequestedSessionId(),taskName,taskDescription,strUserAgent);
		*/

	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
