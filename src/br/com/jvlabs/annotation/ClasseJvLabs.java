package br.com.jvlabs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClasseJvLabs {
	String exibicaoSingular();
	String exibicaoPlural();
	String vogal();
	String icone() default "fad fa-clinic-medical";

	String url() default "";
	boolean criarDinamico() default true;
}


