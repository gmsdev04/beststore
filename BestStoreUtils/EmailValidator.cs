namespace BestStoreUtils
{
    public class EmailValidator
    {

        public static bool IsValid(string email)
        {
            if (!string.IsNullOrEmpty(email) &&
                email.Contains("@") && 
                email.Substring(email.LastIndexOf("@")).Contains("."))
                return true;
            return false;
        }
    }
}
