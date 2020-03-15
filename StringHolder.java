import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class StringHolder implements ActionListener {

    private String s;
    private String message, title;
    private int messageType;
    private final SignalReceiver receiver;

    public StringHolder(String s, String message, String title, int messageType,
        SignalReceiver receiver) {
        this.s = s;
        this.message = message;
        this.title = title;
        this.messageType = messageType;
        this.receiver = receiver;
    }

    public void actionPerformed(ActionEvent e) {
        s = JOptionPane.showInputDialog(null, message, title, messageType);
        receiver.trigger();
    }

    public void setMessage(String s)    {  message = s;  }
    public void setTitle(String s)      {  title = s;  }
    public void setMessageType(int mt)  {  messageType = mt;  }

    public String content()  { return s; }
}