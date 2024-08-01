<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${not empty modulo.id}">
    <input type="hidden" name="modulo.id" value="${modulo.id}" />
	<input type="hidden" name="modulo.ordem" value="${modulo.ordem}" />
</c:if>

<c:if test="${not empty produto.id}">
	<input type="hidden" name="modulo.produto.id" value="${produto.id}" />
</c:if>

<div class="card-body card-padding">
	
	<div class="form-row">
    	<div class="form-group col-md-12">
	        <label for="moduloNome" class="control-label">Nome</label>
	        <input type="text" name="modulo.nome" id="moduloNome" class="form-control required" value="${modulo.nome}"/>
	    </div>        
	</div>
	<hr/>
	<div class="header header-padrao-multt titulo-de-formulario pt-0">
		<div class="container-fluid fonte-padrao p-0">
			<div class="d-flex justify-content-between align-items-center ">
				<div>
					<h2 class="header-title titulo-index-page">
						Turmas Liberadas
					</h2>
					<div class="multt-titulo-line-padrao"></div> 			
				</div>
			</div>
		</div>
	</div>
	
	<div class="turmas-modulo-container">
    	<div class="todas-as-turmas-select-item-modulo">
	       <input name="modulo.permitirTodasAsTurmas" type="checkbox" id="todasAsTurmas"/
	       ${modulo.permitirTodasAsTurmas == true ? 'checked="checked"' : ''}>
	       <label for="todasAsTurmas" class="control-label">
	       		<div class="select-turma-item-btn">
	       			Todas as Turmas
	       		</div>
	       </label>
	    </div>	        
	    <c:forEach items="${turmasDoProduto}" var="turma">
	    	<div class="todas-as-turmas-select-item-modulo" >
		       <input name="modulo.turmas[].id" type="checkbox" class="turma-selection" id="turma-${turma.id}" 
		       value="${turma.id}" ${fn:contains(modulo.turmas, turma) ? 'checked="checked"' : ''}/>
		       <label for="turma-${turma.id}" class="control-label">
		       		<div class="select-turma-item-btn">
		       			${turma.nome}
		       		</div>
		       </label>
		    </div>
	    </c:forEach>
	</div>
	<hr/>
    <div class="form-group custom-control custom-switch">
        <input name="modulo.ativo" type="checkbox" class="custom-control-input " id="moduloAtivo"
         	<c:if test="${empty modulo.ativo || modulo.ativo == true}">checked="checked"</c:if>>
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