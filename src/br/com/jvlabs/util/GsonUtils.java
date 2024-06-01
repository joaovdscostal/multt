package br.com.jvlabs.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.com.jvlabs.model.Entidade;
import br.com.jvlabs.model.Usuario;


public class GsonUtils {

	public static GsonBuilder novoGsonBuilder() {
		return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").registerTypeAdapter(Usuario.class, (JsonSerializer<Usuario>) (usuario, type, context) -> {
			if (usuario == null) {
				return null;
			}
			final JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("id", usuario.getId());
			jsonObj.addProperty("nome", usuario.getNome());
			jsonObj.addProperty("login", usuario.getLogin());
			jsonObj.addProperty("ativo", usuario.getAtivo());
			jsonObj.addProperty("contadorAcesso", usuario.getContadorAcesso());
			jsonObj.addProperty("tipo", String.valueOf(usuario.getTipo()));
			jsonObj.add("criadoEm", context.serialize(usuario.getCriadoEm()));
			jsonObj.add("modificadoEm", context.serialize(usuario.getModificadoEm()));

			if(usuario.getCriadoPor() != null)
				jsonObj.addProperty("criadoPorId", usuario.getCriadoPor());

			if(usuario.getModificadoPor() != null)
				jsonObj.addProperty("modificadoPorId", usuario.getModificadoPor());
			return jsonObj;
		});
	}


	protected boolean ehFilhaDeCollection(FieldAttributes arg0) {
		return Arrays.asList(arg0.getDeclaredClass().getInterfaces())
				.contains(Collection.class) && arg0.getAnnotation(Enumerated.class) == null;
	}


	public Gson paraArquivo() {
		Gson gson = novoGsonBuilder().setExclusionStrategies(
				new ExclusionStrategy() {

					@Override
					public boolean shouldSkipField(FieldAttributes arg0) {
						if(arg0.getName().contains("turmas") || arg0.getName().contains("arquivo")){
							return true;
						}
						return false;
					}

					@Override
					public boolean shouldSkipClass(Class<?> arg0) {
						return false;

					}
				}).serializeNulls().create();
		return gson;
	}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Gson paraLog(final Object object) {
		Gson gson = novoGsonBuilder()
		.registerTypeAdapterFactory(new TypeAdapterFactory() {
			public TypeAdapter<? extends Entidade> create(Gson gson, TypeToken type) {
		        if (!Entidade.class.isAssignableFrom(type.getRawType())) {
		            return null;
		        }

		        final TypeAdapter<Entidade> delegate = gson.getDelegateAdapter(this, type);

		        return new TypeAdapter<Entidade>() {

		        	Collection<Entidade> objetosAnalizadas = new HashSet<>();

		            @Override
		            public void write(JsonWriter out, Entidade value) throws IOException {
		            	if(value != null) {
		            		Boolean permiteSerializar = object.getClass().equals(value.getClass());

			            	if(permiteSerializar) {
			            		objetosAnalizadas.add(value);
			            		delegate.write(out, value);
			            	}else {
			            		out.beginObject();
			            		out.name("id").value(value.getId());
			            		out.endObject();
		            		}
		            	}else {
		            		delegate.write(out, null);
		            	}
		            }

		            @Override
		            public Entidade read(JsonReader in) throws IOException {return delegate.read(in);}
		        };
		    }
		}).setExclusionStrategies(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes arg0) {
				if (arg0.getAnnotation(Transient.class) != null) {
					return true;
				}
				return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> arg0) {
				return false;

			}
		}).serializeNulls().create();
		return gson;
	}

	public Gson padrao(String... incluindo) {
		Gson gson = novoGsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes arg0) {
				if (incluindo != null) {
					for(String campo : incluindo) {
						if(arg0.getName().equals(campo)) {
							return false;
						}
					}
				}
				if (ehFilhaDeCollection(arg0)) {
					return true;
				}
				return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> arg0) {
				return false;
			}
		}).serializeNulls().create();
		return gson;
	}

	public Gson puro() {
		Gson gson = novoGsonBuilder().serializeNulls().create();
		return gson;
	}





}