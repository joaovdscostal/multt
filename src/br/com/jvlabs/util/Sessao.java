package br.com.jvlabs.util;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.jvlabs.model.Configuracao;
import br.com.jvlabs.model.TipoUsuario;
import br.com.jvlabs.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Named("sessao")
@SessionScoped @Getter @Setter
public class Sessao implements Serializable{

	private static final long serialVersionUID = -2716020570285741943L;

	private Usuario usuario;
	private String url;
	private String titulo;

	private String urlContinuacao;
	private String urlPadrao;
	private String urlBanner;
	private String urlManual;

	private Configuracao configuracao;

	public Sessao() {
	}

	public void login(Usuario usuario) {
		this.usuario = usuario;
	}
	public void logout() {
		this.usuario = null;
	}
	public boolean logado() {
		return this.usuario != null && !this.usuario.isTransient();
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	public TipoUsuario getTipoUsuario() {
		if(getUsuario()==null)
			return null;

		return getUsuario().getTipo();
	}
	public Long getUsuarioId() {
		if(getUsuario()==null)
			return null;

		return getUsuario().getId();
	}

	public boolean armazenaUrl(String requestUrl) {
		boolean alterouUrl = this.url == null || !this.url.equals(requestUrl);
		this.url = requestUrl;
		return alterouUrl;
	}

	public String getUrl() {
		return url;
	}

	public String getUrlLimpa() {
		return new FormatterString().toString(url)
									   .removeCharacteresToUrl()
									   .formatString();
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void adicionaUrlContinuacao(String requestUrl) {
		this.urlContinuacao = requestUrl;
	}

	public String getUrlContinuacao() {
		return urlContinuacao;
	}

	public void setUrlContinuacao(String urlContinuacao) {
		this.urlContinuacao = urlContinuacao;
	}

	public void limparUrlContinuacao() {
		this.urlContinuacao = null;
	}

	public boolean possuiUrlContinuacao() {
		return this.urlContinuacao != null && !this.urlContinuacao.isEmpty();
	}

	public String getUrlPadrao() {
		return urlPadrao;
	}
	public String getUrlAdm() {
		return urlPadrao+"adm";
	}

	public void setUrlPadrao(String urlPadrao) {
		this.urlPadrao = urlPadrao;

		if(urlPadrao != null){
			setUrlBanner(urlPadrao + "arquivos/banners/");
			setUrlManual(urlPadrao + "arquivos/manuais/");
		}
	}

	public String getUrlManual() {
		return urlManual;
	}

	public void setUrlManual(String urlManual) {
		this.urlManual = urlManual;
	}

	public String getUrlBanner() {
		return urlBanner;
	}

	public void setUrlBanner(String urlBanner) {
		this.urlBanner = urlBanner;
	}

	public boolean possuiUrlContinuacaoParaLogin() {
		return this.urlContinuacao != null && !this.urlContinuacao.isEmpty() && this.urlContinuacao.contains("logar");
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isAdministrador() {
		return this.usuario != null && this.usuario.isAdministrador();
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}

	public boolean isUsuario() {
		return this.usuario != null && this.usuario.isUsuario();
	}

}