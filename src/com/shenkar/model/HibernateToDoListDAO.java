package com.shenkar.model;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateToDoListDAO implements IToDoListDAO {
	
	private static HibernateToDoListDAO DAO;
	private Session session = null;
	private SessionFactory factory;
	private SessionFactory factoryTask;
	
	private HibernateToDoListDAO() 
	{ 
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		factoryTask = new AnnotationConfiguration().configure("hibernateTask.cfg.xml").buildSessionFactory();
	}
	
	public static HibernateToDoListDAO Instance()
	{
	    if (DAO == null) 
	    {
	    	return new HibernateToDoListDAO();
	    }
	    return DAO;
	}
/*********************************Users methods*********************************/
	public void addUser(User obj)
	{
		try
		{
			session = factory.openSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
	    	if ( session.getTransaction() != null )session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	}
	
	public List getUsers()
	{
		List users = null;
		try{
		session = factory.openSession();
		session.beginTransaction();
		users = session.createQuery("from User").list();
		session.getTransaction().commit();
		return users;
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
	    	if ( session.getTransaction() != null )session.getTransaction().rollback();
		}
		finally
		{
			
			session.close();
		}
		return users;
	}

	public User getUser(int userID, String Password) {
		try {
			session = factory.openSession();
            session.beginTransaction();
            User DBUser = (User) session.get(User.class, userID);
            if (DBUser.getPassword().equals(Password)){
                session.getTransaction().commit();
                return DBUser;
            }
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
		return null;
	}
	
	public String deleteUser(int userID, String Password) {
		try{
			session = factory.openSession();
            session.beginTransaction();
			User ob = (User)session.load(User.class, new Integer(userID));
			if (ob.getPassword().equals(Password))
			{
    			session.delete(ob);
    			session.getTransaction().commit();
    			return "User has been deleted from DB";
			}
			else return "Wrong password for this user - cannnot delete";
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
		return "User cannot be found in DB";
	}
/*******************************************************************************/

/*********************************Tasks methods*********************************/
	public void addTask(Task obj)
	{
		try
		{
			session = factoryTask.openSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
	    	if ( session.getTransaction() != null )session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	}
	
	public List<Task> getTasksForUser(int id)
	{
		List list = null;
		try{
			session = factoryTask.openSession();
			Query query = session.createQuery("from Task where ClientID= :code ");
			query.setParameter("code",id);
			list = query.list();
		}
		catch (HibernateException e)
		{
	    	if ( session.getTransaction() != null )
	    	session.getTransaction().rollback();
		}
		finally
		{
			session.close();
			return list;
		}
	}
	
	public void updateTask(int taskNumber, String taskName, String description)
	{
		try{
			session = factoryTask.openSession();	
			session.beginTransaction();
			Task task = (Task)session.load(Task.class, new Integer (taskNumber));
			task.setDescription(description);
			task.setTask(taskName);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
	    	if ( session.getTransaction() != null )
	    	session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	}	
	
	
	public void deleteTask(int taskNumber)
	{
		try{
			session = factoryTask.openSession();
			Task ob = (Task)session.load(Task.class, new Integer(taskNumber));
			session.beginTransaction();
			session.delete(ob);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
	    	if ( session.getTransaction() != null )
	    	session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	}	
	//testing
	public String getHelloWorld()
	{
		return "Hello world";
	}
}
