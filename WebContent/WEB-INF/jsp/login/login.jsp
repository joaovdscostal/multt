<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${sessao.urlPadrao}css/admin/login.css" media="screen" />

	<c:if test="${ambiente == 'desenvolvimento'}">
			<link href="${sessao.urlPadrao}css/plugins/bootstrap/bootstrap-theme.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/fontawesome/all.min.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/feather/feather.min.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/codemirror/codemirror.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/fileinput/fileinput.min.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/datatables/datatables.min.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/summernote/summernote.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/summernote/summernote-bs4.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/toastr/toastr.min.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/flatpickr/flatpickr.min.css" rel="stylesheet">
			<link href="${sessao.urlPadrao}css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
			<link rel="stylesheet" type="text/css" href="https://at.alicdn.com/t/font_o5hd5vvqpoqiwwmi.css">
			<link href="${sessao.urlPadrao}css/admin/admin.css" rel="stylesheet">
		</c:if>
		<c:if test="${ambiente != 'desenvolvimento'}">
			<link href="${sessao.urlPadrao}css/compactado/${versao}/bibliotecas-min.css" rel="stylesheet">
			<link rel="stylesheet" type="text/css" href="https://at.alicdn.com/t/font_o5hd5vvqpoqiwwmi.css">
			<link href="${sessao.urlPadrao}css/compactado/${versao}/admin-min.css" rel="stylesheet">
		</c:if>

		<script>
			var urlPadrao = '${sessao.urlPadrao}';
		</script>
</head>

<body>
	<form class="form-signin d-flex flex-column align-items-center validateForm" action="${sessao.urlPadrao}adm/login" method="post">
		<section class="container-geral-Login">
        <div class="d-flex holder-login">
            <div class="bg-login bg-login-1 ">
                <img class="desktop-login" src="${sessao.urlPadrao}/img/Teste_print.png"/>
                <div class="mobile-login mobile-form-container">
                    <div class="form-login-header">
                        <img src="${sessao.urlPadrao}/img/LOGO MULTT.png"/>
                    </div>
                    <div class="form-login-section">
      
                    	<c:if test="${not empty message}">
							<div class="btn-msg-login"> ${message} </div>
						</c:if>
						<c:if test="${not empty error}">
                            <div class="btn-erro-login"> ${error} </div>
						</c:if>
						<c:if test="${not empty alert}">
							<div class="alert alert-warning">
								<div class="btn-alert-login"> ${alert} </div>
							</div>
						</c:if>
						
                        <div>
                            <label for="nomeUsuario"> Seu e-mail </label>
                            <input type="text" placeholder="exemplo@exemplo.com" id="nomeUsuarioMobile" class="form-control trasparent-input required"/>
                        </div>
                        
                        <div>
                            <label for="senhaUsuario"> Sua senha </label>
                            <input type="password" placeholder="*************" id="senhaUsuarioMobile" class="form-control trasparent-input required"/>
                            <div class="d-flex justify-content-end">
                                <a href="${sessao.urlPadrao}contas/cadastrar">Esqueci minha senha</a>
                            </div>
                        </div>
                        
                        <div>
                            <button class="btn btn-primary btn-acessar-login">Acessar</button>
                        </div>
                        
                        <div class="d-flex justify-content-between form-login-footer-links">
                            <a href="#">Não tenho conta</a>
                            <a href="#">Política de privacidade</a>
                        </div>
                    </div>
                    <div class="form-login-footer-mobile hide-momile-footer">
                        <div class="bg-phantom-detalahe">
                            <div class="logo-footer-container">
                                <img class="logo" src="${sessao.urlPadrao}/img/LOGO MULTT.png" alt="Logo Multt"/>
                            </div>
                            <p class="text-center">     
                                Copyright © 2024 - multt. Todos os direitos reservados.
                            </p>
                        </div>
                    </div>
                </div>            
            </div>
            <div class="bg-login form-login-container desktop-login">
                <div class="bg-login-2"></div>
                <div class="form-login">
                    <div class="form-login-header">
                        <img src="${sessao.urlPadrao}/img/LOGO MULTT.png"/>
                    </div>
                    <div class="form-login-section">
                    	
                        
                        <c:if test="${not empty message}">
							<div class="btn-msg-login"> ${message} </div>
						</c:if>
						<c:if test="${not empty error}">
                            <div class="btn-erro-login"> ${error} </div>
						</c:if>
						<c:if test="${not empty alert}">
							<div class="alert alert-warning">
								<div class="btn-alert-login"> ${alert} </div>
							</div>
						</c:if>
                        
                        <div>
                            <label for="nomeUsuario"> Seu e-mail </label>
                            <input type="text" placeholder="exemplo@exemplo.com" id="nomeUsuario" class="form-control trasparent-input required"/>
                        </div>
                        <div>
                            <label for="senhaUsuario"> Sua senha </label>
                            <input type="password" placeholder="*************" id="senhaUsuario" class="form-control trasparent-input required"/>
                            <div class="d-flex justify-content-end">
                                <a href="#">Esqueci minha senha</a>
                            </div>
                        </div>
                        <div>
                            <button class="btn btn-acessar-login">Acessar</button>
                        </div>
                        
                        <div class="d-flex justify-content-between form-login-footer-links">
                            <a href="${sessao.urlPadrao}contas/cadastrar">Não tenho conta</a>
                            <a href="#">Política de privacidade</a>
                        </div>
                        
                        
                    </div>
                    <div class="form-login-footer">
                        <p>
                            <img src="${sessao.urlPadrao}/img/LOGO MULTT.png" alt="Logo Multt"/>
                            Copyright © 2024 - multt. Todos os direitos reservados.
                        </p>
                    </div>
                </div> 
            </div>
            
        </div>      
    </section>
	</form>
	
	<script src="${sessao.urlPadrao}js/plugins/jquery/jquery-3.3.1.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/popper/popper.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/bootstrap/bootstrap.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/moment/moment-with-locales.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/mascara/meiomask.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/fileinput/fileinput.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/fileinput/themes/fa/theme.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/fileinput/locales/pt-BR.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/validate/jquery.validate.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/validate/additional-methods.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/validate/localization/messages_pt_BR.js"></script>

	<script src="${sessao.urlPadrao}js/plugins/summernote/summernote-bs4.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/summernote/lang/summernote-pt-BR.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/select/bootstrap-select.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/select/i18n/defaults-pt_BR.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/datatables/datatables.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/toastr/toastr.min.js"></script>

	<script src="${sessao.urlPadrao}js/plugins/flatpickr/flatpickr.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/flatpickr/pt.js"></script>

	<script src="${sessao.urlPadrao}js/plugins/bootstrap-input/bootstrap-input-spinner.js"></script>

	<script src="${sessao.urlPadrao}js/plugins/dragula/dragula.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/jsrender/jsrender.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/jsrender/jsviews.min.js"></script>

	<script src="${sessao.urlPadrao}js/plugins/loading/js/jquery.showLoading.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/cookie/lib.js"></script>


	<c:if test="${ambiente == 'desenvolvimento'}">
		<script src="${sessao.urlPadrao}js/admin/Ajax.js"></script>
		<script src="${sessao.urlPadrao}js/admin/Modal.js"></script>
		<script src="${sessao.urlPadrao}js/admin/dinamico.js"></script>
		<script src="${sessao.urlPadrao}js/admin/paginate.js"></script>
		<script src="${sessao.urlPadrao}js/admin/notificacao.js"></script>
		<script src="${sessao.urlPadrao}js/admin/admin.js"></script>
		<script src="${sessao.urlPadrao}js/admin/forms.js"></script>
	</c:if>
	
	<c:if test="${ambiente != 'desenvolvimento'}">
		<script src="${sessao.urlPadrao}js/compactado/${versao}/Ajax.js"></script>
		<script src="${sessao.urlPadrao}js/compactado/${versao}/Modal.js"></script>
		<script src="${sessao.urlPadrao}js/compactado/${versao}/dinamico.js"></script>
		<script src="${sessao.urlPadrao}js/compactado/${versao}/paginate.js"></script>
		<script src="${sessao.urlPadrao}js/compactado/${versao}/notificacao.js"></script>
		<script src="${sessao.urlPadrao}js/compactado/${versao}/admin.js"></script>
		<script src="${sessao.urlPadrao}js/compactado/${versao}/forms.js"></script>
	</c:if>

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

	<link href="${sessao.urlPadrao}css/plugins/summernote/summernote-bs4.css" rel="stylesheet">

	<c:if test="${not empty controlador}">
		<c:if test="${ambiente == 'desenvolvimento'}">
			<script src="${sessao.urlPadrao}js/admin/controller/${controlador}.js"></script>
		</c:if>
		<c:if test="${ambiente != 'desenvolvimento'}">
			<script src="${sessao.urlPadrao}js/compactado/${versao}/controller/${controlador}.js"></script>
		</c:if>
	</c:if>
	
	<script src="${sessao.urlPadrao}js/plugins/jquery/jquery-3.3.1.min.js"></script>
	<script src="${sessao.urlPadrao}js/plugins/bootstrap/bootstrap.min.js"></script>
	<script src="${sessao.urlPadrao}js/admin/notificacao.js"></script>
	<script src="${sessao.urlPadrao}js/admin/controller/login.js"></script>
</body>

</html>