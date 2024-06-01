package br.com.jvlabs.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

@SuppressWarnings("unchecked")
public class HibernateCriteriaDao<T> {

	private Criteria criteria;
	private Criteria criteriaCount;
	private List<Order> orders;
	private Collection<String> aliases;
	private Criterion criterion;

	public HibernateCriteriaDao(Session hibernateSession, Class<T> persistentClass) {
		this.criteria = hibernateSession.createCriteria(persistentClass);
		this.criteriaCount = hibernateSession.createCriteria(persistentClass);
		this.orders = new ArrayList<>();
		this.aliases = new ArrayList<String>();
	}

	public Criteria getCriteria() {
		return criteria;
	}

	private void addOrdersToCriteria(){
		if(orders != null) {
			for(Order order : orders){
				criteria.addOrder(order);
			}
		}
	}

	public HibernateCriteriaDao<T> add(Criterion criterion) {
		if(this.criterion != null) {
			Conjunction conjunction = Restrictions.conjunction();
			conjunction.add(criterion);
			conjunction.add(this.criterion);

			this.criterion = conjunction;
		}else {
			this.criterion = criterion;
		}
		this.criteria.add(criterion);
		return this;
	}

	public HibernateCriteriaDao<T> distinct() {
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return this;
	}

	public HibernateCriteriaDao<T> primeiro(int minimo){
		criteria.setFirstResult(minimo);
		return this;
	}

	public HibernateCriteriaDao<T> maximo(int maximo){
		criteria.setMaxResults(maximo);
		return this;
	}
	public HibernateCriteriaDao<T> ordenandoPor(Order order){
		if(this.orders == null)
			this.orders = new ArrayList<>();

		this.orders.add(order);
		return this;
	}

	public HibernateCriteriaDao<T> alterandoOrderPor(Order order){
		this.orders = new ArrayList<>();
		this.orders.add(order);
		return this;
	}

	public List<T> list() {
		addOrdersToCriteria();
		return criteria.list();
	}
	public List<Object> listObject() {
		addOrdersToCriteria();
		return criteria.list();
	}

	public T uniqueResult() {
		addOrdersToCriteria();
		return (T) criteria.uniqueResult();
	}
	public T uniqueResultSemErro() {
		addOrdersToCriteria();
		List<T> list = criteria.list();

		if(list.isEmpty())
			return null;

		return list.get(0);
	}

	public Boolean exists() {
		return getRowCount() > 0;
	}

	public HibernateCriteriaDao<T> alias(String alias) {
		return alias(alias, alias);
	}
	public HibernateCriteriaDao<T> alias(String alias, JoinType type) {
		return alias(alias, alias, type);
	}

	public HibernateCriteriaDao<T> alias(String alias, String path, JoinType type) {
		if(aliases == null) {
			aliases = new HashSet<>();
		}
		aliases.add(alias);
		this.criteria.createAlias(path, alias, type);
		this.criteriaCount.createAlias(path, alias, type);
		return this;
	}

	public HibernateCriteriaDao<T> alias(String alias, String path) {
		if(aliases == null) {
			aliases = new HashSet<>();
		}
		aliases.add(alias);
		this.criteria.createAlias(path, alias);
		this.criteriaCount.createAlias(path, alias);
		return this;
	}

	public HibernateCriteriaDao<T> setProjection(Projection projection) {
		criteria.setProjection(projection);
		return this;
	}

	public HibernateCriteriaDao<T> ativo() {
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("ativo", true));
		this.criteria.add(conjunction);
		return this;
	}

	public Integer returnInteger() {
		addOrdersToCriteria();
		List<Integer> object = criteria.list();
		return object.get(0);
	}


	public Long getRowCount(Criterion criterion) {
		return getRowCount(criterion, null);
	}
	public Long getRowCount() {
		return getRowCount(criterion, null);
	}
	public Long getRowCountSemCriterion() {
		return getRowCount(null, null);
	}
	public Long getRowCount(Criterion criterion, String distinctCount) {
		if(criterion != null)
			criteriaCount.add(criterion);

		criteriaCount.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		if(distinctCount != null) {
			criteriaCount.setProjection(Projections.countDistinct(distinctCount));
		}else {
			criteriaCount.setProjection(Projections.rowCount());
		}

		Long rownCount = 0l;

		List<Long> list = criteriaCount.list();

		for(Long count : list)
			rownCount += count == null ? 0l : count;

		return rownCount;
	}


}
