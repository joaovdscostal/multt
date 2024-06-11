package br.com.jvlabs.controller;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.UsuarioDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Usuario;
import br.com.jvlabs.service.UsuarioService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;
import br.com.jvlabs.util.Sessao;


@Controller
public class UsuarioController extends ControllerProjeto {

	@Inject private UsuarioDao usuarioDao;
	@Inject private UsuarioService usuarioService;
	@Inject private Sessao sessao;

	@Get("/adm/usuarios") @Privado({TipoUsuario.ADMINISTRADOR})
	public void index() {}

	@Get("/adm/usuarios/json") @Privado({TipoUsuario.ADMINISTRADOR})
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome", "tipo");
			TableResponse<Usuario> response = usuarioDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("ajax.gson.error");
		}
	}

	@Post("/adm/usuarios") @Privado({TipoUsuario.ADMINISTRADOR})
	public void criar(@Valid Usuario usuario) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			usuarioService.cria(usuario);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UsuarioController.criar");
			return;
		}catch (BusinessException e) {
			HibernateUtil.rollback();
			addValidation(e.getMessage()).onErrorForwardTo(this).novo();
		}

		addMessage("Usuario criado!");
		result.redirectTo(this).index();
	}

	@Get("/adm/usuarios/novo") @Privado({TipoUsuario.ADMINISTRADOR})
	public void novo() {}

	@Post("/adm/usuarios/editar") @Privado({TipoUsuario.ADMINISTRADOR})
	public void atualizar(Usuario usuario) {
		validator.onErrorForwardTo(this).editar(usuario);

		try {
			HibernateUtil.beginTransaction();
			usuarioService.atualiza(usuario);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UsuarioController.atualizar");
		}catch (BusinessException e) {
			addValidation(e.getMessage());
			validator.onErrorForwardTo(this).editar(usuario);
		}

		addMessage("Usuario atualizado!");
		result.redirectTo(this).index();
	}
	
	@Get("/adm/usuarios/{usuario.id}/editar") @Privado({TipoUsuario.ADMINISTRADOR})
	public void editar(Usuario usuario) {
		try {
			result.include("usuario", usuarioDao.get(usuario.getId()));
		}catch (NoResultException e) {
			result.notFound();
		}
	}

	@Get("/adm/usuarios/{usuario.id}/apagar") @Privado({TipoUsuario.ADMINISTRADOR})
	public void apagar(Usuario usuario) {

		try {
			HibernateUtil.beginTransaction();
			usuarioService.apagar(usuario);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UsuarioController.apagar");
			return;
		}

		addMessage("Usuario apagado!");
		result.redirectTo(this).index();
	}

	//alterar todas senhas

	@Get("/adm/usuarios/{usuario.id}/senha") @Privado()
	public void mudarSenha(Usuario usuario) {
		result.include("usuario", usuarioDao.get(usuario.getId()));
	}

	@Post("/adm/usuarios/{usuario.id}/senha") @Privado()
	public void senha(Usuario usuario) {

		try {
			HibernateUtil.beginTransaction();
			usuarioService.atualizaSenha(usuario, false);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UsuarioController.senha");
			return;
		} catch (BusinessException e) {
			HibernateUtil.rollback();
			addValidation(e.getMessage()).onErrorForwardTo(this).mudarSenha(usuario);
		}
		addMessage("Senha atualizada!");
		
		if(sessao.isAdministrador()) {
			result.redirectTo(this).index();
		} else {
			result.redirectTo(ContaController.class).alterarContaPage();
		}
		
		
		
		
	}

	//alterar minha senha

	@Get("/adm/alterarminhasenha") @Privado
	public void mudarMinhaSenha() {
		Usuario usuarioBd = usuarioDao.get(sessao.getUsuario().getId());
		result.include("usuario", usuarioBd);
	}

	@Post("/adm/alterarminhasenha") @Privado
	public void minhaSenha(Usuario usuario) {
		try {
			HibernateUtil.beginTransaction();
			usuarioService.atualizaSenha(usuario, true);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UsuarioController.minhaSenha");
			return;
		}catch (BusinessException e) {
			HibernateUtil.rollback();
			addValidation(e.getMessage()).onErrorForwardTo(this).mudarMinhaSenha();
		}

		addMessage("Senha atualizada!");
		result.redirectTo(this).mudarMinhaSenha();
	}

	@Post("/adm/usuarios/admin-tag/ajax") @Privado()
	public void adminTagAjax(Usuario usuario) throws BusinessException {
		try {
			HibernateUtil.beginTransaction();
			usuario = usuarioService.atualizaAjax(usuario);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "UsuarioController.atualizar");
		}

		addObjetoAjax(usuario);

	}
}
