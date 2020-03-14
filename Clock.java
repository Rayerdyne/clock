import java.awt.*;
import javax.swing.JPanel;
import java.time.LocalTime;
 
public class Clock extends JPanel { 
    /** The class that represents the clock.
     *
     */
    private static final long serialVersionUID = 1L;
    private LocalTime time;
    private int thickness;
    private Color background;
    private Color foreground;
    private int fontsize;
    private Font font;

    public Clock() {
        time = LocalTime.now();
        thickness = 5;
        background = Color.BLACK;
        foreground = Color.WHITE;
        fontsize = 14;
        font = new Font("Calibri", Font.PLAIN, fontsize);
    }

    public void paintComponent(final Graphics g) {
        final int x = this.getWidth();
        final int y = this.getHeight();
        time = LocalTime.now();
        // final int hour = time.getHour();
        // final int minute = time.getMinute();

        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(background);
        g2.fillRect(0, 0, x, y);

        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(foreground);
        g2.drawOval(10, 10, x-20, y-20);

        final int a, b;
        if (x > y) {    a = x / 2 - 10;
                        b = y / 2 - 10;  }
        else        {   a = y / 2 - 10;
                        b = x / 2 - 10;  }

        g2.setFont(font);
        final FontMetrics metrics = g.getFontMetrics();
        final double e = Math.sqrt( a*a - b*b ) / (double) a;
        for (int i = 0; i < 12; i++) {
            final String s = String.valueOf((i+2)%12 +1);

            final double theta = 2*Math.PI * i / 12;
            final double cosTheta = Math.cos(theta);
            final double r = Math.sqrt((double) b*b / (1.0 - e*e*cosTheta*cosTheta) ) - 20;

            int xs = (int) Math.round(r*cosTheta) + x / 2;
            int ys = (int) Math.round(r*Math.sin(theta)) + y / 2;

            //centering the labels
            xs -= metrics.stringWidth(s) / 2;

            g2.drawString(s, xs, ys);
        }
        
        //g.drawLine(10, 10, x-20, y-20);
  }               
}