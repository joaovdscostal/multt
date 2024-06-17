<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<template:admin>
	<div class="header header-padrao-multt mb-5">
		<div class="container-fluid fonte-padrao">
			<div class="d-flex justify-content-between align-items-center">
				<div>
					<h1 class="header-title titulo-index-page">
						Metodos de Pagamento
					</h1>
					<div class="multt-titulo-line-padrao"></div>
				</div>
				<div class="col-auto">
					<a href="${sessao.urlAdm}/metodo-pagamento/novo" class="btn btn-circle btn-primary">
						<i class="fas fa-plus "></i>
					</a>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="form-multt-padrao">
			<form id="metododepagamentoForm">
				<div class="form-row">
					<div class="form-group col-md-2">
						 <label for="usuarioNome" class="control-label">Nome</label>
						<input type="text" name="nome" class="form-control " id="nome" placeholder="Ex.: Jo&atilde;o">
					</div>
	
					 <div class="form-group col-md-2 form-group-button d-flex align-items-end" >
				   		<button type="button" class="btn btn-outline-secondary btn-circle empty-form-paginate ml-1 mr-1" data-toggle="tooltip" data-placement="top" title="Limpar filtro"><i class="fas fa-redo-alt"></i></button>
			   	    	<button class="btn btn-light-rose btn-circle ml-1 mr-1" type="submit"><i class="fas fa-filter"></i></button>
	                 </div>
	             </div>
			</form>
				
			<div class="col-12">
				<div class="table-responsive">
					<table class="table table-nowrap" id="metododepagamentoDatatable">
					</table>
				</div>
			</div>
		</div>
	</div>
</template:admin>