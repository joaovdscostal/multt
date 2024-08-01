"use strict";

(function() {
  new Paginate("conteudoDatatable")
    .url(urlPadrao + "adm/conteudos/json")
    .form("conteudoForm")
    .columns([
      { title: "#", data: "id" },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/conteudos/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/conteudos/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/conteudos/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) conteudo?",
        warning: true
      },


    ])
    .start();







})();

