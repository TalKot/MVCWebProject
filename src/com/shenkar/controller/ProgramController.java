package com.shenkar.controller;

import com.shenkar.model.task;
import com.shenkar.model.user;
import com.shenkar.model.hibernateToDoListDAO;
import com.shenkar.model.userAndTaskException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;




public class programController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(programController.class);
	int secondsPassed=0;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//set timer to check how much time it takes to get the response
		long tStart = System.currentTimeMillis();

		//checking what action needed
		RequestDispatcher dispatcher = null;
		String path = request.getParameter("action");//checking the next URL from form
		if (path==null)
			{
				path = request.getPathInfo();//check URL from browser
				if (path.contains("ChangingTasks"))path = "/ChangingTasks";
				else if (path.contains("AddingTasks"))path = "/AddingTasks";
				else if (path.contains("DeleteTasks"))path = "/DeleteTasks";
				else if (path.contains("Register"))path = "/Register";
				else if (path.contains("LoginForm"))path = "/LoginForm";
				else if (path.contains("UserTask"))path = "/UserTask";
				else if (path.contains("DeleteAccount"))path = "/DeleteAccount";

			}
		else{
				if(path.equals("Delete"))path = "/DeleteTasks";
				else if (path.equals("SignIn"))path = "/Register";
				else if (path.equals("Login"))path = "/UserTask";
				else if (path.equals("Logout"))path = "/LoginForm";
				else if (path.contains("DeleteAccount"))path = "/DeleteAccount";
				else if (path.contains("AddingTasks"))path = "/AddingTasks";
				else if (path.contains("DeleteTasks"))path = "/DeleteTasks";
				else if (path.contains("Register"))path = "/Register";
				else if (path.contains("LoginForm"))path = "/LoginForm";
				else if (path.contains("UserTask"))path = "/UserTask";
				else if (path.contains("ChangingTasks"))path = "/ChangingTasks";
			} 
		HttpSession session = request.getSession();

		switch (path) {
		default:case "/LoginForm":
			try{
					dispatcher = getServletContext().getRequestDispatcher("/loginForm.jsp");
					dispatcher.forward(request, response);
					break;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		case "/Register":
			try{
					//getting all data from Register form
					String firstName = request.getParameter("FirstName");
					String lastName = request.getParameter("LastName");
					String password = request.getParameter("Password");
					String Email = request.getParameter("Email");
					int phoneNumer = Integer.parseInt(request.getParameter("PhoneNumber"));
					int id = Integer.parseInt(request.getParameter("UserID"));
					String strUserAgent = request.getHeader("User-Agent");
					user CreationNewUser = new user(firstName, lastName, id, phoneNumer, Email, password,strUserAgent);//creation of the user object with the data form
					log.info("user - "+CreationNewUser.getId() +"was created");//writing to log4j
					//checking if the user already in the db with this username&password
					if (hibernateToDoListDAO.getInstance().checkUserInDB(id,password))
					{
						log.debug("Clinet "+ id+" is already in the db.Please choose another ID number.");//writing data to log4j
						dispatcher = getServletContext().getRequestDispatcher("/loginForm.jsp");				
						request.setAttribute("queryAnswerLoginform","Clinet "+ id+" is already in the db.Please choose another ID number.");//message taht the client is already in the DB
						dispatcher.forward(request, response);
						break;
					}
					hibernateToDoListDAO.getInstance().addUser(CreationNewUser);//adding user to DB		
				}
				catch(userAndTaskException e)
				{
					log.fatal(e.getMessage());
					dispatcher = getServletContext().getRequestDispatcher("/loginForm.jsp");				
					request.setAttribute("queryAnswerLoginform",e.getMessage());
					dispatcher.forward(request, response);
					break;		
				}
				//there is no break, UserTask section will start automatically
		case "/UserTask":			
			try{
					dispatcher = getServletContext().getRequestDispatcher("/userTask.jsp");
					String Pawword = request.getParameter("Password");				
					int userid = Integer.parseInt(request.getParameter("UserID"));
					//checking that user is not in the DB
					if (hibernateToDoListDAO.getInstance().checkUserInDB(userid,Pawword)==false)
					{
						log.debug("Clinet number - "+ userid+" is not in the db.");
						dispatcher = getServletContext().getRequestDispatcher("/loginForm.jsp");				
						request.setAttribute("queryAnswerLoginform","User ID or Password are wrong");
						dispatcher.forward(request, response);
						break;
					}	
					log.info("Clinet number - "+ userid+" has connected to his account.");
					//return information
					Cookie cookUserID = new Cookie("UserID", String.valueOf(userid));
					response.addCookie(cookUserID);
					request.setAttribute("MyUser",hibernateToDoListDAO.getInstance().getUserWithourPassword(userid));
					request.setAttribute("TasksLists", hibernateToDoListDAO.getInstance().getTasksForUser(userid));
					request.setAttribute("TasksListsClosed",hibernateToDoListDAO.getInstance().getTasksForUserClosed(userid)); 
					long tEnd = System.currentTimeMillis();
					long tDelta = tEnd - tStart;
					double elapsedSeconds = tDelta / 1000.0;
					request.setAttribute("timer", elapsedSeconds);//how much time it took for this action
					Cookie cookieFisrtName = new Cookie("Firstname",hibernateToDoListDAO.getInstance().getUserWithourPassword(userid).getLastName() + " " +hibernateToDoListDAO.getInstance().getUserWithourPassword(userid).getFirstName());
					cookieFisrtName.setMaxAge(80000);
					response.addCookie(cookieFisrtName);//adding cookie to the response
					dispatcher.forward(request, response);
					break;		
					
				}
				catch(Exception e)
				{
					dispatcher = getServletContext().getRequestDispatcher("/loginForm.jsp");				
					request.setAttribute("queryAnswerLoginform",e.getMessage());//reason for the action to fail
					dispatcher.forward(request, response);
					break;				
				}
			case "/DeleteAccount":
				try{
					log.info("delete account section");
					dispatcher = getServletContext().getRequestDispatcher("/loginForm.jsp");
					String Pawword1 = request.getParameter("Password");				
					int userid1 = Integer.parseInt(request.getParameter("UserID"));
					//checking if user is in the DB
					if(hibernateToDoListDAO.getInstance().checkUserInDB(userid1, Pawword1))
					{
						//user is in the DB - delete all tasks - open & close
						for(task tsk:hibernateToDoListDAO.getInstance().getTasksForUser(userid1))
						{
							hibernateToDoListDAO.getInstance().deleteTask(tsk.getTaskNumber());
						}
						for(task tsk:hibernateToDoListDAO.getInstance().getTasksForUserClosed(userid1))
						{
							hibernateToDoListDAO.getInstance().deleteTask(tsk.getTaskNumber());
						}
						//now, delete the user itself
						String str = hibernateToDoListDAO.getInstance().deleteUser(userid1, Pawword1);
						log.info("Clinet number - "+ userid1+" was deleted.");
						request.setAttribute("queryAnswerLoginform",str);
					}
					else{
						request.setAttribute("queryAnswerLoginform","User Cannot Be Found!");
					}
					dispatcher.forward(request, response);
					break;
				}
				catch(Exception e)
				{
					dispatcher = getServletContext().getRequestDispatcher("/loginForm.jsp");				
					request.setAttribute("queryAnswerLoginform",e.getMessage());
					dispatcher.forward(request, response);
					break;				
				}
			case "/AddingTasks":
				try{
					dispatcher = getServletContext().getRequestDispatcher("/userTask.jsp");
					String taskName1 = request.getParameter("taskname");
					String taskDescription1 = request.getParameter("taskdescription");
					session = request.getSession();
					int AddingTaskForUser  = (int) session.getAttribute("userid");
					//adding task to user
					hibernateToDoListDAO.getInstance().addTask(new task(AddingTaskForUser, taskName1, taskDescription1,"Open"));
					//return information
					request.setAttribute("TasksLists", hibernateToDoListDAO.getInstance().getTasksForUser(AddingTaskForUser));
					request.setAttribute("MyUser",hibernateToDoListDAO.getInstance().getUserWithourPassword(AddingTaskForUser));
					request.setAttribute("TasksListsClosed",hibernateToDoListDAO.getInstance().getTasksForUserClosed(AddingTaskForUser)); 
					long tEnd = System.currentTimeMillis();
					long tDelta = tEnd - tStart;
					double elapsedSeconds = tDelta / 1000.0;
					request.setAttribute("timer", elapsedSeconds);//setting the timer right
					dispatcher.forward(request, response);
					break;	
					}
				catch(userAndTaskException e)
				{
					log.fatal(e.getMessage());
					dispatcher = getServletContext().getRequestDispatcher("/loginForm.jsp");				
					request.setAttribute("queryAnswerLoginform",e.getMessage());
					dispatcher.forward(request, response);
					break;		
				}
			case "/ChangingTasks":
				try{
					dispatcher = getServletContext().getRequestDispatcher("/userTask.jsp");
					if (request.getParameter("taskNumber").equals(""))throw new userAndTaskException("please chose a task number!");//checking that there is a number of task to change
					int taskNumber = Integer.parseInt(request.getParameter("taskNumber"));
					String taskName = request.getParameter("taskname");
					String description = request.getParameter("taskdescription");
					//checking that the task is for this client
					session = request.getSession();
					int checkingClientIDforTask  = (int) session.getAttribute("userid");
					task tempTask = hibernateToDoListDAO.getInstance().getTask(taskNumber);//getting task object from DB
					if(tempTask!=null&&tempTask.getClientID()==checkingClientIDforTask)//making sure that the task is for this client
					{
						hibernateToDoListDAO.getInstance().updateTask(taskNumber, taskName, description);
						log.info(taskNumber+" was changed.");
					}
					else
					{
						request.setAttribute("queryAnswer","This task isn't for this client - cannot be changed");
						log.info("Client number "+ checkingClientIDforTask + "tried to change a task that isn't his.");
					}
					//return information
					request.setAttribute("TasksLists", hibernateToDoListDAO.getInstance().getTasksForUser(checkingClientIDforTask));
					request.setAttribute("MyUser",hibernateToDoListDAO.getInstance().getUserWithourPassword(checkingClientIDforTask));
					request.setAttribute("TasksListsClosed",hibernateToDoListDAO.getInstance().getTasksForUserClosed(checkingClientIDforTask)); 
					long tEnd1 = System.currentTimeMillis();
					long tDelta1 = tEnd1 - tStart;
					double elapsedSeconds1 = tDelta1 / 1000.0;
					request.setAttribute("timer", elapsedSeconds1);
					dispatcher.forward(request, response);
					break;	
				}
				catch(userAndTaskException e)
				{
					log.fatal(e.getMessage());
					dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");				
					request.setAttribute("exceptionMessage",e.getMessage());
					dispatcher.forward(request, response);
					break;		
				}
			case "/DeleteTasks":
				try{
					dispatcher = getServletContext().getRequestDispatcher("/userTask.jsp");
					if(request.getParameter("taskNumber").equals(""))throw new userAndTaskException("Please choose a task number that you want to change from open to close");
					int taskNumber1 = Integer.parseInt(request.getParameter("taskNumber"));
					///
					session = request.getSession();
					int changeTaskToCloseUserID  = (int) session.getAttribute("userid");
					task taskToClose = hibernateToDoListDAO.getInstance().getTask(taskNumber1);//getting task object from DB
					if(taskToClose!=null&&taskToClose.getClientID()==changeTaskToCloseUserID)//making sure that the task is for this client
					{
						hibernateToDoListDAO.getInstance().changeStatus(taskNumber1);
						log.info(taskNumber1+" was changed to complete or done.");
					}
					else
					{
						request.setAttribute("queryAnswer","This task isn't for this client - cannot be changed");
						log.info("Client number "+ changeTaskToCloseUserID + "tried to change a task that isn't his.");
					}
					///
					//return information
					request.setAttribute("TasksLists", hibernateToDoListDAO.getInstance().getTasksForUser(changeTaskToCloseUserID));
					request.setAttribute("MyUser",hibernateToDoListDAO.getInstance().getUserWithourPassword(changeTaskToCloseUserID));
					request.setAttribute("TasksListsClosed",hibernateToDoListDAO.getInstance().getTasksForUserClosed(changeTaskToCloseUserID)); 
					long tEnd2 = System.currentTimeMillis();
					long tDelta2 = tEnd2 - tStart;
					double elapsedSeconds2 = tDelta2 / 1000.0;
					request.setAttribute("timer", elapsedSeconds2);
					dispatcher.forward(request, response);
					break;		
				}
				catch(userAndTaskException e)
				{
					log.fatal(e.getMessage());
					dispatcher = getServletContext().getRequestDispatcher("/errorpage.jsp");				
					request.setAttribute("exceptionMessage",e.getMessage());
					dispatcher.forward(request, response);
					break;		
				}
			}
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
