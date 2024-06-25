package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;

import org.hibernate.criterion.Order;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Conteudo;




@RequestScoped
public class ConteudoDao extends HibernateDao<Conteudo> {

	public TableResponse<Conteudo> paginate(Table datatable) {
		DatatableDao<Conteudo> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();


		return datatableDao.carregandoParametros(conjunction).paginate();
	}



}
