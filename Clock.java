import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.time.LocalTime;

public class Clock extends JPanel implements ActionListener { 
    /** The class that represents the clock.
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int minInHour = 60;

    public static final int N_INT_PARAMETERS = 10;

    public static final String DEF_FONT_NAME = "Courier";
    public static final int DEF_FONT_SIZE = 14;
    public static final Color DEF_BACKGROUD_COLOR = Color.BLACK;
    public static final Color DEF_FOREGROUD_COLOR = Color.WHITE;
    public static final int DEF_CLOCK_THICKNESS = 5;
    public static final int DEF_BORDER_X = 10;
    public static final int DEF_BORDER_Y = 10;
    public static final int DEF_BORDER_R = 20;
    public static final int DEF_CPHOUR_RATIO = 30;
    public static final int DEF_CPMINUTE_RATIO = 50;
    public static final int DEF_CPSECOND_RATIO = 60;
    public static final int DEF_CPHOUR_THICKNESS = 5;
    public static final int DEF_CPMINUTE_THICKNESS = 5;
    public static final int DEF_CPSECOND_THICKNESS = 2;
    public static final Color DEF_CPHOUR_COLOR = Color.WHITE;
    public static final Color DEF_CPMINUTE_COLOR = Color.WHITE;
    public static final Color DEF_CPSECOND_COLOR = Color.RED;

    private LocalTime time;
    private final Timer timer = new Timer(1000, this);
    private int clockThickness;
    private int borderX;
    private int borderY;
    private int borderR;
    private int cpHourRatio;
    private int cpMinuteRatio;
    private int cpSecondRatio;
    private int cpHourThickness;
    private int cpMinuteThickness;
    private int cpSecondThickness;

    private Color background;
    private Color foreground;
    private Color cpMinuteColor;
    private Color cpHourColor;
    private Color cpSecondColor;
    private int fontsize;
    private Font font;

    public Clock() {
        time = LocalTime.now();
        timer.start();
        clockThickness = DEF_CLOCK_THICKNESS;
        borderX = DEF_BORDER_X;
        borderY = DEF_BORDER_Y;
        borderR = DEF_BORDER_R;
        cpHourRatio = DEF_CPHOUR_RATIO;
        cpMinuteRatio = DEF_CPMINUTE_RATIO;
        cpSecondRatio = DEF_CPSECOND_RATIO;
        cpHourThickness = DEF_CPHOUR_THICKNESS;
        cpMinuteThickness = DEF_CPMINUTE_THICKNESS;
        cpSecondThickness = DEF_CPSECOND_THICKNESS;
        background = DEF_BACKGROUD_COLOR;
        foreground = DEF_FOREGROUD_COLOR;
        cpHourColor = DEF_CPHOUR_COLOR;
        cpMinuteColor = DEF_CPMINUTE_COLOR;
        cpSecondColor = DEF_CPSECOND_COLOR;
        fontsize = DEF_FONT_SIZE;
        font = new Font(DEF_FONT_NAME, Font.PLAIN, DEF_FONT_SIZE);
    }

    public void paintComponent(final Graphics g) {
        final int x = this.getWidth();
        final int y = this.getHeight();
        final int xc = x / 2;
        final int yc = y / 2;
        
        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(background);
        g2.fillRect(0, 0, x, y);
        
        g2.setStroke(new BasicStroke(clockThickness));
        g2.setColor(foreground);
        g2.drawOval(borderX, borderY, x-2*borderX, y-2*borderY);

        final int a, b;
        if (x > y) {    a = x / 2 - borderX;
                        b = y / 2 - borderY;  }
        else        {   a = y / 2 - borderY;
                        b = x / 2 - borderX;  }

        g2.setFont(font);
        final FontMetrics metrics = g.getFontMetrics();
        final double e = Math.sqrt( a*a - b*b ) / (double) a;
        for (int i = 0; i < 12; i++) {
            final String s = String.valueOf((i+2)%12 +1);

            final double theta = 2*Math.PI * i / 12;
            final double cosTheta = Math.cos(theta);
            final double r = Math.sqrt((double) b*b / (1.0 - e*e*cosTheta*cosTheta) ) - borderR;

            int xs = (int) Math.round(r*cosTheta) + xc;
            int ys = (int) Math.round(r*Math.sin(theta)) + yc;

            //centering the labels
            xs -= metrics.stringWidth(s) / 2;

            g2.drawString(s, xs, ys);
        }

        time = LocalTime.now();
        final int hour = time.getHour();
        final int minute = time.getMinute();
        final int second = time.getSecond();
        final double theta_h = angleOfTime(5*hour + 5*minute / minInHour);
        final double theta_m = angleOfTime(minute);
        final double theta_s = angleOfTime(second);

        final double cosTheta_h = Math.cos(theta_h);
        final double cosTheta_m = Math.cos(theta_m);
        final double cosTheta_s = Math.cos(theta_s);
        final double r_h = Math.sqrt((double) b*b /
            (1.0 - e*e*cosTheta_h*cosTheta_h) );
        final double r_m = Math.sqrt((double) b*b /
            (1.0 - e*e*cosTheta_m*cosTheta_m) );
        final double r_s = Math.sqrt((double) b*b /
            (1.0 - e*e*cosTheta_s*cosTheta_s) );
        

        final int[] xph = colckPointerX(theta_h, xc, cpHourThickness,
            (r_h * cpHourRatio) / 100);
        final int[] yph = colckPointerY(theta_h, yc, cpHourThickness,
            (r_h * cpHourRatio) / 100);
        final int[] xpm = colckPointerX(theta_m, xc, cpMinuteThickness,
            (r_m * cpMinuteRatio) / 100);
        final int[] ypm = colckPointerY(theta_m, yc, cpMinuteThickness,
            (r_m * cpMinuteRatio) / 100);
        final int[] xps = colckPointerX(theta_s, xc, cpSecondThickness,
            (r_s * cpSecondRatio) / 100);
        final int[] yps = colckPointerY(theta_s, yc, cpSecondThickness,
            (r_s * cpSecondRatio) / 100);

        g2.setColor(cpHourColor);
        g2.fillPolygon(xph, yph, 3);

        g2.setColor(cpMinuteColor);
        g2.fillPolygon(xpm, ypm, 3);

        g2.setColor(cpSecondColor);
        g2.fillPolygon(xps, yps, 3);
        
    }
    
    public void setForegroundColor(Color c) {   foreground = c;   }
    public void setBackgroundColor(Color c) {   background = c;   }
    public void setCPHourColor(Color c)     {   cpHourColor = c;  }
    public void setCPMinuteColor(Color c)   {   cpMinuteColor = c;  }
    public void setCPSecondColor(Color c)   {   cpSecondColor = c;  }

    public void setClockThickness(int value)    {   clockThickness = value;  }
    public void setBorderR(int value)           {   borderR = value;  }
    public void setBorderX(int value)           {   borderX = value;  }
    public void setBorderY(int value)           {   borderY = value;  }
    public void setCPHourRatio(int value)       {   cpHourRatio = value;  }
    public void setCPMinuteRatio(int value)     {   cpMinuteRatio = value;  }
    public void setCPSecondRatio(int value)     {   cpSecondRatio = value;  }
    public void setCPHourThickness(int value)   {   cpHourThickness = value;  }
    public void setCPMinuteThickness(int value) {   cpMinuteThickness = value;  }
    public void setCPSecondThickness(int value) {   cpSecondThickness = value;  }

    public String fontName()    {   return font.getFontName();      }
    public int fontSize()       {   return fontsize;                }

    public void setFont(String fontName, int fontSize) {
        try {
            this.fontsize = fontSize;
            Font font2 = new Font(fontName, Font.PLAIN, fontSize);
            font = font2;
        }
        catch(NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error catched : " + 
               "cannot create font" + e, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private static double angleOfTime(int min) {
        /** Converts the position on min to a an angle, measured
         *  couterclockwise, theta = 0 corresponds to the
         *  horizontal right.
         */
        final int min2 = (15 + minInHour - min) % minInHour;
        return (2*Math.PI *(double) min2 / (double) minInHour);
    }

    private static int[] colckPointerX(double theta, int xc, int cpThickness,
        double cpLength) {

        double theta2 = theta + Math.PI / 2;

        final int[] r = { xc + (int) Math.round(cpThickness * Math.cos(theta2) / 2.0),
                      xc + (int) Math.round(cpLength * Math.cos(theta)),
                      xc + (int) Math.round(-cpThickness * Math.cos(theta2) / 2.0)     };
        return r;
    }

    private static int[] colckPointerY(double theta, int yc, int cpThickness,
        double cpLength) {

        double theta2 = theta + Math.PI / 2;

        final int[] r = { yc - (int) Math.round(cpThickness * Math.sin(theta2) / 2.0),
                      yc - (int) Math.round(cpLength * Math.sin(theta)),
                      yc - (int) Math.round(-cpThickness * Math.sin(theta2) / 2.0)     };
        return r;
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}