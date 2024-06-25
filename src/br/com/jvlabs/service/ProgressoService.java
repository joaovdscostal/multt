package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.ProgressoDao;
import br.com.jvlabs.model.Progresso;

@RequestScoped
public class ProgressoService extends ServiceProjeto {

	@Inject
	private ProgressoDao progressoDao;

	public Progresso cria(Progresso progresso) {


		progresso = progressoDao.merge(progresso);
		logService.criarLog("PROGRESSO-CREATE", progresso);
		return progresso;
	}

	public void atualiza(Progresso progresso)  {
		progressoDao.update(progresso);
		logService.criarLog("PROGRESSO-UPDATE", progresso);
	}

	public void apagar(Progresso progresso) {
		progressoDao.delete(progresso);
		logService.criarLog("PROGRESSO-DELETE", progresso);
	}

	public Progresso clonar(Progresso progresso) throws CloneNotSupportedException {
		progresso = progressoDao.get(progresso.getId());

		Progresso clonada = (Progresso) progresso.clone();

		clonada = progressoDao.merge(clonada);
		logService.criarLog("PROGRESSO-CLONE", progresso);
		return clonada;
	}


}
