import javax.swing.JFrame;

public class Window extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Window() {
        this.setTitle("clock");
        this.setSize(150, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Clock c = new Clock();

        this.setContentPane(c);
        this.setVisible(true);
    }
}