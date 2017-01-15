package com.rest.util;

import java.io.File;

import java.math.BigInteger;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.StandardBasicTypes;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	/*private final SessionFactory sessionFactory = buildSessionFactory();

	public SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}*/
	
	private  SessionFactory sessionFactory = buildSessionFactory();
    
    public  SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
//            configuration.addAnnotatedClass(UserActionFeedbackTransactions.class);
//            configuration.addAnnotatedClass(UserActionFeedback.class);
//            configuration.addAnnotatedClass(FeedbackTags.class);
            ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
             
            // builds a session factory from the service registry
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }
         
        return sessionFactory;
    }
    

	public SessionFactory getSessionFactory() {
    return sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}

	public void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
	
	public File getConfigFile() {
	    return new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "hibernate.cfg.xml");
	}

	public Long getNext(Session session) {
		Query query = session.createSQLQuery("select STOCK_SEQ.nextval as num from dual").addScalar("num",
				StandardBasicTypes.BIG_INTEGER);

		return ((BigInteger) query.uniqueResult()).longValue();
	}
}
