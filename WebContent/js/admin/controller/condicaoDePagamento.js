"use strict";

(function() {
  new Paginate("condicaodepagamentoDatatable")
    .url(urlPadrao + "adm/condicoes-de-pagamento/json")
    .form("condicaodepagamentoForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Nome", data: "nome"  },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/condicoes-de-pagamento/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/condicoes-de-pagamento/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/condicoes-de-pagamento/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) condição de pagamento?",
        warning: true
      },


    ])
    .start();







})();

