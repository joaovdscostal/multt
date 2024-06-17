<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<c:if test="${not empty checkout.id}">
    <input type="hidden" name="checkout.id" value="${checkout.id}" />


</c:if>


<div class="card-body card-padding">



	<div class="form-row">



		    			    <div class="form-group  col-md-12  ">
			        <label for="checkoutNome" class="control-label">Nome</label>
			        <input type="text" name="checkout.nome" id="checkoutNome" class="form-control required "  value="${checkout.nome}"    />
			    </div>

	</div>






    <div class="form-group">
        <c:if test="${empty checkout.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty checkout.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>