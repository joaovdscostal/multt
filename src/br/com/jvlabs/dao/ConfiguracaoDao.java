package br.com.jvlabs.dao;

import javax.enterprise.context.RequestScoped;
import org.hibernate.criterion.Order;

import br.com.jvlabs.model.Configuracao;

@RequestScoped
public class ConfiguracaoDao extends HibernateDao<Configuracao> {

	public Configuracao buscaUmRegistro() {
		return createCriteria().ordenandoPor(Order.desc("id")).maximo(1).uniqueResultSemErro();
	}

}
