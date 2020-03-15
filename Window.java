import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
// import javax.swing.JMenuItem;

public class Window extends JFrame implements ActionListener {

    /** Holds the clock, and the menubar
     *
     */
    private static final long serialVersionUID = 1L;

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuEdit = new JMenu("Edit");

    private final ColorMenu menuForegroundColor = new ColorMenu("Foreground color", Color.WHITE, this);
    private final ColorMenu menuBackgroundColor = new ColorMenu("Background color", Color.BLACK, this);
    private final ColorMenu menuCPHourColor = new ColorMenu("Hour clock pointer", Color.WHITE, this);
    private final ColorMenu menuCPMinuteColor = new ColorMenu("Minute clock pointer", Color.WHITE, this);

    private Clock clock;


    public Window() {
        this.setTitle("clock");
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuEdit.add(menuBackgroundColor);
        menuEdit.add(menuForegroundColor);
        menuEdit.addSeparator();
        menuEdit.add(menuCPHourColor);
        menuEdit.add(menuCPMinuteColor);


        menuBar.add(menuEdit);
        
        clock = new Clock();
        this.setContentPane(clock);
        this.setJMenuBar(menuBar);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        clock.setForegroundColor(menuForegroundColor.getMenuColor());
        clock.setBackgroundColor(menuBackgroundColor.getMenuColor());
        clock.setCPHourColor(menuCPHourColor.getMenuColor());
        clock.setCPMinuteColor(menuCPMinuteColor.getMenuColor());
        clock.repaint();
    }
};