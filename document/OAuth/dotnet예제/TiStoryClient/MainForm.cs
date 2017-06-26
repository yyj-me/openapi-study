using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.IO;

namespace TistoryClient
{
    public partial class Form1 : Form
    {
        public Token token = new Token();
        private AuthorizeForm af = new AuthorizeForm();

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            String url = string.Format(Settings.AUTHORIZE_URL + 
                "?client_id={0}&redirect_uri={1}&response_type=token", Settings.CLIENT_ID, Settings.REDIRECT_URI);
            
            af.Owner = this;
            this.Hide();
            af.wbTistory.ScriptErrorsSuppressed = true;
            af.wbTistory.Navigate(url);
            af.Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            String url = string.Format(Settings.BLOG_INFO_URL + "?access_token={0}", token.Access_Token); 
            //블로그 정보 획득
            WebClient client = new WebClient();
            client.Encoding = Encoding.UTF8;
            StringWriter sw = new StringWriter();
            Stream stream = client.OpenRead(url);
            StreamReader sr = new StreamReader(stream, Encoding.UTF8);
            String result = sr.ReadToEnd();

            richTextBox1.Text = result;
        }
    }
}
