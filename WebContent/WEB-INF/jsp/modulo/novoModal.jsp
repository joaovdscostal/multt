<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:admin>
	<form class="m-3 validateForm load" action="${sessao.urlPadrao}adm/modulos" method="post" role="form" enctype="multipart/form-data">
		<%@include file="form.jsp"%>
	</form>
</template:admin>