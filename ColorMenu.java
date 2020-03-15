import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
// import javax.swing.JMenuItem;
// import javax.swing.JColorChooser;
// import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;

public class ColorMenu extends JMenu /*implements ActionListener*/ {

	/** A simple menu to pick a color
	 * For later: add a popup option with a JColorChooser
	 */
    private static final long serialVersionUID = 1L;

    private static final int nColors = 12;
    private static final Color[] colors = {
        Color.BLACK,        Color.DARK_GRAY,
        Color.GRAY,         Color.LIGHT_GRAY,
        Color.WHITE,        Color.BLUE,
        Color.CYAN,         Color.GREEN,
        Color.YELLOW,       Color.ORANGE,
        Color.RED,          Color.PINK};

    // private Color selectedColor = null;
    // private boolean isLastCustom = false;
    private ButtonGroup bg = new ButtonGroup();
    private final JRadioButtonMenuItem radios[] = {
        new JRadioButtonMenuItem("Black"),
        new JRadioButtonMenuItem("Dark gray"),
        new JRadioButtonMenuItem("Gray"),
        new JRadioButtonMenuItem("Light gray"),
        new JRadioButtonMenuItem("White"),
        new JRadioButtonMenuItem("Blue"),
        new JRadioButtonMenuItem("Cyan"),
        new JRadioButtonMenuItem("Green"),
        new JRadioButtonMenuItem("Yellow"),
        new JRadioButtonMenuItem("Orange"),
        new JRadioButtonMenuItem("Red"),
        new JRadioButtonMenuItem("Pink")
    };

    // private final JMenuItem customColor = new JMenuItem("Custom");
    // private final JColorChooser cc = new JColorChooser();

    public ColorMenu(String name, Color defaultColor, ActionListener al) {
        super(name);

        for (int i = 0; i < nColors; i++) {
            bg.add(radios[i]);
            this.add(radios[i]);
            radios[i].addActionListener(al);
            // radios[i].addActionListener(this);
        }

        int k = 0;
        for (int i = 0; i < nColors; i++) {
            if (defaultColor == colors[i]) {
                k = i;
                break;
            }
        }
        radios[k].setSelected(true);        
    }

    public Color getMenuColor() {
        for (int i = 0; i < nColors; i++)
            if (radios[i].isSelected())
                return colors[i];
        return null;
    }

    /*public void actionPerformed(ActionEvent e) {
        if (isLastCustom && selectedColor != null)
            return;
        else 
            for (int i = 0; i < nColors; i++) {
                if (radios[i].isSelected()) 
                    selectedColor = colors[i];
            }
    }*/
}