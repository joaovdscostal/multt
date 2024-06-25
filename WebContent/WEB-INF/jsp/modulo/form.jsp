<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<c:if test="${not empty modulo.id}">
    <input type="hidden" name="modulo.id" value="${modulo.id}" />

		<input type="hidden" name="modulo.ordem" value="${modulo.ordem}" />

</c:if>


<div class="card-body card-padding">



	<div class="form-row">



		    			    <div class="form-group  col-md-12  ">
			        <label for="moduloNome" class="control-label">Nome</label>
			        <input type="text" name="modulo.nome" id="moduloNome" class="form-control required "  value="${modulo.nome}"    />
			    </div>




		    			    <div class="form-group  col-md-12  ">
			        <label for="moduloOrdem" class="control-label">Ordem</label>
			        <input type="text" name="modulo.ordem" id="moduloOrdem" class="form-control required " alt="numero" value="${modulo.ordem}"    />
			    </div>




		    



		    



		    
	</div>




		    <div class="form-group custom-control custom-switch">
		        <input name="modulo.ativo" type="checkbox" class="custom-control-input " id="moduloAtivo"
		         	<c:if test="${empty modulo.ativo || modulo.ativo == true}">checked="checked"</c:if> >
		        <label class="custom-control-label" for="moduloAtivo">Ativo?</label>
		    </div>


    <div class="form-group">
        <c:if test="${empty modulo.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty modulo.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>