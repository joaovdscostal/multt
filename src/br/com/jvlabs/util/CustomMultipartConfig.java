package br.com.jvlabs.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

import br.com.caelum.vraptor.observer.upload.DefaultMultipartConfig;
@Specializes
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {
   @Override
   public long getSizeLimit() {
       //return 50 * 1024 * 1024 * 1024; // 50MB
       return 350 * 1024 * 1024 * 1024; // 50MB
   }

   public long getFileSizeLimit() {
       //return 20 * 1024 * 1024; // 20MB
       return 350 * 1024 * 1024; // 20MB
   }
}