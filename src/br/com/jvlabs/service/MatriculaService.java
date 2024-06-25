package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.MatriculaDao;
import br.com.jvlabs.model.Matricula;

@RequestScoped
public class MatriculaService extends ServiceProjeto {

	@Inject
	private MatriculaDao matriculaDao;

	public Matricula cria(Matricula matricula) {


		matricula = matriculaDao.merge(matricula);
		logService.criarLog("MATRICULA-CREATE", matricula);
		return matricula;
	}

	public void atualiza(Matricula matricula)  {
		matriculaDao.update(matricula);
		logService.criarLog("MATRICULA-UPDATE", matricula);
	}

	public void apagar(Matricula matricula) {
		matriculaDao.delete(matricula);
		logService.criarLog("MATRICULA-DELETE", matricula);
	}

	public Matricula clonar(Matricula matricula) throws CloneNotSupportedException {
		matricula = matriculaDao.get(matricula.getId());

		Matricula clonada = (Matricula) matricula.clone();

		clonada = matriculaDao.merge(clonada);
		logService.criarLog("MATRICULA-CLONE", matricula);
		return clonada;
	}


}
