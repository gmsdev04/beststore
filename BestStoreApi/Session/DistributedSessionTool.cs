using BestStoreModel.Model;
using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace BestStoreApi.Session
{
    public class DistributedSessionTool
    {
        private readonly HttpContext context;
        private bool isOpen;
        public DistributedSessionTool(HttpContext context)
        {
            this.context = context;
            this.isOpen = false; 
        }

        public async Task<bool> OpenAsync()
        {
            await this.context.Session.LoadAsync();
            this.isOpen = true;
            return true;
        }

        public T GetJson<T>(string key)
        {
            if (!this.isOpen)
                throw new Exception("Não é possivel obter uma chave sem realizar o load.");

            string value = this.context.Session.GetString(key);

            if (!string.IsNullOrEmpty(value))
            {
                try
                {
                    return JsonConvert.DeserializeObject<T>(value);
                }
                catch (Exception e)
                {
                    throw new Exception("Falha ao deserializar objeto.", e);
                }
            }

            return default(T);

        }

        public Usuario GetUsuario()
        {
            string key = $"ss:{this.context.Session.Id}";

            return this.GetJson<Usuario>(key);
        }

        public async Task<bool> CommitAsync()
        {
            await this.context.Session.CommitAsync();
            return true;
        }
        

    }
}
