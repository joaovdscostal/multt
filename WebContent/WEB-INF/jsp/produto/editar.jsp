<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
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
    max-width: 300px;
    overflow: hidden;
  }
  
  .slidemenu label{
    width: 50%;
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
    width: 50%;
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
  .slidemenu #slide-item-2:checked ~ .slider .bar{ margin-left: 50%; }
  /*.slidemenu #slide-item-3:checked ~ .slider .bar{ margin-left: 50%; }
  .slidemenu #slide-item-4:checked ~ .slider .bar{ margin-left: 75%; }*/
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
		      
		      <!-- <input type="radio" name="slideItem" id="slide-item-3" class="slide-toggle">
		      <label for="slide-item-3"><span>Folio</span></label>
		      
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
		    <input type="hidden" name="produto.id" value="${produto.id}" />
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
				        <select name="produto.categoria" id="produtoCategoria" class="form-control">
					    	<option value=""> - </option>
					    	
					    </select>
				    </div>
				    
				     <div class="form-group col-md-12 ">
						<label for="produtoDescricao" class="control-label">Descrição</label>
						<textarea name="produto.descricao" id="produtoDescricao" class="form-control"> ${produto.descricao}</textarea>
					</div>
				    
				    <div class="form-group  col-md-12">
				        <label for="produtoPaginaDeVendasExterna" class="control-label">Página de Vendas</label>
				        <input type="text" name="produto.paginaDeVendasExterna" id="produtoPaginaDeVendasExterna" class="form-control required "   value="${produto.paginaDeVendasExterna}"/>
				    </div>
				    
				    <div class="form-group  col-md-6">
				        <label for="produtoValor" class="control-label">Valor do Produto</label>
				        <input type="text" name="produto.valor" id="produtoValor" class="form-control required " value="${produto.valor}" alt="decimalSemZero"/>
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
				    	<div class="input-images-produto" style="margin-bottom: 10px" data-json='' ></div>				    
				    </div>
				    
				    <div class="form-group custom-control custom-switch">
					   <input name="produto.ativo" type="checkbox" class="custom-control-input" id="ativo" <c:if
					       test="${empty produto.id || produto.ativo == true}">checked="checked"</c:if> >
					    <label class="custom-control-label" for="ativo">Produto Ativo?</label>
					</div>
				    
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
				
				</div>
			</div>
			
			<div class="d-flex justify-content-end">
				<button type="submit" class="btn btn-primary sendForm">Salvar<i class="fal fa-save ml-2"></i></button>
			</div>
		</div>
	</form>
</div>
</template:admin>