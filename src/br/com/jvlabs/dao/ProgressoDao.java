package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;


import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Progresso;




@RequestScoped
public class ProgressoDao extends HibernateDao<Progresso> {

	public TableResponse<Progresso> paginate(Table datatable) {
		DatatableDao<Progresso> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();


		return datatableDao.carregandoParametros(conjunction).paginate();
	}



}
