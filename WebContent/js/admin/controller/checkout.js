"use strict";

(function() {
  new Paginate("checkoutDatatable")
    .url(urlPadrao + "adm/checkouts/json")
    .form("checkoutForm")
    .columns([
      { title: "#", data: "id" },
	    	{ title: "Nome", data: "nome"  },
    ])
    .buttons([
    {
    	  title: "Clonar",
    	  icon: "fad fa-clone",
    	  url: urlPadrao + "adm/checkouts/{id}/clonar"
      },
      {
    	  title: "Editar",
    	  icon: "far fa-pencil-alt",
    	  url: urlPadrao + "adm/checkouts/{id}/editar"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/checkouts/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este(a) checkout?",
        warning: true
      },


    ])
    .start();

	$(document).on('click','.btn-multt-checkout-forma-pagamento',function(e){
        selecionarBtnCheckout(this)
    })

})();


 function selecionarBtnCheckout(element){
    $('.btn-multt-checkout-forma-pagamento').removeClass('btn-checkout-selecionado');
    $(element).addClass('btn-checkout-selecionado');
    
    var input = $(element).parent().find('input')
    var campoId = $(input).attr('data-selection')
    
    console.log(campoId)
    
    $('.multt-checkout-form-pt-2').find('.row').hide()
    $(campoId).show();
    
}

