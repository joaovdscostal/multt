/*

Modal.js - a nds library

Criador de modais


Para criar um modal simples

	new Modal()
	.comTitulo('titulo do modal')
	.comConteudo('conteudo do modal')
	.comRodape('rodapé do modal')
	.mostrar();

Funções de alteração de tamanho

	new Modal().pequeno().mostrar();
	new Modal().medio().mostrar();

Para mostrar e ocultar o modal
	var modal = new Modal().mostrar();
	modal.ocultar();

Para pesquisar algum elemento dentro do modal
	modal.find('.jquerySelector');

Para retornar o modal-dialog
	modal.getDialog();

Para alternar loading
	modal.loading();
	modal.loaded();

Evento de mostrar o modal
	modal.executarAoMostrar(function(){
		// será executado quando o modal ser mostrado
	});



Modais autonomos (Utilizando ajax)

Modais que fazem a requisição do ajax para carregar um conteúdo QUE TENHA UMA TAG FORM,
processa este conteúdo e sobrescreve o comportamento de enviar do mesmo para ser realizado
pelo modal.

Deve existir uma estrutura parecida com

 <form action="url" method="get">
 	...

 	<input type="submit" value="Enviar" />
 </form


Exemplo Simples

Para modal completo
	new Modal()
	.ajax(new Ajax(urlPadrao+'url'))
	.ajaxSubmit(function(){
		// será executado depois que a função submit do form for processada
	})
	.comTitulo('Abrir modal')
	.mostrar();

			1. o exemplo acima faz um ajax e com o retorno cria um modal,
			movendo o botão de submit para o rodapé do modal.
			2. quando o usuário clicar neste botão ou enviar o formulário
			o proprio modal faz um request para a action (e method)
			do formulário. quando este request se completar ele chama
			metodo que você passou em ajaxSubmit (se vc passou) e fecha
			o modal, ou mostra uma mensagem de erro


Para modal que não envia o formulário sozinho
	new Modal()
	.ajax(new Ajax(urlPadrao+'url'), function(){
		// será executado quando clicar no submit ou enviar o formulário
	})
	.comTitulo('Abrir modal')
	.mostrar();

			1. o exemplo acima faz um ajax e com o retorno cria um modal,
			movendo o botão de submit para o rodapé do modal.
			2. quando o usuário clicar neste ou enviar o formulário
			será chamada a função que foi passada junto com o ajax


Para uso de botões personalizados
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao+'url'), function(){})
	.comTitulo('Abrir modal');
	modal.executarAoMostrar(function(){
		// deverá ser criada toda logica aqui!!
	});
	modal.mostrar();



*/
function Modal(t_id, t_classe) {
	var _id = t_id + '_' + Date.now();//.getTime();
	var _classe = t_classe;
	var _titulo;
	var _conteudo;
	var _rodape;

	var _modal = '';

	var _datatable;

	var _aofechar;
	var _aocriar;
	var _aomostrar;

	var _ajax;
	var _ajaxFormOnSubmit;
	var _ajaxFormOnSubmit2;
	var _ajaxFormOnErro;
	var _ajaxTagShow = 'form.validateForm';
	var _ajaxAutoFillFooter = true;

	var _size = 'modal-lg';

	var executarFuncoesDinamicas = true;

	return {
		pequeno : function(){
			_size = 'modal-sm';
			return this;
		},
		medio : function(){
			_size = 'modal-md';
			return this;
		},
		extragrande : function(){
			_size = 'modal-xl';
			return this;
		},
		semTitulo : function() {
			_titulo = null;
			return this;
		},
		comTitulo : function(titulo){
			_titulo = titulo;
			return this;
		},
		comConteudo : function(conteudo) {
			_conteudo = conteudo;
			return this;
		},
		semRodape : function() {
			_rodape = null;
			return this;
		},
		comRodape : function(rodape) {
			_rodape = rodape;
			return this;
		},
		mostrar : function() {
			mostrar();
			return this;
		},
		criar : function() {
			criar();
			return this;
		},
		ocultar : function() {
			ocultar();
			return this;
		},
		remover : function() {
			remover();
		},
		find : function(selector) {
			return $('#'+_id).find(selector);
		},
		getModal : function(selector) {
			return $('#'+_id);
		},
		getId : function() {
			return _id;
		},
		getDialog : function() {
			return $('#'+_id).find('.modal-dialog');
		},
		getDatatable : function() {
			return _datatable;
		},
		setDatatable : function(datatable) {
			_datatable = datatable;
			return this;
		},
		loading : function(){
//			$('#'+_id).showLoading();
			return this;
		},
		loaded : function(){
//			$('#'+_id).hideLoading();
			return this;
		},
		executarAoFechar : function(funcao){
			_aofechar = funcao;
			return this;
		},
		executarAoCriar : function(funcao){
			_aocriar = funcao;
			return this;
		},
		executarAoMostrar : function(funcao){
			_aomostrar = funcao;

			return this;
		},
		naoExecutarFuncoesDinamicaAoMostrar(){
			executarFuncoesDinamicas = false;
			return this;
		},
		ajax: function(funcao, onSubmit){
			_ajax = funcao;
			_ajaxFormOnSubmit = onSubmit;
			return this;
		},
		ajaxTagShow: function(ajaxTagShow){
			_ajaxTagShow = ajaxTagShow;
			return this;
		},
		ajaxDoNotFillFooter: function(){
			_ajaxAutoFillFooter=false;
			return this;
		},
		ajaxSubmit: function(onSubmit){
			_ajaxFormOnSubmit2 = onSubmit;
			return this;
		},
		ajaxErro: function(onErro){
			_ajaxFormOnErro = onErro;
			return this;
		}

	}

	function criar() {
		var zIndex = 10200 + (200 * ($('.modal').length + 1));

		var modal_header = $('<div />').addClass('modal-header')
			.append($('<h4 />').addClass('modal-title').html(_titulo))
			.append('<button type="button" class="close btn btn-round" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>');


		var modal_footer = $('<div />').addClass('modal-footer')
			.append('<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>', _rodape);


		 _modal = $('<div />').attr('id', _id).addClass('modal fade modaljs '+((_classe)?_classe:'')).attr('role', 'dialog').css('cssText', 'z-index: '+zIndex+' !important;')
         .append($('<div />').addClass('modal-dialog ' + _size).attr('role', 'document')
             .append($('<div />').addClass('modal-content')
                 .append((_titulo!=null)?modal_header:'')
                 .append($('<div />').addClass('modal-body').html(
                		 $('<div />').html(_conteudo)
                 	)
                 )
                 .append((_rodape!=null)?modal_footer:'')
             )
         );

		 $('body').append(_modal);

		 if(_aocriar!=null)
			 _aocriar();


		 _modal.on('hidden.bs.modal', function (e) {
			 if(_aofechar!=null)
				 _aofechar();

			 remover();

			 $('.modal').last().find('.close').focus();
		})

		_modal.on('shown.bs.modal', function () {
			if(executarFuncoesDinamicas){
				executarFunctionsParaDinamico();
				iniciarFuncoesForm();

				var first = _modal.find('.form-group:first-child input:not([type="hidden"]), .form-group:first-child .bootstrap-select .btn, .form-group:first-child textarea');

				if(first.length > 0){
					first.focus();
				}else{
					if(_modal.find('.btn-select').length > 0){
						_modal.find('.btn-select').first().focus();
					}else{
						$('#'+_id+' .close').focus();
					}
				}
			}
		})
	}

	function mostrar() {
		if(_modal == ''){
			if(_ajax!=null){
				_ajax.addParametro({ajax:true})
				.sucesso(function (data){
					_conteudo = $(data);
					submit = _conteudo.find('[type="submit"]');
//					if(_ajaxAutoFillFooter){
//						_rodape = submit;
//					}

					_conteudo = (_conteudo.is(_ajaxTagShow))?_conteudo:_conteudo.find(_ajaxTagShow);

					var form = _conteudo.is('form')?_conteudo:_conteudo.find('form');

					if(_ajaxFormOnSubmit != null){
						form.on('submit', _ajaxFormOnSubmit);
						submit.on('click', _ajaxFormOnSubmit);
					}else{
						var originalClick = function(e){
							e.preventDefault();

							if (form.valid()){

									var requestAjax = new Ajax(form.attr('action'), _modal.find('.modal-dialog')).metodo(form.attr('method'));

									if(form.attr('data-campo-arquivo')){
										var formData = new FormData();

										 form.serializeArray().forEach(function(objeto){
										    formData.append(objeto.name, objeto.value);
										  });

										formData.append($("#"+form.attr('data-campo-arquivo')).attr('name'), $("#"+form.attr('data-campo-arquivo')).prop('files')[0]);
										requestAjax.multipart().parametros(formData);
									}else if(form.attr('data-campo-arquivos')){
										var formData = new FormData();

										 form.serializeArray().forEach(function(objeto){
										    formData.append(objeto.name, objeto.value);
										  });

										  var elementos = $("[name='"+form.attr('data-campo-arquivos')+"']");

										  if(elementos){
											  var arquivos = elementos.prop('files');

											  for (var i = 0; i < arquivos.length ; i++){
												  var item = arquivos[i];
												  formData.append(elementos.attr('name'), item);
											  }
										  }


										requestAjax.multipart().parametros(formData);
									}else{
										requestAjax.parametros(form.serialize());
									}

						    		requestAjax.erro(function(data){
										if(_ajaxFormOnErro != null){
											_ajaxFormOnErro(data);
										}
									}).sucesso(function(data) {
										_modal.modal('hide');

										if(_ajaxFormOnSubmit2 != null){
											_ajaxFormOnSubmit2(data);
										}
						    		});
						    	}
						}
						form.off('submit').on('submit', originalClick);
						submit.off('click').on('click', originalClick);
					}


					criar();


					_modal.modal({
						backdrop: 'static', // prevent to click outside close modal
						//keyboard: false // prevent to esc close modal
					});

					if(_aomostrar!=null)
						_aomostrar();

					var zIndex = _modal.css('z-index') - 100;

					_modal.next('.modal-backdrop').css('cssText', 'z-index: '+zIndex+' !important;');
				});
			}else{

				criar();

				if(_aomostrar!=null)
					_aomostrar();

				_modal.modal({
					backdrop: 'static', // prevent to click outside close modal
					//keyboard: false // prevent to esc close modal
				});

				var zIndex = _modal.css('z-index') - 100;

				_modal.next('.modal-backdrop').css('cssText', 'z-index: '+zIndex+' !important;');
			}
		}

	}

	function ocultar(){
		_modal.modal('hide');
	}

	function remover(){
		_modal.next('.modal-backdrop').remove();
		_modal.remove();
	}
}


