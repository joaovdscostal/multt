package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.UnidadeDao;
import br.com.jvlabs.model.Unidade;

@RequestScoped
public class UnidadeService extends ServiceProjeto {

	@Inject
	private UnidadeDao unidadeDao;

	public void cria(Unidade unidade) {
		unidade.gerarUrlCurta();
		unidadeDao.save(unidade);
		logService.criarLog("UNIDADE-CREATE", unidade);
	}

	public void atualiza(Unidade unidade)  {
		unidadeDao.update(unidade);
		logService.criarLog("UNIDADE-UPDATE", unidade);
	}

	public void apagar(Unidade unidade) {
		unidadeDao.delete(unidade);
		logService.criarLog("UNIDADE-DELETE", unidade);
	}

}
