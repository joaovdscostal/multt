"use strict";

(function() {
  new Paginate("contaDatatable")
    .url(urlPadrao + "adm/contas/json")
    .form("contaForm")
    .columns([
      { title: "#", data: "id" },
	  { title: "Nome", data: "nome"  },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/contas/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/contas/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/contas/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) conta?",
        warning: true
      },


    ])
    .start();

})();


