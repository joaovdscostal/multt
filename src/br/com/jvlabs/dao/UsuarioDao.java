package br.com.jvlabs.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Usuario;

@RequestScoped
public class UsuarioDao extends HibernateDao<Usuario> {

	public Usuario buscarPorLoginESenha(String login, String password) {
		return createCriteria()
		.add(Restrictions.eq("login", login))
		.add(Restrictions.eq("senha", password))
		.add(Restrictions.eq("ativo", true))
		.uniqueResult();
	}

	public Usuario procuraPorLogin(String login) {
		return createCriteria()
		.add(Restrictions.eq("ativo", true))
		.add(Restrictions.eq("login", login))
		.uniqueResult();
	}

	public TableResponse<Usuario> paginate(Table datatable) {
		Conjunction conjunction = Restrictions.conjunction();
		Disjunction disjunction = Restrictions.disjunction();

		if(datatable.hasFilter("nome")) {
			disjunction.add(Restrictions.ilike("nome", datatable.get("nome"), MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("login", datatable.get("nome"), MatchMode.ANYWHERE));
		}
		if(datatable.hasFilter("tipo")) {
			disjunction.add(Restrictions.eq("tipo", datatable.getValueEnum("tipo", TipoUsuario.class)));
		}

		conjunction.add(disjunction);

		return comDatatable(datatable)
		.carregandoParametros(conjunction)
		.paginate();
	}

	public boolean existeUsuarioComLogin(String string) {
		return createCriteria()
				.add(Restrictions.eq("ativo", true))
				.add(Restrictions.eq("login", string))
				.exists();
	}

	public List<Usuario> retornarPor(TipoUsuario... tipos) {
		return createCriteria()
				.add(Restrictions.in("tipo", tipos))
				.add(Restrictions.eq("ativo", true))
				.ordenandoPor(Order.asc("nome"))
				.list();
	}

	public boolean existeUsuarioComCodigoSrp(Long codigoErp) {
		return createCriteria()
				.add(Restrictions.eq("codigoErp", codigoErp))
				.exists();
	}



}
