"use strict";

(function() {
  new Paginate("ordembumpDatatable")
    .url(urlPadrao + "adm/orders-bump/json")
    .form("ordembumpForm")
    .columns([
      { title: "#", data: "id" },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/orders-bump/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/orders-bump/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/orders-bump/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) ordem bump?",
        warning: true
      },


    ])
    .start();







})();

