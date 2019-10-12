using System;

namespace BestStoreModel.Model
{
    public class Detalhes
    {
        #region Atributos

        private DateTime dataCriacao;
        private DateTime dataUltimaAlteracao;
        private string criadoPor;
        private string ultimoAlterador;

        #endregion

        #region Construtor
        public Detalhes(Usuario usuario)
        {
            this.dataCriacao = DateTime.Now;
            this.criadoPor = usuario.Email;
        }

        public Detalhes() {
            this.dataCriacao = DateTime.Now;
        }
        #endregion

        #region Propriedades

        public DateTime DataCriacao { get => dataCriacao; set => dataCriacao = value; }
        public DateTime DataUltimaAlteracao { get => dataUltimaAlteracao; set => dataUltimaAlteracao = value; }
        public string CriadoPor { get => criadoPor; set => criadoPor = value; }
        public string UltimoAlterador { get => ultimoAlterador; set => ultimoAlterador = value; }

        #endregion

    }
}
