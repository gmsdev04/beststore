using MongoDB.Driver;

namespace BestStoreApi.Dao
{
    public abstract class BaseMongoDAO
    {
        
        protected readonly IMongoClient Client;
        public BaseMongoDAO(IMongoClient client) {
            this.Client = client;
            
        }

    }
}
