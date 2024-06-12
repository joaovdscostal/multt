package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.CondicaoDePagamentoDao;
import br.com.jvlabs.model.CondicaoDePagamento;

@RequestScoped
public class CondicaoDePagamentoService extends ServiceProjeto {

	@Inject
	private CondicaoDePagamentoDao condicaodepagamentoDao;

	public CondicaoDePagamento cria(CondicaoDePagamento condicaodepagamento) {


		condicaodepagamento = condicaodepagamentoDao.merge(condicaodepagamento);
		logService.criarLog("CONDICAODEPAGAMENTO-CREATE", condicaodepagamento);
		return condicaodepagamento;
	}

	public void atualiza(CondicaoDePagamento condicaodepagamento)  {
		condicaodepagamentoDao.update(condicaodepagamento);
		logService.criarLog("CONDICAODEPAGAMENTO-UPDATE", condicaodepagamento);
	}

	public void apagar(CondicaoDePagamento condicaodepagamento) {
		condicaodepagamentoDao.delete(condicaodepagamento);
		logService.criarLog("CONDICAODEPAGAMENTO-DELETE", condicaodepagamento);
	}

	public CondicaoDePagamento clonar(CondicaoDePagamento condicaodepagamento) throws CloneNotSupportedException {
		condicaodepagamento = condicaodepagamentoDao.get(condicaodepagamento.getId());

		CondicaoDePagamento clonada = (CondicaoDePagamento) condicaodepagamento.clone();

		clonada = condicaodepagamentoDao.merge(clonada);
		logService.criarLog("CONDICAODEPAGAMENTO-CLONE", condicaodepagamento);
		return clonada;
	}


}
