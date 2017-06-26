using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.IO;
using JsonExSerializer;

namespace TwitterClient
{
    public partial class Form1 : Form
    {
        private AccessToken token = null;

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //Get Access Token
            string url = "https://api.twitter.com/oauth2/token";
            string param = "grant_type=client_credentials";

            //블로그 정보 획득
            WebClient client = new WebClient();
            client.Headers.Add(HttpRequestHeader.ContentType, "application/x-www-form-urlencoded; charset=utf-8");
            client.Headers.Add("Authorization", "Basic " + Consumer.GetBearerTokenCredential());
            string result = client.UploadString(url, param);

            Serializer ser = new Serializer(typeof(AccessToken));
            token = (AccessToken)ser.Deserialize(result);
            richTextBox1.Text = token.access_token;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            string url = "https://api.twitter.com/1.1/statuses/user_timeline.json?count=100&screen_name=stepanowon";
            WebClient client = new WebClient();
            client.Headers.Add("Authorization", "Bearer " + token.access_token);
            client.Encoding = Encoding.UTF8;
            StringWriter sw = new StringWriter();
            Stream stream = client.OpenRead(url);
            StreamReader sr = new StreamReader(stream, Encoding.UTF8);
            String result = sr.ReadToEnd();

            richTextBox1.Text = result;
        }
    }
}
