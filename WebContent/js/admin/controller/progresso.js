"use strict";

(function() {
  new Paginate("progressoDatatable")
    .url(urlPadrao + "adm/progresso/json")
    .form("progressoForm")
    .columns([
      { title: "#", data: "id" },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/progresso/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/progresso/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/progresso/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) progresso?",
        warning: true
      },


    ])
    .start();







})();

