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
									<li class="breadcrumb-item"><a href="${sessao.urlAdm}/usuarios">Usu&aacute;rios</a></li>
									<li class="breadcrumb-item active" aria-current="page">Editar usu&aacute;rio</li>
									<ul class="actions">
										<li><a href="${sessao.urlPadrao}adm/usuarios/${usuario.id}/senha" title="Alterar senha"><i
													class="fa fa-lock" aria-hidden="true"></i>Alterar senha</a></li>
									</ul>
								</ol>
							</nav>
						</h6>
						<h1 class="header-title">
							Editar usu&aacute;rio
						</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid ">
		<div class="container-form">
			<form class="validateForm load" action="${sessao.urlPadrao}adm/usuarios/editar" method="post" role="form"
				enctype="multipart/form-data">
				<%@include file="form.jsp"%>
			</form>
		</div>
	</div>
</template:admin>