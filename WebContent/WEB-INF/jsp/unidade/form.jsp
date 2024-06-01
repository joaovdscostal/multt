<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty unidade.id}">
    <input type="hidden" name="unidade.id" value="${unidade.id}" />
    <input type="hidden" name="unidade.urlCurta" value="${unidade.urlCurta}" />
</c:if>


<div class="card-body card-padding">
    <div class="form-group">
        <label for="unidadeNome" class="control-label">Nome</label>
        <input type="text" name="unidade.nome" id="unidadeNome" class="form-control required" value="${unidade.nome}" />
    </div>
    <div class="form-group">
        <label for="unidadeEndereco" class="control-label">Endere&ccedil;o</label>
        <textarea name="unidade.endereco" id="unidadeEndereco" class="form-control">${unidade.endereco}</textarea>
    </div>

    <div class="form-group custom-control custom-switch">
        <input name="unidade.ativo" type="checkbox" class="custom-control-input" id="ativo" <c:if
            test="${empty unidade.id || unidade.ativo == true}">checked="checked"</c:if> >
        <label class="custom-control-label" for="ativo">Ativo?</label>
    </div>

    <div class="form-group">
        <c:if test="${empty unidade.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
        </c:if>
        <c:if test="${not empty unidade.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>