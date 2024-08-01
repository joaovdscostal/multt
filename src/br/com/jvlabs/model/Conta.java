package br.com.jvlabs.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.enumerated.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CONTA")
@SQLDelete(sql = "UPDATE CONTA SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Conta extends EntidadeNome implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;
	
	private String email;
	
	private String celular;
	
	private String imagem;
	
	@OneToOne
	private Usuario usuario;
	
	@Embedded
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;
	
	public String pegarPrimeiroNome() {
		String texto = this.getNome();
		
        if (texto == null || texto.isEmpty()) {
            return "";
        }
        String[] palavras = texto.split("\\s+");
        return palavras[0];
    }
	
	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Conta curso = (Conta) super.clone();
		curso.removerId();
		return curso;
	}



}

