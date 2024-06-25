<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<c:if test="${not empty matricula.id}">
    <input type="hidden" name="matricula.id" value="${matricula.id}" />


</c:if>


<div class="card-body card-padding">



	<div class="form-row">



		    



		    



		    		    	<div class="form-group   col-md-12 ">
					<label for="matriculaStatusmatricula" class="control-label"> Status</label>
					<select name="matricula.statusMatricula" class="form-control required " data-toggle="select" id="matriculaStatusmatricula" >
						<option value="">-</option>

						<c:forEach items="${statusMatriculaStatusMatriculaList}" var="statusMatricula">
							<option value="${statusMatricula}" ${matricula.statusMatricula==statusMatricula ? 'selected' : ''} >
									${statusMatricula.descricao}
							</option>
						</c:forEach>
					</select>
				</div>




		    			    <div class="form-group  col-md-12  ">
			        <label for="matriculaData" class="control-label">data</label>
			        <input type="text" name="matricula.data" id="matriculaData" class="form-control required  custom-date-time " alt="datetime"    value="<fmt:formatDate value="${matricula.data}" pattern="dd/MM/yyyy HH:mm"/>" />
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