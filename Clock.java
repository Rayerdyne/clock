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
    private int thickness;
    private int borderX;
    private int borderY;
    private int borderR;
    private int cpHLength;
    private int cpMLength;
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
        thickness = 5;
        borderX = 10;
        borderY = 10;
        borderR = 20;
        cpHLength = 40;
        cpMLength = 60;
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
        System.out.println("1 " + g2.getColor());
        
        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(foreground);
        g2.drawOval(borderX, borderY, x-2*borderX, y-2*borderY);
        System.out.println("2 " + g2.getColor());

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
        System.out.println(time + "h=" + hour + " min=" + minute);
        final double theta_h = angleOfTime(5*hour + 5*minute / minInHour);
        final double theta_m = angleOfTime(minute);

        final int[] xph = colckPointerX(theta_h, xc, cpHThickness, cpHLength);
        final int[] yph = colckPointerY(theta_h, yc, cpHThickness, cpHLength);
        final int[] xpm = colckPointerX(theta_m, xc, cpMThickness, cpMLength);
        final int[] ypm = colckPointerY(theta_m, yc, cpMThickness, cpMLength);

        g2.setColor(cpHourColor);
        g2.fillPolygon(xph, yph, 3);
        System.out.println("3 " + g2.getColor());

        g2.setColor(cpMinuteColor);
        g2.fillPolygon(xpm, ypm, 3);
        System.out.println("4 " + g2.getColor());
        
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
        System.out.println("min "+ min + " min2 " + min2);
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