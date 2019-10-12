using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using BestStoreModel.Model;
using MongoDB.Driver;

namespace BestStoreApi.Dao
{
    public class UsuarioDAO : BaseMongoDAO
    {
        public UsuarioDAO(IMongoClient client) : base(client)
        {
        }

        public void Novo(Usuario usuario)
        {
            IMongoDatabase database = this.Client.GetDatabase("BestStore");
            database.GetCollection<Usuario>("Usuario").InsertOne(usuario);
        }

        public async Task<Usuario> GetUserByEmail(string email)
        {
            IMongoDatabase database = this.Client.GetDatabase("BestStore");
            var user = await database.GetCollection<Usuario>("Usuario")
                  .FindAsync<Usuario>(x => x.Email == email);

            return user.FirstOrDefault();

        }

        public bool Existe(Usuario u)
        {
            IMongoDatabase database = this.Client.GetDatabase("BestStore");
            return database.
                GetCollection<Usuario>("Usuario").
                Find(x => x.Email == u.Email).Any();

        }

        public void Atualizar(Usuario usuario)
        {

            IMongoDatabase database = this.Client.GetDatabase("BestStore");
            database
                .GetCollection<Usuario>("Usuario")
                .ReplaceOne<Usuario>(u => u.Senha.Equals(usuario.Senha), usuario);
        }
    }
}
