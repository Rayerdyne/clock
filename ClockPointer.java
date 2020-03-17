import java.awt.Graphics2D;
import java.awt.Color;

public class ClockPointer {
    private static final int minInHour = 60;
    private int ratio;
    private int thickness;
    private Color color;

    public ClockPointer(int ratio, int thickness, Color color) {
        this.ratio = ratio;
        this.thickness = thickness;
        this.color = color;
    }

    public void setRatio(int ratio)         {  this.ratio = ratio;          }
    public void setThickness(int thickness) {  this.thickness = thickness;  }
    public void setColor(Color color)       {  this.color = color;          }

    public void drawAtTime(int min, int xc, int yc, int a, int b, Graphics2D g) {
        final double e = Math.sqrt( a*a - b*b ) / (double) a;
        final double theta = angleOfTime(min);
        final double cosTheta = Math.cos(theta);

        final double r= Math.sqrt((double)b*b / (1.0 - e*e*cosTheta*cosTheta));

        final int[] xx = clockPointerX(theta, xc, (r*ratio) / 100);
        final int[] yy = clockPointerY(theta, yc, (r*ratio) / 100);

        g.setColor(color);
        g.fillPolygon(xx, yy, 3);
    }

    private static double angleOfTime(int min) {
        /** Converts the position on min to a an angle, measured
         *  couterclockwise, theta = 0 corresponds to the
         *  horizontal right.
         */
        final int min2 = (15 + minInHour - min) % minInHour;
        return (2*Math.PI *(double) min2 / (double) minInHour);
    }

    private int[] clockPointerX(double theta, int xc, double r) {
        /**
         * Computes x coordinates for drawing the clock pointer
         */

        double theta2 = theta + Math.PI / 2;

        final int[] xx = { xc + (int) Math.round(thickness * Math.cos(theta2) / 2.0),
                    xc + (int) Math.round(r * Math.cos(theta)),
                    xc + (int) Math.round(-thickness * Math.cos(theta2) / 2.0)     };
        return xx;
    }

    private int[] clockPointerY(double theta, int yc, double r) {
        /**
         * Computes y coordinates for drawing the clock pointer
         */

        double theta2 = theta + Math.PI / 2;

        final int[] yy = { yc - (int) Math.round(thickness * Math.sin(theta2) / 2.0),
                    yc - (int) Math.round(r * Math.sin(theta)),
                    yc - (int) Math.round(-thickness * Math.sin(theta2) / 2.0)     };
        return yy;
    }
}