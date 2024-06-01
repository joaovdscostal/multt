/*

Exemplo pesquisar dinamico:

<div class="form-group col-4 pl-0 ">
	        <label for="aluguelQuadraUsuario" class="control-label">Adicionar membro 1</label>
	         <div class="input-group">

			<input type="hidden" name="membros[0]" id="contratoAulaMembro1" class="form-control" readonly="readonly">
			<input type="text" name="contratoAulaNomeMembro1" data-campo-id="contratoAulaMembro1" id="contratoAulaNomeMembro1" class="form-control " readonly="readonly">
	        	<div class="input-group-append">
	        		<button class="btn btn-outline-secondary pesquisaDinamico" type="button"
	        		data-generate='{"gerador":{"objeto":"Membro", "filtrarAtivo":true, "metodo":"text","nome":"Membro",
	        		"appends":[{"id":"contratoAulaNomeMembro1","campo":"usuario.nome"},{"id":"contratoAulaMembro1","campo":"id"}],
	        		"pesquisa":["usuario.nome","usuario.login"],"exibicao":["getNome"]}}'

	        		><i class="fas fa-search" ></i></button>
	        	</div>
			</div>
		</div>


Exemplo select:

catalogoAtivoParaSite
 <div class="form-group  col-md-6 ">
			<label for="aulaAvulsaTipoAluguel" class="control-label">Tipo de aula</label>
			<select name="aulaAvulsa.tipo.id" class="form-control selectDinamico required" data-toggle="select"
					data-objeto="TipoGenerico" data-campos-exibicao="getNomeExibicao" data-filtro-ativo="true"
					data-campos-pesquisa="nome" data-selected="${aulaAvulsa.tipo.id}"
				    <c:if test="${sessao.isProfessor() == true}">
						data-parametros-fixos="tipo,AULA_AVULSA|funcionarioComissionado,${funcionario.id}"
				    </c:if>
				   	<c:if test="${sessao.isProfessor() == false}">
						data-parametros-fixos="tipo,AULA_AVULSA"
				    </c:if>
					id="aulaAvulsaTipoAluguel" required="required">
			</select>
		</div>


Exemplo selecao multipla

	<div class="selecao-multipla-dinamica"
			data-generate='{"gerador":{"objeto":"AtividadeEconomica", "filtrarAtivo":true, "ids":"${empresa.getIdsSecundarios(empresa.atividadesSecundarias)}",
			 "metodo":"text", "objetoInput" : "empresa.atividadesSecundarias[].id", "nome":"Atividades secundarias",
			 "pesquisa":["nome"],"exibicao":["getNome", "getCnae"]}}'
			></div>


*/

$(document).on('click', '.button-add-dinamico', function(event){
	var url = $(this).attr('data-url');
	var id = $(this).attr('data-append-id');
	var nome = $(this).attr('data-append-nome');
	var texto = $(this).attr('data-texto');

	var modal = new Modal('idDinamico')
			.ajax(new Ajax(urlPadrao+'adm/' + url +'/novo'))
			.ajaxSubmit(function(data){

				if ($("#" + id).is("select")) {
					$('#' + id).append('<option value="'+data.retorno.id+'" selected="selected">'+data.retorno.nome+'</option>');

					if($('#' + id).hasClass('selectDinamico'))
						$('#' + id).selectpicker('refresh');
				} else {
				  	$("#" + id).val(data.retorno.id);
					$("#" + nome).val(data.retorno.nome);
				}



				new Notificacao('Sucesso', texto + ' adicionada(o)').sucesso();
			})
			.ajaxErro(function(e){
				new Notificacao('Erro', e.responseText).erro();
			})
			.comTitulo('Nova(o) ' + texto)
			.mostrar();

			modal.executarAoMostrar(function(){
				$("#" + modal.getId()).find('form').attr('action', $("#" + modal.getId()).find('form').attr('action') + "/ajax");
				$("#" + modal.getId()).find('div.form-group').removeClass('col-md-*').addClass('col-md-12');
				$("#" + modal.getId()).find(".cadastrar-novo").remove();
				});
});

$('.selecao-multipla-dinamica').each(function(){
	var id = $(this).attr('id');
	selecaoMultiplaDinamica($("#"+id));
	$("#"+id).removeClass('selecao-multipla-dinamica');
});


function selecaoMultiplaDinamica(object) {

		var ids = [];

		var gerador = $(object).attr("data-generate");

		var geradorObj = JSON.parse(gerador);
		var objeto = geradorObj["gerador"];

		var objetoAnalizado = objeto["objeto"];
		var nome = objeto["nome"];

		var parametrosFixos = objeto["parametrosFixos"];
		var pesquisa = objeto["pesquisa"];
		var exibicao = objeto["exibicao"];
		var objetoInput = objeto["objetoInput"];
		var classeFuncaoExecutar = "selecionarDadoParaSelecao";

		if(objeto["ids"]){
			var arraysDeIds = JSON.parse(objeto["ids"]);

			if(Array.isArray(arraysDeIds)){
				ids = arraysDeIds;
			}
		}

		var filtrarAtivo = false;

		if(objeto["filtrarAtivo"]){
			filtrarAtivo = objeto["filtrarAtivo"];
		}

		var url = '';

		for(var i = 0; i < pesquisa.length; i++ ){
			if(i > 0)
				url += '&';
			url += 'pesquisa[' + i + ']=' +  pesquisa[i];
		}


		for(var i = 0; i < exibicao.length; i++ ){
			url += '&exibicao[' + i + ']=' +  exibicao[i];
		}

		var urlParaChamar = url;
		if($("#formPesquisaDinamicoExtras").serialize()){
			urlParaChamar = urlParaChamar + "&" + $("#formPesquisaDinamicoExtras").serialize();
		}

		carregarDadosDoObjeto(objetoAnalizado, filtrarAtivo, nome, ids, objetoInput);

		function carregarDadosDoObjeto(objetoAnalizado, filtrarAtivo, nome, ids, objetoInput){
			new Ajax(urlPadrao+'dinamico/selecao?'+ urlParaChamar).parametros({
					objeto : objetoAnalizado,
					filtrarAtivo : filtrarAtivo,
					nome: nome,
					ids: ids,
					objetoInput: objetoInput,
					parametrosFixos : parametrosFixos
				}).sucesso(function(data){
					$(object).html(data.retorno);


					$("#buttonPesquisaDinamico").unbind('click');
					$("#buttonPesquisaDinamico").click(function(){
						pesquisarDinamicamente();
					});

					$("#formPesquisaDinamico input[type=text]").unbind('click');
					$("#formPesquisaDinamico input[type=text]").unbind('keypress');
					$("#formPesquisaDinamico input[type=text]").keypress(function(event){
						if (event.keyCode == 13){
							pesquisarDinamicamente();
						}
					});


					$(object).find(".addDinamico").click(function(){
						$("#tituloModalPesquisar").html('Pesquisar ' + nome);
						showModal('modalPesquisaDinamico');
						pesquisarDinamicamente($(this));
					});


					$(".remover-item").click(function(){
						var id = $(this).attr('data-id');
						ids.pop(id);
						$(".item-dinamico-" + id).remove();

						if($(".list-group-item").toArray().length == 0){
							$(".list-group").html('N&atilde;o existem itens adicionados');
						}
					});

			});
		}

		function containsObject(obj, list) {
		    var i;
		    for (i = 0; i < list.length; i++) {
		        if (list[i] === obj) {
		            return true;
		        }
		    }

		    return false;
		}

		function pesquisarDinamicamente(objetoAddDinamico){

			var urlParaChamar = url;
			if($("#formPesquisaDinamicoExtras").serialize()){
				urlParaChamar = urlParaChamar + "&" + $("#formPesquisaDinamicoExtras").serialize();
			}

			loading('#modalPesquisaDinamico');
			$.ajax({
				url: urlPadrao + "dinamico/pesquisar?" + urlParaChamar,
				dataType: "json",
				data: {
					valor : $("#pesquisa").val(),
					objeto : objetoAnalizado,
					filtrarAtivo : filtrarAtivo,
					classeFuncaoExecutar : classeFuncaoExecutar,
					parametrosFixosStr: parametrosFixos
				},
				success: function(json){

					if(typeof json == 'string'){
						json = JSON.parse(json);
					}

					$("#conteudoDinamico").html(json.retorno);
					fecharLoading('#modalPesquisaDinamico');

					$("." + classeFuncaoExecutar).click(function(){
						var id = $(this).attr('data-id');
						id= parseInt(id);

						if(containsObject(id, ids)){
							new Notificacao('Erro', nome + ' ja estão adicionadas(os)').erro();
						}else{
							ids.push(id);
							closeModal('modalPesquisaDinamico');
							carregarDadosDoObjeto(objetoAnalizado, filtrarAtivo, nome, ids, objetoInput);
						}
					});


					urlParaChamar = url;


				},error: function(json){
					urlParaChamar = url;
					adicionaErro(json);
					fecharLoading('#modalPesquisaDinamico');
					$("#nomeProfessorPesquisa").focus();
				}
			});

		}

	}


function atualizarDinamicoParaSelect(objetoDeSelect){
		$(objetoDeSelect).selectpicker('destroy');
		$(objetoDeSelect).find('option[value][value!=""]').remove();

		var objeto = $(objetoDeSelect).attr('data-objeto');

		if(!objeto){
			return;
		}

		var campoExibicao = $(objetoDeSelect).attr('data-campos-exibicao');
		var filtroAtivo = $(objetoDeSelect).attr('data-filtro-ativo');
		var camposPesquisa = $(objetoDeSelect).attr('data-campos-pesquisa');
		var campoSelecionado = $(objetoDeSelect).attr('data-selected');
		var parametrosfixos = $(objetoDeSelect).attr('data-parametros-fixos');
		var order = $(objetoDeSelect).attr('data-order');
		var selectSimples = $(objetoDeSelect).attr('data-select-simples');
		var placeholder = $(objetoDeSelect).attr('data-placeholder');
		var atributoAppend = $(objetoDeSelect).attr('data-atributo-append');

		if(placeholder){}else{
			placeholder = "Nada selecionado";
		}

		var idElemento = $(objetoDeSelect).attr('id');

		$.ajax({
				url: urlPadrao + "dinamico/select-dinamico" ,
				dataType: "json",
				data: {
					objeto : objeto,
					campoExibicao : campoExibicao,
					filtroAtivo : filtroAtivo,
					camposPesquisa : camposPesquisa,
					parametrosFixos : parametrosfixos,
					order : order,
					idElemento: idElemento,
					atributoAppend: atributoAppend
				},
				success: function(json){

					var listObjetos = json['retorno'][0];
					var selectDinamico = $("#" + json['retorno'][1]);


					for(var i = 0; i < listObjetos.length; i++ ){
						var option = new Option(listObjetos[i]['texto'], listObjetos[i]['codigo']);

						if(atributoAppend){
							option.setAttribute("data-" + atributoAppend, listObjetos[i]['campoAppend']);
						}


						selectDinamico.append(option);
					}

					if(campoSelecionado){
						var arrayDeObjetosSelecionados = JSON.parse(campoSelecionado);

						if(Array.isArray(arrayDeObjetosSelecionados)){
							for(var i = 0; i < arrayDeObjetosSelecionados.length; i++ ){
								selectDinamico.find('option[value="'+arrayDeObjetosSelecionados[i]+'"]').attr('selected','selected');
							}
						}else{
							selectDinamico.find('option[value="'+campoSelecionado+'"]').attr('selected','selected');
						}
					}

					if(selectSimples == undefined || selectSimples == 'undefined'){
						 $("#" + json['retorno'][1]).selectpicker({
					    	noneSelectedText : placeholder
					    });
					     $("#" + json['retorno'][1]).selectpicker('refresh');
					}
				},error: function(json){
					adicionaErro(json);
				}
			});

	}



function pesqDinam(object) {
		$(".filtrosDinamicos").remove();
		$("#conteudoDinamico").html('');
		$("#pesquisa").val('');

		var gerador = $(object).attr("data-generate");

		var geradorObj = JSON.parse(gerador);
		var objeto = geradorObj["gerador"];

		var objetoAnalizado = objeto["objeto"];
		var nome = objeto["nome"];

		$("#tituloPesquisarDinamico").html(nome);

		var appends = objeto["appends"];
		var autocomplete = objeto["autocomplete"];
		var parametrosFixos = objeto["parametrosFixos"];
		var pesquisa = objeto["pesquisa"];
		var exibicao = objeto["exibicao"];
		var recursivo = objeto["recursivo"];
		var placeHolder = objeto["placeHolder"];
		var metodo = objeto["metodo"];
		var metodoInvocarAoSelecionar = objeto["metodoInvocarAoSelecionar"];
		var classeFuncaoExecutar = "selecionarDadoParaPesquisar";



		$('#modalPesquisaDinamico').on('shown.bs.modal', function () {
			$("#pesquisa").val('');
			$("#conteudoDinamico").html('');
			$("#pesquisa").focus();
			$('.modal-backdrop').last().attr('style','z-index: 11000 !important;').addClass('modal-backdrop-custom');

			pesquisarDinamicamente(metodoInvocarAoSelecionar, recursivo);
		});

		$('#modalPesquisaDinamico').on('hidden.bs.modal', function () {
			$("#pesquisa").val('');
			$('#modalPesquisaDinamico').modal('dispose');
		});


		var filtros = objeto["filtros"];

		if(filtros){
			for(var i = 0; i < filtros.length; i++ ){
				var filtro = filtros[i];
				var nome = filtro["nome"];
				var tipo = filtro["tipo"];
				var label = filtro["label"];
				var classe = filtro["class"];
				var alt = filtro["alt"];
				var valorPadrao = filtro["default"];

				var valorPadraoStr = "";
				var altStr = "";

				if(alt){
					altStr = ' alt="'+alt+'" ';
				}
				if(valorPadrao){
					valorPadraoStr = ' value="'+valorPadrao+'" ';
				}

				var template = '<div class="form-group filtrosDinamicos col-md-12"> '+
							      '<label class="control-label" for="'+ nome + '">'+ label + '</label> '+
							        '<input type="hidden" name="campos['+i+'].nome" id="'+ nome + '-nome" value="'+ nome + '"> '+
							        '<input type="text" class="form-control ' + classe + '" '+ valorPadraoStr +' ' +altStr+  ' name="campos['+i+'].valor" id="'+ nome + '"> '+
							        '<input type="hidden" name="campos['+i+'].tipo" id="'+ nome + '-tipo" value="'+ tipo + '"> '+
							    '</div>';

				$("#formPesquisaDinamicoExtras").prepend(template);

			}

			iniciarFuncoesForm();
		}

		var filtrarAtivo = false;

		if(objeto["filtrarAtivo"]){
			filtrarAtivo = objeto["filtrarAtivo"];
		}

		var url = '';

		for(var i = 0; i < pesquisa.length; i++ ){
			if(i > 0)
				url += '&';
			url += 'pesquisa[' + i + ']=' +  pesquisa[i];
		}


		for(var i = 0; i < exibicao.length; i++ ){
			url += '&exibicao[' + i + ']=' +  exibicao[i];
		}

		if(parametrosFixos){
			for(var i = 0; i < parametrosFixos.length; i++ ){
				url += '&parametrosFixos[' + i + '].nome=' +  parametrosFixos[i].nome;
				url += '&parametrosFixos[' + i + '].valor=' +  parametrosFixos[i].valor;
			}
		}


		if(placeHolder){
			$("#pesquisa").attr('placeHolder', decodeEntities(placeHolder));
		}else{
			$("#pesquisa").removeAttr('placeHolder');
		}



		$("#buttonPesquisaDinamico").unbind('click');
		$("#buttonPesquisaDinamico").click(function(){
			pesquisarDinamicamente(metodoInvocarAoSelecionar, recursivo);
		});

		$("#formPesquisaDinamico input[type=text]").unbind('click');
		$("#formPesquisaDinamico input[type=text]").unbind('keypress');
		$("#formPesquisaDinamico input[type=text]").keypress(function(event){
			if (event.keyCode == 13){
				pesquisarDinamicamente(metodoInvocarAoSelecionar, recursivo);
			}
		});


		function pesquisarDinamicamente(metodoInvocarAoSelecionar, recursivo){
			var urlParaChamar = url;
			if($("#formPesquisaDinamicoExtras").serialize()){
				urlParaChamar = urlParaChamar + "&" + $("#formPesquisaDinamicoExtras").serialize();
			}

			ativarCarregando('modalPesquisaDinamico');
			$.ajax({
				url: urlPadrao + "dinamico/pesquisar?" + urlParaChamar,
				dataType: "json",
				data: {
					valor : $("#pesquisa").val(),
					objeto : objetoAnalizado,
					filtrarAtivo : filtrarAtivo,
					classeFuncaoExecutar: classeFuncaoExecutar
				},
				success: function(json){

					if(typeof json == 'string'){
						json = JSON.parse(json);
					}

					$("#conteudoDinamico").html(json.retorno);
					desativarCarregando('modalPesquisaDinamico');

					$("." + classeFuncaoExecutar).click(function(){
						var id = $(this).attr('data-id');
						selecionarDinamicamente(id, metodoInvocarAoSelecionar, recursivo, $(this));
					});


					$(object).unbind('click');
					$(object).bind("click", function() {
						 pesqDinam(object);
					});

					urlParaChamar = url;

				},error: function(json){
					urlParaChamar = url;
					adicionaErro(json);
					desativarCarregando('modalPesquisaDinamico');
					$("#nomeProfessorPesquisa").focus();
				}
			});
		}


		function selecionarDinamicamente(id, metodoInvocarAoSelecionar, recursivo, objeto){
			$("#conteudoDinamico").html('');
			ativarCarregando('modalPesquisaDinamico');
			$.ajax({
				url: urlPadrao + "dinamico/selecionar" ,
				dataType: "json",
				data: {
					id : id,
					objeto : objetoAnalizado,
					recursivo: recursivo
				},
				success: function(data){
					if(typeof data == 'string'){
						data = JSON.parse(data);
					}

					json = data.retorno;

					$("#conteudoDinamico").html('');
					desativarCarregando('modalPesquisaDinamico');

					if(metodo == "text"){
						for(var i = 0; i < appends.length; i++ ){
							var idCampo = appends[i]["id"];
							var campoAppend = appends[i]["campo"];

							var valueExtra = "";

							var extras = appends[i]["extra"];

							if(extras && objeto){
								for(var e = 0; e < extras.length; e++ ){
									var valorParaAdicionarNoExtra = objeto.attr('data-' + extras[e].toLowerCase());
									valueExtra += " - " + valorParaAdicionarNoExtra;
								}
							}


							if(campoAppend.indexOf(".") > -1){
								var splits = campoAppend.split(".");
								if(splits.length == 2){
									$("#" + idCampo).val(json[splits[0]][splits[1]] + valueExtra);
								}

								if(splits.length == 3){
									$("#" + idCampo).val(json[splits[0]][splits[1]][splits[2]] + valueExtra);
								}

							}else{
								$("#" + idCampo).val(json[campoAppend] + valueExtra);
							}
						}
					}else{
						var valorParaExibir = "";

						for(var i = 0; i < appends.length; i++ ){
							var idCampo = appends[i]["id"];
							var campoAppend = appends[i]["campo"];

							valorParaExibir += json[campoAppend] + " ";
						}


						var element = $('#' + metodo + ' :selected');
						var texto = element.val();


						if($('#' + metodo + ' option[value='+json.id+']').is(":selected") == false){
							$('#' + metodo).append('<option value="'+json.id+'" selected="selected">'+valorParaExibir+'</option>');
							$('.selectpicker').selectpicker('refresh');
						}


					}

					if(metodoInvocarAoSelecionar){
						window[metodoInvocarAoSelecionar](id);
					}

					closeModal('modalPesquisaDinamico');
					adicionaMensagem(nome + " selecionado com sucesso!");
				},error: function(json){
					adicionaErro(json);
					desativarCarregando('modalPesquisaDinamico');
					$("#nomeProfessorPesquisa").focus();
				}
			});
		}

		$("#tituloModalPesquisar").html('Pesquisar ' + nome);
		showModal('modalPesquisaDinamico');

		$(object).unbind('click');
		$(object).bind("click", function() {
			 pesqDinam(object);
		});
	}


function pesquisarDinamamicamentFunction(){
	$(document).on('click', '.pesquisaDinamico', function(event){
		var objeto = this;
		pesqDinam(objeto);
	});

	$('.pesquisaDinamico').each(function(){

		var object = this;
		var gerador = $(object).attr("data-generate");
		var geradorObj = JSON.parse(gerador);

		var objeto = geradorObj["gerador"];

		var objetoAnalizado = objeto["objeto"];
		var autocomplete = objeto["autocomplete"];

		if(autocomplete){

			var permiteNovosCadastros = false;

			if(autocomplete.permitirNovosCadastros && autocomplete.permitirNovosCadastros == true){
				permiteNovosCadastros = true;
			}

			$("#" + autocomplete.field).removeAttr('readonly');
			$("#" + autocomplete.field).attr('autocomplete', 'off');

			$("#" + autocomplete.field).autoComplete({
				noResultsText: 'Nenhum resultado encontrado',
				minLength: 2,
			    resolverSettings: {
			        url: urlPadrao+'dinamico/autocompletar?objeto=' + objetoAnalizado + '&campoExibicao=' + autocomplete.campoExibicao+ '&campoExibicaoFormatado=' + autocomplete.campoExibicaoFormatado + '&filtroAtivo=' + autocomplete.filtroAtivo +
				    '&camposPesquisa=' + autocomplete.camposPesquisa + '&parametrosFixos=' + autocomplete.parametrosfixos + '&order=' + autocomplete.order,
			    }
			});

			$( "#" + autocomplete.field).on( "autocomplete.select", function( event, item ) {
			  $("#" + autocomplete.field).val(item.text);
			  $("#" + autocomplete.fieldId).val(item.value);

			  	var metodoInvocarAoSelecionar = objeto["metodoInvocarAoSelecionar"];
				if(metodoInvocarAoSelecionar){
					window[metodoInvocarAoSelecionar](item.value);
				}

			});
			$( "#" + autocomplete.field).on( "autocomplete.dd.shown", function( event, item ) {
			  $("#" + autocomplete.fieldId).val('');
			});

			$(document).on( "blur", "#" + autocomplete.field, function( event, item ) {
				var condicao = !$("#" + autocomplete.fieldId).val() || $("#" + autocomplete.fieldId).val() === '';

				if(condicao && permiteNovosCadastros == false){
					$("#" + autocomplete.field).val('');
				}
			});

			/*$("#" + autocomplete.field).select2 ({
			    allowClear: true,
			    placeholder: "Selecionar...",
			    formatSearching: function () { return 'Pesquisando, aguarde...'; },
			    ajax: {
				    url: urlPadrao+'dinamico/autocompletar?objeto=' + objetoAnalizado + '&campoExibicao=' + autocomplete.campoExibicao + '&filtroAtivo=' + autocomplete.filtroAtivo +
				    '&camposPesquisa=' + autocomplete.camposPesquisa + '&parametrosFixos=' + autocomplete.parametrosfixos + '&order=' + autocomplete.order,
				    dataType: 'json',
				    quietMillis: 250,
				    data: function (term, page) {
	            		return {
	                		q: term, // search term
	            		};
	        		},
				   	processResults: function (data) {
				      return {
				        results: data
				      };
				    },
				    cache: true,
				  }
			  });*/



		}



	});

}

function adicionarDinamamicamentFunction(){
$(document).on('click', '.adicionarDinamico', function(event){
		$(".elements").remove();

		var gerador = $(this).attr("data-generate");
		var geradorObj = JSON.parse(gerador);
		var objeto = geradorObj["gerador"];
		var objetoAnalizado = objeto["objeto"];
		var nomeObjeto = objeto["nome"];
		var append = objeto["append"];
		var appendIdentificador = objeto["appendIdentificador"];
		var campoAppend = objeto["campoAppend"];

		$("#objeto").val(objetoAnalizado);
		$("#nomeObjeto").val(nomeObjeto);
		$("#append").val(append);
		$("#appendIdentificador").val(appendIdentificador);
		$("#campoAppend").val(campoAppend);

		for(var i = 0; i < objeto["campos"].length; i++ ){
			var campoAnalizado = objeto["campos"][i];
			var id = campoAnalizado["campo"];
			var field = campoAnalizado["field"];
			var required = campoAnalizado["required"];
			var placeHolder = campoAnalizado["placeHolder"];
			var mascara = campoAnalizado["mascara"];

			var requiredStr = "";
			if(required == 'true'){
				requiredStr = " required ";
			}

			var mascaraStr = '';
			if(mascara){
				mascaraStr =  ' alt="' + mascara + '" ';
			}
			var placeHolderStr = '';
			if(placeHolder){
				placeHolderStr =  ' placeHolder="' + decodeEntities(placeHolder) + '" ';
			}

			if(campoAnalizado["type"] == "text" || campoAnalizado["type"] == "bigDecimal" || campoAnalizado["type"] == "integer"){
				var template = '<div class="form-group elements"> '+
							      '<label class="control-label" for="'+ id + '">'+ field + '</label> '+
							      	'<input type="hidden" class="required" name="campos[' + i + '].nome" value="' + id + '">' +
							        '<input type="text" class="form-control ' + requiredStr + '" '+ mascaraStr +' ' +placeHolderStr+  ' name="campos[' + i + '].valor" id="'+ id + '"> '+
							        '<input type="hidden" class="required" name="campos[' + i + '].tipo" value="' + campoAnalizado["type"]+ '">' +
							    '</div>';

				$("#formAdicionarDinamico").append(template);
			}


			if(campoAnalizado["type"] == "boolean"){

				var template = '<div class="form-group custom-control custom-switch"> '+
								'<input type="hidden" class="required" name="campos[' + i + '].nome" value="' + id + '">' +
        						'<input name="campos[' + i + '].valor" checked="checked" type="checkbox" class="custom-control-input" id="ativo" >'+
        						'<input type="hidden" class="required" name="campos[' + i + '].tipo" value="boolean">' +
						        '<label class="custom-control-label" for="'+ id + '">'+ field + '</label>'+
						    '</div>'

				$("#formAdicionarDinamico").append(template);
			}

			if(mascara){
				$('input:text').setMask();
			}

		}

		$("#tituloModalAdicionar").html("Adicionar " + objeto["nome"]);

		$("#formAdicionarDinamico input[type=text]").keypress(function(event){
			if (event.keyCode == 13){
				if($("#formAdicionarDinamico").valid()){
					cadastrarDinamico();
				}
			}
		});


		$('#modalAdicionarDinamico').on('shown.bs.modal', function () {
			$("#formAdicionarDinamico input:text").first().focus();
		})

		showModal('modalAdicionarDinamico');
	});


	$(document).on('click', '#botaoAdicionarDinamico', function(event){
		cadastrarDinamico();
	});

	function cadastrarDinamico(){
	if($("#formAdicionarDinamico").valid()){
		carregando = false;
		ativarCarregando("modalAdicionarDinamico")

		$.ajax({
			url: urlPadrao + "dinamico",
			dataType: "json",
			type: "POST",
			data: $("#formAdicionarDinamico").serialize(),
			success: function(json){
				var appendId = $("#append").val();
				var campoAppend = $("#campoAppend").val();

				if(appendId == 'url'){
					chamarPaginaComUiBlock(campoAppend);
				}else{
					if($('#' + appendId).is("select")){
						if(appendId){
							$('#' + appendId).append('<option value="'+json.retorno.id+'" selected="selected">'+json.retorno[campoAppend]+'</option>');
							$('.selectpicker').selectpicker('refresh');
						}
					}

					if($('#' + appendId).is("input")){
						if(appendId){
							$('#' + appendId).val(json.retorno[campoAppend]);
							$('#' +$('#' + appendId).attr('data-campo-id')).val(json.retorno.id);
						}
					}
				}



				desativarCarregando('modalAdicionarDinamico');
				closeModal('modalAdicionarDinamico');
				adicionaMensagem($("#nomeObjeto").val() + " cadastrado com sucesso!");
			},error: function(json){
				adicionaErro(json);
				desativarCarregando('modalAdicionarDinamico');
		   	}
		});
	}
}

}

function selecionarDinamamicamentFunction(){
	$(".selectDinamico").each(function(){
		atualizarDinamicoParaSelect(this);
	});
}

function executarFunctionsParaDinamico(){
	pesquisarDinamamicamentFunction();
	selecionarDinamamicamentFunction();
	adicionarDinamamicamentFunction();
}

(function(){
	executarFunctionsParaDinamico();
})();

function chamarPaginaComUiBlock(url){
	executarBlockUi();
	document.location.href=url;
}

function executarBlockUi(){
	console.log('executando block ui');
}
