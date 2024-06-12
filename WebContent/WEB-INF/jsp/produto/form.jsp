<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty produto.id}">
    <input type="hidden" name="produto.id" value="${produto.id}" />
</c:if>

<div class="card-body card-padding">
	<div class="form-row form-multt-padrao">
    	<div class="form-group  col-md-12">
	        <label for="produtoNome" class="control-label">Nome</label>
	        <input type="text" name="produto.nome" id="produtoNome" class="form-control required "  value="${produto.nome}"    />
	    </div>
	    
	     <div class="form-group col-md-12 ">
			<label for="produtoDescricao" class="control-label">Descrição</label>
			<textarea name="produto.descricao" id="produtoDescricao" class="form-control"></textarea>
		</div>
	    
	    <div class="form-group  col-md-12">
	        <label for="produtoPaginaDeVendasExterna" class="control-label">Página de Vendas</label>
	        <input type="text" name="produto.paginaDeVendasExterna" id="produtoPaginaDeVendasExterna" class="form-control required "  value=""/>
	    </div>
	    
	    <div class="form-group  col-md-6">
	        <label for="produtoValor" class="control-label">Valor do Produto</label>
	        <input type="text" name="produto.valor" id="produtoValor" class="form-control required "  value="" alt="decimalSemZero"/>
	    </div>
	    
	    <div class="form-group  col-md-6">
	        <label for="produtoTipoPagamento" class="control-label">Tipo de Pagamento</label>
	        <select name="produto.tipoPagamento" id="produtoTipoPagamento" class="form-control">
		    	<option value=""> - </option>
		    	<c:forEach items="${tipoPagamentoTipoPagamentoProdutoList}" var="tipo">
		    		<option value="${tipo}"> ${tipo.descricao} </option>
		    	</c:forEach>
		    </select>
	    </div>
	    
	</div>
	
	<div class="form-group custom-control custom-switch">
	   <input name="produto.ativo" type="checkbox" class="custom-control-input" id="ativo" <c:if
	       test="${empty produto.id || produto.ativo == true}">checked="checked"</c:if> >
	    <label class="custom-control-label" for="ativo">Ativo?</label>
	</div>

    <div class="form-group">
        <c:if test="${empty produto.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <!-- <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>  -->
        </c:if>
        <c:if test="${not empty produto.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>