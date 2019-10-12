using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BestStoreModel.Model
{
    public class Produto
    {
        private string id;
        private string nome;
        private decimal valor;
        private string descricao;

        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get => id; set => id = value; }
        public decimal Valor { get => valor; set => valor = value; }
        public string Nome { get => nome; set => nome = value; }
        public string Descricao { get => descricao; set => descricao = value; }
 

    }
}
