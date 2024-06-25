package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.TurmaDao;
import br.com.jvlabs.model.Turma;

@RequestScoped
public class TurmaService extends ServiceProjeto {

	@Inject
	private TurmaDao turmaDao;

	public Turma cria(Turma turma) {


		turma = turmaDao.merge(turma);
		logService.criarLog("TURMA-CREATE", turma);
		return turma;
	}

	public void atualiza(Turma turma)  {
		turmaDao.update(turma);
		logService.criarLog("TURMA-UPDATE", turma);
	}

	public void apagar(Turma turma) {
		turmaDao.delete(turma);
		logService.criarLog("TURMA-DELETE", turma);
	}

	public Turma clonar(Turma turma) throws CloneNotSupportedException {
		turma = turmaDao.get(turma.getId());

		Turma clonada = (Turma) turma.clone();

		clonada = turmaDao.merge(clonada);
		logService.criarLog("TURMA-CLONE", turma);
		return clonada;
	}


}
