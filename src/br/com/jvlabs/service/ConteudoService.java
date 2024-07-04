package br.com.jvlabs.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.ConteudoDao;
import br.com.jvlabs.model.Conteudo;
import br.com.jvlabs.model.Modulo;

@RequestScoped
public class ConteudoService extends ServiceProjeto {

	@Inject private ConteudoDao conteudoDao;
	@Inject private ModuloService moduloService;
	
	public Conteudo cria(Conteudo conteudo,Modulo modulo) {
		

		conteudo = conteudoDao.merge(conteudo);
		moduloService.inserirConteudo(modulo,conteudo);
		
		logService.criarLog("CONTEUDO-CREATE", conteudo);
		return conteudo;
	}

	public void atualiza(Conteudo conteudo)  {
		conteudoDao.update(conteudo);
		logService.criarLog("CONTEUDO-UPDATE", conteudo);
	}

	public void apagar(Conteudo conteudo) {
		conteudoDao.delete(conteudo);
		logService.criarLog("CONTEUDO-DELETE", conteudo);
	}

	public Conteudo clonar(Conteudo conteudo) throws CloneNotSupportedException {
		conteudo = conteudoDao.get(conteudo.getId());

		Conteudo clonada = (Conteudo) conteudo.clone();

		clonada = conteudoDao.merge(clonada);
		logService.criarLog("CONTEUDO-CLONE", conteudo);
		return clonada;
	}

	public void ordenar(List<Conteudo> dados) {
		for(Conteudo dado : dados) {
			Integer ordem = dado.getOrdem();
			dado = conteudoDao.get(dado.getId());
			dado.setOrdem(ordem);
			conteudoDao.merge(dado);
		}
	}

}
