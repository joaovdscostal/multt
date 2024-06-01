function confirmPaginateFuncion(url, message){
	new Notificacao('Atenção!', message?message:'Vai excluir?').dialogo().confirm(function(){
		window.location = url;
	});
}
/*
 * Paginate.js - a nds library
 */
$.fn.DataTable.ext.errMode = 'none';

function Paginate(idTable) {

	var datatable;
	var classes = [];

	var url;
	var form;
	var searching = true;

	var funcaoExecutadaAoRenderizarPaginate;

	var initParams = {
		"rowId": 		"id",
//		"ajax": 		url,
		"columns": 		[],
//		"destroy": 		true,
//		"retrieve": 	false,
//		"autoWidth": 	false,
		"serverSide": 	true,
		"processing": true,
		"aaSorting": [[ 0, "desc" ]],
		"dom":
			"<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
			"<'row'<'col-sm-12 col-content-table'tr>>" +
			"<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
		"language": {
		    "sEmptyTable": "Nenhum registro encontrado",
		    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
		    "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
		    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
		    "sInfoPostFix": "",
		    "sInfoThousands": ".",
		    "sLengthMenu": "_MENU_ resultados por página",
		    "sLoadingRecords": "Carregando...",
		    "sProcessing": "Processando...",
		    "sZeroRecords": "Nenhum registro encontrado",
		    "sSearch": "Pesquisar:",
		    "oPaginate": {
		        "sNext": "Próximo",
		        "sPrevious": "Anterior",
		        "sFirst": "Primeiro",
		        "sLast": "Último"
		    },
		    "oAria": {
		        "sSortAscending": ": Ordenar colunas de forma ascendente",
		        "sSortDescending": ": Ordenar colunas de forma descendente"
		    }
		}
	};

	function startPaginate(){
		if($("#" + idTable).length){
				if(datatable){
					datatable.destroy();
					datatable = null;
				}

				try {
					if(form){
						$('.empty-form-paginate').on('click', function(event){

							var elemento = $(".dataTables_filter").find("input[type='search']");
							elemento.val('');

							$("select.selectDinamico,select.custom-select-todos,select.custom-select-filter").not('select[name*="Datatable_length"]').selectpicker('destroy');

							resetForm(form);

							$(".selectDinamico").each(function(){
								atualizarDinamicoParaSelect(this);
							});

							iniciarSelect();

							datatable.ajax.url(url + "?" + form.serialize()).load();
						});
					}

					var urlAjax = url;

					if(form){
						urlAjax = urlAjax + "?" + form.serialize();
					}

					initParams.ajax = urlAjax;
					initParams.searching = searching;

					initParams.drawCallback =  function(settings) {
			            var elementos = $('.edit-in-place-dinamico[data-exibir-editavel="true"]');
					    if(elementos){
							elementos.each(function(){
								var divElement = $(this);
					            var originalValue = divElement.html();

								var tipo = divElement.attr('data-tipo');

								if(tipo == 'bigDecimal'){
									originalValue = originalValue.replace(".", "");
									originalValue = originalValue.replace("R$ ", "");
									originalValue = parseFloat(originalValue.replace(",", ".")).toFixed(2);
								}

								divElement.html("");
								montarInputOuSelect(tipo, originalValue, divElement, false);
							})

							iniciarMeioMask();
						}
						if(funcaoExecutadaAoRenderizarPaginate){
							funcaoExecutadaAoRenderizarPaginate();
						}
						fecharLoading('#body-projeto');
			        }
			        /*

					initParams.preDrawCallback =  function(settings, data1) {
			            console.log('Pre renderizada:', settings);
			            console.log('Pre renderizada:', data1);
			            // Coloque aqui o código que deseja executar após a renderização da linha
			        }*/

					loading('#body-projeto');
					datatable = $('#' + idTable).DataTable(initParams);
					editInPlace();
				}catch(err){
					console.error(err);
				}
			}
	}


	return {
		getDatatable: function(){
			return datatable;
		},
		url: function(pUrl) {
			url = pUrl;
			return this;
		},
		columns: function(columns) {
			initParams.columns = columns;
			return this;
		},
		noSearch: function() {
			searching = false;
			return this;
		},
		executarAoRenderizar: function(funcao){
			funcaoExecutadaAoRenderizarPaginate = funcao;
			return this;
		},
		buttons: function(buttons) {

			var render = function(data, type, row, meta){

				var botoesExtras = '';

				var retorno =  $(buttons).map(function(i, item){
					if(!item)
						return '';

					var url = '';

					if (item.url)
						url = eval("'"+item.url.replace(/{(.*?)}/gm, "'+row.$1+'")+"'");

					var menuExtraExibir = item.menuExtra;
					var classeParaChamarFuncao = item.classeParaChamarFuncao;

					if(menuExtraExibir == undefined){
						menuExtraExibir = false;
					}

					if(item.condicao != undefined){
						var temCondicaoParaExibir = false;
						if(item.condicao.length > 0){
							for(var i = 0; i < item.condicao.length; i++ ){
								var itemCondicao = item.condicao[i];

							    if(acessarValor(row, itemCondicao.campo) == itemCondicao.valor){
									temCondicaoParaExibir = true;
								}

							}
						}

						if(!temCondicaoParaExibir)
							return '';
					}
					if(item.condicaoExcludente != undefined){
						var temCondicaoParaExibir = true;

						if(item.condicaoExcludente.length > 0){
							for(var i = 0; i < item.condicaoExcludente.length; i++ ){
								var itemCondicaoExcludente = item.condicaoExcludente[i];

							    if(acessarValor(row, itemCondicaoExcludente.campo) == itemCondicaoExcludente.valor){
									temCondicaoParaExibir = false;
								}

							}
						}

						if(!temCondicaoParaExibir)
							return '';
					}

					if(menuExtraExibir){
						if(item.confirm){
							botoesExtras += '<a href="javascript:confirmPaginateFuncion(\''+url+'\', \''+item.confirmMessage+'\');" title="'+item.title+'" class="dropdown-item ">'+
										'<i class="fa-fw '+item.icon+'"></i>  ' + item.title+
									'</a>';
						}else{
							if(classeParaChamarFuncao){
								botoesExtras += '<a href="javascript:void(0);" title="'+item.title+'" data-id="'+row.id+'" class="dropdown-item '+classeParaChamarFuncao+' ">'+
										'<i class="fa-fw '+item.icon+'"></i>   ' + item.title+
								   '</a>';
							}else{
								botoesExtras += '<a href="'+url+'" title="'+item.title+'" class="dropdown-item ">'+
										'<i class="fa-fw '+item.icon+'"></i>   ' +item.title+
								   '</a>';
							}
						}
 					}else{
						if(item.confirm){
							return '<a href="javascript:confirmPaginateFuncion(\''+url+'\', \''+item.confirmMessage+'\');" title="'+item.title+'" class="btn btn-sm btn-outline-'+(item.warning?'warning':'secondary')+' mr-2 ">'+
										'<i class="fa-fw '+item.icon+'"></i>'+
									'</a>';
						}else{
							if(classeParaChamarFuncao){
								return '<a href="javascript:void(0);" title="'+item.title+'" data-id="'+row.id+'" class="btn '+classeParaChamarFuncao+' btn-sm btn-outline-'+(item.warning?'warning':'secondary')+' mr-2 ">'+
										'<i class="fa-fw '+item.icon+'"></i>'+
								   '</a>';

								   } else {
									   return '<a href="'+url+'" title="'+item.title+'" class="btn btn-sm btn-outline-'+(item.warning?'warning':'secondary')+' mr-2 ">'+
										'<i class="fa-fw '+item.icon+'"></i>'+
								   '</a>';
								   }

						}
 					}
				}).get().join('');

				if(botoesExtras != ''){
					retorno += '<div class="dropdown" style="display:inline;">'+
						'<button class="btn btn-sm btn-circle btn-outline-secondary" id="dLabel" title="Mais opções" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
							'<i class="fa fa-ellipsis-h" aria-hidden="true"></i>'+
						'</button>'+
						'<ul class="dropdown-menu dropdown-datatable" aria-labelledby="dLabel">'+
							botoesExtras+
						'</ul>'+
					'</div>';
				}

				return retorno;
			};

			initParams.columns.push({ "title": "Opções", "data": "", "orderable": false, "render": render, width: "160px"});


			return this;
		},
		form: function(pIdForm) {
			if(pIdForm && $('#'+pIdForm).length > 0){
				form = $('#'+pIdForm);

				form.find('button').not('.empty-form-paginate,.pesquisaDinamico').on('click', function(e){
					e.preventDefault();
					e.stopPropagation();
					form.submit();
				});

				form.on('submit', function(e){
					if(form.hasClass('validateForm')){
						if(form.valid()){
							if(datatable == undefined){
								startPaginate();
							}else{
								loading('#body-projeto');
								datatable.ajax.url(url + "?" + form.serialize()).load();
							}
						}else{
							console.log('nao é valido');
						}
					}else{
						if(datatable == undefined){
							startPaginate();
						}else{
							loading('#body-projeto');
							datatable.ajax.url(url + "?" + form.serialize()).load();
						}
					}

					e.preventDefault();
					e.stopPropagation();
					return false;
				});
			}
			return this;
		},
		start: function() {
			startPaginate();
			return this;
		}
	}
}


function formatStringToPaginate(inputString) {
    let formattedString = inputString.normalize('NFD').replace(/[\u0300-\u036f]/g, '').toLowerCase();
    formattedString = formattedString.replace(/\s+/g, '');
    return formattedString;
}


/***** renders ********************/
function renderStatus(data, type, row){
	if(data == 'ATIVO')
		return '<label class="label label-primary label-outline">Ativo</label>';
	if(data == 'INATIVO')
		return '<label class="label label-danger label-outline">Inativo</label>';
	if(data == 'PENDENTE')
		return '<label class="label label-warning label-outline">Pendente</label>';
	return '';
}

function renderEditDinamico(data, type, row, meta){
	var exibir =  meta.settings.aoColumns[meta.col].exibir;
	var objeto =  meta.settings.aoColumns[meta.col].objeto;
	var idEditar =  meta.settings.aoColumns[meta.col].idEditar;
	var tipo =  meta.settings.aoColumns[meta.col].tipo;
	var objetoPrincipal =  meta.settings.aoColumns[meta.col].objetoPrincipal;
	var idObjetoPrincipal =  meta.settings.aoColumns[meta.col].idObjetoPrincipal;
	var variavelAlterar =  meta.settings.aoColumns[meta.col].variavelAlterar;
	var exibirEditavel =  meta.settings.aoColumns[meta.col].exibirEditavel;
	var filtrarAtivo =  meta.settings.aoColumns[meta.col].filtrarAtivo;
	var idObjetoPrincipalParametro = '';

	if(idObjetoPrincipal){
		idObjetoPrincipalParametro = acessarValor(row, idObjetoPrincipal);
	}

	var exibirEmTela = '';

	if(!exibirEditavel){
		exibirEmTela = 'Editar';
	}

	if(filtrarAtivo == null || filtrarAtivo == undefined){
		filtrarAtivo = true;
	}

	if(data){

		var valorParaExibir = acessarValor(row, exibir);

		if(tipo == 'bigDecimal'){
			valorParaExibir = renderDinheiroSemCifrao(valorParaExibir);
		}

		if(tipo == 'time'){
			//valorParaExibir = valorParaExibir.replace(" ", "").replace("PM", "").replace("AM", "");
			valorParaExibir = converterParaFormato24Horas(valorParaExibir);
		}



		var textoExibicao = `<span class='edit-in-place-dinamico edit-in-place-dinamico-${meta.row}' data-objeto='${objeto}' data-id='${acessarValor(row, idEditar)}'
		data-campo='${exibir}' data-tipo='${tipo}' data-objeto-principal='${objetoPrincipal}' data-id-objeto-principal='${idObjetoPrincipalParametro}'
		data-variavel-alterar='${variavelAlterar}' data-exibir-editavel='${exibirEditavel}' data-filtrar-ativo='${filtrarAtivo}' >${valorParaExibir}</span>`

		return textoExibicao;
	}else{

		var textoExibicao = `<span class='edit-in-place-dinamico edit-in-place-dinamico-${meta.row}' data-objeto='${objeto}' data-id='${acessarValor(row, idEditar)}'
		data-campo='${exibir}' data-tipo='${tipo}' data-objeto-principal='${objetoPrincipal}' data-id-objeto-principal='${idObjetoPrincipalParametro}'
		data-variavel-alterar='${variavelAlterar}' data-exibir-editavel='${exibirEditavel}' data-filtrar-ativo='${filtrarAtivo}' >${exibirEmTela}</span>`;
		return textoExibicao;
	}
}

function converterParaFormato24Horas(hora12) {
  // Criar um objeto Date com a hora no formato de 12 horas
  var hora12Obj = new Date("2000-01-01 " + hora12);

  // Extrair as horas, minutos e segundos
  var horas = hora12Obj.getHours();
  var minutos = hora12Obj.getMinutes();
  var segundos = hora12Obj.getSeconds();

  // Formatando para adicionar zeros à esquerda, se necessário
  horas = horas < 10 ? '0' + horas : horas;
  minutos = minutos < 10 ? '0' + minutos : minutos;
  segundos = segundos < 10 ? '0' + segundos : segundos;

  // Construir a string no formato de 24 horas
  var hora24 = horas + ':' + minutos + ':' + segundos;

  return hora24;
}

function acessarValor(obj, caminho) {
    var partes = caminho.split('.'); // Divide a string em partes
    var valor = obj;

    for (var i = 0; i < partes.length; i++) {
		try{
	        if (valor.hasOwnProperty(partes[i])) {
	            valor = valor[partes[i]];
	        } else {
	            return undefined; // Retorna undefined se a propriedade não for encontrada
	        }
        }catch(error){
			return undefined;
		}
    }

    return valor;
}


 function editInPlace() {
			$(document).on('click','.edit-in-place-dinamico',function(event){
				event.preventDefault();
				var divElement = $(this);

				var editavel = divElement.attr('data-exibir-editavel');
				var isEditavel = (editavel === true || editavel == 'true') ;


	           	if(!isEditavel){
	            	var originalValue = divElement.html();
					var tipo = divElement.attr('data-tipo');
					divElement.html("");
					montarInputOuSelect(tipo, originalValue, divElement, true);
				}
	            // Quando o usuário pressionar Enter, atualize a <div> e o objeto AJAX
            });
  }

  function montarInputOuSelect(tipo, originalValue, divElement, exibirBlur){
	if(tipo != 'select'){
			var inputElement = document.createElement("input");
            inputElement.type = "text";
            inputElement.className = "form-control";

            if(originalValue != 'Editar'){
            	inputElement.value = originalValue;
            }

           	if(divElement.attr('data-tipo') == 'bigDecimal'){
				   inputElement.alt = 'decimal';
			}

			if(divElement.attr('data-tipo') == 'time'){
				   inputElement.alt = 'time';
			}

			divElement.append(inputElement);

			if(exibirBlur){
				inputElement.focus();
			}

			inputElement.addEventListener("keypress", function(event) {
            	if (event.key === "Enter") {
                    var newValue = inputElement.value;

                    // Atualize o objeto AJAX aqui (substitua esta linha com sua lógica)
                    updateObjectAJAX(newValue, divElement.attr('data-objeto'), divElement.attr('data-id'), divElement.attr('data-campo'), divElement.attr('data-tipo'), divElement);

                    // Remova o ouvinte de eventos
                    inputElement.removeEventListener("keypress", this);
                }
            });

			if(exibirBlur){
	            inputElement.addEventListener("blur", function() {
	                divElement.html(originalValue);
	            });

	            iniciarMeioMask();
            }


		}else{
			var id = new Date().getTime();

			var selectElement = $('<select>');
			selectElement.attr('name', 'select-edit-in-place' + id);
			selectElement.attr('class', 'form-control selectDinamico');
			selectElement.attr('data-toggle', 'select');
			selectElement.attr('data-objeto', divElement.attr('data-objeto'));
			selectElement.attr('data-campos-exibicao', 'getExibicaoPadrao');
			selectElement.attr('data-filtro-ativo', divElement.attr('data-filtrar-ativo'));
			selectElement.attr('data-selected', divElement.attr('data-id'));
			selectElement.attr('id', 'edit-in-place-select'  + id);

			selectElement.append('<option value="" >Selecione</option>');



			divElement.append(selectElement);

			atualizarDinamicoParaSelect(selectElement);


			selectElement.change(function() {
				var value = $(this).val();

				updateObjectAJAX(value, divElement.attr('data-objeto-principal'), divElement.attr('data-id-objeto-principal'), divElement.attr('data-variavel-alterar'), 'id', divElement, $(this).find('option:selected').text());

				selectElement.detach();
				selectElement.remove();

            });

		}
}

  /*

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

			*/

    function updateObjectAJAX(newValue, objeto, id, campo, tipo, divElement, newValueStr) {

		var nomeCampo = campo;

		if(tipo != 'select'){
			nomeCampo = pegarUltimoAtributo(campo);
		}

           $.ajax({
			url: urlPadrao + "dinamico/editInPlace",
			type: "POST",
			data: {
				objeto : objeto,
				id : id,
				nome : nomeCampo,
				valor : newValue,
				tipo : tipo
			},
			success: function(json){

				if(tipo == 'bigDecimal'){
                	divElement.html(renderDinheiroSemCifrao(parseFloat(newValue)));
                }else if (tipo == 'id') {
					divElement.html(newValueStr);
				}else{
					divElement.html(newValue);
				}
			},error: function(json){
				adicionaErro(json);
				desativarCarregando('modalAdicionarDinamico');
		   	}
		});
        }

        function pegarUltimoAtributo(caminho) {
	    var partes = caminho.split('.'); // Divide a string em partes

	    return partes[partes.length - 1];
	}



function renderActive(data, type, row){
	return renderBoolean(data, type, row);
}
function renderBoolean(data, type, row){
	if(data == true)
		return '<label class="badge badge-pill badge-success p-2 pl-3 pr-3">Sim</label>';
	return '<label class="badge badge-pill badge-danger p-2 pl-3 pr-3">N&atilde;o</label>';
}
function renderObjeto(data, type, row){
	if(data == null)
		return '';
	return data;
}
function renderDateMoment(data, type, row){
	if(data == null || !data)
		return '';

	var m = moment(data.substring(0,19));

	var retorno = m.format('DD/MM/YYYY');

	if(retorno == 'Invalid date')
		return data;
	else
		return retorno;
}
function renderDateTimeMoment(data, type, row){
	if(data == null)
		return '';
	var dateFormat = new Date(data);
	var m = moment(dateFormat);
	var retorno = m.format('DD/MM/YYYY HH:mm');

	if(retorno == 'Invalid date')
		return data;
	else
		return retorno;
}

function renderTimeMoment(data, type, row){
	if(data == null)
		return '';

	//var dateFormat = Date.parseExact(data, 'Hh:mm:ss');
	//var m = moment(dateFormat);
	//var retorno = m.format('HH:mm');

//	var retorno = moment(data, 'HH:mm', true);

//	if(retorno == 'Invalid date')
//		return data;
//	else
	return data;
}

function renderDinheiro(data, type, row){
	if(data == null)
		return '';

	try{
		return 'R$ ' + formataNumeroComoMoeda(data);
	}catch (e) {
		return '';
	}
}

function renderPercentual(data, type, row){
	if(data == null)
		return '';

	try{
		return data + '%';
	}catch (e) {
		return '';
	}
}

function renderDinheiroSemCifrao(data, type, row){
	if(data == null)
		return '';

	try{
		return 'R$ ' + formataNumeroComoMoeda(data);
	}catch (e) {
		return '';
	}
}

function formataNumeroComoMoeda(data){
	try{
		return data.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
	}catch (e) {
		try{
			return parseFloat(data).toFixed(2);
		}catch (e) {
			return data;
		}
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

function renderDecimal1(data, type, row){
	if(data == null)
		return '';

	try{
		return parseFloat(data).toFixed(1);
	}catch (e) {
		return '';
	}
}

function renderDateMomentDiaSemana(data, type, row){
	if(data == null)
		return '';

	var m = moment(data.substring(0,19), 'DD/MM/YYYY');
	var retorno = m.format('DD/MM/YYYY (dddd)');

	if(retorno == 'Invalid date')
		return data;
	else
		return retorno;
}

function renderAtivo(data, type, row) {
	return renderActive(data, type, row);
}


function convert_to_24h(time_str) {
    // Convert a string like 10:05:23 PM to 24h format, returns like [22,5,23]
    var time = time_str.match(/(\d+):(\d+):(\d+) (\w)/);
    var hours = Number(time[1]);
    var minutes = Number(time[2]);
    var seconds = Number(time[3]);
    var meridian = time[4].toLowerCase();

    if (meridian == 'p' && hours < 12) {
      hours += 12;
    }
    else if (meridian == 'a' && hours == 12) {
      hours -= 12;
    }
    return (hours<10?'0':'')+hours+":"+(minutes<10?'0':'')+minutes;
  };