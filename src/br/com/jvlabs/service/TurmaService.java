package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.TurmaDao;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.model.Turma;

@RequestScoped
public class TurmaService extends ServiceProjeto {

	@Inject private TurmaDao turmaDao;
	@Inject private MatriculaService matriculaService;
	@Inject private ModuloService moduloService;

	public Turma cria(Turma turma) {
		turma = turmaDao.merge(turma);	
		logService.criarLog("TURMA-CREATE", turma);
		return turma;
	}

	public void atualiza(Turma turma)  {
		turmaDao.merge(turma);
		logService.criarLog("TURMA-UPDATE", turma);
	}

	public void apagar(Turma turma) throws BusinessException {
		Boolean possuiModuloComTurma = moduloService.possuiModuloComTurma(turma);
		Boolean possuiAluno = matriculaService.possuiMatriculaComTurma(turma);
		
		turma = turmaDao.get(turma.getId());
		
		if(possuiAluno == true || possuiModuloComTurma == true) {
			throw new BusinessException("Não pode excluir uma turma com matrículas já vinculadas ou que tenha módulos vinculados a ela");
		}
		
		turmaDao.delete(turma);
		logService.criarLog("TURMA-DELETE", turma);
	}

	public Turma clonar(Turma turma) throws CloneNotSupportedException {
		turma = turmaDao.get(turma.getId());

		Turma clonada = (Turma) turma.clone();
		clonada.setOfertas(turma.getOfertas());
		
		clonada = turmaDao.merge(clonada);
		logService.criarLog("TURMA-CLONE", turma);
		return clonada;
	}

	public Boolean existeTurmaParaProduto(Produto produto) {
		Boolean possuiTurma = turmaDao.existeTurmaComProduto(produto);
		return possuiTurma;
	}


}
