import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class StringHolder implements ActionListener {

    private String s;
    private String message, title;
    private int messageType;

    public StringHolder(String s, String message, String title, int messageType) {
        this.s = s;
        this.message = message;
        this.title = title;
        this.messageType = messageType;
    }

    public void actionPerformed(ActionEvent e) {
        s = JOptionPane.showInputDialog(null, message, title, messageType);
    }

    public void setMessage(String s)    {  message = s;  }
    public void setTitle(String s)      {  title = s;  }
    public void setMessageType(int mt)  {  messageType = mt;  }

    public String content()  { return s; }
}