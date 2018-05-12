namespace Gray2RGB {
    partial class Form1 {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.splitContainer2 = new System.Windows.Forms.SplitContainer();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.GrayscaleImage = new System.Windows.Forms.PictureBox();
            this.GrayscaleImageOpen = new System.Windows.Forms.Button();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.ExampleImage = new System.Windows.Forms.PictureBox();
            this.ExampleImageOpen = new System.Windows.Forms.Button();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.Result = new System.Windows.Forms.PictureBox();
            this.ResultConverting = new System.Windows.Forms.Button();
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.toolStripLabel2 = new System.Windows.Forms.ToolStripLabel();
            this.GrayMode = new System.Windows.Forms.ToolStripComboBox();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripLabel1 = new System.Windows.Forms.ToolStripLabel();
            this.RecoveryMode = new System.Windows.Forms.ToolStripComboBox();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.ColorBalance = new System.Windows.Forms.ToolStripButton();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer2)).BeginInit();
            this.splitContainer2.Panel1.SuspendLayout();
            this.splitContainer2.Panel2.SuspendLayout();
            this.splitContainer2.SuspendLayout();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.GrayscaleImage)).BeginInit();
            this.groupBox2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ExampleImage)).BeginInit();
            this.groupBox3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Result)).BeginInit();
            this.toolStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 25);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.splitContainer2);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.groupBox3);
            this.splitContainer1.Size = new System.Drawing.Size(893, 313);
            this.splitContainer1.SplitterDistance = 592;
            this.splitContainer1.TabIndex = 0;
            // 
            // splitContainer2
            // 
            this.splitContainer2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.splitContainer2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer2.Location = new System.Drawing.Point(0, 0);
            this.splitContainer2.Name = "splitContainer2";
            // 
            // splitContainer2.Panel1
            // 
            this.splitContainer2.Panel1.Controls.Add(this.groupBox1);
            // 
            // splitContainer2.Panel2
            // 
            this.splitContainer2.Panel2.Controls.Add(this.groupBox2);
            this.splitContainer2.Size = new System.Drawing.Size(588, 309);
            this.splitContainer2.SplitterDistance = 285;
            this.splitContainer2.TabIndex = 0;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.GrayscaleImage);
            this.groupBox1.Controls.Add(this.GrayscaleImageOpen);
            this.groupBox1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBox1.Location = new System.Drawing.Point(0, 0);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(283, 307);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Grayscale Image";
            // 
            // GrayscaleImage
            // 
            this.GrayscaleImage.Dock = System.Windows.Forms.DockStyle.Fill;
            this.GrayscaleImage.Location = new System.Drawing.Point(3, 17);
            this.GrayscaleImage.Name = "GrayscaleImage";
            this.GrayscaleImage.Size = new System.Drawing.Size(277, 264);
            this.GrayscaleImage.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.GrayscaleImage.TabIndex = 1;
            this.GrayscaleImage.TabStop = false;
            // 
            // GrayscaleImageOpen
            // 
            this.GrayscaleImageOpen.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.GrayscaleImageOpen.Location = new System.Drawing.Point(3, 281);
            this.GrayscaleImageOpen.Name = "GrayscaleImageOpen";
            this.GrayscaleImageOpen.Size = new System.Drawing.Size(277, 23);
            this.GrayscaleImageOpen.TabIndex = 0;
            this.GrayscaleImageOpen.Text = "Open Grayscale Image";
            this.GrayscaleImageOpen.UseVisualStyleBackColor = true;
            this.GrayscaleImageOpen.Click += new System.EventHandler(this.GrayscaleImageOpen_Click);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.ExampleImage);
            this.groupBox2.Controls.Add(this.ExampleImageOpen);
            this.groupBox2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBox2.Location = new System.Drawing.Point(0, 0);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(297, 307);
            this.groupBox2.TabIndex = 0;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Example Color Image";
            // 
            // ExampleImage
            // 
            this.ExampleImage.Dock = System.Windows.Forms.DockStyle.Fill;
            this.ExampleImage.Location = new System.Drawing.Point(3, 17);
            this.ExampleImage.Name = "ExampleImage";
            this.ExampleImage.Size = new System.Drawing.Size(291, 264);
            this.ExampleImage.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.ExampleImage.TabIndex = 1;
            this.ExampleImage.TabStop = false;
            // 
            // ExampleImageOpen
            // 
            this.ExampleImageOpen.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.ExampleImageOpen.Location = new System.Drawing.Point(3, 281);
            this.ExampleImageOpen.Name = "ExampleImageOpen";
            this.ExampleImageOpen.Size = new System.Drawing.Size(291, 23);
            this.ExampleImageOpen.TabIndex = 0;
            this.ExampleImageOpen.Text = "Open Example Image";
            this.ExampleImageOpen.UseVisualStyleBackColor = true;
            this.ExampleImageOpen.Click += new System.EventHandler(this.ExampleImageOpen_Click);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.Result);
            this.groupBox3.Controls.Add(this.ResultConverting);
            this.groupBox3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBox3.Location = new System.Drawing.Point(0, 0);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(293, 309);
            this.groupBox3.TabIndex = 0;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Result";
            // 
            // Result
            // 
            this.Result.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Result.Location = new System.Drawing.Point(3, 17);
            this.Result.Name = "Result";
            this.Result.Size = new System.Drawing.Size(287, 266);
            this.Result.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.Result.TabIndex = 1;
            this.Result.TabStop = false;
            // 
            // ResultConverting
            // 
            this.ResultConverting.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.ResultConverting.Location = new System.Drawing.Point(3, 283);
            this.ResultConverting.Name = "ResultConverting";
            this.ResultConverting.Size = new System.Drawing.Size(287, 23);
            this.ResultConverting.TabIndex = 0;
            this.ResultConverting.Text = "Converting";
            this.ResultConverting.UseVisualStyleBackColor = true;
            this.ResultConverting.Click += new System.EventHandler(this.ResultConverting_Click);
            // 
            // toolStrip1
            // 
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripLabel2,
            this.GrayMode,
            this.toolStripSeparator1,
            this.toolStripLabel1,
            this.RecoveryMode,
            this.toolStripSeparator2,
            this.ColorBalance});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(893, 25);
            this.toolStrip1.TabIndex = 2;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // toolStripLabel2
            // 
            this.toolStripLabel2.Name = "toolStripLabel2";
            this.toolStripLabel2.Size = new System.Drawing.Size(65, 22);
            this.toolStripLabel2.Text = "Gray Mode";
            // 
            // GrayMode
            // 
            this.GrayMode.Items.AddRange(new object[] {
            "Lightness",
            "Luminosity",
            "Average"});
            this.GrayMode.Name = "GrayMode";
            this.GrayMode.Size = new System.Drawing.Size(121, 25);
            this.GrayMode.SelectedIndexChanged += new System.EventHandler(this.GrayMode_SelectedIndexChanged);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
            // 
            // toolStripLabel1
            // 
            this.toolStripLabel1.Name = "toolStripLabel1";
            this.toolStripLabel1.Size = new System.Drawing.Size(89, 22);
            this.toolStripLabel1.Text = "Recovery Mode";
            // 
            // RecoveryMode
            // 
            this.RecoveryMode.Items.AddRange(new object[] {
            "Easy",
            "Difference",
            "Nearest Distance"});
            this.RecoveryMode.Name = "RecoveryMode";
            this.RecoveryMode.Size = new System.Drawing.Size(121, 25);
            this.RecoveryMode.SelectedIndexChanged += new System.EventHandler(this.RecoveryMode_SelectedIndexChanged);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
            // 
            // ColorBalance
            // 
            this.ColorBalance.CheckOnClick = true;
            this.ColorBalance.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.ColorBalance.Image = ((System.Drawing.Image)(resources.GetObject("ColorBalance.Image")));
            this.ColorBalance.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ColorBalance.Name = "ColorBalance";
            this.ColorBalance.Size = new System.Drawing.Size(84, 22);
            this.ColorBalance.Text = "Color Balance";
            this.ColorBalance.Click += new System.EventHandler(this.ColorBalance_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(893, 338);
            this.Controls.Add(this.splitContainer1);
            this.Controls.Add(this.toolStrip1);
            this.Name = "Form1";
            this.Text = "Gray 2 RGB";
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.splitContainer2.Panel1.ResumeLayout(false);
            this.splitContainer2.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer2)).EndInit();
            this.splitContainer2.ResumeLayout(false);
            this.groupBox1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.GrayscaleImage)).EndInit();
            this.groupBox2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.ExampleImage)).EndInit();
            this.groupBox3.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.Result)).EndInit();
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.SplitContainer splitContainer2;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Button GrayscaleImageOpen;
        private System.Windows.Forms.Button ExampleImageOpen;
        private System.Windows.Forms.Button ResultConverting;
        private System.Windows.Forms.PictureBox GrayscaleImage;
        private System.Windows.Forms.PictureBox ExampleImage;
        private System.Windows.Forms.PictureBox Result;
        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.ToolStripLabel toolStripLabel1;
        private System.Windows.Forms.ToolStripComboBox RecoveryMode;
        private System.Windows.Forms.ToolStripLabel toolStripLabel2;
        private System.Windows.Forms.ToolStripComboBox GrayMode;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripButton ColorBalance;
    }
}

