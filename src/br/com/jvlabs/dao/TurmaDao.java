package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;


import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.model.Turma;

@RequestScoped
public class TurmaDao extends HibernateDao<Turma> {

	public TableResponse<Turma> paginate(Table datatable) {
		DatatableDao<Turma> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();
		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public List<Turma> buscarTurmasDoProduto(Produto produto) {
		HibernateCriteriaDao<Turma> criteria = createCriteria();
		criteria.add(Restrictions.eq("produto", produto));
		return criteria.list();
	}

	public Boolean existeTurmaComProduto(Produto produto) {
		HibernateCriteriaDao<Turma> criteria = createCriteria();
		criteria.add(Restrictions.eq("produto", produto));
		return criteria.exists();
	}

}
