"use strict";

(function() {
  var produtoId = $('#produtoId').val();
	
  var paginateIndex = new Paginate("produtoDatatable")
    .url(urlPadrao + "adm/produtos/json")
    .form("produtoForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Nome", data: "nome"  },
    ])
    .buttons([
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/produtos/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/produtos/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) produto?",
      },
    ])
    .start();   
    
//.............................................................................................................................................
    
    var paginateCursos = new Paginate("produtoCursos")
    .url(urlPadrao + "adm/produtos/json")
    .form("produtoCursoForm")
    .columns([
      { title: "#", data: "id" },
	  { title: "Nome", data: "nome"  },
	  { title: "URL", data: "url"  },
	  { title: "Alunos", data: "qtdAlunos"},
    ])
    .buttons([     
      {
        title: "Acessar",
        icon: "fas fa-wrench",
        url: urlPadrao + "adm/area-de-membros/{id}/config"   
      },
    ])
    .start();   

//.............................................................................................................................................
    
     var paginateTurmas = new Paginate("turmaDatatableMembros")
    .url(urlPadrao + `adm/turmas/${produtoId}/json`)
    .form("turmaFormMembros")
    .columns([
  		{ title: "#", data: "id" },
    	{ title: "Nome", data: "nome" },
	  	{ title: "Valor", data: "valor"}
    ])
    .buttons([
        {
    	    title: "Clonar",
    	    icon: "fad fa-clone",
    		classeParaChamarFuncao: 'clonar-turma'
        },
        {
    	    title: "Editar",
    	    icon: "far fa-pencil-alt",
    	    classeParaChamarFuncao: 'editar-turma'
        },
        {
	        title: "Remover",
	        icon: "far fa-trash",
	        classeParaChamarFuncao: 'del-turma',
	        warning: true
        },
    ])
    .start();
    
//.............................................................................................................................................
    
    var paginateMembros = new Paginate("alunosDatatableMembros")
    .url(urlPadrao + `adm/matriculas/por-produto/${produtoId}/json`)
    .form("alunosForm")
    .columns([
	    { title: "#", data: "id"},
		{ title: "Aluno", data: "aluno.nome"},
		{ title: "Turma", data: "turma.nome"},
		//{ title: "Status", data: "statusMatricula" },
		//{ title: "data", data: "data", render: renderDateTimeMoment},
    ])
    .buttons([
		/*{
		    title: "Clonar",
		    icon: "fad fa-clone",
		    url: urlPadrao + "adm/matriculas/{id}/clonar"
		},*/
		{
		    title: "Editar",
		    icon: "far fa-pencil-alt",
		    classeParaChamarFuncao: 'editar-matricula',
		},
		{
		    title: "Remover",
		    icon: "far fa-trash",
		    classeParaChamarFuncao: 'del-matricula',
		    warning: true
		},
    ])
    .start();
    
//.............................................................................................................................................

	$(document).on('click','.cadastrar-rapido-produto',function(e){
		e.preventDefault();
		abrirModalParaCadastroRapidoDeProduto(paginateIndex);
	});
	
	$(document).on('click','.cadastrar-checkout',function(e){
		e.preventDefault();
		var idProduto = $(this).attr('data-produto-id')
		abrirModalCheckout(idProduto);
	});
	
	$(document).on('click','.verificar-disponibilidade-oferta',function(e){
		var idOferta = $(this).attr('data-oferta-id');
		verificarDisponibilidadeDaOferta(idOferta,this)
	});
	
	$(document).on('click','.removeCheckout',function(e){
		e.preventDefault();
		var idCheckout = $(this).attr('data-id')
		apagarCheckout(idCheckout);
	});
	
	$(document).on('click','.selection-card-label',function(e){
		$('#form-container-produto').show();
	});
	
	
	//...OrderBump.......................................................
	
	$(document).on('click','.editarBump',function(e){
		e.preventDefault();
		var idOrderBump = $(this).attr('data-id');
		abrirModalParaEdicaoDeBump(idOrderBump);
	});
	
	$(document).on('click','.removeBump',function(e){
		e.preventDefault();
		var idOrderBump = $(this).attr('data-id');
		excluirOrderBump(idOrderBump);
	});
	
	$(document).on('change','#exibirImagemBump',function(e){
		var produtoImagem = $('#produtoBump').find('option:selected').attr('data-img')
		
		if(variavelPossuiValor(produtoImagem)){
			alternarImagemBump()
		} else {
			new Notificacao('Ops!','Não há produto selecionado, ou o produto selecionado não tem imagem').erro();
			$('#exibirImagemBump').prop('checked', false);
		}
	
	});
	
	$(document).on('click','.cadastrar-order-bump',function(e){
		e.preventDefault();
		var idProduto = $(this).attr('data-produto-id')
		abrirModalParaCadastroDeOrderBump(idProduto);
	});
	
	$(document).on('change','#produtoBump',function(e){
		gerarModalDeOfertasParaProduto($(this).val())
	});
	
	$(document).on('change','#ofertaBump',function(e){
		gerarPreVisualizacaoBump()
	});
	
	$(document).on('change','#tituloBump',function(e){
		gerarPreVisualizacaoBump()
	});
	
	$(document).on('change','#callToActionBump',function(e){
		gerarPreVisualizacaoBump()
	});
	
	$(document).on('change','#descricaoBump',function(e){
		gerarPreVisualizacaoBump();
	});
	
	//...OrderBump.......................................................
	
	//...Oferta.......................................................
		
	$(document).on('change','.verificar-disponibilidade-oferta',function(e){
		$('.verificar-disponibilidade-oferta').prop('checked',false)
		$(this).prop('checked',true)
	});
	
	$(document).on('click','.addOferta',function(e){
		e.preventDefault();
		addOferta(this)
	});
	
	$(document).on('click','.removeOferta',function(e){
		e.preventDefault();
		new Notificacao('Atenção!', 'Esta oferta será desabilitada permanentemente,deseja continuar?').dialogo().confirm(function(){
			removeOferta(this)
		});
	});
		
	//...Oferta.......................................................
	
	//...Matricula.......................................................
	
	$(document).on('click','.add-matricula',function(e){
		e.preventDefault();
		var produtoId = $('#produtoId').val();
		new Ajax(urlPadrao + `adm/modulos/turmas/produto/${produtoId}/possui-turmas/ajax`)
		.erro(function(data){
			new Notificacao('Ops!', data.responseText).erro();
		})
		.sucesso(function(data){
			abrirModalParaCadastroRapidoDeMatricula();
			paginateMembros.start();
			buscarDadosDeAlunos(produtoId);
		});
	});
	
	$(document).on('click','.del-matricula',function(e){
		e.preventDefault();
		var matriculaId = $(this).attr('data-id');
		excluirMatricula(matriculaId,paginateMembros);
	});
	
	/*$(document).on('click','.clonar-matricula',function(e){
		e.preventDefault();
		var matriculaId = $(this).attr('data-id');
		clonarMatricula(matriculaId,paginateMembros);
	});*/
	
	$(document).on('click','.editar-matricula',function(e){
		e.preventDefault();
		var matriculaId = $(this).attr('data-id');
		abrirModalParaEdicaoRapidaDeMatricula(matriculaId,paginateMembros);
	});
	
	//...Matricula.......................................................
	
	//...Turma.......................................................
	
	$(document).on('click','.add-turma',function(e){
		e.preventDefault();
		abrirModalParaCadastroRapidoDeTurma(paginateTurmas);
	});
	
	$(document).on('click','.del-turma',function(e){
		e.preventDefault();
		var turmaId = $(this).attr('data-id');
		excluirTurma(turmaId,paginateTurmas);
	});
	
	$(document).on('click','.clonar-turma',function(e){
		e.preventDefault();
		var turmaId = $(this).attr('data-id');
		clonarTurma(turmaId,paginateTurmas);
	});
	
	$(document).on('click','.editar-turma',function(e){
		e.preventDefault();
		var turmaId = $(this).attr('data-id');
		abrirModalParaEdicaoRapidaDeTurma(turmaId,paginateTurmas);
	});
	
	$(document).on('click','#todasAsTurmas',function(e){
		var valorCampo = $(this).is(':checked');
		var todasMarcadas = true;
		
		if(valorCampo ==  true){
			$('.turma-selection').prop('checked',true)
		}
		
		$('.turma-selection').each(function(index,element){
			if($(element).is(':checked') == false){
				todasMarcadas = false;
			}
		})
		
		$('#todasAsTurmas').prop('checked',todasMarcadas)
		
	});
	
	$(document).on('click','.turma-selection',function(e){
		var todasMarcadas = true;
		
		$('.turma-selection').each(function(index,element){
			if($(element).is(':checked') == false){
				todasMarcadas = false;
			}
		})
		
		console.log(todasMarcadas);
		
		$('#todasAsTurmas').prop('checked',todasMarcadas)
	});
	
	//...Turma.......................................................
	
	//...Modulo e Conteudo.......................................................
	
	$(document).on('click','.add-modulo',function(e){
		e.preventDefault();
		var produtoId = $('#produtoId').val();
		new Ajax(urlPadrao + `adm/modulos/turmas/produto/${produtoId}/possui-turmas/ajax`)
		.erro(function(data){
			new Notificacao('Ops!', data.responseText).erro();
		})
		.sucesso(function(data){
			abrirModalParaCadastroRapidoDeModulo();
		});		
	});
	
	$(document).on('click','.editar-modulo',function(e){
		gerarModalParaEdicaoRapidaDeModulo(this);
	});
	
	$(document).on('click','.add-conteudo',function(e){
		e.preventDefault();
		var modId = $('.add-conteudo').attr('data-mod-id');
		abrirModalParaCadastroRapidoDeConteudo(modId);
	});
	
	$(document).on('change','#conteudoTipoliberacao',function(e){
		var tipo = $(this).val();
		console.log(tipo)
		
		$('.liberacao-conteudo').hide();
		
		if(tipo == 'POR_DIAS_APOS_COMPRA'){
			$('#dias-apos-liberacao').show();
		} 
		
		if(tipo == 'POR_DATA'){
			$('#data-liberacao').show();
		} 
		
	});
	
	$(document).on('click','.editar-conteudo',function(e){
		var idConteudo = $(this).attr('data-id-conteudo')
		abrirModalParaEditarConteudo(idConteudo)
	});
	
	$(document).on('click','.excluir-conteudo',function(e){
		var idConteudo = $(this).attr('data-id-conteudo')
		excluirConteudo(idConteudo)
	});
	
	//...Modulo e Conteudo.......................................................
		
	inputImages();
	gerarModulos();
	buscarDadosDeAlunos(produtoId);
})();

function buscarDadosDeAlunos(idProduto) {
	
	new Ajax(urlPadrao + `adm/alunos/buscar-dados-produto/${idProduto}/ajax`)
	.erro(function(data){
		new Notificacao('Erro', e.responseText).erro();
	})
	.sucesso(function(data){
		var retorno = data.retorno;
		renderizarDadosDeAlunosParaProduto(retorno)
	});
}

function renderizarDadosDeAlunosParaProduto(dados) {
	console.log(dados);
	$('#alunoQuantidade').html(dados[0])
}

function excluirConteudo(idConteudo) {
	new Ajax(urlPadrao + `adm/conteudos/${idConteudo}/apagar`)
	.sucesso(function(data){
		gerarModulos()
		new Notificacao('Sucesso!','Conteúdo excluído com sucesso!').sucesso();
	});
}

function abrirModalParaEditarConteudo(idConteudo) {
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao + `adm/conteudos/${idConteudo}/editar/modal`))
	.ajaxSubmit(function(data) {
		gerarModulos()
		new Notificacao('Sucesso!','Conteúdo editado com sucesso!').sucesso();
	})
	.ajaxErro(function(e) {
		new Notificacao('Erro', e.responseText).erro();
	})
	.comTitulo('Edição de Conteúdo');

	modal.executarAoMostrar(function() {});
	
	modal.executarAoFechar(function() {})
	
	modal.extragrande().mostrar();
}

function gerarModulos() {
	if ($("#modulos-container").length) {
		var id = $("#produtoId").val();

		new Ajax(urlPadrao + `adm/modulos/${id}/template`)
		.sucesso(function(data) {
			console.log(data)
			
			var dataTemplate = { dados: data.retorno };
			var template = $.templates("#ModuloTemplate");
			var htmlTemplate = template.render(dataTemplate);

			$("#modulos-container").html(htmlTemplate);
		});
	}
}

function verificarDisponibilidadeDaOferta(idOferta,elemento) {
	new Ajax(urlPadrao + `adm/checkouts/verificar-oferta/${idOferta}/ajax`)
	.erro(function(e){
		new Notificacao('Erro', e.responseText).erro();
	})
	.sucesso(function(data){
		if(data.retorno == false){
			new Notificacao('Erro', "Esta oferta já está vinculada a outro checkout").erro();
			$(elemento).prop('checked',false)
		}
		
	});
}

function apagarCheckout(idCheckout) {
	new Ajax(urlPadrao + `adm/checkouts/${idCheckout}/apagar`)
	.erro(function(e){
		new Notificacao('Erro', e.responseText).erro();
	})
	.sucesso(function(data){
		gerarListaDeCheckouts($('#produtoId').val());
	});
}

function abrirModalCheckout(idProduto) {
	var modal = new Modal()
		.ajax(new Ajax(urlPadrao + `adm/checkouts/novo/${idProduto}/modal`))
		.ajaxSubmit(function(data) {})
		.ajaxErro(function(e) {
			new Notificacao('Erro', e.responseText).erro();
		})
		.comTitulo('Cadastro de Checkout');

	modal.executarAoMostrar(function() {});
	
	modal.executarAoFechar(function() {
		if(variavelPossuiValor($('#produtoId').val())){
			gerarListaDeCheckouts($('#produtoId').val());
		}
	})
	
	modal.extragrande().mostrar();
}

function abrirModalParaCadastroRapidoDeTurma(paginateTurmas) {
	var produtoId = $('#produtoId').val();
	
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao + `adm/turmas/novo/modal?produto.id=${produtoId}`))
	.ajaxSubmit(function(data) {
		paginateTurmas.start();
	})
	.ajaxErro(function(e) {
		new Notificacao('Erro', e.responseText).erro();
	})
	.comTitulo('Cadastro de Turma');

	modal.executarAoMostrar(function() {});
	modal.medio().mostrar();
}

function abrirModalParaEdicaoRapidaDeTurma(turmaId,paginateTurmas){
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao + `adm/turmas/${turmaId}/editar/modal`))
	.ajaxSubmit(function(data) {
		paginateTurmas.start();
	})
	.ajaxErro(function(e) {
		new Notificacao('Erro', e.responseText).erro();
	})
	.comTitulo('Edição de Turma');

	modal.executarAoMostrar(function() {});
	modal.medio().mostrar();
}

function abrirModalParaEdicaoRapidaDeMatricula(matriculaId,paginateMatriculas){
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao + `adm/matriculas/${matriculaId}/editar/modal`))
	.ajaxSubmit(function(data) {
		paginateMatriculas.start();
	})
	.ajaxErro(function(e) {
		new Notificacao('Erro', e.responseText).erro();
	})
	.comTitulo('Edição de Matricula');

	modal.executarAoMostrar(function() {});
	modal.medio().mostrar();
}



function excluirMatricula(matriculaId,paginateMembros){
	new Notificacao('Atenção!', 'Deseja mesmo apagar esta matrícula?').dialogo().confirm(function(){
		new Ajax(urlPadrao + `adm/matriculas/${matriculaId}/apagar/ajax`)
		.post()
		.erro(function(data){
			new Notificacao('Erro', data.responseText).erro();
			swal.close();
		})
		.sucesso(function(data){
			console.log();
			new Notificacao('Sucesso!', data.retorno).sucesso();
			paginateMembros.start();
			swal.close();
		});
	});
	
}

function excluirTurma(turmaId,paginateTurmas){
	new Notificacao('Atenção!', 'Deseja mesmo apagar esta turma?').dialogo().confirm(function(){
		new Ajax(urlPadrao + `adm/turmas/${turmaId}/apagar/ajax`)
		.post()
		.erro(function(data){
			new Notificacao('Erro', data.responseText).erro();
			swal.close();
		})
		.sucesso(function(data){
			console.log();
			new Notificacao('Sucesso!', data.retorno).sucesso();
			paginateTurmas.start();
			swal.close();
		});
	});
	
}

function clonarTurma(turmaId,paginateTurmas) {
	new Ajax(urlPadrao + `adm/turmas/${turmaId}/clonar/ajax`)
	.post()
	.erro(function(data){
		new Notificacao('Erro', data.responseText).erro();
	})
	.sucesso(function(data){
		console.log(data.retorno);
		new Notificacao('Sucesso!', data.retorno).sucesso();
		paginateTurmas.start();
	});
}

function abrirModalParaCadastroRapidoDeModulo() {
	var produtoId = $('#produtoId').val();
	
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao + `adm/modulos/novo/modal?produtoId=${produtoId}`))
	.ajaxSubmit(function(data) {
		gerarModulos()
		new Notificacao('Sucesso!','Módulo cadastrado com sucesso!').sucesso();
	})
	.ajaxErro(function(e) {
		new Notificacao('Erro', e.responseText).erro();
	})
	.comTitulo('Cadastro de Módulo');

	modal.executarAoMostrar(function() {});
	modal.medio().mostrar();
}

function abrirModalParaCadastroRapidoDeMatricula() {
	var produtoId = $('#produtoId').val();
	
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao + `adm/matriculas/novo/modal?produtoId=${produtoId}`))
	.ajaxSubmit(function(data) {
		new Notificacao('Sucesso!','Aluno cadastrado com sucesso!').sucesso();
	})
	.ajaxErro(function(e) {
		new Notificacao('Erro', e.responseText).erro();
	})
	.comTitulo('Cadastro de Matrícula');

	modal.executarAoMostrar(function() {});
	modal.medio().mostrar();
}



function gerarModalParaEdicaoRapidaDeModulo(elemento) {
	var moduloId = $(elemento).attr('data-mod-id');
	
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao + `adm/modulos/editar/${moduloId}/modal`))
	.ajaxSubmit(function(data) {
		gerarModulos()
		new Notificacao('Sucesso!','Módulo editado com sucesso!').sucesso();
	})
	.ajaxErro(function(e) {
		new Notificacao('Erro', e.responseText).erro();
	})
	.comTitulo('Cadastro de Módulo');

	modal.executarAoMostrar(function() {});
	modal.executarAoFechar(function() {
		gerarModulos()
	})
	modal.medio().mostrar();
}

function abrirModalParaCadastroRapidoDeConteudo(modId) {
	console.log(modId)
	
	var modal = new Modal()
	.ajax(new Ajax(urlPadrao + `adm/conteudos/novo?modulo.id=${modId}`))
	.ajaxSubmit(function(data) {
		gerarModulos()
		new Notificacao('Sucesso!','Conteúdo cadastrado com sucesso!').sucesso();
	})
	.ajaxErro(function(e) {
		new Notificacao('Erro', e.responseText).erro();
	})
	.comTitulo('Cadastro de Conteúdo');

	modal.executarAoMostrar(function() {});
	modal.extragrande().mostrar();
}

function abrirModalParaCadastroRapidoDeProduto(paginateIndex) {
	var modal = new Modal()
		.ajax(new Ajax(urlPadrao + 'adm/produtos/novo'))
		.ajaxSubmit(function(data) {
			window.location.href = `${urlPadrao}adm/produtos/${data.retorno.id}/editar`;
		})
		.ajaxErro(function(e) {
			new Notificacao('Erro', e.responseText).erro();
		})
		.comTitulo('Cadastro de Produto');

	modal.executarAoMostrar(function() {});
	modal.extragrande().mostrar();
}

function abrirModalParaCadastroDeOrderBump(idProduto) {
	var modalBump = new Modal()
		.ajax(new Ajax(urlPadrao + `adm/produtos/${idProduto}/order-bump/modal`))
		.ajaxSubmit(function(data) {})
		.ajaxErro(function(e) {
			new Notificacao('Erro', e.responseText).erro();
		})
		.comTitulo('Cadastro de Order Bump');

	modalBump.executarAoMostrar(function() {
		gerarPreVisualizacaoBump();
	});
	
	modalBump.executarAoFechar(function() {
		if(variavelPossuiValor($('#produtoId').val())){
			gerarListaDeOrdersBump($('#produtoId').val());
		}
	})
	
	modalBump.extragrande().mostrar();
}

function abrirModalParaEdicaoDeBump(idOrderBump) {
	var modalBump = new Modal()
		.ajax(new Ajax(urlPadrao + `adm/produtos/order-bump/editar/${idOrderBump}/modal`))
		.ajaxSubmit(function(data) {})
		.ajaxErro(function(e) {
			new Notificacao('Erro', e.responseText).erro();
		})
		.comTitulo('Editar Order Bump');

	modalBump.executarAoMostrar(function() {
		gerarPreVisualizacaoBump()
	});
	
	modalBump.executarAoFechar(function() {
		if(variavelPossuiValor($('#produtoId').val())){
			gerarListaDeOrdersBump($('#produtoId').val());
		}
	})
	
	modalBump.extragrande().mostrar();
}

function gerarModalDeOfertasParaProduto(idProduto) {
	var containerProduto = $('#produtoBump').parent()
	
	new Ajax(urlPadrao + `adm/ofertas/buscar-por-produto/${idProduto}`)
	.erro(function(data){
		new Notificacao('Erro', e.responseText).erro();
	})
	.sucesso(function(data){
		var ofertas = data.retorno;
		
		var ofertasListFormatada = ofertas.map(function(oferta){
			return `<option data-valor="${oferta.valor}" value="${oferta.id}"> ${oferta.nome} </option>`;
		})
		
		$('#ofertaBump').parent().remove();
		
		$(containerProduto).after(`
			<div class="form-group  col-md-6">
		        <label for="ofertaBump" class="control-label">Oferta</label>
		        <select name="ordembump.oferta.id" id="ofertaBump" class="form-control required">
			    	<option value=""> - </option>
			    	${ofertasListFormatada}
			    </select>
		    </div>
		`)
		
		gerarPreVisualizacaoBump()
	});
}

function gerarListaDeOrdersBump(idProduto) {
	
	new Ajax(urlPadrao + `adm/produtos/${idProduto}/buscar-bumps/ajax`)
	.erro(function(e){
		new Notificacao('Erro', e.responseText).erro();
	})
	.sucesso(function(data){
		console.log(data)
		var retorno = data.retorno;
		
		var bumps = retorno.map(function(bump){
			return `
				<tr>
    				<td>${bump.id}</td>
    				<td style="width:100%">${bump.titulo}</td>
    				<td class="d-flex">
    					<button data-id="${bump.id}" class="btn btn-circle btn-secondary editarBump mr-2"><i class="fas fa-pen"></i></button>
    					<button data-id="${bump.id}" class="btn btn-circle btn-danger removeBump"><i class="fas fa-times"></i></button>				    					
					</td>
				</tr>
			`;
		})
		
		$("#bumpBody").html(`
			${bumps}
		`)
		
		if(!variavelPossuiValor(retorno[0])){
			$("#bumpBody").html(`
				<tr>
				 	<td colspan="3" class="text-center">Nenhum Bump Cadastrado</td>
				</tr>
			`)
			
		}
	});
	
}

function gerarListaDeCheckouts(idProduto) {
	
	new Ajax(urlPadrao + `adm/produtos/${idProduto}/buscar-checkouts/ajax`)
	.erro(function(e){
		new Notificacao('Erro', e.responseText).erro();
	})
	.sucesso(function(data){
		console.log(data)
		var retorno = data.retorno;	
		
		var checkouts = retorno.map(function(checkout){
			return `
				<tr>
    				<td>${checkout.id}</td>
    				<td style="width:100%">${checkout.nome}</td>
    				<td class="d-flex">
    					<button data-id="${checkout.id}" class="btn btn-circle btn-danger removeCheckout"><i class="fas fa-times"></i></button>				    					
					</td>
				</tr>
			`;
		})
		
		$("#checkoutBody").html(`
			${checkouts}
		`)
		
		if(!variavelPossuiValor(retorno[0])){
			$("#checkoutBody").html(`
				<tr>
				 	<td colspan="3" class="text-center">Nenhum Checkout Cadastrado</td>
				</tr>
			`)
		}
	});
	
}

function excluirOrderBump(idOrder) {
	new Ajax(urlPadrao + `adm/orders-bump/${idOrder}/apagar`)
	.erro(function(e){
		new Notificacao('Erro', e.responseText).erro();
	})
	.sucesso(function(data){
		if(variavelPossuiValor($('#produtoId').val())){
			gerarListaDeOrdersBump($('#produtoId').val());
		}
	});
}

function gerarPreVisualizacaoBump() {
	var isChecked = $("#exibirImagemBump").is(':checked');
	var titulo = $('#tituloBump').val();
	var callToAction = $('#callToActionBump').val()
	var descricao = $('#descricaoBump').val();
	
	var produtoImagem = $('#produtoBump').find('option:selected').attr('data-img')
	var ofertaValor = $('#ofertaBump').find('option:selected').attr('data-valor')
	
	$('.pre-vizualizacao-order-bump').html(`
		<div style="background-image: url('${urlPadrao}arquivos/imagens/${produtoImagem}')" class="img-container-bump img-bump-molile bump-mobile"></div>
		<div class="card d-flex flex-row m-0">
			<div style="background-image: url('${urlPadrao}arquivos/imagens/${produtoImagem}')" class="img-container-bump img-bump-desktop bump-desktop"></div>
			<div class="container-bump">
				<div class="card-header">
					<div class="d-flex align-items-center">							
						<h3 class="bump-call-to-action">${callToAction}</h3>
						<i class="fas fa-arrow-alt-right mr-2 ml-2"></i>
						<input type="checkbox"/>
					</div>
				</div>
				<div class="card-body">
					<h4>
						<span class="bump-titulo">${titulo}:</span> 
						<span class="bump-detalhes">${descricao} - </span>
						<span class="bump-valor">R$ 0,00</span>
					</h4>
				</div>
			</div>
		</div>
	`)
	
	if(!isChecked){
		$('.img-container-bump').hide();
	}
	
	if(!variavelPossuiValor(produtoImagem)){
		$('.img-container-bump').hide();
		$('#exibirImagemBump').prop('checked', false);
	}
	
	if(possuiValor(ofertaValor)){
		$('.bump-valor').html(`R$ ${ofertaValor}`);		
	}
	
	alternarImagemBump()
}

function alternarImagemBump() {
	var isChecked = $("#exibirImagemBump").is(':checked');
	var larguraTela = window.innerWidth;
	
	if(larguraTela > 550){
		if(isChecked){
			$('.img-bump-desktop').show();
			$('.card-header').css('border-top-left-radius','0px')
		} else {
			$('.img-bump-desktop').hide();
			$('.card-header').css('border-top-left-radius','calc(0.5rem - 1px)')
		}
	} else {
		if(isChecked){
			$('.img-bump-molile').show();
			$('.card-header').css('border-top-left-radius','0px')
		} else {
			$('.img-bump-molile').hide();
			$('.card-header').css('border-top-left-radius','calc(0.5rem - 1px)')
		}
	}
}

//..Ofertas....................................................................................................................

var contadorOfertas = $('.multt-produto-ofertas-list-container').find('.multt-produto-oferta').length

function addOferta(el) {
	
	var btnContainer = $(el).parent()
	var item = $(el).closest('.multt-produto-oferta')
	var nome = $(item).find('.nomeOferta').val()
	var valor = $(item).find('.valorOferta').val()
	
	if(!variavelPossuiValor(nome)){
		//$(item).find('.nomeOferta').addClass('isInvalid');
		new Notificacao('Atenção!', 'Insira um nome para a oferta').erro();
		return;
	}
	
	if(!variavelPossuiValor(valor)){
		//$(item).find('.valorOferta').addClass('isInvalid');
		new Notificacao('Atenção!', 'Insira um valor para a oferta').erro();
		return;
	}
	
	$(btnContainer).html(`<button class="btn btn-circle btn-danger removeOferta"><i class="fas fa-times"></i></i></button>`)
	
	$(item).after(` 
		<div class="multt-produto-oferta row align-items-center mt-3">
    		<div class="form-group col-md-4 m-0">				       
		        <input type="text" name="produto.ofertas[${contadorOfertas}].nome" placeholder="Nome da Oferta" class="form-control nomeOferta"/>
		    </div>
		    <div class="form-group col-md-4 m-0">				        
		        <input type="text" name="produto.ofertas[${contadorOfertas}].valor" placeholder="Valor da Oferta" class="form-control valorOferta" alt="decimalSemZero"/>
		    </div>
		     <div class="form-group col-md-1 m-0 d-flex align-items-end">					    	
    			<button class="btn btn-circle btn-primary addOferta mr-3"><i class="fas fa-plus"></i></button>					        			    
		    </div>					    
    	</div>
	`)
	
	contadorOfertas++
	iniciarMeioMask();
}

function removeOferta(e) {
	$(e).closest('.multt-produto-oferta').remove()
}
