<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:admin>

<form class="validateForm load form-multt-padrao" action="${sessao.urlPadrao}adm/contas/editar" method="post" role="form" enctype="multipart/form-data"> 
	<c:if test="${not empty conta.id}">
	    <input type="hidden" name="conta.id" value="${conta.id}" />
	</c:if>
	
	<div class="header header-padrao-multt titulo-de-formulario">
		<div class="container-fluid fonte-padrao">
			<div class="d-flex justify-content-between align-items-center ">
				<div>
					<h1 class="header-title titulo-index-page">
						Editar Conta
					</h1>
					<div class="multt-titulo-line-padrao"></div>
					<div class="multt-sub-header-title">
						Aprenda sobre <a href="#">Contas</a> no checkout
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="card-body card-padding">
		<div class="form-row">
			<div class="form-group col-md-12 d-flex align-items-end" >
				<div class="d-flex input-file-field align-items-center">
					<div class="file-img-container">
						<c:if test="${not empty conta.imagem and not empty conta.id}">
							<img src="${sessao.urlPadrao}arquivos/contas/perfil_${conta.id}/${conta.imagem}">
						</c:if>
						<c:if test="${empty conta.imagem and empty conta.id}">
							<img src="${sessao.urlPadrao}img/sem_perfil.png">
						</c:if>
					</div>
					<div class="file-input-container">
						<label for="imagemJogo" class="custom-file-upload m-0 btn btn-primary"> Alterar </label>
						<input type="file" data-aceita-video="false" name="imagem" id="imagemJogo" style="display:none"/>
					</div>
				</div>
			</div>
	    	<div class	"form-group col-md-3">
		        <label for="contaNome" class="control-label">Nome</label>
		        <input type="text" name="conta.nome" id="contaNome" class="form-control required"  value="${conta.nome}"    />
		    </div>
		    <div class="form-group col-md-3">
		        <label for="contaNome" class="control-label">Email</label>
		        <input type="text" name="conta.email" id="contaEmail" class="form-control required" readonly="readonly" value="${conta.email}"    />
		    </div>
		    <div class="form-group col-md-3">
		        <label for="contaNome" class="control-label">Celular</label>
		        <input type="text" name="conta.celular" id="contaCelular" class="form-control required"  alt="phone" value="${conta.celular}"    />
		    </div>
		</div>
		<div class="header header-padrao-multt titulo-de-formulario">
		
		<div class="container-fluid fonte-padrao">
				<div class="d-flex justify-content-between align-items-center ">
					<div>
						<h1 class="header-title titulo-index-page">
								Endereço
						</h1>
						<div class="multt-titulo-line-padrao"></div>
						<div class="multt-sub-header-title">
							Aprenda sobre <a href="#">Endereços</a> no checkout
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>CEP</label> <input type="text"
							class="form-control cep " alt="cep"
							name="conta.endereco.cep" value="${conta.endereco.cep}" />
					</div>
				</div>
				<div class="col-md-10">
					<div class="form-group">
						<label>Endereço</label> <input type="text"
							class="form-control rua " name="conta.endereco.rua"
							value="${conta.endereco.rua}" />
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label>N&uacute;mero</label> <input type="text"
							class="form-control " name="conta.endereco.numero"
							value="${conta.endereco.numero}" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Complemento</label> <input type="text" class="form-control"
							name="conta.endereco.complemento"
							value="${conta.endereco.complemento}" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Bairro</label> <input type="text"
							class="form-control bairro "
							name="conta.endereco.bairro" value="${conta.endereco.bairro}" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Cidade</label> <input type="text"
							class="form-control cidade "
							name="conta.endereco.cidade" value="${conta.endereco.cidade}" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Estado</label> 
						<select class="form-control uf" name="conta.endereco.estado">
							<option value="">Selecione um estado</option>
							<c:forEach items="${estadoList}" var="estado">
								<option value="${estado}"
									<c:if test="${conta.endereco.estado == estado}">selected="selected"</c:if>>
									${estado.descricao}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		
	    <div class="form-group">
	        <c:if test="${empty conta.id}">
	            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
	            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
	        </c:if>
	        <c:if test="${not empty conta.id}">
	            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
	        </c:if>
	    </div>
	</div>
</form>

</template:admin>

