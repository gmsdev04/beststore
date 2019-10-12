using BestStoreModel.Mongo;
using BestStoreUtils;
using BestStoreUtils.Utils;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace BestStoreModel.Model
{
    public class Usuario
    {

        #region Atributos

        private ObjectId id;
        private string email;
        private string nome;
        private string dataNascimento;
        private Senha senha;
        private bool status;
        private DateTime dataCriacao;
        private IList<string> lojasAdministradas;
        private DateTime ultimoLogin;


        #endregion

        #region Construtor


        public Usuario()
        {
            this.status = true;
            this.id = ObjectId.GenerateNewId();
            this.dataCriacao = DateTime.Now;
            this.lojasAdministradas = new List<string>();
        }

        #endregion

        #region Propriedades

        [JsonConverter(typeof(ObjectIdConverter))]
        public ObjectId Id { get => id; set => id = value; }
        public string Email { get => email; set => email = value; }
        public Senha Senha { get => senha; set => senha = value; }
        public bool Status { get => status; set => status = value; }
        public DateTime DataCriacao { get => dataCriacao; set => dataCriacao = value; }
        public IList<string> LojasAdministradas { get => lojasAdministradas; set => lojasAdministradas = value; }
        public string Nome { get => nome; set => nome = value; }
        public string DataNascimento { get => dataNascimento; set => dataNascimento = value; }
        public DateTime UltimoLogin { get => ultimoLogin; set => ultimoLogin = value; }

        #endregion

        #region Metodos
        public bool IsValidToCreateNew()
        {
            return (!string.IsNullOrEmpty(this.nome) &&
                     EmailValidator.IsValid(this.email) &&
                    !string.IsNullOrEmpty(this.senha.Key));
        }
        #endregion

    }
}
