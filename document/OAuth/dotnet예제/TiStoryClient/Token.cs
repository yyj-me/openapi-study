using System;
using System.Collections.Generic;
using System.Text;
using System.Collections.Specialized;

namespace TistoryClient
{
    public class Token
    {
        public String Access_Token { get; set; }
        public String Expires_In { get; set; }


        public static NameValueCollection getParams(string queryString)
        {
            NameValueCollection queryParameters = new NameValueCollection();
            string[] querySegments = queryString.Split('&');
            foreach (string segment in querySegments)
            {
                string[] parts = segment.Split('=');
                if (parts.Length > 0)
                {
                    string key = parts[0].Trim(new char[] { '?', ' ' });
                    string val = parts[1].Trim();

                    queryParameters.Add(key, val);
                }
            }

            return queryParameters;
        }
    }
}
