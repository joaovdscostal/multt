package br.com.jvlabs.controller;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.ProgressoDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Progresso;
import br.com.jvlabs.service.ProgressoService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class ProgressoController extends ControllerProjeto {

	@Inject private ProgressoDao progressoDao;
	@Inject private ProgressoService progressoService;

	@Get("/adm/progresso") @Privado
	public void index() {
	}

	@Get("/adm/progresso/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request);
			TableResponse<Progresso> response = progressoDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/progresso") @Privado
	public void criar(@Valid Progresso progresso) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			progressoService.cria(progresso);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProgressoController.criar");
			return;
		}

		addMessage("Progresso criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/progresso/ajax") @Privado
	public void criarAjax(@Valid Progresso progresso) {

		try {
			HibernateUtil.beginTransaction();
			progresso = progressoService.cria(progresso);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(progresso);
	}

	@Get("/adm/progresso/novo") @Privado
	public void novo() {
	}

	@Post("/adm/progresso/editar") @Privado
	public void atualizar(Progresso progresso) {
		validator.onErrorForwardTo(this).editar(progresso);

		try {
			HibernateUtil.beginTransaction();
			progressoService.atualiza(progresso);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProgressoController.atualizar");
		}

		addMessage("Progresso atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/progresso/{progresso.id}/editar") @Privado
	public void editar(Progresso progresso) {
		try {
			result.include("progresso", progressoDao.get(progresso.getId()));
		}catch (NoResultException e) {
			addValidation("Progresso nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/progresso/{progresso.id}/apagar") @Privado
	public void apagar(Progresso progresso) {

		try {
			HibernateUtil.beginTransaction();
			progressoService.apagar(progresso);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProgressoController.apagar");
			return;
		}

		addMessage("Progresso removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/progresso/{progresso.id}/clonar") @Privado
	public void clonar(Progresso progresso) {

		try {
			HibernateUtil.beginTransaction();
			progresso = progressoService.clonar(progresso);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProgressoController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o progresso " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Progresso clonado com sucesso!");
		result.redirectTo(this).editar(progresso);
	}




}
