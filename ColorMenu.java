import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.Color;

public class ColorMenu extends JMenu {

	/** A simple menu to pick a color
	 *
	 */
    private static final long serialVersionUID = 1L;

    private static final int nColors = 12;

    private final JRadioButtonMenuItem radios[] = new JRadioButtonMenuItem[nColors];
    private ButtonGroup bg = new ButtonGroup();

    public ColorMenu(String name) {
        super(name);
        radios[0] = new JRadioButtonMenuItem("Black");
        radios[1] = new JRadioButtonMenuItem("Dark gray");
        radios[2] = new JRadioButtonMenuItem("Gray");
        radios[3] = new JRadioButtonMenuItem("Light Gray");
        radios[4] = new JRadioButtonMenuItem("White");
        radios[5] = new JRadioButtonMenuItem("Blue");
        radios[6] = new JRadioButtonMenuItem("Cyan");
        radios[7] = new JRadioButtonMenuItem("Green");
        radios[8] = new JRadioButtonMenuItem("Yellow");
        radios[9] = new JRadioButtonMenuItem("Orange");
        radios[10] = new JRadioButtonMenuItem("Red");
        radios[11] = new JRadioButtonMenuItem("Pink");

        for (int i = 0; i < nColors; i++) {
            bg.add(radios[i]);
            this.add(radios[i]);
        }

        radios[0].setSelected(true);        
    }

    public Color getMenuColor() {
        Color[] colors = new Color[nColors];
        colors[0] = Color.BLACK;
        colors[1] = Color.DARK_GRAY;
        colors[2] = Color.GRAY;
        colors[3] = Color.LIGHT_GRAY;
        colors[4] = Color.WHITE;
        colors[5] = Color.BLUE;
        colors[6] = Color.CYAN;
        colors[7] = Color.GREEN;
        colors[8] = Color.YELLOW;
        colors[9] = Color.ORANGE;
        colors[10] = Color.RED;
        colors[11] = Color.PINK;

        for (int i = 0; i < nColors; i++) {
            if (radios[i].isSelected())
                return colors[i];
        }
        return Color.BLACK;
    }
}