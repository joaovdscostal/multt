package br.com.jvlabs.service;

import javax.inject.Inject;

import br.com.caelum.vraptor.validator.Validator;
import br.com.jvlabs.util.Sessao;

public abstract class ServiceProjeto {

	@Inject protected LogService logService;
	@Inject protected Validator validator;
	@Inject protected Sessao sessao;

}
