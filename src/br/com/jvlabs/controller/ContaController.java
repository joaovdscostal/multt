package br.com.jvlabs.controller;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.annotation.Site;
import br.com.jvlabs.dao.ContaDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Usuario;
import br.com.jvlabs.service.ContaService;
import br.com.jvlabs.service.LoginService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class ContaController extends ControllerProjeto {

	@Inject private ContaDao contaDao;
	@Inject private ContaService contaService;
	@Inject private LoginService loginService;

	@Get("/adm/contas") @Privado
	public void index() {
	}
	
	@Get("/contas/cadastrar") @Site
	public void cadastrarPage(){}

	@Get("/adm/contas/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<Conta> response = contaDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/contas/cadastrar") @Site
	public void criar(@Valid Conta conta) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			conta = contaService.cria(conta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addValidation(e.getMessage());
			validator.onErrorForwardTo(this).cadastrarPage();
		} catch (BusinessException e) {
			HibernateUtil.rollback();
			addValidation(e.getMessage());
			addMessage(e.getMessage());
			validator.onErrorForwardTo(this).cadastrarPage();
			
		}

		addMessage("Conta criada com sucesso!");
		
		loginService.logarNaSessao(conta.getUsuario());
		result.redirectTo(IndexController.class).admin();
		
	}

	@Get("/adm/contas/novo") @Privado
	public void novo() {
	}

	@Post("/adm/contas/editar") @Privado
	public void atualizar(Conta conta) {
		validator.onErrorForwardTo(this).editar(conta);

		try {
			HibernateUtil.beginTransaction();
			contaService.atualiza(conta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ContaController.atualizar");
		}

		addMessage("Conta atualizada com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/contas/{conta.id}/editar") @Privado
	public void editar(Conta conta) {
		try {
			result.include("conta", contaDao.get(conta.getId()));
		}catch (NoResultException e) {
			addValidation("Conta nao encontrada!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/contas/{conta.id}/apagar") @Privado
	public void apagar(Conta conta) {

		try {
			HibernateUtil.beginTransaction();
			contaService.apagar(conta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ContaController.apagar");
			return;
		}

		addMessage("Conta removida com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/contas/{conta.id}/clonar") @Privado
	public void clonar(Conta conta) {

		try {
			HibernateUtil.beginTransaction();
			conta = contaService.clonar(conta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ContaController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar a conta " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Conta clonada com sucesso!");
		result.redirectTo(this).editar(conta);
	}




}
