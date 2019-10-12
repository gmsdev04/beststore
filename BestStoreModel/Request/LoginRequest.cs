using BestStoreUtils;
using BestStoreUtils.Utils;
using System;

namespace BestStoreModel.Request
{
    public class LoginRequest
    {
        private readonly MD5Converter md5;

        public LoginRequest()
        {
            this.ManterLogado = true;
            this.md5 = new MD5Converter();
        }

        public string Email { get; set; }
        public string Senha { get; set; }
        public bool ManterLogado { get; set; }

        public string SenhaCriptografada
        {
            get
            {
                return  md5.RetornarMD5(this.Senha);
            }
        }

        public bool IsValid()
        {
            return !string.IsNullOrEmpty(this.Senha) &&
                   EmailValidator.IsValid(this.Email);
        }

    }
}
