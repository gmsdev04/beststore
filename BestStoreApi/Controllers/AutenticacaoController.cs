using BestStoreApi.Dao;
using BestStoreModel.Api;
using BestStoreModel.Model;
using BestStoreModel.Request;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Caching.Distributed;
using Newtonsoft.Json;
using System.Security.Claims;
using System.Threading.Tasks;

namespace BestStoreApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AutenticacaoController : Controller
    {
        [HttpPost]
        public async Task<IActionResult> OnPostAsync([FromServices]UsuarioDAO ud,
                                 [FromBody] LoginRequest lr)
        {
            if (!lr.IsValid())
                return BadRequest();

            JsonResult result = ApiResult.Response(401, "Usuário ou senha inválido");

            await HttpContext.Session.LoadAsync();

            string chaveCache = $"ss:{HttpContext.Session.Id}";

            if (HttpContext.Session.GetString(chaveCache) != null)
                return ApiResult.Response(202, "Você já está logado");

            try
            {
                Usuario dbUser = await ud.GetUserByEmail(lr.Email);

                if (dbUser != null)
                {
                    bool canAcess = dbUser != null &&
                         dbUser.Senha.Key == lr.SenhaCriptografada;

                    if (canAcess)
                    {
                        HttpContext.Session.SetString(chaveCache, JsonConvert.SerializeObject(dbUser));
                        await HttpContext.Session.CommitAsync();

                        result = ApiResult.Response(200, "Logado com sucesso");
                    }
                }

            }
            catch
            {
                result = ApiResult.TenteMaisTarde;
            }

            return result;
        }
    }
}