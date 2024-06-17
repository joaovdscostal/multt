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
import br.com.jvlabs.dao.OfertaDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Oferta;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.service.OfertaService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class OfertaController extends ControllerProjeto {

	@Inject private OfertaDao ofertaDao;
	@Inject private OfertaService ofertaService;

	@Get("/adm/ofertas") @Privado
	public void index() {
	}

	@Get("/adm/ofertas/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<Oferta> response = ofertaDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/ofertas") @Privado
	public void criar(@Valid Oferta oferta) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			ofertaService.cria(oferta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "OfertaController.criar");
			return;
		}

		addMessage("Oferta criada com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/ofertas/ajax") @Privado
	public void criarAjax(@Valid Oferta oferta) {

		try {
			HibernateUtil.beginTransaction();
			oferta = ofertaService.cria(oferta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(oferta);
	}

	@Get("/adm/ofertas/novo") @Privado
	public void novo() {
	}
	
	@Get("/adm/ofertas/{oferta.id}/buscar") @Privado
	public void retornarOferta(Oferta oferta) {
		oferta = ofertaDao.get(oferta.getId());
		addObjetoAjax(oferta);
	}
	
	@Get("/adm/ofertas/buscar-por-produto/{produto.id}") @Privado
	public void retornarOfertasDoProduto(Produto produto) {
		List<Oferta> ofertas = ofertaDao.buscarOfertasDaContaPorProduto(produto);
		addObjetoAjax(ofertas);
	}

	@Post("/adm/ofertas/editar") @Privado
	public void atualizar(Oferta oferta) {
		validator.onErrorForwardTo(this).editar(oferta);

		try {
			HibernateUtil.beginTransaction();
			ofertaService.atualiza(oferta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "OfertaController.atualizar");
		}

		addMessage("Oferta atualizada com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/ofertas/{oferta.id}/editar") @Privado
	public void editar(Oferta oferta) {
		try {
			result.include("oferta", ofertaDao.get(oferta.getId()));
		}catch (NoResultException e) {
			addValidation("Oferta nao encontrada!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/ofertas/{oferta.id}/apagar") @Privado
	public void apagar(Oferta oferta) {

		try {
			HibernateUtil.beginTransaction();
			ofertaService.apagar(oferta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "OfertaController.apagar");
			return;
		}

		addMessage("Oferta removida com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/ofertas/{oferta.id}/clonar") @Privado
	public void clonar(Oferta oferta) {

		try {
			HibernateUtil.beginTransaction();
			oferta = ofertaService.clonar(oferta);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "OfertaController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar a oferta " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Oferta clonada com sucesso!");
		result.redirectTo(this).editar(oferta);
	}




}
