using BestStoreModel.Mongo;
using MongoDB.Bson;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace BestStoreModel.Model
{
    public class Loja
    {
        #region Atributos

        private ObjectId id;
        private string nome;
        private Endereco endereco;

        public bool IsValid()
        {
            return !string.IsNullOrEmpty(this.nome) &&
                    this.endereco.IsValid();

        }

        private IList<UsuarioLoja> usuariosAdministradores;
        private bool status;
        private Detalhes detalhes;
        #endregion

        #region Construtores

        public Loja(Usuario usuario)
        {
            this.status = true;
            this.detalhes = new Detalhes(usuario);
            this.usuariosAdministradores = new List<UsuarioLoja>() { new UsuarioLoja(usuario) };
        }

        public Loja() {
            this.status = true;
            this.detalhes = new Detalhes();
            this.endereco = new Endereco();
            this.usuariosAdministradores = new List<UsuarioLoja>();
        }
        #endregion

        #region Propriedades

        [JsonConverter(typeof(ObjectIdConverter))]
        public ObjectId Id { get => id; set => id = value; }
        public string Nome { get => nome; set => nome = value; }
        public Endereco Endereco { get => endereco; set => endereco = value; }
        public IList<UsuarioLoja> UsuariosAdministradores { get => usuariosAdministradores; set => usuariosAdministradores = value; }
        public bool Status { get => status; set => status = value; }
        public Detalhes Detalhes { get => detalhes; set => detalhes = value; }

        #endregion

        #region Metodos

     

        #endregion

    }
}
