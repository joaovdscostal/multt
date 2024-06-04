<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty usuario.id}">
    <input type="hidden" name="usuario.id" value="${usuario.id}" />
</c:if>

<div class="header header-padrao-multt titulo-de-formulario">
	<div class="container-fluid fonte-padrao">
		<div class="d-flex justify-content-between align-items-center ">
			<div>
				<h1 class="header-title titulo-index-page">
					Informações do Usu&aacute;rio
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
	
<div class="row form-multt-padrao">
    <div class="form-group col-md-4">
        <label for="usuarioNome" class="control-label">Nome</label>
        <input type="text" name="usuario.nome" id="usuarioNome" class="form-control required" value="${usuario.nome}" />
    </div>

    <div class="form-group col-md-4">
        <label for="usuarioEmail" class="control-label">Email</label>
        <input type="text" name="usuario.email" id="usuarioEmail" class="form-control required" value="${usuario.email}" />
    </div>

    <div class="form-group col-md-4">
		<label for="usuarioTipo" class="control-label">Tipo</label>
		<select name="usuario.tipo" class="form-control" data-toggle="select" id="usuarioTipo" required="required">
			<option value="">-</option>
			<c:forEach items="${tipoUsuarioList}" var="tipo">
				<option value="${tipo}" ${tipo == usuario.tipo ? 'selected' : ''}>
					${tipo.exibicao}
				</option>
			</c:forEach>
		</select>
	</div>
</div>

<div class="header header-padrao-multt titulo-de-formulario">
	<div class="container-fluid fonte-padrao">
		<div class="d-flex justify-content-between align-items-center ">
			<div>
				<h1 class="header-title titulo-index-page">
					Dados de Acesso
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

<div class="row form-multt-padrao">

    <div class="form-group col-md-4">
        <label for="usuarioLogin" class="control-label">Login</label>
        <input type="text" name="usuario.login" id="usuarioLogin" class="form-control required" value="${usuario.login}" />
    </div>

    <c:if test="${empty usuario.id}">
        <div class="form-group col-md-4">
            <label for="usuarioSenha" class="control-label">Senha</label>
            <input type="password" name="usuario.senha" id="usuarioSenha" class="form-control required" value="${usuario.senha}"/>
        </div>
        <div class="form-group col-md-4">
            <label for="usuarioSenhaConfirmacao" class="control-label">Confirmar senha</label>
            <input type="password" name="usuario.senhaConfirmacao" id="usuarioSenhaConfirmacao"
                class="form-control required" value="${usuario.senhaConfirmacao}" />
        </div>
    </c:if>
</div>

<div class="form-group custom-control custom-switch">
   <input name="usuario.ativo" type="checkbox" class="custom-control-input" id="ativo" <c:if
       test="${empty usuario.id || usuario.ativo == true}">checked="checked"</c:if> >
    <label class="custom-control-label" for="ativo">Ativo?</label>
</div>

<div class="form-group">
    <c:if test="${empty usuario.id}">
       <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
   </c:if>
   <c:if test="${not empty usuario.id}">
       <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
   </c:if>
</div>