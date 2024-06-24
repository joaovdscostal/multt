package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.CategoriaProdutoDao;
import br.com.jvlabs.model.CategoriaProduto;

@RequestScoped
public class CategoriaProdutoService extends ServiceProjeto {

	@Inject
	private CategoriaProdutoDao categoriaprodutoDao;

	public CategoriaProduto cria(CategoriaProduto categoriaproduto) {


		categoriaproduto = categoriaprodutoDao.merge(categoriaproduto);
		logService.criarLog("CATEGORIAPRODUTO-CREATE", categoriaproduto);
		return categoriaproduto;
	}

	public void atualiza(CategoriaProduto categoriaproduto)  {
		categoriaprodutoDao.update(categoriaproduto);
		logService.criarLog("CATEGORIAPRODUTO-UPDATE", categoriaproduto);
	}

	public void apagar(CategoriaProduto categoriaproduto) {
		categoriaprodutoDao.delete(categoriaproduto);
		logService.criarLog("CATEGORIAPRODUTO-DELETE", categoriaproduto);
	}

	public CategoriaProduto clonar(CategoriaProduto categoriaproduto) throws CloneNotSupportedException {
		categoriaproduto = categoriaprodutoDao.get(categoriaproduto.getId());

		CategoriaProduto clonada = (CategoriaProduto) categoriaproduto.clone();

		clonada = categoriaprodutoDao.merge(clonada);
		logService.criarLog("CATEGORIAPRODUTO-CLONE", categoriaproduto);
		return clonada;
	}


}
