"use strict";

(function() {
	$('nav.navbar .nav-link').each(function(i, link){
		var dashboardHref = urlPadrao+'adm';
		var linkHref = $(link).attr('href');
		var winHref = window.location.href;


		var isDashboard = linkHref == dashboardHref;

		if(		(!isDashboard 	&& winHref.startsWith(linkHref)) ||
				(isDashboard 	&& winHref == dashboardHref)){

			$(link).addClass('active');

			var navItem = $(link).closest('.nav-colapse-item');

			if(navItem){
				navItem.removeClass('collapsed');
				navItem.find('.collapse').addClass('show');
				navItem.find('.nav-link[data-toggle="collapse"]').attr('aria-expanded', 'true');
			}
		}
	})

	$(document).on('click', '.button-remove', function(){
		var $this = $(this);
		var url = $this.attr("data-url");
		var mensagem = $this.attr("data-message");

		new Notificacao('Atenção!', mensagem).dialogo().confirm(function(){
			document.location = url;
		});
	});

	$(document).on('click', '.button-remove-ajax', function(){
		var $this = $(this);
		var url = $this.attr("data-url");
		var mensagem = $this.attr("data-message");
		var removeClosest = $this.attr("data-remove-closest");

		new Notificacao('Atenção!', mensagem).dialogo().confirm(function(){
			$.ajax(url)
			.always(function(){
				new Notificacao('Sucesso!', '').sucesso();
				swal.close();
				if(removeClosest){
					$this.closest('.list-group-item').remove();
				}
			});
		});
	});

	$(document).on('click','#collapseMenuLink',function(){

		var isOpen = $(this).attr('data-is-open')

		if(isOpen == "true"){
			$('#sidebar').addClass('hidden')
			$('#main-content').addClass('hidden')
			$(this).attr('data-is-open',"false")

		}else{
			$('#sidebar').removeClass('hidden')
			$('#main-content').removeClass('hidden')
			$(this).attr('data-is-open',"true")

		}

		var isOpenAtualizado = $(this).attr('data-is-open')
		var idUsuario = $(this).attr('data-usuario-id')
			$.ajax({

			        url: urlPadrao + "adm/usuarios/admin-tag/ajax",
			        type: "POST",
			        data: {
						"usuario.id": idUsuario,
						"usuario.abaLateral": isOpenAtualizado,
					},
			        success: function(data) {

			        },
			        error: function() {


			        }
			      })

	})

	//.........Img.Input.File..............................................................................................
	
	$('.input-file-field input[type="file"]').change(function() {
        carregarImagem(this);
        $(this).closest('.input-file-field').css('border-color','#CECECE');
    });
    
    $('.input-file-field img').click(function() {
        var imagemSelecionada = $(this).closest('.input-file-field').find('img').attr('src');
        $('#display-imagem-jogo').attr('src',imagemSelecionada);
        $('#imagemJogoSelecionada').modal('show');
    });
    
    $('.sendForm').click(function() {
        verificaValidadeInputImagemParaForm(); 
    });
    
//.....................................................................................................................

	iniciarFuncoesDeCep();
})();

//.........Img.Input.File..............................................................................................

	function carregarImagem(input) {
		var aceitaVideo = $(input).attr('data-aceita-video')
		
        if (input.files && input.files[0]) {
            var leitor = new FileReader();
			var nomeArquivo = input.files[0].name;
			var campoImagem = $(input).closest('.input-file-field').find('img');
			var campoVideo = aceitaVideo =='true' ? $(input).closest('.input-file-field').find('video') : null;
			var campoTexto = $(input).closest('.input-file-field').find('label');

		if (aceitaVideo == 'false' ? validarArquivoDeImagem(nomeArquivo) : validarArquivoDeImagemEVideo(nomeArquivo)) {
			leitor.onload = function(e) {
				if (!nomeArquivo.includes('.mp4')) {
					$(campoVideo).hide();
					$(campoImagem).attr('src', e.target.result);
					$(campoImagem).show();
				}
				if(nomeArquivo.includes('.mp4')){
					$(campoImagem).hide();
					$(campoVideo).attr('poster', e.target.result);
				}

			}
			leitor.readAsDataURL(input.files[0]);
		} else {
			new Notificacao(`Só sao permitidas as seguintes extensoes para imagens: jpg, jpeg, png, webp${aceitaVideo == "false"? " ou avif" : ", avif ou mp4"}`).erro();
			$(campoImagem).attr('src', urlPadrao + 'img/sem-imagem.jpg');
			$(campoImagem).show();
			if(possuiValorSemValidarZero($(campoVideo))){
				$(campoVideo).hide();
				$(campoVideo).attr('poster', '');
			}
			$('.file-input-container label').html('Selecionar arquivo...');
			$(input).val('');

		}
	}
}   
    
    function validarArquivoDeImagemEVideo(nomeArquivo){
		if(nomeArquivo.includes(".mp4")){
    		return true;
    	}
		return validarArquivoDeImagem(nomeArquivo)
	} 
    
    function validarArquivoDeImagem(nomeArquivo){
    	if(nomeArquivo.includes(".jpg")){
    		return true;
    	}
    	
    	if(nomeArquivo.includes(".png")){
    		return true;
    	}
    	
    	if(nomeArquivo.includes(".jpeg")){
    		return true;
    	}
    	
    	if(nomeArquivo.includes(".webp")){
    		return true;
    	}
    	
    	if(nomeArquivo.includes(".avif")){
    		return true;
    	}
    	
    	return false;
    	
    }
    
    function verificaValidadeInputImagemParaForm(){
    	$('.input-file-field').each(function(index,inputFileField){	
    		var input = $(inputFileField).find('input');
    		if($(input).hasClass('required')){	
	    		if(!inputFileTemConteudo(input)){
	    			$(inputFileField).css('border-color','red');		
	    		} 		
    		}
    	})
    }
    
    function inputFileTemConteudo(input){
    	var inputFile = $(input);
		if (inputFile[0].files.length > 0) {
		    return true;
		} else {
		     return false;
		}
    }

//.....................................................................................................................

function notificacao(background, message){
	var html = '' +
	'<div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">'+
		'<div class="toast-body bg-'+background+' text-white">'+
			message+
			'<button type="button" class="ml-2 mb-1 close text-white" data-dismiss="toast" aria-label="Close">'+
			  '<span aria-hidden="true">&times;</span>'+
			'</button>'+
		'</div>'+
	'</div>';

	$('.toast').remove();
	$('body').append(html);
	$('.toast').toast({delay: 3*1000}).toast('show')
}

function renderTipoUsuario(tipo){
	try{
		switch (tipo) {
			case 'ADMINISTRADOR': return 'Administrador';
			case 'FUNCIONARIO': return 'Funcionário';
			case 'MENSALISTA': return 'Mensalista';
			case 'PROFESSOR': return 'Professor';
			case 'ALUNO': return 'Aluno';
			case 'OUTRO': return 'Outro';
		}
		return '';
	}catch(err){
		console.error('function renderTipoUsuario(tipo)', tipo, err);
		return '';
	}
}
function renderDinheiro(data, type, row){
	if(data == null)
		return '';
	try{
		return parseFloat(data).emReal();
	}catch (e) {
		return '';
	}
}

function renderDecimal(data, type, row){
	if(data == null)
		return '';

	try{
		return parseFloat(data).toFixed(2);
	}catch (e) {
		return '';
	}
}

Number.prototype.emReal = function() {
	var numero = this.toFixed(2).split('.');
	numero[0] = "R$ " + numero[0].split(/(?=(?:...)*$)/).join('.');
	return numero.join(',');
};

function numeroParaReal(numero) {
	var numero = numero.toFixed(2).split('.');
	numero[0] = "R$ " + numero[0].split(/(?=(?:...)*$)/).join('.');
	return numero.join(',');
}
function numeroParaRealSemCifrao(numero) {
	var numero = numero.toFixed(2).split('.');
	numero[0] =  numero[0].split(/(?=(?:...)*$)/).join('.');
	return numero.join(',');
}

function fecharModal(modal){
	$(modal).modal('hide');
}
function closeModal(modal){
	$("#"+modal).modal('hide');
}
function abrirModal(modal){
	$(modal).modal('show');
}
function showModal(modal){
	$("#"+modal).modal('show');
}

function adicionaMensagem(mensagem){
	new Notificacao('Aten&ccedil;&atilde;o!', mensagem).sucesso();
}
function adicionaErro(erro){
	new Notificacao('Aten&ccedil;&atilde;o!', erro.responseJSON).erro();
}

function ativarCarregando(idLoading){
	jQuery('#' + idLoading).showLoading();
}
function desativarCarregando(idLoading){
	jQuery('#' + idLoading).hideLoading();
}
function loading(idLoading){
	jQuery(idLoading).showLoading();
}
function fecharLoading(idLoading){
	jQuery(idLoading).hideLoading();
}

function renderDateMoment(data, type, row){
	if(data == null)
		return '';

	var m = moment(data.substring(0,19));
	var retorno = m.format('DD/MM/YYYY');

	if(retorno == 'Invalid date')
		return data;
	else
		return retorno;
}

function renderTipoDisponibilidade(tipo){
	try{
		switch (tipo) {
			case 'AULA': return 'Aula';
			case 'ALUGUEL': return 'Aluguel';
			case 'MENSALISTA': return 'Mensalista';
		}
		return '';
	}catch(err){
		console.error('function renderTipoDisponibilidade(tipo)', tipo, err);
		return '';
	}
}


function renderDateTimeMoment(data, type, row){
	if(data == null)
		return '';

	var dataPadronizada = data.replace('T', ' ').substring(0, 19);
	var dateFormat = new Date(dataPadronizada);
	var m = moment(dateFormat);
	var retorno = m.format('DD/MM/YYYY HH:mm');

	if(retorno == 'Invalid date')
		return data;
	else
		return retorno
}

function renderTimeMoment(data, type, row){
	if(data == null)
		return '';

	var retorno = moment(data, ["h:mm A"]).format('HH:mm');

	if(retorno == 'Invalid date')
		return data;
	else
		return retorno;
}

function openJanela(url,nomeJanela) {
	window.open(url,nomeJanela,'width=710,height=400,menubar,scrollbars,resizable');
}


function decodeEntities(s){
    var str, temp= document.createElement('p');
    temp.innerHTML= s;
    str= temp.textContent || temp.innerText;
    temp=null;
    return str;
}

function removerElementoForm(campo){
	var elemento = $("input[name*='." + campo +"'], textarea[name*='." + campo +"'], select[name*='." + campo +"'], input[name='" + campo +"'], textarea[name='" + campo +"'], select[name='" + campo +"'], checkbox[id='" + campo +"'], img[id='" + campo +"']").parents('div.control-group');
	if(elemento){
		elemento.remove();
	}
}

function renderDia(dia){
	try{
		switch (dia) {
			case 'SEGUNDA': return 'Segunda';
			case 'TERCA': return 'Terça';
			case 'QUARTA': return 'Quarta';
			case 'QUINTA': return 'Quinta';
			case 'SEXTA': return 'Sexta';
			case 'SABADO': return 'Sábado';
			case 'DOMINGO': return 'Domingo';
		}
		return '';
	}catch(err){
		console.error('function renderDia(dia)', dia, err);
		return '';
	}
}

function iniciarFuncoesDeCep(){
	function limpa_formulário_cep() {
            $(".rua").val("");
            $(".bairro").val("");
            $(".cidade").val("");
            $(".uf, .estado").val("");
        }

	 $(".cep").blur(function() {
        //Nova variável "cep" somente com dígitos.
        var cep = $(this).val().replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                $(".rua").val("...");
                $(".bairro").val("...");
                $(".cidade").val("...");
                $(".uf").val("...");

                //Consulta o webservice viacep.com.br/
                $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                    if (!("erro" in dados)) {
                        //Atualiza os campos com os valores da consulta.
                        $(".rua").val(dados.logradouro);
                        $(".bairro").val(dados.bairro);
                        $(".cidade").val(dados.localidade);
						$(".uf").val(dados.uf);


                    } //end if.
                    else {
                        //CEP pesquisado não foi encontrado.
                        limpa_formulário_cep();
                        console.log("CEP não encontrado.");
                    }

                });
            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                console.log("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    });
}
