package br.com.jvlabs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.ConteudoDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Conteudo;
import br.com.jvlabs.model.Modulo;
import br.com.jvlabs.service.ConteudoService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class ConteudoController extends ControllerProjeto {

	@Inject private ConteudoDao conteudoDao;
	@Inject private ConteudoService conteudoService;

	@Get("/adm/conteudos") @Privado
	public void index() {
	}

	@Get("/adm/conteudos/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request);
			TableResponse<Conteudo> response = conteudoDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/conteudos") @Privado
	public void criar(@Valid Conteudo conteudo,Modulo modulo) {
		validator.onErrorForwardTo(this).novo(modulo);

		try {
			HibernateUtil.beginTransaction();
			conteudoService.cria(conteudo,modulo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ConteudoController.criar");
			return;
		}

		addMessage("Conteudo criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo(modulo);
		else
			result.redirectTo(this).index();
	}

	@Get("/adm/conteudos/novo") @Privado
	public void novo(Modulo modulo) {
		result.include("modulo",modulo);
	}

	@Post("/adm/conteudos/editar") @Privado
	public void atualizar(Conteudo conteudo) {
		validator.onErrorForwardTo(this).editar(conteudo);

		try {
			HibernateUtil.beginTransaction();
			conteudoService.atualiza(conteudo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ConteudoController.atualizar");
		}

		addMessage("Conteudo atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/conteudos/{conteudo.id}/editar/modal") @Privado
	public void editar(Conteudo conteudo) {
		try {
			result.include("conteudo", conteudoDao.get(conteudo.getId()));
		}catch (NoResultException e) {
			addValidation("Conteudo nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/conteudos/{conteudo.id}/apagar") @Privado
	public void apagar(Conteudo conteudo) {

		try {
			HibernateUtil.beginTransaction();
			conteudoService.apagar(conteudo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ConteudoController.apagar");
			return;
		}

		addMessage("Conteudo removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/conteudos/{conteudo.id}/clonar") @Privado
	public void clonar(Conteudo conteudo) {

		try {
			HibernateUtil.beginTransaction();
			conteudo = conteudoService.clonar(conteudo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ConteudoController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o conteudo " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Conteudo clonado com sucesso!");
		result.redirectTo(this).editar(conteudo);
	}


	@Get("/adm/conteudos/ordenar") @Privado
	public void ordenar(List<Conteudo> dados) {
		try {
			HibernateUtil.beginTransaction();
			conteudoService.ordenar(dados);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax("Ordena&ccedil;&atilde;o efetuada com sucesso!");
	}


}
