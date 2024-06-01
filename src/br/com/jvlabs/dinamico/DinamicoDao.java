package br.com.jvlabs.dinamico;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.dao.HibernateDao;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Usuario;
import br.com.jvlabs.util.FormatterString;
import net.vidageek.mirror.dsl.Mirror;

@RequestScoped
public class DinamicoDao extends HibernateDao<Usuario> {

	public Object cadastrar(Object objetoCriado) {
		return this.hibernateSession.merge(objetoCriado);
	}

	public List<Object> procuraPor(Object objetoCriado,
			List<String> pesquisa, List<ParametroFixo> parametrosFixos, String valor, Boolean filtrarAtivo, List<Dinamico> camposDinamico,
			String order) throws InstantiationException, IllegalAccessException {

		Criteria criteria = hibernateSession.createCriteria(objetoCriado.getClass());
		List<String> parametrosAdicionados = new ArrayList<String>();

		Disjunction disjunction = Restrictions.disjunction();

		List<String> aliasCriados = new ArrayList<String>();

		for (String campo : pesquisa) {

			List<String> campos = new FormatterString().toString(campo).trim().splitString(".").formatListString();

			String aliasBusca = "";

			if(campos.size() == 1){
				aliasBusca = campos.get(0);
			}

			if(campos.size() == 2){
				String alias = campos.get(0);

				if(!aliasCriados.contains(alias)){
					criteria.createAlias(alias, alias);
					aliasCriados.add(alias);
				}

				aliasBusca = campo;
			}

			if(campos.size() == 3){
				String alias = campos.get(0);

				if(!aliasCriados.contains(alias)){
					criteria.createAlias(alias, alias);
					aliasCriados.add(alias);
				}

				String alias2 = campos.get(0) + "." + campos.get(1);

				if(!aliasCriados.contains(alias2)){
					criteria.createAlias(alias2, campos.get(1));
					aliasCriados.add(alias);
				}

				aliasBusca = campos.get(1) + "." + campos.get(2);
			}

			if(valor != null) {
				if(!aliasBusca.equals("id"))
					disjunction.add(Restrictions.ilike(aliasBusca, valor, MatchMode.ANYWHERE));
				else
					disjunction.add(Restrictions.eq(aliasBusca, Long.parseLong(valor)));
			}
		}

		if(camposDinamico != null) {
			for(Dinamico dinamico : camposDinamico) {
				if(dinamico.temValor()) {
					parametrosAdicionados.add(dinamico.getNome());

					if(dinamico.isTexto()) {
						criteria.add(Restrictions.ilike(dinamico.getNome(), dinamico.getValor(), MatchMode.ANYWHERE));
					}else {
						criteria.add(Restrictions.eq(dinamico.getNome(), dinamico.getValorValidandoTipo()));
					}
				}
			}
		}

		if(filtrarAtivo) {
			Field field = new Mirror().on(objetoCriado.getClass()).reflect().field("usuario");

			if(field != null) {
				String alias = "usuario";
				if(!aliasCriados.contains(alias)){
					criteria.createAlias(alias, alias);
					aliasCriados.add(alias);
				}
				parametrosAdicionados.add("usuario.ativo");
				criteria.add(Restrictions.eq("usuario.ativo", true));
			}else {
				parametrosAdicionados.add("ativo");
				criteria.add(Restrictions.eq("ativo", true));
			}
		}

		if(parametrosFixos != null) {
			for(ParametroFixo parametro : parametrosFixos) {
				try {
					Long idParametro = Long.parseLong(parametro.getValor());
					Field field = new Mirror().on(objetoCriado.getClass()).reflect().field(parametro.getNome());
					Object object = new Mirror().on(field.getType()).invoke().constructor().withArgs(idParametro);
					parametrosAdicionados.add(parametro.getNome());
					criteria.add(Restrictions.eq(parametro.getNome(), object));
				}catch (Exception e) {

					if(parametro.getValor().contains("null")) {
						Disjunction disjunctionParaParametros = Restrictions.disjunction();
						List<String> valores = new FormatterString().toString(parametro.getValor()).splitString("_").formatListString();

						for(String valorParametro : valores) {
							if(valorParametro.equals("null")) {
								disjunctionParaParametros.add(Restrictions.isNull(parametro.getNome()));
								parametrosAdicionados.add(parametro.getNome());
							}else {
								try {
									Long idParametro = Long.parseLong(valorParametro);
									Field field = new Mirror().on(objetoCriado.getClass()).reflect().field(parametro.getNome());
									Object object = new Mirror().on(field.getType()).invoke().constructor().withArgs(idParametro);
									disjunctionParaParametros.add(Restrictions.eq(parametro.getNome(), object));
									parametrosAdicionados.add(parametro.getNome());
								}catch (Exception e3) {
									Boolean valorDoParametro = Boolean.parseBoolean(valorParametro);
									disjunctionParaParametros.add(Restrictions.eq(parametro.getNome(), valorDoParametro));
									parametrosAdicionados.add(parametro.getNome());
								}
							}
						}

						criteria.add(disjunctionParaParametros);
					}else {
						try {
							if(!parametro.getValor().equals("false") && !parametro.getValor().equals("true"))
								throw new BusinessException("nao e boolean");

							Boolean parametroBoolean = Boolean.parseBoolean(parametro.getValor());
							Field field = new Mirror().on(objetoCriado.getClass()).reflect().field(parametro.getNome());
							Object object = new Mirror().on(field.getType()).invoke().constructor().withArgs(parametroBoolean);
							criteria.add(Restrictions.eq(parametro.getNome(), object));
							parametrosAdicionados.add(parametro.getNome());
						}catch (Exception e1) {
							String parametroStr = parametro.getNome();
							Boolean temNot = parametro.getNome().contains("not");

							if(temNot) {
								parametroStr = parametroStr.replace("not", "");
							}

							Object object = null;

							if(parametroStr.contains(".")) {
								String[] parametros = parametroStr.split("\\.");
								Field field = new Mirror().on(objetoCriado.getClass()).reflect().field(parametros[0]);

								Field field2 = new Mirror().on(field.getType()).reflect().field(parametros[1]);
								object = new Mirror().on(field2.getType()).invoke().method("valueOf").withArgs(parametro.getValor());
							}else {
								Field field = new Mirror().on(objetoCriado.getClass()).reflect().field(parametroStr);
								object = new Mirror().on(field.getType()).invoke().method("valueOf").withArgs(parametro.getValor());
							}



							if(parametroStr.contains(".")) {
								String alias = parametroStr.substring(0, parametroStr.indexOf("."));


								if(!aliasCriados.contains(alias)) {
									criteria.createAlias(alias, alias);
									aliasCriados.add(alias);
								}
							}

							if(object instanceof String) {
								criteria.add(Restrictions.ilike(parametroStr, (String) object, MatchMode.ANYWHERE));
								parametrosAdicionados.add(parametroStr);
							}else {
								if(temNot) {
									parametrosAdicionados.add(parametroStr);
									criteria.add(Restrictions.ne(parametroStr, object));
								}else {
									parametrosAdicionados.add(parametroStr);
									criteria.add(Restrictions.eq(parametroStr, object));
								}

							}

						}
					}
				}
			}
		}

		if(valor != null) {
			try{
				parametrosAdicionados.add("id");
				disjunction.add(Restrictions.eq("id", Long.parseLong(valor)));
			}catch(Exception e){}
		}


		if(valor == null || valor.isEmpty() || valor.length() <= 2){
			criteria.setMaxResults(100);
		}

		if(order != null && !order.trim().isEmpty()){
			if(order.contains("DESC")) {
				criteria.addOrder(Order.desc(order.replace("DESC", "")));
			}else {
				criteria.addOrder(Order.asc(order));
			}

		}else {
			criteria.addOrder(Order.desc("id"));
		}

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(disjunction);
		return criteria.list();
	}

	public Object getMontado(Object objetoCriado, Long id) {
		return hibernateSession.get(objetoCriado.getClass(), id);
	}

}
