using BestStoreApi.Dao;
using BestStoreModel.Api;
using BestStoreModel.Model;
using BestStoreModel.Request;
using Microsoft.AspNetCore.Mvc;

namespace BestStoreApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsuarioController : Controller
    {
        [HttpPost]
        public IActionResult OnPost([FromServices]UsuarioDAO ud, [FromBody]Usuario usuario)
        {
            IActionResult result;

            if (!usuario.IsValidToCreateNew())
                return BadRequest();
            try
            {
                if (ud.Existe(usuario))
                    return ApiResult.Response(202, "E-mail já cadastrado em outra conta");

                usuario.Senha.Criptografar();
                ud.Novo(usuario);

                result = ApiResult.Response(201, "Bem vindo!");

            }
            catch (System.Exception)
            {
                result = ApiResult.TenteMaisTarde;
            }
            return result;
        }

     


    }
}