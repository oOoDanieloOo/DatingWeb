package abd.p1.view.lists;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import abd.p1.view.panels.AvatarPanel;

public class ElementUserList extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private String nombre = "Nombre";
    private int edad = 0;
    
    private JLabel labelEdad;
    private JLabel labelNombre;
    private AvatarPanel panelAvatar;

    /**
     * Default constructor
     */
    public ElementUserList()
    {
        initComponents();
    }

    /**
     * "GETTER" method for name label
     * 
     * @return
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * "SETTER" method for name label
     * 
     * @return
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
        labelNombre.setText(nombre);
    }

    /**
     * "GETTER" method for age label
     * 
     * @return
     */
    public int getEdad()
    {
        return edad;
    }
  
    /**
     * "SETTER" method for image panel
     * 
     * @param imagen
     */
    public void setAvatarImage(byte[] imagen)
    {
    	BufferedImage icono = null;
		try {
				icono = ImageIO.read(new ByteArrayInputStream(imagen));
				this.panelAvatar.setIcon(new ImageIcon(icono));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * "SETTER" method for age label
     * 
     * @return
     */
    public void setAge(Date fecha)
    {
    	Calendar birth = Calendar.getInstance();
		birth.setTime(fecha);
		Calendar today = Calendar.getInstance();
		
		int year = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE) - birth.get(Calendar.DATE);

        if (month < 0 || (month == 0 && day < 0))
        {
        	year--;
        }
        
        labelEdad.setText(year + " años");
    }

    /**
     * Initialize components
     */
    private void initComponents()
    {

        labelNombre = new JLabel();
        labelEdad = new JLabel();
        panelAvatar = new AvatarPanel();

        labelNombre.setFont(new Font("Tekton Pro Ext", 1, 19)); // NOI18N
        labelNombre.setText("Nombre");

        labelEdad.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        labelEdad.setText("Edad");

        GroupLayout avatarPanel1Layout = new GroupLayout(panelAvatar);
        panelAvatar.setLayout(avatarPanel1Layout);
        avatarPanel1Layout.setHorizontalGroup(
            avatarPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );
        avatarPanel1Layout.setVerticalGroup(
            avatarPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelEdad, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(233, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelEdad)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }                       
                                  
}

