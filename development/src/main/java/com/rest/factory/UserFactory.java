package com.rest.factory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.rest.dto.Users;
import com.rest.util.HibernateUtil;

public class UserFactory {

	public String setUsersData(String userId, String userName, String phoneNumber, String eMail, String gender,
			String age, String password, String deviceId, String osType) {
		Users users = new Users();
		SessionFactory sessionFactory = null;
		Session session = null;
		HibernateUtil hibernateUtil = new HibernateUtil();
		try {
			sessionFactory = hibernateUtil.buildSessionFactory();
			hibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			users.setUserId(userId);
			users.setUserName(userName);
			users.setPhoneNumber(phoneNumber);
			users.seteMail(eMail);
			users.setGender(gender);
			users.setAge(age);
			users.setPassword(password);
			users.setDeviceId(deviceId);
			users.setOsType(osType);
			session.save(users);
			session.getTransaction().commit();
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			sessionFactory.close();
			hibernateUtil.shutdown();
		}
	}

	public String getUserId(String phoneNumber) {
		Users users = new Users();
		SessionFactory sessionFactory = null;
		Session session = null;
		HibernateUtil hibernateUtil = new HibernateUtil();
		Gson gson = new Gson();
		try {
			sessionFactory = hibernateUtil.buildSessionFactory();
			hibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			String hql = "select user_id from users where phone_number ='" + phoneNumber + "'";
			List userId = session.createSQLQuery(hql).list();
			return (String) userId.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessionFactory.close();
			hibernateUtil.shutdown();
		}
	}

	public String getUserdetails(String userId, String phoneNumber) {
		SessionFactory sessionFactory = null;
		Session session = null;
		HibernateUtil hibernateUtil = new HibernateUtil();
		Gson gson = new Gson();
		List userData = new ArrayList<String>();
		try {
			sessionFactory = hibernateUtil.buildSessionFactory();
			hibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			String phoneDuplicationCheckSql = "select phone_number FROM development.users where phone_number = "
					+ phoneNumber;
			List phoneDuplicationCheckRes = session.createSQLQuery(phoneDuplicationCheckSql).list();
			if (!phoneDuplicationCheckRes.isEmpty() && phoneNumber.equals((String) phoneDuplicationCheckRes.get(0))) {
				return "0";
			}else if (phoneDuplicationCheckRes.isEmpty()) {
				return "2";
			}
			return gson.toJson(userData);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			sessionFactory.close();
			hibernateUtil.shutdown();
		}
	}
	
	public String getUserdetails(String userId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		HibernateUtil hibernateUtil = new HibernateUtil();
		Gson gson = new Gson();
		List userData = new ArrayList<String>();
		try {
			sessionFactory = hibernateUtil.buildSessionFactory();
			hibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			String hql = "FROM Users where  userId = '" + userId + "'";
			userData = session.createQuery(hql).list();
			return gson.toJson(userData);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			sessionFactory.close();
			hibernateUtil.shutdown();
		}
	}

}
