package com.shenkar.model;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class hibernateToDoListDAO implements IToDoListDAO {
	
/*********************************Singleton*********************************/
	private static final hibernateToDoListDAO hibernateObject = new hibernateToDoListDAO();
	//private Session session = null;
	private SessionFactory factory;
	
	
	private hibernateToDoListDAO() 
	{ 
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	public static hibernateToDoListDAO getInstance()
	{
	    return hibernateObject;
	}
/*********************************Users methods*********************************/
	@Override
	public void addUser(user obj)throws userAndTaskException
	{
		Session session = null;
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
	@Override
	public List getUsers() throws userAndTaskException
	{
		Session session = null;
		List users = null;
		try{
			session = factory.openSession();
			session.beginTransaction();
			users = session.createQuery("from user").list();
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

	@Override
	public user getUser(int userID, String Password) throws userAndTaskException {
		Session session = null;
		try{
			session = factory.openSession();
            session.beginTransaction();
            user DBUser = (user) session.get(user.class, userID);
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
	
	public user getUserWithourPassword(int userID) throws userAndTaskException{
		Session session = null;
		try {
			session = factory.openSession();
            session.beginTransaction();
            user DBUser = (user) session.get(user.class, userID);
            session.getTransaction().commit();
            return DBUser;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
		return null;
	}
	
	@Override
	public boolean checkUserInDB (int userID, String Password)throws userAndTaskException{
		Session session = null;
		session = factory.openSession();
		session.beginTransaction();
		user DBUser = (user)session.get(user.class, userID);
		if (DBUser==null) return false;
		else{
			if (DBUser.getPassword().equals(Password))
	        {
				session.getTransaction().commit();
				return true;
	        }
		}
		return false;
	}
	
	@Override
	public String deleteUser(int userID, String Password)throws userAndTaskException {
		Session session = null;
			String str = null;
		try{
			session = factory.openSession();
			
            session.beginTransaction();
			user ob = (user)session.load(user.class, new Integer(userID));
			if (ob.getPassword().equals(Password))
			{
    			session.delete(ob);
    			session.getTransaction().commit();
    			str= "User has been deleted from DB";
			}
			else return "Wrong password for this user - cannnot delete";
        }
        catch (HibernateException e) 
		{
            e.printStackTrace();
            session.getTransaction().rollback();
            str= "User cannot be found in DB";
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
			return str;
		}
	}

/*********************************Tasks methods*********************************/
	@Override
	public void addTask(task obj)throws userAndTaskException{
		Session session = null;
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
	
	
	@Override
	public void changeStatus(int TaskNumber)throws userAndTaskException{
		Session session = null;
		try{
			session = factory.openSession();
			task ob = (task)session.load(task.class, new Integer(TaskNumber));
			session.beginTransaction();
			ob.setStatus("Close");
			session.update(ob);
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
	
	@Override
	public List<task> getTasksForUser(int id)throws userAndTaskException{
		Session session = null;
		List list = null;
		try{
			session = factory.openSession();
			Query query = session.createQuery("from task where ClientID= :code and Status = :stat");
			query.setParameter("code",id);
			query.setParameter("stat","Open");
			list = query.list();
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
			return list;
		}
	}
	
	@Override
	public List<task> getTasksForUserClosed(int id)throws userAndTaskException{
		Session session = null;
		List list = null;
		try{
			session = factory.openSession();
			Query query = session.createQuery("from task where ClientID= :code and Status = :stat");
			query.setParameter("code",id);
			query.setParameter("stat","Close");
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
	@Override
	public void updateTask(int taskNumber, String taskName, String description)throws userAndTaskException{
		Session session = null;
		try{
			session = factory.openSession();	
			session.beginTransaction();
			task task = (task)session.load(task.class, new Integer (taskNumber));
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
	
	@Override
	public void deleteTask(int taskNumber)throws userAndTaskException{
		Session session = null;
		try{
			session = factory.openSession();
			task ob = (task)session.load(task.class, new Integer(taskNumber));
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
	
	public task getTask(int taskID)throws userAndTaskException {
		Session session = null;
		try {
			session = factory.openSession();
            session.beginTransaction();
            task DBTask = (task) session.get(task.class, taskID);
            session.getTransaction().commit();
            return DBTask;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
		return null;
	}
}
