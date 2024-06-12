<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<c:if test="${not empty condicaodepagamento.id}">
    <input type="hidden" name="condicaodepagamento.id" value="${condicaodepagamento.id}" />


</c:if>


<div class="card-body card-padding">



	<div class="form-row">



		    			    <div class="form-group  col-md-12  ">
			        <label for="condicaodepagamentoNome" class="control-label">Nome</label>
			        <input type="text" name="condicaodepagamento.nome" id="condicaodepagamentoNome" class="form-control required "  value="${condicaodepagamento.nome}"    />
			    </div>

	</div>




		    <div class="form-group custom-control custom-switch">
		        <input name="condicaodepagamento.ativo" type="checkbox" class="custom-control-input " id="condicaodepagamentoAtivo"
		         	<c:if test="${empty condicaodepagamento.ativo || condicaodepagamento.ativo == true}">checked="checked"</c:if> >
		        <label class="custom-control-label" for="condicaodepagamentoAtivo">Ativo?</label>
		    </div>


    <div class="form-group">
        <c:if test="${empty condicaodepagamento.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty condicaodepagamento.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>