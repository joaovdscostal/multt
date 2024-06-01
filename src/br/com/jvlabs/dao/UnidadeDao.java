package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Unidade;

@RequestScoped
public class UnidadeDao extends HibernateDao<Unidade> {

	public TableResponse<Unidade> paginate(Table datatable) {
		Conjunction conjunction = Restrictions.conjunction();

		if (datatable.hasFilter("nome")) {
			conjunction.add(Restrictions.ilike("nome", datatable.get("nome"), MatchMode.ANYWHERE));
		}

		return comDatatable(datatable).carregandoParametros(conjunction).paginate();
	}

	public List<Unidade> retornarOrdenadoPorVigenciaDeContrato() {
		return createCriteria()
		.add(Restrictions.eq("ativo", true))
		.ordenandoPor(Order.desc("dataDeVigenciaDoContrato"))
		.list();
	}

}
