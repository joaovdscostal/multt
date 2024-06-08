"use strict";

(function() {
	
	gerenciarTamanhoDeTelaDoDispositivo()

})();

function gerenciarTamanhoDeTelaDoDispositivo(){
	var tamanhoDaTela = $(window).width()
	
	if(tamanhoDaTela < 931){
		console.log("modo mobile ativado")
		$('#nomeUsuarioMobile').addClass('required')
		$('#emailUsuarioMobile').addClass('required')
		$('#senhaUsuarioMobile').addClass('required')
		$('#celularUsuarioMobile').addClass('required')
		
		$('#nomeUsuarioMobile').attr('name','conta.nome')
		$('#emailUsuarioMobile').attr('name','conta.email')
		$('#senhaUsuarioMobile').attr('name','conta.usuario.senha')
		$('#celularUsuarioMobile').attr('name','conta.celular')
		
		$('#nomeUsuario').removeClass('required')
		$('#emailUsuario').removeClass('required')
		$('#senhaUsuario').removeClass('required')
		$('#celularUsuario').removeClass('required')
	} else {
		console.log("modo desktop ativado")
		$('#nomeUsuarioMobile').removeClass('required')
		$('#emailUsuarioMobile').removeClass('required')
		$('#senhaUsuarioMobile').removeClass('required')
		$('#celularUsuarioMobile').removeClass('required')
		
		$('#nomeUsuario').attr('name','conta.nome')
		$('#emailUsuario').attr('name','conta.email')
		$('#senhaUsuario').attr('name','conta.usuario.senha')
		$('#celularUsuario').attr('name','conta.celular')
		
		$('#nomeUsuario').addClass('required')
		$('#emailUsuario').addClass('required')
		$('#senhaUsuario').addClass('required')
		$('#celularUsuario').addClass('required')
	}
	
}