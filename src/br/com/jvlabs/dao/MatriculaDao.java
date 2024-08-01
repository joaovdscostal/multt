package br.com.jvlabs.dao;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Matricula;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.model.Turma;




@RequestScoped
public class MatriculaDao extends HibernateDao<Matricula> {

	public TableResponse<Matricula> paginate(Table datatable) {
		DatatableDao<Matricula> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();

		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public TableResponse<Matricula> paginatePorProduto(Table datatable, Produto produto) {
		DatatableDao<Matricula> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();
		datatableDao.alias("turma");
		conjunction.add(Restrictions.eq("turma.produto", produto));
		
		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public Long buscarQuantidadeAlunosDoProduto(Produto produto) {
		HibernateCriteriaDao<Matricula> criteria = createCriteria();
		criteria.alias("turma");
		criteria.add(Restrictions.eq("turma.produto", produto));
		return criteria.getRowCount();
	}

	public Boolean possuiMatriculaComTurma(Turma turma) {
		HibernateCriteriaDao<Matricula> criteria = createCriteria();
		criteria.add(Restrictions.eq("turma", turma));
		return criteria.exists();
	}

}
