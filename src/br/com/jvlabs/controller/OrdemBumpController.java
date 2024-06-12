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
import br.com.jvlabs.dao.OrdemBumpDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.OrdemBump;
import br.com.jvlabs.service.OrdemBumpService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class OrdemBumpController extends ControllerProjeto {

	@Inject private OrdemBumpDao ordembumpDao;
	@Inject private OrdemBumpService ordembumpService;

	@Get("/adm/orders-bump") @Privado
	public void index() {
	}

	@Get("/adm/orders-bump/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request);
			TableResponse<OrdemBump> response = ordembumpDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/orders-bump") @Privado
	public void criar(@Valid OrdemBump ordembump) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			ordembumpService.cria(ordembump);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "OrdemBumpController.criar");
			return;
		}

		addMessage("OrdemBump criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/orders-bump/ajax") @Privado
	public void criarAjax(@Valid OrdemBump ordembump) {

		try {
			HibernateUtil.beginTransaction();
			ordembump = ordembumpService.cria(ordembump);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(ordembump);
	}

	@Get("/adm/orders-bump/novo") @Privado
	public void novo() {
	}

	@Post("/adm/orders-bump/editar") @Privado
	public void atualizar(OrdemBump ordembump) {
		validator.onErrorForwardTo(this).editar(ordembump);

		try {
			HibernateUtil.beginTransaction();
			ordembumpService.atualiza(ordembump);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "OrdemBumpController.atualizar");
		}

		addMessage("OrdemBump atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/orders-bump/{ordembump.id}/editar") @Privado
	public void editar(OrdemBump ordembump) {
		try {
			result.include("ordembump", ordembumpDao.get(ordembump.getId()));
		}catch (NoResultException e) {
			addValidation("OrdemBump nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/orders-bump/{ordembump.id}/apagar") @Privado
	public void apagar(OrdemBump ordembump) {

		try {
			HibernateUtil.beginTransaction();
			ordembumpService.apagar(ordembump);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "OrdemBumpController.apagar");
			return;
		}

		addMessage("OrdemBump removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/orders-bump/{ordembump.id}/clonar") @Privado
	public void clonar(OrdemBump ordembump) {

		try {
			HibernateUtil.beginTransaction();
			ordembump = ordembumpService.clonar(ordembump);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "OrdemBumpController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o ordembump " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("OrdemBump clonado com sucesso!");
		result.redirectTo(this).editar(ordembump);
	}




}
