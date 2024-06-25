<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<template:admin>

<!--Inicio Menu Slide Produto ........................................................................................ -->
<style>
  .slider .bar{
    width: 25%;
    height: 5px;
    background: #9448AE;
    border-radius: 5px;
  }
  
  .slidemenu label{
    width: 25%;
    text-align: center;
    display: block;
    float: left;
    opacity: 0.2;
    color: #666; 
  }  
 
  .slidemenu{
    font-family: arial, sans-serif;
    max-width: 500px;
    overflow: hidden;
  }

  .slidemenu #slide-item-1:checked ~ .slider .bar{ margin-left: 0; }
  .slidemenu #slide-item-2:checked ~ .slider .bar{ margin-left: 25%; }
  .slidemenu #slide-item-3:checked ~ .slider .bar{ margin-left: 50%; }
  .slidemenu #slide-item-4:checked ~ .slider .bar{ margin-left: 75%; }
</style>
<!--Fim Menu Slide Produto ........................................................................................... -->



<div class="pl-4">
	
	<div id="multt-menu-editar-produto">
	   <nav class="slidemenu fonte-padrao">	
	      <input type="radio" name="slideItem" id="slide-item-1" data-container-id="#conteudo" class="slide-toggle" checked>
	      <label for="slide-item-1"><span>Conteúdo</span></label>
	      
	      <input type="radio" name="slideItem" id="slide-item-2"  data-container-id="#alunos" class="slide-toggle">
	      <label for="slide-item-2"><span>Alunos</span></label>
	      
	      <input type="radio" name="slideItem" id="slide-item-3"  data-container-id="#turmas" class="slide-toggle">
	      <label for="slide-item-3"><span>Turmas</span></label>
	      
	      <input type="radio" name="slideItem" id="slide-item-4"  data-container-id="#config" class="slide-toggle">
	      <label for="slide-item-4"><span>Configurações</span></label>

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
							Produto: ${produto.nome}
						</h1>
						<div class="multt-titulo-line-padrao"></div>							
					</div>
				</div>
			</div>
		</div>
		
		<div id="conteudo" class="multt-form-menu container-ativo">		
	   		<div clas="multt-container-cursos row">
	   			<hr/>
	   				<div class="d-flex justify-content-between align-items-center">
		   				<h3 class="header-title titulo-index-page"> Módulos </h3>
		   				<div>
			   				<button class="btn btn-circle btn-primary"> <i class="fas fa-plus"></i> </button>
			   				<button class="btn btn-circle btn-primary"> <i class="far fa-trash-alt"></i> </button>
			   				<!-- <button class="btn btn-circle btn-primary"> <i class="fas fa-compress"></i> </button>
			   				<button class="btn btn-circle btn-primary"> <i class="fas fa-expand"></i> </button>
			   				<button class="btn btn-circle btn-primary"> <i class="fas fa-cloud-upload"></i> </button>		   				
			   				<button class="btn btn-circle btn-primary"> <i class="far fa-eye"></i> </button> -->
		   				</div>					
	   				</div>
		   			
				<hr/>
	   			<div class="col-md-12">
	   				<div class="accordion" id="accordionExample">
					  <div class="card">
					    <div class="card-header" id="headingOne" style="background-color:#d2d6dc">
				    		<div style="width:100%" class="d-flex justify-content-between align-items-center">
				          		<h3><i class="fas fa-bars mr-2"></i><input type="checkbox" class="mr-2"/> M1</h3>					          		
				          	</div>
					      	<h2 class="mb-0">
					      		
						        <a href="#" class="btn btn-circle btn-light mr-2" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
						          		<i class="far fa-expand"></i>
						        </a>
						        
						        <!-- <a href="#" class="btn btn-circle btn-light mr-2" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
						          		<i class="far fa-compress"></i>
						        </a> -->
						        
						        <a href="#" class="btn btn-primary" type="button">
						          	Conteúdo <i class="far fa-plus ml-2"></i>
						        </a>
					      	</h2>
					    </div>
					
					    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
					      <div class="card-body">
					      	<table class="table">
					      		<tbody>
					      			<tr>     				
					      				<td style="width:100%">		      				
						      				<i class="fas fa-bars mr-2"></i>
						      				<input type="checkbox" class="mr-2"/> Nome 
					      				</td>
					      				<td> <h4><span class="badge badge-success">Publicado</span></h4> </td>
					      				<td class="d-flex"> 
					      					<button class="btn btn-circle btn-secondary mr-2"> <i class="fal fa-pencil-alt"></i> </button>
					      					<button class="btn btn-circle btn-danger"> <i class="fas fa-times"></i> </button> 
					      				</td>
					      			</tr>	
					      			
					      			
					      					      			
					      		</tbody>
					      	</table>
					      </div>
					    </div>
					  </div>
					  					  
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