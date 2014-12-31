package sbs.bank.sessionfactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class SessionFactoryUtil {
	//private static final SessionFactory sessionFactory;
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	static {
		try {
			// Create the SessionFactory from user.cfg.xml
			Configuration configuration = new Configuration();
		    configuration.configure("/sbs/bank/sessionfactory/hibernate.cfg.xml");
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    
			//sessionFactory = new Configuration().configure("/sbs/bank/sessionfactory/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	} 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	} 
}
