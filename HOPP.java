import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class HOPP extends JPanel
  implements ClipboardOwner
{
  int testing = 0;

  String version = "Version 1.5";
  int wWidth;
  int wHeight;
  int view = 1; int v2class = 0;
  int mouseOver = 50; int mouseDown = 0;
  int shiftDown = 0; int commandDown = 0;
  int backRemember = 0;
  double[] select = new double[5];
  String[] dateBetween = new String[2];
  Date cDate = new Date();
  int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  int newItem = 0;
  ArrayList<String> fileAL;
  String fullPage;
  String[][] items = new String[10][8];
  String backup = new String("");
  Calendar cal = new GregorianCalendar();
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  Date today;
  Date duedate;
  String fontn = "";

  Color[][] colours = new Color[5][10];
  HOCalcs calcs;
  String fileName;
  String fileName2;
  String fileName3;
  int OS = 0;
  JTextArea jta;
  JScrollPane jsp;
  JTextField jtf;
  Image dbImage;
  Graphics dbg;

  public HOPP(int w, int h)
  {
    String osName = System.getProperty("os.name").toLowerCase();
    if (osName.startsWith("mac")) {
      this.OS += 1;
    }

    this.fileName2 = "HomeWork Organizer";
    this.fileName3 = "info.txt";

    if (this.OS == 1) {
      this.fontn = "Eurostile";
      this.fileName = (FileSystemView.getFileSystemView().getDefaultDirectory().toString() + "/Documents/" + this.fileName2 + "/" + this.fileName3);
    } else {
      this.fontn = "Calibri";
      this.fileName = (FileSystemView.getFileSystemView().getDefaultDirectory().toString() + "/" + this.fileName2 + "/" + this.fileName3);
    }

    this.fileAL = new ArrayList();
    String line = null;
    try
    {
      FileReader fileReader = new FileReader(this.fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while ((line = bufferedReader.readLine()) != null)
      {
        this.fullPage += line;
      }
      System.out.println(this.fullPage);
      String[] s1 = this.fullPage.split("~~");
      for (int i = 0; i < s1.length; i++) {
        this.fileAL.add(s1[i]);
      }
      bufferedReader.close();
    }
    catch (Exception e)
    {
      File f2;
      if (this.OS == 1) {
        File f1 = new File(FileSystemView.getFileSystemView().getDefaultDirectory(), "Documents");
        f2 = new File(f1, this.fileName2);
      } else {
        f2 = new File(FileSystemView.getFileSystemView().getDefaultDirectory(), this.fileName2);
      }
      f2.mkdir();
      File f = new File(f2, this.fileName3);
      try {
        f.createNewFile();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      try {
        PrintWriter pw = new PrintWriter(f);
        pw.write("");
        for (int i = 1; i < 11; i++) {
          pw.write(" Class " + i + "~~ ``~~ ``~~ ``~~ ``~~n``~~ ``~~ `` `` ~~");
        }

        pw.close();
      } catch (FileNotFoundException e1) {
        System.out.println("Didn't work");
      }

      try
      {
        FileReader fileReader = new FileReader(this.fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null)
        {
          this.fullPage += line;
        }
        String[] s1 = this.fullPage.split("~~");
        for (int i = 0; i < s1.length; i++) {
          this.fileAL.add(s1[i]);
        }
        bufferedReader.close();
      } catch (Exception e1) {
        System.out.println("Didn't work 2");
      }

      System.out.println("Didn't work 3");
    }

    try
    {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < this.items[i].length; j++) {
          this.items[i][j] = ((String)this.fileAL.get(i * this.items[i].length + j));
        }

      }

      this.items[0][0] = this.items[0][0].substring(4, this.items[0][0].length());
    } catch (Exception e) {
      this.testing = 2;
    }

    this.calcs = new HOCalcs(w, h);

    this.wWidth = w;
    this.wHeight = (h - 22);

    int t = 128;
    
    this.colours[0][0] = new Color(t, 0, 0);
    this.colours[0][1] = new Color(t, t/2, 0);
    this.colours[0][2] = new Color(t, t, 0);
    this.colours[0][3] = new Color(0, t, 0);
    this.colours[0][4] = new Color(0, t, t);
    this.colours[0][5] = new Color(0, 0, t);
    this.colours[0][6] = new Color(t/2, 0, t);
    this.colours[0][7] = new Color(t, 0, t);
    this.colours[0][8] = new Color(t, t, t);
    this.colours[0][9] = new Color(t/2, t/2, t/2);

    this.colours[1][0] = new Color(t*3/4, 0, 0);
    this.colours[1][1] = new Color(t*3/4, t*3/8, 0);
    this.colours[1][2] = new Color(t*3/4, t*3/4, 0);
    this.colours[1][3] = new Color(0, t*3/4, 0);
    this.colours[1][4] = new Color(0, t*3/4, t*3/4);
    this.colours[1][5] = new Color(0, 0, t*3/4);
    this.colours[1][6] = new Color(t*3/8, 0, t*3/4);
    this.colours[1][7] = new Color(t*3/4, 0, t*3/4);
    this.colours[1][8] = new Color(t*3/4, t*3/4, t*3/4);
    this.colours[1][9] = new Color(t/4, t/4, t/4);

    this.colours[2][0] = new Color(t, t/4, t/4);
    this.colours[2][1] = new Color(t, t*3/5, t/4);
    this.colours[2][2] = new Color(t, t, t/2);
    this.colours[2][3] = new Color(t*3/5, t, t*3/5);
    this.colours[2][4] = new Color(t/2, t, t);
    this.colours[2][5] = new Color(t/4, t/4, t);
    this.colours[2][6] = new Color(t*3/5, t/4, t);
    this.colours[2][7] = new Color(t, t/2, t);
    this.colours[2][8] = new Color(t*7/8, t*7/8, t*7/8);
    this.colours[2][9] = new Color(t*4/5, t*4/5, t*4/5);

    this.colours[3][0] = new Color(t*2/5, 0, 0);
    this.colours[3][1] = new Color(t*2/5, t/5, 0);
    this.colours[3][2] = new Color(t*2/5, t*2/5, 0);
    this.colours[3][3] = new Color(t/5, t*2/5, 0);
    this.colours[3][4] = new Color(0, t*2/5, t*2/5);
    this.colours[3][5] = new Color(0, 0, t*2/5);
    this.colours[3][6] = new Color(t/5, 0, t*2/5);
    this.colours[3][7] = new Color(t*2/5, 0, t*2/5);
    this.colours[3][8] = new Color(t/4, t/4, t/4);
    this.colours[3][9] = new Color(t/8, t/8, t/8);

    this.colours[4][0] = new Color(t/4, t/4, t/4);
    this.colours[4][1] = new Color(t*2/5, t*2/5, t*2/5);
    this.colours[4][2] = new Color(t*7/8, t*7/8, t*7/8);
    this.colours[4][3] = new Color(t/8, t/8, t/8);
    this.colours[4][4] = new Color(t*3/4, t*3/4, t*3/4);
    this.colours[4][5] = new Color(t/2, t/2, t/2);
    this.colours[4][6] = new Color(t*3/5, t/4, t);
    this.colours[4][7] = new Color(t, t/2, t);
    this.colours[4][8] = new Color(t*7/8, t*7/8, t*7/8);
    this.colours[4][9] = new Color(t*3/4, t*3/4, t*3/4);

    this.dateBetween[0] = this.sdf.format(this.cal.getTime());
    this.dateBetween[1] = this.dateBetween[0];
    for (int i = 0; i < 7; i++)
      try {
        this.dateBetween[1] = this.calcs.changeDay(this.sdf.parse(this.dateBetween[1]), 1);
      }
      catch (Exception localException1)
      {
      }
  }

  public void paint(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, this.wWidth, this.wHeight);

    pToolbar(g);

    if (this.view == 1) {
      pClasses(g);
      pv1MouseOver(g);
      pCLabels(g);
    } else if (this.view == 2) {
      pv2Layout(g);
      pv2Items(g);
      pv2FocusItem(g);
      pv2Upcoming(g);
      pv2Facts(g);
    } else if (this.view == 3) {
      pv3Calendar(g);
    }

    g.setColor(Color.black);
    g.fillRect(0, this.wHeight - 5, this.wWidth, 5);

    if (this.testing == 1)
      g.fillRect(200, 200, 400, 400);
  }

  public void pToolbar(Graphics g)
  {
    g.setColor(Color.black);
    g.fillRect(0, 0, this.wWidth, this.calcs.tbheight);

    Font f = new Font(this.fontn, 0, 20);
    g.setFont(f);

    g.setColor(Color.white);
    g.drawString("\u00a9 Raghav Kansal", this.wWidth - 150, 22);
    g.drawString(this.version, this.wWidth - 350, 22);

    int tx = this.wWidth - 210; int tw = 18; int th = 18;
    int ty;
    if (this.view == 1)
      ty = 7;
    else {
      ty = 6;
    }

    g.setColor(this.colours[0][this.v2class]);
    g.drawRect(tx - 3, ty - 3, tw + 5, th + 5);
    if (((this.view == 1) && (this.mouseOver == 40)) || ((this.view == 2) && (this.mouseOver == 20)) || ((this.view == 3) && (this.mouseOver == 2))) {
      g.setColor(this.colours[1][this.v2class]);
      g.fillRect(tx, ty, tw, th);
      if (this.mouseDown == 1)
        g.fillRect(tx - 2, ty - 2, tw + 4, th + 4);
    }
    else {
      g.fillRect(tx, ty, tw, th);
    }
    g.setColor(Color.black);

    g.drawString("C", tx + 2, ty + 16);

    if (this.view == 2) {
      g.setColor(this.colours[0][this.v2class]);
      g.drawRect(this.wWidth / 20 - 46 - 13, 3, 36, 23);
      if (this.mouseOver == 15) {
        g.setColor(this.colours[1][this.v2class]);
        g.fillRect(this.wWidth / 20 - 46 - 10, 6, 31, 18);
        if (this.mouseDown == 1)
          g.fillRect(this.wWidth / 20 - 46 - 12, 4, 35, 22);
      }
      else {
        g.fillRect(this.wWidth / 20 - 46 - 10, 6, 31, 18);
      }
      g.setColor(Color.black);
      int[] xp = { this.wWidth / 20 - 46 - 7, this.wWidth / 20 - 30, this.wWidth / 20 - 30 };
      int[] yp = { 15, 9, 22 };
      g.fillPolygon(xp, yp, 3);

      g.setColor(this.colours[0][this.v2class]);
      g.drawRect(this.wWidth / 20, 3, 23, 23);
      if (this.mouseOver == 2) {
        g.setColor(this.colours[1][this.v2class]);
        g.fillRect(this.wWidth / 20 + 3, 6, 18, 18);
        if (this.mouseDown == 1)
          g.fillRect(this.wWidth / 20 + 1, 4, 22, 22);
      }
      else {
        g.fillRect(this.wWidth / 20 + 3, 6, 18, 18);
      }
      g.setColor(Color.black);
      g.fillRect(this.wWidth / 20 + 4, 14, 16, 2);
      g.fillRect(this.wWidth / 20 + 11, 7, 2, 16);

      g.setColor(this.colours[0][this.v2class]);
      g.drawRect(this.wWidth / 20 + 46, 3, 23, 23);
      if (this.mouseOver == 11) {
        g.setColor(this.colours[1][this.v2class]);
        g.fillRect(this.wWidth / 20 + 49, 6, 18, 18);
        if (this.mouseDown == 1)
          g.fillRect(this.wWidth / 20 + 47, 4, 22, 22);
      }
      else {
        g.fillRect(this.wWidth / 20 + 49, 6, 18, 18);
      }
      g.setColor(Color.black);
      g.fillRect(this.wWidth / 20 + 50, 14, 16, 2);
    } else if (this.view == 3) {
      g.setColor(this.colours[0][this.v2class]);
      g.drawRect(this.wWidth / 20 - 46 - 13, 3, 36, 23);
      if (this.mouseOver == 1) {
        g.setColor(this.colours[1][this.v2class]);
        g.fillRect(this.wWidth / 20 - 46 - 10, 6, 31, 18);
        if (this.mouseDown == 1)
          g.fillRect(this.wWidth / 20 - 46 - 12, 4, 35, 22);
      }
      else {
        g.fillRect(this.wWidth / 20 - 46 - 10, 6, 31, 18);
      }
      g.setColor(Color.black);
      int[] xp = { this.wWidth / 20 - 46 - 7, this.wWidth / 20 - 30, this.wWidth / 20 - 30 };
      int[] yp = { 15, 9, 22 };
      g.fillPolygon(xp, yp, 3);
    }
  }

  public void pClasses(Graphics g)
  {
    for (int i = 0; i < 10; i++) {
      g.setColor(this.colours[0][i]);
      g.fillRect(i % 5 * this.calcs.v1cw, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);

      g.setColor(Color.black);
      g.drawRect(i % 5 * this.calcs.v1cw + 1, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
      g.drawRect(i % 5 * this.calcs.v1cw - 1, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
      g.drawRect(i % 5 * this.calcs.v1cw, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight + 1, this.calcs.v1cw, this.calcs.v1ch);
      g.drawRect(i % 5 * this.calcs.v1cw, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight - 1, this.calcs.v1cw, this.calcs.v1ch);
    }
  }

  public void pv1MouseOver(Graphics g) {
    if (this.mouseOver < 10) {
      g.setColor(this.colours[1][this.mouseOver]);
      g.fillRect(this.mouseOver % 5 * this.calcs.v1cw, (int)Math.floor(this.mouseOver / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw + 2, this.calcs.v1ch);
      if (this.mouseDown == 0) {
        g.setColor(Color.black);
        g.drawRect(this.mouseOver % 5 * this.calcs.v1cw + 1, (int)Math.floor(this.mouseOver / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
        g.drawRect(this.mouseOver % 5 * this.calcs.v1cw - 1, (int)Math.floor(this.mouseOver / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
        g.drawRect(this.mouseOver % 5 * this.calcs.v1cw, (int)Math.floor(this.mouseOver / 5) * this.calcs.v1ch + this.calcs.tbheight + 1, this.calcs.v1cw, this.calcs.v1ch);
        g.drawRect(this.mouseOver % 5 * this.calcs.v1cw, (int)Math.floor(this.mouseOver / 5) * this.calcs.v1ch + this.calcs.tbheight - 1, this.calcs.v1cw, this.calcs.v1ch);
      }
    } else if (this.mouseOver < 20) {
      if (!this.calcs.delim(this.items[(this.mouseOver % 10)][1], 0).equals("")) {
        if (!this.calcs.delim(this.items[(this.mouseOver % 10)][5], 0).equals("y")) {
          g.setColor(Color.black);
          g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 19.0D), 12, 12);
          if (this.mouseDown == 1) {
            g.setColor(Color.lightGray);
            g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 34, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 18.0D), 14, 14);
          }
        }
        else if (this.mouseDown == 1) {
          g.setColor(Color.black);
          g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 34, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 18.0D), 14, 14);
        }
      }
      else {
        int temp = this.mouseOver % 10;
        g.setColor(this.colours[1][temp]);
        g.fillRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw + 2, this.calcs.v1ch);
        if (this.mouseDown == 0) {
          g.setColor(Color.black);
          g.drawRect(temp % 5 * this.calcs.v1cw + 1, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw - 1, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight + 1, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight - 1, this.calcs.v1cw, this.calcs.v1ch);
        }
      }
    } else if (this.mouseOver < 30) {
      if (!this.calcs.delim(this.items[(this.mouseOver % 10)][1], 1).equals("")) {
        if (!this.calcs.delim(this.items[(this.mouseOver % 10)][5], 1).equals("y")) {
          g.setColor(Color.black);
          g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 19.0D), 12, 12);
          if (this.mouseDown == 1) {
            g.setColor(Color.lightGray);
            g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 34, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 18.0D), 14, 14);
          }
        }
        else if (this.mouseDown == 1) {
          g.setColor(Color.black);
          g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 34, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 18.0D), 14, 14);
        }
      }
      else {
        int temp = this.mouseOver % 10;
        g.setColor(this.colours[1][temp]);
        g.fillRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw + 2, this.calcs.v1ch);
        if (this.mouseDown == 0) {
          g.setColor(Color.black);
          g.drawRect(temp % 5 * this.calcs.v1cw + 1, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw - 1, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight + 1, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight - 1, this.calcs.v1cw, this.calcs.v1ch);
        }
      }
    } else if (this.mouseOver < 40) {
      if (!this.calcs.delim(this.items[(this.mouseOver % 10)][1], 2).equals("")) {
        if (!this.calcs.delim(this.items[(this.mouseOver % 10)][5], 2).equals("y")) {
          g.setColor(Color.black);
          g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 19.0D), 12, 12);
          if (this.mouseDown == 1) {
            g.setColor(Color.lightGray);
            g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 34, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 18.0D), 14, 14);
          }
        }
        else if (this.mouseDown == 1) {
          g.setColor(Color.black);
          g.fillRect(this.mouseOver % 5 * this.calcs.v1cw + this.calcs.v1cw - 34, (int)((int)Math.floor(this.mouseOver % 10 / 5) * this.calcs.v1ch + this.calcs.tbheight * (Math.floor((this.mouseOver - 10) / 10) * 3.0D + 4.0D) + 18.0D), 14, 14);
        }
      }
      else {
        int temp = this.mouseOver % 10;
        g.setColor(this.colours[1][temp]);
        g.fillRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw + 2, this.calcs.v1ch);
        if (this.mouseDown == 0) {
          g.setColor(Color.black);
          g.drawRect(temp % 5 * this.calcs.v1cw + 1, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw - 1, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight + 1, this.calcs.v1cw, this.calcs.v1ch);
          g.drawRect(temp % 5 * this.calcs.v1cw, (int)Math.floor(temp / 5) * this.calcs.v1ch + this.calcs.tbheight - 1, this.calcs.v1cw, this.calcs.v1ch);
        }
      }
    }
  }

  public void pCLabels(Graphics g)
  {
    for (int i = 0; i < 10; i++) {
      g.setColor(Color.black);
      g.fillRect(i % 5 * this.calcs.v1cw + 10, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight + 10, this.calcs.v1cw - 20, this.calcs.v1ch / 10);

      g.setColor(Color.white);
      Font f = new Font(this.fontn, 0, 24);
      g.setFont(f);

      if (this.items[i][0].length() > 19)
        g.drawString(this.items[i][0].substring(0, 17) + "...", i % 5 * this.calcs.v1cw + 7, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 2 + 6);
      else {
        g.drawString(this.items[i][0], i % 5 * this.calcs.v1cw + 7, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 2 + 6);
      }

      g.setColor(Color.black);
      if (!this.calcs.delim(this.items[i][1], 0).equals("")) {
        try {
          this.duedate = this.sdf.parse(this.calcs.delim(this.items[i][4], 0));
          g.drawRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 4 + 17, 15, 15);
          if (this.calcs.delim(this.items[i][5], 0).equals("y")) {
            if (this.mouseOver != i + 10)
              g.fillRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 4 + 17 + 2, 12, 12);
          }
          else if (this.today.compareTo(this.duedate) > 0) {
            g.setColor(this.colours[2][i]);
            g.fillRect(i % 5 * this.calcs.v1cw + 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 4 + 12, 126, 24);
            g.setColor(Color.black);
            g.drawRect(i % 5 * this.calcs.v1cw + 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 4 + 12, 126, 24);
          }
        } catch (ParseException e) {
          g.drawRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 4 + 17, 15, 15);
          if ((this.calcs.delim(this.items[i][5], 0).equals("y")) && 
            (this.mouseOver != i + 10)) {
            g.fillRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 4 + 17 + 2, 12, 12);
          }
        }

      }

      if (!this.calcs.delim(this.items[i][1], 1).equals("")) {
        try {
          this.duedate = this.sdf.parse(this.calcs.delim(this.items[i][4], 1));
          g.drawRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 7 + 17, 15, 15);
          g.drawRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 7 + 17, 15, 15);
          if (this.calcs.delim(this.items[i][5], 1).equals("y")) {
            if (this.mouseOver != i + 20)
              g.fillRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 7 + 17 + 2, 12, 12);
          }
          else if (this.today.compareTo(this.duedate) > 0) {
            g.setColor(this.colours[2][i]);
            g.fillRect(i % 5 * this.calcs.v1cw + 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 7 + 12, 126, 24);
            g.setColor(Color.black);
            g.drawRect(i % 5 * this.calcs.v1cw + 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 7 + 12, 126, 24);
          }
        } catch (ParseException e) {
          g.drawRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 7 + 17, 15, 15);
          if ((this.calcs.delim(this.items[i][5], 1).equals("y")) && 
            (this.mouseOver != i + 20)) {
            g.fillRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 7 + 17 + 2, 12, 12);
          }
        }

      }

      if (!this.calcs.delim(this.items[i][1], 2).equals("")) {
        try {
          this.duedate = this.sdf.parse(this.calcs.delim(this.items[i][4], 2));
          g.drawRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 10 + 17, 15, 15);
          g.drawRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 10 + 17, 15, 15);
          if (this.calcs.delim(this.items[i][5], 2).equals("y")) {
            if (this.mouseOver != i + 30)
              g.fillRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 10 + 17 + 2, 12, 12);
          }
          else if (this.today.compareTo(this.duedate) > 0) {
            g.setColor(this.colours[2][i]);
            g.fillRect(i % 5 * this.calcs.v1cw + 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 10 + 12, 126, 24);
            g.setColor(Color.black);
            g.drawRect(i % 5 * this.calcs.v1cw + 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 10 + 12, 126, 24);
          }
        } catch (ParseException e) {
          g.drawRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 10 + 17, 15, 15);
          if ((this.calcs.delim(this.items[i][5], 2).equals("y")) && 
            (this.mouseOver != i + 30)) {
            g.fillRect(i % 5 * this.calcs.v1cw + this.calcs.v1cw - 33, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * 10 + 17 + 2, 12, 12);
          }
        }

      }

      g.setColor(Color.black);
      Font f2 = new Font(this.fontn, 0, 18);
      g.setFont(f2);

      for (int j = 0; j < 3; j++) {
        if (this.calcs.delim(this.items[i][1], j).length() > 27)
          g.drawString(this.calcs.delim(this.items[i][1], j).substring(0, 24) + "...", i % 5 * this.calcs.v1cw + 20, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * (j * 3 + 4));
        else {
          g.drawString(this.calcs.delim(this.items[i][1], j), i % 5 * this.calcs.v1cw + 20, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * (j * 3 + 4));
        }
        g.drawString(this.calcs.delim(this.items[i][4], j), i % 5 * this.calcs.v1cw + 35, (int)Math.floor(i / 5) * this.calcs.v1ch + this.calcs.tbheight * (j * 3 + 5));
      }
    }
  }

  public void pv2Layout(Graphics g)
  {
    g.setColor(this.colours[0][this.v2class]);
    g.fillRect(0, this.calcs.tbheight, this.wWidth, this.wHeight - this.calcs.tbheight);

    if (this.select[0] == 1.0D) {
      g.setColor(this.colours[4][1]);
      g.fillRect(0, this.calcs.tbheight + 1, this.wWidth, (int)(this.calcs.tbheight * 1.5D));
    } else if (this.select[0] == 1.1D) {
      g.setColor(this.colours[3][this.v2class]);
      g.fillRect(0, this.calcs.tbheight + 1, this.wWidth, (int)(this.calcs.tbheight * 1.5D));
    } else if (this.mouseOver == 1) {
      g.setColor(this.colours[4][0]);
      if (this.mouseDown == 1)
        g.fillRect(0, this.calcs.tbheight, this.wWidth, (int)(this.calcs.tbheight * 1.5D) + 2);
      else
        g.fillRect(0, this.calcs.tbheight + 1, this.wWidth, (int)(this.calcs.tbheight * 1.5D));
    }
    else {
      g.setColor(Color.black);
      g.fillRect(0, this.calcs.tbheight + 1, this.wWidth, (int)(this.calcs.tbheight * 1.5D));
    }

    g.setColor(Color.black);
    g.drawLine(0, (int)(this.calcs.tbheight + this.calcs.tbheight * 1.5D - 1.0D), this.wWidth, (int)(this.calcs.tbheight + this.calcs.tbheight * 1.5D - 1.0D));
    g.drawLine(0, (int)(this.calcs.tbheight + this.calcs.tbheight * 1.5D), this.wWidth, (int)(this.calcs.tbheight + this.calcs.tbheight * 1.5D));

    g.setColor(Color.white);
    Font f = new Font(this.fontn, 0, 36);
    g.setFont(f);
    g.drawString(this.items[this.v2class][0], this.wWidth / 5, this.calcs.tbheight + 37);

    g.setColor(this.colours[2][this.v2class]);
    g.fillRect(0, this.calcs.v2ty, this.calcs.v2ip, this.wHeight - this.calcs.v2ty);

    g.setColor(this.colours[2][this.v2class]);
    g.fillRect(this.calcs.v2fp, this.calcs.v2ty, this.wWidth / 10 * 3, this.wHeight - this.calcs.v2ty);

    g.setColor(this.colours[1][this.v2class]);
    g.fillRect(this.calcs.v2ip, this.calcs.v2ty, this.calcs.v2fp - this.calcs.v2ip, this.wHeight - this.calcs.v2hs);

    g.setColor(Color.white);
    g.fillRect(this.calcs.v2ip, this.calcs.v2hs, this.calcs.v2fp - this.calcs.v2ip, this.wHeight - this.calcs.v2hs);

    g.setColor(Color.black);
    g.drawLine(this.calcs.v2ip - 1, this.calcs.v2ty, this.calcs.v2ip - 1, this.wHeight);
    g.drawLine(this.calcs.v2ip, this.calcs.v2ty, this.calcs.v2ip, this.wHeight);
    g.drawLine(this.calcs.v2ip + 1, this.calcs.v2ty, this.calcs.v2ip + 1, this.wHeight);

    g.drawLine(this.calcs.v2fp - 1, this.calcs.v2ty, this.calcs.v2fp - 1, this.wHeight);
    g.drawLine(this.calcs.v2fp, this.calcs.v2ty, this.calcs.v2fp, this.wHeight);
    g.drawLine(this.calcs.v2fp + 1, this.calcs.v2ty, this.calcs.v2fp + 1, this.wHeight);

    g.drawLine(this.calcs.v2fp, this.calcs.v2hs - 1, this.calcs.v2ip, this.calcs.v2hs - 1);
    g.drawLine(this.calcs.v2fp, this.calcs.v2hs, this.calcs.v2ip, this.calcs.v2hs);
    g.drawLine(this.calcs.v2fp, this.calcs.v2hs + 1, this.calcs.v2ip, this.calcs.v2hs + 1);
  }

  public void pv2Items(Graphics g)
  {
    g.setColor(this.colours[0][this.v2class]);
    g.fillRect(0, (int)(this.calcs.v2ty + this.calcs.v2ih * this.select[2]), this.calcs.v2ip - 1, this.calcs.v2ih);

    g.setColor(this.colours[1][this.v2class]);
    if ((this.select[1] >= 0.0D) && (this.select[1] < 6.0D)) {
      g.fillRect(0, (int)(this.calcs.v2ty + this.calcs.v2ih * this.select[1]), this.calcs.v2ip - 1, this.calcs.v2ih);
    }

    for (int i = 1; i < 7; i++) {
      g.setColor(Color.black);
      g.drawLine(0, this.calcs.v2ty + this.calcs.v2ih * i, this.calcs.v2ip, this.calcs.v2ty + this.calcs.v2ih * i);
      g.drawLine(0, this.calcs.v2ty + this.calcs.v2ih * i - 1, this.calcs.v2ip, this.calcs.v2ty + this.calcs.v2ih * i - 1);
    }

    g.setColor(this.colours[3][this.v2class]);
    Font f1 = new Font(this.fontn, 0, 18);
    Font f2 = new Font(this.fontn, 0, 15);

    for (int i = 0; i < 6; i++) {
      String s1 = this.calcs.delim(this.items[this.v2class][1], (int)(i + this.select[3]));
      String s2 = this.calcs.delim(this.items[this.v2class][2], (int)(i + this.select[3]));

      g.setFont(f1);
      if (s1.length() > 38)
        g.drawString(s1.substring(0, 35) + "...", this.calcs.v2ip / 25, this.calcs.v2ty + this.calcs.v2ih * i + this.calcs.v2ih / 5 * 2);
      else {
        g.drawString(s1, this.calcs.v2ip / 25, this.calcs.v2ty + this.calcs.v2ih * i + this.calcs.v2ih / 5 * 2);
      }

      g.setFont(f2);
      if (s2.length() > 80) {
        g.drawString(s2.substring(0, 40), this.calcs.v2ip / 21, (int)(this.calcs.v2ty + this.calcs.v2ih * i + this.calcs.v2ih / 5 * 3.5D));
        g.drawString(s2.substring(40, 77) + "...", this.calcs.v2ip / 21, (int)(this.calcs.v2ty + this.calcs.v2ih * i + this.calcs.v2ih / 5 * 3.5D + 20.0D));
      } else if (s2.length() > 40) {
        g.drawString(s2.substring(0, 39) + "-", this.calcs.v2ip / 21, (int)(this.calcs.v2ty + this.calcs.v2ih * i + this.calcs.v2ih / 5 * 3.5D));
        g.drawString(s2.substring(39, s2.length()), this.calcs.v2ip / 21 + 4, (int)(this.calcs.v2ty + this.calcs.v2ih * i + this.calcs.v2ih / 5 * 3.5D + 20.0D));
      } else {
        g.drawString(s2, this.calcs.v2ip / 21, (int)(this.calcs.v2ty + this.calcs.v2ih * i + this.calcs.v2ih / 5 * 3.5D));
      }
    }
  }

  public void pv2FocusItem(Graphics g)
  {
    int sel = (int)(this.select[1] + this.select[3]);

    g.setColor(Color.black);
    g.drawRect(this.calcs.v2ip + 14, this.calcs.v2ty + 14, this.calcs.v2fp - this.calcs.v2ip - 28, this.calcs.v2hs / 10 + 2);
    g.drawRect(this.calcs.v2ip + 15, this.calcs.v2ty + 15, this.calcs.v2fp - this.calcs.v2ip - 30, this.calcs.v2hs / 10);
    if (this.select[0] == 2.0D) {
      g.setColor(this.colours[4][4]);
      g.fillRect(this.calcs.v2ip + 16, this.calcs.v2ty + 16, this.calcs.v2fp - this.calcs.v2ip - 31, this.calcs.v2hs / 10 - 1);
    } else if (this.select[0] == 2.1D) {
      g.setColor(this.colours[2][this.v2class]);
      g.fillRect(this.calcs.v2ip + 16, this.calcs.v2ty + 16, this.calcs.v2fp - this.calcs.v2ip - 31, this.calcs.v2hs / 10 - 1);
    }
    else if (this.mouseOver == 3) {
      g.setColor(this.colours[4][2]);
      if (this.mouseDown == 1)
        g.fillRect(this.calcs.v2ip + 14, this.calcs.v2ty + 14, this.calcs.v2fp - this.calcs.v2ip - 27, this.calcs.v2hs / 10 + 3);
      else
        g.fillRect(this.calcs.v2ip + 16, this.calcs.v2ty + 16, this.calcs.v2fp - this.calcs.v2ip - 31, this.calcs.v2hs / 10 - 1);
    }
    else {
      g.setColor(Color.white);
      g.fillRect(this.calcs.v2ip + 16, this.calcs.v2ty + 16, this.calcs.v2fp - this.calcs.v2ip - 31, this.calcs.v2hs / 10 - 1);
    }

    Font f = new Font(this.fontn, 0, 24);
    g.setFont(f);
    if (this.calcs.delim(this.items[this.v2class][1], sel).equals(" ")) {
      g.setColor(Color.GRAY);
      g.drawString("Assignment Name", this.calcs.v2ip + 22, this.calcs.v2ty + 44);
    } else {
      g.setColor(this.colours[3][this.v2class]);
      g.drawString(this.calcs.delim(this.items[this.v2class][1], sel), this.calcs.v2ip + 14, this.calcs.v2ty + 44);
    }

    g.setColor(Color.black);
    g.drawRect(this.calcs.v2ip + 14, this.calcs.v2ty + this.calcs.v2hs / 10 + 30, (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 - 28, this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 44);
    g.drawRect(this.calcs.v2ip + 15, this.calcs.v2ty + this.calcs.v2hs / 10 + 31, (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 - 30, this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 46);
    if (this.select[0] == 3.0D) {
      g.setColor(this.colours[4][4]);
      g.fillRect(this.calcs.v2ip + 16, this.calcs.v2ty + this.calcs.v2hs / 10 + 32, (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 - 31, this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 47);
    } else if (this.select[0] == 3.1D) {
      g.setColor(this.colours[2][this.v2class]);
      g.fillRect(this.calcs.v2ip + 16, this.calcs.v2ty + this.calcs.v2hs / 10 + 32, (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 - 31, this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 47);
    }
    else if (this.mouseOver == 4) {
      g.setColor(this.colours[4][2]);
      if (this.mouseDown == 1)
        g.fillRect(this.calcs.v2ip + 14, this.calcs.v2ty + this.calcs.v2hs / 10 + 30, (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 - 28, this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 44);
      else
        g.fillRect(this.calcs.v2ip + 16, this.calcs.v2ty + this.calcs.v2hs / 10 + 32, (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 - 31, this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 47);
    }
    else {
      g.setColor(Color.white);
      g.fillRect(this.calcs.v2ip + 16, this.calcs.v2ty + this.calcs.v2hs / 10 + 32, (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 - 31, this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 47);
    }

    String s1 = this.calcs.delim(this.items[this.v2class][2], sel);
    f = new Font("Inconsulata", 0, 12);
    g.setFont(f);
    int l = 45;
    int n = (int)Math.ceil(s1.length() / l);
    if (this.calcs.delim(this.items[this.v2class][2], sel).equals(" ")) {
      g.setColor(Color.GRAY);
      g.drawString("Notes", this.calcs.v2ip + 23, this.calcs.v2ty + this.calcs.v2hs / 10 + 52);
    } else {
      g.setColor(this.colours[3][this.v2class]);
      for (int i = 0; i < n; i++) {
        if (i == n - 1) {
          if (i == 0)
            g.drawString(s1.substring(l * i, s1.length()), this.calcs.v2ip + 18, this.calcs.v2ty + this.calcs.v2hs / 10 + 52 + 20 * i);
          else {
            g.drawString(" " + s1.substring(l * i, s1.length()), this.calcs.v2ip + 18, this.calcs.v2ty + this.calcs.v2hs / 10 + 52 + 20 * i);
          }
        }
        else if (i == 0)
          g.drawString(s1.substring(l * i, l * (i + 1)) + "-", this.calcs.v2ip + 18, this.calcs.v2ty + this.calcs.v2hs / 10 + 52 + 20 * i);
        else {
          g.drawString(" " + s1.substring(l * i, l * (i + 1)) + "-", this.calcs.v2ip + 18, this.calcs.v2ty + this.calcs.v2hs / 10 + 52 + 20 * i);
        }

      }

    }

    int x1 = this.calcs.v2ip + (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 + 2;
    int y1 = this.calcs.v2ty + this.calcs.v2hs / 10 + 30 + 2;
    int w1 = this.calcs.v2fp - x1 - 15;
    int h1 = (this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 114) / 5 + 1;

    for (int i = 0; i < 5; i++) {
      g.setColor(Color.black);
      if (i == 4) {
        h1 -= 2;
      }
      g.drawRect(x1 - 2, y1 - 2, w1 + 3, h1 + 3);
      g.drawRect(x1 - 1, y1 - 1, w1 + 1, h1 + 1);
      if (this.select[0] == i + 4) {
        g.setColor(this.colours[4][4]);
        g.fillRect(x1, y1, w1, h1);
      } else if (this.select[0] == i + 4.1D) {
        g.setColor(this.colours[2][this.v2class]);
        g.fillRect(x1, y1, w1, h1);
      }
      else if (this.mouseOver == i + 5) {
        g.setColor(this.colours[4][2]);
        if (this.mouseDown == 1)
          g.fillRect(x1 - 2, y1 - 2, w1 + 3, h1 + 3);
        else
          g.fillRect(x1, y1, w1, h1);
      }
      else {
        g.setColor(Color.white);
        g.fillRect(x1, y1, w1, h1);
      }

      y1 += h1 + 16;
    }
    g.setColor(this.colours[1][this.v2class]);
    g.fillRect(x1 + w1 / 2 - 8, y1 - (h1 + 16) * 2 - 4, 16, h1 + 6);
    g.setColor(Color.black);
    g.fillRect(x1 + w1 / 2 - 8, y1 - (h1 + 16) * 2 - 4, 2, h1 + 6);
    g.fillRect(x1 + w1 / 2 + 7, y1 - (h1 + 16) * 2 - 4, 2, h1 + 6);

    y1 = this.calcs.v2ty + this.calcs.v2hs / 10 + 30 + 2;

    f = new Font(this.fontn, 0, 18);
    g.setFont(f);
    if (this.calcs.delim(this.items[this.v2class][3], sel).equals(" ")) {
      g.setColor(Color.GRAY);
      g.drawString(" Date Assigned", x1 + 9, y1 + 24);
    } else {
      g.setColor(this.colours[3][this.v2class]);
      g.drawString(this.calcs.delim(this.items[this.v2class][3], sel) + "      -     A", x1 + w1 / 20, y1 + 24);
    }

    if (this.calcs.delim(this.items[this.v2class][4], sel).equals(" ")) {
      g.setColor(Color.GRAY);
      g.drawString(" Date Due", x1 + 9, h1 + 18 + y1 + 24);
    } else {
      g.setColor(this.colours[3][this.v2class]);
      g.drawString(this.calcs.delim(this.items[this.v2class][4], sel) + "      -     D", x1 + w1 / 20, h1 + 18 + y1 + 24);
    }

    g.setColor(this.colours[3][this.v2class]);
    try {
      String daysLeft = this.calcs.daysLeft(this.sdf.parse(this.calcs.delim(this.items[this.v2class][4], (int)(this.select[1] + this.select[3]))), this.today);
      if (this.calcs.delim(this.items[this.v2class][5], sel).equals("n"))
        g.drawString(daysLeft, x1 + w1 / 20 + 4, (h1 + 18) * 2 + y1 + 24);
      else
        g.drawString(" - Days Left", x1 + w1 / 20 + 4, (h1 + 18) * 2 + y1 + 24);
    }
    catch (ParseException e) {
      if (this.calcs.delim(this.items[this.v2class][4], sel).equals(" ")) {
        g.setColor(Color.GRAY);
        g.drawString(" Days Left", x1 + w1 / 20, (h1 + 18) * 2 + y1 + 24);
      } else {
        g.setColor(Color.black);
        g.drawString(" Invalid Due Date", x1 + w1 / 20, (h1 + 18) * 2 + y1 + 24);
      }
    }

    g.setColor(this.colours[3][this.v2class]);
    if (this.calcs.delim(this.items[this.v2class][6], sel).equals(" ")) {
      g.setColor(Color.gray);
      g.drawString(" % Grade", x1 + w1 / 20, (h1 + 18) * 3 + y1 + 24);
    } else {
      g.drawString(this.calcs.delim(this.items[this.v2class][6], sel) + "%", x1 + w1 / 20, (h1 + 18) * 3 + y1 + 24);
    }

    g.setColor(this.colours[3][this.v2class]);
    if (this.calcs.delim(this.items[this.v2class][6], sel).equals(" ")) {
      g.setColor(Color.gray);
      g.drawString("Letter Grade", x1 + w1 / 20 + w1 / 2 + 2, (h1 + 18) * 3 + y1 + 24);
    } else {
      g.drawString(this.calcs.lGrade(this.calcs.delim(this.items[this.v2class][6], sel)), x1 + w1 / 20 + w1 / 2 + 8, (h1 + 18) * 3 + y1 + 24);
    }

    g.setColor(this.colours[3][this.v2class]);
    if (this.calcs.delim(this.items[this.v2class][5], sel).equals("n"))
      g.drawString(" Incomplete", x1 + w1 / 20, (h1 + 18) * 4 + y1 + 24);
    else {
      g.drawString(" Complete", x1 + w1 / 20, (h1 + 18) * 4 + y1 + 24);
    }

    g.setColor(Color.black);
    g.drawRect(x1 + w1 - h1 - 5, y1 + (h1 + 18) * 4 + 3, h1 - 7, h1 - 7);
    g.drawRect(x1 + w1 - h1 - 4, y1 + (h1 + 18) * 4 + 4, h1 - 9, h1 - 9);
    if (this.mouseOver == 10) {
      g.setColor(Color.gray);
      g.fillRect(x1 + w1 - h1 - 2, y1 + (h1 + 18) * 4 + 6, h1 - 12, h1 - 12);
      if (this.mouseDown == 1)
        g.fillRect(x1 + w1 - h1 - 5, y1 + (h1 + 18) * 4 + 3, h1 - 6, h1 - 6);
    }
    else {
      g.setColor(Color.black);
      g.fillRect(x1 + w1 - h1 - 2, y1 + (h1 + 18) * 4 + 6, h1 - 12, h1 - 12);
    }
  }

  public void pv2Upcoming(Graphics g)
  {
    int x1 = this.calcs.v2ip + (this.calcs.v2fp - this.calcs.v2ip) * 2 / 5;
    g.setColor(Color.black);
    g.drawLine(x1, this.calcs.v2hs, x1, this.wHeight);
    g.drawLine(x1 + 1, this.calcs.v2hs, x1 + 1, this.wHeight);

    g.setColor(this.colours[3][this.v2class]);
    Font f = new Font(this.fontn, 0, 24);
    g.setFont(f);
    g.drawString("Assignments Due", this.calcs.v2ip + 30, this.calcs.v2hs + 66);
    g.drawString("Between", this.calcs.v2ip + 73, this.calcs.v2hs + 100);
    g.drawString("and", this.calcs.v2ip + 100, this.calcs.v2hs + 196);

    int x2 = this.calcs.v2ip + 20;
    int y2 = this.calcs.v2hs + 120;
    int w2 = x1 - this.calcs.v2ip - 40;
    int h2 = 40;

    for (int i = 0; i < 2; i++) {
      g.setColor(Color.black);
      g.drawRect(x2 - 2, y2 - 2, w2 + 3, h2 + 3);
      g.drawRect(x2 - 1, y2 - 1, w2 + 1, h2 + 1);
      if (this.select[0] == i + 16) {
        g.setColor(this.colours[1][this.v2class]);
        g.fillRect(x2, y2, w2, h2);
      } else if (this.select[0] == i + 16.100000000000001D) {
        g.setColor(this.colours[4][4]);
        g.fillRect(x2, y2, w2, h2);
      }
      else if (this.mouseOver == i + 16) {
        g.setColor(this.colours[0][this.v2class]);
        if (this.mouseDown == 1)
          g.fillRect(x2 - 2, y2 - 2, w2 + 3, h2 + 3);
        else
          g.fillRect(x2, y2, w2, h2);
      }
      else {
        g.setColor(this.colours[2][this.v2class]);
        g.fillRect(x2, y2, w2, h2);
      }

      g.setColor(Color.black);
      g.drawString(this.dateBetween[i], x2 + 20, y2 + 28);
      y2 += h2 + 60;
    }

    int[] assignments = { 900000, 900000, 900000, 900000, 900000 };
    int nass = 0;
    for (int i = 0; i < this.calcs.delimLength(this.items[this.v2class][1]); i++) {
      if (nass >= 5) continue;
      try {
        if ((this.calcs.dateCompare(this.sdf.parse(this.calcs.delim(this.items[this.v2class][4], i)), this.sdf.parse(this.dateBetween[0])) > -1) && (this.calcs.dateCompare(this.sdf.parse(this.calcs.delim(this.items[this.v2class][4], i)), this.sdf.parse(this.dateBetween[1])) < 1)) {
          assignments[nass] = i;
          nass++;
        }

      }
      catch (Exception localException)
      {
      }

    }

    Font f1 = new Font(this.fontn, 0, 24);
    Font f2 = new Font(this.fontn, 0, 18);

    int x3 = x1 + 10;
    int y3 = this.calcs.v2hs + 10;
    int w3 = this.calcs.v2fp - x1 - 20;
    int h3 = (this.wHeight - this.calcs.v2hs - 60) / 5;
    for (int i = 0; i < 5; i++)
      if (assignments[i] != 900000) {
        g.setColor(this.colours[0][this.v2class]);
        g.fillRect(x3, y3, w3, h3);

        g.setColor(Color.black);
        g.drawRect(x3, y3, w3, h3);
        g.drawRect(x3 - 1, y3 - 1, w3 + 2, h3 + 2);

        g.setFont(f1);
        if (this.calcs.delim(this.items[this.v2class][1], assignments[i]).length() < 30)
          g.drawString(this.calcs.delim(this.items[this.v2class][1], assignments[i]), x3, y3 + 25);
        else {
          g.drawString(this.calcs.delim(this.items[this.v2class][1], assignments[i]).substring(0, 30) + "...", x3, y3 + 25);
        }

        g.setFont(f2);
        g.drawString(this.calcs.delim(this.items[this.v2class][4], assignments[i]), x3 + 60, y3 + 45);
        if (this.calcs.delim(this.items[this.v2class][5], assignments[i]).equals("n"))
          g.drawString("Incomplete", x3 + 240, y3 + 45);
        else {
          g.drawString("Complete", x3 + 240, y3 + 45);
        }
        y3 += h3 + 10;
      }
  }

  public void pv2Facts(Graphics g)
  {
    int x = this.calcs.v2fp + 18;
    int y = this.calcs.v2ty + 18;
    int w = this.wWidth - this.calcs.v2fp - 35;
    int h = (this.wHeight - this.calcs.v2ty - 16 - 5) / 10 - 19;

    for (int i = 0; i < 4; i++) {
      if (i > 1) {
        g.setColor(Color.black);
        g.drawRect(x - 2, y - 2, w + 3, h + 3);
        g.drawRect(x - 1, y - 1, w + 1, h + 1);
        g.setColor(Color.white);
        g.fillRect(x, y, w, h);
      } else {
        g.setColor(Color.black);
        g.drawRect(x - 2, y - 2, w + 3, h + 3);
        g.drawRect(x - 1, y - 1, w + 1, h + 1);
        if (this.select[0] == i + 12) {
          g.setColor(this.colours[4][4]);
          g.fillRect(x, y, w, h);
        } else if (this.select[0] == i + 12.1D) {
          g.setColor(this.colours[0][this.v2class]);
          g.fillRect(x, y, w, h);
        }
        else if (this.mouseOver == i + 12) {
          g.setColor(this.colours[4][2]);
          if (this.mouseDown == 1)
            g.fillRect(x - 2, y - 2, w + 4, h + 4);
          else
            g.fillRect(x, y, w, h);
        }
        else {
          g.setColor(Color.white);
          g.fillRect(x, y, w, h);
        }
      }

      y += h + 28;
    }

    h = this.wHeight - y - 16 - 5;

    g.setColor(Color.black);
    g.drawRect(x - 2, y - 2, w + 3, h + 3);
    g.drawRect(x - 1, y - 1, w + 1, h + 1);
    if (this.select[0] == 14.0D) {
      g.setColor(this.colours[4][4]);
      g.fillRect(x, y, w, h);
    } else if (this.select[0] == 14.1D) {
      g.setColor(this.colours[0][this.v2class]);
      g.fillRect(x, y, w, h);
    }
    else if (this.mouseOver == 14) {
      g.setColor(this.colours[4][2]);
      if (this.mouseDown == 1)
        g.fillRect(x - 2, y - 2, w + 4, h + 4);
      else
        g.fillRect(x, y, w, h);
    }
    else {
      g.setColor(Color.white);
      g.fillRect(x, y, w, h);
    }

    Font f = new Font(this.fontn, 0, 24);
    g.setFont(f);

    h = (this.wHeight - this.calcs.v2ty - 16 - 5) / 10 - 15;
    y = this.calcs.v2ty + h;
    x += 5;
    if (this.calcs.delim(this.items[this.v2class][7], 0).equals(" ")) {
      g.setColor(Color.gray);
      g.drawString(" Teacher: ", x, y);
    } else {
      g.setColor(this.colours[3][this.v2class]);
      g.drawString(" Teacher:" + this.calcs.delim(this.items[this.v2class][7], 0), x, y);
    }

    y += h + 24;
    if (this.calcs.delim(this.items[this.v2class][7], 1).equals(" ")) {
      g.setColor(Color.gray);
      g.drawString(" Students: ", x, y);
    } else {
      g.setColor(this.colours[3][this.v2class]);
      g.drawString(" Students:" + this.calcs.delim(this.items[this.v2class][7], 1), x, y);
    }

    y += h + 24;
    g.setColor(this.colours[3][this.v2class]);
    g.drawString(" Assignments: " + this.calcs.delimLength(this.items[this.v2class][1]), x, y);

    y += h + 24;
    g.setColor(this.colours[3][this.v2class]);
    g.drawString(" Longest Assignment: " + this.calcs.longDate(this.items[this.v2class][3], this.items[this.v2class][4]) + " Days", x, y);

    y += h + 13;
    String s1 = this.calcs.delim(this.items[this.v2class][7], 2);
    f = new Font("Inconsulata", 0, 13);
    g.setFont(f);
    int l = 46;
    int n = (int)Math.ceil(s1.length() / l);
    if (s1.equals(" ")) {
      g.setColor(Color.GRAY);
      g.drawString(" Notes", x, y);
    } else {
      g.setColor(this.colours[3][this.v2class]);
      for (int i = 0; i < n; i++)
        if (i == n - 1) {
          if (i == 0)
            g.drawString(s1.substring(l * i, s1.length()), x, y + 20 * i);
          else {
            g.drawString(" " + s1.substring(l * i, s1.length()), x, y + 20 * i);
          }
        }
        else if (i == 0)
          g.drawString(s1.substring(l * i, l * (i + 1)) + "-", x, y + 20 * i);
        else
          g.drawString(" " + s1.substring(l * i, l * (i + 1)) + "-", x, y + 20 * i);
    }
  }

  public void pv3Calendar(Graphics g)
  {
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    SimpleDateFormat mm = new SimpleDateFormat("MMMM");
    Calendar c = Calendar.getInstance();
    try {
      c.setTime(this.sdf.parse(this.calcs.firstDay(this.cDate)));
    }
    catch (Exception localException1)
    {
    }
    g.setColor(this.colours[4][2]);
    g.fillRect(0, this.calcs.tbheight, this.wWidth, 40);
    g.setColor(Color.black);
    try {
      g.drawString(mm.format(this.cDate) + "   " + yy.format(this.cDate), 420, this.calcs.tbheight + 27);
    }
    catch (Exception localException2)
    {
    }
    int y1 = this.calcs.tbheight + 40;
    int h1 = (this.wHeight - y1) / 7;
    g.setColor(Color.black);
    g.fillRect(0, y1, this.wWidth, 2);

    g.setColor(this.colours[4][1]);
    g.fillRect(0, y1 + 2, this.wWidth - 300, h1);

    g.setColor(Color.black);
    for (int i = 0; i < 7; i++) {
      y1 += h1;
      g.fillRect(0, y1, this.wWidth - 300, 2);
    }

    int x1 = 0;
    y1 = this.calcs.tbheight + 40;
    int w1 = (this.wWidth - 300) / 7;
    String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

    for (int i = 0; i < 7; i++) {
      g.setColor(Color.white);
      g.drawString(days[i], x1 + w1 / 2 - 18, y1 + h1 / 2 + 5);
      x1 += w1;
      g.setColor(Color.black);
      g.fillRect(x1, y1, 2, this.wHeight);
    }

    int firstDay = c.get(7) - 1;
    int month = c.get(2);

    if (Integer.parseInt(yy.format(this.cDate)) % 4 == 0) {
      this.monthDays[1] = 29;
    }

    int[][] dueDays = new int[10][31];
    Date tDate = null;
    try {
      tDate = this.sdf.parse(this.calcs.firstDay(this.cDate));
    }
    catch (Exception localException3) {
    }
    for (int i = 0; i < this.monthDays[month]; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < this.calcs.delimLength(this.items[j][1]); k++)
          try {
            if (this.calcs.dateCompare(this.sdf.parse(this.calcs.delim(this.items[j][4], k)), tDate) == 0)
              dueDays[j][i] = 1;
          }
          catch (Exception localException4)
          {
          }
      }
      try
      {
        tDate = this.sdf.parse(this.calcs.changeDay(tDate, 1));
      }
      catch (Exception localException5) {
      }
    }
    this.select[1] = firstDay;
    this.select[2] = (this.monthDays[month] + firstDay);

    for (int i = firstDay; i < this.monthDays[month] + firstDay; i++)
    {
      int ty = 0; int th = 0;
      int tw;
      if (i % 7 == 0) {
        int tx = 0;
        tw = w1;
      } else {
        int tx = i % 7 * w1 + 2;
        tw = w1 - 2;
      }

      int size = 0;
      int[] classes = new int[10];

      for (int j = 0; j < 10; j++)
      {
        if (dueDays[j][(i - firstDay)] == 1) {
          classes[size] = j;
          size++;
        }
      }
      int tts;
      if (size > 5)
        tts = (w1 - 13) / size;
      else {
        tts = (w1 - 13) / 5;
      }
      int ttx = i % 7 * w1 + 8;
      int tty = (int)(Math.ceil(i / 7) * h1) + h1 + y1 + 7;

      for (int j = 0; j < size; j++) {
        if (classes[j] == 8)
          g.setColor(this.colours[4][2]);
        else {
          g.setColor(this.colours[0][classes[j]]);
        }
        if (i % 7 == 0)
          g.fillRect(ttx - 2, tty, tts, tts);
        else {
          g.fillRect(ttx, tty, tts, tts);
        }
        ttx += tts;
      }

      ttx = i % 7 * w1 + 8;
      tty = (int)(Math.ceil(i / 7) * h1) + h1 + y1 + 7;

      for (int j = 0; j < size; j++) {
        if (i % 7 == 0) {
          g.setColor(Color.black);
          g.drawRect(ttx - 2, tty, tts, tts);
        } else {
          g.setColor(Color.black);
          g.drawRect(ttx, tty, tts, tts);
        }
        ttx += tts;
      }
      g.setColor(Color.black);
      int tx = w1 / 2 - 10 + i % 7 * w1;
      ty = (int)(y1 + 3 * h1 / 2 + 5 + Math.ceil(i / 7) * h1);
      g.drawString(" " + (i - firstDay + 1), tx, ty);
    }

    for (int i = 0; i < firstDay; i++) {
      g.setColor(this.colours[4][4]);

      int y = y1 + h1 + 2;
      if (i == 0) {
        int x = i * w1;
        g.fillRect(x, y, w1, h1 - 2);
      } else {
        int x = i * w1 + 2;
        g.fillRect(x, y, w1 - 2, h1 - 2);
      }
    }
    for (int i = this.monthDays[month] + firstDay; i < 42; i++) {
      g.setColor(this.colours[4][4]);
      int x = i % 7 * w1;
      int y = (int)(Math.ceil(i / 7) * h1 + h1 + y1 + 2.0D);
      if (x == 0)
        g.fillRect(x, y, w1, h1 - 2);
      else {
        g.fillRect(x + 2, y, w1 - 2, h1 - 2);
      }
    }

    if ((this.select[0] > firstDay - 1) && (this.select[0] < firstDay + this.monthDays[month]))
    {
      int[] dueDays2 = new int[10];

      for (int i = 0; i < 10; i++) {
        dueDays2[i] = 1000000000;
      }
      try
      {
        tDate = this.sdf.parse(this.calcs.firstDay(this.cDate));
        for (int i = 0; i < this.select[0] - firstDay; i++) {
          try
          {
            tDate = this.sdf.parse(this.calcs.changeDay(tDate, 1));
          }
          catch (Exception e) {
            check("lkjse");
          }
        }

      }
      catch (Exception localException6)
      {
      }

      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < this.calcs.delimLength(this.items[i][1]); j++) {
          try {
            if (this.calcs.dateCompare(this.sdf.parse(this.calcs.delim(this.items[i][4], j)), tDate) == 0) {
              dueDays2[i] = j;
            }
          }
          catch (Exception localException7)
          {
          }
        }
      }
      int tw = 280; int tx = this.wWidth - 290; int ty = y1 + 60; int th = (this.wHeight - y1 - 160) / 10;

      Font f = new Font(this.fontn, 0, 24);
      g.setFont(f);

      g.setColor(this.colours[4][4]);
      g.fillRect(tx - 8, y1 + 2, tw + 20, 50);

      g.setColor(Color.black);
      g.fillRect(tx - 10, ty - 10, tw + 20, 2);

      g.drawString(mm.format(this.cDate) + " " + (int)(this.select[0] - firstDay + 1.0D), tx + 60, ty - 26);

      for (int i = 0; i < 10; i++) {
        if (dueDays2[i] != 1000000000) {
          g.setColor(this.colours[0][i]);
          g.fillRect(tx, ty, tw, th);
          g.setColor(Color.black);
          g.drawRect(tx, ty, tw, th);
          g.drawRect(tx - 1, ty - 1, tw + 2, th + 2);

          f = new Font(this.fontn, 0, 24);
          g.setFont(f);

          if (this.calcs.delim(this.items[i][1], dueDays2[i]).length() < 20)
            g.drawString(this.calcs.delim(this.items[i][1], dueDays2[i]), tx + 3, ty + 24);
          else {
            g.drawString(this.calcs.delim(this.items[i][1], dueDays2[i]).substring(0, 20) + "...", tx + 3, ty + 24);
          }

          f = new Font(this.fontn, 0, 18);
          g.setFont(f);

          if (this.items[i][0].length() < 16)
            g.drawString(this.items[i][0], tx + 130, ty + 44);
          else {
            g.drawString(this.items[i][0].substring(0, 16) + "...", tx + 130, ty + 44);
          }

          ty += th + 10;
        }

      }

    }

    this.monthDays[1] = 28;
  }

  public void update(Graphics g)
  {
    if (this.dbImage == null)
    {
      this.dbImage = createImage(getSize().width, getSize().height);
      this.dbg = this.dbImage.getGraphics();
    }

    this.dbg.setColor(getBackground());
    this.dbg.fillRect(0, 0, getSize().width, getSize().height);

    this.dbg.setColor(getForeground());
    paint(this.dbg);

    g.drawImage(this.dbImage, 0, 0, this);
  }

  public void forever()
  {
    this.today = this.cal.getTime();
    try
    {
      PrintWriter writer = new PrintWriter(this.fileName);
      writer.print("");
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < this.items[i].length; j++) {
          writer.write(this.items[i][j]);
          writer.write("~~");
        }

      }

      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void check(double t)
  {
    System.out.println(t);
  }

  public void check(String s) {
    System.out.println(s);
  }

  public void resetView(int i) {
    this.select[1] = 0.0D;
    this.select[2] = 0.0D;
    this.select[3] = 0.0D;
    this.select[4] = 0.0D;
    this.select[0] = 0.0D;
    this.view = i;
  }

  public void newItem() {
    if (this.view == 2)
      for (int j = 1; j < 7; j++)
        if (j == 5)
        {
          int tmp28_27 = j;
          String[] tmp28_26 = this.items[this.v2class]; tmp28_26[tmp28_27] = (tmp28_26[tmp28_27] + "n``");
        } else if (j == 3)
        {
          int tmp69_68 = j;
          String[] tmp69_67 = this.items[this.v2class]; tmp69_67[tmp69_68] = (tmp69_67[tmp69_68] + " " + this.sdf.format(this.today) + "``");
        }
        else
        {
          int tmp125_124 = j;
          String[] tmp125_123 = this.items[this.v2class]; tmp125_123[tmp125_124] = (tmp125_123[tmp125_124] + " ``");
        }
  }

  public void removeItem()
  {
    int sel = (int)(this.select[1] + this.select[3]);
    if ((this.view == 2) && 
      (this.calcs.delimLength(this.items[this.v2class][sel]) > 1))
      for (int j = 1; j < 7; j++)
        this.items[this.v2class][j] = this.calcs.delimRemoveItem(this.items[this.v2class][j], sel, j);
  }

  public void mouseMoved(int x, int y)
  {
    if (this.view == 1) {
      int tx1 = this.wWidth - 215; int ty1 = 27; int tw1 = 23; int th1 = 23;
      if (y > this.calcs.tbheight + 24)
        this.mouseOver = this.calcs.v1class(x, y - this.calcs.tbheight - 41);
      else if ((x > tx1) && (x < tx1 + tw1) && (y > ty1) && (y < ty1 + th1))
        this.mouseOver = 40;
      else
        this.mouseOver = 50;
    }
    else if (this.view == 2) {
      int x1 = this.calcs.v2ip + (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 + 2;
      int y1 = this.calcs.v2ty + this.calcs.v2hs / 10 + 30 + 2 + 24;
      int w1 = this.calcs.v2fp - x1 - 15;
      int h1 = (this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 114) / 5 + 1;

      int x2 = this.calcs.v2fp + 18;
      int y2 = this.calcs.v2ty + 18 + 24;
      int w2 = this.wWidth - this.calcs.v2fp - 35;
      int h2 = (this.wHeight - this.calcs.v2ty - 16 - 5) / 10 - 19;

      int x3 = this.calcs.v2ip + 20;
      int y3 = this.calcs.v2hs + 120 + 24;
      int w3 = x1 - this.calcs.v2ip - 40;
      int h3 = 40;
      int tx1 = this.wWidth - 215; int ty1 = 27; int tw1 = 23; int th1 = 23;

      if ((x > this.wWidth / 20) && (y > 27) && (x < this.wWidth / 20 + 23) && (y < 50)) {
        this.mouseOver = 2;
      } else if ((y > this.calcs.tbheight * 2 - 3) && (y < this.calcs.v2ty + this.calcs.tbheight - 3)) {
        this.mouseOver = 1;
      } else if ((y > this.calcs.v2ty + this.calcs.tbheight - 3) && (x < this.calcs.v2ip)) {
        this.mouseOver = 0;
        this.select[2] = this.calcs.v2imo(y - this.calcs.v2ty - this.calcs.tbheight + 3);
      } else if ((x > this.calcs.v2ip + 14) && (y > this.calcs.v2ty + 14 + 24) && (x < this.calcs.v2ip + 14 + this.calcs.v2fp - this.calcs.v2ip - 28) && (y < this.calcs.v2ty + 14 + this.calcs.v2hs / 10 + 2 + 24)) {
        this.mouseOver = 3;
      } else if ((x > this.calcs.v2ip + 14) && (y > this.calcs.v2ty + this.calcs.v2hs / 10 + 54) && (x < this.calcs.v2ip + 14 + (this.calcs.v2fp - this.calcs.v2ip) * 3 / 5 - 28) && (y < this.calcs.v2ty + this.calcs.v2hs / 10 + 30 + this.calcs.v2hs - this.calcs.v2ty - this.calcs.v2hs / 10 - 20)) {
        this.mouseOver = 4;
      } else if ((x > x1) && (x < x1 + w1) && (y > y1) && (y < y1 + h1)) {
        this.mouseOver = 5;
      } else if ((x > x1) && (x < x1 + w1) && (y > y1 + h1 + 16) && (y < y1 + h1 + h1 + 16)) {
        this.mouseOver = 6;
      } else if ((x > x1) && (x < x1 + w1 / 2 - 8) && (y > y1 + (h1 + 16) * 3) && (y < y1 + h1 + (h1 + 16) * 3)) {
        this.mouseOver = 8;
      } else if ((x > x1 + w1 - h1 - 2) && (y > y1 + (h1 + 16) * 4) && (x < x1 + w1 - h1 - 2 + h1 - 12) && (y < y1 + (h1 + 16) * 4 + 4 + h1 - 12)) {
        this.mouseOver = 10;
      } else if ((x > this.wWidth / 20 + 49) && (y > 30) && (x < this.wWidth / 20 + 67) && (y < 48)) {
        this.mouseOver = 11;
      } else if ((x > x2) && (x < x2 + w2) && (y > y2) && (y < y2 + h2)) {
        this.mouseOver = 12;
      } else if ((x > x2) && (x < x2 + w2) && (y > y2 + h2 + 28) && (y < y2 + h2 + h2 + 28)) {
        this.mouseOver = 13;
      } else if ((x > x2) && (x < x2 + w2) && (y > y2 + (h2 + 28) * 4) && (y < this.wHeight)) {
        this.mouseOver = 14;
      } else if ((x > this.wWidth / 20 - 46 - 10) && (y > 28) && (x < 31 + this.wWidth / 20 - 46 - 10) && (y < 48)) {
        this.mouseOver = 15; } else {
        if (x > x3) if ((((x < x3 + w3 ? 1 : 0) & (y > y3 ? 1 : 0)) != 0) && (y < y3 + 40)) {
            this.mouseOver = 16;
            return;
          } if (x > x3) if ((((x < x3 + w3 ? 1 : 0) & (y > y3 + h3 + 60 ? 1 : 0)) != 0) && (y < y3 + 40 + h3 + 60)) {
            this.mouseOver = 17;
            return;
          } if ((x > tx1) && (x < tx1 + tw1) && (y > ty1) && (y < ty1 + th1)) {
          this.mouseOver = 20;
        }
        else
        {
          this.mouseOver = 0;
        }
      }
    } else if (this.view == 3) {
      int tx1 = this.wWidth - 215; int ty1 = 27; int tw1 = 23; int th1 = 23;
      if ((x > tx1) && (x < tx1 + tw1) && (y > ty1) && (y < ty1 + th1))
        this.mouseOver = 2;
      else if ((x > this.wWidth / 20 - 46 - 10) && (y > 28) && (x < 31 + this.wWidth / 20 - 46 - 10) && (y < 48)) {
        this.mouseOver = 1;
      }
      else
      {
        this.mouseOver = 0;
      }
    }
  }

  public void mouseDown(int x, int y) {
    if (this.view == 1) {
      this.mouseDown = 1;
      if ((this.mouseOver > 9) && (this.mouseOver < 50) && 
        (!this.calcs.delim(this.items[(this.mouseOver % 10)][1], (int)Math.floor((this.mouseOver - 10) / 10)).equals(""))) {
        if (this.calcs.delim(this.items[(this.mouseOver % 10)][5], (int)Math.floor((this.mouseOver - 10) / 10)).equals("y"))
          this.items[(this.mouseOver % 10)][5] = this.calcs.delimChange(this.items[(this.mouseOver % 10)][5], "n", (int)Math.floor((this.mouseOver - 10) / 10));
        else {
          this.items[(this.mouseOver % 10)][5] = this.calcs.delimChange(this.items[(this.mouseOver % 10)][5], "y", (int)Math.floor((this.mouseOver - 10) / 10));
        }
      }
    }
    else if (this.view == 2) {
      this.mouseDown = 1;
    } else if (this.view == 3) {
      this.mouseDown = 1;
    }
  }

  public void mouseUp(int x, int y) {
    if (this.view == 1) {
      int tx1 = this.wWidth - 215; int ty1 = 27; int tw1 = 23; int th1 = 23;
      if ((this.mouseOver > 9) && (this.mouseOver < 40)) {
        this.mouseOver = 40;
      } else if ((x > tx1) && (x < tx1 + tw1) && (y > ty1) && (y < ty1 + th1)) {
        resetView(3);
        this.backRemember = 0;
      } else if (this.mouseOver < 10) {
        this.v2class = this.mouseOver;
        resetView(2);
      }
      this.mouseDown = 0;
    } else if (this.view == 2) {
      int sel = (int)(this.select[3] + this.select[1]);
      int tx1 = this.wWidth - 215; int ty1 = 27; int tw1 = 23; int th1 = 23;
      if (this.mouseOver == 1) {
        this.select[0] = 1.0D;
      } else if ((y > this.calcs.v2ty + this.calcs.tbheight - 3) && (x < this.calcs.v2ip)) {
        if (!this.calcs.delim(this.items[this.v2class][1], (int)(this.select[2] + this.select[3])).equals(""))
          this.select[1] = this.select[2];
      }
      else if ((this.mouseOver > 2) && (this.mouseOver < 10)) {
        this.select[0] = (this.mouseOver - 1);
      } else if (this.mouseOver == 10) {
        if (this.calcs.delim(this.items[this.v2class][5], sel).equals("y"))
          this.items[this.v2class][5] = this.calcs.delimChange(this.items[this.v2class][5], "n", sel);
        else
          this.items[this.v2class][5] = this.calcs.delimChange(this.items[this.v2class][5], "y", sel);
      }
      else if (this.mouseOver == 2) {
        newItem();
      } else if (this.mouseOver == 11) {
        if (this.calcs.delimLength(this.items[this.v2class][1]) > 1) {
          for (int j = 1; j < 7; j++) {
            this.items[this.v2class][j] = this.calcs.delimRemoveItem(this.items[this.v2class][j], sel, j);
          }
        }
        if (sel > this.calcs.delimLength(this.items[this.v2class][1]) - 1)
          this.select[1] -= 1.0D;
      }
      else if ((this.mouseOver > 11) && (this.mouseOver < 15)) {
        this.select[0] = this.mouseOver;
      } else if (this.mouseOver == 15) {
        this.select[0] = 0.0D;
        this.view = 1;
      } else if ((this.mouseOver > 15) && (this.mouseOver < 18)) {
        this.select[0] = this.mouseOver;
      } else if ((x > tx1) && (x < tx1 + tw1) && (y > ty1) && (y < ty1 + th1)) {
        resetView(3);
        this.backRemember = (this.v2class + 1);
      }
      else
      {
        this.select[0] = 0.0D;
      }
      this.mouseDown = 0;
    } else if (this.view == 3) {
      int ty = this.calcs.tbheight + 40; int th = (this.wHeight - ty) / 7; int tw = (this.wWidth - 300) / 7;
      if ((x < this.wWidth - 300) && (y > ty + 23))
        this.select[0] = (Math.floor(x / tw) + (Math.floor((y - ty - 23) / th) - 1.0D) * 7.0D);
      else if ((x > this.wWidth / 20 - 46 - 10) && (y > 28) && (x < 31 + this.wWidth / 20 - 46 - 10) && (y < 48)) {
        if (this.backRemember == 0) {
          resetView(1);
        } else {
          resetView(2);
          this.v2class = (this.backRemember - 1);
        }

      }

      this.mouseDown = 0;
    }
  }

  public void keyDown(int k)
  {
    if (((this.OS == 1) && (k == 157)) || ((this.OS == 0) && (k == 17))) {
      this.commandDown = 1;
    }

    if (this.view == 2) {
      if (k == 27) {
        if (this.select[0] == 0.0D) {
          this.view = 1;
        } else if (this.select[0] == 1.1D) {
          this.items[this.v2class][0] = this.backup;
          this.select[0] = 0.0D;
        } else if ((Math.round(this.select[0] * 10.0D % 10.0D) == 1L) && (this.select[0] > 2.0D) && (this.select[0] < 12.0D)) {
          int i = (int)(Math.floor(this.select[0]) - 1.0D);
          this.items[this.v2class][i] = this.calcs.delimChange(this.items[this.v2class][i], this.backup, (int)(this.select[3] + this.select[1]));
          this.select[0] = 0.0D;
        } else if (Math.round(this.select[0] * 10.0D % 10.0D) == 1L) {
          if ((this.select[0] >= 12.0D) && (this.select[0] <= 15.0D)) {
            int i = (int)Math.floor(this.select[0]) - 12;
            this.items[this.v2class][7] = this.calcs.delimChange(this.items[this.v2class][7], this.backup, i);
            this.select[0] = 0.0D;
          } else if (this.select[0] > 15.0D) {
            this.dateBetween[(int)(this.select[0] - 16.0D)] = this.backup;
          }
        } else {
          this.select[0] = 0.0D;
        }
      } else if (k == 10)
      {
        if (this.select[0] == 1.0D) {
          this.backup = this.items[this.v2class][0];
          this.select[0] = 1.1D;
        } else if ((Math.round(this.select[0] * 10.0D % 10.0D) == 0L) && (this.select[0] >= 2.0D) && (this.select[0] < 12.0D)) {
          int i = (int)(Math.floor(this.select[0]) - 1.0D);
          this.backup = this.calcs.delim(this.items[this.v2class][i], (int)(this.select[3] + this.select[1]));
          this.select[0] += 0.1D;
        } else if ((Math.round(this.select[0] * 10.0D % 10.0D) == 1L) && (this.select[0] > 1.0D)) {
          this.select[0] -= 0.1D;
          this.select[0] = Math.round(this.select[0]);
        } else if (this.select[0] == 0.0D) {
          if (!this.calcs.delim(this.items[this.v2class][1], (int)(this.select[2] + this.select[3])).equals(""))
            this.select[1] = this.select[2];
        }
        else if (Math.round(this.select[0] * 10.0D % 10.0D) == 0L) {
          if ((this.select[0] >= 12.0D) && (this.select[0] <= 15.0D)) {
            int i = (int)Math.floor(this.select[0]) - 12;
            this.backup = this.calcs.delim(this.items[this.v2class][7], i);
            this.select[0] += 0.1D;
          } else if (this.select[0] > 15.0D) {
            this.backup = this.dateBetween[(int)(this.select[0] - 16.0D)];
            this.select[0] += 0.1D;
          }
        }
      } else if (k == 9) {
        if (this.select[0] == 0.0D)
          this.select[0] = 1.0D;
        else if ((this.select[0] == 1.0D) || (this.select[0] == 1.1D))
          this.select[0] = 2.0D;
        else if ((this.select[0] == 2.0D) || (this.select[0] == 2.1D))
          this.select[0] = 3.0D;
        else if ((this.select[0] == 3.0D) || (this.select[0] == 3.1D))
          this.select[0] = 4.0D;
        else if ((this.select[0] == 4.0D) || (this.select[0] == 4.1D))
          this.select[0] = 5.0D;
        else if ((this.select[0] == 5.0D) || (this.select[0] == 5.1D))
          this.select[0] = 7.0D;
        else if ((this.select[0] == 7.0D) || (this.select[0] == 7.1D))
          this.select[0] = 12.0D;
        else if ((this.select[0] == 12.0D) || (this.select[0] == 12.1D))
          this.select[0] = 13.0D;
        else if ((this.select[0] == 13.0D) || (this.select[0] == 13.1D))
          this.select[0] = 14.0D;
        else if ((this.select[0] == 14.0D) || (this.select[0] == 14.1D))
          this.select[0] = 16.0D;
        else if ((this.select[0] == 16.0D) || (this.select[0] == 16.100000000000001D))
          this.select[0] = 17.0D;
        else if ((this.select[0] == 17.0D) || (this.select[0] == 17.100000000000001D))
          this.select[0] = 0.0D;
      }
      else if (k == 8) {
        if (this.select[0] == 1.1D) {
          if (this.shiftDown == 1) {
            if (this.items[this.v2class][0].length() > 1) {
              this.items[this.v2class][0] = " ";
            }
          }
          else if (this.items[this.v2class][0].length() > 1) {
            this.items[this.v2class][0] = this.items[this.v2class][0].substring(0, this.items[this.v2class][0].length() - 1);
          }
        }
        else if ((Math.round(this.select[0] * 10.0D % 10.0D) == 1L) && (this.select[0] > 2.0D) && (this.select[0] < 12.0D)) {
          int i = (int)(Math.floor(this.select[0]) - 1.0D);
          if (this.shiftDown == 1)
            this.items[this.v2class][i] = this.calcs.delimChange(this.items[this.v2class][i], " ", (int)(this.select[3] + this.select[1]));
          else
            this.items[this.v2class][i] = this.calcs.delimDelete(this.items[this.v2class][i], (int)(this.select[3] + this.select[1]));
        }
        else if (Math.round(this.select[0] * 10.0D % 10.0D) == 1L) {
          if ((this.select[0] >= 12.0D) && (this.select[0] <= 15.0D)) {
            int i = (int)Math.floor(this.select[0]) - 12;
            if (this.shiftDown == 1)
              this.items[this.v2class][7] = this.calcs.delimChange(this.items[this.v2class][7], " ", i);
            else
              this.items[this.v2class][7] = this.calcs.delimDelete(this.items[this.v2class][7], i);
          }
          else if (this.select[0] > 15.0D) {
            if (this.shiftDown == 1) {
              this.dateBetween[(int)(this.select[0] - 16.0D)] = "";
            }
            else if (!this.dateBetween[(int)(this.select[0] - 16.0D)].equals("")) {
              this.dateBetween[(int)(this.select[0] - 16.0D)] = this.dateBetween[(int)(this.select[0] - 16.0D)].substring(0, this.dateBetween[(int)(this.select[0] - 16.0D)].length() - 1);
            }
          }
        }
      }
      else if (k == 37) {
        this.select[0] = 0.0D;
        if (this.select[0] == 0.0D) {
          if (this.v2class > 0)
            this.v2class -= 1;
          else {
            this.v2class = 9;
          }
          resetView(2);
        }
      } else if (k == 39) {
        this.select[0] = 0.0D;
        if (this.select[0] == 0.0D) {
          if (this.v2class < 9)
            this.v2class += 1;
          else {
            this.v2class = 0;
          }
          resetView(2);
        }
      } else if (k == 38) {
        this.select[0] = 0.0D;
        if (this.select[0] == 0.0D) {
          if (this.select[2] > 0.0D) {
            this.select[2] -= 1.0D;
          } else if (this.select[3] > 0.0D) {
            this.select[3] -= 1.0D;
            this.select[1] += 1.0D;
          }
        }
        check(this.select[2]);
      } else if (k == 40) {
        this.select[0] = 0.0D;
        if (this.select[0] == 0.0D) {
          if (this.select[2] < 5.0D) {
            this.select[2] += 1.0D;
          } else if (this.select[3] < this.calcs.delimLength(this.items[this.v2class][1]) - 6) {
            this.select[3] += 1.0D;
            this.select[1] -= 1.0D;
          }
        }
        check(this.select[3]);
      } else if (k == 16) {
        this.shiftDown = 1;
      }

      if ((this.select[0] == 1.1D) && 
        (this.items[this.v2class][0].length() < 30)) {
        if ((k > 64) && (k < 91)) {
          if (this.shiftDown == 1)
          {
            int tmp2207_2206 = 0;
            String[] tmp2207_2205 = this.items[this.v2class]; tmp2207_2205[tmp2207_2206] = (tmp2207_2205[tmp2207_2206] + (char)k);
          }
          else
          {
            int tmp2242_2241 = 0;
            String[] tmp2242_2240 = this.items[this.v2class]; tmp2242_2240[tmp2242_2241] = (tmp2242_2240[tmp2242_2241] + (char)(k + 32));
          }
        } else if ((k > 47) && (k < 58) && (this.shiftDown == 1)) {
          if (k == 48)
          {
            int tmp2306_2305 = 0;
            String[] tmp2306_2304 = this.items[this.v2class]; tmp2306_2304[tmp2306_2305] = (tmp2306_2304[tmp2306_2305] + ')');
          }
          else
          {
            int tmp2341_2340 = 0;
            String[] tmp2341_2339 = this.items[this.v2class]; tmp2341_2339[tmp2341_2340] = (tmp2341_2339[tmp2341_2340] + (char)(k - 17));
          }
        } else if ((k != 10) && (k != 8) && (k != 16))
        {
          int tmp2397_2396 = 0;
          String[] tmp2397_2395 = this.items[this.v2class]; tmp2397_2395[tmp2397_2396] = (tmp2397_2395[tmp2397_2396] + (char)k);
        }

      }

      int sel = (int)(this.select[3] + this.select[1]);

      if ((this.select[0] == 2.1D) || (this.select[0] == 3.1D)) {
        int i = (int)(Math.floor(this.select[0]) - 1.0D);
        int n = 0;
        if (this.select[0] == 2.1D)
          n = 55;
        else if (this.select[0] == 3.1D) {
          n = 500;
        }
        if (this.commandDown == 1) {
          if (k == 86) {
            String cc = this.calcs.ctrlv(getClipboardContents());
            int la = n - this.calcs.delim(this.items[this.v2class][i], sel).length();
            if (cc.length() < la)
              this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], cc, sel);
            else
              this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], cc.substring(0, la), sel);
          }
          else if (k == 67) {
            StringSelection ss = new StringSelection(this.calcs.delim(this.items[this.v2class][i], sel).substring(1, this.calcs.delim(this.items[this.v2class][i], sel).length()));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(ss, this);
          } else if (k == 88) {
            StringSelection ss = new StringSelection(this.calcs.delim(this.items[this.v2class][i], sel).substring(1, this.calcs.delim(this.items[this.v2class][i], sel).length()));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(ss, this);
            this.items[this.v2class][i] = this.calcs.delimChange(this.items[this.v2class][i], " ", (int)(this.select[3] + this.select[1]));
          }
        }
        else if (this.calcs.delim(this.items[this.v2class][i], sel).length() < n) {
          if ((k > 64) && (k < 91)) {
            if (this.shiftDown == 1)
              this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], String.valueOf((char)k), sel);
            else
              this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], String.valueOf((char)(k + 32)), sel);
          }
          else if ((k > 47) && (k < 58) && (this.shiftDown == 1)) {
            if (k == 48)
              this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], String.valueOf(')'), sel);
            else
              this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], String.valueOf((char)(k - 17)), sel);
          }
          else if ((k != 10) && (k != 8) && (k != 16)) {
            this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], String.valueOf((char)k), sel);
          }

        }

      }

      if ((this.select[0] == 4.1D) || (this.select[0] == 5.1D)) {
        int i = (int)(Math.floor(this.select[0]) - 1.0D);
        int n = 11;
        if ((this.calcs.delim(this.items[this.v2class][i], sel).length() < n) && 
          (k > 46) && (k < 58)) {
          this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], String.valueOf((char)k), sel);
        }

      }

      if (this.select[0] == 7.1D) {
        int i = (int)(Math.floor(this.select[0]) - 1.0D);
        int n = 7;
        if ((this.calcs.delim(this.items[this.v2class][i], sel).length() < n) && (
          ((k > 47) && (k < 58)) || (k == 46))) {
          this.items[this.v2class][i] = this.calcs.delimAdd(this.items[this.v2class][i], String.valueOf((char)k), sel);
        }

      }

      if (this.select[0] == 12.1D) {
        int i = (int)Math.floor(this.select[0]) - 12;
        int n = 30;
        if (this.calcs.delim(this.items[this.v2class][7], i).length() < n) {
          if ((k > 64) && (k < 91)) {
            if (this.shiftDown == 1)
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)k), i);
            else
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)(k + 32)), i);
          }
          else if ((k > 47) && (k < 58) && (this.shiftDown == 1)) {
            if (k == 48)
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf(')'), i);
            else
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)(k - 17)), i);
          }
          else if ((k != 10) && (k != 8) && (k != 16)) {
            this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)k), i);
          }
        }
      }

      if (this.select[0] == 13.1D) {
        int i = (int)Math.floor(this.select[0]) - 12;
        int n = 4;
        if ((this.calcs.delim(this.items[this.v2class][7], i).length() < n) && 
          (k > 47) && (k < 58)) {
          this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)k), i);
        }

      }

      if (this.select[0] == 14.1D) {
        int i = (int)Math.floor(this.select[0]) - 12;
        int n = 700;
        if (this.commandDown == 1) {
          if (k == 86) {
            String cc = this.calcs.ctrlv(getClipboardContents());
            int la = n - this.calcs.delim(this.items[this.v2class][i], sel).length();
            if (cc.length() < la)
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], cc, i);
            else
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], cc.substring(0, la), i);
          }
          else if (k == 67) {
            StringSelection ss = new StringSelection(this.calcs.delim(this.items[this.v2class][7], i).substring(1, this.calcs.delim(this.items[this.v2class][7], i).length()));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(ss, this);
          } else if (k == 88) {
            StringSelection ss = new StringSelection(this.calcs.delim(this.items[this.v2class][7], i).substring(1, this.calcs.delim(this.items[this.v2class][7], i).length()));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(ss, this);
            this.items[this.v2class][7] = this.calcs.delimChange(this.items[this.v2class][7], " ", i);
          }
        }
        else if (this.calcs.delim(this.items[this.v2class][7], i).length() < n) {
          if ((k > 64) && (k < 91)) {
            if (this.shiftDown == 1)
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)k), i);
            else
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)(k + 32)), i);
          }
          else if ((k > 47) && (k < 58) && (this.shiftDown == 1)) {
            if (k == 48)
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf(')'), i);
            else
              this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)(k - 17)), i);
          }
          else if ((k != 10) && (k != 8) && (k != 16)) {
            this.items[this.v2class][7] = this.calcs.delimAdd(this.items[this.v2class][7], String.valueOf((char)k), i);
          }
        }

      }

      if ((this.select[0] == 17.100000000000001D) || (this.select[0] == 16.100000000000001D)) {
        int i = (int)(Math.floor(this.select[0]) - 16.0D);
        int n = 11;
        if ((this.dateBetween[i].length() < n) && 
          (k > 46) && (k < 58))
        {
          int tmp4546_4545 = i;
          String[] tmp4546_4542 = this.dateBetween; tmp4546_4542[tmp4546_4545] = (tmp4546_4542[tmp4546_4545] + String.valueOf((char)k));
        }
      }

    }
    else if (this.view == 3) {
      if (k == 37)
        try {
          this.cDate = this.sdf.parse(this.calcs.changeMonth(this.cDate, -1));
        }
        catch (Exception localException) {
        }
      else if (k == 39)
        try {
          this.cDate = this.sdf.parse(this.calcs.changeMonth(this.cDate, 1));
        }
        catch (Exception localException1) {
        }
      else if (k == 27)
        if (this.backRemember == 0) {
          resetView(1);
        } else {
          resetView(2);
          this.v2class = (this.backRemember - 1);
        }
    }
  }

  public void keyUp(int k)
  {
    if (((this.OS == 1) && (k == 157)) || ((this.OS == 0) && (k == 17))) {
      this.commandDown = 0;
    }
    if (k == 16)
      this.shiftDown = 0;
  }

  public void lostOwnership(Clipboard arg0, Transferable arg1)
  {
  }

  public String getClipboardContents()
  {
    String result = "";
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    Transferable contents = clipboard.getContents(null);
    boolean hasTransferableText = 
      (contents != null) && 
      (contents.isDataFlavorSupported(DataFlavor.stringFlavor));

    if (hasTransferableText) {
      try {
        result = (String)contents.getTransferData(DataFlavor.stringFlavor);
      }
      catch (UnsupportedFlavorException ex)
      {
        System.out.println(ex);
        ex.printStackTrace();
      }
      catch (IOException ex) {
        System.out.println(ex);
        ex.printStackTrace();
      }
    }
    return result;
  }
}