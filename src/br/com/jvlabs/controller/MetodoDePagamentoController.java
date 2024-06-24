package br.com.jvlabs.controller;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.MetodoDePagamentoDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.MetodoDePagamento;
import br.com.jvlabs.service.MetodoDePagamentoService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class MetodoDePagamentoController extends ControllerProjeto {

	@Inject private MetodoDePagamentoDao metododepagamentoDao;
	@Inject private MetodoDePagamentoService metododepagamentoService;

	@Get("/adm/metodo-pagamento") @Privado
	public void index() {
	}

	@Get("/adm/metodo-pagamento/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<MetodoDePagamento> response = metododepagamentoDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/metodo-pagamento") @Privado
	public void criar(@Valid MetodoDePagamento metododepagamento) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			metododepagamentoService.cria(metododepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MetodoDePagamentoController.criar");
			return;
		}

		addMessage("MetodoDePagamento criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/metodo-pagamento/ajax") @Privado
	public void criarAjax(@Valid MetodoDePagamento metododepagamento) {

		try {
			HibernateUtil.beginTransaction();
			metododepagamento = metododepagamentoService.cria(metododepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(metododepagamento);
	}

	@Get("/adm/metodo-pagamento/novo") @Privado
	public void novo() {
	}

	@Post("/adm/metodo-pagamento/editar") @Privado
	public void atualizar(MetodoDePagamento metododepagamento) {
		validator.onErrorForwardTo(this).editar(metododepagamento);

		try {
			HibernateUtil.beginTransaction();
			metododepagamentoService.atualiza(metododepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MetodoDePagamentoController.atualizar");
		}

		addMessage("MetodoDePagamento atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/metodo-pagamento/{metododepagamento.id}/editar") @Privado
	public void editar(MetodoDePagamento metododepagamento) {
		try {
			result.include("metododepagamento", metododepagamentoDao.get(metododepagamento.getId()));
		}catch (NoResultException e) {
			addValidation("MetodoDePagamento nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/metodo-pagamento/{metododepagamento.id}/apagar") @Privado
	public void apagar(MetodoDePagamento metododepagamento) {

		try {
			HibernateUtil.beginTransaction();
			metododepagamentoService.apagar(metododepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MetodoDePagamentoController.apagar");
			return;
		}

		addMessage("MetodoDePagamento removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/metodo-pagamento/{metododepagamento.id}/clonar") @Privado
	public void clonar(MetodoDePagamento metododepagamento) {

		try {
			HibernateUtil.beginTransaction();
			metododepagamento = metododepagamentoService.clonar(metododepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MetodoDePagamentoController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o metododepagamento " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("MetodoDePagamento clonado com sucesso!");
		result.redirectTo(this).editar(metododepagamento);
	}




}
