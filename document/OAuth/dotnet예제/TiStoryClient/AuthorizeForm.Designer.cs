namespace TistoryClient
{
    partial class AuthorizeForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.wbTistory = new System.Windows.Forms.WebBrowser();
            this.SuspendLayout();
            // 
            // wbTistory
            // 
            this.wbTistory.Dock = System.Windows.Forms.DockStyle.Fill;
            this.wbTistory.Location = new System.Drawing.Point(0, 0);
            this.wbTistory.MinimumSize = new System.Drawing.Size(20, 20);
            this.wbTistory.Name = "wbTistory";
            this.wbTistory.Size = new System.Drawing.Size(721, 453);
            this.wbTistory.TabIndex = 0;
            this.wbTistory.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.wbTistory_DocumentCompleted);
            this.wbTistory.Navigated += new System.Windows.Forms.WebBrowserNavigatedEventHandler(this.wbTistory_Navigated);
            this.wbTistory.Navigating += new System.Windows.Forms.WebBrowserNavigatingEventHandler(this.wbTistory_Navigating);
            // 
            // AuthorizeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(721, 453);
            this.ControlBox = false;
            this.Controls.Add(this.wbTistory);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "AuthorizeForm";
            this.ShowIcon = false;
            this.ShowInTaskbar = false;
            this.Text = "Authorize!";
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.WebBrowser wbTistory;

    }
}