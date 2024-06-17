package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;


import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.OrdemBump;
import br.com.jvlabs.model.Produto;




@RequestScoped
public class OrdemBumpDao extends HibernateDao<OrdemBump> {

	public TableResponse<OrdemBump> paginate(Table datatable) {
		DatatableDao<OrdemBump> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();


		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public List<OrdemBump> buscarBumpsDoProduto(Produto produto) {
		HibernateCriteriaDao<OrdemBump> criteria = createCriteria();
		criteria.add(Restrictions.eq("produtoReferencia", produto));
		return criteria.list();
	}



}
