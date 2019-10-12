using BestStoreUtils;
using Xunit;

namespace BestStoreTest.Utils
{
    public class EmailValidatorTest
    {
        [Fact]
        public void EmailSemArrobaTest()
        {
            string email = "guilhermesiqueirahotmail.com";
            bool isValid = EmailValidator.IsValid(email);

            Assert.False(isValid);
        }


        [Fact]
        public void EmailSemPontoAlgoNoFinal()
        {
            string email = "guilhermesiqueira@hotmail";
            bool isValid = EmailValidator.IsValid(email);

            Assert.False(isValid);

        }

        [Fact]
        public void EmailVazio()
        {
            bool isValid = EmailValidator.IsValid("");

            Assert.False(isValid);
        }

    }
}
