package abd.p1.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Dani
 */
public class AvatarPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 64;
    private static final ImageIcon defaultIcon = new ImageIcon(AvatarPanel.class.getResource("default.jpg"));
    
    private ImageIcon icon;
    private Image rescaledIcon;
    
    /**
     * Default constructor
     */
    public AvatarPanel()
    {
        this(defaultIcon);
    }
    
    /**
     * Main constructor
     */
    public AvatarPanel(ImageIcon icon)
    {
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.icon = icon;
        this.rescaledIcon = icon.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_DEFAULT);
    }

    /**
     * Method "GETTER" for user image icon
     * 
     * @return
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * Method "SETTER" for user image icon
     * 
     * @return
     */
    public void setIcon(ImageIcon icon)
    {
		this.icon = icon;
        this.rescaledIcon = icon.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_DEFAULT);
        this.repaint();
    }

    /**
     * Paint image panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        g.drawImage(rescaledIcon, 0, 0, this);
    }                    
                 
}
