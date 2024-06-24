package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;


import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Oferta;
import br.com.jvlabs.model.Produto;




@RequestScoped
public class OfertaDao extends HibernateDao<Oferta> {

	public TableResponse<Oferta> paginate(Table datatable) {
		DatatableDao<Oferta> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();

			if (datatable.hasFilter("nome")) {
					conjunction.add(Restrictions.ilike("nome", datatable.get("nome"), MatchMode.ANYWHERE));
			}

		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public List<Oferta> buscarOfertasDaContaPorProduto(Produto produto) {
		HibernateCriteriaDao<Oferta> criteria = createCriteria();
		criteria.add(Restrictions.eq("produto", produto));
		return criteria.list();
	}
	
}
