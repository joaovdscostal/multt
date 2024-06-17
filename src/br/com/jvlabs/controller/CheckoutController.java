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
import br.com.jvlabs.dao.CheckoutDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Checkout;
import br.com.jvlabs.service.CheckoutService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class CheckoutController extends ControllerProjeto {

	@Inject private CheckoutDao checkoutDao;
	@Inject private CheckoutService checkoutService;

	@Get("/adm/checkouts") @Privado
	public void index() {
	}

	@Get("/adm/checkouts/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<Checkout> response = checkoutDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/checkouts") @Privado
	public void criar(@Valid Checkout checkout) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			checkoutService.cria(checkout);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CheckoutController.criar");
			return;
		}

		addMessage("Checkout criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/checkouts/ajax") @Privado
	public void criarAjax(@Valid Checkout checkout) {

		try {
			HibernateUtil.beginTransaction();
			checkout = checkoutService.cria(checkout);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(checkout);
	}

	@Get("/adm/checkouts/novo") @Privado
	public void novo() {
	}

	@Post("/adm/checkouts/editar") @Privado
	public void atualizar(Checkout checkout) {
		validator.onErrorForwardTo(this).editar(checkout);

		try {
			HibernateUtil.beginTransaction();
			checkoutService.atualiza(checkout);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CheckoutController.atualizar");
		}

		addMessage("Checkout atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/checkouts/{checkout.id}/editar") @Privado
	public void editar(Checkout checkout) {
		try {
			result.include("checkout", checkoutDao.get(checkout.getId()));
		}catch (NoResultException e) {
			addValidation("Checkout nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/checkouts/{checkout.id}/apagar") @Privado
	public void apagar(Checkout checkout) {

		try {
			HibernateUtil.beginTransaction();
			checkoutService.apagar(checkout);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CheckoutController.apagar");
			return;
		}

		addMessage("Checkout removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/checkouts/{checkout.id}/clonar") @Privado
	public void clonar(Checkout checkout) {

		try {
			HibernateUtil.beginTransaction();
			checkout = checkoutService.clonar(checkout);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CheckoutController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o checkout " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Checkout clonado com sucesso!");
		result.redirectTo(this).editar(checkout);
	}




}
