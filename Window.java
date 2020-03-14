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

    private final ColorMenu menuFC = new ColorMenu("Foreground color", Color.WHITE, this);
    private final ColorMenu menuBC = new ColorMenu("Background color", Color.BLACK, this);

    private final FontMenu menuFont = new FontMenu("Font");

    private Clock clock;


    public Window() {
        this.setTitle("clock");
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuEdit.add(menuBC);
        menuEdit.add(menuFC);
        menuEdit.addSeparator();
        menuEdit.add(menuFont);

        menuBar.add(menuEdit);
        
        clock = new Clock();
        this.setContentPane(clock);
        this.setJMenuBar(menuBar);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        clock.setFGColor(menuFC.getMenuColor());
        clock.setBGColor(menuBC.getMenuColor());
        clock.repaint();
    }
};