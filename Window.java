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

abstract interface ValueSetter {    void set(int value);    }
abstract interface ColorSetter {    void set(Color color);  }


public class Window extends JFrame implements ActionListener, KeyListener, SignalReceiver {

    /** Holds the clock, and the menubar
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean isMenuBarShown;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuColors = new JMenu("Colors");
    private final JMenu menuEdit = new JMenu("Settings");

    private final ColorMenu menuForegroundColor = new ColorMenu("Foreground color", 
        Clock.DEF_FOREGROUD_COLOR, this);
    private final ColorMenu menuBackgroundColor = new ColorMenu("Background color", 
        Clock.DEF_BACKGROUD_COLOR, this);
    private final ColorMenu menuCPHourColor = new ColorMenu("Hour clock pointer", 
        Clock.DEF_CPHOUR_COLOR, this);
    private final ColorMenu menuCPMinuteColor = new ColorMenu("Minute clock pointer", 
        Clock.DEF_CPMINUTE_COLOR, this);
    private final ColorMenu menuCPSecondColor = new ColorMenu("Second clock pointer", 
        Clock.DEF_CPSECOND_COLOR, this);

    private final JMenuItem itemHide = new JMenuItem("Hide menu bar : h");
    private final BoolMenuItem itemSecond = new BoolMenuItem("Show seconds", true, this);
    private final FontMenu menuFont = new FontMenu("Font", Clock.DEF_FONT_NAME, Clock.DEF_FONT_SIZE, this);

    private final ParamMenuItem[] items = new ParamMenuItem[Clock.N_INT_PARAMETERS];
    private final ValueSetter[] itemsSetters;
    private final String[] itemsNames = {
        "Border R",             "Border X",             "Border Y",
        "Hour CP Ratio",        "Minute CP Ratio",      "Seconds CP Ratio",
        "Hour CP Thickness",    "Minute CP Thickness",  "Seconds CP Thickness",
        "Clock thickness"
    };
    private final String[] itemsMessages = {
        "Set the distance between circle and numerals",
        "Set the horizontal gap",
        "Set the vertical gap",
        "Set the hour clock pointer ratio (% of total radius)",
        "Set the minute clock pointer ratio (% of total radius)",
        "Set the second clock pointer ratio (% of total radius)",
        "Set the hour clock pointer thickness (px)",
        "Set the minute clock pointer thickness (px)",
        "Set the second clock pointer thickness (px)",
        "Set the clock's perimeter thickness"
    };
    private final int[] itemsDefs = {
        Clock.DEF_BORDER_R,         Clock.DEF_BORDER_X,             Clock.DEF_BORDER_Y,
        Clock.DEF_CPHOUR_RATIO,     Clock.DEF_CPMINUTE_RATIO,       Clock.DEF_CPSECOND_RATIO,
        Clock.DEF_CPHOUR_THICKNESS, Clock.DEF_CPMINUTE_THICKNESS,   Clock.DEF_CPSECOND_THICKNESS,
        Clock.DEF_CLOCK_THICKNESS
    };

    private Clock clock;


    public Window() {
        this.setTitle("clock");
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clock = new Clock();

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
        menuEdit.add(itemSecond);
        menuEdit.addSeparator();

        menuEdit.add(menuFont);
        menuEdit.addSeparator();

        for (int i = 0; i < Clock.N_INT_PARAMETERS; i++) {
            items[i] = new ParamMenuItem(itemsNames[i], itemsMessages[i],
                "Pick a value", JOptionPane.INFORMATION_MESSAGE, itemsDefs[i], this);
            menuEdit.add(items[i]);
        }
        itemsSetters = new ValueSetter[] {
            clock::setBorderR,          clock::setBorderX,           clock::setBorderY,
            clock::setCPHourRatio,      clock::setCPMinuteRatio,     clock::setCPSecondRatio,
            clock::setCPHourThickness,  clock::setCPMinuteThickness, clock::setCPSecondThickness,
            clock::setClockThickness
        };
        // itemsSetters = itemsSettersTmp;

        menuBar.add(menuEdit);
        menuBar.add(menuColors);
        isMenuBarShown = true;
        this.addKeyListener(this);
        
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

        clock.setShowSecond(itemSecond.state());
        for (int i = 0; i < Clock.N_INT_PARAMETERS; i++) {
            itemsSetters[i].set(items[i].value());
        }

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