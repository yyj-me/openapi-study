using System;
using System.Collections.Generic;
using System.Text;

namespace TistoryClient
{
    public class Settings
    {
        public static String CLIENT_ID = "dc3069fbdb17ec8433ef1707f67db977";
	    public static  String AUTHORIZE_URL="https://www.tistory.com/oauth/authorize";
	    public static  String ACCES_TOKEN_URL = "https://www.tistory.com/oauth/access_token";
        public static String REDIRECT_URI = "http://localhost:8000/tistory2/callback.html";
	    public static  String BLOG_INFO_URL = "https://www.tistory.com/apis/blog/info";
    }
}
