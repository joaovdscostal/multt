"use strict";

(function() {
  new Paginate("metododepagamentoDatatable")
    .url(urlPadrao + "adm/metodo-pagamento/json")
    .form("metododepagamentoForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Nome", data: "nome"  },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/metodo-pagamento/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/metodo-pagamento/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/metodo-pagamento/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) metodo de pagamento?",
        warning: true
      },


    ])
    .start();







})();

