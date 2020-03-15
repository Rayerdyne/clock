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
    private final JMenuItem fontDescriptionItem = new JMenuItem("empty - empty");
    private final SignalReceiver receiver; 

    private StringHolder fontSizeStr = new StringHolder("empty", "Font size :",
        "Select font size", JOptionPane.INFORMATION_MESSAGE, this);;
    private StringHolder fontName = new StringHolder("empty", 
        "Font name: ", "Select font", JOptionPane.INFORMATION_MESSAGE, this);;


    public FontMenu(String name, String fontName, int fontSize, SignalReceiver receiver) {
        super(name);

        this.receiver = receiver;
        this.fontName.setContent(fontName);
        this.fontSizeStr.setContent(String.valueOf(fontSize));

        fontNameItem.addActionListener(this.fontName);
        fontSizeItem.addActionListener(this.fontSizeStr);
        fontDescriptionItem.setEnabled(false);

        this.add(fontNameItem);
        this.add(fontSizeItem);
        this.add(fontDescriptionItem);
    }

    public String getFontName() {   return fontName.content();  }
    public int getFontSize()    {   return Integer.parseInt(fontSizeStr.content());  }
    
    public void trigger() {
        fontDescriptionItem.setText(fontName.content() + " - " + fontSizeStr.content());
        receiver.trigger();
    }
};