using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Text;

namespace BestStoreModel.Api
{
    public class ApiResult
    {

        public ApiResult()
        {

        }

        public static JsonResult TenteMaisTarde
        {
            get
            {
                return new JsonResult(new
                {
                    message = "Houve um erro, tente novamente mais tarde",
                    data = ""
                });
            }
        }

        public static JsonResult Response(int sc,string msg, dynamic dt = null)
        {
            return new JsonResult(new {message = msg, data = dt }) { StatusCode = sc};
        }



    }
}
