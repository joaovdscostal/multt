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
import br.com.jvlabs.dao.CondicaoDePagamentoDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.CondicaoDePagamento;
import br.com.jvlabs.service.CondicaoDePagamentoService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class CondicaoDePagamentoController extends ControllerProjeto {

	@Inject private CondicaoDePagamentoDao condicaodepagamentoDao;
	@Inject private CondicaoDePagamentoService condicaodepagamentoService;

	@Get("/adm/condicoes-de-pagamento") @Privado
	public void index() {
	}

	@Get("/adm/condicoes-de-pagamento/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<CondicaoDePagamento> response = condicaodepagamentoDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/condicoes-de-pagamento") @Privado
	public void criar(@Valid CondicaoDePagamento condicaodepagamento) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			condicaodepagamentoService.cria(condicaodepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CondicaoDePagamentoController.criar");
			return;
		}

		addMessage("CondicaoDePagamento criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/condicoes-de-pagamento/ajax") @Privado
	public void criarAjax(@Valid CondicaoDePagamento condicaodepagamento) {

		try {
			HibernateUtil.beginTransaction();
			condicaodepagamento = condicaodepagamentoService.cria(condicaodepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(condicaodepagamento);
	}

	@Get("/adm/condicoes-de-pagamento/novo") @Privado
	public void novo() {
	}

	@Post("/adm/condicoes-de-pagamento/editar") @Privado
	public void atualizar(CondicaoDePagamento condicaodepagamento) {
		validator.onErrorForwardTo(this).editar(condicaodepagamento);

		try {
			HibernateUtil.beginTransaction();
			condicaodepagamentoService.atualiza(condicaodepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CondicaoDePagamentoController.atualizar");
		}

		addMessage("CondicaoDePagamento atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/condicoes-de-pagamento/{condicaodepagamento.id}/editar") @Privado
	public void editar(CondicaoDePagamento condicaodepagamento) {
		try {
			result.include("condicaodepagamento", condicaodepagamentoDao.get(condicaodepagamento.getId()));
		}catch (NoResultException e) {
			addValidation("CondicaoDePagamento nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/condicoes-de-pagamento/{condicaodepagamento.id}/apagar") @Privado
	public void apagar(CondicaoDePagamento condicaodepagamento) {

		try {
			HibernateUtil.beginTransaction();
			condicaodepagamentoService.apagar(condicaodepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CondicaoDePagamentoController.apagar");
			return;
		}

		addMessage("CondicaoDePagamento removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/condicoes-de-pagamento/{condicaodepagamento.id}/clonar") @Privado
	public void clonar(CondicaoDePagamento condicaodepagamento) {

		try {
			HibernateUtil.beginTransaction();
			condicaodepagamento = condicaodepagamentoService.clonar(condicaodepagamento);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CondicaoDePagamentoController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o condicaodepagamento " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("CondicaoDePagamento clonado com sucesso!");
		result.redirectTo(this).editar(condicaodepagamento);
	}




}
