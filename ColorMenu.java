import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorMenu extends JMenu {

	/** A simple menu to pick a color
	 *
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

    public ColorMenu(String name, Color defaultColor, ActionListener al) {
        super(name);

        for (int i = 0; i < nColors; i++) {
            bg.add(radios[i]);
            this.add(radios[i]);
            radios[i].addActionListener(al);
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

        for (int i = 0; i < nColors; i++) {
            if (radios[i].isSelected())
                return colors[i];
        }
        return Color.BLACK;
    }
}