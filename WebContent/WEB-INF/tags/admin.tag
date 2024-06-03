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
		
		<style media="" data-href="https://fonts.googleapis.com/css?family=Roboto:400,300,100">/* cyrillic-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 100;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1MmgVxFIzIFKw.woff2) format('woff2');
        unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
      }
      /* cyrillic */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 100;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1MmgVxMIzIFKw.woff2) format('woff2');
        unicode-range: U+0301, U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
      }
      /* greek-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 100;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1MmgVxEIzIFKw.woff2) format('woff2');
        unicode-range: U+1F00-1FFF;
      }
      /* greek */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 100;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1MmgVxLIzIFKw.woff2) format('woff2');
        unicode-range: U+0370-0377, U+037A-037F, U+0384-038A, U+038C, U+038E-03A1, U+03A3-03FF;
      }
      /* vietnamese */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 100;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1MmgVxHIzIFKw.woff2) format('woff2');
        unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
      }
      /* latin-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 100;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1MmgVxGIzIFKw.woff2) format('woff2');
        unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
      }
      /* latin */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 100;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1MmgVxIIzI.woff2) format('woff2');
        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
      }
      /* cyrillic-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 300;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmSU5fCRc4EsA.woff2) format('woff2');
        unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
      }
      /* cyrillic */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 300;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmSU5fABc4EsA.woff2) format('woff2');
        unicode-range: U+0301, U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
      }
      /* greek-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 300;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmSU5fCBc4EsA.woff2) format('woff2');
        unicode-range: U+1F00-1FFF;
      }
      /* greek */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 300;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmSU5fBxc4EsA.woff2) format('woff2');
        unicode-range: U+0370-0377, U+037A-037F, U+0384-038A, U+038C, U+038E-03A1, U+03A3-03FF;
      }
      /* vietnamese */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 300;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmSU5fCxc4EsA.woff2) format('woff2');
        unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
      }
      /* latin-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 300;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmSU5fChc4EsA.woff2) format('woff2');
        unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
      }
      /* latin */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 300;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmSU5fBBc4.woff2) format('woff2');
        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
      }
      /* cyrillic-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 400;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu72xKOzY.woff2) format('woff2');
        unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
      }
      /* cyrillic */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 400;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu5mxKOzY.woff2) format('woff2');
        unicode-range: U+0301, U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
      }
      /* greek-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 400;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu7mxKOzY.woff2) format('woff2');
        unicode-range: U+1F00-1FFF;
      }
      /* greek */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 400;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu4WxKOzY.woff2) format('woff2');
        unicode-range: U+0370-0377, U+037A-037F, U+0384-038A, U+038C, U+038E-03A1, U+03A3-03FF;
      }
      /* vietnamese */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 400;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu7WxKOzY.woff2) format('woff2');
        unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
      }
      /* latin-ext */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 400;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu7GxKOzY.woff2) format('woff2');
        unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
      }
      /* latin */
      @font-face {
        font-family: 'Roboto';
        font-style: normal;
        font-weight: 400;
        src: url(https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu4mxK.woff2) format('woff2');
        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
      }
      </style>   
	</head>
	<body id="body-projeto">
		<main id="main-content" class="main-content ${sessao.usuario.abaLateral ? '': 'hidden'}" >
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
			<nav class="nav-cont-dashMenu desktop-dashboard">
            <ul class="nav">
            <li class="nav-items-dashMenu">
                <svg class="perfil" width="68" height="68" viewBox="0 0 58 58" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="29" cy="29" r="29" fill="#DDB6EA"/>
                    <path d="M23.1967 17.8926C22.5002 17.8926 21.8106 18.0298 21.1671 18.2963C20.5237 18.5628 19.939 18.9535 19.4465 19.446C18.954 19.9384 18.5634 20.5231 18.2968 21.1666C18.0303 21.81 17.8931 22.4997 17.8931 23.1961C17.8931 23.8926 18.0303 24.5823 18.2968 25.2257C18.5634 25.8692 18.954 26.4539 19.4465 26.9463C19.939 27.4388 20.5237 27.8295 21.1671 28.096C21.8106 28.3625 22.5002 28.4997 23.1967 28.4997C24.6033 28.4997 25.9523 27.941 26.9469 26.9463C27.9415 25.9517 28.5003 24.6027 28.5003 23.1961C28.5003 21.7896 27.9415 20.4406 26.9469 19.446C25.9523 18.4513 24.6033 17.8926 23.1967 17.8926ZM19.661 23.1961C19.661 22.7318 19.7524 22.2721 19.9301 21.8431C20.1078 21.4141 20.3683 21.0243 20.6966 20.696C21.0249 20.3677 21.4147 20.1073 21.8436 19.9296C22.2726 19.7519 22.7324 19.6604 23.1967 19.6604C23.661 19.6604 24.1208 19.7519 24.5498 19.9296C24.9787 20.1073 25.3685 20.3677 25.6968 20.696C26.0252 21.0243 26.2856 21.4141 26.4633 21.8431C26.641 22.2721 26.7324 22.7318 26.7324 23.1961C26.7324 24.1339 26.3599 25.0332 25.6968 25.6963C25.0338 26.3594 24.1344 26.7319 23.1967 26.7319C22.259 26.7319 21.3597 26.3594 20.6966 25.6963C20.0335 25.0332 19.661 24.1339 19.661 23.1961ZM35.5717 20.2497C34.4777 20.2497 33.4285 20.6843 32.6549 21.4579C31.8813 22.2315 31.4467 23.2807 31.4467 24.3747C31.4467 25.4687 31.8813 26.5179 32.6549 27.2915C33.4285 28.0651 34.4777 28.4997 35.5717 28.4997C36.6657 28.4997 37.7149 28.0651 38.4885 27.2915C39.2621 26.5179 39.6967 25.4687 39.6967 24.3747C39.6967 23.2807 39.2621 22.2315 38.4885 21.4579C37.7149 20.6843 36.6657 20.2497 35.5717 20.2497ZM33.2146 24.3747C33.2146 23.7496 33.4629 23.15 33.905 22.708C34.347 22.2659 34.9466 22.0176 35.5717 22.0176C36.1969 22.0176 36.7964 22.2659 37.2385 22.708C37.6805 23.15 37.9289 23.7496 37.9289 24.3747C37.9289 24.9999 37.6805 25.5994 37.2385 26.0415C36.7964 26.4835 36.1969 26.7319 35.5717 26.7319C34.9466 26.7319 34.347 26.4835 33.905 26.0415C33.4629 25.5994 33.2146 24.9999 33.2146 24.3747ZM14.3574 33.5086C14.3574 32.8054 14.6368 32.1309 15.1341 31.6336C15.6314 31.1362 16.3059 30.8569 17.0092 30.8569H29.3842C30.0875 30.8569 30.762 31.1362 31.2593 31.6336C31.7566 32.1309 32.036 32.8054 32.036 33.5086V34.1345C32.0351 34.2299 32.0284 34.3251 32.016 34.4197C31.9894 34.6628 31.9461 34.9038 31.8863 35.141C31.6911 35.9148 31.3437 36.6419 30.8645 37.2801C29.6694 38.8747 27.3901 40.2854 23.1967 40.2854C19.0034 40.2854 16.724 38.8747 15.5289 37.2801C15.0501 36.6418 14.7031 35.9147 14.5083 35.141C14.4311 34.8329 14.3818 34.5185 14.361 34.2016L14.3574 34.1345V33.5086ZM16.1253 34.092V34.1133L16.1359 34.2311C16.1477 34.3419 16.1712 34.5081 16.2231 34.712C16.3605 35.2571 16.6049 35.7695 16.942 36.2194C17.7364 37.2765 19.4347 38.5176 23.1967 38.5176C26.9587 38.5176 28.657 37.2765 29.4502 36.2194C29.8627 35.6701 30.0678 35.1209 30.1691 34.712C30.2188 34.5156 30.2516 34.3153 30.267 34.1133L30.2681 34.092V33.5086C30.2681 33.2742 30.175 33.0494 30.0092 32.8836C29.8435 32.7178 29.6186 32.6247 29.3842 32.6247H17.0092C16.7748 32.6247 16.5499 32.7178 16.3842 32.8836C16.2184 33.0494 16.1253 33.2742 16.1253 33.5086V34.092ZM32.1539 37.4804C33.0649 37.7598 34.1892 37.9283 35.5693 37.9283C38.834 37.9283 40.6584 36.9901 41.6496 35.8587C42.0453 35.4106 42.3389 34.8818 42.51 34.3089C42.5768 34.0774 42.6199 33.8397 42.6384 33.5994L42.6408 33.544V33.5086C42.6408 32.8054 42.3614 32.1309 41.8641 31.6336C41.3668 31.1362 40.6923 30.8569 39.989 30.8569H32.1468C32.6111 31.3413 32.9506 31.9482 33.1109 32.6247H39.9902C40.2226 32.6247 40.4456 32.7162 40.6111 32.8794C40.7765 33.0427 40.871 33.2645 40.8741 33.4969L40.8682 33.5511C40.8564 33.64 40.8379 33.7278 40.8128 33.8139C40.7134 34.1397 40.5445 34.44 40.3178 34.6943C39.7627 35.3307 38.4946 36.1604 35.5693 36.1604C34.4603 36.1604 33.5893 36.0414 32.9046 35.8575C32.724 36.4281 32.4718 36.9734 32.1539 37.4804Z" fill="white"/>
                </svg>
            </li>
            
            <li class="nav-items-dashMenu ">
                <svg width="47" height="34" viewBox="0 0 47 34" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g clip-path="url(#clip0_80_2797)">
                        <path d="M21.0654 33.8199C25.6273 33.9047 30.1917 33.9794 34.7549 33.9598C35.8572 33.9553 36.9633 33.9598 38.0637 33.8965C39.4927 33.8148 40.87 33.4641 42.1098 32.7254C42.929 32.2374 43.7204 31.6044 44.3258 30.8581C44.7559 30.3277 45.1746 29.7922 45.4546 29.1662C45.7674 28.4661 46.0335 27.778 46.1836 27.0261C46.5506 25.1854 46.6105 23.2896 46.6401 21.4185C46.6792 18.9771 46.6591 16.535 46.7101 14.0936C46.7448 12.427 46.83 10.7401 46.6673 9.07786C46.4989 7.36247 46.118 5.74647 45.225 4.25137C44.5213 3.07276 43.6252 2.11379 42.4749 1.37003C41.2212 0.560449 39.8124 0.225601 38.3563 0.0546962C38.007 0.0135523 37.6557 -0.00227225 37.3038 0.000259683C36.5969 0.00595652 35.8654 0.303458 35.3641 0.806679C34.8886 1.28395 34.5297 2.06758 34.5606 2.75373C34.6269 4.23554 35.7664 5.51986 37.3038 5.5072C37.7062 5.50404 38.1072 5.52809 38.5064 5.58063C38.2636 5.54772 38.0202 5.5148 37.7774 5.48252C38.3822 5.5686 38.9699 5.72305 39.5337 5.96105L38.8785 5.68317C39.3105 5.8737 39.7128 6.10854 40.0887 6.39528C39.9039 6.25159 39.7185 6.10854 39.5337 5.96485C39.8793 6.23893 40.1776 6.54719 40.45 6.89533C40.3068 6.70987 40.1643 6.52377 40.0212 6.33831C40.4052 6.8447 40.7243 7.39729 40.9715 7.98343L40.6947 7.32576C40.9652 7.97963 41.1323 8.66452 41.2288 9.36523C41.196 9.12153 41.1632 8.8772 41.1311 8.6335C41.3751 10.466 41.2622 12.3447 41.2238 14.1867C41.1676 16.8895 41.208 19.5942 41.1374 22.2964C41.1096 23.3649 41.0648 24.4372 40.9267 25.498L41.0245 24.7663C40.9255 25.5037 40.7779 26.2386 40.4929 26.9286C40.5849 26.7096 40.677 26.4899 40.7697 26.2709C40.6001 26.6722 40.3832 27.0476 40.1202 27.3945C40.2633 27.209 40.4059 27.0229 40.549 26.8374C40.228 27.2539 39.8547 27.6242 39.4429 27.9515C39.6277 27.8078 39.8131 27.6647 39.9978 27.5211C39.5766 27.847 39.1232 28.118 38.6344 28.3294L39.2897 28.0515C38.8173 28.2452 38.3305 28.3762 37.8247 28.4446C38.0675 28.4117 38.3109 28.3787 38.5537 28.3465C37.1689 28.5155 35.7336 28.4503 34.3425 28.4541C31.784 28.4604 29.2256 28.4357 26.6672 28.4022C24.8006 28.3781 22.9339 28.3477 21.0673 28.3129C20.3724 28.3003 19.6163 28.6294 19.1275 29.1193C18.652 29.5966 18.2932 30.3802 18.3241 31.0664C18.3916 32.5811 19.5292 33.7914 21.0673 33.8199H21.0654Z" fill="#EEEEEE"/>
                        <path d="M25.6981 0.182115C21.1356 0.0972959 16.5718 0.022604 12.0086 0.0422265C10.9063 0.0466573 9.80022 0.0422264 8.69979 0.105525C7.27081 0.187179 5.89354 0.537851 4.65375 1.27654C3.83457 1.76457 3.04315 2.39755 2.43775 3.14384C2.00767 3.67428 1.58894 4.20978 1.30895 4.8358C0.996159 5.53588 0.730038 6.22393 0.579951 6.97591C0.212931 8.81662 0.153022 10.7124 0.123383 12.5835C0.0842851 15.0249 0.104465 17.467 0.0533848 19.9084C0.0187008 21.575 -0.0664327 23.2619 0.0962668 24.9241C0.264642 26.6395 0.645535 28.2555 1.53849 29.7506C2.24226 30.9292 3.13837 31.8882 4.28862 32.6319C5.54229 33.4415 6.95109 33.7764 8.40719 33.9473C8.75655 33.9884 9.1078 34.0042 9.45969 34.0017C10.1666 33.996 10.8981 33.6985 11.3995 33.1953C11.875 32.718 12.2338 31.9344 12.2029 31.2482C12.1367 29.7664 10.9971 28.4821 9.45969 28.4948C9.05735 28.4979 8.65628 28.4739 8.2571 28.4213C8.49989 28.4543 8.74331 28.4872 8.98609 28.5195C8.38133 28.4334 7.79359 28.2789 7.22982 28.0409L7.88503 28.3188C7.45306 28.1283 7.05073 27.8934 6.67488 27.6067C6.85965 27.7504 7.04505 27.8934 7.22982 28.0371C6.88424 27.763 6.58596 27.4548 6.31353 27.1066C6.45668 27.2921 6.5992 27.4782 6.74235 27.6637C6.35831 27.1573 6.03921 26.6047 5.79201 26.0185C5.88408 26.2376 5.97615 26.4572 6.06885 26.6762C5.79832 26.0223 5.6312 25.3375 5.53472 24.6367L5.63247 25.3685C5.38842 23.536 5.5013 21.6573 5.53976 19.8153C5.59589 17.1125 5.55553 14.4078 5.62616 11.7056C5.65391 10.6371 5.69868 9.56481 5.83679 8.50393L5.73904 9.23566C5.83805 8.49823 5.98561 7.76334 6.27065 7.07339C6.17858 7.2924 6.08651 7.51205 5.99381 7.73106C6.16345 7.32975 6.38038 6.95439 6.64335 6.60752C6.5002 6.79298 6.35768 6.97908 6.21453 7.16454C6.53551 6.74804 6.90884 6.37774 7.32063 6.05049C7.13586 6.19418 6.95046 6.33723 6.76569 6.48092C7.18694 6.15493 7.64035 5.88402 8.12908 5.6726C7.91089 5.76502 7.69207 5.85743 7.47387 5.95048C7.9462 5.75679 8.43304 5.62576 8.9388 5.5574C8.69601 5.59031 8.45259 5.62323 8.2098 5.65551C9.59464 5.48651 11.0299 5.5517 12.4211 5.5479C14.9795 5.54157 17.5379 5.56626 20.0963 5.59981C21.963 5.62386 23.8296 5.65425 25.6962 5.68906C26.3912 5.70172 27.1473 5.37257 27.636 4.88264C28.1115 4.40537 28.4703 3.62174 28.4394 2.93559C28.3719 1.42086 27.2343 0.2106 25.6962 0.182115H25.6981Z" fill="#EEEEEE"/>
                    </g>
                    <defs>
                        <clipPath id="clip0_80_2797">
                            <rect width="47" height="34" fill="white"/>
                        </clipPath>
                    </defs>
                </svg>
                <img src="${sessao.urlPadrao}/img/LOGO_MULTT_TEXTO.png"/>
            </li>
                
            <li class="nav-items-dashMenu ">
                <svg width="38" height="38" viewBox="0 0 38 38" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g clip-path="url(#clip0_135_62)">
                        <path d="M4.7442 -0.00195312C2.13988 -0.00195312 0 2.13792 0 4.74225V12.6565C0 15.2608 2.13988 17.4161 4.7442 17.4161H12.6708C15.2751 17.4161 17.415 15.2608 17.415 12.6565V4.74225C17.415 2.13792 15.2751 -0.00195312 12.6708 -0.00195312H4.7442ZM25.3292 -0.00195312C22.7249 -0.00195312 20.585 2.13792 20.585 4.74225V12.6565C20.585 15.2608 22.7249 17.4161 25.3292 17.4161H33.2558C35.8601 17.4161 38 15.2608 38 12.6565V4.74225C38 2.13792 35.8601 -0.00195312 33.2558 -0.00195312H25.3292ZM4.7442 3.16497H12.6708C13.5754 3.16497 14.2481 3.83762 14.2481 4.74225V12.6565C14.2481 13.5611 13.5754 14.2461 12.6708 14.2461H4.7442C3.83957 14.2461 3.16692 13.5611 3.16692 12.6565V4.74225C3.16692 3.83762 3.83957 3.16497 4.7442 3.16497ZM25.3292 3.16497H33.2558C34.1604 3.16497 34.8331 3.83762 34.8331 4.74225V12.6565C34.8331 13.5611 34.1604 14.2461 33.2558 14.2461H25.3292C24.4246 14.2461 23.7519 13.5611 23.7519 12.6565V4.74225C23.7519 3.83762 24.4246 3.16497 25.3292 3.16497ZM4.7442 20.5831C2.13988 20.5831 0 22.7229 0 25.3273V33.2538C0 35.8582 2.13988 37.998 4.7442 37.998H12.6708C15.2751 37.998 17.415 35.8582 17.415 33.2538V25.3273C17.415 22.7229 15.2751 20.5831 12.6708 20.5831H4.7442ZM25.3292 20.5831C22.7249 20.5831 20.585 22.7229 20.585 25.3273V33.2538C20.585 35.8582 22.7249 37.998 25.3292 37.998H33.2558C35.8601 37.998 38 35.8582 38 33.2538V25.3273C38 22.7229 35.8601 20.5831 33.2558 20.5831H25.3292ZM4.7442 23.75H12.6708C13.5754 23.75 14.2481 24.4226 14.2481 25.3273V33.2538C14.2481 34.1585 13.5754 34.8311 12.6708 34.8311H4.7442C3.83957 34.8311 3.16692 34.1585 3.16692 33.2538V25.3273C3.16692 24.4226 3.83957 23.75 4.7442 23.75ZM25.3292 23.75H33.2558C34.1604 23.75 34.8331 24.4226 34.8331 25.3273V33.2538C34.8331 34.1585 34.1604 34.8311 33.2558 34.8311H25.3292C24.4246 34.8311 23.7519 34.1585 23.7519 33.2538V25.3273C23.7519 24.4226 24.4246 23.75 25.3292 23.75Z" fill="#EEEEEE"/>
                    </g>
                    <defs>
                        <clipPath id="clip0_135_62">
                            <rect width="38" height="38" fill="white"/>
                        </clipPath>
                    </defs>
                </svg>
            <a href="">Dashboard</a>
            </li>
                
            <li class="nav-items-dashMenu ">
                <svg width="48" height="48" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M44.9836 46.4729L43.4799 1.35938C43.4547 0.601406 42.8328 0 42.0744 0H5.92428C5.16584 0 4.54409 0.601406 4.51878 1.35938L3.0125 46.5469C2.99984 46.9277 3.14225 47.2974 3.40718 47.5714C3.67221 47.8453 4.037 48 4.418 48H43.5806C43.5813 48 43.5821 48 43.5824 48C44.3592 48 44.9887 47.3704 44.9887 46.5938C44.9887 46.553 44.987 46.5127 44.9836 46.4729ZM5.87196 45.1875L7.2845 2.8125H40.7142L42.1267 45.1875H5.87196Z" fill="#EEEEEE"/>
                    <path d="M33.0369 9.03711C32.2603 9.03711 31.6307 9.66673 31.6307 10.4434C31.6307 14.6512 28.2073 18.0746 23.9994 18.0746C19.7915 18.0746 16.3682 14.6512 16.3682 10.4434C16.3682 9.66673 15.7385 9.03711 14.9619 9.03711C14.1853 9.03711 13.5557 9.66673 13.5557 10.4434C13.5557 16.202 18.2407 20.8871 23.9994 20.8871C29.7582 20.8871 34.4432 16.202 34.4432 10.4434C34.4432 9.66673 33.8135 9.03711 33.0369 9.03711Z" fill="#EEEEEE"/>
                </svg>
                <a href="">Vendas</a>
            </li>

            <li class="nav-items-dashMenu ">
                <svg width="50" height="50" viewBox="0 0 50 50" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M18.75 43.75H31.25M18.75 43.75V33.3333M18.75 43.75H7.5C7.16848 43.75 6.85054 43.6183 6.61612 43.3839C6.3817 43.1495 6.25 42.8315 6.25 42.5V34.5833C6.25 34.2518 6.3817 33.9339 6.61612 33.6994C6.85054 33.465 7.16848 33.3333 7.5 33.3333H18.75M31.25 43.75V18.75M31.25 43.75H42.5C42.8315 43.75 43.1495 43.6183 43.3839 43.3839C43.6183 43.1495 43.75 42.8315 43.75 42.5V7.5C43.75 7.16848 43.6183 6.85054 43.3839 6.61612C43.1495 6.3817 42.8315 6.25 42.5 6.25H32.5C32.1685 6.25 31.8505 6.3817 31.6161 6.61612C31.3817 6.85054 31.25 7.16848 31.25 7.5V18.75M18.75 33.3333V20C18.75 19.6685 18.8817 19.3505 19.1161 19.1161C19.3505 18.8817 19.6685 18.75 20 18.75H31.25" stroke="#EEEEEE" stroke-width="3.125"/>
                </svg>
                <a href="">Relaórios</a>
            </li>

            <li class="nav-items-dashMenu ">
                <svg width="49" height="49" viewBox="0 0 49 49" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M42.875 16.3318C42.8743 15.6157 42.6852 14.9124 42.3269 14.2925C41.9685 13.6725 41.4535 13.1577 40.8333 12.7997L26.5417 4.633C25.9209 4.27461 25.2168 4.08594 24.5 4.08594C23.7832 4.08594 23.0791 4.27461 22.4583 4.633L8.16667 12.7997C7.54654 13.1577 7.03146 13.6725 6.67311 14.2925C6.31476 14.9124 6.12573 15.6157 6.125 16.3318V32.6651C6.12573 33.3812 6.31476 34.0844 6.67311 34.7044C7.03146 35.3243 7.54654 35.8391 8.16667 36.1972L22.4583 44.3638C23.0791 44.7222 23.7832 44.9109 24.5 44.9109C25.2168 44.9109 25.9209 44.7222 26.5417 44.3638L40.8333 36.1972C41.4535 35.8391 41.9685 35.3243 42.3269 34.7044C42.6852 34.0844 42.8743 33.3812 42.875 32.6651V16.3318Z" stroke="#EEEEEE" stroke-width="4.08333" stroke-linecap="round" stroke-linejoin="round"/>
                    <path d="M6.7373 14.291L24.4998 24.4993M24.4998 24.4993L42.2623 14.291M24.4998 24.4993V44.916" stroke="#EEEEEE" stroke-width="4.08333" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                <a href="">Produtos</a>
            </li>

            <li class="nav-items-dashMenu ">
                <svg width="46" height="46" viewBox="0 0 46 46" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M25.0324 15.5459L27.7445 18.256C28.9905 19.5019 29.9789 20.981 30.6532 22.6089C31.3275 24.2368 31.6746 25.9815 31.6746 27.7435C31.6746 29.5056 31.3275 31.2503 30.6532 32.8782C29.9789 34.5061 28.9905 35.9852 27.7445 37.231L27.066 37.9076C25.8201 39.1535 24.341 40.1419 22.7131 40.8161C21.0852 41.4904 19.3405 41.8375 17.5785 41.8375C15.8165 41.8375 14.0718 41.4904 12.4439 40.8161C10.816 40.1419 9.3369 39.1535 8.09098 37.9076C6.84507 36.6617 5.85675 35.1826 5.18247 33.5547C4.50818 31.9269 4.16113 30.1821 4.16113 28.4201C4.16113 26.6581 4.50818 24.9134 5.18247 23.2855C5.85675 21.6577 6.84507 20.1785 8.09098 18.9326L10.8031 21.6447C9.90678 22.5332 9.1948 23.5899 8.70801 24.7543C8.22123 25.9187 7.96922 27.1677 7.96645 28.4298C7.96369 29.6918 8.21023 30.942 8.69191 32.1084C9.17359 33.2749 9.88093 34.3348 10.7733 35.2272C11.6657 36.1196 12.7256 36.8269 13.8921 37.3086C15.0586 37.7903 16.3087 38.0368 17.5708 38.0341C18.8328 38.0313 20.0818 37.7793 21.2462 37.2925C22.4106 36.8057 23.4673 36.0938 24.3558 35.1975L25.0343 34.519C26.8309 32.7218 27.8402 30.2847 27.8402 27.7435C27.8402 25.2024 26.8309 22.7653 25.0343 20.9681L22.3222 18.256L25.0324 15.5459ZM37.9086 27.065L35.1984 24.3549C36.9579 22.55 37.9354 20.1245 37.9193 17.6039C37.9031 15.0834 36.8946 12.6706 35.1121 10.8884C33.3296 9.1062 30.9168 8.09799 28.3962 8.0822C25.8756 8.0664 23.4503 9.0443 21.6457 10.804L20.9672 11.4806C19.1706 13.2778 18.1613 15.7149 18.1613 18.256C18.1613 20.7972 19.1706 23.2343 20.9672 25.0315L23.6792 27.7435L20.9672 30.4537L18.257 27.7435C17.011 26.4977 16.0226 25.0186 15.3483 23.3907C14.6739 21.7628 14.3268 20.0181 14.3268 18.256C14.3268 16.494 14.6739 14.7493 15.3483 13.1214C16.0226 11.4935 17.011 10.0144 18.257 8.76854L18.9355 8.09196C20.1814 6.84604 21.6605 5.85773 23.2884 5.18344C24.9163 4.50916 26.661 4.16211 28.423 4.16211C30.185 4.16211 31.9297 4.50916 33.5576 5.18344C35.1855 5.85773 36.6646 6.84604 37.9105 8.09196C39.1564 9.33788 40.1447 10.817 40.819 12.4449C41.4933 14.0727 41.8403 15.8175 41.8403 17.5795C41.8403 19.3415 41.4933 21.0862 40.819 22.7141C40.1447 24.3419 39.1564 25.821 37.9105 27.067" fill="#EEEEEE"/>
                </svg>
                <a href="">Links</a>
            </li>

            <li class="nav-items-dashMenu ">
                <svg width="58" height="58" viewBox="0 0 58 58" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M7.25 12.6876C6.7693 12.6876 6.30828 12.8785 5.96837 13.2184C5.62846 13.5584 5.4375 14.0194 5.4375 14.5001C5.4375 14.9808 5.62846 15.4418 5.96837 15.7817C6.30828 16.1216 6.7693 16.3126 7.25 16.3126H11.2737L16.0297 35.3438C16.4339 36.957 17.8785 38.0626 19.5406 38.0626H42.1424C43.7791 38.0626 45.1693 36.9751 45.5989 35.3982L50.2969 18.1251H46.5015L42.1406 34.4376H19.5387L14.7846 15.4063C14.5882 14.6256 14.1355 13.9333 13.4989 13.4403C12.8624 12.9474 12.0788 12.6823 11.2737 12.6876H7.25ZM39.875 38.0626C36.8934 38.0626 34.4375 40.5185 34.4375 43.5001C34.4375 46.4816 36.8934 48.9376 39.875 48.9376C42.8566 48.9376 45.3125 46.4816 45.3125 43.5001C45.3125 40.5185 42.8566 38.0626 39.875 38.0626ZM23.5625 38.0626C20.5809 38.0626 18.125 40.5185 18.125 43.5001C18.125 46.4816 20.5809 48.9376 23.5625 48.9376C26.5441 48.9376 29 46.4816 29 43.5001C29 40.5185 26.5441 38.0626 23.5625 38.0626ZM29 12.6876V21.7501H23.5625L30.8125 29.0001L38.0625 21.7501H32.625V12.6876H29ZM23.5625 41.6876C24.5847 41.6876 25.375 42.4778 25.375 43.5001C25.375 44.5223 24.5847 45.3126 23.5625 45.3126C22.5403 45.3126 21.75 44.5223 21.75 43.5001C21.75 42.4778 22.5403 41.6876 23.5625 41.6876ZM39.875 41.6876C40.8973 41.6876 41.6875 42.4778 41.6875 43.5001C41.6875 44.5223 40.8973 45.3126 39.875 45.3126C38.8527 45.3126 38.0625 44.5223 38.0625 43.5001C38.0625 42.4778 38.8527 41.6876 39.875 41.6876Z" fill="#EEEEEE"/>
                </svg>
                <a href="">Compras</a>
            </li>

            <li class="nav-items-dashMenu ">
                <svg width="20" height="4" viewBox="0 0 20 4" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="2" cy="2" r="2" fill="#D9D9D9"/>
                    <circle cx="10" cy="2" r="2" fill="#D9D9D9"/>
                    <circle cx="18" cy="2" r="2" fill="#D9D9D9"/>
                </svg>
                <a href="">Ver Mais</a>
            </li>
                
            </ul>
        </nav>
        
        <div class="dashboard-container">
            <header class="mobile-dashboard mobile-header-menu-dashboard">
                <div id="menu-mobile-dashboard">
                    <div class="line-header-menu-dashboard">
                        <div class="dashboard-mobile-menu-logo-container">
                            <img src="${sessao.urlPadrao}/img/LOGO MULTT.png"/>
                        </div>
                        <div class="d-inline-flex gap-1">
                            <a data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                                <svg width="30px" height="30px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" stroke="#ffffff">
                                    <g id="SVGRepo_bgCarrier" stroke-width="0"/>
                                    <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"/>
                                    <g id="SVGRepo_iconCarrier"> <path d="M4 6H20M4 12H20M4 18H20" stroke="#ffffff" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/> </g>
                                </svg>
                            </a>   
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <div class="collapse" id="collapseExample">
                                <div class="mb-3 mt-2 dashboard-mobile-menu-conteudo">
                                    <ul>
                                        <li>Dashboard</li>
                                        <li>Vendas</li>
                                        <li>Relatório</li>
                                        <li>Produtos</li>
                                        <li>Links</li>
                                        <li>Compras</li>
                                        <li>Ver Mais</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <div id="dashboard-menu-main-content">
            	<jsp:doBody/>
            </div>
        </div>
			
			
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
