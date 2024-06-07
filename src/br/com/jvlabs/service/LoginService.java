package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.UsuarioDao;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Usuario;

@RequestScoped
public class LoginService extends ServiceProjeto {

	@Inject
	private UsuarioDao usuarioDao;

	public Usuario logar(Usuario usuario) throws BusinessException {

		Usuario usuarioBanco = usuarioDao.procuraPorLogin(usuario.getLogin());

		if (usuarioBanco == null)
			throw new BusinessException("Usuário não encontrado!");

		if (!usuario.getEncryptedPassword().equals(usuarioBanco.getSenha()))
			throw new BusinessException("Senha incorreta!");

		logarNaSessao(usuarioBanco);

		return usuarioBanco;
	}

	public void logarNaSessao(Usuario usuarioBanco) {
		sessao.login(usuarioBanco);
	}

}