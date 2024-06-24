"use strict";

(function() {
  new Paginate("ofertaDatatable")
    .url(urlPadrao + "adm/ofertas/json")
    .form("ofertaForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Nome", data: "nome"  },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/ofertas/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/ofertas/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/ofertas/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) oferta?",
        warning: true
      },


    ])
    .start();







})();

