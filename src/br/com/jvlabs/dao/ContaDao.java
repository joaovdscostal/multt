package br.com.jvlabs.dao;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Usuario;




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

	public Conta buscarPorUsuario(Usuario usuario) {
		HibernateCriteriaDao<Conta> criteria = createCriteria();
		criteria.add(Restrictions.eq("usuario", usuario));
		return criteria.uniqueResult();
	}

	public Boolean existeContaPorEmail(String email) {
		HibernateCriteriaDao<Conta> criteria = createCriteria();
		criteria.alias("usuario");
		criteria.add(Restrictions.eq("usuario.email", email));
		return criteria.exists();
	}

	public Conta buscarContaParaMatricula(String email) {
		HibernateCriteriaDao<Conta> criteria = createCriteria();
		criteria.alias("usuario");
		criteria.add(Restrictions.eq("usuario.email", email));
		return criteria.uniqueResult();
	}



}
