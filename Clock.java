import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
import javax.swing.JPanel;
import java.time.LocalTime;
 
public class Clock extends JPanel { 
    /** The class that represents the clock.
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int minInHour = 60;

    private LocalTime time;
    private int clockThickness;
    private int borderX;
    private int borderY;
    private int borderR;
    private int cpHRatio;
    private int cpMRatio;
    private int cpHThickness;
    private int cpMThickness;

    private Color background;
    private Color foreground;
    private Color cpMinuteColor;
    private Color cpHourColor;
    private int fontsize;
    private Font font;

    public Clock() {
        time = LocalTime.now();
        clockThickness = 5;
        borderX = 10;
        borderY = 10;
        borderR = 20;
        cpHRatio = 30;
        cpMRatio = 50;
        cpHThickness = 5;
        cpMThickness = 5;
        background = Color.BLACK;
        foreground = Color.WHITE;
        cpHourColor = Color.WHITE;
        cpMinuteColor = Color.WHITE;
        fontsize = 14;
        font = new Font("Calibri", Font.PLAIN, fontsize);
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
        final double theta_h = angleOfTime(5*hour + 5*minute / minInHour);
        final double theta_m = angleOfTime(minute);
        final double cosTheta_h = Math.cos(theta_h);
        final double cosTheta_m = Math.cos(theta_m);
        final int r_h = (int) Math.round(
            Math.sqrt((double) b*b / (1.0 - e*e*cosTheta_h*cosTheta_h) ));
        final int r_m = (int) Math.round(
            Math.sqrt((double) b*b / (1.0 - e*e*cosTheta_m*cosTheta_m) ));
        

        final int[] xph = colckPointerX(theta_h, xc, cpHThickness, (r_h * cpHRatio) / 100);
        final int[] yph = colckPointerY(theta_h, yc, cpHThickness, (r_h * cpHRatio) / 100);
        final int[] xpm = colckPointerX(theta_m, xc, cpMThickness, (r_m * cpMRatio) / 100);
        final int[] ypm = colckPointerY(theta_m, yc, cpMThickness, (r_m * cpMRatio) / 100);

        g2.setColor(cpHourColor);
        g2.fillPolygon(xph, yph, 3);

        g2.setColor(cpMinuteColor);
        g2.fillPolygon(xpm, ypm, 3);
        
    }
    
    public void setForegroundColor(Color c) {   foreground = c;
        System.out.println("foreground = " + c);     }
    public void setBackgroundColor(Color c) {   background = c;
        System.out.println("background = " + c);          }
    public void setCPHourColor(Color c)     {   cpHourColor = c;
        System.out.println("cph = " + c + " vs " + cpHourColor);         }
    public void setCPMinuteColor(Color c)   {   cpMinuteColor = c;
        System.out.println("cpm = " + c + " vs " + cpMinuteColor);       }
    
    private static double angleOfTime(int min) {
        /** Converts the position on min to a an angle, measured
         *  couterclockwise, theta = 0 corresponds to the
         *  horizontal right.
         */
        final int min2 = (15 + minInHour - min) % minInHour;
        return (2*Math.PI *(double) min2 / (double) minInHour);
    }

    private static int[] colckPointerX(double theta, int xc, int cpThickness, int cpLength) {
        double theta2 = theta + Math.PI / 2;

        final int[] r =   { xc + (int) Math.round(cpThickness * Math.cos(theta2) / 2.0),
                            xc + (int) Math.round(cpLength * Math.cos(theta)),
                            xc + (int) Math.round(-cpThickness * Math.cos(theta2) / 2.0)     };
        return r;
    }

    private static int[] colckPointerY(double theta, int yc, int cpThickness, int cpLength) {
        double theta2 = theta + Math.PI / 2;

        final int[] r =   { yc - (int) Math.round(cpThickness * Math.sin(theta2) / 2.0),
                            yc - (int) Math.round(cpLength * Math.sin(theta)),
                            yc - (int) Math.round(-cpThickness * Math.sin(theta2) / 2.0)     };
        return r;
    }
}