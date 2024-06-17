package br.com.jvlabs.controller;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.CategoriaProdutoDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.CategoriaProduto;
import br.com.jvlabs.service.CategoriaProdutoService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class CategoriaProdutoController extends ControllerProjeto {

	@Inject private CategoriaProdutoDao categoriaprodutoDao;
	@Inject private CategoriaProdutoService categoriaprodutoService;

	@Get("/adm/categorias-de-produto") @Privado
	public void index() {
	}

	@Get("/adm/categorias-de-produto/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<CategoriaProduto> response = categoriaprodutoDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/categorias-de-produto") @Privado
	public void criar(@Valid CategoriaProduto categoriaproduto) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			categoriaprodutoService.cria(categoriaproduto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CategoriaProdutoController.criar");
			return;
		}

		addMessage("CategoriaProduto criada com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/categorias-de-produto/ajax") @Privado
	public void criarAjax(@Valid CategoriaProduto categoriaproduto) {

		try {
			HibernateUtil.beginTransaction();
			categoriaproduto = categoriaprodutoService.cria(categoriaproduto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(categoriaproduto);
	}

	@Get("/adm/categorias-de-produto/novo") @Privado
	public void novo() {
	}

	@Post("/adm/categorias-de-produto/editar") @Privado
	public void atualizar(CategoriaProduto categoriaproduto) {
		validator.onErrorForwardTo(this).editar(categoriaproduto);

		try {
			HibernateUtil.beginTransaction();
			categoriaprodutoService.atualiza(categoriaproduto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CategoriaProdutoController.atualizar");
		}

		addMessage("CategoriaProduto atualizada com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/categorias-de-produto/{categoriaproduto.id}/editar") @Privado
	public void editar(CategoriaProduto categoriaproduto) {
		try {
			result.include("categoriaproduto", categoriaprodutoDao.get(categoriaproduto.getId()));
		}catch (NoResultException e) {
			addValidation("CategoriaProduto nao encontrada!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/categorias-de-produto/{categoriaproduto.id}/apagar") @Privado
	public void apagar(CategoriaProduto categoriaproduto) {

		try {
			HibernateUtil.beginTransaction();
			categoriaprodutoService.apagar(categoriaproduto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CategoriaProdutoController.apagar");
			return;
		}

		addMessage("CategoriaProduto removida com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/categorias-de-produto/{categoriaproduto.id}/clonar") @Privado
	public void clonar(CategoriaProduto categoriaproduto) {

		try {
			HibernateUtil.beginTransaction();
			categoriaproduto = categoriaprodutoService.clonar(categoriaproduto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "CategoriaProdutoController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar a categoriaproduto " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("CategoriaProduto clonada com sucesso!");
		result.redirectTo(this).editar(categoriaproduto);
	}




}
