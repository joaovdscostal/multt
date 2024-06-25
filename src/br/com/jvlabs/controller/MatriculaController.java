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
import br.com.jvlabs.dao.MatriculaDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Matricula;
import br.com.jvlabs.service.MatriculaService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class MatriculaController extends ControllerProjeto {

	@Inject private MatriculaDao matriculaDao;
	@Inject private MatriculaService matriculaService;

	@Get("/adm/matriculas") @Privado
	public void index() {
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

	@Post("/adm/matriculas") @Privado
	public void criar(@Valid Matricula matricula) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			matriculaService.cria(matricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "MatriculaController.criar");
			return;
		}

		addMessage("Matricula criada com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/matriculas/ajax") @Privado
	public void criarAjax(@Valid Matricula matricula) {

		try {
			HibernateUtil.beginTransaction();
			matricula = matriculaService.cria(matricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(matricula);
	}

	@Get("/adm/matriculas/novo") @Privado
	public void novo() {
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

	@Get("/adm/matriculas/{matricula.id}/clonar") @Privado
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
			addValidation("N&atilde;o foi poss&iacute;vel clonar a matricula " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Matricula clonada com sucesso!");
		result.redirectTo(this).editar(matricula);
	}




}
