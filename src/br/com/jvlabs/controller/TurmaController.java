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
import br.com.jvlabs.exception.BusinessException;
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
	public void index() {}
	
	@Get("/adm/modulos/turmas/produto/{produto.id}/possui-turmas/ajax") @Privado
	public void possuiTurmas(Produto produto) {
		try {
			Boolean possuiTurma = turmaService.existeTurmaParaProduto(produto);
			
			if(possuiTurma == true) {
				addObjetoAjax(possuiTurma);
			} else {
				addErroAjax("Cadastre ao menos uma turma antes de cadastrar os módulos");
			}
			
		} catch (Exception e) {
			addErroAjax("Erro ao verificar se produto possui turmas");
		}
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
	
	@Get("/adm/turmas/{produto.id}/json") @Privado
	public void paginate(Table datatable,Produto produto) {
		try {
			datatable.filters(request);
			TableResponse<Turma> response = turmaDao.paginateComProduto(datatable,produto);
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
	
	@Get("/adm/turmas/{turma.id}/editar/modal") @Privado
	public void editarModal(Turma turma) {
		try {
			turma = turmaDao.get(turma.getId());
			Produto produto = produtoDao.get(turma.getProduto().getId());
			result.include("produto",produto);
			result.include("turma", turma);	
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
		} catch (BusinessException e) {
			HibernateUtil.rollback();
			addValidation(e.getMessage());
			return;
		}

		addMessage("Turma removida com sucesso!");
		result.redirectTo(this).index();
	}
	
	@Post("/adm/turmas/{turma.id}/apagar/ajax") @Privado
	public void apagarAjax(Turma turma) {

		try {
			HibernateUtil.beginTransaction();
			turmaService.apagar(turma);
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
		
		addObjetoAjax("Turma excluída com sucesso");
		return;
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
	
	@Post("/adm/turmas/{turma.id}/clonar/ajax") @Privado
	public void clonarAjax(Turma turma) {

		try {
			HibernateUtil.beginTransaction();
			turma = turmaService.clonar(turma);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "TurmaController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			HibernateUtil.rollback();
			addValidation("N&atilde;o foi poss&iacute;vel clonar a turma" + e.getMessage());
			return;
		}

		addObjetoAjax("Turma clonada com sucesso");
	}




}
