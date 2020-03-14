import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Window extends JFrame {

    /** Holds the clock, and the menubar
     *
     */
    private static final long serialVersionUID = 1L;

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuEdit = new JMenu("Edit");

    private final ColorMenu menuFC = new ColorMenu("Foreground color");
    private final ColorMenu menuBC = new ColorMenu("Background color");


    public Window() {
        this.setTitle("clock");
        this.setSize(150, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuEdit.add(menuBC);
        menuEdit.add(menuFC);

        menuBar.add(menuEdit);
        
        final Clock c = new Clock();
        this.setContentPane(c);
        this.setMenuBar(menuBar);

        this.setVisible(true);
    }
};