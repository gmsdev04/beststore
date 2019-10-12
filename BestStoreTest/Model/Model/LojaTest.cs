using BestStoreModel.Model;
using MongoDB.Driver.GeoJsonObjectModel;
using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace BestStoreTest.Model.Model
{
    public class LojaTest
    {
        [Fact]
        public void LojaSemNomeTest()
        {
            Loja loja = new Loja();

            bool isValid = loja.IsValid();

            Assert.False(isValid);

        }

        [Fact]
        public void LojaSemEnderecoTest()
        {
            Loja loja = new Loja() { Nome = "MinhaLoja" };

            bool isValid = loja.IsValid();

            Assert.False(isValid);
        }

        [Fact]
        public void LojaComEnderecoTest()
        {
            Loja loja = new Loja()
            {
                Nome = "Minha loja",
                Endereco = new Endereco()
                {
                    Numero = 436,
                    Complemento = "Casa 2",
                    Logradouro = "Rua campo da vinha",
                    Coordenadas = new GeoJsonPoint<GeoJson2DGeographicCoordinates>(new GeoJson2DGeographicCoordinates(1, 1))
                }
            };

            bool isValid = loja.IsValid();

            Assert.True(isValid);

        }
    }
}
