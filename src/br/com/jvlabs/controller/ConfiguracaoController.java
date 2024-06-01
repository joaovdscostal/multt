package br.com.jvlabs.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.model.Configuracao;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.service.ConfiguracaoService;
import br.com.jvlabs.util.HibernateUtil;


@Controller
public class ConfiguracaoController extends ControllerProjeto {

	@Inject private ConfiguracaoService configuracaoService;

	@Get("/adm/configuracao") @Privado({TipoUsuario.ADMINISTRADOR})
	public void index() {
	}

	@Post("/adm/configuracao") @Privado({TipoUsuario.ADMINISTRADOR})
	public void criar(@Valid Configuracao configuracao) {
		validator.onErrorForwardTo(this).index();

		try {
			HibernateUtil.beginTransaction();
			configuracaoService.atualiza(configuracao);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ConfiguracaoController.criar");
			return;
		}

		addMessage("Configuração atualizada!");
		result.redirectTo(this).index();
	}

}
