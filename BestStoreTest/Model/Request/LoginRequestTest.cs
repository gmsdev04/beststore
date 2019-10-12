using BestStoreModel.Request;
using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace BestStoreTest.Model.Request
{
    public class LoginRequestTest
    {
        [Fact]
        public void LoginRequestSemSenha()
        {
            LoginRequest lr = new LoginRequest() { Email = "gmsdev@xpto.com", Senha = string.Empty };
            bool isValid = lr.IsValid();

            Assert.False(isValid);
        }

        [Fact]
        public void LoginRequestSemEmail()
        {
            LoginRequest lr = new LoginRequest() { Email = string.Empty, Senha = "hello test" };
            bool isValid = lr.IsValid();

            Assert.False(isValid);
        }

    }
}
