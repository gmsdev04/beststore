using BestStoreUtils.Utils;
using System;
using System.Collections.Generic;
using System.Text;

namespace BestStoreModel.Model
{
    public class Senha
    {
        private string key;
        private DateTime dataDefinicao;

        public Senha()
        {
            this.dataDefinicao = DateTime.Now;
        }

        public string Key { get => key; set => key = value; }
        public DateTime DataDefinicao { get => dataDefinicao; set => dataDefinicao = value; }

        public override bool Equals(object obj)
        {
            bool equals = false;

            if (obj is Senha)
            {
                Senha otherSenha = (Senha)obj;
                equals = otherSenha.Key == this.Key;
            }
            return equals;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        public void Criptografar()
        {
            this.Key = new MD5Converter().RetornarMD5(this.Key);
        }
    }


}