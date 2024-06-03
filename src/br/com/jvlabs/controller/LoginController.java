package br.com.jvlabs.controller;

import javax.inject.Inject;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.jvlabs.annotation.Public;
import br.com.jvlabs.annotation.Site;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Usuario;
import br.com.jvlabs.service.LoginService;
import br.com.jvlabs.service.UsuarioService;
import br.com.jvlabs.util.HibernateUtil;


@Controller
public class LoginController extends br.com.jvlabs.controller.ControllerProjeto{

	@Inject private LoginService loginService;
	@Inject private UsuarioService usuarioService;

	@Get("/adm/login") @Site
	public void login(){
		result.include("login", true);
		sessao.logout();

		try{
			HibernateUtil.beginTransaction();
			usuarioService.verificarUsuarioAdmin();
			HibernateUtil.commit();
		}catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "LoginController.login");
			return;
		}

	}
	
	@Get("/contas/cadastrar") @Site
	public void cadastrarPage(){}

	@Post("/adm/login") @Site
	public void logar(Usuario usuario){

		try {
			usuario = loginService.logar(usuario);
		} catch (BusinessException e) {
			addError(e.getMessage());
			validator.add(new SimpleMessage("error", e.getMessage())).onErrorForwardTo(this).login();
		}

		try{
			HibernateUtil.beginTransaction();
			usuarioService.realizaAcessoNoSistema(usuario);
			HibernateUtil.commit();
		}catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "LoginController.logar");
			return;
		}

		if(sessao.possuiUrlContinuacao() && !sessao.possuiUrlContinuacaoParaLogin())
			result.redirectTo(sessao.getUrlContinuacao());
		else {
			result.redirectTo(IndexController.class).admin();
		}
	}

	@Path("/adm/logout") @Public
	public void logout(){
		sessao.logout();
		result.redirectTo(this).login();
	}
}