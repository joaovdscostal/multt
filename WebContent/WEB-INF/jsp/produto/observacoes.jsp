<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:admin>
	<div class="header">
		<div class="container-fluid">
			<div class="header-body">
				<div class="row align-items-end">
					<div class="col">
						<h6 class="header-pretitle">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="${sessao.urlPadrao}adm">Home</a></li>
									<li class="breadcrumb-item"><a href="${sessao.urlPadrao}adm/produtos">Produtos</a></li>
									<li class="breadcrumb-item active" aria-current="page">${produto.nome}</li>

								</ol>
							</nav>
						</h6>
						<h1 class="header-title">
							Observacoes do ${produto.nome}
						</h1>
					</div>
					<div class="col-auto">

                    <!-- Buttons -->
                    <a href="javascript:void(0)" id="button-add-Observacao" data-id="${produto.id}" class="btn btn-primary ml-2">
                      Adicionar Observacoes
                    </a>

                  </div>
				</div>
			</div>
		</div>
	</div>
<div class="card-body card-padding">

	<input type="hidden" id="idProduto" value="${produto.id}">

	<div class="tab-content">
              <div class="tab-pane fade active show" id="ObservacaoList" role="tabpanel" aria-labelledby="contactsListTab">

                <!-- Card -->
                <div class="card" style="" id="contactsList">
                  <div class="table-responsive">
                    <table class="table table-sm table-hover table-nowrap card-table">
                      <thead>
                        <tr>
                          <th width="4%">
                          </th>

                          	 <th>
                          	<a class="text-muted" data-sort="item-name" href="#">Nome</a>
                          	</th>

                          <th width="10px">
                          #
                          </th>
                        </tr>
                      </thead>
                      <tbody class="list font-size-base" id="ObservacaoContent" data-id="${produto.id}">


                        </tbody>
                    </table>
                  </div>
                </div>

              </div>

            </div>

</div>
</template:admin>