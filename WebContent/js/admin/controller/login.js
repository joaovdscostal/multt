"use strict";

(function() {
	
	gerenciarTamanhoDeTelaDoDispositivo()

})();

function gerenciarTamanhoDeTelaDoDispositivo(){
	var tamanhoDaTela = $(window).width()
	
	if(tamanhoDaTela < 931){
		console.log("modo mobile ativado")
		$('#nomeUsuarioMobile').addClass('required')
		$('#senhaUsuarioMobile').addClass('required')
		
		$('#nomeUsuarioMobile').attr('name','usuario.login')
		$('#senhaUsuarioMobile').attr('name','usuario.senha')
		
		$('#nomeUsuario').removeClass('required')
		$('#senhaUsuario').removeClass('required')
	} else {
		console.log("modo desktop ativado")
		$('#nomeUsuarioMobile').removeClass('required')
		$('#senhaUsuarioMobile').removeClass('required')
		
		$('#nomeUsuario').attr('name','usuario.login')
		$('#senhaUsuario').attr('name','usuario.senha')
		
		$('#nomeUsuario').addClass('required')
		$('#senhaUsuario').addClass('required')
	}
	
}