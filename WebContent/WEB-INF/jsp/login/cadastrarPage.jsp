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
        width: 100%;
        height: 100%;
        mix-blend-mode: soft-light;
        
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
    <section class="container-geral-cadastro">
        <div class="d-flex holder-cadastro">

            <div class="bg-cadastro form-cadastro-container desktop-cadastro">
                <div class="bg-cadastro-2"></div>
                <div class="form-cadastro">
                    <div class="form-cadastro-header">
                        <img src="${sessao.urlPadrao}/img/LOGO_MULTT_COM_COR.png"/>
                    </div>
                    <div class="form-cadastro-section">
                        <div>
                            <label for="nomeUsuario"> Nome </label>
                            <input type="text" placeholder="Seu nome" id="nomeUsuario" class="form-control trasparent-input"/>
                        </div>
                        <div>
                            <label for="emailUsuario"> E-mail </label>
                            <input type="text" placeholder="Seu e-mail" id="nomeUsuario" class="form-control trasparent-input"/>
                        </div>
                        <div class="row">
                            <div class="col-4">
                                <label for="nomeUsuario"> Telefone </label>
                                <input type="text" placeholder="+55" id="nomeUsuario" class="form-control trasparent-input"/>
                            </div>
                            <div class="col-8 d-flex align-items-end">
                                <input type="text" placeholder="Número de telefone" id="nomeUsuario" class="form-control trasparent-input"/>
                            </div>
                        </div>
                        <div>
                            <label for="senhaUsuario"> Senha </label>                        
                            <div>                 
                                <input type="text" placeholder="*************" id="senhaUsuario" class="form-control trasparent-input"/>
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
                    <div class="form-cadastro-section">
                        <div>
                            <label for="nomeUsuario"> Nome </label>
                            <input type="text" placeholder="Seu nome" id="nomeUsuario" class="form-control trasparent-input"/>
                        </div>
                        <div>
                            <label for="emailUsuario"> E-mail </label>
                            <input type="text" placeholder="Seu e-mail" id="nomeUsuario" class="form-control trasparent-input"/>
                        </div>
                        <div class="row">
                            <div class="col-4">
                                <label for="nomeUsuario"> Telefone </label>
                                <input type="text" placeholder="+55" id="nomeUsuario" class="form-control trasparent-input"/>
                            </div>
                            <div class="col-8 d-flex align-items-end">
                                <input type="text" placeholder="Número de telefone" id="nomeUsuario" class="form-control trasparent-input"/>
                            </div>
                        </div>
                        <div>
                            <label for="senhaUsuario"> Senha </label>                        
                            <div>                 
                                <input type="text" placeholder="*************" id="senhaUsuario" class="form-control trasparent-input"/>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>