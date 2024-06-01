"use strict";

(function() {
  new Paginate("unidadeDatatable")
    .url(urlPadrao + "adm/unidades/json")
    .form("unidadeForm")
    .columns([
      { title: "#", data: "id" },
      { title: "Nome", data: "nome", width: "100%" },
      { title: "Ativo?", data: "ativo", render: renderAtivo }
    ])
    .buttons([
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/unidades/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/unidades/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir esta unidade?",
        warning: true
      }
    ])
    .start();
})();

