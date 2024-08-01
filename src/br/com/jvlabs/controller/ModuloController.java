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
import br.com.jvlabs.dao.ModuloDao;
import br.com.jvlabs.dao.ProdutoDao;
import br.com.jvlabs.dao.TurmaDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Modulo;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.model.Turma;
import br.com.jvlabs.service.ModuloService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class ModuloController extends ControllerProjeto {

	@Inject private ModuloDao moduloDao;
	@Inject private ModuloService moduloService;
	@Inject private ProdutoDao produtoDao;
	@Inject private TurmaDao turmaDao;

	@Get("/adm/modulos") @Privado
	public void index() {
	}

	@Get("/adm/modulos/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<Modulo> response = moduloDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/modulos") @Privado
	public void criar(@Valid Modulo modulo) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			moduloService.cria(modulo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		} catch (BusinessException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addMessage("Modulo criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}
	
	@Get("/adm/modulos/{produto.id}/template") @Privado
	public void buscarParaTemplate(Produto produto) {
		produto = produtoDao.get(produto.getId());
		List<Modulo> modulos = moduloDao.buscarModulosDoProduto(produto);	
		addObjetoAjaxRecursive(modulos,"conteudos");
	}
	
	@Get("/adm/modulos/novo") @Privado
	public void novo() {}

	@Get("/adm/modulos/novo/modal") @Privado
	public void novoModal(Long produtoId) {
		Produto produto = produtoDao.get(produtoId);
		List<Turma> turmasDoProduto = turmaDao.buscarTurmasDoProduto(produto);
		
		result.include("turmasDoProduto",turmasDoProduto);
		result.include("produto",produto);
	}
	
	@Get("/adm/modulos/editar/{modulo.id}/modal") @Privado
	public void editarModal(Modulo modulo) {
		modulo = moduloDao.get(modulo.getId());
		List<Turma> turmasDoProduto = turmaDao.buscarTurmasDoProduto(modulo.getProduto());
		
		result.include("turmasDoProduto",turmasDoProduto);
		result.include("modulo",modulo);
	}

	@Post("/adm/modulos/editar") @Privado
	public void atualizar(Modulo modulo) {
		validator.onErrorForwardTo(this).editar(modulo);

		try {
			HibernateUtil.beginTransaction();
			moduloService.atualiza(modulo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		} catch (BusinessException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addMessage("Modulo atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/modulos/{modulo.id}/editar") @Privado
	public void editar(Modulo modulo) {
		try {
			result.include("modulo", moduloDao.get(modulo.getId()));
		}catch (NoResultException e) {
			addValidation("Modulo nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/modulos/{modulo.id}/apagar") @Privado
	public void apagar(Modulo modulo) {

		try {
			HibernateUtil.beginTransaction();
			moduloService.apagar(modulo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ModuloController.apagar");
			return;
		}

		addMessage("Modulo removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/modulos/{modulo.id}/clonar") @Privado
	public void clonar(Modulo modulo) {

		try {
			HibernateUtil.beginTransaction();
			modulo = moduloService.clonar(modulo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ModuloController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o modulo " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Modulo clonado com sucesso!");
		result.redirectTo(this).editar(modulo);
	}


	@Get("/adm/modulos/ordenar") @Privado
	public void ordenar(List<Modulo> dados) {
		try {
			HibernateUtil.beginTransaction();
			moduloService.ordenar(dados);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax("Ordena&ccedil;&atilde;o efetuada com sucesso!");
	}


}
