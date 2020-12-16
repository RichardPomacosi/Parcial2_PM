using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing.Imaging;

using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Textura
{
    public partial class Form1 : Form
    {
        int cR, cG, cB;
        //Media de los puntos
        int cmR, cmG, cmB;

        int[,] m = new int[20, 20];
        string[] v = new string[30];

        public Form1()
        {
            InitializeComponent();
            v[0] = "Nieve"; v[1] = "Lago"; v[2] = "Vegetacion"; v[3] = "tierra";

            //RGB de detecion                                   //RGB colo Para la deteccion
     /*Nieve*/      m[0, 0] = 253; m[0, 1] = 253; m[0, 2] = 253;        m[0, 3] = 105; m[0, 4] = 120; m[0, 5] = 115;
     /*Lago*/       m[1, 0] = 1; m[1, 1] = 7; m[1, 2] = 15;             m[1, 3] = 46; m[1, 4] = 247; m[1, 5] = 247;
    /*Vegetacion*/  m[2, 0] = 104; m[2, 1] = 105; m[2, 2] = 55;         m[2, 3] = 87; m[2, 4] = 246; m[2, 5] = 23;
    /*Tierra*/      m[3, 0] = 180; m[3, 1] = 149; m[3, 2] = 89;         m[3, 3] = 141; m[3, 4] = 106; m[3, 5] = 26;

        }



        private void button1_Click(object sender, EventArgs e)
        {
            openFileDialog1.Filter = "Imagenes JPG|*.jpg";
            openFileDialog1.ShowDialog();
            Bitmap bmp = new Bitmap(openFileDialog1.FileName);
            pictureBox1.Image = bmp;

           

         

        }

      

        private void pictureBox1_MouseClick(object sender, MouseEventArgs e)
        {
            Bitmap bmp = new Bitmap(pictureBox1.Image);
            Color c = new Color();
            c = bmp.GetPixel(e.X, e.Y);
            cR = c.R;
            cG = c.G;
            cB = c.B;
            textBox1.Text = c.R.ToString();
            textBox2.Text = c.G.ToString();
            textBox3.Text = c.B.ToString();
            cmR = 0;
            cmG = 0;
            cmB = 0;
            for (int i = e.X; i < e.X + 5; i++)
                for (int j = e.Y; j < e.Y + 5; j++)
                {
                    c = bmp.GetPixel(i, j);
                    cmR = cmR + c.R;
                    cmG = cmG + c.G;
                    cmB = cmB + c.B;
                }
            cmR = cmR / 25;
            cmG = cmG / 25;
            cmB = cmB / 25;
            textBox1.Text = cmR.ToString();
            textBox2.Text = cmG.ToString();
            textBox3.Text = cmB.ToString();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Bitmap bmp = new Bitmap(pictureBox1.Image);
            Bitmap bmp2 = new Bitmap(bmp.Width, bmp.Height);
            Color c = new Color();
            for (int i = 0; i < bmp.Width; i++)
            {
                for (int j = 0; j < bmp.Height; j++)
                {
                    c = bmp.GetPixel(i, j);
                    bmp2.SetPixel(i, j, Color.FromArgb(0, 0, c.B));

                }

            }
            pictureBox1.Image = bmp2;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Bitmap bmp = new Bitmap(pictureBox1.Image);
            Bitmap bmp2 = new Bitmap(bmp.Width, bmp.Height);
            Color c = new Color();
           
            int ciR, ciG, ciB;
            for (int i = 0; i < bmp.Width; i++)
            {
                for (int j = 0; j < bmp.Height; j++)
                {
                    c = bmp.GetPixel(i, j);
                    ciR = c.R;
                    ciG = c.G;
                    ciB = c.B;

                    if ((cR - 10 < ciR) && (ciR < cR + 10) && (cG - 10 < ciG) && (ciG < cG + 10) && (cB - 10 < ciB) && (ciB < cB + 10))
                    {
                        bmp2.SetPixel(i, j, Color.Red);
                    }
                    else
                    {
                        bmp2.SetPixel(i, j, Color.FromArgb(c.R, c.G, c.B));
                    }


                }

            }
            pictureBox1.Image = bmp2;
        }

        private void button4_Click(object sender, EventArgs e)
        {
            Bitmap bmp = new Bitmap(pictureBox1.Image);
            Bitmap bmp2 = new Bitmap(bmp.Width, bmp.Height);
            Color c = new Color();
            int ciR, ciG, ciB;
            for (int i = 0; i < bmp.Width - 5; i = i + 5)
                for (int j = 0; j < bmp.Height - 5; j = j + 5)
                {
                    ciR = 0;
                    ciG = 0;
                    ciB = 0;
                    for (int x = i; x < i + 5; x++)
                        for (int y = j; y < j + 5; y++)
                        {
                            c = bmp.GetPixel(x, y);
                            ciR = ciR + c.R;
                            ciG = ciG + c.G;
                            ciB = ciB + c.B;
                        }
                    ciR = ciR / 25;
                    ciG = ciG / 25;
                    ciB = ciB / 25;

                    if (((cmR - 10 < ciR) && (ciR < cmR + 10)) && ((cmG - 10 < ciG) && (ciG < cmG + 10)) && ((cmB - 10 < ciB) && (ciB < cmB + 10)))
                    {

                        for (int x = i; x < i + 5; x++)
                            for (int y = j; y < j + 5; y++)
                            {
                                bmp2.SetPixel(x, y, Color.Red);
                            }
                    }
                    else
                    {

                        for (int x = i; x < i + 5; x++)
                            for (int y = j; y < j + 5; y++)
                            {
                                bmp2.SetPixel(x, y, Color.FromArgb(c.R, c.G, c.B));
                            }

                    }

                }
            pictureBox1.Image = bmp2;
        }

        private void button5_Click(object sender, EventArgs e)
        {
            //original
            Bitmap bmp = new Bitmap(pictureBox1.Image);

            //Backup
            Bitmap bmpb = new Bitmap(pictureBox1.Image);

            //procesado
            Bitmap bmp2 = new Bitmap(bmp.Width, bmp.Height);

            //siguiente proceso
            Bitmap bmp3 = new Bitmap(bmp.Width, bmp.Height);

            Color c = new Color();
             Color c2 = new Color();
            int ciR, ciG, ciB;

            for (int i = 0; i < bmp.Width - 5; i = i + 5)
                for (int j = 0; j < bmp.Height - 5; j = j + 5)
                {
                    ciR = 0;
                    ciG = 0;
                    ciB = 0;
                    for (int x = i; x < i + 5; x++)
                        for (int y = j; y < j + 5; y++)
                        {
                            c = bmp.GetPixel(x, y);
                            ciR = ciR + c.R;
                            ciG = ciG + c.G;
                            ciB = ciB + c.B;
                        }
                    //promedio pixel
                    ciR = ciR / 25;
                    ciG = ciG / 25;
                    ciB = ciB / 25;

                    for(int h = 0; h < 4; h++)
                    {
                        cmR = m[h, 0]; cmG = m[h, 1]; cmB = m[h, 2];

                        if (((cmR - 10 < ciR) && (ciR < cmR + 10)) && ((cmG - 10 < ciG) && (ciG < cmG + 10)) && ((cmB - 10 < ciB) && (ciB < cmB + 10)))
                        {

                            for (int x = i; x < i + 5; x++)
                                for (int y = j; y < j + 5; y++)
                                {
                                    bmp2.SetPixel(x, y, Color.FromArgb(m[h, 3], m[h, 4], m[h, 5]));
                                }
                        }
                        
                    }

                }

           

            int rbR, rbG, rbB;
            for (int i = 0; i < bmp.Width ; i++)
                for (int j = 0; j < bmp.Height; j++)
                {
                    c = bmpb.GetPixel(i, j);
                    c2 = bmp2.GetPixel(i, j);
                    rbR = c2.R;
                    rbG = c2.G;
                    rbB = c2.B;
                    for (int h = 0; h < 4; h++)
                    {
                        if(rbR==0&&rbG==0&& rbB==0)
                        {
                            bmp2.SetPixel(i, j, Color.FromArgb(c.R,c.G,c.B));
                        }

                    }

                }
            pictureBox1.Image = bmp2;


        }
    }
}
