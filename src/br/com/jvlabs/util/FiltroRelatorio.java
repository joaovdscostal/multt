package br.com.jvlabs.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FiltroRelatorio {

	private List<CampoRelatorio> campos;

	public boolean temFiltroPara(String param) {
		if(campos == null)
			return false;

		CampoRelatorio campo = CampoRelatorio.builder().nome(param).build();

		int index = campos.indexOf(campo);

		if(index < 0)
			return false;

		return getValue(param) != null && !getValue(param).trim().isEmpty();
	}


	public String getValue(String param) {
		CampoRelatorio campo = CampoRelatorio.builder().nome(param).build();
		int index = campos.indexOf(campo);

		if(index < 0)
			return "";

		return campos.get(index).getValor();
	}

	public Long getValueLong(String param) {
		try {
			return Long.parseLong(getValue(param));
		} catch (NumberFormatException e) {
			return 0L;
		}
	}

	public BigDecimal getValueBigDecimal(String param) {
		try {
			return new BigDecimal(getValue(param));
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	public Date getValueDate(String param) {
		return DateUtils.stringToDate(getValue(param));
	}

	public <E> E getValueEnum(String param, Class<E> classe) {

		if (classe.isEnum()) {
			for (E constant : classe.getEnumConstants()) {
				if (constant.toString().equals(getValue(param))) {
					return constant;
				}
			}
		}

		return null;
	}
}
