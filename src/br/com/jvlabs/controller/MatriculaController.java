package br.com.jvlabs.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.MatriculaDao;
import br.com.jvlabs.dao.ProdutoDao;
import br.com.jvlabs.dao.TurmaDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Matricula;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.model.Turma;
import br.com.jvlabs.service.MatriculaService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class MatriculaController extends ControllerProjeto {

	@Inject private MatriculaDao matriculaDao;
	@Inject private MatriculaService matriculaService;
	@Inject private ProdutoDao produtoDao;
	@Inject private TurmaDao turmaDao;

	@Get("/adm/matriculas") @Privado
	public void index() {}
	
	@Get("/adm/alunos/buscar-dados-produto/{idProduto}/ajax") @Privado
	public void retornarDadosAlunos(Long idProduto) {
		Set<Object> dados = new HashSet<Object>();
		Produto produto = produtoDao.get(idProduto);
		
		Long quantidadeAlunos = matriculaService.buscarQuantidadeAlunosDoProduto(produto);
		//BigDecimal progressaoConcluidosTotal = matriculaService.buscarPercentualProgressaoCobluidaDeAlunosDoProduto(produto);
		//BigDecimal progressaoMediaTotal = matriculaService.buscarMediaProgressaoQuantidadeAlunosProduto(produto);
		
		dados.add(quantidadeAlunos);
		//dados.add(progressaoConcluidosTotal);
		//dados.add(progressaoMediaTotal);
		
		addObjetoAjax(dados);
	}

	@Get("/adm/matriculas/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request);
			TableResponse<Matricula> response = matriculaDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}
	
	@Get("/adm/matriculas/por-produto/{produto.id}/json") @Privado
	public void paginatePorProduto(Table datatable,Produto produto) {
		try {
			datatable.filters(request);
			TableResponse<Matricula> response = matriculaDao.paginatePorProduto(datatable,produto);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/matriculas") @Privado
	public void criar(@Valid Matricula matricula) {
		validator.onErrorForwardTo(this).novo(null);

		try {
			HibernateUtil.beginTransaction();
			matriculaService.cria(matricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MatriculaController.criar");
			return;
		} catch (BusinessException e) {
			HibernateUtil.rollback();
			addValidation("N&atilde;o foi poss&iacute;vel clonar a matricula " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Matricula criada com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo(null);
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/matriculas/ajax") @Privado
	public void criarAjax(@Valid Matricula matricula) {

		try {
			HibernateUtil.beginTransaction();
			matricula = matriculaService.criaRapido(matricula);
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

		addObjetoAjax(matricula);
	}

	@Get("/adm/matriculas/novo/modal") @Privado
	public void novo(Long produtoId) {
		if(produtoId != null) {
			Produto produto = produtoDao.get(produtoId);
			List<Turma> turmas = turmaDao.buscarTurmasDoProduto(produto);
			result.include("turmas",turmas);
		}
		
	}

	@Post("/adm/matriculas/editar") @Privado
	public void atualizar(Matricula matricula) {
		validator.onErrorForwardTo(this).editar(matricula);

		try {
			HibernateUtil.beginTransaction();
			matriculaService.atualiza(matricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MatriculaController.atualizar");
		}

		addMessage("Matricula atualizada com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/matriculas/{matricula.id}/editar") @Privado
	public void editar(Matricula matricula) {
		try {
			result.include("matricula", matriculaDao.get(matricula.getId()));
		}catch (NoResultException e) {
			addValidation("Matricula nao encontrada!");
			validator.onErrorForwardTo(this).index();
		}
	}
	
	@Get("/adm/matriculas/{matricula.id}/editar/modal") @Privado
	public void editarModal(Matricula matricula) {
		try {
			matricula = matriculaDao.get(matricula.getId());
			Produto produto = produtoDao.get(matricula.getIdProdutoDaTurma());
			List<Turma> turmas = turmaDao.buscarTurmasDoProduto(produto);
			
			result.include("turmas",turmas);
			result.include("matricula", matricula);
		}catch (NoResultException e) {
			addValidation("Matricula nao encontrada!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/matriculas/{matricula.id}/apagar") @Privado
	public void apagar(Matricula matricula) {

		try {
			HibernateUtil.beginTransaction();
			matriculaService.apagar(matricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MatriculaController.apagar");
			return;
		}

		addMessage("Matricula removida com sucesso!");
		result.redirectTo(this).index();
	}
	
	@Post("/adm/matriculas/{matricula.id}/apagar/ajax") @Privado
	public void apagarAjax(Matricula matricula) {

		try {
			HibernateUtil.beginTransaction();
			matriculaService.apagar(matricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MatriculaController.apagar");
			return;
		}
		
		addObjetoAjax("Matricula excluída com sucesso");

	}

	/*@Get("/adm/matriculas/{matricula.id}/clonar") @Privado
	public void clonar(Matricula matricula) {

		try {
			HibernateUtil.beginTransaction();
			matricula = matriculaService.clonar(matricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MatriculaController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			HibernateUtil.rollback();
			addValidation("N&atilde;o foi poss&iacute;vel clonar a matricula " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Matricula clonada com sucesso!");
		result.redirectTo(this).editar(matricula);
	}*/




}
