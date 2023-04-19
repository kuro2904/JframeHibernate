package Util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory factory;
	static {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		factory = configuration.buildSessionFactory(registry);
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}
}
