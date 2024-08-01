package br.com.jvlabs.service;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.jvlabs.dao.ContaDao;
import br.com.jvlabs.enumerated.TipoConta;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Matricula;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Usuario;

@RequestScoped
public class ContaService extends ServiceProjeto {

	@Inject private ContaDao contaDao;
	@Inject private UsuarioService usuarioService;
	@Inject private ArquivoServicePadrao arquivoServicePadrao;
	
	public Conta cria(Conta conta) throws BusinessException {
		Usuario usuario = conta.getUsuario();
		usuario = usuarioService.criarUsuario(usuario,conta.getNome(),conta.getEmail());
		
		conta.setUsuario(usuario);
		conta.setTipoConta(TipoConta.PRODUTOR);
		conta = contaDao.merge(conta);
		logService.criarLog("CONTA-CREATE", conta);
		return conta;
	}

	public Conta atualiza(Conta conta,UploadedFile imagem) throws BusinessException, IOException  {
		Conta banco = contaDao.get(conta.getId());
		
		conta.setTipoConta(banco.getTipoConta());
		conta.setUsuario(banco.getUsuario());
		
		if(imagem != null) {
			String nomeImagem = arquivoServicePadrao.salvarDocumentoParaPerfilDeConta(imagem, conta);

			if(!arquivoServicePadrao.isExtensaoValidaParaImagem(nomeImagem)) {
				throw new BusinessException("Este tipo de arquivo não é válido para este campo");
			}

			conta.setImagem(nomeImagem);

		} else {
			conta.setImagem(banco.getImagem());
		}
		
		conta = contaDao.merge(conta);
		logService.criarLog("CONTA-UPDATE", conta);
		
		sessao.setConta(conta);
		return conta;
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

	public Conta criarParaMatricula(Matricula matricula) throws BusinessException {
		Usuario usuario = new Usuario();
		usuario.setTipo(TipoUsuario.CONSUMIDOR);
		usuario = usuarioService.criarUsuario(usuario,matricula.getNomeAluno(),matricula.getEmailAluno());
		
		Conta conta = Conta.builder()
				           .email(usuario.getEmail())
				           .tipoConta(TipoConta.ALUNO)
				           .usuario(usuario)
				           .build();
		
		conta.setNome(usuario.getNome());
		conta = contaDao.merge(conta);
		logService.criarLog("CONTA-CREATE", conta);
		return conta;
	}

	public Boolean existeContaPorEmail(String email) {
		return contaDao.existeContaPorEmail(email);
	}

	public Conta buscarContaParaMatricula(String email) {
		// TODO Auto-generated method stub
		return contaDao.buscarContaParaMatricula(email);
	}

	


}
