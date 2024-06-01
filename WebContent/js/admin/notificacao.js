/*

Usos dessa classe

Para ajax
	new Notificacao().ajax(data);

Para ajax
	new Notificacao().ajax(data, function(){
		funcao par aexecutar quando fexar a notficacao

	});

Para alertas e erros ajax
	new Notificacao().ajaxAlert(data);


Para toastr [https://github.com/CodeSeven/toastr]
	new Notificacao('Sucesso!', 'mensagem').sucesso();
	new Notificacao('Ops!', 'mensagem').alerta();
	new Notificacao('Ops!', 'mensagem').erro();


Para swal - basta adicionar .dialogo()
	new Notificacao('Sucesso!', 'mensagem').dialogo().sucesso();
	new Notificacao('Ops!', 'mensagem').dialogo().alerta();
	new Notificacao('Ops!', 'mensagem').dialogo().erro();

Para confirm (somente swal)
	new Notificacao('Atenção!', 'mensagem').dialogo().confirm(function(){
		//funcao de sim
	});
	new Notificacao('Atenção!', 'mensagem').dialogo().confirm(function(){
		//função de sim
	}, function(){
		//funcao de não
	});
 */
//Para swal com input (somente swal)
	/*new Notificacao('titulo', 'mensagem').dialogo().input(function(inputValue){
		//funcao de retorno
	});*/


function Notificacao(_titulo, _mensagem) {

	if(!_titulo) {_titulo="";}
	if(!_mensagem) {_mensagem="";}

	var _dialogo = false;

	var _confirm = function(){};
	var _negate = function(){};
	var _onShow = function(){};


	var _optionsToastr = {
		"closeButton": false,
		"debug": false,
		"newestOnTop": false,
		"progressBar": false,
		"positionClass": "toast-top-right",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "2000",
		"timeOut": "5000",
		"extendedTimeOut": "2000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	};

	var _optionsSwal = {
		"title": "",
		"text": "",
		"type": null,
		"allowOutsideClick": false,
		"showConfirmButton": true,
		"showCancelButton": false,
		"closeOnConfirm": true,
		"closeOnCancel": true,
		"confirmButtonText": "OK",
		"confirmButtonColor": "",
		"cancelButtonText": "Cancelar",
		"imageUrl": null,
		"imageSize": null,
		"timer": null,
		"customClass": "",
		"html": true,
		"animation": true,
		"allowEscapeKey": true,
		"inputType": 'text',
		"inputPlaceholder": "",
		"inputValue": "",
		"showLoaderOnConfirm": false
	};

	return {
		dialogo : function() {
			_dialogo = true;
			return this;
		},


		sucesso: function(options){
			_collectData(options);

			_success();
		},
		alerta: function(options){
			_collectData(options);

			if(!_titulo)
				_titulo = "Ops!";

			_warning();
		},
		erro: function(options){
			_collectData(options);

			if(!_titulo)
				_titulo = "Ops!";

			_error();
		},

		ajax: function(data, depois){
			if(depois){
				_confirm = depois;
			}

			_ajax(data, true);
		},
		ajaxAlert: function(data, depois){

			console.log(data);

			if(depois){
				console.log(depois);
				_confirm = depois;
			}

			_ajax(data, false);
		},

		//swal
		input: function(ok, options, onShow){
			if(!_dialogo) throw "use somente para swal, ex: new Notificacao().dialogo()";

			if(onShow){_onShow = onShow;}

			_collectData(options);
			_input(ok);
		},
		onShow: function(onShow){
			if(onShow){_onShow = onShow;}
			return this;
		},
		prompt: function(options){
			if(!_dialogo) throw "use somente para swal, ex: new Notificacao().dialogo()";

			_collectData(options);
			_prompt();
		},
		confirm: function(confirm, negate,  options){
			if(!_dialogo) throw "use somente para swal, ex: new Notificacao().dialogo()";

			$.extend(_optionsSwal, {
				"showLoaderOnConfirm": true,
				"showCancelButton": true,
				"confirmButtonText": 'Sim',
				"cancelButtonText": 'Não',
				"closeOnConfirm": false,
				"closeOnCancel": true
			});

			_collectData(options);
			_warning();

			if(confirm)
				_confirm = confirm;

			if(negate)
				_negate = negate;
		},
		confirmSingle: function(returnFunction,  options){
			if(!_dialogo) throw "use somente para swal, ex: new Notificacao().dialogo()";

			$.extend(_optionsSwal, {
				"showLoaderOnConfirm": true,
				"showCancelButton": true,
				"confirmButtonText": 'Sim',
				"cancelButtonText": 'Não',
				"closeOnConfirm": false,
				"closeOnCancel": true
			});

			_collectData(options);
			_warning();

			if(returnFunction){
				_confirm = returnFunction;
				_negate = returnFunction;
			}
		},

	}

	function _collectData(options){
		_optionsSwal.title = _titulo;
		_optionsSwal.text = _mensagem;

		if(options){
			if(_dialogo){
				$.extend(_optionsSwal, options);
			}else{
				$.extend(_optionsToastr, options);
			}
		}
	}

	function _swal(always){
		swal(_optionsSwal, function(ok){
			if (ok) {
				_confirm(ok);
			}else{
				_negate(ok);
			}
		});

		if(always){
			setTimeout(function(){_confirm();}, 300);
		}
	}

	function _success(){
		if(_dialogo){
			_optionsSwal.type = "success";
			_swal();
		}else{
			toastr.success(_mensagem, _titulo, _optionsToastr);
		}
	}
	function _error(){
		if(_dialogo){
			_optionsSwal.type = "error";
			_swal();
		}else{
			toastr.error(_mensagem, _titulo, _optionsToastr);
		}
	}
	function _warning(){
		if(_dialogo){
			_optionsSwal.type = "warning";
			_swal();
		}else{
			toastr.warning(_mensagem, _titulo, _optionsToastr);
		}
	}
	function _ajax(data, mostrarSucesso){
		swal.close();

		var statusNew = data.status;

		if(data.responseJSON){
			data = data.responseJSON;
		}

		if(data.responseText){
			data = data.responseText.startsWith('<!doctype html>')?{
																		mensagens:[{
																			category: 'error',
																			message: 'Algo deu errado, tente novamente!'
																		}]
																	}:data.responseText;
		}

		if(data.stacktrace){
			console.error(data.stacktrace);
		}

		if(data.mensagens==undefined){
			if(data && statusNew == 400){
				data = {
																			mensagens:[{
																				category: 'error',
																				message: data
																			}]
																		}
			}else{
				console.error('Sem mensagem para exibir!', data);
				return false;
			}
		}




		if(_dialogo){
			var mensagem = '';
			var categoria = data.mensagens[0].category;

			if(data.mensagens.length == 1){
				mensagem = data.mensagens[0].message;
			}

			if(data.mensagens.length > 1){
				mensagem = "<ul>"+$.map(data.mensagens, function(item){
					return '<li>'+item.message+'</li>';
				}).join('')+'</ul>';
			}

			if(mensagem != ''){
				if(categoria == 'message' && mostrarSucesso){
					_optionsSwal.title = "Sucesso!";
					_optionsSwal.text = mensagem;
					_optionsSwal.type = "success";
					_swal(true);
				}
				if(categoria == 'error'){
					_optionsSwal.title = "Ops!";
					_optionsSwal.text = mensagem;
					_optionsSwal.type = "error";
					_swal(true);
				}
				if(categoria == 'warning'){
					_optionsSwal.title = "Ops!";
					_optionsSwal.text = mensagem;
					_optionsSwal.type = "warning";
					_swal(true);
				}
			}
		}else{
			$.each(data.mensagens, function(i, item){
				if(item.category == 'message' && mostrarSucesso){
					toastr.success(item.message, "Sucesso!", _optionsToastr);
				}
				if(item.category == 'error'){
					toastr.error(item.message, "Ops!", _optionsToastr);
				}
				if(item.category == 'warning'){
					toastr.warning(item.message, "Ops!", _optionsToastr);
				}
			});

			_confirm();
		}
	}
	function _errorAjax(data){
//		if(_dialogo){
//			_optionsSwal.type = "error";
//			_swal();
//		}else{
//			toastr.error(_mensagem, _titulo, _optionsToastr);
//		}
	}
	function _warningAjax(data){
//		if(_dialogo){
//			_optionsSwal.type = "warning";
//			_swal();
//		}else{
//			toastr.warning(_mensagem, _titulo, _optionsToastr);
//		}
	}


	function _input(ok){
		_optionsSwal.type = "input";

		swal(_optionsSwal, ok);

		if(_onShow){
			_onShow($('.sweet-alert'));
		}
	}
	function _prompt(){
		_optionsSwal.type = "prompt";
		_swal();
	}
}