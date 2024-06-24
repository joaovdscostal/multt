package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Imagem;
import br.com.jvlabs.model.Produto;




@RequestScoped
public class ImagemDao extends HibernateDao<Imagem> {

	public TableResponse<Imagem> paginate(Table datatable) {
		DatatableDao<Imagem> datatableDao = comDatatable(datatable);
		Conjunction conjunction = Restrictions.conjunction();


		return datatableDao.carregandoParametros(conjunction).paginate();
	}

	public List<Imagem> buscarTodasDoProduto(Produto produto) {
		HibernateCriteriaDao<Imagem> criteria = createCriteria();
		criteria.add(Restrictions.eq("produto", produto));
		return criteria.list();
	}



}
