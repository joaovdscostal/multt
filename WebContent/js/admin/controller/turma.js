"use strict";

(function() {
  new Paginate("turmaDatatable")
    .url(urlPadrao + "adm/turmas/json")
    .form("turmaForm")
    .columns([
      { title: "#", data: "id" },
      { title: "Nome", data: "nome" },
	  { title: "Valor", data: "valor",width:'100%'}
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/turmas/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/turmas/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/turmas/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) turma?",
        warning: true
      },


    ])
    .start();







})();

