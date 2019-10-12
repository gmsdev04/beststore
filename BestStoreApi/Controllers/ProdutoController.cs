using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using BestStoreApi.Dao;
using BestStoreModel.Model;
using Microsoft.AspNetCore.Mvc;

namespace BestStoreApi.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class ProdutoController : Controller
    {
        [HttpPost()]
        public IActionResult Novo(Produto produto,
                                  [FromServices]ProdutoDAO pd)
        {
            pd.CriarNovoProduto(produto);

            return Json("Sucesso");
        }

        [HttpGet]
        public IActionResult Todos([FromServices]ProdutoDAO pd)
        {
            IList<Produto> prods = pd.Todos();
            return Json(prods);
        }

        [HttpPost()]
        public IActionResult Deletar([FromServices]ProdutoDAO pd,
                                     [FromForm]string id)
        {
            pd.Deletar(id);
            return Json("Sucesso");
        }

        [HttpPost]
        public IActionResult Atualizar([FromServices]ProdutoDAO pd,
                                        Produto produto)
        {
            pd.Atualizar(produto);
            return Json("Sucesso");
        }

    }
}