<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty conteudo.id}">
    <input type="hidden" name="conteudo.id" value="${conteudo.id}" />
	<input type="hidden" name="conteudo.ordem" value="${conteudo.ordem}" />
</c:if>

<c:if test="${not empty modulo.id}">
	<input type="hidden" name="modulo.id" value="${modulo.id}" />
</c:if>

<div class="card-body card-padding">
	<div class="form-row">
    	<div class="form-group col-md-12">
	        <label for="conteudoTitulo" class="control-label">Título</label>
	        <input type="text" name="conteudo.titulo" id="conteudoTitulo" class="form-control required"  value="${conteudo.titulo}"/>
	    </div>

    	<div class="form-group col-md-12">
	        <label for="conteudoEmbed" class="control-label">Embed</label>
	        <input type="text" name="conteudo.embed" id="conteudoEmbed" class="form-control required"  value="${conteudo.embed}"/>
	    </div>

    	<div class="form-group col-md-12">
	        <label for="conteudoConteudo" class="control-label">Conteúdo</label>
	        <textarea name="conteudo.conteudo" id="conteudoConteudo" class="form-control required">${conteudo.conteudo}</textarea>
	    </div>
	    
	    <div class="form-group col-md-12" >
	        <label for="conteudoDiasdelimitacaodeduracao" class="control-label"> Limitação de Duração em Dias </label>
	        <input type="text" name="conteudo.diasDeLimitacaoDeDuracao" id="conteudoDiasdelimitacaodeduracao" class="form-control required " alt="numero" value="${conteudo.diasDeLimitacaoDeDuracao}"/>
	    </div>

    	<div class="form-group col-md-6">
			<label for="conteudoTipoliberacao" class="control-label"> Tipo de Liberação</label>
			<select name="conteudo.tipoLiberacao" class="form-control required " data-toggle="select" id="conteudoTipoliberacao">
				<c:forEach items="${tipoLiberacaoTipoLiberacaoList}" var="tipoLiberacao">
					<option value="${tipoLiberacao}" ${conteudo.tipoLiberacao==tipoLiberacao ? 'selected' : ''} >
							${tipoLiberacao.descricao}
					</option>
				</c:forEach>
			</select>
		</div>
		
    	<div class="form-group col-md-6 liberacao-conteudo" id="dias-apos-liberacao" style="display:none">
	        <label for="conteudoDiasapos" class="control-label"> Dias Após Compra</label>
	        <input type="text" name="conteudo.diasApos" id="conteudoDiasapos" class="form-control" alt="numero" value="${conteudo.diasApos}"/>
	    </div>
	    
	    <div class="form-group col-md-6 liberacao-conteudo" id="data-liberacao" style="display:none">
	        <label for="conteudoData" class="control-label"> Data </label>
	        <input type="text" name="conteudo.data" id="conteudoData" class="form-control"/>
	    </div>

	</div>

    <div class="form-group">
        <c:if test="${empty conteudo.id}">
            <button type="submit" class="btn btn-primary sendForm">Cadastrar</button>
            <button type="submit" class="btn btn-primary sendForm cadastrar-novo" name="flag" value="novo">Cadastrar e adicionar novo</button>
        </c:if>
        <c:if test="${not empty conteudo.id}">
            <button type="submit" class="btn btn-primary sendForm">Atualizar</button>
        </c:if>
    </div>
</div>