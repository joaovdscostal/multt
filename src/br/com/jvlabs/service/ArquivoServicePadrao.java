package br.com.jvlabs.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Arquivo;
import br.com.jvlabs.model.Checkout;
import br.com.jvlabs.model.Conta;
import br.com.jvlabs.model.Produto;


@RequestScoped
public class ArquivoServicePadrao {

	public static final String SEPARADOR = "X";
	public static int TAMANHO_IMAGEM_MENOR = 60;
	public static int TAMANHO_IMAGEM_MAIOR = 220;

	@Inject private Environment environment;

	public String salvarArquivo(Arquivo arquivo) throws BusinessException, IOException {
		String path = environment.get("realPath") + arquivo.getPathFiles();
		salvarArquivoInPath(arquivo.getNomeArquivo(), arquivo.getUploadFile().getFile(), path);
		return arquivo.getNomeArquivo();
	}

	public boolean isExtensaoValidaParaImagem(String fileName) {
		if(fileName.toLowerCase().contains(".jpg"))
			return true;
		if(fileName.toLowerCase().contains(".jpeg"))
			return true;
		if(fileName.toLowerCase().contains(".png"))
			return true;
		if(fileName.toLowerCase().contains(".webp"))
			return true;
		if(fileName.toLowerCase().contains(".avif"))
			return true;

		return false;
	}
	
	public boolean isExtensaoValidaComVideo(String fileName) {
		if(fileName.toLowerCase().contains(".mp4")) {
			return true;
		}
		return isExtensaoValidaParaImagem(fileName);
		
	}

	public String salvarArquivoImagem(Arquivo arquivo) throws BusinessException, IOException {
		String path = environment.get("realPath") + arquivo.getPathFiles() + java.io.File.separator + "imagens";
		salvarArquivoInPath(arquivo.getNomeArquivo(), arquivo.getUploadFile().getFile(), path);

		return arquivo.getNomeArquivo();
	}

	private String salvarArquivoInPath(String nomeArquivo, InputStream file, String path) throws BusinessException {
		String pathSave = path + File.separator + nomeArquivo;
		salvarArquivoItem(file, pathSave);
		return path;
	}

	public void verificarDiretorio(String arquivo) {
		String directorySave = arquivo.substring(0, arquivo.lastIndexOf(File.separator));

		File file = new File(directorySave);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	private void salvarArquivoItem(InputStream inputStream, String directory) throws BusinessException {
		String directorySave = directory.substring(0, directory.lastIndexOf(File.separator));

		File file = new File(directorySave);
		if (!file.exists()) {
			file.mkdirs();
		}


		FileOutputStream fos = null;
		InputStream in = null;
		try {
			in = inputStream;
			File fileSalvar = new File(directory);

			fos = new FileOutputStream(fileSalvar);
			int c;
			while ((c = in.read()) != -1)
				fos.write(c);
		} catch (Exception e) {
			throw new BusinessException("Erro ao salvar arquivo - " + e.getMessage());
		} finally {
			try {
				fos.flush();
				fos.close();
				in.close();
			} catch (IOException e) {
				throw new BusinessException("Erro ao salvar arquivo - " + e.getMessage());
			}
		}
	}
	
	public String salvarDocumentoParaPerfilDeConta(UploadedFile uploadedFile,Conta conta) throws BusinessException, IOException {
		Arquivo arquivo = Arquivo.montar(uploadedFile);
		
		String path = environment.get("realPath") + java.io.File.separator + "arquivos" 
		+ java.io.File.separator + "contas" + java.io.File.separator + "perfil_" + conta.getId().toString();
		
		salvarArquivoInPath(arquivo.getNomeArquivo(), arquivo.getUploadFile().getFile(), path);
		return arquivo.getNomeArquivo();
	}

	public String salvarImagemParaProduto(UploadedFile uploadedFile,Conta conta, Produto produto) throws BusinessException, IOException {
		Arquivo arquivo = Arquivo.montar(uploadedFile);
		
		String path = environment.get("realPath") + java.io.File.separator + "arquivos" + java.io.File.separator + "imagens";
		
		salvarArquivoInPath(arquivo.getNomeArquivo(), arquivo.getUploadFile().getFile(), path);
		return arquivo.getNomeArquivo();
	}

	public String salvarBannerPara(UploadedFile uploadedFile, Checkout checkout) throws BusinessException, IOException {
		Arquivo arquivo = Arquivo.montar(uploadedFile);		
		String path = environment.get("realPath") + java.io.File.separator + "arquivos" + java.io.File.separator + "banners";
		
		salvarArquivoInPath(arquivo.getNomeArquivo(), arquivo.getUploadFile().getFile(), path);
		return arquivo.getNomeArquivo();
	}
	
	

}

