package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;


import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Conta;




@RequestScoped
public class ContaDao extends HibernateDao<Conta> {

	public TableResponse<Conta> paginate(Table datatable) {
		DatatableDao<Conta> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();

			if (datatable.hasFilter("nome")) {
					conjunction.add(Restrictions.ilike("nome", datatable.get("nome"), MatchMode.ANYWHERE));
			}

		return datatableDao.carregandoParametros(conjunction).paginate();
	}



}
