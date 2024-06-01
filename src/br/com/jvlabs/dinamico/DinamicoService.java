package br.com.jvlabs.dinamico;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import net.vidageek.mirror.dsl.Mirror;

import br.com.caelum.vraptor.freemarker.Freemarker;
import br.com.jvlabs.model.Entidade;
import br.com.jvlabs.model.Usuario;
import br.com.jvlabs.service.ServiceProjeto;
import br.com.jvlabs.util.DinheiroUtils;
import br.com.jvlabs.util.FormatterString;
import freemarker.template.TemplateException;

@RequestScoped
public class DinamicoService extends ServiceProjeto{

	@Inject private DinamicoDao dinamicoDao;
	@Inject private Freemarker freemarker;

	public String pesquisar(String objeto, List<String> pesquisa, List<String> exibicao, List<ParametroFixo> parametrosFixos, Usuario usuario,String valor, Boolean filtrarAtivo, List<Dinamico> campos, String classeFuncaoExecutar) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException, TemplateException {

		Object objetoCriado = Class.forName("br.com.jvlabs.model." + objeto).newInstance();

		List<Object> resposta = dinamicoDao.procuraPor(objetoCriado, pesquisa, parametrosFixos, valor, filtrarAtivo, campos, null);

		List<ObjUtil<Long, List<String>>> montarFree = new ArrayList<>();

		for (Object object : resposta) {
			String attr = "";

			List<String> linha = new ArrayList<String>();
			for(String exibir : exibicao){
				Method method = object.getClass().getMethod(exibir);
				Object retorno = method.invoke(object);

				String valorDaLinha = "";

				if(retorno == null)
					valorDaLinha = "";
				else if(retorno instanceof BigDecimal)
					valorDaLinha = DinheiroUtils.formataBigDecimal((BigDecimal) retorno, 2);
				else
					valorDaLinha = retorno.toString();

				attr +=" data-" + exibir + "=\"" + valorDaLinha + "\" ";

				linha.add(valorDaLinha);
			}

			Method method = object.getClass().getMethod("getId");
			Object retorno = method.invoke(object);
			Long id = Long.parseLong(retorno.toString());

			ObjUtil<Long, List<String>> objUtil = new ObjUtil<>(id, linha, attr);
			montarFree.add(objUtil);
		}

		List<String> exibicaoUpper = new ArrayList<String>();

		for(String string : exibicao) {
			String chaveParaExibicao = formatarString(string);
			exibicaoUpper.add(chaveParaExibicao);
		}


		String retorno = freemarker.use("modeloLista").with("dados", montarFree).with("exibicao", exibicaoUpper).with("classeFuncaoExecutar", classeFuncaoExecutar).getContent();
		return retorno;
	}

	private String formatarString(String string) {
		string = string.replaceFirst("get", "");
		String[] textoQuebrado = string.split("(?=\\p{Upper})");
		return Arrays.asList(textoQuebrado).stream().collect(Collectors.joining(" "));
	}

	public Object recuperar(String objeto, Long id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Object objetoCriado = Class.forName("br.com.jvlabs.model." + objeto).newInstance();
		return dinamicoDao.getMontado(objetoCriado, id);
	}



	public String selecao(String objeto, List<String> exibicao, List<Long> ids, String nome, String objetoInput) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException, TemplateException {

		List<Object> resposta = new ArrayList<>();

		if(ids != null) {
			for(Long idObjeto : ids) {
				if(idObjeto != null){
					resposta.add(recuperar(objeto, idObjeto));
				}
			}
		}

		List<ObjUtil<Long, List<String>>> montarFree = new ArrayList<>();

		for (Object object : resposta) {
			List<String> linha = new ArrayList<String>();
			for(String exibir : exibicao){
				Method method = object.getClass().getMethod(exibir);
				Object retorno = method.invoke(object);

				if(retorno == null)
					linha.add("");
				else if(retorno instanceof BigDecimal)
					linha.add(DinheiroUtils.formataBigDecimal((BigDecimal) retorno, 2));
				else
					linha.add(retorno.toString());
			}

			Method method = object.getClass().getMethod("getId");
			Object retorno = method.invoke(object);
			Long id = Long.parseLong(retorno.toString());

			ObjUtil<Long, List<String>> objUtil = new ObjUtil<>(id, linha);
			montarFree.add(objUtil);
		}

		List<String> exibicaoUpper = new ArrayList<String>();

		for(String string : exibicao)
			exibicaoUpper.add(string.toUpperCase().replaceAll("GET","").replaceAll("STR",""));


		//String retorno = freemarker.use("modeloLista").with("dados", montarFree).with("exibicao", exibicaoUpper).with("classeFuncaoExecutar", classeFuncaoExecutar).getContent();
		String retorno = freemarker.use("modeloSelecaoMultipla").with("nome", nome).with("objetoInput", objetoInput).with("dados", montarFree).getContent();
		return retorno;
	}



	public Object cadastrar(String objeto, String tipoReferencia, List<Dinamico> campos, Usuario usuario) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		Object objetoCriado = Class.forName("br.com.jvlabs.model." + objeto).newInstance();

		for (Dinamico dinamico : campos) {
			Method method = objetoCriado.getClass().getMethod(dinamico.getNomeSet(), dinamico.getValorValidandoTipo().getClass());
			method.invoke(objetoCriado, dinamico.getValorValidandoTipo());
		}

		Object retorno = dinamicoDao.cadastrar(objetoCriado);
		logService.criarLog("Cadastrando objeto dinamicos", (Entidade) objetoCriado);
		return retorno;
	}

	public Object editInPlace(String objeto, Long id, List<Dinamico> campos, Usuario usuario) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		Object objetoCriado = Class.forName("br.com.jvlabs.model." + objeto).newInstance();

		Object paraEditar = dinamicoDao.getMontado(objetoCriado, id);

		for (Dinamico dinamico : campos) {
			Method method = objetoCriado.getClass().getMethod(dinamico.getNomeSet(), dinamico.getValorValidandoTipo().getClass());
			method.invoke(paraEditar, dinamico.getValorValidandoTipo());
		}

		Object retorno = dinamicoDao.cadastrar(paraEditar);
		logService.criarLog("Editando objeto dinamicos", (Entidade) objetoCriado);
		return retorno;
	}

	public List<RetornoSelect> selectDinamico(String objeto, String campoExibicao, Boolean filtrarAtivo,
			String camposPesquisa, String parametrosFixos, String order, String atributoAppend) throws Exception {

		Object objetoCriado = Class.forName("br.com.jvlabs.model." + objeto).newInstance();

		List<String> pesquisa = new FormatterString().toString(camposPesquisa).splitString(",").formatListString();
		List<String> exibicao = new FormatterString().toString(campoExibicao).splitString(",").formatListString();

		List<ParametroFixo> parametros = new ArrayList<>();

		if(parametrosFixos != null && !parametrosFixos.trim().isEmpty()) {
			List<String> params = new FormatterString().toString(parametrosFixos).splitString("|").formatListString();

			for(String parametroStr : params) {
				List<String> param = new FormatterString().toString(parametroStr).splitString(",").formatListString();
				ParametroFixo parametro = new ParametroFixo();
				parametro.setNome(param.get(0));
				parametro.setValor(param.get(1));
				parametros.add(parametro);
			}
		}

		List<Object> resposta = dinamicoDao.procuraPor(objetoCriado, pesquisa, parametros, null, filtrarAtivo, null, order);

		List<ObjUtil<Long, List<String>>> montarFree = new ArrayList<>();

		for (Object object : resposta) {
			List<String> linha = new ArrayList<String>();
			for(String exibir : exibicao){
				Method method = object.getClass().getMethod(exibir);
				Object retorno = method.invoke(object);

				if(retorno == null)
					linha.add("");
				else if(retorno instanceof BigDecimal)
					linha.add(DinheiroUtils.formataBigDecimal((BigDecimal) retorno, 2));
				else
					linha.add(retorno.toString());
			}

			Method method = object.getClass().getMethod("getId");
			Object retorno = method.invoke(object);
			Long id = Long.parseLong(retorno.toString());

			Method methodExibicaoStr = object.getClass().getMethod("getExibicao");
			Object retornoExibicaoStr = methodExibicaoStr.invoke(object);
			String exibicaoSTR = retornoExibicaoStr.toString();

			Object valorAppend = null;

			if(atributoAppend != null && !atributoAppend.trim().isEmpty()) {
				valorAppend =  new Mirror().on(object).invoke().getterFor(atributoAppend);
			}

			ObjUtil<Long, List<String>> objUtil = new ObjUtil<>(id, linha, exibicaoSTR, valorAppend);
			montarFree.add(objUtil);
		}

		List<RetornoSelect> retorno = new ArrayList<>();

		for(ObjUtil<Long, List<String>> objetoMontado : montarFree) {

			String retornoStr = "";

			for(String string : objetoMontado.getUtil2()) {
				retornoStr += string + " ";
			}

			retorno.add(new RetornoSelect(objetoMontado.getUtil1(), retornoStr, (String)objetoMontado.getUtil3(), objetoMontado.getUtil4() != null ? objetoMontado.getUtil4().toString() : null));
		}


		return retorno;
	}


}
