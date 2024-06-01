package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.UsuarioDao;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Usuario;

@RequestScoped
public class UsuarioService extends ServiceProjeto {

	@Inject
	private UsuarioDao usuarioDao;

	public void cria(Usuario usuario) throws BusinessException {
		usuario.init();
		usuarioDao.save(usuario);
		logService.criarLog("USUARIO-CREATE", usuario);
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
