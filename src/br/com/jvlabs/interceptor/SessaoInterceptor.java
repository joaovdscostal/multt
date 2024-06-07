package br.com.jvlabs.interceptor;

import java.util.Arrays;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.annotation.Public;
import br.com.jvlabs.annotation.Site;
import br.com.jvlabs.controller.IndexController;
import br.com.jvlabs.controller.LoginController;
import br.com.jvlabs.exception.PermissionException;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.util.Sessao;


@RequestScoped
@Intercepts(after= {HibernateInterceptor.class, PutDataSessionInterceptor.class})
public class SessaoInterceptor implements Interceptor {

	@Inject private HttpServletRequest request;
	@Inject private Sessao sessao;
	@Inject private Result result;
	@Inject private Environment environment;

	protected Logger logger = Logger.getLogger(HibernateInterceptor.class);

	@Override
	public boolean accepts(ControllerMethod method) {
		Boolean rotaPublica = method.containsAnnotation(Site.class) || method.containsAnnotation(Public.class);
		return !rotaPublica;
	}

	public void intercept(InterceptorStack interceptor, ControllerMethod method, Object controller) {
		String urlRequest = request.getRequestURL().toString();

		result.on(PermissionException.class).forwardTo(IndexController.class).admin();

		Boolean validationPermission = Boolean.parseBoolean(environment.get("validarPermissao"));

		if(validationPermission && !controller.getClass().getSimpleName().contains("Quartz")){
			if(!sessao.logado()){
				result.include("error", "Por favor, efetue o login!");
				result.forwardTo(LoginController.class).login();
				sessao.setUrlContinuacao(urlRequest);
	    		return;
			}

			if(!podeAcessar(method)){
				result.include("error", "Sem permiss&atilde;o!");
				result.redirectTo(LoginController.class).login();
				sessao.limparUrlContinuacao();
	    		return;
			}

			sessao.limparUrlContinuacao();
		}

		interceptor.next(method, controller);
	}

	private boolean podeAcessar(ControllerMethod method) {
		if(sessao.isAdministrador()) {
			return true;
		}
		
		if(sessao.isUsuario()) {
			return true;
		}

		if(method.containsAnnotation(Privado.class)) {
			Privado privado = method.getMethod().getAnnotation(Privado.class);
			TipoUsuario[] tiposDoRequest = privado.value();

			boolean todosAcessam = tiposDoRequest.length == 0;
			if(todosAcessam) {
				return true;
			}

			boolean podeAcessar = Arrays.asList(tiposDoRequest).contains(sessao.getUsuario().getTipo());

			return podeAcessar;
		}
		return false;
	}

}