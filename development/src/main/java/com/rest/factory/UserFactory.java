package com.rest.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.rest.dto.Users;
import com.rest.util.HibernateUtil;

public class UserFactory {

	public Users setUsersData(String userId, String userName, String phoneNumber, String eMail, String gender, String age,
			String password, String deviceId, String osType) {
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
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sessionFactory.close();
			hibernateUtil.shutdown();
		}
		return null;

	}

}
