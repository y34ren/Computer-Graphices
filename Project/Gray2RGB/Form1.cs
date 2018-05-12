using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Imaging;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gray2RGB {
    public partial class Form1 : Form {
        ImageProcessing imagePro;

        // ========== Initialize ==========
        public Form1() {
            imagePro = new ImageProcessing();
            InitializeComponent();
            ResultConverting.Enabled = imagePro.canConverte(); // change Result Converting enable
            GrayMode.SelectedIndex = 0; // reset gray mode
            RecoveryMode.SelectedIndex = 0; // reset Recovery Mode
            ColorBalance.Checked = false; // reset color balance button
        }

        // ========== Button Function ==========
        // change Recovery Mode
        private void RecoveryMode_SelectedIndexChanged(object sender, EventArgs e) {
            imagePro.rmode = (ImageProcessing.RECOVERY_MODE)RecoveryMode.SelectedIndex;
        }

        // change gray mode
        private void GrayMode_SelectedIndexChanged(object sender, EventArgs e) {
            imagePro.gmode = (ImageProcessing.GRAY_MODE)GrayMode.SelectedIndex;
        }

        // check if it use color balance
        private void ColorBalance_Click(object sender, EventArgs e) {
            imagePro.colorBalance = ColorBalance.Checked;
        }

        // open gray image
        private void GrayscaleImageOpen_Click(object sender, EventArgs e) {
            imagePro.setGrayMap(openImage());
            GrayscaleImage.Image = imagePro.getGrayMap();
            ResultConverting.Enabled = imagePro.canConverte(); // change Result Converting enable
        }

        // open example image
        private void ExampleImageOpen_Click(object sender, EventArgs e) {
            imagePro.setExampleMap(openImage());
            ExampleImage.Image = imagePro.getExampleMap();
            ResultConverting.Enabled = imagePro.canConverte(); // change Result Converting enable
        }

        // converting picture
        private void ResultConverting_Click(object sender, EventArgs e) {
            Bitmap r = imagePro.getResultMap();
            Result.Image = r;
            saveImage(r);
        }

        // open Image file to bitmap
        private static Bitmap openImage() {
            OpenFileDialog open = new OpenFileDialog();
            open.Filter = "Image Files(*.jpg; *.jpeg; *.gif; *.bmp; *.png)|*.jpg; *.jpeg; *.gif; *.bmp; *.png";
            if (open.ShowDialog() == DialogResult.OK)
                return new Bitmap(open.FileName);
            else return null;
        }

        // save Image to file
        private static void saveImage(Bitmap image) {
            SaveFileDialog save = new SaveFileDialog();
            save.Filter = "JPeg Image|*.jpg|Bitmap Image|*.bmp|PNG Image|*.png";
            if (save.ShowDialog() == DialogResult.OK) {
                switch (save.FilterIndex) {
                    case 1:
                        image.Save(save.FileName, ImageFormat.Jpeg);
                        break;
                    case 2:
                        image.Save(save.FileName, ImageFormat.Bmp);
                        break;
                    case 3:
                        image.Save(save.FileName, ImageFormat.Png);
                        break;
                }
            }
        }
    }
}
