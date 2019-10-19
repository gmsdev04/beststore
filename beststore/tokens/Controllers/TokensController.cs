using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace tokens.Controllers
{
    [Route("apis/sts/v1/[action]")]
    [ApiController]
    public class TokensController : ControllerBase
    {
        // GET api/values
        [HttpPost]
        public ActionResult<IEnumerable<string>> Tokens()
        {
            return new string[] {"Requested"};
        }
    }
}