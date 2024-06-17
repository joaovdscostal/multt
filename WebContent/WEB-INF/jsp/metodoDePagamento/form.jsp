<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty metododepagamento.id}">
    <input type="hidden" name="metododepagamento.id" value="${metododepagamento.id}" />
</c:if>

	
	<div class="form-row form-multt-padrao">
    	<div class="form-group  col-md-6  ">
	        <label for="metododepagamentoNome" class="control-label">Nome</label>
	        <input type="text" name="metododepagamento.nome" id="metododepagamentoNome" class="form-control required "  value="${metododepagamento.nome}"    />
	    </div>
	    
	    <div class="form-group col-md-6">
	        <label for="portadorMetodoDePagamento" class="control-label">Tipo</label>
	        <select name="metododepagamento.portador" id="portadorMetodoDePagamento" class="form-control">
		    	<option value=""> - </option>
		    	<c:forEach items="${portadorTipoFormaPagamentoList}" var="tipo">
		    		<option value="${tipo}"> ${tipo.descricao} </option>
		    	</c:forEach>
		    </select>
	    </div>
	    
	    <div class="form-group col-md-4">
	        <label for="maxParcelasMetodoDePagamento" class="control-label">Máximo de Parcelas</label>
	        <input type="text" name="metododepagamento.maximoDeParcelas" id="maxParcelasMetodoDePagamento" class="form-control required"  value="${metododepagamento.maximoDeParcelas}" alt="numero"/>
	    </div>
	    
	    <div class="form-group col-md-4">
	        <label for="taxaApiMetodoDePagamento" class="control-label">Taxa Api</label>
	        <input type="text" name="metododepagamento.taxaApi" id="taxaApiMetodoDePagamento" class="form-control required"  value="${metododepagamento.taxaApi}" alt="decimalSemZero"/>
	    </div>
	    
	    <div class="form-group col-md-4">
	        <label for="descontoePagamento" class="control-label">Desconto</label>
	        <input type="text" name="metododepagamento.desconto" id="descontoePagamento" class="form-control required"  value="${metododepagamento.desconto}" alt="decimalSemZero"/>
	    </div>
	    

	</div>

    <div class="form-group custom-control custom-switch">
        <input name="metododepagamento.ativo" type="checkbox" class="custom-control-input " id="metododepagamentoAtivo"
         	<c:if test="${empty metododepagamento.ativo || metododepagamento.ativo == true}">checked="checked"</c:if> >
        <label class="custom-control-label" for="metododepagamentoAtivo">Ativo?</label>
    </div>

    <div class="form-group">
        <c:if test="${empty metododepagamento.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty metododepagamento.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>