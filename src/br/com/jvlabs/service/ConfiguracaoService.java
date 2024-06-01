package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.environment.Environment;
import br.com.jvlabs.dao.ConfiguracaoDao;
import br.com.jvlabs.model.Configuracao;

@RequestScoped
public class ConfiguracaoService extends ServiceProjeto{

	@Inject private ConfiguracaoDao configuracaoDao;
	@Inject private Environment environment;

	public Configuracao getConfiguracao() {
		Configuracao configuracao = configuracaoDao.buscaUmRegistro();

		if(configuracao == null){
			configuracao = new Configuracao();
			configuracao.setNome(environment.get("nome"));
			configuracao = configuracaoDao.merge(configuracao);
		}

		return configuracao;
	}

	public void atualiza(Configuracao configuracao) {
		configuracao = configuracaoDao.merge(configuracao);
		sessao.setConfiguracao(configuracao);
		logService.criarLog("CONFIGURACAO-UPDATE", configuracao);
	}
}
