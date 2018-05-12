using System;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Imaging;
using System.Windows.Forms;

namespace Gray2RGB {
    public class ImageProcessing {

        public enum GRAY_MODE {
            Lightness = 0,
            Luminosity = 1,
            Average = 2
        };

        public enum RECOVERY_MODE {
            Easy = 0,
            Difference = 1,
            NearestDistance = 2 };

        private Bitmap grayMap, exampleMap, exampleGrayMap, resultMap; // save the image information
        public GRAY_MODE gmode; // what mode is the way that we change color to gray
        public RECOVERY_MODE rmode; // what mode should we use
        public bool colorBalance; // Do we need auto color balance at final

        // ========== Initialize ==========
        public ImageProcessing() {
            grayMap = exampleMap = exampleGrayMap = resultMap = null;
            gmode = GRAY_MODE.Lightness;
            rmode = RECOVERY_MODE.Easy;
            colorBalance = false;
        }

        // ========== Set & Get Values ==========
        public void setGrayMap(Bitmap grayMap) {
            Cursor.Current = Cursors.WaitCursor; // set cursor to wait

            Bitmap t;
            if (grayMap != null)
                t = color2gray_Average(grayMap);
            else
                t = null;
            this.grayMap = t;

            Cursor.Current = Cursors.Default; // set cursor to back
        }

        public Bitmap getGrayMap() {
            return this.grayMap;
        }

        public void setExampleMap(Bitmap exampleMap) {
            Cursor.Current = Cursors.WaitCursor; // set cursor to wait

            this.exampleMap = exampleMap;

            Cursor.Current = Cursors.Default; // set cursor to back
        }

        public Bitmap getExampleMap() {
            return this.exampleMap;
        }

        public Bitmap getResultMap() {
            Cursor.Current = Cursors.WaitCursor; // set cursor to wait

            // set example gray map
            if (exampleMap != null) {
                switch (gmode) {
                    case GRAY_MODE.Lightness:
                        exampleGrayMap = color2gray_Lightness(exampleMap);
                        break;
                    case GRAY_MODE.Luminosity:
                        exampleGrayMap = color2gray_Luminosity(exampleMap);
                        break;
                    case GRAY_MODE.Average:
                        exampleGrayMap = color2gray_Average(exampleMap);
                        break;
                }
            }

            // make color table
            Color[] table = new Color[256];
            for (int y = 0; y < exampleMap.Height; y++)
                for (int x = 0; x < exampleMap.Width; x++) {
                    table[exampleGrayMap.GetPixel(x, y).R] = exampleMap.GetPixel(x, y);
                }

            // progressing result
            switch (rmode) {
                case RECOVERY_MODE.Easy:
                    this.resultMap = gray2color_easy(this.grayMap, table);
                    break;
                case RECOVERY_MODE.Difference:
                    this.resultMap = gray2color_difference(this.grayMap, table);
                    break;
                case RECOVERY_MODE.NearestDistance:
                    this.resultMap = gray2color_nearestDistance(this.grayMap, this.exampleGrayMap, this.exampleMap, table);
                    break;
            }

            // color balance
            if (colorBalance)
                this.resultMap = autoColorBalance(this.resultMap);

            Cursor.Current = Cursors.Default; // set cursor to back
            return this.resultMap;
        }

        // ========== Functions ==========
        // To judge it is have enough image to Converte it
        public bool canConverte() {
            return grayMap != null && exampleMap != null;
        }

        // use for change a color image to gray image
        private static Bitmap color2gray_Lightness(Bitmap colorImage) {
            //create a blank bitmap the same size as colorImage
            Bitmap newBitmap = new Bitmap(colorImage.Width, colorImage.Height);

            // get and set values we need
            for (int y = 0; y < colorImage.Height; y++)
                for (int x = 0; x < colorImage.Width; x++) {
                    int r = colorImage.GetPixel(x, y).R;
                    int g = colorImage.GetPixel(x, y).G;
                    int b = colorImage.GetPixel(x, y).B;
                    int gray = (MAX43(r, g, b) + MIN43(r, g, b)) / 2;
                    newBitmap.SetPixel(x, y, Color.FromArgb(gray, gray, gray));
                }

            //return
            return newBitmap;
        }

        // find the value is MAX
        private static int MAX43(int a,int b,int c) {
            int r = a;
            if (r < b) r = b;
            if (r < c) r = c;
            return r;
        }

        // find the value is min
        private static int MIN43(int a, int b, int c) {
            int r = a;
            if (r > b) r = b;
            if (r > c) r = c;
            return r;
        }

        // use for change a color image to gray image
        private static Bitmap color2gray_Luminosity(Bitmap colorImage) {
            //create a blank bitmap the same size as colorImage
            Bitmap newBitmap = new Bitmap(colorImage.Width, colorImage.Height);

            //get a graphics object from the new image
            Graphics g = Graphics.FromImage(newBitmap);

            //create the grayscale ColorMatrix
            ColorMatrix colorMatrix = new ColorMatrix(
                new float[][]{
                    new float[] {0.21f,   0.21f, 0.21f, 0, 0},
                    new float[] {0.72f,   0.72f, 0.72f, 0, 0},
                    new float[] {0.07f,   0.07f, 0.07f, 0, 0},
                    new float[] {0,        0,      0,   1, 0},
                    new float[] {0,        0,      0,   0, 1}});

            //create some image attributes
            ImageAttributes attributes = new ImageAttributes();

            //set the color matrix attribute
            attributes.SetColorMatrix(colorMatrix);

            //draw the colorImage image on the new image
            //using the grayscale color matrix
            g.DrawImage(colorImage, new Rectangle(0, 0, colorImage.Width, colorImage.Height),
               0, 0, colorImage.Width, colorImage.Height, GraphicsUnit.Pixel, attributes);

            //dispose the Graphics object
            g.Dispose();
            return newBitmap;
        }

        // use for change a color image to gray image
        private static Bitmap color2gray_Average(Bitmap colorImage) {
            //create a blank bitmap the same size as colorImage
            Bitmap newBitmap = new Bitmap(colorImage.Width, colorImage.Height);

            //get a graphics object from the new image
            Graphics g = Graphics.FromImage(newBitmap);

            //create the grayscale ColorMatrix
            ColorMatrix colorMatrix = new ColorMatrix(
                new float[][]{
                    new float[] { 1f / 3f, 1f / 3f, 1f / 3f,    0, 0},
                    new float[] { 1f / 3f, 1f / 3f, 1f / 3f,    0, 0},
                    new float[] { 1f / 3f, 1f / 3f, 1f / 3f,    0, 0},
                    new float[] {0,        0,       0,          1, 0},
                    new float[] {0,        0,       0,          0, 1} });

            //create some image attributes
            ImageAttributes attributes = new ImageAttributes();

            //set the color matrix attribute
            attributes.SetColorMatrix(colorMatrix);

            //draw the colorImage image on the new image
            //using the grayscale color matrix
            g.DrawImage(colorImage, new Rectangle(0, 0, colorImage.Width, colorImage.Height),
               0, 0, colorImage.Width, colorImage.Height, GraphicsUnit.Pixel, attributes);

            //dispose the Graphics object
            g.Dispose();
            return newBitmap;
        }

        // use for change a gray image to color image (c(n))
        private static Bitmap gray2color_easy(Bitmap grayImage, Color[] colorTable) {
            //create a blank bitmap the same size as grayImage
            Bitmap newBitmap = new Bitmap(grayImage.Width, grayImage.Height);

            // change each pixle to color base on color table
            for (int y = 0; y < grayImage.Height; y++)
                for (int x = 0; x < grayImage.Width; x++) {
                    newBitmap.SetPixel(x, y, colorTable[findClosest(grayImage.GetPixel(x, y).R, colorTable)]);
                }

            // return
            return newBitmap;
        }

        // use for change a gray image to color image (c(n))
        private static Bitmap gray2color_difference(Bitmap grayImage, Color[] colorTable) {
            //create a blank bitmap the same size as grayImage
            Bitmap newBitmap = new Bitmap(grayImage.Width, grayImage.Height);

            // change each pixle to color base on color table
            for (int y = 0; y < grayImage.Height; y++)
                for (int x = 0; x < grayImage.Width; x++) {
                    // find the difference
                    int gray = grayImage.GetPixel(x, y).R;
                    int closest = findClosest(gray, colorTable);
                    int dif = closest - gray;
                    // get fixed rgb value
                    int r = colorTable[closest].R - dif;
                    int g = colorTable[closest].G - dif;
                    int b = colorTable[closest].B - dif;
                    // fixed it to avilible value
                    r = Math.Max(r, 0);
                    r = Math.Min(r, 255);
                    g = Math.Max(g, 0);
                    g = Math.Min(g, 255);
                    b = Math.Max(b, 0);
                    b = Math.Min(b, 255);
                    // set color
                    Color p = Color.FromArgb(r, g, b);
                    newBitmap.SetPixel(x, y, p);
                }

            // return
            return newBitmap;
        }

        // use for change a gray image to color image (c(n^2))
        private static Bitmap gray2color_nearestDistance(Bitmap grayImage, Bitmap exampleGray, Bitmap exampleColor, Color[] colorTable) {
            //create a blank bitmap the same size as grayImage
            Bitmap newBitmap = new Bitmap(grayImage.Width, grayImage.Height);

            // change each pixle to color base on color table
            for (int y = 0; y < grayImage.Height; y++) {
                for (int x = 0; x < grayImage.Width; x++) {
                    // find the difference
                    int gray = grayImage.GetPixel(x, y).R;
                    int closest = findClosest(gray, colorTable);
                    int dif = closest - gray;
                    // find the nearest point
                    int ox = x * exampleGray.Width / grayImage.Width,
                        oy = y * exampleGray.Height / grayImage.Height;
                    int nx = x, ny = y;
                    int xs = 0, xe = exampleGray.Width, ys = 0, ye = exampleGray.Height;
                    int d = xe * xe + ye * ye; // set d to bigest
                    for (int yy = ys; yy < ye; yy++) {
                        int dy = (yy - oy) * (yy - oy);
                        for (int xx = xs; xx < xe; xx++) {
                            if (exampleGray.GetPixel(xx, yy).R == closest) {// find color
                                int dx = (xx - ox) * (xx - ox);
                                if (dx + dy < d) {// Distance is less than now
                                    d = dx + dy;
                                    nx = xx;
                                    ny = yy;
                                    // Narrow the search
                                    int area = quickSqrt(d);
                                    // if (oy - area > ys) ys = oy - area; // cannot to be used
                                    if (oy + area < ye) ye = oy + area;
                                    if (ox - area > xs) xs = ox - area;
                                    if (ox + area < xe) xe = ox + area;
                                }
                            }
                        }
                    }
                    // get fixed rgb value
                    int r = exampleColor.GetPixel(nx, ny).R - dif;
                    int g = exampleColor.GetPixel(nx, ny).G - dif;
                    int b = exampleColor.GetPixel(nx, ny).B - dif;
                    // fixed it to avilible value
                    r = Math.Max(r, 0);
                    r = Math.Min(r, 255);
                    g = Math.Max(g, 0);
                    g = Math.Min(g, 255);
                    b = Math.Max(b, 0);
                    b = Math.Min(b, 255);
                    // set color
                    Color p = Color.FromArgb(r, g, b);
                    newBitmap.SetPixel(x, y, p);
                }
                Debug.WriteLine(y);
            }

            // return
            return newBitmap;
        }

        // use for auto balancing colors
        private static Bitmap autoColorBalance(Bitmap image) {
            // copy the color value from image
            int size = image.Width * image.Height;
            int[] redset = new int[size];
            int[] greenset = new int[size];
            int[] blueset = new int[size];

            int i = 0;
            for (int y = 0; y < image.Height; y++)
                for (int x = 0; x < image.Width; x++) {
                    Color c = image.GetPixel(x, y);
                    redset[i] = c.R;
                    greenset[i] = c.G;
                    blueset[i] = c.B;
                    i++;
                }

            // Sort the pixel values
            Array.Sort(redset);
            Array.Sort(greenset);
            Array.Sort(blueset);

            // Pick the quantiles from the sorted pixels
            float half_percent = 1f / 200.0f;
            int mini = (int)Math.Floor((size - 1) * half_percent);
            int maxi = (int)Math.Ceiling((size - 1) * (1 - half_percent));
            int bottomR = redset[mini],
                bottomG = greenset[mini],
                bottomB = blueset[mini];
            int topR = redset[maxi],
                topG = greenset[maxi],
                topB = blueset[maxi];
            

            // Affine transform
            Bitmap result = new Bitmap(image.Width, image.Height);
            for (int y = 0; y < image.Height; y++)
                for (int x = 0; x < image.Width; x++) {
                    // get pixles
                    Color c = image.GetPixel(x, y);
                    int r = c.R,
                        g = c.G,
                        b = c.B;
                    // Saturate the pixels
                    r = Math.Max(r, bottomR);
                    g = Math.Max(g, bottomG);
                    b = Math.Max(b, bottomB);
                    r = Math.Min(r, topR);
                    g = Math.Min(g, topG);
                    b = Math.Min(b, topB);
                    // transform
                    r = (r - bottomR) * 255 / (topR - bottomR);
                    g = (g - bottomG) * 255 / (topG - bottomG);
                    b = (b - bottomB) * 255 / (topB - bottomB);
                    // make color back to bitmap
                    result.SetPixel(x, y, Color.FromArgb(r, g, b));
                }
            // return
            return result;
        }

        // to find closest gray color
        private static int findClosest(int gray, Color[] table) {
            int up = findUpClosest(gray, table);
            int down = findDownClosest(gray, table);

            if (up >= 256) return down;
            else if (down < 0) return up;
            else if (up - gray < down - gray) return up;
            else return down;
        }

        // to find up closest gray color
        private static int findUpClosest(int gray, Color[] table) {
            if (gray < 256 && table[gray].IsEmpty)
                return findUpClosest(++gray, table);
            else
                return gray;
        }

        // to find down closest gray color
        private static int findDownClosest(int gray, Color[] table) {
            if (gray >= 0 && table[gray].IsEmpty)
                return findDownClosest(--gray, table);
            else
                return gray;
        }

        // quick sqrt
        private static int quickSqrt(int a,int from=0) {
            if (from * from > a) return --from;
            else return quickSqrt(a, ++from);
        }

        /*// Quick sort
        private static int[] quickSort(int[] set) {
            int l = set.Length;
            if (l <= 1) {
                return set;
            } else {
                // sort it
                int mid = set[0];
                int a = 1, b = l - 1;
                while (a <= b) {
                    if (set[a] <= mid) a++;
                    else if (set[b] > mid) b--;
                    else {
                        int e = set[a];
                        set[a] = set[b];
                        set[b] = e;
                    }
                }
                set[0] = set[b];
                set[b] = mid;
                // split array
                int[] aset = new int[b + 1];
                int[] bset = new int[l - b + 1];
                for(int i=0;i< l; i++) {
                    if (i < b + 1) aset[i] = set[i];
                    else bset[i - b + 1] = set[i];
                }
                // sort cell array
                aset = quickSort(aset);
                bset = quickSort(bset);
                // merge cell array
                int[] result = new int[l];
                for (int i = 0; i < l; i++) {
                    if (i < aset.Length) result[i] = aset[i];
                    else result[i] = bset[i - aset.Length];
                }
                // return
                return result;
            }
        }*/
    }
}
