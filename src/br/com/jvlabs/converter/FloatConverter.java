package br.com.jvlabs.converter;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;

@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@Convert(Float.class)
public class FloatConverter implements Converter<Float> {

	private static final String INVALID_MESSAGE_KEY = "is_not_a_valid_number";

	@Override
	public Float convert(String value, Class<? extends Float> type) {
		if (isNullOrEmpty(value)) {
			return null;
		}

//		if(value.contains(".")) {
//			value = value.replace(".", ",");
//		}

		try {
			return Float.parseFloat(value);
		} catch (Exception e) {
			throw new ConversionException(new ConversionMessage(INVALID_MESSAGE_KEY, value));
		}
	}

}
