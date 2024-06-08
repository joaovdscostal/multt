package br.com.jvlabs.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.Transient;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.jvlabs.util.DateUtils;
import br.com.jvlabs.util.FormatterString;
import br.com.jvlabs.util.Sessao;

	public class Arquivo implements Serializable {

		private static final long serialVersionUID = 1L;

		private String nomeArquivo;

		@Transient
		private UploadedFile uploadFile;

		private Long size;

		public UploadedFile getUploadFile() {
			return uploadFile;
		}

		public void setUploadFile(UploadedFile uploadFile) {
			if(uploadFile != null) {
				this.uploadFile = uploadFile;
				this.nomeArquivo = uploadFile.getFileName();
				atualizaNome();
				this.size = uploadFile.getSize();
			}
			
		}

		private void renomearArquivoEAdicionarData() {
			String estenssao = getExtension();
			String nameWithoutExtension = getUrlWithoutExtension();

			String date = DateUtils.datetimeToString(new Date());
			date = new FormatterString().toString(date).removeMask().removeSpace().removeMaskForFile().formatString();

			this.nomeArquivo = nameWithoutExtension + "_" + date + estenssao;
		}

		public String getUrlWithoutExtension() {
			return nomeArquivo.substring(0, nomeArquivo.indexOf("."));
		}

		public String getExtension() {
			int index = nomeArquivo.lastIndexOf(".");

			if (index == -1)
				return nomeArquivo;

			return nomeArquivo.substring(index);
		}
		public void atualizaNome() {
			setNomeArquivo(nomeArquivo);
			renomearArquivoEAdicionarData();
		}

		public String getNomeArquivo() {
			if(this.nomeArquivo == null)
				this.nomeArquivo = new FormatterString().toString(nomeArquivo.toLowerCase()).removeAccents().removeSpace().removeMaskForFile().formatString();

			return nomeArquivo;
		}

		public void setNomeArquivo(String nomeArquivo) {
			nomeArquivo = nomeArquivo.toLowerCase();
			nomeArquivo = new FormatterString().toString(nomeArquivo).removeAccents().removeSpace().removeMaskForFile().formatString();
			this.nomeArquivo = nomeArquivo;
		}

		public String getPathFiles() {
			return java.io.File.separator + "arquivos";
		}
		public Arquivo(String nome) {
			this.setNomeArquivo(nome);
			renomearArquivoEAdicionarData();
		}

		public static String getPathUrl() {
			return "arquivos";
		}

		public Arquivo() {

		}

		public Long getSize() {
			return size;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		public String getFileUrl() {
			Sessao sessao = CDI.current().select(Sessao.class).get();
			return sessao.getUrlPadrao()+getPathUrl()+File.separator+nomeArquivo;
		}

		public boolean isValido() {
			return this.uploadFile != null;
		}
		
		public boolean isImagemValida() {
			return isValido() && temExtensaoParaImagemValida();
		}
		

		private boolean temExtensaoParaImagemValida() {
			if(getExtension().toLowerCase().contains("jpg"))
				return true;
			if(getExtension().toLowerCase().contains("jpeg"))
				return true;
			if(getExtension().toLowerCase().contains("png"))
				return true;
			
			return false;
		}

		public static Arquivo montar(UploadedFile uploadedFile) {
			Arquivo arquivo = new Arquivo();
			arquivo.setUploadFile(uploadedFile);
			return arquivo;
		}


	}


