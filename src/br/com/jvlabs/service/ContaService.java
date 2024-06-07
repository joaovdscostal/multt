package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.ContaDao;
import br.com.jvlabs.enumerated.TipoConta;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Usuario;

@RequestScoped
public class ContaService extends ServiceProjeto {

	@Inject private ContaDao contaDao;
	@Inject private UsuarioService usuarioService;
	
	public Conta cria(Conta conta) throws BusinessException {
		Usuario usuario = conta.getUsuario();
		usuario = usuarioService.criarUsuario(usuario,conta.getNome(),conta.getEmail());
		
		conta.setUsuario(usuario);
		conta.setTipoConta(TipoConta.PRODUTOR);
		conta = contaDao.merge(conta);
		logService.criarLog("CONTA-CREATE", conta);
		return conta;
	}

	public void atualiza(Conta conta)  {
		contaDao.update(conta);
		logService.criarLog("CONTA-UPDATE", conta);
	}

	public void apagar(Conta conta) {
		contaDao.delete(conta);
		logService.criarLog("CONTA-DELETE", conta);
	}

	public Conta clonar(Conta conta) throws CloneNotSupportedException {
		conta = contaDao.get(conta.getId());

		Conta clonada = (Conta) conta.clone();

		clonada = contaDao.merge(clonada);
		logService.criarLog("CONTA-CLONE", conta);
		return clonada;
	}


}
