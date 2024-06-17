package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Produto;




@RequestScoped
public class ProdutoDao extends HibernateDao<Produto> {

	public TableResponse<Produto> paginate(Table datatable) {
		DatatableDao<Produto> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();

			if (datatable.hasFilter("nome")) {
					conjunction.add(Restrictions.ilike("nome", datatable.get("nome"), MatchMode.ANYWHERE));
			}

		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public List<Produto> buscarProdutosDaConta(Conta conta) {
		HibernateCriteriaDao<Produto> criteria = createCriteria();
		criteria.add(Restrictions.eq("conta", conta));
		return criteria.list();
	}



}
