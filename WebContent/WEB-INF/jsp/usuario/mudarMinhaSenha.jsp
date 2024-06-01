<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
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
									<li class="breadcrumb-item active" aria-current="page">Alterar senha</li>
								</ol>
							</nav>
						</h6>
						<h1 class="header-title">
							Alterar senha
						</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid ">
		<div class="container-form">
			<form class=" validateForm load" action="${sessao.urlPadrao}adm/alterarminhasenha" method="post" role="form">
				<div class="card-body card-padding">
					<c:if test="${not empty usuario.id}">
						<input type="hidden" name="usuario.id" value="${usuario.id}" />
						<input type="hidden" name="usuario.login" value="${usuario.login}" />
					</c:if>

					<div class="form-group">
						<label for="usuarioSenhaAntiga" class="control-label">Senha antiga</label>
						<input type="password" name="usuario.senhaAntiga" id="usuarioSenhaAntiga" pattern=".{5,}"
							class="form-control required" />
					</div>

					<hr />

					<div class="form-group">
						<label for="usuarioSenha" class="control-label">Senha nova</label>
						<input type="password" name="usuario.senha" id="usuarioSenha" pattern=".{5,}" class="form-control required" />
					</div>

					<div class="form-group">
						<label for="usuarioSenhaConfirmacao" class="control-label">Confirmar senha</label>
						<input type="password" name="usuario.senhaConfirmacao" id="usuarioSenhaConfirmacao" pattern=".{5,}"
							class="form-control required" />
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-primary sendForm">Atualizar</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</template:admin>