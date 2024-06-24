package br.com.jvlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.annotation.Public;
import br.com.jvlabs.dao.CheckoutDao;
import br.com.jvlabs.dao.OfertaDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.dto.OfertaCheckoutDTO;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Checkout;
import br.com.jvlabs.model.Oferta;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.service.CheckoutService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class CheckoutController extends ControllerProjeto {

	@Inject private CheckoutDao checkoutDao;
	@Inject private CheckoutService checkoutService;
	@Inject private OfertaDao ofertaDao;

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
	
	@Get("/checkout/{codigo}") @Public
	public void checkoutPage(String codigo) {
		Checkout checkout = checkoutDao.pegarViaCodigo(codigo);
		result.include("checkout",checkout);
	}

	@Post("/adm/checkouts/") @Privado
	public void criar(@Valid Checkout checkout,OfertaCheckoutDTO ofertaDTO) {
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

	@Post("/adm/checkouts/modal") @Privado
	public void criarViaModal(@Valid Checkout checkout,List<OfertaCheckoutDTO> ofertasDTO,UploadedFile banner) {

		try {
			HibernateUtil.beginTransaction();
			checkout = checkoutService.criaViaProduto(checkout,ofertasDTO,banner);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		} catch (BusinessException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		} catch (IOException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(checkout);
	}
	
	@Get("/adm/checkouts/verificar-oferta/{oferta.id}/ajax") @Privado
	public void verificarOfertaAjax(Oferta oferta) {
		Boolean ofertaValida = checkoutDao.verificarDisponibilidadeDeOferta(oferta);
		addObjetoAjax(!ofertaValida);
	}
	
	@Get("/adm/checkouts/novo") @Privado
	public void novo() {}

	@Get("/adm/checkouts/novo/{produto.id}/modal") @Privado
	public void novoCheckoutPage(Produto produto) {
		List<Oferta> ofertas = ofertaDao.buscarOfertasDaContaPorProduto(produto);
		result.include("listaOfertas",ofertas);
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
