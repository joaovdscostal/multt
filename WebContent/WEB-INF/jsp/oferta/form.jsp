<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<c:if test="${not empty oferta.id}">
    <input type="hidden" name="oferta.id" value="${oferta.id}" />


</c:if>


<div class="card-body card-padding">



	<div class="form-row">



		    			    <div class="form-group  col-md-12  ">
			        <label for="ofertaNome" class="control-label">Nome</label>
			        <input type="text" name="oferta.nome" id="ofertaNome" class="form-control required "  value="${oferta.nome}"    />
			    </div>

	</div>




		    <div class="form-group custom-control custom-switch">
		        <input name="oferta.ativo" type="checkbox" class="custom-control-input " id="ofertaAtivo"
		         	<c:if test="${empty oferta.ativo || oferta.ativo == true}">checked="checked"</c:if> >
		        <label class="custom-control-label" for="ofertaAtivo">Ativo?</label>
		    </div>


    <div class="form-group">
        <c:if test="${empty oferta.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty oferta.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>