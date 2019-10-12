using MongoDB.Driver.GeoJsonObjectModel;

namespace BestStoreModel.Model
{
    public class Endereco
    {
        private string logradouro;
        private int numero;
        private string complemento;
        private GeoJsonPoint<GeoJson2DGeographicCoordinates> coordenadas;
        
        public Endereco() {
            this.numero = -1;
            this.coordenadas = new GeoJsonPoint<GeoJson2DGeographicCoordinates>(new GeoJson2DGeographicCoordinates(0, 0));
        }

        public string Logradouro { get => logradouro; set => logradouro = value; }
        public int Numero { get => numero; set => numero = value; }
        public string Complemento { get => complemento; set => complemento = value; }
        public GeoJsonPoint<GeoJson2DGeographicCoordinates> Coordenadas { get => coordenadas; set => coordenadas = value; }

        public bool IsValid()
        {
            return !string.IsNullOrEmpty(this.logradouro) &&
                this.numero != -1;//&&
                //coordenadas.Coordinates.Latitude != 0 &&
               // coordenadas.Coordinates.Longitude != 0;

        }
    }
}
