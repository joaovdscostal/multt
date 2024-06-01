<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<template:admin>
	<div class="header">
		<div class="container-fluid">
			<div class="header-body">
				<div class="row align-items-end">
					<div class="col">
						<h6 class="header-pretitle">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="${sessao.urlAdm}">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">Usu&aacute;rios</li>
								</ol>
							</nav>
						</h6>
						<h1 class="header-title">
							Usu&aacute;rios
						</h1>
					</div>
					<div class="col-auto">
						<a href="${sessao.urlAdm}/usuarios/novo" class="btn btn-primary">
							<i class="fas fa-plus"></i>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="container-padrao">
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

				 <div class="form-group col-md-2 form-group-button" >
								   	<button type="button" class="btn btn-outline-warning empty-form-paginate" data-toggle="tooltip" data-placement="top" title="Limpar filtro"><i class="fas fa-redo-alt"></i></button>
							   	    <button class="btn btn-primary" type="submit"><i class="fad fa-search"></i> Filtrar</button>
	                              </div>
	             </div>
			</form>
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="table-responsive">
						<table class="table table-nowrap" id="usuarioDatatable">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</template:admin>