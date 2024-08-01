package br.com.jvlabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.util.EncryptionUtils;
import br.com.jvlabs.util.FormatterString;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "USUARIO")
@SQLDelete(sql = "UPDATE USUARIO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Usuario extends EntidadeNomeAtivo {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
	private String login;

	@NotEmpty(message = "not.senha")
	@Column(nullable = false)
	private String senha;

	@Column
	private String email;

	private Integer contadorAcesso;

	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	@Transient
	private String senhaAntiga;

	@Transient
	private String senhaConfirmacao;

	@Type(type = "true_false")
	private Boolean abaLateral = Boolean.FALSE;

	public String getEncryptedPassword() {
		return EncryptionUtils.md5(senha);
	}

	public void makeAccessInSystem() {
		this.contadorAcesso = this.contadorAcesso == null ? 0 : this.contadorAcesso + 1;
	}

	public void setEncryptedPassword(String senha) {
		if (senha != null && !senha.isEmpty())
			this.senha = EncryptionUtils.md5(senha);
	}

	public void encryptPassword() {
		if (this.senha == null || this.senha.isEmpty())
			throw new IllegalArgumentException("password.cannot.be.empty");

		this.senha = EncryptionUtils.md5(this.senha);
	}

	public void updateUserToRegistration() {
		setEncryptedPassword(this.senha);
		this.contadorAcesso = 0;
	}
	
	public void init() throws BusinessException {
		init(null);
	}
	
	public void init(String senhaPadrao) throws BusinessException {
		if(senhaPadrao != null && !senhaPadrao.trim().isEmpty()) {
			this.senha = senhaPadrao;
			this.senhaConfirmacao = senhaPadrao;
		}

		verificaSenha();

		this.senha = EncryptionUtils.md5(senha);
		this.contadorAcesso = 0;
	}

	public void verificaSenha() throws BusinessException {
		if (!this.senha.equals(this.senhaConfirmacao))
			throw new BusinessException("usuario.senha.precisaSerIgual");

		if (this.senha.length() <= 4)
			throw new BusinessException("usuario.senha.precisaSerMaior");

		if (this.senha.contains(" "))
			throw new BusinessException("usuario.senha.precisaConterCaractere");
	}

	public boolean passwordIsCorrect(String encryptedPassword) {
		return this.senha.equals(encryptedPassword);
	}

	public boolean temSenha() {
		return this.senha != null && !senha.isEmpty();
	}

	public String getOldPasswordEncrypted() {
		return EncryptionUtils.md5(this.senhaAntiga);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getContadorAcesso() {
		return contadorAcesso;
	}

	public void setContadorAcesso(Integer contadorAcesso) {
		this.contadorAcesso = contadorAcesso;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public static Usuario padrao() {
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setSenha("Nds*FR");
		usuario.setNome("Usuario");
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);
		usuario.updateUserToRegistration();
		usuario.ativado();
		return usuario;
	}

	public static Usuario novo(String nome, TipoUsuario tipo) {
		String login = new FormatterString().toString(nome).gerarSenha().formatString();
		nome = new FormatterString().toString(nome).capitalize().formatString();

		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha("abc123");
		usuario.setNome(nome);
		usuario.setTipo(tipo);
		usuario.updateUserToRegistration();
		usuario.ativado();
		return usuario;
	}

	private void ativado() {
		setAtivo(true);
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getExibicao() {
		return this.getNome() + (tipo!=null ? " ("+tipo.getExibicao()+")" : "");
	}

	public boolean isAdministrador() {
		return this.tipo != null && this.tipo.isAdministrador();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public boolean temEmail() {
		return this.email != null && !this.email.trim().isEmpty();
	}

	public boolean isUsuario() {
		return this.tipo != null && this.tipo.isUsuario();
	}

	public boolean possuiTipo() {
		return this.tipo != null;
	}





}