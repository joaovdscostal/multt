/*

Ajax.js - a nds library

Usos dessa classe

Para ajax simples

	new Ajax(urlPadrao+'url')
	.sucesso(function(data){
		//chama a função de ajax e recebe o retorno no objeto data
	});

Para passar parametros para o ajax utilize a função parametros

	new Ajax(urlPadrao+'url').parametros('?q=google').sucesso(function(data){  codigo aqui   });
	new Ajax(urlPadrao+'url').parametros({ q: 'google' }).sucesso(function(data){  codigo aqui   });
	new Ajax(urlPadrao+'url').parametros($('form').serialize()).sucesso(function(data){  codigo aqui   });
	new Ajax(urlPadrao+'url').parametros(new FormData()).sucesso(function(data){  codigo aqui   });   // veja mais sobre formdata aqui https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects


Funcoes de erro e sempre

	new Ajax(urlPadrao+'url')
	.erro(function(data){
		// sobrescrever comportamento de erro
	})
	.sempre(function(data){
		// vai ser executado SEMPRE que o request acaba
	})
	.sucesso(function(data){
		// chama a função de ajax e recebe o retorno no objeto data
	});



Pode ser passado um objeto de jquery ou um id de objeto para fazer loading automaticamente
	* não para o loading se sobrescrever a função sempre

	new Ajax(url, $('#objeto')) ou new Ajax(url, 'objeto').sucesso(function(data){  codigo aqui   });


Pode utilizar alguns métodos para alterar o verbo http
	new Ajax(url).post().sucesso(function(data){  codigo aqui   });
	new Ajax(url).get().sucesso(function(data){  codigo aqui   });
	new Ajax(url).method('put').sucesso(function(data){  codigo aqui   });


Pode utilizar alguns métodos para alterar o datatype
	new Ajax(url).json().sucesso(function(data){  codigo aqui   });
	new Ajax(url).xml().sucesso(function(data){  codigo aqui   });
	new Ajax(url).html().sucesso(function(data){  codigo aqui   });
	new Ajax(url).script().sucesso(function(data){  codigo aqui   });
	new Ajax(url).jsonp().sucesso(function(data){  codigo aqui   });
	new Ajax(url).text().sucesso(function(data){  codigo aqui   });
	new Ajax(url).dataType('json').sucesso(function(data){  codigo aqui   });

Para enviar arquivos utilize multipart
	* somente FormData aceita envio de arquivos

	new Ajax(url).multipart().sucesso(function(data){  codigo aqui   });

*/
function Ajax(url, loading) {

	var _url = url;
	var _data = {};
	var _method = 'get';
	var _dataType = undefined;

//	var _loading = _loader(loading);
	var _loading = undefined;

	var _process = true;
	var _beforeAjax = function(){};

	var _done = function(data, textStatus, jqXHR){};
	var _fail = function(jqXHR, textStatus, errorThrown) {
		swal.close();

		if (jqXHR.status == 404){
			new Notificacao('Atenção', 'Recurso não encontrado!').erro();
		} else if (jqXHR.status == 403){
			new Notificacao('Atenção', 'Sem permissão!').erro();
		} else if (jqXHR.status == 401){
			if($.cookie('user')){
				new Ajax(urlPadrao + "app/verificar/ajax")
				.parametros({
					identificador: $.cookie('user'),
					unidade: $.cookie('unit'),
					tipo: $.cookie('personType')
				})
				.sucesso(function(data) {
					new Notificacao('Atenção', 'Conseguimos efetuar o login. Por favor, tente a ação novamente!').alerta();
				});
			}else{
				new Notificacao('Atenção', 'Por favor, se logue para continuar!').erro();
				window.location = urlPadrao+'login';
			}
		} else {
			new Notificacao().ajax(jqXHR);
		}
	};
	var _always = function(dataOuJqXHR, textStatus, jqXHROuErrorThrown){
	};


	return {
		para : function(url){
			_url = url;
			return this;
		},
		get : function(){
			_method = 'get';
			return this;
		},
		post : function(){
			_method = 'post';
			return this;
		},
		metodo : function(method){
			_method = method;
			return this;
		},
		xml : function(){
			_dataType = 'xml';
			return this;
		},
		html : function(){
			_dataType = 'html';
			return this;
		},
		script : function(){
			_dataType = 'script';
			return this;
		},
		json : function(){
			_dataType = 'json';
			return this;
		},
		jsonp : function(){
			_dataType = 'jsonp';
			return this;
		},
		text : function(){
			_dataType = 'text';
			return this;
		},
		dataType : function(type){
			_dataType = type;
			return this;
		},
		parametros : function(params){
			_data = params;
			return this;
		},
		addParametro : function(params){
			$.extend(_data, params);
			return this;
		},
		executarAntes : function(func){
			_beforeAjax = func;
			return this;
		},
		multipart : function(){
			_process = false;
			return this;
		},
		loading : function(value){
			_loading = _loader(value);
			return this;
		},

		getAjax : function(func){
			return precriar();
		},
		erro : function(func){
			_fail = func;
			return this;
		},
		sempre : function(func){
			_always = func;
			return this;
		},
		sucesso : function(func){
			_done = func;
			return criar();
		}
	}

	function _loader(value){
		if(value == undefined)
			return undefined;

		if(value instanceof jQuery)
			return value;
		else
			return $('#'+value);
	}

	function criar() {
		if(_loading != undefined) _loading.showLoading();

		_beforeAjax();

		var settings = {
			"url": _url,
			"data": _data,
			"method": _method,
		};

		if(_dataType!=undefined)
			settings.dataType = _dataType;

		if(_process==false){
			settings.processData = false;
			settings.contentType = false;
			settings.mimeTypes="multipart/form-data; charset=UTF-8";
		}

//		console.log('load', _loading);

		return $.ajax(settings)
		.done(_done)
		.fail(_fail)
		.always(function(dataOuJqXHR, textStatus, jqXHROuErrorThrown){
			if(_loading != undefined) {
				_loading.hideLoading();
			}
			_always();
		});
	}
}