<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<template:admin>

<!--Inicio Menu Slide Produto ........................................................................................ -->
<style>
  .slider .bar{
    width: 50%;
    height: 5px;
    background: #9448AE;
    border-radius: 5px;
  }
  
  .slidemenu label{
    width: 50%;
    text-align: center;
    display: block;
    float: left;
    opacity: 0.2;
    color: #666; 
  }  
 
  .slidemenu{
    font-family: arial, sans-serif;
    max-width: 300px;
    overflow: hidden;
  }

  .slidemenu #slide-item-1:checked ~ .slider .bar{ margin-left: 0; }
  .slidemenu #slide-item-2:checked ~ .slider .bar{ margin-left: 50%; }
</style>
<!--Fim Menu Slide Produto ........................................................................................... -->



<div class="pl-4">
	
	<div id="multt-menu-editar-produto">
	   <nav class="slidemenu fonte-padrao">	
	      <input type="radio" name="slideItem" id="slide-item-1" data-container-id="#cursos" class="slide-toggle" checked>
	      <label for="slide-item-1"><span>Cursos</span></label>
	      
	      <input type="radio" name="slideItem" id="slide-item-2"  data-container-id="#escolas" class="slide-toggle">
	      <label for="slide-item-2"><span>Escolas</span></label>

	      <div class="slider">
	          <div class="bar"></div>
	      </div>		
	   </nav>
	</div>
		
	<div class="mt-4 card card-body">
		<c:if test="${not empty produto.id}">
			<input type="hidden" name="produto.id" id="produtoId" value="${produto.id}" />
		</c:if>
		
		<div class="header header-padrao-multt titulo-de-formulario p-0">
			<div class="container-fluid fonte-padrao p-0">
				<div class="d-flex justify-content-between align-items-center ">
					<div>
						<h1 class="header-title titulo-index-page">
							Área de Membros
						</h1>
						<div class="multt-titulo-line-padrao"></div>							
					</div>
				</div>
			</div>
		</div>
		
		<div id="cursos" class="multt-form-menu container-ativo">		
	   		<div clas="multt-container-cursos row">
	   			<hr/>
	   			<h3 class="header-title titulo-index-page">
					Cursos
				</h3>
				<hr/>
	   			<div class="col-md-4">   				
	   				<form id="produtoCursoForm">
						<div class="form-row">
							<div class="form-group col-md-6">
								<input type="text" name="buscaPesquisa" class="form-control" id="nome" placeholder="Buscar...">
							</div>			
			
							 <div class="form-group col-md-4 form-group-button" >
						   		<button type="button" class="btn btn-outline-secondary btn-circle empty-form-paginate ml-1 mr-1" data-toggle="tooltip" data-placement="top" title="Limpar filtro"><i class="fas fa-redo-alt"></i></button>
					   	    	<button class="btn btn-light-rose btn-circle ml-1 mr-1" type="submit"><i class="fas fa-filter"></i></button>
			                 </div>
			             </div>
					</form>
	   			</div>
	   			<div class="col-md-12">
					<div class="table-responsive table-index-container">
						<table class="table table-nowrap" id="produtoCursos"></table>
					</div>			
	   			</div>
	   		</div>
		</div>
			
		<div id="escolas" class="multt-form-menu">			
	   		<div clas="multt-container-escolas">
	   			<hr/>
	   			<h3 class="header-title titulo-index-page">
					Escola
				</h3>
				<hr/>
	   		</div>
		</div>	
	</div>
</div>


</template:admin>