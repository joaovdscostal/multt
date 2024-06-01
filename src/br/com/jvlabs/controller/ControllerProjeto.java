package br.com.jvlabs.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.jvlabs.util.CampoRelatorio;
import br.com.jvlabs.util.FiltroRelatorio;
import br.com.jvlabs.util.Sessao;

public abstract class ControllerProjeto {


	@Inject protected Result result;
	@Inject protected Validator validator;
	@Inject protected Sessao sessao;
	@Inject protected HttpServletRequest request;
	@Inject protected HttpServletResponse response;
	@Inject protected Environment environment;



	protected ResourceBundle bundle = ResourceBundle.getBundle("messages");
	protected Logger logger = Logger.getLogger(ControllerProjeto.class);

	protected void addMessage(String message) {
		result.include("message", convertI18N(message));
	}
	protected void addAlert(String alert) {
		result.include("alert", convertI18N(alert));
	}
	protected void addError(String error) {
		result.include("error", convertI18N(error));
	}

	protected Validator addValidation(String message) {
		return validator.add(new SimpleMessage("error", convertI18N(message)));
	}
	protected Validator addValidation(Exception e) {
		String message = "";
		if(e instanceof NullPointerException || e == null) {
			if(e != null) e.printStackTrace();

			message = "Erro de falta de informações! Entre em contato com os administradores do sistema!";
		}else {
			message = e.getMessage();
		}

		return validator.add(new SimpleMessage("error", convertI18N(message)));
	}

	protected void addRetornoSucessoAjax() {
		result.use(Results.http()).setStatusCode(200);
	}


	protected void addLogAndSendToErrorPage(Exception e, String local) {
		logger.error(local, e);
		e.printStackTrace();
		addValidation("Desculpe, estamos trabalhando para melhorar nossos serviços! Os administradores do sistema ja foram informados sobre esse caso. Qualquer coisa, entrem em contato com nosso suporte!");
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(byteOutput);
		e.printStackTrace(stream);
		result.include("stack", byteOutput.toString());
		result.include("messageErro", e.getMessage());
		result.include("localizedMessageErro", e.getLocalizedMessage());
		validator.onErrorRedirectTo(IndexController.class).admin();
	}

	private String convertI18N(String string) {
		if (string != null && bundle.containsKey(string)){
			string = bundle.getString(string);
		}
		return string;
	}

	protected void addObjetoAjaxRecursive(Object object, String... names) {
		result.use(Results.json()).from(object,"retorno").include(names).serialize();
	}

	protected void addPlainAjax(String retorno) {
		result.use(Results.http()).body(retorno).addHeader("Content-Type", "application/json").setStatusCode(200);
	}

	protected void addObjetoAjax(Object object) {
		result.use(Results.json()).from(object,"retorno").serialize();
	}
	protected void addObjetoAjaxRecursive(Object object) {
		result.use(Results.json()).from(object,"retorno").recursive().serialize();
	}
	protected void addObjetoAjax(Object... objects) {
		result.use(Results.json()).from(objects,"retorno").serialize();
	}

	protected void addObjetoCustomAjax(Object object) {
		result.use(Results.http()).setStatusCode(200);
		result.use(Results.json()).withoutRoot().from(object).serialize();
	}
	protected void addObjetoAjaxUsuario(Object object) {
		result.use(Results.json()).from(object,"retorno").include("usuario").serialize();
	}
	protected void addObjetosAjax(Object... object) {
		result.use(Results.json()).from(object,"retorno").serialize();
	}

	protected void addErroAjax(String string) {
		result.use(Results.http()).setStatusCode(400);
		result.use(Results.json()).withoutRoot().from(string).serialize();
	}

	protected void addErroAjax(Exception e) {
		if(e instanceof java.lang.NullPointerException || e instanceof UnsupportedOperationException) {
			logger.error("Deu um null pointer ou unsupported", e);
			addErroAjax("Consulte o administrador! Nao foi possivel exibir o erro");
		}else {
			addErroAjax(e.getMessage());
		}

	}

	public String getParameter(String parameter) {
		return request.getParameter(parameter);
	}

	protected boolean temFlagNovo() {
		return getParameter("flag") != null && getParameter("flag").equals("novo");
	}

	protected void processarFielsDo(FiltroRelatorio filtro) {
		if(filtro != null && filtro.getCampos() != null && !filtro.getCampos().isEmpty()) {
			for(CampoRelatorio campoRelatorio : filtro.getCampos()) {
				campoRelatorio.setValor(request.getParameter(campoRelatorio.getNome()));
			}
		}

		result.include("filtro", filtro);
	}

	private static final int TAMANHOPADRAO = 25;

	public static String repeteCaracteres(String string) {
		String texto = "";
		for(int i = 0 ; i < TAMANHOPADRAO ; i ++){
			texto += string;
		}
		return texto;
	}

	public static String centraliza(String string) {
		String texto = "";
		int size = string.length();
		int caracteresFaltantes = TAMANHOPADRAO - size;
		int divisao = caracteresFaltantes / 2;
		for(int i = 0 ; i < divisao ; i ++){
			texto += " ";
		}
		texto += string;
		for(int i = 0 ; i < divisao ; i ++){
			texto += " ";
		}
		return texto;
	}
	public static String wrapString(String s) {
		s = s.toUpperCase();
		return WordUtils.wrap(s, TAMANHOPADRAO, "<br/>", true);
	}

	public static String completa(String texto, String caracter){
		if(texto.length() < 12){
			int diferenca = 12 - texto.length() ;
			for(int i = 0 ; i < diferenca ; i++){
				texto += " ";
			}
			return texto + caracter;
		}else{
			return texto + caracter;
		}
	}

	public String getSenhaPadrao() {
		return environment.get("senhaPadrao");
	}

	protected String highlightSearchTerm(String text, String searchTerm) {
		 // Convertendo o texto e o termo de pesquisa para minúsculas
		String textLower = text.toLowerCase();
      searchTerm = searchTerm.toLowerCase();

      // Encontra a posição da primeira ocorrência do termo de pesquisa
      int index = textLower.indexOf(searchTerm);

      // Se o termo de pesquisa for encontrado, formata o texto com negrito
      if (index != -1) {
          String beforeHighlight = text.substring(0, index);
          String highlighted = text.substring(index, index + searchTerm.length());
          String afterHighlight = text.substring(index + searchTerm.length());

          // Formata o texto usando HTML para aplicar o negrito
          String formattedText = beforeHighlight + "<strong>" + highlighted + "</strong>" + afterHighlight;
          return formattedText;
      }

      // Se o termo de pesquisa não for encontrado, retorna o texto original
      return text;
  }
}
