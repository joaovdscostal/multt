package br.com.jvlabs.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.jvlabs.dao.LogDao;
import br.com.jvlabs.model.Entidade;
import br.com.jvlabs.model.Log;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.Sessao;

@ApplicationScoped
public class LogService {

	@Inject private LogDao logDao;
	@Inject private Sessao sessao;

	public void criarLog(String titulo, Entidade objeto) {
		String descricao = new GsonUtils().paraLog(objeto).toJson(objeto);
		Log log = new Log(sessao.getUsuario(), titulo, descricao, objeto);
		logDao.merge(log);
	}

}
