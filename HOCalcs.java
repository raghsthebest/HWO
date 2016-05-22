import java.text.SimpleDateFormat;
import java.util.Date;

public class HOCalcs
{
  int width;
  int height;
  int tbheight;
  int mx;
  int my;
  int mdx;
  int mdy;
  int v1cw;
  int v1ch;
  int v2ty;
  int v2hs;
  int v2ip;
  int v2fp;
  int v2ih;
  int checking = 0;

  public HOCalcs(int w, int h)
  {
    this.width = w;
    this.height = h;
    this.tbheight = (h / 25);

    this.v1cw = (this.width / 5);
    this.v1ch = ((this.height - this.height / 25) / 2);

    this.v2ty = ((int)(this.tbheight * 1.5D) + this.tbheight + 1);
    this.v2hs = ((this.height - ((int)(this.tbheight * 1.5D) + this.tbheight * 2) - 5) / 2 + ((int)(this.tbheight * 1.5D) + this.tbheight + 1) + 4);
    this.v2ip = (this.width / 5 + 50);
    this.v2fp = (this.width / 10 * 7);
    this.v2ih = ((this.height - this.tbheight - this.v2ty + 7) / 6);
  }

  public int v1class(int mx, int my)
  {
    int modx = mx % this.v1cw;
    int mody = my % this.v1ch;

    double x = Math.floor(mx / this.v1cw);
    double y = 5.0D * Math.floor(my / this.v1ch);

    if ((modx > this.v1cw - 35) && (modx < this.v1cw - 20) && (mody > this.tbheight * 3) && (mody < this.tbheight * 3 + 38 - 23))
      return (int)((int)x + y) + 10;
    if ((modx > this.v1cw - 35) && (modx < this.v1cw - 20) && (mody > this.tbheight * 6) && (mody < this.tbheight * 6 + 38 - 23))
      return (int)((int)x + y) + 20;
    if ((modx > this.v1cw - 35) && (modx < this.v1cw - 20) && (mody > this.tbheight * 9) && (mody < this.tbheight * 9 + 38 - 23)) {
      return (int)((int)x + y) + 30;
    }
    return (int)((int)x + y);
  }

  public int delimLength(String s)
  {
    String[] s1 = s.split("``");
    return s1.length;
  }

  public String delim(String s, int i) {
    String[] s1 = s.split("``");
    if (s1.length > i) {
      return s1[(s1.length - i - 1)];
    }
    return "";
  }

  public String delimChange(String s1, String s2, int i)
  {
    String[] s3 = s1.split("``");
    String s4 = new String("");
    if (s3.length > i) {
      s3[(s3.length - i - 1)] = s2;
      for (int j = 0; j < s3.length; j++) {
        s4 = s4 + s3[j] + "``";
      }
      return s4;
    }
    return "";
  }

  public String delimAdd(String s1, String s2, int i)
  {
    String[] s3 = s1.split("``");
    String s4 = new String("");
    if (s3.length > i)
    {
      int tmp35_34 = (s3.length - i - 1);
      String[] tmp35_26 = s3; tmp35_26[tmp35_34] = (tmp35_26[tmp35_34] + s2);
      for (int j = 0; j < s3.length; j++) {
        s4 = s4 + s3[j] + "``";
      }
      return s4;
    }
    return "";
  }

  public String delimDelete(String s1, int i)
  {
    String[] s2 = s1.split("``");
    String s3 = new String("");
    if (s2.length > i) {
      if (s2[(s2.length - i - 1)].length() > 1) {
        s2[(s2.length - i - 1)] = s2[(s2.length - i - 1)].substring(0, s2[(s2.length - i - 1)].length() - 1);
      }
      for (int j = 0; j < s2.length; j++) {
        s3 = s3 + s2[j] + "``";
      }
      return s3;
    }
    return "";
  }

  public String delimRemoveItem(String s1, int i, int k)
  {
    String[] s2 = s1.split("``");
    String s3 = new String("");
    for (int j = 0; j < s2.length; j++) {
      if (j != s2.length - i - 1) {
        s3 = s3 + s2[j] + "``";
      }
    }

    return s3;
  }

  public String daysLeft(Date d1, Date d2)
  {
    SimpleDateFormat dd = new SimpleDateFormat("dd");
    SimpleDateFormat mm = new SimpleDateFormat("MM");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    String s = "";

    if (yy.format(d1).equals(yy.format(d2))) {
      if (mm.format(d1).equals(mm.format(d2))) {
        if (dd.format(d1).equals(dd.format(d2))) {
          s = "Due Today";
        } else {
          int left = Integer.parseInt(dd.format(d1).toString()) - Integer.parseInt(dd.format(d2).toString());
          if (left > 0) {
            if (left == 1)
              s = "1 Day Left";
            else {
              s = left + " Days Left";
            }
          }
          else if (left == -1)
            s = "1 Day Overdue";
          else
            s = Math.abs(left) + " Days Overdue";
        }
      }
      else
      {
        int left = Integer.parseInt(mm.format(d1).toString()) - Integer.parseInt(mm.format(d2).toString());
        if (left > 0) {
          if (left == 1)
            s = "1 Month Left";
          else {
            s = left + " Months Left";
          }
        }
        else if (left == -1)
          s = "1 Month Overdue";
        else
          s = Math.abs(left) + " Months Overdue";
      }
    }
    else
    {
      int left = Integer.parseInt(yy.format(d1).toString()) - Integer.parseInt(yy.format(d2).toString());
      if (left > 0) {
        if (left == 1)
          s = "1 Year Left";
        else {
          s = left + " Years Left";
        }
      }
      else if (left == -1)
        s = "1 Year Overdue";
      else {
        s = Math.abs(left) + " Years Overdue";
      }
    }

    return s;
  }

  public String firstDay(Date d1)
  {
    SimpleDateFormat dd = new SimpleDateFormat("dd");
    SimpleDateFormat mm = new SimpleDateFormat("MM");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    String s = "01/";
    s = s + mm.format(d1) + "/";
    s = s + yy.format(d1);

    return s;
  }

  public String changeMonth(Date d1, int i)
  {
    SimpleDateFormat dd = new SimpleDateFormat("dd");
    SimpleDateFormat mm = new SimpleDateFormat("MM");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    String s = "01/";

    if (i == 1) {
      if (mm.format(d1).equals("12"))
        s = s + "01/" + (Integer.parseInt(yy.format(d1)) + 1);
      else
        s = s + (Integer.parseInt(mm.format(d1)) + 1) + "/" + yy.format(d1);
    }
    else if (i == -1) {
      if (mm.format(d1).equals("01"))
        s = s + "12/" + (Integer.parseInt(yy.format(d1)) - 1);
      else {
        s = s + (Integer.parseInt(mm.format(d1)) - 1) + "/" + yy.format(d1);
      }
    }

    return s;
  }

  public String changeDay(Date d1, int i)
  {
    SimpleDateFormat dd = new SimpleDateFormat("dd");
    SimpleDateFormat mmyy = new SimpleDateFormat("MM/yyyy");

    String s = "";

    if (i == 1)
      s = s + (Integer.parseInt(dd.format(d1)) + 1) + "/" + mmyy.format(d1);
    else if (i == -1) {
      s = s + (Integer.parseInt(dd.format(d1)) - 1) + "/" + mmyy.format(d1);
    }

    return s;
  }

  public int dateCompare(Date d1, Date d2)
  {
    SimpleDateFormat dd = new SimpleDateFormat("dd");
    SimpleDateFormat mm = new SimpleDateFormat("MM");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    if (yy.format(d1).equals(yy.format(d2))) {
      if (mm.format(d1).equals(mm.format(d2))) {
        if (dd.format(d1).equals(dd.format(d2))) {
          return 0;
        }
        int left = Integer.parseInt(dd.format(d1).toString()) - Integer.parseInt(dd.format(d2).toString());
        if (left > 0) {
          return 1;
        }
        return -1;
      }

      int left = Integer.parseInt(mm.format(d1).toString()) - Integer.parseInt(mm.format(d2).toString());
      if (left > 0) {
        return 1;
      }
      return -1;
    }

    int left = Integer.parseInt(yy.format(d1).toString()) - Integer.parseInt(yy.format(d2).toString());
    if (left > 0) {
      return 1;
    }
    return -1;
  }

  public int longDate(String s1, String s2)
  {
    String[] s3 = s1.split("``");
    String[] s4 = s2.split("``");
    Date[] d1 = new Date[s3.length];
    Date[] d2 = new Date[s3.length];
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    long hDiff = 0L;

    for (int i = 0; i < s3.length; i++) {
      try {
        d1[i] = sdf.parse(s3[i].substring(1, s3[i].length() - 1));
        d2[i] = sdf.parse(s4[i].substring(1, s4[i].length() - 1));

        if (d2[i].getTime() - d1[i].getTime() > hDiff) {
          hDiff = d2[i].getTime() - d1[i].getTime();
        }
      }
      catch (Exception localException)
      {
      }
    }
    return (int)(hDiff / 86400000L);
  }

  public String lGrade(String s)
  {
	  
	int g;
    if (s.length() < 2) {
      return " ";
    }

    try
    {
      g = (int)Math.round(Double.parseDouble(s.substring(1, s.length())));
    }
    catch (Exception e)
    {
      return " ";
    }
    
    String s1 = new String(" ");
    if (g >= 100) {
      s1 = s1 + "A+";
      return s1;
    }

    if (g <= 59) {
      s1 = s1 + "F";
      return s1;
    }

    if (g >= 90)
      s1 = s1 + "A";
    else if (g >= 80)
      s1 = s1 + "B";
    else if (g >= 70)
      s1 = s1 + "C";
    else {
      s1 = s1 + "D";
    }

    if (g % 10 >= 8)
      return s1 + "+";
    if (g % 10 >= 4) {
      return s1;
    }
    return s1 + "-";
  }

  public String ctrlv(String s1)
  {
    String[] s2 = s1.split(" ");
    String s3 = "";
    for (int i = 0; i < s2.length; i++) {
      if ((!s2[i].contains("``")) && (!s2[i].contains("~~"))) {
        s3 = s3 + s2[i] + " ";
      }
    }
    return s3;
  }

  public int v2imo(int i)
  {
    return (int)Math.floor(i / this.v2ih);
  }
}