package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.OrdemBumpDao;
import br.com.jvlabs.model.OrdemBump;

@RequestScoped
public class OrdemBumpService extends ServiceProjeto {

	@Inject
	private OrdemBumpDao ordembumpDao;

	public OrdemBump cria(OrdemBump ordembump) {


		ordembump = ordembumpDao.merge(ordembump);
		logService.criarLog("ORDEMBUMP-CREATE", ordembump);
		return ordembump;
	}

	public void atualiza(OrdemBump ordembump)  {
		ordembumpDao.update(ordembump);
		logService.criarLog("ORDEMBUMP-UPDATE", ordembump);
	}

	public void apagar(OrdemBump ordembump) {
		ordembumpDao.delete(ordembump);
		logService.criarLog("ORDEMBUMP-DELETE", ordembump);
	}

	public OrdemBump clonar(OrdemBump ordembump) throws CloneNotSupportedException {
		ordembump = ordembumpDao.get(ordembump.getId());

		OrdemBump clonada = (OrdemBump) ordembump.clone();

		clonada = ordembumpDao.merge(clonada);
		logService.criarLog("ORDEMBUMP-CLONE", ordembump);
		return clonada;
	}


}
