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
import br.com.jvlabs.dao.ModuloDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Modulo;
import br.com.jvlabs.service.ModuloService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class ModuloController extends ControllerProjeto {

	@Inject private ModuloDao moduloDao;
	@Inject private ModuloService moduloService;

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
			addLogAndSendToErrorPage(e, "ModuloController.criar");
			return;
		}

		addMessage("Modulo criado com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/modulos/ajax") @Privado
	public void criarAjax(@Valid Modulo modulo) {

		try {
			HibernateUtil.beginTransaction();
			modulo = moduloService.cria(modulo);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(modulo);
	}

	@Get("/adm/modulos/novo") @Privado
	public void novo() {
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
			addLogAndSendToErrorPage(e, "ModuloController.atualizar");
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
