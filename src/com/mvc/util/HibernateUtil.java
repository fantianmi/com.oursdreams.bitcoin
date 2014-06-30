package com.mvc.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	/** ThreadLocal Session Map */
	public static final ThreadLocal<Session> SESSIONMAP = new ThreadLocal<Session>();
	private static final SessionFactory requestFactory;
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

	static {
		try {
			LOGGER.debug("HibernateUti.static - loading cofig");
			requestFactory = new Configuration().configure()
					.buildSessionFactory();
			LOGGER.debug("HibernateUtil.static - end");
		} catch (Throwable ex) {
			ex.printStackTrace();
			LOGGER.error("HibernateUti error : ExceptionInInitializerError");
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	private HibernateUtil() {
		
	}

	public static Session getAttribute() throws HibernateException {
		Session request = SESSIONMAP.get();
		
		if(request == null) {
			request = requestFactory.openSession();
			SESSIONMAP.set(request);
		}
		
		return request;
	}
	
	public static void closeSession() throws HibernateException {
		Session request = SESSIONMAP.get();
		SESSIONMAP.set(null);
		
		if(request != null) {
			request.close();
		}
	}

}
