<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:admin>
	
	<div class="header header-padrao-multt">
		<div class="container-fluid fonte-padrao">
			<div class="d-flex justify-content-between align-items-center">
				<div>
					<h1 class="header-title titulo-index-page">
						Alterar senha
					</h1>
					<div class="multt-titulo-line-padrao"></div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid fonte-padrao">
		<div class="form-multt-padrao">
			<form class="validateForm load" action="${sessao.urlPadrao}adm/usuarios/${usuario.id}/senha" method="post"role="form">
				<div class="card-body card-padding">
					<c:if test="${not empty usuario.id}">
						<input type="hidden" name="usuario.id" value="${usuario.id}" />
					</c:if>
					
					<div class="row">
						<div class="form-group col-md-6">
							<label for="usuarioSenha" class="control-label">Senha</label>
							<input type="password" name="usuario.senha" id="usuarioSenha" pattern=".{5,}" class="form-control required" />
						</div>
	
						<div class="form-group col-md-6">
							<label for="usuarioSenhaConfirmacao" class="control-label">Confirmar senha</label>
							<input type="password" name="usuario.senhaConfirmacao" id="usuarioSenhaConfirmacao" pattern=".{5,}"
								class="form-control required" />
						</div>
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-primary sendForm">Atualizar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br>
</template:admin>