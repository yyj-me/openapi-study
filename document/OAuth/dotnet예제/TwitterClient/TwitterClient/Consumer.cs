using System;
using System.Collections.Generic;
using System.Text;
using System.Web;

namespace TwitterClient
{
    public class Consumer
    {
        public static String ConsumerKey = "GpIZTUtip4oWN6UmE3eg";
        public static String ConsumerSecret = "qnGfMhNEDNhXN1G0Sih8p5UKkm7qaOoOHFna5dhIog";

        public static String GetBearerTokenCredential()
        {
            string encConsumerKey = HttpUtility.UrlEncode(ConsumerKey, Encoding.UTF8);
            string encConsumerSecret = HttpUtility.UrlEncode(ConsumerSecret, Encoding.UTF8);
            string bearerToken = Convert.ToBase64String(Encoding.UTF8.GetBytes(encConsumerKey + ":" + encConsumerSecret));
            return bearerToken;
        }
    }
}
