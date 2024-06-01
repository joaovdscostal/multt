package br.com.jvlabs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.jvlabs.enumerated.TipoDinamico;


@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldJvLabs {
	boolean exibirForm() default true;

	String label() default "";

	boolean required() default false;

	int ordem() default 0;

	boolean exibirPesquisa() default false;

	boolean exibirListagem() default false;

	String size() default " col-md-12 ";

	String mascara() default "";

	String css() default "";

	boolean dinheiro() default false;

	TipoDinamico dinamico() default TipoDinamico.NENHUM;

	boolean ehClassePai() default false;
}


