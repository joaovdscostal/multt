package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.ContaDao;
import br.com.jvlabs.dao.UsuarioDao;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Usuario;

@RequestScoped
public class LoginService extends ServiceProjeto {

	@Inject private UsuarioDao usuarioDao;
	@Inject private ContaDao contaDao;

	public Usuario logar(Usuario usuario) throws BusinessException {

		Usuario usuarioBanco = usuarioDao.procuraPorLogin(usuario.getLogin());
		Conta contaBanco = contaDao.buscarPorUsuario(usuarioBanco);
		
		if (usuarioBanco == null)
			throw new BusinessException("Usuário não encontrado!");

		if (!usuario.getEncryptedPassword().equals(usuarioBanco.getSenha()))
			throw new BusinessException("Senha incorreta!");

		logarNaSessao(usuarioBanco,contaBanco);

		return usuarioBanco;
	}

	public void logarNaSessao(Usuario usuarioBanco, Conta conta) {
		sessao.login(usuarioBanco,conta);
	}

}