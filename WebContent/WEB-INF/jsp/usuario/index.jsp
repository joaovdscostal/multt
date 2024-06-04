<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<template:admin>
	<div class="header header-padrao-multt">
		<div class="container-fluid fonte-padrao">
			<div class="d-flex justify-content-between align-items-center">
				<div>
					<h1 class="header-title titulo-index-page">
						Usu&aacute;rios
					</h1>
					<div class="multt-titulo-line-padrao"></div>
				</div>
				<div class="col-auto">
					<a href="${sessao.urlAdm}/usuarios/novo" class="btn btn-circle btn-primary">
						<i class="fas fa-plus "></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="quadro-padrao-container">
			<div class="icon-left-multt-quadro">
				<i class="fas fa-shopping-cart"></i>
				<p>0 ITENS</p>
			</div>
			<div>
				<h4>TOTAL DE VENDAS <span>R$ 0,00</span></h4>
			</div>
		</div>
	</div>
	
	<div class="container-fluid fonte-padrao">
		<div class="form-multt-padrao">
			<form id="usuarioForm">
				<div class="form-row">
					<div class="form-group col-md-2">
						 <label for="usuarioNome" class="control-label">Nome</label>
						<input type="text" name="nome" class="form-control " id="nome"
							placeholder="Ex.: Jo&atilde;o">
					</div>
	
					<div class="form-group col-md-2">
						<label for="usuarioTipo" class="control-label">Tipo</label>
						<select name="tipo" class="form-control" data-toggle="select" id="usuarioTipo" required="required">
							<option value="">-</option>
							<c:forEach items="${tipoUsuarioList}" var="tipo">
								<option value="${tipo}" ${tipo == usuario.tipo ? 'selected' : ''}>
									${tipo.exibicao}
								</option>
							</c:forEach>
						</select>
					</div>
	
					 <div class="form-group col-md-2 form-group-button d-flex align-items-end" >
				   		<button type="button" class="btn btn-outline-secondary btn-circle empty-form-paginate ml-1 mr-1" data-toggle="tooltip" data-placement="top" title="Limpar filtro"><i class="fas fa-redo-alt"></i></button>
			   	    	<button class="btn btn-light-rose btn-circle ml-1 mr-1" type="submit"><i class="fas fa-filter"></i></button>
	                 </div>
	             </div>
			</form>
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="table-responsive table-index-container">
						<table class="table table-nowrap" id="usuarioDatatable">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</template:admin>