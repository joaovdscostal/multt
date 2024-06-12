"use strict";

(function() {
  new Paginate("categoriaprodutoDatatable")
    .url(urlPadrao + "adm/categorias-de-produto/json")
    .form("categoriaprodutoForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Nome", data: "nome"  },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/categorias-de-produto/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/categorias-de-produto/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/categorias-de-produto/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) categoria de produto?",
        warning: true
      },


    ])
    .start();







})();

