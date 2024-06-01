package br.com.jvlabs.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.CDI;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.CustomType;
import org.hibernate.type.EnumType;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.events.VRaptorInitialized;
import br.com.jvlabs.exception.BusinessException;

@SuppressWarnings("resource")
@ApplicationScoped
public class HibernateUtil {

	private static Logger logger = Logger.getLogger(HibernateUtil.class);
	private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();
	private static org.hibernate.SessionFactory sessionFactory;

	public void init(@Observes VRaptorInitialized event) {
		try {
			Environment environment = CDI.current().select(Environment.class).get();
			URL resource = environment.getResource("/hibernate.cfg.xml");

			of(resource);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("nao conect", e);
		}
	}


	public static void of(URL resource) {
		try {
			Configuration ac = new Configuration();
			ac.configure(resource);

			InputStream mapeamentoHibernate = FileUtils.loadFile("mapeamento.hibernate");
//			System.out.println("mapemento "+ mapeamentoHibernate);

			String classeMapeada = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(mapeamentoHibernate));
			if (mapeamentoHibernate != null) {
				while ((classeMapeada = reader.readLine()) != null) {
					if (classeMapeada != null && !classeMapeada.trim().isEmpty()) {
						try {
//							System.out.println(classeMapeada);
							ac.addAnnotatedClass(Class.forName(classeMapeada));
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("Erro ao mapear classe definida no arquivo mapeamento.hibernate", e);
						}
					}
				}
			}

			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(ac.getProperties()).build();
			sessionFactory = ac.buildSessionFactory(sr);

		} catch (Throwable e) {
			logger.error("nao conect", e);
			e.printStackTrace();
		}
	}

	public static Session openSession() {
		if (sessions.get() != null) {
			logger.error("J&aacute; existe uma session para essa thread.");
		}
		sessions.set(sessionFactory.openSession());
		return sessions.get();
	}

	public static Session createSession() {
		return sessionFactory.openSession();
	}

	public static void closeCurrentSession() {
		if (sessions.get() != null && sessions.get().isOpen()) {
			sessions.get().close();
			sessions.set(null);
		}
	}

	public static Session currentSession() {
		return sessions.get();
	}

	public static void beginTransaction() {
		sessions.get().beginTransaction();
	}

	public static void commit() {
		sessions.get().getTransaction().commit();
	}

	public static void rollback() {
		sessions.get().getTransaction().rollback();
	}

	public static void clearSession() {
		if (sessions.get() != null)
			sessions.get().clear();
	}

	public static void closeSessionFactory() {
		sessionFactory.close();
	}

	public static CustomType customEnumType(Class<?> enumerator) throws BusinessException {
		if(enumerator.isEnum()) {
			Properties params = new Properties();
			params.put("enumClass", enumerator.getName());
			params.put("useNamed", true);

			EnumType enumType = new EnumType();
			enumType.setParameterValues(params);
			CustomType customType = new CustomType(enumType);

			return customType;
		}
		throw new BusinessException("Deve enviar um enum");
	}

}
