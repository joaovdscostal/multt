package br.com.jvlabs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.enumerated.TipoLiberacao;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "CONTEUDO")
@SQLDelete(sql = "UPDATE CONTEUDO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Conteudo extends Entidade implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;
	
	private String titulo;
	
	private String embed;
	
	@Lob
	private String conteudo;
	
	@ElementCollection
	@CollectionTable(name = "conteudo_anexos",joinColumns = @JoinColumn(name = "conteudo_id"))
    @Column(name = "anexo")
	private List<String> anexos;
	
	@Enumerated(EnumType.STRING)
	private TipoLiberacao tipoLiberacao;
	
	private Integer ordem;
	
	private Integer diasApos;
	
	private Integer diasDeLimitacaoDeDuracao;

	private Date data;

	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Conteudo conteudo = (Conteudo) super.clone();
		conteudo.removerId();
		return conteudo;
	}

	@Override
	public String getExibicao() {
		// TODO Auto-generated method stub
		return null;
	}
}
