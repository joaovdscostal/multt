package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.MatriculaDao;
import br.com.jvlabs.enumerated.StatusMatricula;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Matricula;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.model.Turma;

@RequestScoped
public class MatriculaService extends ServiceProjeto {

	@Inject private MatriculaDao matriculaDao;
	@Inject private ContaService contaService;

	public Matricula cria(Matricula matricula) throws BusinessException {
		matricula = matriculaDao.merge(matricula);
		logService.criarLog("MATRICULA-CREATE", matricula);
		return matricula;
	}
	
	public Matricula criaRapido(Matricula matricula) throws BusinessException {
		Boolean existeContaPorEmail = contaService.existeContaPorEmail(matricula.getEmailAluno());
		Conta contaParaVincular = new Conta();
		
		if(existeContaPorEmail != true) {
			contaParaVincular = contaService.criarParaMatricula(matricula);
		} else {
			contaParaVincular = contaService.buscarContaParaMatricula(matricula.getEmailAluno());
		}
		
		if(contaParaVincular.isTransient()) {
			throw new BusinessException("Erro ao tentar cadastrar ou vincular conta ao usuário");
		}
		
		matricula.setAluno(contaParaVincular);
		matricula.setStatusMatricula(StatusMatricula.CONCLUIDO);
		
		matricula = matriculaDao.merge(matricula);
		logService.criarLog("MATRICULA-CREATE", matricula);
		return matricula;
	}

	public void atualiza(Matricula matricula)  {
		matriculaDao.merge(matricula);
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

	public Long buscarQuantidadeAlunosDoProduto(Produto produto) {
		return matriculaDao.buscarQuantidadeAlunosDoProduto(produto);
	}

	public Boolean possuiMatriculaComTurma(Turma turma) {
		return matriculaDao.possuiMatriculaComTurma(turma);
	}


}
