<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>${configuracao.nome} - Login</title>
	<link rel="icon" type="image/png" href="${sessao.urlPadrao}img/favicon-32x32.png" sizes="32x32" />
	<link rel="icon" type="image/png" href="${sessao.urlPadrao}img/favicon-16x16.png" sizes="16x16" />

	<link href="${sessao.urlPadrao}css/plugins/bootstrap/bootstrap-theme.css" rel="stylesheet">
	<link href="${sessao.urlPadrao}css/admin/login.css" rel="stylesheet">
</head>

<body>

	<form class="form-signin d-flex flex-column align-items-center" action="${sessao.urlPadrao}adm/login" method="post">
		<img class="mb-4 img-fluid w-100" src="${sessao.urlPadrao}img/logo-transparent.png" alt="Logo">

		<h1 class="h5 m-3 font-weight-normal text-center">Administra&ccedil;&atilde;o</h1>

		<label for="inputEmail" class="sr-only">Usu&aacute;rio</label>
		<input name="usuario.login" type="text" id="inputEmail" class="form-control" placeholder="Usu&aacute;rio"
			required="" autofocus="">

		<label for="inputPassword" class="sr-only">Senha</label>
		<input name="usuario.senha" type="password" id="inputPassword" class="form-control" placeholder="Senha" required="">


		<button class="btn btn-lg btn-primary btn-block mb-4" type="submit">Entrar</button>

		<c:if test="${not empty message}">
			<div class="alert alert-success">
				${message}
			</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				${error}
			</div>
		</c:if>
		<c:if test="${not empty alert}">
			<div class="alert alert-warning">
				${alert}
			</div>
		</c:if>
		<p class="mt-5 mb-3 text-muted text-center small">Copyright &copy; 2020</p>
	</form>

	<script src="${sessao.urlPadrao}js/plugins/jquery/jquery-3.3.1.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/bootstrap/bootstrap.min.js"></script>
	<script src="${sessao.urlPadrao}js/admin/notificacao.js"></script>
	<script src="${sessao.urlPadrao}js/admin/login.js"></script>
</body>

</html>