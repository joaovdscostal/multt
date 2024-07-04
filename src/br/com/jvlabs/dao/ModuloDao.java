package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Modulo;
import br.com.jvlabs.model.Produto;




@RequestScoped
public class ModuloDao extends HibernateDao<Modulo> {

	public TableResponse<Modulo> paginate(Table datatable) {
		DatatableDao<Modulo> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();

			if (datatable.hasFilter("nome")) {
					conjunction.add(Restrictions.ilike("nome", datatable.get("nome"), MatchMode.ANYWHERE));
			}

		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public List<Modulo> buscarModulosDoProduto(Produto produto) {
		HibernateCriteriaDao<Modulo> criteria = createCriteria();
		criteria.add(Restrictions.eq("produto", produto));
		return criteria.ordenandoPor(Order.desc("ordem")).list();
	}

	public Integer pegarUltimoNumeroGeradoParardem() {
		HibernateCriteriaDao<Modulo> criteria = createCriteria();
	    Integer maxOrdem = criteria.inteiro(Projections.max("ordem"));
		return maxOrdem;
	}

}
