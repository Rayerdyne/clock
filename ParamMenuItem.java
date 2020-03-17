import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

// import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParamMenuItem extends JMenuItem implements ActionListener {
    /** Holds a integer parameter
     * 
     */
    private static final long serialVersionUID = 1L;

    private int value;
    private String name, message, title;
    private int messageType;
    private final SignalReceiver receiver;

    public ParamMenuItem(String name, String message, String title, 
        int messageType, int value, SignalReceiver receiver) {

        super(name + ": " + value);
        
        this.name = name;
        this.message = message;
        this.title = title;
        this.messageType = messageType;
        this.value = value;
        this.receiver = receiver;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String s = JOptionPane.showInputDialog(null, message, title, messageType);
        try {   
            int newv = Integer.parseInt(s);
            value = newv;
            this.setText(name + ": " + value);
        } catch(NumberFormatException ex) {
                System.out.println("ERROR: could not parse integer : " + ex);
        }
        receiver.trigger();
    }

    public int value() {  return value;  }

    public void setMessage(String s) {  message = s;  }
    public void setTitle(String s) {  title = s;  }
    public void setMessageType(int t) {  messageType = t;  }
} 