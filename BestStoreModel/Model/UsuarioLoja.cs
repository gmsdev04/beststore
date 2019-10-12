using BestStoreModel.Mongo;
using MongoDB.Bson;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;

namespace BestStoreModel.Model
{
    public class UsuarioLoja
    {
        public UsuarioLoja()
        {

        }

        public UsuarioLoja(Usuario user)
        {
            this.id = user.Id;
            this.email = user.Email;
        }

        [JsonConverter(typeof(ObjectIdConverter))]
        private ObjectId id;
        private string email;

        public ObjectId Id { get => id; set => id = value; }
        public string Email { get => email; set => email = value; }
    }
}
