import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class BoolMenuItem extends JMenuItem implements ActionListener {
    /** Simple menu item that holds a boolean
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean state;
    private String name;

    public BoolMenuItem(String name, boolean state, SignalReceiver receiver) {
        super(name + state);
        this.name = name;
        this.state = state;
    }

    public boolean state() {    return state;   }

    public void actionPerformed(ActionEvent e) {
        if (state) 
            state = false;
        else 
            state = true;
        this.setText(name + state);
    }
}