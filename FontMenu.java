import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FontMenu extends JMenu implements SignalReceiver {
    /** Simple menu to describe a font (name; size)
     * 
     */
    private static final long serialVersionUID = 1L;

    private final JMenuItem fontNameItem = new JMenuItem("Font name");
    private final JMenuItem fontSizeItem = new JMenuItem("Font size");
    private final JMenuItem fontDescriptionItem = new JMenuItem("Calibri - 14");
    private final SignalReceiver receiver; 

    private StringHolder sizeStr = new StringHolder("14", "Font size :",
        "Select font size", JOptionPane.INFORMATION_MESSAGE, this);;
    private StringHolder name = new StringHolder("Calibri", 
        "Font name: ", "Select font", JOptionPane.INFORMATION_MESSAGE, this);;


    public FontMenu(String name, SignalReceiver receiver) {
        super(name);

        this.receiver = receiver;

        fontNameItem.addActionListener(this.name);
        // fontNameItem.addActionListener(this);
        // fontNameItem.addActionListener(al);
        fontSizeItem.addActionListener(this.sizeStr);
        // fontSizeItem.addActionListener(this);
        // fontSizeItem.addActionListener(al);

        this.add(fontNameItem);
        this.add(fontSizeItem);
        this.add(fontDescriptionItem);
    }

    public String getFontName() {   return name.content();  }
    public int getFontSize()    {   return Integer.parseInt(sizeStr.content());  }
    
    public void trigger() {
        fontDescriptionItem.setText(name.content() + " - " + sizeStr.content());
        receiver.trigger();
    }
};