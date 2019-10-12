$(document).ready(function () {

    $.ajax({
        url: "https://localhost:44371/api/autenticacao",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        timeout: 20000,
        beforeSend: function (xhr) {
            xhr.withCredentials = true;
            xhr.crossDomain = true;
            $('#msgLogin').text('Iniciando..');
        },
        statusCode: {
            200: function () {
                window.location.replace("/loja/todas");
            },
            202: function () {
                $('#msgLogin').text('Existe uma sessão aberta, gostaria de continuar?');
            },
            401: function () {
                $('#msgLogin').text('Usuário ou senha inválido');
            }
        }
    });

});