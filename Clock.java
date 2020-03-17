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
    public static final boolean DEF_SHOW_SECONDS = true;
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
    private boolean showSeconds = DEF_SHOW_SECONDS;
    private int borderX;
    private int borderY;
    private int borderR;

    private ClockPointer hour = new ClockPointer(DEF_CPHOUR_RATIO,
        DEF_CPHOUR_THICKNESS, DEF_CPHOUR_COLOR);
    private ClockPointer minute = new ClockPointer(DEF_CPMINUTE_RATIO,
        DEF_CPMINUTE_THICKNESS, DEF_CPMINUTE_COLOR);
    private ClockPointer second = new ClockPointer(DEF_CPSECOND_RATIO,
        DEF_CPSECOND_THICKNESS, DEF_CPSECOND_COLOR);

    private Color background;
    private Color foreground;
    private int fontsize;
    private Font font;

    public Clock() {
        time = LocalTime.now();
        timer.start();
        clockThickness = DEF_CLOCK_THICKNESS;
        borderX = DEF_BORDER_X;
        borderY = DEF_BORDER_Y;
        borderR = DEF_BORDER_R;
        background = DEF_BACKGROUD_COLOR;
        foreground = DEF_FOREGROUD_COLOR;
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
        
        
        if (showSeconds) {
        }
        
    }
    
    public void setForegroundColor(Color c) {   foreground = c;   }
    public void setBackgroundColor(Color c) {   background = c;   }
    
    public void setClockThickness(int value)    {   clockThickness = value;  }
    public void setBorderR(int value)           {   borderR = value;  }
    public void setBorderX(int value)           {   borderX = value;  }
    public void setBorderY(int value)           {   borderY = value;  }
    
    public void setCPHourColor(Color c)         {   hour.setColor(c);    }
    public void setCPMinuteColor(Color c)       {   minute.setColor(c);  }
    public void setCPSecondColor(Color c)       {   second.setColor(c);  }
    public void setCPHourRatio(int value)       {   hour.setRatio(value);    }
    public void setCPMinuteRatio(int value)     {   minute.setRatio(value);  }
    public void setCPSecondRatio(int value)     {   second.setRatio(value);  }
    public void setCPHourThickness(int value)   {   hour.setThickness(value);    }
    public void setCPMinuteThickness(int value) {   minute.setThickness(value);  }
    public void setCPSecondThickness(int value) {   second.setThickness(value);  }

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

    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}