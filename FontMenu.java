import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FontMenu extends JMenu implements ActionListener {
    /** Simple menu to describe a font (name; size)
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final StringHolder INIT_SIZE = new StringHolder("14", "Font size :",
        "Select font size", JOptionPane.INFORMATION_MESSAGE);
    private static final StringHolder INIT_NAME = new StringHolder("Calibri", 
        "Font name: ", "Select font", JOptionPane.INFORMATION_MESSAGE);

    private final JMenuItem fontNameItem = new JMenuItem("Font name");
    private final JMenuItem fontSizeItem = new JMenuItem("Font size");
    private final JMenuItem fontDescriptionItem = new JMenuItem("Calibri - 14");

    private StringHolder sizeStr = INIT_SIZE;
    private StringHolder name = INIT_NAME;


    public FontMenu(String name, ActionListener al) {
        super(name);

        fontNameItem.addActionListener(this.name);
        fontNameItem.addActionListener(al);
        fontNameItem.addActionListener(this);
        fontSizeItem.addActionListener(this.sizeStr);
        fontSizeItem.addActionListener(al);
        fontSizeItem.addActionListener(this);

        this.add(fontNameItem);
        this.add(fontSizeItem);
        this.add(fontDescriptionItem);
    }

    public String getFontName() {   return name.content();  }
    public int getFontSize()    {   return Integer.parseInt(sizeStr.content());  }

    public void actionPerformed(ActionEvent e) {
        fontDescriptionItem.setText(name.content() + " - " + sizeStr.content());
    }
};