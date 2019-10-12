using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace BestStore.Controllers
{
    public class LojaController : Controller
    {
        [HttpGet]
        public IActionResult Todas()
        {
            return View();
        }
    }
}