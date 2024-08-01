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
									<li class="breadcrumb-item"><a href="${sessao.urlPadrao}adm">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">Modulos</li>
								</ol>
							</nav>
						</h6>
						<h1 class="header-title">
							Modulos
						</h1>
					</div>
					<div class="col-auto">
						<a href="${sessao.urlPadrao}adm/modulos/novo" class="btn btn-primary">
							<i class="fas fa-plus"></i> Novo modulo
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-12">
				<form class="form-inline" id="moduloForm">
					<div class="col p-0">
						<label class="sr-only" for="moduloNome">Nome</label>
						<input type="text" name="nome" class="form-control mb-2 mr-sm-2 form-control-sm" id="nome"
							placeholder="Nome" >

					<button type="submit" class="btn btn-outline-primary mb-2 btn-sm"><i class="fad fa-search"></i>
						Pesquisar</button>
					</div>
				</form>

			</div>
			<div class="col-12">
				<div class="table-responsive">
					<table class="table table-nowrap" id="moduloDatatable">
					</table>
				</div>
			</div>
		</div>
	</div>
</template:admin>