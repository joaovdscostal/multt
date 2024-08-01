<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:admin>
	<form class="m-3 validateForm load" action="${sessao.urlPadrao}adm/modulos" method="post" role="form" enctype="multipart/form-data">
		 <div class="header header-padrao-multt titulo-de-formulario pt-0">
			<div class="container-fluid fonte-padrao p-0">
				<div class="d-flex justify-content-between align-items-center ">
					<div>
						<h2 class="header-title titulo-index-page">
							Novo Módulo
						</h2>
						<div class="multt-titulo-line-padrao"></div> 			
					</div>
				</div>
			</div>
		</div>
		<%@include file="form.jsp"%>
	</form>
</template:admin>