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
import br.com.jvlabs.dao.ProdutoDao;
import br.com.jvlabs.dao.TurmaDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.model.Turma;
import br.com.jvlabs.service.TurmaService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class TurmaController extends ControllerProjeto {

	@Inject private TurmaDao turmaDao;
	@Inject private TurmaService turmaService;
	@Inject private ProdutoDao produtoDao;

	@Get("/adm/turmas") @Privado
	public void index() {
	}

	@Get("/adm/turmas/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request);
			TableResponse<Turma> response = turmaDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/turmas") @Privado
	public void criar(@Valid Turma turma) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			turmaService.cria(turma);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "TurmaController.criar");
			return;
		}

		addMessage("Turma criada com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/turmas/ajax") @Privado
	public void criarAjax(@Valid Turma turma) {

		try {
			HibernateUtil.beginTransaction();
			turma = turmaService.cria(turma);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(turma);
	}
	
	@Get("/adm/turmas/novo") @Privado
	public void novo() {}
	
	@Get("/adm/turmas/lista") @Privado
	public void listaTurmas() {
		List<Turma> turmas = turmaDao.findAll();
		addObjetoAjax(turmas);
	}
	
	@Get("/adm/turmas/novo/modal") @Privado
	public void novoModal(Produto produto) {
		produto = produtoDao.get(produto.getId());
		result.include("produto",produto);
	}

	@Post("/adm/turmas/editar") @Privado
	public void atualizar(Turma turma) {
		validator.onErrorForwardTo(this).editar(turma);

		try {
			HibernateUtil.beginTransaction();
			turmaService.atualiza(turma);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "TurmaController.atualizar");
		}

		addMessage("Turma atualizada com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/turmas/{turma.id}/editar") @Privado
	public void editar(Turma turma) {
		try {
			result.include("turma", turmaDao.get(turma.getId()));
		}catch (NoResultException e) {
			addValidation("Turma nao encontrada!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/turmas/{turma.id}/apagar") @Privado
	public void apagar(Turma turma) {

		try {
			HibernateUtil.beginTransaction();
			turmaService.apagar(turma);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "TurmaController.apagar");
			return;
		}

		addMessage("Turma removida com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/turmas/{turma.id}/clonar") @Privado
	public void clonar(Turma turma) {

		try {
			HibernateUtil.beginTransaction();
			turma = turmaService.clonar(turma);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "TurmaController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar a turma " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Turma clonada com sucesso!");
		result.redirectTo(this).editar(turma);
	}




}
