<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:admin>
	<form class="m-3 validateForm load" action="${sessao.urlPadrao}adm/orders-bump/editar" method="post" role="form" enctype="multipart/form-data">
		<c:if test="${not empty produtoAtual.id}">
		   <input type="hidden" name="ordembump.produtoReferencia.id" value="${produtoAtual.id}" />
		   <input type="hidden" name="ordembump.id" id="idBump" value="${orderBump.id}" />
		</c:if>
		
		<div class="card-body card-padding">
			<div class="form-row form-multt-padrao">
				
				<div class="form-group col-md-6">
			        <label for="produtoBump" class="control-label">Produto</label>
			        <select name="ordembump.produto.id" id="produtoBump" class="form-control required">
				    	<option value=""> - </option>
				    	<c:forEach items="${produtosList}" var="produto">
				    		<option ${produto.id == orderBump.produto.id ? 'selected' : ''} data-img="${produto.pegarPrimeiraImagem()}" value="${produto.id}"> ${produto.nome} </option>
				    	</c:forEach>
				    </select>
			    </div>
			    
			    <div class="form-group  col-md-6">
			        <label for="ofertaBump" class="control-label">Oferta</label>
			        <select name="ordembump.oferta.id" id="ofertaBump" class="form-control">
				    	<option value=""> - </option>
				    	<c:forEach items="${ofertasList}" var="oferta">
				    		<option ${oferta.id == orderBump.oferta.id ? 'selected' : ''} data-valor="${oferta.valor}" value="${oferta.id}"> ${oferta.nome} </option>
				    	</c:forEach>
				    </select>
			    </div>			    
				
		    	<div class="form-group  col-md-12">
			        <label for="tituloBump" class="control-label"> Título </label>
			        <input type="text" name="ordembump.titulo" id="tituloBump" class="form-control required " value="${orderBump.titulo}"/>
			    </div>
			    
			    <div class="form-group  col-md-12">
			        <label for="callToActionBump" class="control-label">Call to Action</label>
			        <input type="text" name="ordembump.callToAction" id="callToActionBump" class="form-control required " value="${orderBump.callToAction}"/>
			    </div>
			    
			     <div class="form-group col-md-12 ">
					<label for="descricaoBump" class="control-label">Descrição</label>
					<textarea name="ordembump.descricao" id="descricaoBump" class="form-control required" data-descricao="${orderBump.descricao}"> ${orderBump.descricao}</textarea>
				</div>    
			    
			</div>
		
			<div class="form-group custom-control custom-switch">
			   <input name="ordembump.exibirImagemDoProduto" type="checkbox" class="custom-control-input" id="exibirImagemBump" 
			   <c:if test="${orderBump.exibirImagemDoProduto}">checked="checked"</c:if>>
			   <label class="custom-control-label" for="exibirImagemBump">Exibir Imagem do Produto?</label>
			</div>
			
			<hr/>
			
			<div class="form-group col-md-12 ">
				<div>
					<h4>Pré visualização</h4>
				</div>
				<div class="pre-vizualizacao-order-bump mt-3 p-3"></div>
			</div> 
			<hr/>
		
		    <div class="form-group">
		           <button style="width:100%" type="submit" class="btn btn-primary sendForm">Atualizar</button>	       
		    </div>
		</div>
	</form>
</template:admin>