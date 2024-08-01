package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.HistoricoMatriculaDao;
import br.com.jvlabs.model.HistoricoMatricula;

@RequestScoped
public class HistoricoMatriculaService extends ServiceProjeto {

	@Inject
	private HistoricoMatriculaDao historicomatriculaDao;

	public HistoricoMatricula cria(HistoricoMatricula historicomatricula) {


		historicomatricula = historicomatriculaDao.merge(historicomatricula);
		logService.criarLog("HISTORICOMATRICULA-CREATE", historicomatricula);
		return historicomatricula;
	}

	public void atualiza(HistoricoMatricula historicomatricula)  {
		historicomatriculaDao.update(historicomatricula);
		logService.criarLog("HISTORICOMATRICULA-UPDATE", historicomatricula);
	}

	public void apagar(HistoricoMatricula historicomatricula) {
		historicomatriculaDao.delete(historicomatricula);
		logService.criarLog("HISTORICOMATRICULA-DELETE", historicomatricula);
	}

	public HistoricoMatricula clonar(HistoricoMatricula historicomatricula) throws CloneNotSupportedException {
		historicomatricula = historicomatriculaDao.get(historicomatricula.getId());

		HistoricoMatricula clonada = (HistoricoMatricula) historicomatricula.clone();

		clonada = historicomatriculaDao.merge(clonada);
		logService.criarLog("HISTORICOMATRICULA-CLONE", historicomatricula);
		return clonada;
	}


}
