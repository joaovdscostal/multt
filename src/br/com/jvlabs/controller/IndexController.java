package br.com.jvlabs.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.annotation.Public;
import br.com.jvlabs.model.TipoUsuario;

@Controller
public class IndexController extends ControllerProjeto {

	@Privado({TipoUsuario.ADMINISTRADOR})
	@Path(value = {"/adm"}, priority=Path.HIGH)
	public void admin() {
	}

	@Get @Public
	@Path(value = {"/"}, priority=Path.HIGH)
	public void index() {
	}

}
