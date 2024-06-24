"use strict";

(function() {
  new Paginate("imagemDatatable")
    .url(urlPadrao + "adm/imagens/json")
    .form("imagemForm")
    .columns([
      { title: "#", data: "id" },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/imagens/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/imagens/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/imagens/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) imagem?",
        warning: true
      },


    ])
    .start();







})();

