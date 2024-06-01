package br.com.jvlabs.dinamico;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.controller.ControllerProjeto;
import br.com.jvlabs.util.FormatterString;
import br.com.jvlabs.util.HibernateUtil;
import net.vidageek.mirror.dsl.Mirror;
import java.util.Arrays;

@Controller
public class DinamicoController extends ControllerProjeto {

	@Inject private DinamicoService dinamicoService;

	@Post("/dinamico") @Privado
	public void cadastrar(String objeto, String tipoReferencia, List<Dinamico> campos){
		Object objetoCriado;

		try {
			HibernateUtil.beginTransaction();
			objetoCriado = dinamicoService.cadastrar(objeto, tipoReferencia, campos, sessao.getUsuario());
			HibernateUtil.commit();
		} catch (Exception e) {
			HibernateUtil.rollback();
			logger.error("Erro ao cadastrar objeto dinamico!", e);
			addErroAjax("Erro ao cadastrar objeto dinamico!");
			return;
		}

		result.use(Results.json()).from(objetoCriado,"retorno").serialize();
	}

	@Get("/dinamico/pesquisar") @Privado
	public void pesquisar(String objeto, List<String> pesquisa, List<ParametroFixo> parametrosFixos, List<String> exibicao, String valor,
			Boolean filtrarAtivo, List<Dinamico> campos, String classeFuncaoExecutar, String parametrosFixosStr){

		List<ParametroFixo> parametros = new ArrayList<>();

		if(parametrosFixosStr != null && !parametrosFixosStr.trim().isEmpty()) {
			List<String> params = new FormatterString().toString(parametrosFixosStr).splitString("|").formatListString();

			for(String parametroStr : params) {
				List<String> param = new FormatterString().toString(parametroStr).splitString(",").formatListString();
				ParametroFixo parametro = new ParametroFixo();
				parametro.setNome(param.get(0));
				parametro.setValor(param.get(1));
				parametros.add(parametro);
			}
		}

		if(parametrosFixos != null) {
			parametros.addAll(parametrosFixos);
		}

		try{
			Long.parseLong(valor);
		}catch(Exception e){}

		String retorno;

		try {
			retorno = dinamicoService.pesquisar(objeto, pesquisa, exibicao, parametros,  sessao.getUsuario(), valor, filtrarAtivo, campos, classeFuncaoExecutar);
		} catch (Exception e) {
			logger.error("Erro ao pesquisar objeto dinamico!", e);
			addErroAjax("Erro ao pesquisar objeto dinamico!");
			return;
		}

		addObjetoAjax(retorno);
	}

	@Get("/dinamico/selecao") @Privado
	public void selecaoMultipla(String objeto, List<String> pesquisa, List<ParametroFixo> parametrosFixos, List<String> exibicao,
								String valor, Boolean filtrarAtivo, List<Dinamico> campos, String nome, List<Long> ids, String objetoInput){

		String retorno = null;
		try {
			retorno = dinamicoService.selecao(objeto, exibicao, ids, nome, objetoInput);
		} catch (Exception e) {
			logger.error("Erro ao pesquisar objeto dinamico!", e);
			addErroAjax("Erro ao pesquisar objeto dinamico!");
			return;
		}

		addObjetoAjax(retorno);
	}

	/*objeto : objeto,
					metodoExibicao : metodoExibicao,
					filtroAtivo : filtroAtivo,
					camposPesquisa : camposPesquisa

*/
	@Get("/dinamico/select-dinamico") @Privado
	public void selectDinamico(String objeto, String campoExibicao, Boolean filtroAtivo,
			String camposPesquisa, String parametrosFixos, String order, String idElemento, String atributoAppend){
		List<RetornoSelect> retorno;

		if(filtroAtivo == null) {
			filtroAtivo = false;
		}

		try {
			retorno = dinamicoService.selectDinamico(objeto, campoExibicao, filtroAtivo, camposPesquisa, parametrosFixos, order, atributoAppend);
		} catch (Exception e) {
			logger.error("Erro ao pesquisar objeto dinamico!", e);
			addErroAjax("Erro ao pesquisar objeto dinamico!");
			return;
		}

		addObjetoAjax(retorno, idElemento);
	}
	@Get("/dinamico/autocompletar") @Privado
	public void autocompletar(String objeto, String campoExibicao, String campoExibicaoFormatado, Boolean filtroAtivo,
			String camposPesquisa, String parametrosFixos, String order, String q){
		List<RetornoSelect> retorno;

		if(filtroAtivo == null) {
			filtroAtivo = false;
		}

		campoExibicaoFormatado = senearField(campoExibicaoFormatado);

		if(campoExibicaoFormatado == null) {
			campoExibicaoFormatado = campoExibicao;
		}

		parametrosFixos = senearField(parametrosFixos);
		order = senearField(order);
		camposPesquisa = senearField(camposPesquisa);

		if(parametrosFixos == null) {
			parametrosFixos = camposPesquisa + "," + q;
		}else {
			parametrosFixos += "|" +  camposPesquisa + "," + q;
		}

		try {
			retorno = dinamicoService.selectDinamico(objeto, campoExibicao, filtroAtivo, camposPesquisa, parametrosFixos, order, "");
		} catch (Exception e) {
			logger.error("Erro ao pesquisar objeto dinamico!", e);
			addErroAjax("Erro ao pesquisar objeto dinamico!");
			return;
		}

		List<RetornoNovo> select = converter(retorno, q);
		addObjetoCustomAjax(select);
	}


	private List<RetornoNovo> converter(List<RetornoSelect> retorno, String q) {
		List<RetornoNovo> retornoNovo = new ArrayList<>();
		for(RetornoSelect ret : retorno) {
			retornoNovo.add(RetornoNovo.builder().text(ret.getTextoExibicao()).value(ret.getCodigo()).html(highlightSearchTerm(ret.getTexto(), q)).build());
		}
		return retornoNovo;
	}

	private String senearField(String parametrosFixos) {
		if(parametrosFixos != null && parametrosFixos.equals("undefined"))
			return null;

		if(parametrosFixos == null)
			return parametrosFixos;

		return parametrosFixos.trim();
	}

	@Get("/dinamico/selecionar") @Privado
	public void selecionarComRecursivo(String objeto, Long id, List<String> recursivo){

		Object objetoCriado;

		try {
			objetoCriado = dinamicoService.recuperar(objeto, id);
		} catch (Exception e) {
			logger.error("Erro ao cadastrar objeto dinamico!", e);
			addErroAjax("Erro ao cadastrar objeto dinamico!");
			return;
		}

		Field field = new Mirror().on(objetoCriado.getClass()).reflect().field("usuario");


		if(recursivo == null) {
			recursivo = new ArrayList<String>();
		}


		if(field == null)
			addObjetoAjaxRecursive(objetoCriado, recursivo.toArray(new String[0]));
		else
			addObjetoAjaxUsuario(objetoCriado);

	}

	@Post("/dinamico/editInPlace") @Privado
	public void editInPlace(String objeto, Long id, String nome, String valor, String tipo){
		Object objetoCriado;

		Dinamico dinamico = new Dinamico(nome, valor, tipo);

		try {
			HibernateUtil.beginTransaction();
			objetoCriado = dinamicoService.editInPlace(objeto, id, Arrays.asList(dinamico), sessao.getUsuario());
			HibernateUtil.commit();
		} catch (Exception e) {
			HibernateUtil.rollback();
			logger.error("Erro ao cadastrar objeto dinamico!", e);
			addErroAjax("Erro ao cadastrar objeto dinamico!");
			return;
		}

		result.use(Results.json()).from(objetoCriado,"retorno").serialize();
	}


	@Get("/adm/dinamico/getObjeto") @Privado
	public void selecionar(String objeto, Long id, List<String> recursivo) {

		Object objetoCriado;

		try {
			objetoCriado = dinamicoService.recuperar(objeto, id);
		} catch (Exception e) {
			logger.error("Erro ao cadastrar objeto dinamico!", e);
			addErroAjax("Erro ao cadastrar objeto dinamico!");
			return;
		}

		if(recursivo == null) {
			recursivo = new ArrayList<String>();
		}

		addObjetoAjaxRecursive(objetoCriado, recursivo.toArray(new String[0]));

	}

}