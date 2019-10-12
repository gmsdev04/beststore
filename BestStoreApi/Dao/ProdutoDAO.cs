using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using BestStoreModel.Model;
using MongoDB.Driver;

namespace BestStoreApi.Dao
{
    public class ProdutoDAO : BaseMongoDAO
    {
        public ProdutoDAO(IMongoClient client) : base(client)
        {

        }

        public void CriarNovoProduto(Produto produto)
        {
            IMongoDatabase database = this.Client.GetDatabase("BestStore");

            database.GetCollection<Produto>("Produto").InsertOne(produto);
        }

        public IList<Produto> Todos()
        {
           IMongoDatabase database = this.Client.GetDatabase("BestStore");

           return database.GetCollection<Produto>("Produto").Find(_ => true).ToList<Produto>();
        }

        public void Deletar(string id)
        {
            IMongoDatabase database = this.Client.GetDatabase("BestStore");
            database.GetCollection<Produto>("Produto").DeleteOne<Produto>(p => p.Id == id);
        }

        internal void Atualizar(Produto produto)
        {
            IMongoDatabase database = this.Client.GetDatabase("BestStore");

            database.GetCollection<Produto>("Produto").ReplaceOne(x => x.Id == produto.Id,produto);
        }
    }
}
