<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<template:admin>

<!--Inicio Menu Slide Produto ........................................................................................ -->
<style>
  .slider .bar{
    width: 33.3%;
    height: 5px;
    background: #9448AE;
    border-radius: 5px;
  }
  
  .slidemenu label{
    width: 33.3%;
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
  
  @media (max-width: 550px) {
	      .slidemenu{	    
		    max-width: 90vw;
		  }		
		  
		  .slidemenu label span{
		    display: block;    
		    font-size: 12px;
		    font-weight: 600;
		  }
		  
	}
  
  .mult-area-membros-container{
  	padding-left: 10px;
  	padding-right: 10px;
  }

  .slidemenu #slide-item-1:checked ~ .slider .bar{ margin-left: 0; }
  .slidemenu #slide-item-2:checked ~ .slider .bar{ margin-left: 33.3%; }
  .slidemenu #slide-item-3:checked ~ .slider .bar{ margin-left: 66.6%; }
  /*.slidemenu #slide-item-4:checked ~ .slider .bar{ margin-left: 75%; }*/
</style>
<!--Fim Menu Slide Produto ........................................................................................... -->



<div class="mult-area-membros-container">
	
	<div id="multt-menu-editar-produto">
	   <nav class="slidemenu fonte-padrao">	
	   		
	   	  <input type="radio" name="slideItem" id="slide-item-1"  data-container-id="#turmas" class="slide-toggle" checked>
	      <label for="slide-item-1"><span>Turmas</span></label>
	      
	      <input type="radio" name="slideItem" id="slide-item-2"  data-container-id="#alunos" class="slide-toggle">
	      <label for="slide-item-2"><span>Alunos</span></label>
	   
	      <input type="radio" name="slideItem" id="slide-item-3" data-container-id="#conteudo" class="slide-toggle" >
	      <label for="slide-item-3"><span>Conteúdo</span></label>
	      
	      <!-- <input type="radio" name="slideItem" id="slide-item-4"  data-container-id="#config" class="slide-toggle">
	      <label for="slide-item-4"><span>Config</span></label> -->

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
		
		<div id="turmas" class="multt-form-menu container-ativo">			
	   		<div clas="multt-container-escolas">
	   			<hr/>
   				<div class="d-flex justify-content-between align-items-center">
		   			<h3 class="header-title titulo-index-page">
						Turmas
					</h3>
					<div>
						<button class="btn btn-circle btn-primary add-turma"> <i class="fas fa-plus"></i> </button>
					</div>
				</div>
				<hr/>
				<div class="table-responsive" id="turmas-container">
					<table class="table table-nowrap" id="turmaDatatableMembros"></table>
				</div> 
	   		</div>
		</div>	
		
		<div id="alunos" class="multt-form-menu">			
	   		<div clas="multt-container-escolas">
	   			<hr/>
   				<div class="d-flex justify-content-between align-items-center">
		   			<h3 class="header-title titulo-index-page">
						Alunos
					</h3>
					<div>
						<button class="btn btn-circle btn-primary add-matricula"> <i class="fas fa-plus"></i> </button>
					</div>
				</div>
				<hr/>
				<div class="row">
					<div class="col-12 col-lg-4">
						<div class="card card-multt">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col">
										<h6 class="text-uppercase mb-2">Número de Alunos</h6>
										<span class="h2 mb-0 text-primary" id="totalAlunos"></span>
									</div>
									<div class="col-auto">
										<span class="h2 fal fas fa-users mb-0"></span>
									</div>
									<div class="col-12 mt-3 text-left">
										<span class="h2 mb-0" id="alunoQuantidade">1</span>
									</div>
									<div class="col-12 text-left">
										<p style="font-size: 10px" class="m-0"> Alunos Matriculados </p>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-12 col-lg-4">
						<div class="card card-multt">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col">
										<h6 class="text-uppercase mb-2">Progressão</h6>
										<span class="h2 mb-0 text-primary" id="alunosMediaProgressao"></span>
									</div>
									<div class="col-auto">
										<span class="h2 fal fas fa-tasks mb-0"></span>
									</div>
									<div class="col-12 mt-3 text-left">
										<span class="h2 mb-0" id="alunoProgressao"><fmt:formatNumber minFractionDigits="2" value = "20" />%</span>
									</div>
									<div class="col-12 text-left">
										<p style="font-size: 10px" class="m-0"> Média de Progressão </p>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-12 col-lg-4">
						<div class="card card-multt">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col">
										<h6 class="text-uppercase mb-2">Conclusão</h6>
										<span class="h2 mb-0 text-primary" id="alunosProgressaoConcluidos"></span>
									</div>
									<div class="col-auto">
										<span class="h2 fal fas fa-check-circle mb-0"></span>
									</div>
									<div class="col-12 mt-3 text-left">
										<span class="h2 mb-0" id="alunoProgressaoConcluido"><fmt:formatNumber minFractionDigits="2" value = "20"/>%</span>
									</div>
									<div class="col-12 text-left">
										<p style="font-size: 10px" class="m-0"> Concluíram o Curso </p>
									</div>
								</div>
							</div>
						</div>
					</div>						
				</div>
				<hr/>
				<div class="table-responsive" id="alunos-container">
					<table class="table table-nowrap" id="alunosDatatableMembros"></table>
				</div> 
	   		</div>
		</div>	
		
		<div id="conteudo" class="multt-form-menu ">		
	   		<div clas="multt-container-cursos row">
	   			<hr/>
	   				<div class="d-flex justify-content-between align-items-center">
		   				<h3 class="header-title titulo-index-page"> Módulos </h3>
		   				<div>
			   				<button class="btn btn-circle btn-primary add-modulo"> <i class="fas fa-plus"></i> </button>
			   				<button class="btn btn-circle btn-danger"> <i class="far fa-trash-alt"></i> </button>
			   				<!-- <button class="btn btn-circle btn-primary"> <i class="fas fa-compress"></i> </button>
			   				<button class="btn btn-circle btn-primary"> <i class="fas fa-expand"></i> </button>
			   				<button class="btn btn-circle btn-primary"> <i class="fas fa-cloud-upload"></i> </button>		   				
			   				<button class="btn btn-circle btn-primary"> <i class="far fa-eye"></i> </button> -->
		   				</div>					
	   				</div>
				<hr/>			
				<div class="container-responsive" id="modulos-container"></div>															
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