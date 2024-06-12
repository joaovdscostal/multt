package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.MetodoDePagamentoDao;
import br.com.jvlabs.model.MetodoDePagamento;

@RequestScoped
public class MetodoDePagamentoService extends ServiceProjeto {

	@Inject
	private MetodoDePagamentoDao metododepagamentoDao;

	public MetodoDePagamento cria(MetodoDePagamento metododepagamento) {


		metododepagamento = metododepagamentoDao.merge(metododepagamento);
		logService.criarLog("METODODEPAGAMENTO-CREATE", metododepagamento);
		return metododepagamento;
	}

	public void atualiza(MetodoDePagamento metododepagamento)  {
		metododepagamentoDao.update(metododepagamento);
		logService.criarLog("METODODEPAGAMENTO-UPDATE", metododepagamento);
	}

	public void apagar(MetodoDePagamento metododepagamento) {
		metododepagamentoDao.delete(metododepagamento);
		logService.criarLog("METODODEPAGAMENTO-DELETE", metododepagamento);
	}

	public MetodoDePagamento clonar(MetodoDePagamento metododepagamento) throws CloneNotSupportedException {
		metododepagamento = metododepagamentoDao.get(metododepagamento.getId());

		MetodoDePagamento clonada = (MetodoDePagamento) metododepagamento.clone();

		clonada = metododepagamentoDao.merge(clonada);
		logService.criarLog("METODODEPAGAMENTO-CLONE", metododepagamento);
		return clonada;
	}


}
