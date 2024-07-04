<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty turma.id}">
    <input type="hidden" name="turma.id" value="${turma.id}"/>
</c:if>

<c:if test="${not empty produto.id}">
    <input type="hidden" name="produto.id" value="${produto.id}"/>
</c:if>

<div class="card-body card-padding">
	<div class="form-row">
    	<div class="form-group  col-md-12  ">
	        <label for="turmaValor" class="control-label">Valor</label>
	        <input type="text" name="turma.valor" id="turmaValor" class="form-control required " alt="decimalSemZero" value="${turma.valor}"/>
	    </div>
	    
	    <div class="form-group  col-md-12">
	        <label for="turmaOfertas" class="control-label"> Ofertas </label>
			<select name="turma.ofertas[].id" class="form-control custom-select required" multiple="multiple"  data-toggle="select" id="turmaOfertas">
				<c:forEach items="${produto.ofertas}" var="oferta">
					<option value="${oferta.id}" ${fn:contains(turma.ofertas, oferta) ? 'selected' : ''}>
							${oferta.nome}
					</option>
				</c:forEach>
			</select>
	    </div>   
	</div>

    <div class="form-group">
        <c:if test="${empty turma.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty turma.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>