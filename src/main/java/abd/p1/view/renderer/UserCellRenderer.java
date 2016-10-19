package abd.p1.view.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abd.p1.model.Usuario;
import abd.p1.view.lists.ElementUserList;
import abd.p1.view.panels.AvatarPanel;

public class UserCellRenderer extends ElementUserList implements ListCellRenderer<Usuario>
{

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public UserCellRenderer()
	{
		
	}
	
	/**
	 * Overrides constructor from ListCellRenderer that renders the user cell
	 */
	@Override
    public Component getListCellRendererComponent(
            JList<? extends Usuario> list,
            Usuario value, int index,
            boolean isSelected, boolean cellHasFocus)
    {
		// This crap code inside try catch is only because i can't pass a null image for those users that
		// doesn't have an image, so i have to convert my default.jpg image to byte[] before it calls
		// ElementUserList.java. It's strange but it's the only way i found to solve this.
		try 
		{	
			ByteArrayOutputStream baos = null;
			BufferedImage originalImage = ImageIO.read((AvatarPanel.class.getResource("default.jpg")));
			baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			
			if(value.getImagen() != null)
				this.setAvatarImage(value.getImagen());
			else
				this.setAvatarImage(imageInByte);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        this.setNombre(value.getNombre());
        this.setAge(value.getFecha_nacimimento());
        
        this.setOpaque(true);
        if(isSelected)
        {
            this.setBackground(Color.ORANGE);
        } else
        {
            this.setBackground(Color.WHITE);
        }
        return this;
    }
    
}

