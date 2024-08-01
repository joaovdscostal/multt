<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<c:if test="${not empty matricula.id}">
    <input type="hidden" name="matricula.id" value="${matricula.id}" />
</c:if>

<c:if test="${not empty matricula.aluno.id}">
    <input type="hidden" name="matricula.aluno.id" value="${matricula.aluno.id}" />
</c:if>

<div class="card-body card-padding">
	<div class="form-row">
		
		<c:if test="${not empty matricula.aluno.id}">
			<div class="form-group col-md-12">
		        <label for="matriculaNome" class="control-label">Nome</label>
		        <input type="text" id="matriculaNomeDisplay" disabled="disabled" class="form-control" value="${matricula.aluno.nome}"/>
		    </div>
		</c:if>
		
		<c:if test="${empty matricula.aluno.id}">
			<div class="form-group col-md-12">
		        <label for="matriculaNome" class="control-label">Nome</label>
		        <input type="text" name="matricula.nomeAluno" id="matriculaNome" class="form-control required" value="${matricula.aluno.nome}"/>
		    </div>
		</c:if>
		
		<c:if test="${not empty matricula.aluno.id}">
			<div class="form-group col-md-12">
		        <label for="matriculaEmail" class="control-label">Email</label>
		        <input type="text" id="matriculaEmailDisplay" disabled="disabled" class="form-control" value="${matricula.aluno.email}"/>
		    </div>
		</c:if>
		
		<c:if test="${empty matricula.aluno.id}">
			<div class="form-group col-md-12">
		        <label for="matriculaEmail" class="control-label">Email</label>
		        <input type="text" name="matricula.emailAluno" id="matriculaEmail" class="form-control required" value="${matricula.aluno.email}"/>
		    </div>
		</c:if>
	
	    <div class="form-group col-md-12">
			<label for="matriculaTurma" class="control-label"> Turma </label>
			<select name="matricula.turma.id" class="form-control required" data-toggle="select" id="matriculaTurma" >
				<option value="">-</option>
				<c:forEach items="${turmas}" var="turma">
					<option value="${turma.id}" ${matricula.turma.id == turma.id ? 'selected' : ''} >
							${turma.nome}
					</option>
				</c:forEach>
			</select>
		</div>
		
	</div>

    <div class="form-group">
        <c:if test="${empty matricula.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty matricula.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>