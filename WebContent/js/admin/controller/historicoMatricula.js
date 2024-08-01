"use strict";

(function() {
  new Paginate("historicomatriculaDatatable")
    .url(urlPadrao + "adm/historico-matricula/json")
    .form("historicomatriculaForm")
    .columns([
      { title: "#", data: "id" },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/historico-matricula/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/historico-matricula/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/historico-matricula/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) historico matricula?",
        warning: true
      },


    ])
    .start();







})();

