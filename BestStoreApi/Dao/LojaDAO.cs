using System;
using System.Threading.Tasks;
using BestStoreModel.Model;
using MongoDB.Driver;

namespace BestStoreApi.Dao
{
    public class LojaDAO : BaseMongoDAO
    {
        public LojaDAO(IMongoClient client) : base(client)
        {

        }

        internal async Task<Loja> AdicionarNovaAsync(Loja loja)
        {
            IMongoDatabase database = this.Client.GetDatabase("BestStore");
            IMongoCollection<Loja> lojas = database.GetCollection<Loja>("Loja");

            await lojas.InsertOneAsync(loja);
            return loja;
        }
    }
}
