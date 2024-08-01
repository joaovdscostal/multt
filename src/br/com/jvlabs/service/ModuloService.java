package br.com.jvlabs.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.ModuloDao;
import br.com.jvlabs.dao.TurmaDao;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Conteudo;
import br.com.jvlabs.model.Modulo;
import br.com.jvlabs.model.Turma;

@RequestScoped
public class ModuloService extends ServiceProjeto {

	@Inject private ModuloDao moduloDao;
	@Inject private TurmaDao turmaDao;
	

	public Modulo cria(Modulo modulo) throws BusinessException {	
		Integer ordem = moduloDao.pegarUltimoNumeroGeradoParardem();
		
		if(!modulo.possuiTurmas()) {
			throw new BusinessException("O módulo deve possuir ao menos uma turma");
		}
		
		if(ordem == null) {
			ordem = 0;
		} else {
			ordem++;
		}
		
		modulo.setOrdem(ordem);
		modulo = moduloDao.merge(modulo);
		logService.criarLog("MODULO-CREATE", modulo);
		return modulo;
	}

	public void atualiza(Modulo modulo) throws BusinessException  {
		Modulo banco = moduloDao.get(modulo.getId());
		modulo.setProduto(banco.getProduto());
		
		if(!modulo.possuiTurmas()) {
			throw new BusinessException("O módulo deve possuir ao menos uma turma");
		}
		
		moduloDao.merge(modulo);
		logService.criarLog("MODULO-UPDATE", modulo);
	}

	public void apagar(Modulo modulo) {
		moduloDao.delete(modulo);
		logService.criarLog("MODULO-DELETE", modulo);
	}

	public Modulo clonar(Modulo modulo) throws CloneNotSupportedException {
		modulo = moduloDao.get(modulo.getId());

		Modulo clonada = (Modulo) modulo.clone();

		clonada = moduloDao.merge(clonada);
		logService.criarLog("MODULO-CLONE", modulo);
		return clonada;
	}

	public void ordenar(List<Modulo> dados) {
		for(Modulo dado : dados) {
			Integer ordem = dado.getOrdem();
			dado = moduloDao.get(dado.getId());
			dado.setOrdem(ordem);
			moduloDao.merge(dado);
		}
	}

	public void inserirConteudo(Modulo modulo, Conteudo conteudo) {
		Modulo banco = moduloDao.get(modulo.getId());
		banco.addConteudo(conteudo);
		moduloDao.merge(banco);
	}

	public Boolean possuiModuloComTurma(Turma turma) {
		turma = turmaDao.get(turma.getId());
		return moduloDao.possuiModuloComTurma(turma);
	}

}
