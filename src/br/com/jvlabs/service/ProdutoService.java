package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.ProdutoDao;
import br.com.jvlabs.model.Produto;

@RequestScoped
public class ProdutoService extends ServiceProjeto {

	@Inject
	private ProdutoDao produtoDao;

	public Produto cria(Produto produto) {
		
		if(sessao.temConta()) {
			produto.setConta(sessao.getConta());
		}
		
		produto = produtoDao.merge(produto);
		logService.criarLog("PRODUTO-CREATE", produto);
		return produto;
	}

	public void atualiza(Produto produto)  {
		Produto banco = produtoDao.get(produto.getId());
		
		if(banco.temContaVinculada()) {
			produto.setConta(banco.getConta());
		} else {
			if(sessao.temConta()) {
				produto.setConta(sessao.getConta());
			}
		}
		
		produtoDao.merge(produto);
		logService.criarLog("PRODUTO-UPDATE", produto);
	}

	public void apagar(Produto produto) {
		produtoDao.delete(produto);
		logService.criarLog("PRODUTO-DELETE", produto);
	}

	public Produto clonar(Produto produto) throws CloneNotSupportedException {
		produto = produtoDao.get(produto.getId());

		Produto clonada = (Produto) produto.clone();

		clonada = produtoDao.merge(clonada);
		logService.criarLog("PRODUTO-CLONE", produto);
		return clonada;
	}


}
