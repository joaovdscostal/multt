<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
    
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
		<link type="text/css" rel="stylesheet" href="${sessao.urlPadrao}js/plugins/img-upload/image-uploader.min.css">
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
<style>
	fieldset input {
		display: none;
	}

    .conainer-multt-padrao{
        padding-left: 20%;
        padding-right: 20%;
    }

    button{
        box-shadow: none !important;
    }

    .multt-checkout-banner{
        background-color: gray;
        width: 100%;
        min-height: 300px;
    }
    
    .multt-checkout-banner img{
        width: 100%;
    }

    .multt-checkout-forma-pagamento button{
        width: 100%;
    }

    .logo-checkout{
        height: 30px;
    }

    .btn-multt-checkout-forma-pagamento{
        padding-left: 20px;
        padding-right: 20px;
        height: 80px;
        border: 1px solid #000000ab;
        border-radius: 10px;
        margin-top: 15px;
        width: 100%;
    }

    .btn-multt-checkout-forma-pagamento .multt-checked-icon-checkout{
        display: none;
    }

    .multt-checkout-form-pt-2{
        background-color: #D9D9D940;
        border-radius: 10px;
        padding: 15px;
    }

    .btn-mullt-finalizar-checkout{
        max-width: 600px;
        width: 100%;
    }

    .finalizar-checkout{
       width: 100%;
       text-align: center;
    }

    .btn-checkout-selecionado{
        border-color: #9448AE;
        color: #9448AE !important;
        position: relative;
    }

    svg{
        stroke: #3C3C3C;
        fill: white;
    }

    .btn-checkout-selecionado svg{
        stroke: #9448AE !important;
    }

    .btn-checkout-selecionado .multt-checked-icon-checkout{
        display: block !important;
        position: absolute;
        right: -15px;
        top: -20px;
    }

    .multt-input-icon-group{
        position: relative;
    }

    .multt-input-icon-group svg{
        position: absolute;
        right: 25px;
        top: 7px;
        height: 23px;
    }

    .mult-icon-text{
        font-size: 15px;
    }

    @media (max-width: 747px) {
        .conainer-multt-padrao{
            padding-left: 5%;
            padding-right: 5%;
        }
    }
    
    .multt-checkout-produto{
    	height: 80px;
    	margin-top: 20px;
    	margin-bottom: 10px;
    	display: flex;
    	gap:10px;
    }
    
    .multt-checkout-produto img {
    	height: 100%;
    	border-radius: 6px;
    }
    
    .display-order-bump{
    	border: 2px dashed gray;
    	margin-top:15px;
    	margin-bottom:10px;
    	min-height: 50px;
    }
    
    .checkout-container-bump {
	    border-left: 1px solid #edf2f9;
	    width: 100%;
	    flex-grow: 1;
	}
	
	.checkout-container-bump .card-header {
	    border-top-left-radius: 0px;
	    background-color: gray;
	    color: white;
	    height: auto !important;
	    padding-top: 5px;
	    padding-bottom: 5px;
	    white-space: normal;
	    word-wrap: break-word;
	}
	
	.checkout-container-bump .card-body {
	    height: auto !important;
	    padding-top: 10px;
	    padding-bottom: 10px;
	    white-space: normal;
	    word-wrap: break-word;
	}
	
	.checkout-container-bump .card-header input {
	    height: 20px;
	    width: 20px;
	}
	
	.checkout-img-container-bump {
	    border-top-left-radius: calc(0.5rem - 1px);
	    border-bottom-left-radius: calc(0.5rem - 1px);
	    min-width: 10%;
	    max-width: 15%;
	    background-repeat: no-repeat;
	    background-size: contain;
	    background-position: center;
	}
	
	.display-order-bump {
	    border: 2px dashed gray;
	    min-height: 50px;
	    width: 100%;
	}
	
	.display-order-bump .card>* {
	    flex-shrink: 1 !important;
	}
	
	.multt-checkout-order-bump-container{
		width: 100%;
	}
	
	.checkout-menu-pix-container{
		display: flex;
		flex-direction: column;	
	}
	.checkout-menu-pix-container p {
		margin-top:10px;
		padding: 12px;
		border-radius:7px;
		background-color: #CCCCCC;
	}
	
	.checkout-menu-pix-container button {
		margin-bottom: 10px;
	}
	
	.checkout-pix-QRCode-container{
		display: flex;
		justify-content: end;
	}
	
	.info-boleto{
		border-right: 2px dashed gray;
	    padding-left: 10px;
	    padding-right: 10px;
	}
	
	
	
	@media (max-width: 550px) { 
   
	   .checkout-bump-mobile{
		  display: block;
		  height: 50px;
		  width: 50px;
	   }
	   
	   .checkout-img-container-bump{
		  border: 1px solid #edf2f9 !important;
		  border-bottom: none;
		  min-width: 100%;
		  max-width: unset;
		  height: 100px;
		  background-repeat: no-repeat;
		  background-size: contain;
		  background-position: center;   
	   }
	   
	   .checkout-container-bump .card-header{
			border-top-left-radius: 0px !important;
			border-top-right-radius: 0px !important;
		}
	   
	   .checkout-bump-desktop{
		  display: none !important;
		  height: 0px;
		  width: 0px;
	   }
	   
	   .checkout-pix-QRCode-container{
			justify-content: center !important;
		}
		
		
	}
	
	@media (max-width: 767px) { 
	   .checkout-pix-QRCode-container{
			justify-content: center !important;
		}
	}
	
	@media (min-width: 550px) {
	    .checkout-bump-mobile{
		  display: none;
		  height: 0px;
		  width: 0px;
	   }
	   
	   .checkout-bump-desktop{
	   	  display: flex;
		  display: block;
	   }
	}

</style>
<body>
    <header class="conainer-multt-padrao mt-3">
        <div class="multt-checkout-banner-container">
            <div class="multt-checkout-banner">
            	<c:if test="${not empty checkout.banner}">
            		<img src="${sessao.urlPadrao}arquivos/banners/${checkout.banner}"/>
            	</c:if>
            </div>
        </div>
        <div class="multt-checkout-produto-container">
            <div class="multt-checkout-produto">
                <img src="${sessao.urlPadrao}arquivos/imagens/${checkout.oferta.produto.pegarPrimeiraImagem()}"/>
                <div>
                	<div>
                    	<h3>${checkout.oferta.produto.nome}</h3>
                    	<p><small>Autor: ${checkout.oferta.produto.conta.nome}</small></p>
                    </div>
                    <h3>R$ ${checkout.oferta.pegarValorFormatado()}</h3>
                </div>
            </div>
        </div>
    </header>
    <section class="conainer-multt-padrao">
        <form class="multt-checkout-form-container ">
            <div class="multt-checkout-form-pt-1 row">
                <div class="col-md-12 mt-3">
                    <input type="text" placeholder="Nome Completo" class="form-control"/>
                </div>
                <div class="col-md-12 mt-3">
                    <input type="text mt-3" placeholder="E-mail" class="form-control"/>
                </div>
                <div class="col-md-12 mt-3">
                    <input type="text mt-3" placeholder="Confirmar E-mail" class="form-control"/>
                </div>
                <div class="col-md-6 mt-3">
                    <input type="text" placeholder="CPF" class="form-control"/>
                </div>
                <div class="col-md-6 mt-3">
                    <input type="text" placeholder="Celular com DDD"  class="form-control"/>
                </div>
            </div>
            <fieldset>
            	<div class="multt-checkout-forma-pagamento mt-3 row">
	                <div class="col-md-4">
	                	<input type="radio" data-selection="#cartaoDeCredito" id="select-forma-pagamento-1" name="forma-pagamento" class="form-check-input" checked/>
	                    <label for="select-forma-pagamento-1" class="btn btn-multt-checkout-forma-pagamento btn-checkout-selecionado">
	                        <svg class="mr-3" width="34" height="34" viewBox="0 0 34 34" fill="none" xmlns="http://www.w3.org/2000/svg">
	                            <path d="M6.90625 6.375L27.0937 6.375C29.1476 6.375 30.8125 8.03994 30.8125 10.0938L30.8125 23.9063C30.8125 25.9601 29.1476 27.625 27.0937 27.625L6.90625 27.625C4.85244 27.625 3.18749 25.9601 3.18749 23.9062L3.1875 10.0937C3.1875 8.03994 4.85244 6.375 6.90625 6.375Z" stroke-width="2.125" stroke-linecap="round" stroke-linejoin="round"/>
	                            <path d="M30.8125 12.75L3.1875 12.75M25.5 19.9219L22.3125 19.9219L22.3125 21.25L25.5 21.25L25.5 19.9219Z" stroke-width="3.98438" stroke-linejoin="round"/>
	                        </svg>
	                        Cartão
	                        <svg class="multt-checked-icon-checkout" width="37" height="37" viewBox="0 0 37 37" fill="none" xmlns="http://www.w3.org/2000/svg">
	                            <path d="M32.6438 14.8607C32.0989 14.2913 31.5353 13.7045 31.3228 13.1885C31.1262 12.7159 31.1147 11.9325 31.1031 11.1737C31.0814 9.76309 31.0583 8.16457 29.9469 7.05312C28.8354 5.94168 27.2369 5.91855 25.8263 5.89687C25.0675 5.88531 24.2841 5.87375 23.8115 5.67719C23.297 5.46473 22.7087 4.90105 22.1393 4.35617C21.142 3.39793 20.0089 2.3125 18.5 2.3125C16.9911 2.3125 15.8594 3.39793 14.8607 4.35617C14.2913 4.90105 13.7045 5.46473 13.1885 5.67719C12.7188 5.87375 11.9325 5.88531 11.1737 5.89687C9.76309 5.91855 8.16457 5.94168 7.05312 7.05312C5.94168 8.16457 5.92578 9.76309 5.89687 11.1737C5.88531 11.9325 5.87375 12.7159 5.67719 13.1885C5.46473 13.703 4.90105 14.2913 4.35617 14.8607C3.39793 15.858 2.3125 16.9911 2.3125 18.5C2.3125 20.0089 3.39793 21.1406 4.35617 22.1393C4.90105 22.7087 5.46473 23.2955 5.67719 23.8115C5.87375 24.2841 5.88531 25.0675 5.89687 25.8263C5.91855 27.2369 5.94168 28.8354 7.05312 29.9469C8.16457 31.0583 9.76309 31.0814 11.1737 31.1031C11.9325 31.1147 12.7159 31.1262 13.1885 31.3228C13.703 31.5353 14.2913 32.0989 14.8607 32.6438C15.858 33.6021 16.9911 34.6875 18.5 34.6875C20.0089 34.6875 21.1406 33.6021 22.1393 32.6438C22.7087 32.0989 23.2955 31.5353 23.8115 31.3228C24.2841 31.1262 25.0675 31.1147 25.8263 31.1031C27.2369 31.0814 28.8354 31.0583 29.9469 29.9469C31.0583 28.8354 31.0814 27.2369 31.1031 25.8263C31.1147 25.0675 31.1262 24.2841 31.3228 23.8115C31.5353 23.297 32.0989 22.7087 32.6438 22.1393C33.6021 21.142 34.6875 20.0089 34.6875 18.5C34.6875 16.9911 33.6021 15.8594 32.6438 14.8607ZM25.0993 15.8493L17.0055 23.943C16.8982 24.0506 16.7706 24.1358 16.6303 24.194C16.4899 24.2522 16.3394 24.2822 16.1875 24.2822C16.0356 24.2822 15.8851 24.2522 15.7447 24.194C15.6044 24.1358 15.4768 24.0506 15.3695 23.943L11.9007 20.4743C11.7933 20.3669 11.7081 20.2393 11.6499 20.099C11.5918 19.9586 11.5619 19.8082 11.5619 19.6562C11.5619 19.5043 11.5918 19.3539 11.6499 19.2135C11.7081 19.0732 11.7933 18.9456 11.9007 18.8382C12.1177 18.6212 12.4119 18.4994 12.7188 18.4994C12.8707 18.4994 13.0211 18.5293 13.1615 18.5874C13.3018 18.6456 13.4294 18.7308 13.5368 18.8382L16.1875 21.4904L23.4632 14.2132C23.5706 14.1058 23.6982 14.0206 23.8385 13.9624C23.9789 13.9043 24.1293 13.8744 24.2812 13.8744C24.4332 13.8744 24.5836 13.9043 24.724 13.9624C24.8643 14.0206 24.9919 14.1058 25.0993 14.2132C25.2067 14.3206 25.2919 14.4482 25.3501 14.5885C25.4082 14.7289 25.4381 14.8793 25.4381 15.0312C25.4381 15.1832 25.4082 15.3336 25.3501 15.474C25.2919 15.6143 25.2067 15.7419 25.0993 15.8493Z" fill="#DDB6EA"/>
	                        </svg>                        
	                    </label>
	                </div>
	                <div class="col-md-4">
	                	<input type="radio" data-selection="#boleto" id="select-forma-pagamento-2" name="forma-pagamento" class="form-check-input"/>
	                    <label for="select-forma-pagamento-2" class="btn btn-multt-checkout-forma-pagamento ">
	                        <svg class="mr-3" width="52" height="33" viewBox="0 0 52 33" fill="none" xmlns="http://www.w3.org/2000/svg">
	                            <path d="M1.17297 1.09375H51.173V31.7389H1.17297V1.09375Z" stroke-width="1.6129" stroke-linejoin="round"/>
	                            <path d="M4.3988 4.32031V29.3203M6.81816 4.32031V29.3203M13.2698 4.32031V27.7074M19.7214 4.32031V27.7074" stroke-width="1.6129" stroke-linecap="round" stroke-linejoin="round"/>
	                            <path d="M9.23749 27.7074V4.32031H10.8504V27.7074H9.23749ZM15.6891 27.7074V4.32031H17.302V27.7074H15.6891ZM22.1407 27.7074V4.32031H22.9472V27.7074H22.1407Z" fill="#3C3C3C" stroke-width="1.6129" stroke-linecap="round" stroke-linejoin="round"/>
	                            <path d="M34.2375 4.32031V27.7074M36.6568 4.32031V27.7074M43.1085 4.32031V27.7074" stroke-width="1.6129" stroke-linecap="round" stroke-linejoin="round"/>
	                            <path d="M30.2053 27.7074V4.32031H31.8182V27.7074H30.2053ZM39.0762 27.7074V4.32031H40.6891V27.7074H39.0762Z" fill="#3C3C3C"  stroke-width="1.6129" stroke-linecap="round" stroke-linejoin="round"/>
	                            <path d="M45.5278 4.32031V29.3203M47.9472 4.32031V29.3203M25.3665 4.32031V29.3203M27.7859 4.32031V29.3203" stroke-width="1.6129" stroke-linecap="round" stroke-linejoin="round"/>
	                        </svg>
	                        Boleto
	                        <svg class="multt-checked-icon-checkout" width="37" height="37" viewBox="0 0 37 37" fill="none" xmlns="http://www.w3.org/2000/svg">
	                            <path d="M32.6438 14.8607C32.0989 14.2913 31.5353 13.7045 31.3228 13.1885C31.1262 12.7159 31.1147 11.9325 31.1031 11.1737C31.0814 9.76309 31.0583 8.16457 29.9469 7.05312C28.8354 5.94168 27.2369 5.91855 25.8263 5.89687C25.0675 5.88531 24.2841 5.87375 23.8115 5.67719C23.297 5.46473 22.7087 4.90105 22.1393 4.35617C21.142 3.39793 20.0089 2.3125 18.5 2.3125C16.9911 2.3125 15.8594 3.39793 14.8607 4.35617C14.2913 4.90105 13.7045 5.46473 13.1885 5.67719C12.7188 5.87375 11.9325 5.88531 11.1737 5.89687C9.76309 5.91855 8.16457 5.94168 7.05312 7.05312C5.94168 8.16457 5.92578 9.76309 5.89687 11.1737C5.88531 11.9325 5.87375 12.7159 5.67719 13.1885C5.46473 13.703 4.90105 14.2913 4.35617 14.8607C3.39793 15.858 2.3125 16.9911 2.3125 18.5C2.3125 20.0089 3.39793 21.1406 4.35617 22.1393C4.90105 22.7087 5.46473 23.2955 5.67719 23.8115C5.87375 24.2841 5.88531 25.0675 5.89687 25.8263C5.91855 27.2369 5.94168 28.8354 7.05312 29.9469C8.16457 31.0583 9.76309 31.0814 11.1737 31.1031C11.9325 31.1147 12.7159 31.1262 13.1885 31.3228C13.703 31.5353 14.2913 32.0989 14.8607 32.6438C15.858 33.6021 16.9911 34.6875 18.5 34.6875C20.0089 34.6875 21.1406 33.6021 22.1393 32.6438C22.7087 32.0989 23.2955 31.5353 23.8115 31.3228C24.2841 31.1262 25.0675 31.1147 25.8263 31.1031C27.2369 31.0814 28.8354 31.0583 29.9469 29.9469C31.0583 28.8354 31.0814 27.2369 31.1031 25.8263C31.1147 25.0675 31.1262 24.2841 31.3228 23.8115C31.5353 23.297 32.0989 22.7087 32.6438 22.1393C33.6021 21.142 34.6875 20.0089 34.6875 18.5C34.6875 16.9911 33.6021 15.8594 32.6438 14.8607ZM25.0993 15.8493L17.0055 23.943C16.8982 24.0506 16.7706 24.1358 16.6303 24.194C16.4899 24.2522 16.3394 24.2822 16.1875 24.2822C16.0356 24.2822 15.8851 24.2522 15.7447 24.194C15.6044 24.1358 15.4768 24.0506 15.3695 23.943L11.9007 20.4743C11.7933 20.3669 11.7081 20.2393 11.6499 20.099C11.5918 19.9586 11.5619 19.8082 11.5619 19.6562C11.5619 19.5043 11.5918 19.3539 11.6499 19.2135C11.7081 19.0732 11.7933 18.9456 11.9007 18.8382C12.1177 18.6212 12.4119 18.4994 12.7188 18.4994C12.8707 18.4994 13.0211 18.5293 13.1615 18.5874C13.3018 18.6456 13.4294 18.7308 13.5368 18.8382L16.1875 21.4904L23.4632 14.2132C23.5706 14.1058 23.6982 14.0206 23.8385 13.9624C23.9789 13.9043 24.1293 13.8744 24.2812 13.8744C24.4332 13.8744 24.5836 13.9043 24.724 13.9624C24.8643 14.0206 24.9919 14.1058 25.0993 14.2132C25.2067 14.3206 25.2919 14.4482 25.3501 14.5885C25.4082 14.7289 25.4381 14.8793 25.4381 15.0312C25.4381 15.1832 25.4082 15.3336 25.3501 15.474C25.2919 15.6143 25.2067 15.7419 25.0993 15.8493Z" fill="#DDB6EA"/>
	                        </svg>  
	                    </label>
	                </div>
	                <div class="col-md-4">
	                	<input type="radio" data-selection="#pix" id="select-forma-pagamento-3" name="forma-pagamento"  class="form-check-input"/>
	                    <label for="select-forma-pagamento-3" class="btn btn-multt-checkout-forma-pagamento">
	                        <svg class="mr-3" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
	                            <path d="M15.45 16.5193L12.44 13.5093C12.33 13.3993 12.2 13.3793 12.13 13.3793C12.06 13.3793 11.93 13.3993 11.82 13.5093L8.80003 16.5293C8.46003 16.8693 7.93003 17.4193 6.16003 17.4193L9.87003 21.1193C10.4325 21.6811 11.195 21.9967 11.99 21.9967C12.785 21.9967 13.5475 21.6811 14.11 21.1193L17.83 17.4093C16.92 17.4093 16.16 17.2293 15.45 16.5193ZM8.80003 7.46931L11.82 10.4893C11.9 10.5693 12.02 10.6193 12.13 10.6193C12.24 10.6193 12.36 10.5693 12.44 10.4893L15.43 7.49931C16.14 6.75931 16.95 6.58931 17.86 6.58931L14.14 2.87931C13.5775 2.31751 12.815 2.00195 12.02 2.00195C11.225 2.00195 10.4625 2.31751 9.90003 2.87931L6.19003 6.57931C7.95003 6.57931 8.49003 7.15931 8.80003 7.46931Z" />
	                            <path d="M21.11 9.85031L18.86 7.59031H17.6C17.06 7.59031 16.52 7.81031 16.15 8.20031L13.15 11.2003C12.87 11.4803 12.5 11.6203 12.13 11.6203C11.749 11.6149 11.3844 11.4648 11.11 11.2003L8.09005 8.17031C7.71005 7.79031 7.19005 7.57031 6.64005 7.57031H5.17005L2.88005 9.87031C2.31824 10.4328 2.00269 11.1953 2.00269 11.9903C2.00269 12.7853 2.31824 13.5478 2.88005 14.1103L5.17005 16.4103H6.65005C7.19005 16.4103 7.71005 16.1903 8.10005 15.8103L11.12 12.7903C11.4 12.5103 11.77 12.3703 12.14 12.3703C12.51 12.3703 12.88 12.5103 13.16 12.7903L16.17 15.8003C16.55 16.1803 17.07 16.4003 17.62 16.4003H18.88L21.13 14.1403C21.6942 13.5677 22.0088 12.795 22.005 11.9912C22.0013 11.1873 21.6795 10.4177 21.11 9.85031Z" />
	                        </svg>
	                        Pix
	                        <svg class="multt-checked-icon-checkout" width="37" height="37" viewBox="0 0 37 37" fill="none" xmlns="http://www.w3.org/2000/svg">
	                            <path d="M32.6438 14.8607C32.0989 14.2913 31.5353 13.7045 31.3228 13.1885C31.1262 12.7159 31.1147 11.9325 31.1031 11.1737C31.0814 9.76309 31.0583 8.16457 29.9469 7.05312C28.8354 5.94168 27.2369 5.91855 25.8263 5.89687C25.0675 5.88531 24.2841 5.87375 23.8115 5.67719C23.297 5.46473 22.7087 4.90105 22.1393 4.35617C21.142 3.39793 20.0089 2.3125 18.5 2.3125C16.9911 2.3125 15.8594 3.39793 14.8607 4.35617C14.2913 4.90105 13.7045 5.46473 13.1885 5.67719C12.7188 5.87375 11.9325 5.88531 11.1737 5.89687C9.76309 5.91855 8.16457 5.94168 7.05312 7.05312C5.94168 8.16457 5.92578 9.76309 5.89687 11.1737C5.88531 11.9325 5.87375 12.7159 5.67719 13.1885C5.46473 13.703 4.90105 14.2913 4.35617 14.8607C3.39793 15.858 2.3125 16.9911 2.3125 18.5C2.3125 20.0089 3.39793 21.1406 4.35617 22.1393C4.90105 22.7087 5.46473 23.2955 5.67719 23.8115C5.87375 24.2841 5.88531 25.0675 5.89687 25.8263C5.91855 27.2369 5.94168 28.8354 7.05312 29.9469C8.16457 31.0583 9.76309 31.0814 11.1737 31.1031C11.9325 31.1147 12.7159 31.1262 13.1885 31.3228C13.703 31.5353 14.2913 32.0989 14.8607 32.6438C15.858 33.6021 16.9911 34.6875 18.5 34.6875C20.0089 34.6875 21.1406 33.6021 22.1393 32.6438C22.7087 32.0989 23.2955 31.5353 23.8115 31.3228C24.2841 31.1262 25.0675 31.1147 25.8263 31.1031C27.2369 31.0814 28.8354 31.0583 29.9469 29.9469C31.0583 28.8354 31.0814 27.2369 31.1031 25.8263C31.1147 25.0675 31.1262 24.2841 31.3228 23.8115C31.5353 23.297 32.0989 22.7087 32.6438 22.1393C33.6021 21.142 34.6875 20.0089 34.6875 18.5C34.6875 16.9911 33.6021 15.8594 32.6438 14.8607ZM25.0993 15.8493L17.0055 23.943C16.8982 24.0506 16.7706 24.1358 16.6303 24.194C16.4899 24.2522 16.3394 24.2822 16.1875 24.2822C16.0356 24.2822 15.8851 24.2522 15.7447 24.194C15.6044 24.1358 15.4768 24.0506 15.3695 23.943L11.9007 20.4743C11.7933 20.3669 11.7081 20.2393 11.6499 20.099C11.5918 19.9586 11.5619 19.8082 11.5619 19.6562C11.5619 19.5043 11.5918 19.3539 11.6499 19.2135C11.7081 19.0732 11.7933 18.9456 11.9007 18.8382C12.1177 18.6212 12.4119 18.4994 12.7188 18.4994C12.8707 18.4994 13.0211 18.5293 13.1615 18.5874C13.3018 18.6456 13.4294 18.7308 13.5368 18.8382L16.1875 21.4904L23.4632 14.2132C23.5706 14.1058 23.6982 14.0206 23.8385 13.9624C23.9789 13.9043 24.1293 13.8744 24.2812 13.8744C24.4332 13.8744 24.5836 13.9043 24.724 13.9624C24.8643 14.0206 24.9919 14.1058 25.0993 14.2132C25.2067 14.3206 25.2919 14.4482 25.3501 14.5885C25.4082 14.7289 25.4381 14.8793 25.4381 15.0312C25.4381 15.1832 25.4082 15.3336 25.3501 15.474C25.2919 15.6143 25.2067 15.7419 25.0993 15.8493Z" fill="#DDB6EA"/>
	                        </svg>  
	                    </label>
	                </div>
                </div>
            </fieldset>
            <hr/>
            <div class="multt-checkout-form-pt-2">
            	<div class="row" id="cartaoDeCredito">
	                <div class="col-md-12 multt-input-icon-group">
	                    <input type="text" placeholder="Número de Cartão de Crédito" class="form-control"/>
	                    <svg width="23" height="30" viewBox="0 0 23 29" fill="none" xmlns="http://www.w3.org/2000/svg">
	                        <path d="M11.5 0L0 5.27273V13.1818C0 20.4977 4.90667 27.3391 11.5 29C18.0933 27.3391 23 20.4977 23 13.1818V5.27273L11.5 0ZM11.5 7.90909C13.2889 7.90909 15.0778 9.35909 15.0778 11.2045V13.1818C15.8444 13.1818 16.6111 13.9727 16.6111 14.8955V19.5091C16.6111 20.3 15.8444 21.0909 14.95 21.0909H7.92222C7.15556 21.0909 6.38889 20.3 6.38889 19.3773V14.7636C6.38889 13.9727 7.15556 13.1818 7.92222 13.1818V11.2045C7.92222 9.35909 9.71111 7.90909 11.5 7.90909ZM11.5 9.49091C10.4778 9.49091 9.58333 10.15 9.58333 11.2045V13.1818H13.4167V11.2045C13.4167 10.15 12.5222 9.49091 11.5 9.49091Z" fill="#999999"/>
	                    </svg>
	                </div>
	                <div class="col-md-4 mt-3">
	                    <select class="form-control" class="form-control">
	                        <option value="">Mês</option>
	                    </select>
	                </div>
	                <div class="col-md-3 mt-3">
	                    <select class="form-control" class="form-control">
	                        <option value="">Ano</option>
	                    </select>
	                </div>
	                <div class="col-md-5 mt-3 multt-input-icon-group">
	                    <input type="text" placeholder="Cód. de Segurança" class="form-control"/>
	                    <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
	                        <path d="M10.9375 14.0625C10.9375 14.2479 10.8825 14.4292 10.7795 14.5833C10.6765 14.7375 10.5301 14.8577 10.3588 14.9286C10.1875 14.9996 9.99896 15.0182 9.81711 14.982C9.63525 14.9458 9.4682 14.8565 9.33709 14.7254C9.20598 14.5943 9.11669 14.4273 9.08052 14.2454C9.04434 14.0635 9.06291 13.875 9.13387 13.7037C9.20482 13.5324 9.32499 13.386 9.47916 13.283C9.63333 13.18 9.81458 13.125 10 13.125C10.2486 13.125 10.4871 13.2238 10.6629 13.3996C10.8387 13.5754 10.9375 13.8139 10.9375 14.0625ZM10 5.625C8.27657 5.625 6.875 6.88672 6.875 8.4375V8.75C6.875 8.91576 6.94085 9.07473 7.05806 9.19194C7.17527 9.30915 7.33424 9.375 7.5 9.375C7.66576 9.375 7.82474 9.30915 7.94195 9.19194C8.05916 9.07473 8.125 8.91576 8.125 8.75V8.4375C8.125 7.57812 8.96641 6.875 10 6.875C11.0336 6.875 11.875 7.57812 11.875 8.4375C11.875 9.29688 11.0336 10 10 10C9.83424 10 9.67527 10.0658 9.55806 10.1831C9.44085 10.3003 9.375 10.4592 9.375 10.625V11.25C9.375 11.4158 9.44085 11.5747 9.55806 11.6919C9.67527 11.8092 9.83424 11.875 10 11.875C10.1658 11.875 10.3247 11.8092 10.4419 11.6919C10.5592 11.5747 10.625 11.4158 10.625 11.25V11.1938C12.05 10.932 13.125 9.79531 13.125 8.4375C13.125 6.88672 11.7234 5.625 10 5.625ZM18.125 10C18.125 11.607 17.6485 13.1779 16.7557 14.514C15.8629 15.8502 14.594 16.8916 13.1093 17.5065C11.6247 18.1215 9.99099 18.2824 8.4149 17.9689C6.8388 17.6554 5.39106 16.8815 4.25476 15.7452C3.11846 14.6089 2.34463 13.1612 2.03112 11.5851C1.71762 10.009 1.87852 8.37535 2.49348 6.8907C3.10844 5.40605 4.14985 4.1371 5.486 3.24431C6.82214 2.35152 8.39303 1.875 10 1.875C12.1542 1.87727 14.2195 2.73403 15.7427 4.25727C17.266 5.78051 18.1227 7.84581 18.125 10ZM16.875 10C16.875 8.64025 16.4718 7.31104 15.7164 6.18045C14.9609 5.04987 13.8872 4.16868 12.631 3.64833C11.3747 3.12798 9.99238 2.99183 8.65876 3.2571C7.32514 3.52237 6.10013 4.17716 5.13864 5.13864C4.17716 6.10013 3.52238 7.32513 3.2571 8.65875C2.99183 9.99237 3.12798 11.3747 3.64833 12.6309C4.16868 13.8872 5.04987 14.9609 6.18046 15.7164C7.31105 16.4718 8.64026 16.875 10 16.875C11.8227 16.8729 13.5702 16.1479 14.8591 14.8591C16.1479 13.5702 16.8729 11.8227 16.875 10Z" fill="#999999"/>
	                    </svg>
	                </div>
	                <div class="col-md-12 mt-3">
	                    <select class="form-control" class="form-control">
	                        <option value="12x de R$ 14,76">12x de R$ 14,76</option>
	                    </select>
	                </div>
                </div>
                <div class="row" id="boleto" style="display: none">                
	                <div class="col-md-4 info-boleto  mt-3" >
	                	<div>
	                		<i class="fal fa-calendar-day"></i>
	                		<h5>Pague até a data de vencimento</h5>
	                		<p>Faça o pagamento até a data de vencimento e garanta seu acesso ao produto.</p>
	                	</div>
	                </div>
	                <div class="col-md-4 info-boleto  mt-3">
	                	<div>
	                		<i class="fal fa-calendar-day"></i>
	                		<h5>Pague até a data de vencimento</h5>
	                		<p>Faça o pagamento até a data de vencimento e garanta seu acesso ao produto.</p>
	                	</div>
	                </div>
	                <div class="col-md-4 mt-3">
	                	<div>
	                		<i class="fal fa-calendar-day"></i>
	                		<h5>Pague até a data de vencimento</h5>
	                		<p>Faça o pagamento até a data de vencimento e garanta seu acesso ao produto.</p>
	                	</div>
	                </div>
                </div>
                <div class="row" id="pix" style="display: none">    	               
                	<div class="checkout-menu-pix-container col-md-6">        		
               			<p>Após o pagamento, você receberá por email as informações de acesso à sua compra</p>		                	
                		<button class="btn btn-primary">
                			Gerar código QR
                		</button>
	                	<button class="btn btn-primary">Copiar Código Pix</button>
	                </div>
	                <div class="checkout-pix-QRCode-container col-md-6 d-flex justify-content-end">
	                	<svg width="231" height="231" viewBox="0 0 512 512" xmlns="http://www.w3.org/2000/svg">
						  <path class="" fill="currentColor" d="M144 64H48c-8.8 0-16 7.2-16 16v96c0 8.8 7.2 16 16 16h96c8.8 0 16-7.2 16-16V80c0-8.8-7.2-16-16-16zM48 32h96c26.5 0 48 21.5 48 48v96c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V80C0 53.5 21.5 32 48 32zm96 288H48c-8.8 0-16 7.2-16 16v96c0 8.8 7.2 16 16 16h96c8.8 0 16-7.2 16-16V336c0-8.8-7.2-16-16-16zM48 288h96c26.5 0 48 21.5 48 48v96c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V336c0-26.5 21.5-48 48-48zM304 64c-8.8 0-16 7.2-16 16v96c0 8.8 7.2 16 16 16h96c8.8 0 16-7.2 16-16V80c0-8.8-7.2-16-16-16H304zM256 80c0-26.5 21.5-48 48-48h96c26.5 0 48 21.5 48 48v96c0 26.5-21.5 48-48 48H304c-26.5 0-48-21.5-48-48V80zm0 224c0-8.8 7.2-16 16-16h64c8.8 0 16 7.2 16 16v68h64V304c0-8.8 7.2-16 16-16s16 7.2 16 16v84c0 8.8-7.2 16-16 16H336c-8.8 0-16-7.2-16-16V320H288V472c0 8.8-7.2 16-16 16s-16-7.2-16-16V304zM88 104h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16H88c-8.8 0-16-7.2-16-16V120c0-8.8 7.2-16 16-16zM72 376c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16H88c-8.8 0-16-7.2-16-16V376zM344 104h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16H344c-8.8 0-16-7.2-16-16V120c0-8.8 7.2-16 16-16zM320 448c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16H336c-8.8 0-16-7.2-16-16V448zm96-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16H416c-8.8 0-16-7.2-16-16V448c0-8.8 7.2-16 16-16z"></path>
						</svg>	
	                </div>
                </div>
            </div>
            
            <div class="multt-checkout-order-bump-container">
            	<c:forEach items="${checkout.oferta.produto.listarBumps()}" var="bump">
	            	<div class="display-order-bump p-3">
	            		<c:if test="${not empty bump.produto.pegarPrimeiraImagem() and bump.exibirImagemDoProduto == true}">
	            			<div style="background-image: url('${sessao.urlPadrao}arquivos/imagens/${bump.produto.pegarPrimeiraImagem()}')" class="checkout-img-container-bump checkout-img-bump-molile checkout-bump-mobile"></div>
	            		</c:if>
	            		<div class="card d-flex flex-row m-0">
	            			<c:if test="${not empty bump.produto.pegarPrimeiraImagem() and bump.exibirImagemDoProduto == true}">
								<div style="background-image: url('${sessao.urlPadrao}arquivos/imagens/${bump.produto.pegarPrimeiraImagem()}')" class="checkout-img-container-bump checkout-img-bump-desktop checkout-bump-desktop"></div>
	            			</c:if>
	            			<div class="checkout-container-bump">
								<div class="card-header" <c:if test="${bump.exibirImagemDoProduto == false}">style="border-top-left-radius:calc(0.5rem - 1px);"</c:if>>
									<div class="d-flex align-items-center">							
										<h3 class="checkout-bump-call-to-action"> ${bump.callToAction} </h3>
										<i class="fas fa-arrow-alt-right mr-2 ml-2"></i>
										<input type="checkbox"/>
									</div>
								</div>
								<div class="card-body">
									<h4>
										<span class="checkout-bump-titulo"> ${bump.titulo}: </span> 
										<span class="checkout-bump-detalhes"> ${bump.descricao} - </span>
										<span class="checkout-bump-valor">R$ ${bump.oferta.pegarValorFormatado()} </span>
									</h4>
								</div>
							</div>
	            		</div>     		
	            	</div>
            	</c:forEach>
            </div>
            
            <div class="mt-3">
                <div class="mb-3">
                    <input type="checkbox"/>
                    Salvar dados para as próximas compras
                </div>
                <p class="mult-icon-text">
                    <svg width="17" height="17" viewBox="0 0 23 29" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path stroke="#9448AE" fill="#9448AE" d="M11.5 0L0 5.27273V13.1818C0 20.4977 4.90667 27.3391 11.5 29C18.0933 27.3391 23 20.4977 23 13.1818V5.27273L11.5 0ZM11.5 7.90909C13.2889 7.90909 15.0778 9.35909 15.0778 11.2045V13.1818C15.8444 13.1818 16.6111 13.9727 16.6111 14.8955V19.5091C16.6111 20.3 15.8444 21.0909 14.95 21.0909H7.92222C7.15556 21.0909 6.38889 20.3 6.38889 19.3773V14.7636C6.38889 13.9727 7.15556 13.1818 7.92222 13.1818V11.2045C7.92222 9.35909 9.71111 7.90909 11.5 7.90909ZM11.5 9.49091C10.4778 9.49091 9.58333 10.15 9.58333 11.2045V13.1818H13.4167V11.2045C13.4167 10.15 12.5222 9.49091 11.5 9.49091Z" />
                    </svg> Nós protegemos seus dados de pagamento usando encriptação para prover segurança no nível de bancos.
                </p>
                <p class="mult-icon-text">
                    <svg class="my-svg" width="17" height="17" viewBox="0 0 21 21" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill="#9448AE"  stroke="#9448AE" class="my-path" d="M3.9375 2.625V18.375H17.0625V2.625H3.9375ZM3.28125 1.3125H17.7188C17.8928 1.3125 18.0597 1.38164 18.1828 1.50471C18.3059 1.62778 18.375 1.7947 18.375 1.96875V19.0312C18.375 19.2053 18.3059 19.3722 18.1828 19.4953C18.0597 19.6184 17.8928 19.6875 17.7188 19.6875H3.28125C3.1072 19.6875 2.94028 19.6184 2.81721 19.4953C2.69414 19.3722 2.625 19.2053 2.625 19.0312V1.96875C2.625 1.7947 2.69414 1.62778 2.81721 1.50471C2.94028 1.38164 3.1072 1.3125 3.28125 1.3125ZM6.5625 10.5H14.4375V11.8125H6.5625V10.5ZM6.5625 6.5625H10.5V7.875H6.5625V6.5625ZM6.5625 14.4375H14.4375V15.75H6.5625V14.4375Z" fill="#673279"/>
                    </svg> A cobrança aparecerá na sua fatura como: PG MULTT⭑FORNECEDOR
                </p>
            </div>
            <div class="text-center mt-3 finalizar-checkout">
                <button class="btn btn-primary btn-mullt-finalizar-checkout">Pagar agora</button>
            </div>
        </form>
       
    </section>
    <footer class="mt-4 conainer-multt-padrao">
        <div class="text-center">
            <img class="logo-checkout mb-4" src="${sessao.urlPadrao}/img/LOGO_MULTT_CINZA.png" alt="Logo Multt"/>
            <p>
                Esse site é protegido pelo reCAPTCHA do Google Política de Privacidade e Termos de Serviço<br/>
                *Parcelamento com tarifa de 2.99% a.m<br/>
                Ao prosseguir você concorda com os Termos de Compra
            </p>
        </div>
        <div class="text-center">
            <p>
                <img class="mr-2" src="${sessao.urlPadrao}/img/LOGO_MULTT_PRETO.png" alt="Logo Multt"/>
                Copyright © 2024 - multt. Todos os direitos reservados.
            </p>
        </div>
    </footer>

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
		
		<script type="text/javascript" src="${sessao.urlPadrao}js/plugins/img-upload/image-uploader.min.js"></script>


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