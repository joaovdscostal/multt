package br.com.jvlabs.controller;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.ProdutoDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.service.ProdutoService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class ProdutoController extends ControllerProjeto {

	@Inject private ProdutoDao produtoDao;
	@Inject private ProdutoService produtoService;

	@Get("/adm/produtos") @Privado
	public void index() {}

	@Get("/adm/produtos/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<Produto> response = produtoDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/produtos") @Privado
	public void criar(@Valid Produto produto) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			produtoService.cria(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProdutoController.criar");
			return;
		}

		addMessage("Produto criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/produtos/ajax") @Privado
	public void criarAjax(@Valid Produto produto) {

		try {
			HibernateUtil.beginTransaction();
			produto = produtoService.cria(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(produto);
	}

	@Get("/adm/produtos/novo") @Privado
	public void novo() {
	}

	@Post("/adm/produtos/editar") @Privado
	public void atualizar(Produto produto) {
		validator.onErrorForwardTo(this).editar(produto);

		try {
			HibernateUtil.beginTransaction();
			produtoService.atualiza(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProdutoController.atualizar");
		}

		addMessage("Produto atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/produtos/{produto.id}/editar") @Privado
	public void editar(Produto produto) {
		try {
			result.include("produto", produtoDao.get(produto.getId()));
		}catch (NoResultException e) {
			addValidation("Produto nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/produtos/{produto.id}/apagar") @Privado
	public void apagar(Produto produto) {

		try {
			HibernateUtil.beginTransaction();
			produtoService.apagar(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProdutoController.apagar");
			return;
		}

		addMessage("Produto removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/produtos/{produto.id}/clonar") @Privado
	public void clonar(Produto produto) {

		try {
			HibernateUtil.beginTransaction();
			produto = produtoService.clonar(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProdutoController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o produto " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Produto clonado com sucesso!");
		result.redirectTo(this).editar(produto);
	}




}
