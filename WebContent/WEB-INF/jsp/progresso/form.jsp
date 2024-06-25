<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<c:if test="${not empty progresso.id}">
    <input type="hidden" name="progresso.id" value="${progresso.id}" />


</c:if>


<div class="card-body card-padding">



	<div class="form-row">



		    



		    



		    
	</div>




		    <div class="form-group custom-control custom-switch">
		        <input name="progresso.assistido" type="checkbox" class="custom-control-input " id="progressoAssistido"
		         	<c:if test="${empty progresso.assistido || progresso.assistido == true}">checked="checked"</c:if> >
		        <label class="custom-control-label" for="progressoAssistido">Assistido</label>
		    </div>


    <div class="form-group">
        <c:if test="${empty progresso.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty progresso.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>