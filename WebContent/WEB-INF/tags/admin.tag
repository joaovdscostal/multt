<%@tag description="Template para páginas do admin" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://core.jvlabs.com.br/tag/ui" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>${configuracao.nome} - Administra&ccedil;&atilde;o</title>
		<link rel="icon" type="image/png" href="${sessao.urlPadrao}img/favicon-32x32.png" sizes="32x32" />
		<link rel="icon" type="image/png" href="${sessao.urlPadrao}img/favicon-16x16.png" sizes="16x16" />

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
	<body id="body-projeto">
		<nav class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light d-print-none ${sessao.usuario.abaLateral ? '': 'hidden'}" id="sidebar">
			<div class="container-fluid">

				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#sidebarCollapse" aria-controls="sidebarCollapse" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<a class="navbar-brand" href="${sessao.urlAdm}">
					<img src="${sessao.urlPadrao}img/logo-transparent.png" class="navbar-brand-img mx-auto" alt="Logo">
					<div class="clearfix"></div>
				</a>

				<div class="collapse navbar-collapse" id="sidebarCollapse">

				<form class="mt-4 mb-3 d-md-none">
					<div class="input-group input-group-rounded input-group-merge">
						<input type="search" class="form-control form-control-rounded form-control-prepended" placeholder="Search" aria-label="Search">
						<div class="input-group-prepend">
							<div class="input-group-text">
								<span class="fe fe-search"></span>
							</div>
						</div>
					</div>
				</form>

				<ui:menu>
					<ui:link icone="fad fa-home" url="${sessao.urlPadrao}adm" texto="Dashboard" button="false" />
					<ui:link icone="fad fa-user-crown" url="${sessao.urlPadrao}adm/usuarios" texto="Usu&aacute;rios" button="false" />
				</ui:menu>

				<hr class="navbar-divider my-3">

				<ui:menu>
				</ui:menu>





				<ul class="navbar-nav mt-auto">
					<li class="nav-item">
						<a href="${sessao.urlPadrao}adm/configuracao" class="nav-link ">
							<i class="fad fa-cogs fa-fw mr-2"></i> Configura&ccedil;&otilde;es
						</a>
					</li>
					<li class="nav-item">
						<a href="${sessao.urlPadrao}adm/alterarminhasenha" class="nav-link ">
							<i class="fad fa-key fa-fw mr-2"></i> Alterar senha
						</a>
					</li>
					<li class="nav-item">
						<a href="${sessao.urlAdm}/logout" class="nav-link ">
							<i class="fad fa-sign-out fa-fw mr-2"></i> Sair
						</a>
					</li>
				</ul>
			</div>
			</div>
		</nav>

		<main id="main-content"
		class="main-content ${sessao.usuario.abaLateral ? '': 'hidden'}"
		>

		<c:if test="${not empty errors}">
		<div class="container-fluid">
			<div class="alert alert-danger alert-principal" style="margin-top: 15px; margin-bottom: 0px;">
				<a class="close" data-dismiss="alert"><i class="fa fa-times" aria-hidden="true"></i></a>
				<ul class="list-unstyled">
					<c:forEach items="${errors}" var="error">
						<c:if test="${error.message != 'Invalid upload'}">
							<li>${error.message}</li>
						</c:if>
						<c:if test="${error.message == 'Invalid upload'}">
							<li><fmt:message key="invalid.image"></fmt:message></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		</c:if>
			<div id="graficoDiv"></div>
			<div class="tabGrande">
			<nav class="navbar navbar-expand-xl navbar-light mb-2" style="border: none; width: 100%;">
				<div style="width: 100%; max-width: 100%" class="container m-0 ">
					<!-- Brand -->
					<a class="navbar-brand ml-2" id="collapseMenuLink"
						href="Javascript:void(0)" data-usuario-id="${sessao.getUsuario().id}"
						data-is-open="${sessao.getUsuario().abaLateral}"> <i
						class="fas fa-bars fa-lg"></i>
					</a>

					<!-- Collapse -->
					<div>

						<!-- Nav -->
						<ul class="navbar-nav me-auto">
							<li class="nav-item d-flex align-items-center">

									<h3 style="padding: 11.5px 8px" class="mb-0">
										<i class="fas fa-user-circle fa-lg mr-2"></i> Bem vindo,
										${sessao.getUsuario().nome}
									</h3>
								</li>
						</ul>
					</div>
				</div>
			</nav>
			</div>
			<jsp:doBody/>
		</main>

		<%@include file="_modais.jsp"%>

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

		<%@include file="template.jsp"%>

	<c:if test="${not empty message}">
			<script>
				(function() {
					notificacao('success', '${message}');
					console.log('${message}')
				})();
			</script>
		</c:if>
		<c:if test="${not empty error}">
			<script>
				(function() {
					notificacao('danger', '${error}');
					console.error('${error}')
				})();
			</script>
		</c:if>
		<c:if test="${not empty alert}">
			<script>
				(function() {
					notificacao('warning', '${alert}');
					console.warn('${alert}')
				})();
			</script>
		</c:if>

	<div style="display: none;" id="stackPrint">${stack}</div>
	<div style="display: none;" id="messagePrint">${messageErro}</div>
	<div style="display: none;" id="localizedMessagePrint">${localizedMessageErro}</div>

	</body>
</html>
