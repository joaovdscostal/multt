package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.OfertaDao;
import br.com.jvlabs.model.Oferta;
import br.com.jvlabs.model.Produto;

@RequestScoped
public class OfertaService extends ServiceProjeto {

	@Inject
	private OfertaDao ofertaDao;

	public Oferta cria(Oferta oferta) {
		oferta = ofertaDao.merge(oferta);
		logService.criarLog("OFERTA-CREATE", oferta);
		return oferta;
	}

	public void atualiza(Oferta oferta)  {
		ofertaDao.merge(oferta);
		logService.criarLog("OFERTA-UPDATE", oferta);
	}

	public void apagar(Oferta oferta) {
		ofertaDao.delete(oferta);
		logService.criarLog("OFERTA-DELETE", oferta);
	}

	public Oferta clonar(Oferta oferta) throws CloneNotSupportedException {
		oferta = ofertaDao.get(oferta.getId());

		Oferta clonada = (Oferta) oferta.clone();

		clonada = ofertaDao.merge(clonada);
		logService.criarLog("OFERTA-CLONE", oferta);
		return clonada;
	}

	public Oferta criarOfertaPadraoParaProduto(Produto produto) {
		Oferta oferta = Oferta.builder()
						.produto(produto)
						.valor(produto.getValor())
						.build();
		
		oferta.setNome(produto.getNome());
		oferta = ofertaDao.merge(oferta);
		logService.criarLog("OFERTA-CREATE", oferta);
		
		return oferta;
	}


}
