package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Checkout;
import br.com.jvlabs.model.Oferta;
import br.com.jvlabs.model.Produto;




@RequestScoped
public class CheckoutDao extends HibernateDao<Checkout> {

	public TableResponse<Checkout> paginate(Table datatable) {
		DatatableDao<Checkout> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();

		if (datatable.hasFilter("nome")) {
			conjunction.add(Restrictions.ilike("nome", datatable.get("nome"), MatchMode.ANYWHERE));
		}

		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public List<Checkout> buscarCheckoutsDoProduto(Produto produto) {
		HibernateCriteriaDao<Checkout> criteria = createCriteria();
		criteria.alias("oferta");
		criteria.add(Restrictions.eq("oferta.produto", produto));
		List<Checkout> checkoutList = criteria.distinct().list();
		return checkoutList;
	}
	
	public Boolean verificarDisponibilidadeDeOferta(Oferta oferta) {
		HibernateCriteriaDao<Checkout> criteria = createCriteria();
		criteria.alias("oferta");
		criteria.add(Restrictions.in("oferta.id", oferta.getId()));
		return criteria.exists();
	}

	public boolean existeCodigoIgual(String codigo) {
		HibernateCriteriaDao<Checkout> criteria = createCriteria();
		criteria.add(Restrictions.eq("codigo", codigo));
		return criteria.exists();
	}

	public Checkout pegarViaCodigo(String codigo) {
		HibernateCriteriaDao<Checkout> criteria = createCriteria();
		criteria.add(Restrictions.like("codigo", codigo));
		return criteria.uniqueResult();
	}



}
