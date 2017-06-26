using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Collections.Specialized;

namespace TistoryClient
{
    public partial class AuthorizeForm : Form
    {
        public AuthorizeForm()
        {
            InitializeComponent();
            
        }

        private void wbTistory_Navigated(object sender, WebBrowserNavigatedEventArgs e)
        {

        }

        private void wbTistory_Navigating(object sender, WebBrowserNavigatingEventArgs e)
        {

        }

        private void wbTistory_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            string url = wbTistory.Url.ToString();
            if (url.StartsWith(Settings.REDIRECT_URI))
            {
                //wbTistory.Url = new Uri("about:Tabs");

                string queryString = url.Split('#')[1];
                NameValueCollection collection = Token.getParams(queryString);
                Form1 mainForm = (Form1)this.Owner;
                mainForm.token.Access_Token = collection["access_token"];
                //mainForm.token.Expires_In = collection["expires_in"];
                mainForm.Text = mainForm.token.Access_Token;
                mainForm.Show();

                this.Hide();
                //this.Close();
                //this.Dispose();
            }
        }

    }
}
