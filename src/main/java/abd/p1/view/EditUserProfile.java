package abd.p1.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import abd.p1.controller.EditUserProfileController;
import abd.p1.model.Usuario;
import abd.p1.view.panels.UserProfilePanel;

public class EditUserProfile extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private JButton buttonCambiarContraseña;
	private JButton buttonCancelar;
	private JButton buttonGuardarCambios;
	private JPanel jPanel2;
	private UserProfilePanel userProfilePanel1;
	
	private EditUserProfileController editUserProfileController;
	private Usuario usuario;

	/**
	 * Main constructor
	 * 
	 * @param parent
	 * @param modal
	 */
	public EditUserProfile(Frame parent, boolean modal, EditUserProfileController editUserProfileController, Usuario usuario)
	{
		super(parent, modal);
		this.editUserProfileController = editUserProfileController;
		this.usuario = usuario;
		initComponents();
	}
	
	/**
	 * Initialize components
	 */
    private void initComponents()
    {

       jPanel2 = new JPanel();
       buttonCambiarContraseña = new JButton();
       buttonCancelar = new JButton();
       buttonGuardarCambios = new JButton();
       userProfilePanel1 = new UserProfilePanel(editUserProfileController, usuario);

       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       setTitle("Modificacion de perfil de usuario");
       
       buttonCambiarContraseña.setText("Cambiar contraseña");
       buttonCambiarContraseña.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   buttonCambiarContraseñaActionPerformed(evt);
           }
       });

       buttonCancelar.setText("Cancelar");
       buttonCancelar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   buttonCancelarActionPerformed(evt);
           }
       });

       buttonGuardarCambios.setText("Guardar cambios");
       buttonGuardarCambios.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   buttonGuardarCambiosActionPerformed(evt);
           }
       });

       GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
       jPanel2.setLayout(jPanel2Layout);
       jPanel2Layout.setHorizontalGroup(
           jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(jPanel2Layout.createSequentialGroup()
               .addComponent(buttonCambiarContraseña)
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(buttonGuardarCambios)
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(buttonCancelar))
       );
       jPanel2Layout.setVerticalGroup(
           jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
               .addGap(0, 0, Short.MAX_VALUE)
               .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(buttonCambiarContraseña)
                   .addComponent(buttonCancelar)
                   .addComponent(buttonGuardarCambios)))
       );

       GroupLayout layout = new GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                   .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                       .addGap(0, 0, Short.MAX_VALUE)
                       .addComponent(userProfilePanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(userProfilePanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addContainerGap())
       );

       pack();
       this.setLocationRelativeTo(null);
    }                       

    /**
	 * Action performed for "CAMBIAR CONTRASEÑA" button
	 * 
	 * @param evt
	 */
    private void buttonCambiarContraseñaActionPerformed(ActionEvent evt)
    {                                             
	    String pass = JOptionPane.showInputDialog(null, "Introduzca una contraseña:", "Cambiar contraseña", JOptionPane.QUESTION_MESSAGE);
	    
	    if(pass != null)
	    {
	    	this.editUserProfileController.updatePass(pass);
	    }
    }
    
    /**
	 * Action performed for "GUARDAR CAMBIOS" button
	 * 
	 * @param evt
	 */
    private void buttonGuardarCambiosActionPerformed(ActionEvent evt)
    {
    	this.dispose();
		this.editUserProfileController.updateDescription(userProfilePanel1.getTextPaneDescripcion());
    	this.editUserProfileController.commitUser();

    }
    
    /**
	 * Action performed for "CANCELAR" button
	 * 
	 * @param evt
	 */
   	private void buttonCancelarActionPerformed(ActionEvent evt)
    {
   		this.dispose();
   		this.editUserProfileController.checkCancel();
    }

}
