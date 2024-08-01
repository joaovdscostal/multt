package br.com.jvlabs.interceptor;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jvlabs.enumerated.Estado;
import br.com.jvlabs.enumerated.StatusMatricula;
import br.com.jvlabs.enumerated.TipoConta;
import br.com.jvlabs.enumerated.TipoDeProduto;
import br.com.jvlabs.enumerated.TipoFormaPagamento;
import br.com.jvlabs.enumerated.TipoLiberacao;
import br.com.jvlabs.enumerated.TipoPagamentoProduto;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.service.ConfiguracaoService;
import br.com.jvlabs.util.DateUtils;
import br.com.jvlabs.util.Sessao;


@RequestScoped
@Intercepts(after = HibernateInterceptor.class, before = SessaoInterceptor.class)
public class PutDataSessionInterceptor implements Interceptor {

	private static final String HTTPS = "https://";
	private static final String HTTP = "http://";

	@Inject private Sessao sessao;
	@Inject private Result result;
	@Inject private Environment environment;
	@Inject private HttpServletRequest request;
	@Inject private ConfiguracaoService configuracaoService;
	@Inject protected Validator validator;

	@Override
	public boolean accepts(ControllerMethod method) {
		return true;
	}

	@Override
	public void intercept(InterceptorStack interceptor, ControllerMethod method, Object controller) {

		sessao.setConfiguracao(configuracaoService.getConfiguracao());

		result.include("versao", environment.get("versao"));
		result.include("ambiente", environment.getName());
		result.include("configuracao", sessao.getConfiguracao());

		result.include("urlBanner", environment.get("urlPadrao") + "imagens/banner/" );
		result.include("urlPrevisualizacao", environment.get("urlPadrao") + "imagens/previsualizacao/");
		result.include("urlArquivo", environment.get("urlPadrao") + "arquivos/");

		result.include("tipoContaTipoContaList", TipoConta.values());
		result.include("tipoTipoDeProdutoList", TipoDeProduto.values());
		result.include("tipoPagamentoTipoPagamentoProdutoList", TipoPagamentoProduto.values());
		result.include("portadorTipoFormaPagamentoList", TipoFormaPagamento.values());
		result.include("estadoList", Estado.values());
		
		result.include("statusMatriculaStatusMatriculaList", StatusMatricula.values());
		result.include("tipoPagamentoProdutoTipoPagamentoProdutoList", TipoPagamentoProduto.values());
		result.include("statusMatriculaStatusMatriculaList", StatusMatricula.values());
		result.include("tipoLiberacaoTipoLiberacaoList", TipoLiberacao.values());

		if(sessao.isAdministrador()) {
			result.include("tipoUsuarioList", TipoUsuario.values());
		}

		result.include("anoAtual", DateUtils.recuperaAnoAtual());
		result.include("hoje", new Date());
		result.include("mesAtualInteger", DateUtils.recuperaMesAtualInt());
		result.include("controlador", getControllerAssetName(method));

		String urlRequest = request.getRequestURL().toString();
		String urlPadrao = getUrlStandard(urlRequest);

		sessao.setTitulo(environment.get("nome"));
		sessao.setUrlPadrao(urlPadrao);

		interceptor.next(method, controller);
	}

	private String getControllerAssetName(ControllerMethod method) {
		String simpleName = method.getController().getType().getSimpleName().replaceAll("Controller", "");
		simpleName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
		return simpleName;
	}

	private String getUrlStandard(String urlRequest) {
		boolean temHttps = urlRequest.startsWith("https");

		urlRequest = urlRequest.replaceAll(HTTP, "").replaceAll(HTTPS, "");
		urlRequest = urlRequest.substring(0, urlRequest.indexOf('/'));

		boolean ehAmbienteDeTeste = environment.getName().equals("desenvolvimento") || environment.getName()
				.equals("homologacao");

		if (ehAmbienteDeTeste) {
			String portRequest = "8080";

			if (urlRequest.indexOf(':') >= 0) {
				String[] url = urlRequest.split(":");
				urlRequest = url[0];
				portRequest = url[1];
			}

			String contextPath = request.getContextPath();

			return (temHttps ? HTTPS : HTTP) + urlRequest + ":" + portRequest + contextPath+"/";
		} else if (temHttps) {
			return environment.get("urlPadrao").replaceAll(HTTP, HTTPS);
		}

		return environment.get("urlPadrao");
	}

}