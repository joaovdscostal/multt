package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.environment.Environment;
import br.com.jvlabs.dao.UsuarioDao;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Usuario;

@RequestScoped
public class UsuarioService extends ServiceProjeto {

	@Inject private UsuarioDao usuarioDao;
	@Inject Environment environment;

	public void cria(Usuario usuario) throws BusinessException {
		usuario.init();
		usuarioDao.merge(usuario);
		logService.criarLog("USUARIO-CREATE", usuario);
	}
	
	public Usuario criarUsuario(Usuario usuario, String nome, String email) throws BusinessException {
		if(usuario.temSenha()) {
			usuario = criarUsuario(usuario, nome,email, false);
		} else {
			usuario = criarUsuario(usuario, nome,email, true);
		}
		
		return usuario;
	}


	public Usuario criarUsuario(Usuario usuario, String nome,String email, Boolean comSenhaPadrao) throws BusinessException {
		if(comSenhaPadrao) {
			usuario.init(environment.get("senhaPadrao"));
		}else {
			if(usuario.temSenha()) {
				usuario.init(usuario.getSenha());
			} else {
				throw new BusinessException("È necessárioa senha para cadastro do usuário");
			}
		}

		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setLogin(email);
		usuario.setAtivo(true);
		usuario.setTipo(TipoUsuario.USUARIO);

		if(usuarioDao.existeUsuarioComLogin(usuario.getLogin()))
			throw new BusinessException("Ja existe um usuario com esse login!");

		Usuario usuarioBanco = usuarioDao.merge(usuario);

		if(logService != null)
			logService.criarLog("USUARIO-CREATE", usuario);

		return usuarioBanco;
	}

	public void atualiza(Usuario usuario) throws BusinessException {
		Usuario usuarioBanco = usuarioDao.get(usuario.getId());
		usuario.setSenha(usuarioBanco.getSenha());
		usuario.setContadorAcesso(usuarioBanco.getContadorAcesso());

		usuarioDao.merge(usuario);
		logService.criarLog("USUARIO-UPDATE", usuario);
	}

	public void apagar(Usuario usuario) {
		usuarioDao.delete(usuario);
		logService.criarLog("USUARIO-DELETE", usuario);
	}

	public void realizaAcessoNoSistema(Usuario ususario) {
		Usuario usuarioBanco = usuarioDao.get(ususario.getId());
		usuarioBanco.makeAccessInSystem();
		this.usuarioDao.merge(usuarioBanco);
	}

	public void atualizaSenha(Usuario usuario, boolean onlyMyPassword) throws BusinessException {
		Usuario usuarioBanco = usuarioDao.get(usuario.getId());

		usuario.verificaSenha();

		if (onlyMyPassword) {
			usuarioBanco = usuarioDao.buscarPorLoginESenha(usuario.getLogin(), usuario.getOldPasswordEncrypted());

			if (usuarioBanco == null) {
				throw new BusinessException("current.password.does.not.match");
			}
			if (!usuarioBanco.equals(sessao.getUsuario())) {
				throw new BusinessException("Sem permiss&atilde;o");
			}
		}

		usuarioBanco.setEncryptedPassword(usuario.getSenha());

		this.usuarioDao.merge(usuarioBanco);

		logService.criarLog("USUARIO-UPDATEPASSWORD", usuario);
	}

	public void verificarUsuarioAdmin() {

		if(!usuarioDao.existeUsuarioComLogin("admin")) {
			Usuario usuario = Usuario.padrao();
			usuarioDao.save(usuario);
			logService.criarLog("USUARIO-CREATE", usuario);
		}

	}

	public Usuario atualizaAjax(Usuario usuario) {
		Usuario banco = usuarioDao.get(usuario.getId());
		banco.setAbaLateral(usuario.getAbaLateral());
		sessao.setUsuario(banco);
		return usuarioDao.merge(banco);
	}

}
