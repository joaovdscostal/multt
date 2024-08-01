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
import br.com.jvlabs.dao.HistoricoMatriculaDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.HistoricoMatricula;
import br.com.jvlabs.service.HistoricoMatriculaService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class HistoricoMatriculaController extends ControllerProjeto {

	@Inject private HistoricoMatriculaDao historicomatriculaDao;
	@Inject private HistoricoMatriculaService historicomatriculaService;

	@Get("/adm/historico-matricula") @Privado
	public void index() {
	}

	@Get("/adm/historico-matricula/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request);
			TableResponse<HistoricoMatricula> response = historicomatriculaDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/historico-matricula") @Privado
	public void criar(@Valid HistoricoMatricula historicomatricula) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			historicomatriculaService.cria(historicomatricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "HistoricoMatriculaController.criar");
			return;
		}

		addMessage("HistoricoMatricula criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/historico-matricula/ajax") @Privado
	public void criarAjax(@Valid HistoricoMatricula historicomatricula) {

		try {
			HibernateUtil.beginTransaction();
			historicomatricula = historicomatriculaService.cria(historicomatricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(historicomatricula);
	}

	@Get("/adm/historico-matricula/novo") @Privado
	public void novo() {
	}

	@Post("/adm/historico-matricula/editar") @Privado
	public void atualizar(HistoricoMatricula historicomatricula) {
		validator.onErrorForwardTo(this).editar(historicomatricula);

		try {
			HibernateUtil.beginTransaction();
			historicomatriculaService.atualiza(historicomatricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "HistoricoMatriculaController.atualizar");
		}

		addMessage("HistoricoMatricula atualizado com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/historico-matricula/{historicomatricula.id}/editar") @Privado
	public void editar(HistoricoMatricula historicomatricula) {
		try {
			result.include("historicomatricula", historicomatriculaDao.get(historicomatricula.getId()));
		}catch (NoResultException e) {
			addValidation("HistoricoMatricula nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/historico-matricula/{historicomatricula.id}/apagar") @Privado
	public void apagar(HistoricoMatricula historicomatricula) {

		try {
			HibernateUtil.beginTransaction();
			historicomatriculaService.apagar(historicomatricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "HistoricoMatriculaController.apagar");
			return;
		}

		addMessage("HistoricoMatricula removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/historico-matricula/{historicomatricula.id}/clonar") @Privado
	public void clonar(HistoricoMatricula historicomatricula) {

		try {
			HibernateUtil.beginTransaction();
			historicomatricula = historicomatriculaService.clonar(historicomatricula);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "HistoricoMatriculaController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o historicomatricula " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("HistoricoMatricula clonado com sucesso!");
		result.redirectTo(this).editar(historicomatricula);
	}




}
