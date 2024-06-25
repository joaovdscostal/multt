"use strict";

(function() {
  new Paginate("moduloDatatable")
    .url(urlPadrao + "adm/modulos/json")
    .form("moduloForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Nome", data: "nome"  },
	    	{ title: "Ordem", data: "ordem"  },
	    	{ title: "Produto", data: "produto"  },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/modulos/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/modulos/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/modulos/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) modulo?",
        warning: true
      },


    ])
    .start();







})();

