<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:admin>
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-12 col-lg-10 col-xl-8">
				<div class="header mt-md-5">
					<div class="header-body">
						<div class="row align-items-center">
							<div class="col">
								<h6 class="header-pretitle">
									Site utilit&aacute;rios
								</h6>

								<h1 class="header-title">
									Configura&ccedil;&otilde;es
								</h1>
							</div>
						</div>
						<div class="row align-items-center">
							<div class="col">
								<ul class="nav nav-tabs nav-overflow header-tabs" role="tablist">
									<li class="nav-item">
										<a href="#geral" class="nav-link active" data-toggle="tab">
											Geral
										</a>
									</li>
									<!-- <li class="nav-item">
										<a href="#endereco" class="nav-link" data-toggle="tab">
											Endere&ccedil;o
										</a>
									</li>
									<li class="nav-item">
										<a href="#social" class="nav-link" data-toggle="tab">
											Redes sociais
										</a>
									</li> -->
								</ul>
							</div>
						</div>
					</div>
				</div>

				<form class="mb-4" action="${sessao.urlPadrao}adm/configuracao" method="post">

					<input type="hidden" name="configuracao.id" value="${configuracao.id}" />
					<div class="tab-content">
						<div class="tab-pane active" id="geral" role="tabpanel">
							<div class="row">
								<div class="col-10">
									<div class="form-group">
										<label>Titulo do site</label>
										<input type="text" class="form-control" name="configuracao.nome"
											value="${configuracao.nome}">
									</div>
								</div>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary lift">
						Atualizar configura&ccedil;&atilde;o
					</button>
				</form>

			</div>
		</div>
	</div>
</template:admin>