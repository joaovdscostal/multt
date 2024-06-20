<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty checkout.id}">
    <input type="hidden" name="checkout.id" value="${checkout.id}" />
</c:if>


<div class="card-body card-padding">
	<div class="form-row">
    	<div class="form-group  col-md-6">
	        <label for="checkoutNome" class="control-label">Nome</label>
	        <input type="text" name="checkout.nome" id="checkoutNome" class="form-control required"  value="${checkout.nome}"    />
	    </div>
	    <div class="form-group  col-md-6">
	        <label for="checkoutBanner" class="control-label">Banner</label>
	        <input type="file" name="banner" id="checkoutBanner" class="form-control"/>
	    </div>
	</div>
	<hr/>
	<div class="header header-padrao-multt titulo-de-formulario p-0">
		<div class="container-fluid fonte-padrao p-0">
			<div class="d-flex justify-content-between align-items-center justify-content-between">
				<div>
					<h1 class="header-title titulo-index-page">
						Ofertas
					</h1>
					<div class="multt-titulo-line-padrao"></div>							
				</div>
			</div>
		</div>
	</div>	
	<div class="checkout-ofertas-container">
		<table class="table">
			<thead>
				<tr>
					<th> Selecionar </th>
					<th> Oferta </th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty listaOfertas}">
	    			<c:forEach items="${listaOfertas}" var="oferta" varStatus="status">
	    				<tr>
							<td>
								<input name="ofertasDTO[${status.count}].selected" type="checkbox" data-oferta-id="${oferta.id}" class="verificar-disponibilidade-oferta"/>
								<input name="ofertasDTO[${status.count}].oferta.id" type="hidden" value="${oferta.id}"/>
							</td>
							<td>${oferta.nome}</td>
						</tr>
	    			</c:forEach>
    			</c:if>	
    			<c:if test="${empty listaOfertas}">
    				<tr>
					 	<td colspan="3" class="text-center">Nenhum Checkout Cadastrado</td>
					</tr>
    			</c:if>	
				
			</tbody>
		</table>
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