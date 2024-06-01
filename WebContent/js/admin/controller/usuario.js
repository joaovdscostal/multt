"use strict";

(function() {
  new Paginate("usuarioDatatable")
    .url(urlPadrao + "adm/usuarios/json")
    .form("usuarioForm")
    .columns([
      { title: "#", data: "id" },
      { title: "Nome", data: "nome", width: "100%" },
      { title: "Login", data: "login" },
      { title: "Nº de acessos", data: "contadorAcesso" },
      { title: "Tipo", data: "tipo", render: renderTipoUsuario },
      { title: "Ativo?", data: "ativo", render: renderAtivo }
    ])
    .buttons([
      {
        title: "Editar",
        icon: "far fa-pencil-alt",
        url: urlPadrao + "adm/usuarios/{id}/editar"
      },
      {
        title: "Alterar senha",
        icon: "fa fa-lock",
        url: urlPadrao + "adm/usuarios/{id}/senha"
      },
      {
        title: "Remover",
        icon: "far fa-trash",
        url: urlPadrao + "adm/usuarios/{id}/apagar",
        confirm: true,
        confirmMessage: "Deseja excluir este usuario?",
        warning: true
      }
    ])
    .start();
})();
