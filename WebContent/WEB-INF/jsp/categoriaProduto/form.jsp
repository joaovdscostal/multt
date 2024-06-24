<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<c:if test="${not empty categoriaproduto.id}">
    <input type="hidden" name="categoriaproduto.id" value="${categoriaproduto.id}" />
</c:if>
	
<div class="form-row form-multt-padrao">
   	<div class="form-group  col-md-3  ">
        <label for="categoriaprodutoNome" class="control-label">Nome</label>
        <input type="text" name="categoriaproduto.nome" id="categoriaprodutoNome" class="form-control required "  value="${categoriaproduto.nome}"    />
    </div>
</div>

   <div class="form-group custom-control custom-switch">
       <input name="categoriaproduto.ativo" type="checkbox" class="custom-control-input " id="categoriaprodutoAtivo"
        	<c:if test="${empty categoriaproduto.ativo || categoriaproduto.ativo == true}">checked="checked"</c:if> >
       <label class="custom-control-label" for="categoriaprodutoAtivo">Ativo?</label>
   </div>

   <div class="form-group">
       <c:if test="${empty categoriaproduto.id}">
           <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
           <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
       </c:if>
       <c:if test="${not empty categoriaproduto.id}">
           <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
       </c:if>
   </div>
