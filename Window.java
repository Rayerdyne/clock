import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Window extends JFrame implements ActionListener, KeyListener, SignalReceiver {

    /** Holds the clock, and the menubar
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean isMenuBarShown;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuColors = new JMenu("Colors");
    private final JMenu menuEdit = new JMenu("Edit");

    private final ColorMenu menuForegroundColor = new ColorMenu("Foreground color", Color.WHITE, this);
    private final ColorMenu menuBackgroundColor = new ColorMenu("Background color", Color.BLACK, this);
    private final ColorMenu menuCPHourColor = new ColorMenu("Hour clock pointer", Color.WHITE, this);
    private final ColorMenu menuCPMinuteColor = new ColorMenu("Minute clock pointer", Color.WHITE, this);
    private final ColorMenu menuCPSecondColor = new ColorMenu("Second clock pointer", Color.RED, this);

    private final JMenuItem itemHide = new JMenuItem("Hide menu bar : h");
    private final FontMenu menuFont = new FontMenu("Font", Clock.DEF_FONT_NAME, Clock.DEF_FONT_SIZE, this);
    private final ParamMenuItem itemBorderR = new ParamMenuItem("Border R",
        "Set the distance between circle and numerals", "Pick a value", 
        JOptionPane.INFORMATION_MESSAGE, Clock.DEF_BORDER_R, this);

    private Clock clock;


    public Window() {
        this.setTitle("clock");
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //   COLOR  MENU
        menuColors.add(menuBackgroundColor);
        menuColors.add(menuForegroundColor);
        menuColors.addSeparator();
        
        menuColors.add(menuCPHourColor);
        menuColors.add(menuCPMinuteColor);
        menuColors.add(menuCPSecondColor);
        
        //   EDIT  MENU
        itemHide.setEnabled(false);
        menuEdit.add(itemHide);
        menuEdit.addSeparator();

        menuEdit.add(menuFont);
        menuEdit.addSeparator();

        menuEdit.add(itemBorderR);


        menuBar.add(menuColors);
        menuBar.add(menuEdit);
        isMenuBarShown = true;
        this.addKeyListener(this);
        
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
        clock.setCPSecondColor(menuCPSecondColor.getMenuColor());
        clock.setFont(menuFont.getFontName(), menuFont.getFontSize());
        clock.setBorderR(itemBorderR.value());
        clock.repaint();
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'h') {
            if (isMenuBarShown == false) {
                isMenuBarShown = true;
                menuBar.setVisible(true);
                this.setJMenuBar(this.menuBar);
            }
            else {
                isMenuBarShown = false;
                menuBar.setVisible(false);
                this.setJMenuBar(null);
            }
            repaint();
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void trigger() {
        actionPerformed(null);
    }
};