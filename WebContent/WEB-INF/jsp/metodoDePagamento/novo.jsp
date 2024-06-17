<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:admin>	
	
	<div class="header header-padrao-multt titulo-de-formulario ">
		<div class="container-fluid fonte-padrao">
			<div class="d-flex justify-content-between align-items-center ">
				<div>
					<h1 class="header-title titulo-index-page">
						Novo Metodo de Pagamento
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
	<form class="ml-3 validateForm load" action="${sessao.urlPadrao}adm/metodo-pagamento" method="post" role="form"
		enctype="multipart/form-data">
		<%@include file="form.jsp"%>
	</form>
</template:admin>