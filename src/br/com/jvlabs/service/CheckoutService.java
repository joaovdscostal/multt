package br.com.jvlabs.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

import br.com.jvlabs.dao.CheckoutDao;
import br.com.jvlabs.dto.OfertaCheckoutDTO;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.Checkout;

@RequestScoped
public class CheckoutService extends ServiceProjeto {

	@Inject private CheckoutDao checkoutDao;

	public Checkout cria(Checkout checkout) {


		checkout = checkoutDao.merge(checkout);
		logService.criarLog("CHECKOUT-CREATE", checkout);
		return checkout;
	}

	public void atualiza(Checkout checkout)  {
		checkoutDao.update(checkout);
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

	public Checkout criaViaProduto(Checkout checkout, List<OfertaCheckoutDTO> ofertasDTO) throws BusinessException {
		
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
					checkout.addOferta(o.getOferta());
				}	
			}		
		}
		
		checkout = checkoutDao.merge(checkout);
		logService.criarLog("CHECKOUT-CREATE", checkout);
		return checkout;
	}


}
