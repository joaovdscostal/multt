package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.ImagemDao;
import br.com.jvlabs.model.Imagem;

@RequestScoped
public class ImagemService extends ServiceProjeto {

	@Inject
	private ImagemDao imagemDao;

	public Imagem cria(Imagem imagem) {


		imagem = imagemDao.merge(imagem);
		logService.criarLog("IMAGEM-CREATE", imagem);
		return imagem;
	}

	public void atualiza(Imagem imagem)  {
		imagemDao.update(imagem);
		logService.criarLog("IMAGEM-UPDATE", imagem);
	}

	public void apagar(Imagem imagem) {
		imagemDao.delete(imagem);
		logService.criarLog("IMAGEM-DELETE", imagem);
	}

	public Imagem clonar(Imagem imagem) throws CloneNotSupportedException {
		imagem = imagemDao.get(imagem.getId());

		Imagem clonada = (Imagem) imagem.clone();

		clonada = imagemDao.merge(clonada);
		logService.criarLog("IMAGEM-CLONE", imagem);
		return clonada;
	}


}
