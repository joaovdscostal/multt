<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<template:admin>

<!--Inicio Menu Slide Produto ........................................................................................ -->
<style>

  clear{
    clear: both;
  }
  
  .slide-toggle{
    display: none;
  }
  
  .slidemenu{
    font-family: arial, sans-serif;
    max-width: 400px;
    overflow: hidden;
  }
  
  .slidemenu label{
    width: 33.333333%;
    text-align: center;
    display: block;
    float: left;
    color: #9448AE !important;
    opacity: 0.2;
  
  }
  
  .slidemenu label:hover{
    cursor: pointer;
    color: #666;
  }
  
  .slidemenu label span{
    display: block;    
  }
  
  .slider{
    width: 100%;
    height: 5px;
    display: block;
    background: #ccc;
    border-radius: 5px;
    margin-top: 23px;
  }
  
  .slider .bar{
    width: 33.333333%;
    height: 5px;
    background: #9448AE;
    border-radius: 5px;
  }
  
  .slidemenu label, .slider .bar {
    transition: all 500ms ease-in-out;
    -webkit-transition: all 300ms ease-in-out;
    -moz-transition: all 300ms ease-in-out;
  }
  
  .slidemenu .slide-toggle:checked + label{
    opacity: 1;
  }

  .slidemenu #slide-item-1:checked ~ .slider .bar{ margin-left: 0; }
  .slidemenu #slide-item-2:checked ~ .slider .bar{ margin-left: 33.333333%; }
  .slidemenu #slide-item-3:checked ~ .slider .bar{ margin-left: 66.666666% }
  /*.slidemenu #slide-item-4:checked ~ .slider .bar{ margin-left: 75%; }*/
</style>
<!--Fim Menu Slide Produto ........................................................................................... -->



<div class="pl-4">
	<div id="multt-menu-editar-produto">
		   <nav class="slidemenu">
	
		      <!-- Item 1 -->
		      <input type="radio" name="slideItem" id="slide-item-1" data-container-id="#gerais" class="slide-toggle" checked="">
		      <label for="slide-item-1"><span>Gerais</span></label>
		      
		      <!-- Item 2 -->
		      <input type="radio" name="slideItem" id="slide-item-2"  data-container-id="#config" class="slide-toggle">
		      <label for="slide-item-2"><span>Configurações</span></label>
		      
		      <!-- Item 2 -->
		      <input type="radio" name="slideItem" id="slide-item-3"  data-container-id="#checkout" class="slide-toggle">
		      <label for="slide-item-3"><span>Checkout</span></label>
		      
		      <!-- <input type="radio" name="slideItem" id="slide-item-3" class="slide-toggle">
		      <label for="slide-item-3"><span>Checkout</span></label>
		      
		      <input type="radio" name="slideItem" id="slide-item-4" class="slide-toggle">
		      <label for="slide-item-4"><span>Blog</span></label> -->
		      
		      <!-- Bar -->
		      <div class="slider">
		          <div class="bar"></div>
		      </div>
		
		   </nav>
	</div>
	<form class="validateForm load" action="${sessao.urlPadrao}adm/produtos/editar" method="post" role="form" enctype="multipart/form-data">
		
		<c:if test="${not empty produto.id}">
		    <input type="hidden" name="produto.id" id="produtoId" value="${produto.id}" />
		</c:if>
		<div class="mt-4 card card-body">
		<div id="gerais" class="multt-form-menu container-ativo">
			<div class="header header-padrao-multt titulo-de-formulario p-0">
				<div class="container-fluid fonte-padrao p-0">
					<div class="d-flex justify-content-between align-items-center ">
						<div>
							<h1 class="header-title titulo-index-page">
								Gerais
							</h1>
							<div class="multt-titulo-line-padrao"></div>
							<div class="multt-sub-header-title">
								Aprenda sobre as configurações 
								de <a href="#">parcelamento</a> no checkout
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-row form-multt-padrao">
		    	<div class="form-group  col-md-4">
			        <label for="produtoNome" class="control-label">Nome</label>
			        <input type="text" name="produto.nome" id="produtoNome" class="form-control required " value="${produto.nome}"/>
			    </div>
			    
			    <div class="form-group  col-md-4">
			        <label for="produtoNome" class="control-label">Tipo</label>
			        <select name="produto.tipo" id="produtoCategoria" class="form-control">
				    	<option value=""> - </option>
				    	<c:forEach items="${tipoTipoDeProdutoList}" var="tipo">
				    		<option value="${tipo}" ${tipo == produto.tipo ? 'selected' : ''}> ${tipo.descricao} </option>
				    	</c:forEach>
				    </select>
			    </div>	
			    
			    <div class="form-group  col-md-4">
			        <label for="produtoNome" class="control-label">Categoria</label>
			        <select name="produto.categoria.id" id="produtoCategoria" class="form-control">
				    	<option value=""> - </option>			 
				    	<c:forEach items="${categorias}" var="categoria">
				    		<option value="${categoria.id}" ${categoria.id == produto.categoria.id ? 'selected' : ''}> ${categoria.nome} </option>
				    	</c:forEach>   	
				    </select>
			    </div>
			    
			     <div class="form-group col-md-12 ">
					<label for="produtoDescricao" class="control-label">Descrição</label>
					<textarea name="produto.descricao" id="produtoDescricao" class="form-control"> ${produto.descricao}</textarea>
				</div>
			    
			    <div class="form-group  col-md-6">
			        <label for="produtoPaginaDeVendasExterna" class="control-label">Página de Vendas</label>
			        <input type="text" name="produto.paginaDeVendasExterna" id="produtoPaginaDeVendasExterna" class="form-control required "   value="${produto.paginaDeVendasExterna}"/>
			    </div>
			    
			    
			    <div class="form-group  col-md-6">
			        <label for="produtoTipoPagamento" class="control-label">Tipo de Pagamento</label>
			        <select name="produto.tipoPagamento" id="produtoTipoPagamento" class="form-control">
				    	<option value=""> - </option>
				    	<c:forEach items="${tipoPagamentoTipoPagamentoProdutoList}" var="tipo">
				    		<option value="${tipo}" ${tipo == produto.tipoPagamento ? 'selected' : ''}> ${tipo.descricao} </option>
				    	</c:forEach>
				    </select>
			    </div>
			    
			    <div class="form-group  col-md-12">
			    	<label for="imagensProduto" class="control-label">Imagens</label>
			    	<div class="input-images-produto" id="imagensProduto" style="margin-bottom: 10px" data-json='${produto.getImagensJson(sessao.getUrlPadrao())}' ></div>				    
			    </div>
			    
			    <div class="header header-padrao-multt titulo-de-formulario p-0 m-0">
					<div class="container-fluid fonte-padrao p-0">
						<div class="d-flex justify-content-between align-items-center ">
							<div>
								<h1 class="header-title titulo-index-page">
									Ofertas
								</h1>
								<div class="multt-titulo-line-padrao"></div>
								<div class="multt-sub-header-title">
									Aprenda sobre <a href="#">Ofertas</a> no guia
								</div>
							</div>
						</div>
					</div>
				</div>
			    
			    <div class="form-group col-md-12">
			        <div class="multt-produto-ofertas-list-container mt-4">
			        	<!-- <div class="multt-aviso-vazio-padrao"> Nenhuma Oferta Cadastrada</div> -->
			        	<c:forEach items="${produto.ofertas}" var="oferta" varStatus="status">
			        		<div class="multt-produto-oferta row align-items-center mt-3">
				        		<div class="form-group col-md-4 m-0">				        
							        <input type="text" name="produto.ofertas[${status.count}].nome" placeholder="Nome da Oferta" class="form-control nomeOferta" value="${oferta.nome}"/>
							    </div>
							    <div class="form-group col-md-4 m-0">				        
							        <input type="text" name="produto.ofertas[${status.count}].valor" placeholder="Valor da Oferta" class="form-control valorOferta" alt="decimalSemZero" value="${oferta.valor}"/>
							    </div>
							     <div class="form-group col-md-1 m-0 d-flex align-items-end">					    	
				        			<button class="btn btn-circle btn-danger removeOferta"><i class="fas fa-times"></i></button>
				        			<input type="hidden" name="produto.ofertas[${status.count}].id" value="${oferta.id}"/>				        			    
							    </div>					    
				        	</div>
			        	</c:forEach>    	
			        	<div class="multt-produto-oferta row align-items-center mt-3">
			        		<div class="form-group col-md-4 m-0">				        
						        <input type="text" name="produto.ofertas[0].nome" placeholder="Nome da Oferta" class="form-control nomeOferta"/>
						    </div>
						    <div class="form-group col-md-4 m-0">				        
						        <input type="text" name="produto.ofertas[0].valor" placeholder="Valor da Oferta" class="form-control valorOferta" alt="decimalSemZero"/>
						    </div>
						     <div class="form-group col-md-1 m-0 d-flex align-items-end">					    	
			        			<button class="btn btn-circle btn-primary addOferta mr-3"><i class="fas fa-plus"></i></button>				        			    
						    </div>					    
			        	</div>
			        </div>
			        <hr/>
			    </div>
			    
			    <!-- <div class="form-group col-md-12">
			    	<hr/>
			    	<div>
			    		<div class="d-flex justify-content-between align-items-center ">
							<div>
								<h1 class="header-title titulo-index-page">
									Lista de Ofertas
								</h1>
								<div class="multt-titulo-line-padrao"></div>									
							</div>
							<div>
								<div class="form-group custom-control custom-switch m-0">
								   <input name="" type="checkbox" class="custom-control-input multt-ativador-lista" id="ativo" <c:if
								       test="${true}">checked="checked"</c:if> >
								    <label class="custom-control-label" for="ativo">Habilitar Lista de Ofertas</label>
								</div>								
							</div>
						</div>
			    	</div>
			    	<hr/>
			    	
			    	<div class="mult-form-lista-padrao">
			    		<div class="lista-cadastro-container row">
			    			<div class="form-group col-md-4">
						    	<label for="ofertaNome" class="control-label">Nome</label>
			        			<input type="text" id="ofertaNome" class="form-control required "/>			    
						    </div>
						    <div class="form-group col-md-4">
						    	<label for="ofertaNome" class="control-label">Nome</label>
			        			<input type="text" id="ofertaNome" class="form-control required "/>			    
						    </div>
						    <div class="form-group col-md-1 d-flex align-items-end">					    	
			        			<button class="btn btn-circle btn-primary"><i class="fas fa-plus "></i></button>	    
						    </div>
			    		</div>
			    		<div class="lista-item-container">
			    			<div class="multt-aviso-vazio-padrao"> Nenhuma Oferta Cadastrada</div>
			    		</div>
			    	</div>	 -->			    
			    </div>
			    
			    <div class="form-group custom-control custom-switch">
				   <input name="produto.ativo" type="checkbox" class="custom-control-input" id="ativo" <c:if
				   test="${empty produto.id || produto.ativo == true}">checked="checked"</c:if> >
				   <label class="custom-control-label" for="ativo">Produto Ativo?</label>
				</div>
			    
			</div>
			
			<div id="config" class="multt-form-menu">
				
				<div class="header header-padrao-multt titulo-de-formulario p-0">
					<div class="container-fluid fonte-padrao p-0">
						<div class="d-flex justify-content-between align-items-center ">
							<div>
								<h1 class="header-title titulo-index-page">
									Configurações
								</h1>
								<div class="multt-titulo-line-padrao"></div>
								<div class="multt-sub-header-title">
									Aprenda sobre as configurações 
									de <a href="#">parcelamento</a> no checkout
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="form-row form-multt-padrao">
					<div class="form-group  col-md-4">
						<!-- Area em manutenção -->
				        <label for="produtoConfigMetodoPagamento" class="control-label"> Metodos de Pagamento</label>
						<select name="produto.configuracao.metodosDePagamento[].id" class="form-control custom-select required" multiple="multiple"  data-toggle="select" id="produtoConfigMetodoPagamento" >
							<c:forEach items="${metodosDePagamento}" var="metodo">
								<option data-id="${metodo.id}" value="${metodo.id}" ${fn:contains(produto.configuracao.metodosDePagamento, metodo) ? 'selected' : ''}>
										${metodo.nome}
								</option>
							</c:forEach>
						</select>
						<!-- Area em manutenção -->
				    </div>
				
					<div class="form-group  col-md-4">
				        <label for="produtoConfigLimiteParcela" class="control-label">Limite de Parcelas</label>
				        <input type="text" name="produto.configuracao.limiteParcela" id="produtoConfigLimiteParcela" class="form-control required" alt="numero" value="${produto.configuracao.limiteParcela}"/>
				    </div>
				    
				    <div class="form-group  col-md-4">
				        <label for="produtoConfigDiasVencimentoBoleto" class="control-label">Dias para Vencimento do Boleto</label>
				        <input type="text" name="produto.configuracao.diasVencimentoBoleto" id="produtoConfigDiasVencimentoBoleto" class="form-control required" alt="numero" value="${produto.configuracao.diasVencimentoBoleto}"/>
				    </div>
				    
				    <div class="form-group  col-md-12">
					    <hr/>
					    <div class="header header-padrao-multt titulo-de-formulario p-0">
							<div class="container-fluid fonte-padrao p-0">
								<div class="d-flex justify-content-between align-items-center justify-content-between">
									<div>
										<h1 class="header-title titulo-index-page">
											Orders Bump
										</h1>
										<div class="multt-titulo-line-padrao"></div>							
									</div>
									<div class="d-flex align-items-center">
										<button class="btn btn-primary cadastrar-order-bump" data-produto-id="${produto.id}"> Add Order Bump <i class="fas fa-plus ml-2"></i></button>			
									</div>
								</div>
							</div>
						</div>
						<div class="ordemBump-container">
				    		<table class="table">
				    			<thead>
				    				<tr>
					    				<th>#</th>
					    				<th style="width:100%">Produto</th>
					    				<th>Ações</th>
				    				</tr>
				    			</thead>
				    			<tbody id="bumpBody">
				    			<c:if test="${not empty produto.configuracao.ordersBump}">
					    			<c:forEach items="${produto.configuracao.ordersBump}" var="bump">
					    				<tr>
						    				<td>${bump.id}</td>
						    				<td style="width:100%">${bump.titulo}</td>
						    				<td class="d-flex">
						    					<button data-id="${bump.id}" class="btn btn-circle btn-secondary editarBump mr-2"><i class="fas fa-pen"></i></button>
						    					<button data-id="${bump.id}" class="btn btn-circle btn-danger removeBump"><i class="fas fa-times"></i></button>				    					
											</td>
										</tr>
					    			</c:forEach>
				    			</c:if>	
				    			<c:if test="${empty produto.configuracao.ordersBump}">
				    				<tr>
									 	<td colspan="3" class="text-center">Nenhum Bump Cadastrado</td>
									</tr>
				    			</c:if>	
				    			</tbody>
				    		</table>
				    	</div>
						<hr/>					
				    </div>
				</div>
			</div>
			
			<div id="checkout" class="multt-form-menu">
				<div class="header header-padrao-multt titulo-de-formulario p-0">
					<div class="container-fluid fonte-padrao p-0">
						<div class="d-flex justify-content-between align-items-center justify-content-between">
							<div>
								<h1 class="header-title titulo-index-page">
									Checkout
								</h1>
								<div class="multt-titulo-line-padrao"></div>							
							</div>
							<div class="d-flex align-items-center">
								<button class="btn btn-primary cadastrar-checkout" data-produto-id="${produto.id}"> Add Checkout <i class="fas fa-plus ml-2"></i></button>			
							</div>
						</div>
					</div>
				</div>			
				<div class="ordemBump-container">
		    		<table class="table">
		    			<thead>
		    				<tr>
			    				<th>#</th>
			    				<th style="width:100%">Produto</th>
			    				<th>Ações</th>
		    				</tr>
		    			</thead>
		    			<tbody id="checkoutBody">
		    			<c:if test="${not empty listaCheckouts}">
			    			<c:forEach items="${listaCheckouts}" var="checkout">
			    				<tr>
				    				<td>${checkout.id}</td>
				    				<td style="width:100%">${checkout.nome}</td>
				    				<td class="d-flex">
				    					<button data-id="${checkout.id}" class="btn btn-circle btn-danger removeCheckout"><i class="fas fa-times"></i></button>				    					
									</td>
								</tr>
			    			</c:forEach>
		    			</c:if>	
		    			<c:if test="${empty listaCheckouts}">
		    				<tr>
							 	<td colspan="3" class="text-center">Nenhum Checkout Cadastrado</td>
							</tr>
		    			</c:if>	
		    			</tbody>
		    		</table>
		    	</div>
		    	<hr/>
			</div>
			
			<div class="d-flex justify-content-end">
				<button type="submit" class="btn btn-primary sendForm">Salvar<i class="fal fa-save ml-2"></i></button>
			</div>
		</div>
		</div>
	</form>
</div>
</template:admin>