using Microsoft.AspNetCore.Mvc.Filters;
using System;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace BestStoreApi.Filters
{
    public class AutenticacaoFilterAsync : IAsyncActionFilter
    {
        public async Task OnActionExecutionAsync(ActionExecutingContext context, ActionExecutionDelegate next)
        {

            if (context.HttpContext.Request.Path != "/api/autenticacao")
            {
                await context.HttpContext.Session.LoadAsync();

                string chaveCache = $"ss:{context.HttpContext.Session.Id}";

                bool isAuthenticated = context.HttpContext.Session.Keys.Contains(chaveCache);

                if (isAuthenticated)
                {
                    await next();
                }
                else
                {
                    context.HttpContext.Response.StatusCode = 401;
                }

            }
            else
            {
                await next();
            }

        }
    }
}
