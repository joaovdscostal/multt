<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:admin>
	<div class="header header-padrao-multt titulo-de-formulario">
		<div class="container-fluid fonte-padrao">
			<div class="d-flex justify-content-between align-items-center ">
				<div>
					<h1 class="header-title titulo-index-page">
						Novo Usu&aacute;rio
					</h1>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid ">
		<div class="container-form-multt">
			<form class="validateForm load" action="${sessao.urlPadrao}adm/usuarios" method="post" role="form"
				enctype="multipart/form-data">
				<%@include file="form.jsp"%>
			</form>
		</div>
	</div>
</template:admin>