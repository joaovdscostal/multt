package br.com.jvlabs.interceptor;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.events.RequestSucceded;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.jvlabs.annotation.Estatico;
import br.com.jvlabs.util.HibernateUtil;

@RequestScoped
@Intercepts(before= {SessaoInterceptor.class, PutDataSessionInterceptor.class})
public class HibernateInterceptor implements Interceptor {

	protected Logger logger = Logger.getLogger(HibernateInterceptor.class);

	@Override
	public boolean accepts(ControllerMethod method) {
		return !method.containsAnnotation(Estatico.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method, Object controllerInstance) {
		try {
			HibernateUtil.openSession();
			stack.next(method, controllerInstance);
		} catch (JDBCConnectionException e) {
			logger.error("error", e);
		}
	}

	public void after(@Observes RequestSucceded method) {
		try {
			HibernateUtil.closeCurrentSession();
		} catch (JDBCConnectionException e) {
			logger.error("error", e);
		}
	}

}
