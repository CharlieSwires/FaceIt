package main.java.model;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class DbHelper {
	private SessionFactory sessionFactory;

	public DbHelper() {
		this.sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public BigInteger delete(BigInteger id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("delete UserDb where id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
			tx.commit();
			session.close();
			return id;
		} catch (HibernateException hibernateEx) {
			try {
				tx.rollback();
			} catch(RuntimeException runtimeEx){
				System.err.printf("Couldn�t Roll Back Transaction", runtimeEx);
			}
			hibernateEx.printStackTrace();
		} finally {
			if(session!= null) {
				//session.close();
			}
		}	
		return null;
	}

	public User getUserById(BigInteger id) {

		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from UserDb where id=:id");
		query.setParameter("id", id);
		List<UserDb> userList = query.list();
		session.close();
		User user = new User();
		if (userList.size() > 0) {
			Utility.copyUserDbtoUser(userList.get(0),user);
			return user;
		}
		return null;
	}
	
	public Users getUsers() {

		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from UserDb");
		List<UserDb> userList = query.list();
		session.close();
		List<User> ul  = new ArrayList<User>();
		if (userList.size() > 0) {
			for (UserDb userDb: userList) {
				User user = new User();
				Utility.copyUserDbtoUser(userDb,user);
				ul.add(user);
			}
			Users users = new Users();
			users.setUsers(ul);
			return users;
		}
		return null;
	}

	public Users getUsersByCountry(String country) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from UserDb where country=:country");
		query.setParameter("country", country);
		List<UserDb> userList = query.list();
		session.close();
		List<User> users = new ArrayList<User>();
		for (UserDb userDb: userList) {
			User user = new User();
			Utility.copyUserDbtoUser(userDb, user);
			users.add(user);
		}
		Users us = new Users();
		us.setUsers(users);
		return us;
	}

	public boolean createUsers(Users users) {

		Session session = this.sessionFactory.openSession();
		System.out.println("******************session:"+(session != null));

		Transaction tx = session.beginTransaction();
		try {
			for (User user: users.getUsers()) {
				System.out.println(user.toString());
				UserDb udb = new UserDb();
				Utility.copyUsertoUserDb(user, udb);
				System.out.println(udb.toString());
				session.save(udb);
				Utility.copyUserDbtoUser(udb, user);
				System.out.println("******************Saved user:"+user.toString());
			}
			tx.commit();
			session.close();
			System.out.println("******************session2:"+(session != null));

			return true;	

		} catch (HibernateException hibernateEx) {
			try {
				tx.rollback();
			} catch(RuntimeException runtimeEx){
				System.err.printf("Couldn�t Roll Back Transaction", runtimeEx);
			}
			hibernateEx.printStackTrace();
		} finally {
			if(session!= null) {
				//session.close();
			}
		}
		return false;	
	}

	public User updateUserById(User user, BigInteger id) {
		user.setId(id.intValue());

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			UserDb userDb = new UserDb();
			Utility.copyUsertoUserDb(user, userDb);
			session.saveOrUpdate(userDb);
			tx.commit();
			session.close();
		} catch (HibernateException hibernateEx) {
			try {
				tx.rollback();
			} catch(RuntimeException runtimeEx){
				System.err.printf("Couldn�t Roll Back Transaction", runtimeEx);
			}
			hibernateEx.printStackTrace();
		} finally {
			if(session!= null) {
				//session.close();
			}
		}
		return user;	

	}

}
