"use strict";

(function() {
  new Paginate("matriculaDatatable")
    .url(urlPadrao + "adm/matriculas/json")
    .form("matriculaForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Aluno", data: "aluno"  },
	    	{ title: "Turma", data: "turma"  },
	    	{ title: "Status", data: "statusMatricula"  },
	    	{ title: "data", data: "data"  , render: renderDateTimeMoment},
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/matriculas/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/matriculas/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/matriculas/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) matricula?",
        warning: true
      },


    ])
    .start();
    
    







})();

