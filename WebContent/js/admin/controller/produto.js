"use strict";

(function() {
  var paginateIndex = new Paginate("produtoDatatable")
    .url(urlPadrao + "adm/produtos/json")
    .form("produtoForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Nome", data: "nome"  },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/produtos/{id}/clonar"
      },
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

	$(document).on('click','.cadastrar-rapido-produto',function(e){
		e.preventDefault();
		abrirModalParaCadastroRapidoDeProduto(paginateIndex);
	});
	
	inputImages();
	
})();

function abrirModalParaCadastroRapidoDeProduto(paginateIndex) {
	var modal = new Modal()
		.ajax(new Ajax(urlPadrao + 'adm/produtos/novo'))
		.ajaxSubmit(function() {
			paginateIndex.start();
		})
		.ajaxErro(function(e) {
			new Notificacao('Erro', e.responseText).erro();
		})
		.comTitulo('Cadastro de Produto do Catálogo');

	modal.executarAoMostrar(function() {});

	modal.extragrande().mostrar();
}
