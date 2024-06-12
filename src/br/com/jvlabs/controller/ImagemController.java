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
import br.com.jvlabs.dao.ImagemDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Imagem;
import br.com.jvlabs.service.ImagemService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class ImagemController extends ControllerProjeto {

	@Inject private ImagemDao imagemDao;
	@Inject private ImagemService imagemService;

	@Get("/adm/imagens") @Privado
	public void index() {
	}

	@Get("/adm/imagens/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request);
			TableResponse<Imagem> response = imagemDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/imagens") @Privado
	public void criar(@Valid Imagem imagem) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			imagemService.cria(imagem);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ImagemController.criar");
			return;
		}

		addMessage("Imagem criada com sucesso!");

		if(temFlagNovo())
			result.redirectTo(this).novo();
		else
			result.redirectTo(this).index();
	}

	@Post("/adm/imagens/ajax") @Privado
	public void criarAjax(@Valid Imagem imagem) {

		try {
			HibernateUtil.beginTransaction();
			imagem = imagemService.cria(imagem);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}

		addObjetoAjax(imagem);
	}

	@Get("/adm/imagens/novo") @Privado
	public void novo() {
	}

	@Post("/adm/imagens/editar") @Privado
	public void atualizar(Imagem imagem) {
		validator.onErrorForwardTo(this).editar(imagem);

		try {
			HibernateUtil.beginTransaction();
			imagemService.atualiza(imagem);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ImagemController.atualizar");
		}

		addMessage("Imagem atualizada com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/imagens/{imagem.id}/editar") @Privado
	public void editar(Imagem imagem) {
		try {
			result.include("imagem", imagemDao.get(imagem.getId()));
		}catch (NoResultException e) {
			addValidation("Imagem nao encontrada!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/imagens/{imagem.id}/apagar") @Privado
	public void apagar(Imagem imagem) {

		try {
			HibernateUtil.beginTransaction();
			imagemService.apagar(imagem);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ImagemController.apagar");
			return;
		}

		addMessage("Imagem removida com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/imagens/{imagem.id}/clonar") @Privado
	public void clonar(Imagem imagem) {

		try {
			HibernateUtil.beginTransaction();
			imagem = imagemService.clonar(imagem);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ImagemController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar a imagem " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Imagem clonada com sucesso!");
		result.redirectTo(this).editar(imagem);
	}




}
