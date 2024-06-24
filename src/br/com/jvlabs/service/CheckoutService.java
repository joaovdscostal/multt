package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.io.IOException;
import java.util.List;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.jvlabs.dao.CheckoutDao;
import br.com.jvlabs.dto.OfertaCheckoutDTO;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Checkout;
import br.com.jvlabs.model.Imagem;
import br.com.jvlabs.util.Utils;

@RequestScoped
public class CheckoutService extends ServiceProjeto {

	@Inject private CheckoutDao checkoutDao;
	@Inject private ArquivoServicePadrao arquivoServicePadrao;
	
	public Checkout cria(Checkout checkout) {
		
		String codigo = Utils.gerarCodigoAleatorio(10);
		while(checkoutDao.existeCodigoIgual(codigo)) {
			codigo = Utils.gerarCodigoAleatorio(10);
		};
		
		checkout.setCodigo(codigo);
		checkout = checkoutDao.merge(checkout);
		logService.criarLog("CHECKOUT-CREATE", checkout);
		return checkout;
	}

	public void atualiza(Checkout checkout)  {
		checkoutDao.merge(checkout);
		logService.criarLog("CHECKOUT-UPDATE", checkout);
	}

	public void apagar(Checkout checkout) {
		checkoutDao.delete(checkout);
		logService.criarLog("CHECKOUT-DELETE", checkout);
	}

	public Checkout clonar(Checkout checkout) throws CloneNotSupportedException {
		checkout = checkoutDao.get(checkout.getId());

		Checkout clonada = (Checkout) checkout.clone();

		clonada = checkoutDao.merge(clonada);
		logService.criarLog("CHECKOUT-CLONE", checkout);
		return clonada;
	}

	public Checkout criaViaProduto(Checkout checkout, List<OfertaCheckoutDTO> ofertasDTO,UploadedFile banner) throws BusinessException, IOException {
		
		if(ofertasDTO != null ) {
			if(ofertasDTO.isEmpty()) {
				throw new BusinessException("Nenhuma Oferta Foi Selecionada");
			}
		} else {
			throw new BusinessException("Nenhuma Oferta Foi Selecionada");
		}
		
		for(OfertaCheckoutDTO o: ofertasDTO) {
			if(o != null) {
				if(o.getSelected()) {
					checkout.setOferta(o.getOferta());
				}	
			}		
		}
		
		if (banner != null) {
			String nomeImagem = arquivoServicePadrao.salvarBannerPara(banner, checkout);
			checkout.setBanner(nomeImagem);
		}
		
		String codigo = Utils.gerarCodigoAleatorio(10);
		while(checkoutDao.existeCodigoIgual(codigo)) {
			codigo = Utils.gerarCodigoAleatorio(10);
		};

		checkout.setCodigo(codigo);
		
		checkout = checkoutDao.merge(checkout);
		logService.criarLog("CHECKOUT-CREATE", checkout);
		return checkout;
	}


}
