package br.com.jvlabs.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.jvlabs.dao.ImagemDao;
import br.com.jvlabs.dao.OfertaDao;
import br.com.jvlabs.dao.ProdutoDao;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Imagem;
import br.com.jvlabs.model.Oferta;
import br.com.jvlabs.model.Produto;

@RequestScoped
public class ProdutoService extends ServiceProjeto {

	@Inject private ProdutoDao produtoDao;
	
	@Inject private OfertaDao ofertaDao;
	@Inject private OfertaService ofertaService;
	@Inject private ArquivoServicePadrao arquivoServicePadrao;
	@Inject private ImagemDao imagemDao;

	public Produto cria(Produto produto) {
		
		if(sessao.temConta()) {
			produto.setConta(sessao.getConta());
		}
		
		BigDecimal valorProduto = produto.getValor();
		
		produto = produtoDao.merge(produto);
		logService.criarLog("PRODUTO-CREATE", produto);
		
		produto.setValor(valorProduto);
		Oferta ofertaPadrao = ofertaService.criarOfertaPadraoParaProduto(produto);
		produto.addOferta(ofertaPadrao);
		
		return produto;
	}

	public void atualiza(Produto produto,List<UploadedFile> images) throws BusinessException, IOException  {
		Produto banco = produtoDao.get(produto.getId());
		
		List<Oferta> ofertasFront = produto.getOfertas();	
		List<Oferta> ofertasBanco = banco.getOfertas();
		
		for(Oferta o:ofertasBanco) {
			if(!o.isTransient()) {
				if(!ofertasFront.contains(o)) {
					ofertaDao.delete(o);
				}
			}	
		}
		
		if(banco.temContaVinculada()) {
			produto.setConta(banco.getConta());
		} else {
			if(sessao.temConta()) {
				produto.setConta(sessao.getConta());
			}
		}
		
		produto.limparListas();
		produto.validarTransient();
		
		produto = produtoDao.merge(produto);
		
		cadastrarImagensParaProdutoQueExiste(produto,images);
		
		for(Oferta o:ofertasFront) {
			if(o.getNome() != null && o.getValor() != null) {
				if(o.isTransient()) {
					o.setProduto(produto);
					ofertaService.cria(o);
				} else {
					o.setProduto(produto);
					ofertaService.atualiza(o);
				}
			}
		}
		
		logService.criarLog("PRODUTO-UPDATE", produto);
	}
	
	private void cadastrarImagensParaProdutoQueExiste(Produto produto, List<UploadedFile> images) throws BusinessException, IOException {
		produto.validarImagens();

		if (images != null) {
			for (UploadedFile uploadedFile : images) {
				if (uploadedFile != null) {
					String nomeImagem = arquivoServicePadrao.salvarImagemParaProduto(uploadedFile,sessao.getConta(), produto);
					Imagem imagem = Imagem.builder().produto(produto).nomeImagem(nomeImagem).build();
					imagemDao.merge(imagem);
				}
			}
		}
	}

	public void apagar(Produto produto) {
		produtoDao.delete(produto);
		logService.criarLog("PRODUTO-DELETE", produto);
	}

	public Produto clonar(Produto produto) throws CloneNotSupportedException {
		produto = produtoDao.get(produto.getId());

		Produto clonada = (Produto) produto.clone();

		clonada = produtoDao.merge(clonada);
		logService.criarLog("PRODUTO-CLONE", produto);
		return clonada;
	}


}
