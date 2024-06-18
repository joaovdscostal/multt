package br.com.jvlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.jvlabs.annotation.Privado;
import br.com.jvlabs.dao.CategoriaProdutoDao;
import br.com.jvlabs.dao.CheckoutDao;
import br.com.jvlabs.dao.MetodoDePagamentoDao;
import br.com.jvlabs.dao.OfertaDao;
import br.com.jvlabs.dao.OrdemBumpDao;
import br.com.jvlabs.dao.ProdutoDao;
import br.com.jvlabs.datatables.Table;
import br.com.jvlabs.datatables.TableResponse;
import br.com.jvlabs.exception.BusinessException;
import br.com.jvlabs.model.CategoriaProduto;
import br.com.jvlabs.model.Checkout;
import br.com.jvlabs.model.MetodoDePagamento;
import br.com.jvlabs.model.Oferta;
import br.com.jvlabs.model.OrdemBump;
import br.com.jvlabs.model.Produto;
import br.com.jvlabs.service.ProdutoService;
import br.com.jvlabs.util.GsonUtils;
import br.com.jvlabs.util.HibernateUtil;



@Controller
public class ProdutoController extends ControllerProjeto {

	@Inject private ProdutoDao produtoDao;
	@Inject private ProdutoService produtoService;
	@Inject private CategoriaProdutoDao categoriaProdutoDao;
	@Inject private MetodoDePagamentoDao metodoDePagamentoDao;
	@Inject private OfertaDao ofertaDao;
	@Inject private OrdemBumpDao ordemBumpDao;
	@Inject private CheckoutDao checkoutDao;

	@Get("/adm/produtos") @Privado
	public void index() {}

	@Get("/adm/produtos/json") @Privado
	public void paginate(Table datatable) {
		try {
			datatable.filters(request, "nome");
			TableResponse<Produto> response = produtoDao.paginate(datatable);
			String retorno = new GsonUtils().padrao().toJson(response);
			addPlainAjax(retorno);
		} catch (StackOverflowError e) {
			addErroAjax("Erro ao serializar paginate!");
		}
	}

	@Post("/adm/produtos") @Privado
	public void criar(@Valid Produto produto) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			produto = produtoService.cria(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProdutoController.criar");
			return;
		}
		
		addMessage("Produto criado com sucesso!");
		result.redirectTo(this).editar(produto);

	}
	
	@Post("/adm/produtos/ajax") @Privado
	public void criarAjax(@Valid Produto produto) {
		validator.onErrorForwardTo(this).novo();

		try {
			HibernateUtil.beginTransaction();
			produto = produtoService.cria(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addErroAjax(e.getMessage());
			return;
		}
		
		addObjetoAjax(produto);

	}
	
	@Get("/adm/produtos/novo") @Privado
	public void novo() {}

	@Get("/adm/produtos/novo/modal") @Privado
	public void novoModal() {}
	
	@Get("/adm/produtos/{produto.id}/buscar-bumps/ajax") @Privado
	public void buscarOrersBump(Produto produto) {
		List<OrdemBump> orders = ordemBumpDao.buscarBumpsDoProduto(produto);
		addObjetoAjax(orders);
	}
	
	@Get("/adm/produtos/{produto.id}/buscar-checkouts/ajax") @Privado
	public void buscarCheckous(Produto produto) {
		List<Checkout> checkout = checkoutDao.buscarCheckoutsDoProduto(produto);
		addObjetoAjax(checkout);
	}
	
	@Get("/adm/produtos/{produto.id}/order-bump/modal") @Privado
	public void criarOrderBump(Produto produto) {
		produto = produtoDao.get(produto.getId());
		List<Produto> produtos = produtoDao.buscarProdutosDaConta(sessao.getConta());
		List<Oferta> ofertas = ofertaDao.buscarOfertasDaContaPorProduto(produto);
		
		OrdemBump orderBump = OrdemBump.builder()
									   .callToAction("Eu aceito esta oferta!")
									   .titulo(produto.getNome())
									   .descricao("adicione em sua compra!")
									   .build();
		
		result.include("produtoAtual",produto);
		result.include("produtosList",produtos);
		result.include("ofertasList",ofertas);
		result.include("orderBump",orderBump);
	}

	@Get("/adm/produtos/order-bump/editar/{ordemBump.id}/modal") @Privado
	public void editarOrderBump(OrdemBump ordemBump) {
		ordemBump = ordemBumpDao.get(ordemBump.getId());
		Produto produto = produtoDao.get(ordemBump.pegarIdDoProdutoReferencia());
		List<Produto> produtos = produtoDao.buscarProdutosDaConta(sessao.getConta());
		List<Oferta> ofertas = ofertaDao.buscarOfertasDaContaPorProduto(ordemBump.getProduto());
		
		result.include("produtoAtual",produto);
		result.include("produtosList",produtos);
		result.include("ofertasList",ofertas);
		result.include("orderBump",ordemBump);
	}
	
	
	
	@Post("/adm/produtos/editar") @Privado
	public void atualizar(Produto produto,List<UploadedFile> images) {
		validator.onErrorForwardTo(this).editar(produto);

		try {
			HibernateUtil.beginTransaction();
			produtoService.atualiza(produto,images);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProdutoController.atualizar");
		} catch (BusinessException e) {
			addValidation(e.getMessage());
			validator.onErrorForwardTo(this).editar(produto);
		} catch (IOException e) {
			addValidation(e.getMessage());
			validator.onErrorForwardTo(this).editar(produto);
		}

		addMessage("Produto atualizado com sucesso!");
		result.redirectTo(this).editar(produto);
	}

	@Get("/adm/produtos/{produto.id}/editar") @Privado
	public void editar(Produto produto) {
		try {
			produto = produtoDao.get(produto.getId());
			List<CategoriaProduto> categorias = categoriaProdutoDao.findAll();
			List<MetodoDePagamento> metodos = metodoDePagamentoDao.findAll();
			List<Checkout> checkouts = checkoutDao.buscarCheckoutsDoProduto(produto);
			
			result.include("listaCheckouts",checkouts);
			result.include("produto", produto);
			result.include("categorias", categorias);
			result.include("metodosDePagamento", metodos);
			
		}catch (NoResultException e) {
			addValidation("Produto nao encontrado!");
			validator.onErrorForwardTo(this).index();
		}
	}

	@Get("/adm/produtos/{produto.id}/apagar") @Privado
	public void apagar(Produto produto) {

		try {
			HibernateUtil.beginTransaction();
			produtoService.apagar(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProdutoController.apagar");
			return;
		}

		addMessage("Produto removido com sucesso!");
		result.redirectTo(this).index();
	}

	@Get("/adm/produtos/{produto.id}/clonar") @Privado
	public void clonar(Produto produto) {

		try {
			HibernateUtil.beginTransaction();
			produto = produtoService.clonar(produto);
			HibernateUtil.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback();
			addLogAndSendToErrorPage(e, "ProdutoController.apagar");
			return;
		} catch (CloneNotSupportedException e) {
			addValidation("N&atilde;o foi poss&iacute;vel clonar o produto " + e.getMessage());
			validator.onErrorForwardTo(this).index();
		}

		addMessage("Produto clonado com sucesso!");
		result.redirectTo(this).editar(produto);
	}




}
