package br.com.jvlabs.dao;

import java.util.List;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.Entidade;

public class DatatableDao<T extends Entidade> {

	private HibernateCriteriaDao<T> dao;
	private Table datatable;

	public DatatableDao(HibernateDao<T> dao, Table datatable){
		this.dao = dao.createCriteria();
		this.datatable = datatable;
	}

	public DatatableDao<T> carregandoParametros() {
		return carregandoParametros(Restrictions.conjunction());
	}
	public DatatableDao<T> carregandoParametros(Conjunction conjunction) {
		datatable.getFiltersMap().forEach((parametro, valor) -> {
			if(valor != null && "order".equals(parametro) && "orderDir".equals(parametro)) {
				if(parametro instanceof String)
					conjunction.add(Restrictions.ilike(parametro, valor, MatchMode.ANYWHERE));
				else
					conjunction.add(Restrictions.eq(parametro, valor));
			}
		});

		dao.ordenandoPor(datatable.getOrder())
		.add(conjunction);
		return this;
	}

	public DatatableDao<T> carregandoConjunction(Conjunction conjunction) {
		dao.ordenandoPor(datatable.getOrder())
		.add(conjunction);
		return this;
	}
	public DatatableDao<T> comAlias(String alias) {
		dao.alias(alias);
		return this;
	}

	public TableResponse<T> paginate() {
		List<T> resultList = dao
		.primeiro(datatable.getStart().intValue())
		.maximo(datatable.getLength().intValue())
		.distinct()
		.list();

		return new TableResponse<>(resultList, dao.getRowCount(), dao.getRowCountSemCriterion());
	}

	public Table getDatatable() {
		return datatable;
	}

	public void setDatatable(Table datatable) {
		this.datatable = datatable;
	}

	public DatatableDao<T> alias(String alias) {
		this.dao.alias(alias);
		return this;
	}
	public DatatableDao<T> alias(String alias, String path) {
		this.dao.alias(alias, path);
		return this;
	}

	public DatatableDao<T> ordernandoPor(Order desc) {
		this.dao.ordenandoPor(desc);
		this.datatable.setOrder(desc);
		return this;
	}

	public DatatableDao<T> alterandoOrderPor(Order order) {
		this.dao.alterandoOrderPor(order);
		this.datatable.setOrder(order);
		return this;
	}

}
