package br.com.jvlabs.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.NoResultException;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.model.Entidade;
import br.com.jvlabs.util.HibernateUtil;
import br.com.jvlabs.util.Sessao;

@SuppressWarnings("unchecked")
public abstract class HibernateDao<T extends Entidade> {


	public static final String MENSAGEM_NENHUM_RESULTADO_ENCONTRADO = "Nenhum resultado encontrado";
	private Sessao sessao;
	protected Session hibernateSession;
	private Class<T> persistentClass;

	public HibernateDao() {
		super();
		this.sessao = CDI.current().select(Sessao.class).get();
		this.hibernateSession = HibernateUtil.currentSession();
		this.persistentClass = getGenericParameterizedTypeArgument();
	}

	protected HibernateCriteriaDao<T> createCriteria() {
		return new HibernateCriteriaDao<>(hibernateSession, persistentClass);
	}

	public Session getHibernateSession() {
		return hibernateSession;
	}

	// savings
	public void save(T t) {
		t.setManipulador(sessao.getUsuario());
		hibernateSession.save(t);
	}

	public void update(T t) {
		t.setManipulador(sessao.getUsuario());
		this.hibernateSession.update(t);
	}

	public T merge(T t) {
		t.setManipulador(sessao.getUsuario());
		return (T) this.hibernateSession.merge(t);
	}

	public SQLQuery criarQuery(String string) {
		return this.hibernateSession.createSQLQuery(string);
	}

	public void saveOrUpdate(T t) {
		t.setManipulador(sessao.getUsuario());
		hibernateSession.saveOrUpdate(t);
	}

	public void delete(T t) {
		t.setManipulador(sessao.getUsuario());
		hibernateSession.delete(t);
	}
	// end savings

	// gettings
	/** @throws NoResultException May throw this exception if they not found at least one result */
	public T get(Serializable id) {
		T t = getWithoutExeption(id);

		if (t == null)
			throw new NoResultException(MENSAGEM_NENHUM_RESULTADO_ENCONTRADO);

		return t;
	}

	public T getWithoutExeption(Serializable id) {
		return (T) hibernateSession.get(persistentClass, id);
	}

	public T getSemExcecao(Serializable id) {
		return getWithoutExeption(id);
	}

	public List<T> findAll() {
		return createCriteria().distinct().list();
	}
	public List<T> findAll(Order order) {
		return createCriteria().distinct().ordenandoPor(order).list();
	}

	public List<T> findAllActive() {
		return createCriteria().distinct().ativo().list();
	}
	public List<T> findAllActive(Order order) {
		return createCriteria().distinct().ordenandoPor(order).ativo().list();
	}

	// others
	private Class<T> getGenericParameterizedTypeArgument() {
		Type genericSuperclass = this.getClass().getGenericSuperclass();

		ParameterizedType parameterizedType;

		if (genericSuperclass instanceof ParameterizedType) {
			parameterizedType = (ParameterizedType) genericSuperclass;
		} else {
			parameterizedType = (ParameterizedType) ((Class<T>) genericSuperclass).getGenericSuperclass();
		}

		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	//
	public DatatableDao<T> comDatatable(Table datatable) {
		return new DatatableDao<>(this, datatable);
	}


}
