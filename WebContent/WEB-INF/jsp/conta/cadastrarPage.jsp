<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">        

</head>
<style>
    .container-geral-cadastro{
        width: 100vw;
        height: 100vh;
        background-color: #fffcfc;
    }

    .holder-cadastro{
        width: 100%;
        height: 100%;
    }

    .trasparent-input{
        background-color: transparent !important;
    }

    .bg-cadastro img{
        height: 100%;
        max-height: 100%;
    }

    .bg-cadastro-2{
        background-image: url(${sessao.urlPadrao}/img/pexels-pixabay-267569\ 2.png);
        background-repeat: no-repeat;
        background-size: cover;
        position: absolute;
        top: 0;
        mix-blend-mode: soft-light;
        z-index:1;
        
    }

    .form-cadastro-section label{
        width: 100%;
        text-align: left;
    }

    .form-cadastro-container{
        position: relative;
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        text-align: -webkit-right;
    }

    .form-cadastro{
        margin-left: 30px;
        margin-right: 30px;
        max-width: 605px;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        z-index: 999;
    }

    .bg-cadastro-1{
        height: 100%;
    }

    .form-cadastro-section{
        display: flex;
        flex-direction: column;
    }

    .form-cadastro-section input{
        margin-bottom: 10px;
        border-color: rgba(51, 51, 51, 0.63);;
    }

    .form-cadastro-section input::placeholder{
        color: rgba(51, 51, 51, 0.603);
    }

    .form-cadastro-footer{
        position: absolute;
        bottom: 0px;
        left: 0px;
        display: flex;
        margin-left: 30px;
        margin-right: 30px;
    }

    .form-cadastro-section label,p{
        color: rgb(0, 0, 0);
    }

    .form-cadastro-section a{
        font-family: "Bebas Neue", sans-serif;
        font-size: 12px;
        font-weight: 400;
        line-height: 22.33px;
        letter-spacing: 0.08em;
        text-align: left;
        color: rgba(51, 51, 51, 0.603);

    }

    .form-cadastro-header{
        margin-bottom: 50px;
    }

    .form-cadastro-footer img{
        height: 11px;
        margin-right: 10px;
    }

    .btn-acessar-cadastro{
        width: 100%;
        background-color: #9448AE !important;
        border-color: #9448AE;
        margin-top: 15px;
    }
    
    .cadastro-termos-de-uso{
        text-align: left;
    }

    .cadastro-termos-de-uso{
        color: #707070b7;
        font-size: 14px;
        margin-left: 10px;
        margin-right: 10px;
    }

    .cadastro-termos-de-uso a{
       color: #9448AE;
       font-family: Arial, Helvetica, sans-serif;
       font-weight: bold;
    }

    .cadastro-termos-de-uso input{
        transform: scale(1.2);
        margin-right: 5px;
    }

    .form-cadastro-footer-links{
       color: #707070b7;
       margin-top: 6px;
       font-size: 14px;
    }

    .form-cadastro-footer-links a{
       color: #9448AE;
       font-family: Arial, Helvetica, sans-serif;
       font-weight: bold;
       margin-left: 5px;
       text-decoration: none;
    }
    
    .btn-erro-login{
        width: 100%;
        background-color: #c82333;
        border-radius: 0.25rem;
        padding: .375rem .75rem;
        margin-bottom: 15px;
        text-align: center;
        color: white;
    }
    
    .btn-alert-login{
		width: 100%;
        background-color: rgb(176, 144, 5);
        border-radius: 0.25rem;
        padding: .375rem .75rem;
        margin-bottom: 15px;
        text-align: center;
        color: white;
	}
	
	.btn-msg-login{
		width: 100%;
        background-color: rgb(26, 95, 180);
        border-radius: 0.25rem;
        padding: .375rem .75rem;
        margin-bottom: 15px;
        text-align: center;
        color: white;
	}

    @media (min-width: 931px) {
        .desktop-cadastro{
            display: block;
        }

        .mobile-cadastro{
            display: none;
            position: absolute;
            max-width: 0px;
            max-height: 0px;
        }

        .hide-momile-footer-cadastro{
            display: none;
            position: absolute;
            max-width: 0px;
            max-height: 0px;
        }
    }

    @media (max-width: 931px) {
       .desktop-cadastro{
            display: none;
            position: absolute;
            max-width: 0px;
        }

        .form-cadastro-footer-mobile .logo{
            height: 11px;
            margin-right: 10px;
        }

        .form-cadastro-footer-mobile{
            position: fixed;
            bottom: 0px;
            left: 0px;
            width: 100vw;
            background-image: url(${sessao.urlPadrao}/img/pexels-pixabay-267569\ 2.png);
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center;
        }

        .bg-cadastro-1{
            background-color: rgb(255, 255, 255);
            height: 100%;
            width: 100%;
        }

        .mobile-cadastro{
            padding-left: 20px;
            padding-right: 20px;
            height: 100%;
        }

        .mobile-form-container-cadastro{
            display: flex;
            justify-content: center;
            flex-direction: column;
        }

        .form-cadastro-header{
            margin-bottom: 40px;
            text-align: center;
        }

        .bg-phantom-detalahe-cadastro {
            padding: 10px;
            height: 100%;
            background-color: #673279ad;
        }

        .bg-phantom-detalahe-cadastro img{
           height: 15px !important;
        }

        .bg-phantom-detalahe-cadastro p{
            margin: 0px;
            font-size: 10px;;
            color: white;
        }

        .logo-footer-container-cadastro{
            width: 100%;
            text-align: center;
        }
    }

</style>
<body>
	<form class="validateForm load" action="${sessao.urlPadrao}contas/cadastrar" method="post" role="form" enctype="multipart/form-data">
	    <section class="container-geral-cadastro">
	        <div class="d-flex holder-cadastro">
	
	            <div class="bg-cadastro form-cadastro-container desktop-cadastro">
	                <div class="bg-cadastro-2"></div>
	                <div class="form-cadastro">
	                    <div class="form-cadastro-header">
	                        <img src="${sessao.urlPadrao}/img/LOGO_MULTT_COM_COR.png"/>
	                    </div>
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
	                    <div class="form-cadastro-section">
	                        <div>
	                            <label for="nomeUsuario"> Nome </label>
	                            <input name="" type="text" placeholder="Seu nome" id="nomeUsuario" class="form-control trasparent-input"/>
	                        </div>
	                        <div>
	                            <label for="emailUsuario"> E-mail </label>
	                            <input name="" type="text" placeholder="Seu e-mail" id="emailUsuario" class="form-control trasparent-input"/>
	                        </div>                        
                            <div>
                                <label for="nomeUsuario"> Celular </label>
                                <input type="text" placeholder="Número de telefone" id="celularUsuario" class="form-control trasparent-input" alt="phone"/>
                            </div>
	                        <div>
	                            <label for="senhaUsuario"> Senha </label>                        
	                            <div>                 
	                                <input type="password" placeholder="*************" id="senhaUsuario" class="form-control trasparent-input"/>
	                            </div>
	                            <div class="cadastro-termos-de-uso">
	                                <input type="checkbox"/>
	                                Eu concordo com os <a href="#">Termos de Uso e Políticas</a>, Tenho ciência 
	                                da <a href="#">Política de Privacidade</a> do Greenn e declaro ser maior 
	                                de idade de acordo com a legislação aplicável.                           
	                            </div>
	                        </div>
	                        <div>
	                            <button class="btn btn-primary btn-acessar-cadastro">CADASTRE-SE AGORA</button>
	                        </div>
	                        <div class="d-flex justify-content-center form-cadastro-footer-links">
	                           Já possui uma conta? <a href="#">Faça Login</a>
	                        </div>
	                    </div>
	                </div> 
	            </div>
	            <div class="bg-cadastro bg-cadastro-1 ">
	                <img class="desktop-cadastro" src="${sessao.urlPadrao}/img/PRINT_2.png"/>
	                <div class="mobile-cadastro mobile-form-container-cadastro">
	                    <div class="form-cadastro-header">
	                        <img src="${sessao.urlPadrao}/img/LOGO_MULTT_COM_COR.png"/>
	                    </div>
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
	                    <div class="form-cadastro-section">
	                        <div>
	                            <label for="nomeUsuario"> Nome </label>
	                            <input name="" type="text" placeholder="Seu nome" id="nomeUsuarioMobile" class="form-control trasparent-input"/>
	                        </div>
	                        <div>
	                            <label for="emailUsuario"> E-mail </label>
	                            <input name="" type="text" placeholder="Seu e-mail" id="emailUsuarioMobile" class="form-control trasparent-input"/>
	                        </div>	                       
                            <div>
                                <label for="nomeUsuario"> Celular </label>                          
                                <input type="text" placeholder="Número de telefone" id="celularUsuarioMobile" class="form-control trasparent-input" alt="phone"/>
                            </div>
	                        <div>
	                            <label for="senhaUsuario"> Senha </label>                        
	                            <div>                 
	                                <input type="password" placeholder="*************" id="senhaUsuarioMobile" class="form-control trasparent-input"/>
	                            </div>
	                            <div class="cadastro-termos-de-uso">
	                                <input type="checkbox"/>
	                                Eu concordo com os <a href="#">Termos de Uso e Políticas</a>, Tenho ciência 
	                                da <a href="#">Política de Privacidade</a> do Greenn e declaro ser maior 
	                                de idade de acordo com a legislação aplicável.
	                                
	                            </div>
	                        </div>
	                        <div>
	                            <button class="btn btn-primary btn-acessar-cadastro">CADASTRE-SE AGORA</button>
	                        </div>
	                        <div class="d-flex justify-content-center form-cadastro-footer-links">
	                           Já possui uma conta? <a href="#">Faça Login</a>
	                        </div>
	                    </div>
	                    <div class="form-cadastro-footer-mobile hide-momile-footer-cadastro">
	                        <div class="bg-phantom-detalahe-cadastro">
	                            <div class="logo-footer-container-cadastro">
	                                <img class="logo" src="${sessao.urlPadrao}/img/LOGO MULTT.png" alt="Logo Multt"/>
	                            </div>
	                            <p class="text-center">     
	                                Copyright © 2024 - multt. Todos os direitos reservados.
	                            </p>
	                        </div>
	                    </div>
	                </div>            
	            </div>
	           
	            
	        </div>      
	    </section>
    </form>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="${sessao.urlPadrao}js/plugins/mascara/meiomask.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<script src="${sessao.urlPadrao}js/admin/controller/cadastro.js"></script>
	
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
</body>
</html>