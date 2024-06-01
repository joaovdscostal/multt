package br.com.jvlabs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.jvlabs.dao.UsuarioDao;

@MappedSuperclass
public abstract class Entidade implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date criadoEm;

	@Column(updatable = false, name="criadoPor_id")
	private Long criadoPor;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificadoEm;

	@Column(name="modificadoPor_id")
	private Long modificadoPor;

	@Type(type = "true_false")
	private Boolean excluido = Boolean.FALSE;

	public void setManipulador(Usuario usuario) {
		if(usuario != null) {
			this.setCriadoPor(usuario.getId());
			this.setModificadoPor(usuario.getId());
		}
	}

	public boolean isTransient() {
		return id == null;
	}

	public String getNomeCriador() {
		if(criadoPor != null) {
			UsuarioDao dao = new UsuarioDao();
			return dao.get(criadoPor).getNome();
		}else {
			return "";
		}
	}

	public String getNomeModificador() {
		if(modificadoPor != null) {
			UsuarioDao dao = new UsuarioDao();
			return dao.get(modificadoPor).getNome();
		}else {
			return "";
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Date getModificadoEm() {
		return modificadoEm;
	}

	public void setModificadoEm(Date modificadoEm) {
		this.modificadoEm = modificadoEm;
	}

	public Boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

	public boolean isExcluido() {
		return excluido != null ? getExcluido() : true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entidade other = (Entidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getIdsSecundarios(Object object) {
		String retorno = "[";

		int contador = 1;

		if(object != null) {

			if(object instanceof List) {
				List<Object> objectList = (List<Object>) object;
				for(Object objeto : objectList) {
					if(contador> 1)
						retorno += ",";

					Entidade entidade = (Entidade) objeto;

					retorno+= entidade.getId().toString();
					contador++;
				}
			}

			if(object instanceof Set) {
				Set<Object> objectList = (Set<Object>) object;
				for(Object objeto : objectList) {
					if(contador> 1)
						retorno += ",";

					Entidade entidade = (Entidade) objeto;

					retorno+= entidade.getId().toString();
					contador++;
				}
			}


		}

		retorno += "]";
		return retorno;
	}

	public Long getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Long criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Entidade entidade = (Entidade) super.clone();
		entidade.removerId();
		return entidade;
	}

	public void removerId(){
		this.id = null;
	}

}
