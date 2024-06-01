package br.com.jvlabs.converter;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;

@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@Convert(BigDecimal.class)
public class BigDecimalConverter implements Converter<BigDecimal> {

	private static final String INVALID_MESSAGE_KEY = "is_not_a_valid_number";

	private final Locale locale = new Locale ("pt", "BR");

	/**
	 * @deprecated CDI eyes only
	 */
	protected BigDecimalConverter() {
	}

	@Override
	public BigDecimal convert(String value, Class<? extends BigDecimal> type) {
		if (isNullOrEmpty(value)) {
			return null;
		}

		if(value.contains(".")) {
			value = value.replace(".", ",");
		}

		try {
			return (BigDecimal) getNumberFormat().parse(value);
		} catch (ParseException e) {
			throw new ConversionException(new ConversionMessage(INVALID_MESSAGE_KEY, value));
		}
	}

	protected NumberFormat getNumberFormat() {
		DecimalFormat fmt = ((DecimalFormat) DecimalFormat.getInstance(locale));
		fmt.setParseBigDecimal(true);
		return fmt;
	}
}
