using BestStoreApi.Dao;
using BestStoreApi.Session;
using BestStoreModel.Api;
using BestStoreModel.Model;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Caching.Distributed;
using System.Threading.Tasks;

namespace BestStoreApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LojaController : Controller
    {
        [HttpPost]
        public async Task<IActionResult> OnPostAsync([FromServices]LojaDAO ld, [FromBody]Loja loja)
        {
            if (!loja.IsValid())
                return ApiResult.Response(400, "Parametros inválidos");

            DistributedSessionTool dst = new DistributedSessionTool(this.HttpContext);

            await dst.OpenAsync();

            Usuario user = dst.GetUsuario();

            loja.UsuariosAdministradores.Add(new UsuarioLoja(user));
            loja.Detalhes.CriadoPor = user.Email;

            await ld.AdicionarNovaAsync(loja);

            return Json(new { isValid = true });
        }
    }
}