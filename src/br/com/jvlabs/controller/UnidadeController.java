package br.com.jvlabs.controller;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.UnidadeDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Unidade;
import br.com.jvlabs.service.UnidadeService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;


@Controller
public class UnidadeController extends ControllerProjeto {

	@Inject private UnidadeDao unidadeDao;
	@Inject private UnidadeService unidadeService;

	@Get("/adm/unidades") @Privado({TipoUsuario.ADMINISTRADOR})
	public void index() {
	}

	@Get("/adm/unidades/json") @Privado({TipoUsuario.ADMINISTRADOR})
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<Unidade> response = unidadeDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/unidades") @Privado({TipoUsuario.ADMINISTRADOR})
	public void criar(@Valid Unidade unidade) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			unidadeService.cria(unidade);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UnidadeController.criar");
			return;
		}

		addMessage("Unidade criada com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/unidades/novo") @Privado({TipoUsuario.ADMINISTRADOR})
	public void novo() {
	}

	@Post("/adm/unidades/editar") @Privado({TipoUsuario.ADMINISTRADOR})
	public void atualizar(Unidade unidade) {
		validator.onErrorForwardTo(this).editar(unidade);

		try {
			HibernateUtil.beginTransaction();
			unidadeService.atualiza(unidade);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UnidadeController.atualizar");
		}

		addMessage("Unidade atualizada com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/unidades/{unidade.id}/editar") @Privado({TipoUsuario.ADMINISTRADOR})
	public void editar(Unidade unidade) {
		try {
			result.include("unidade", unidadeDao.get(unidade.getId()));
		}catch (NoResultException e) {
			addValidation("Unidade nao encontrada!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/unidades/{unidade.id}/apagar") @Privado({TipoUsuario.ADMINISTRADOR})
	public void apagar(Unidade unidade) {

		try {
			HibernateUtil.beginTransaction();
			unidadeService.apagar(unidade);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UnidadeController.apagar");
			return;
		}

		addMessage("Unidade removida com sucesso!");
		result.redirectTo(this).index();
	}


}
