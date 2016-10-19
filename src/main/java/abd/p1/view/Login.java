package abd.p1.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import abd.p1.controller.LoginController;

public class Login extends JDialog
{
	
	private static final long serialVersionUID = 1L;
	
	private JButton buttonAceptar;
	private JButton buttonNuevoUsuario;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPasswordField textFieldPassword;
    private JTextField textFieldCorreo;
    
    private LoginController loginController;

    /**
     * Main constructor
     * 
     * @param parent
     * @param modal
     */
	public Login(Frame parent, boolean modal, LoginController loginController)
	{
       super(parent, modal);
       this.loginController = loginController;
       initComponents();
    }

	/**
	 * Initialize components
	 */
	private void initComponents()
	{
		jLabel1 = new JLabel();
        textFieldCorreo = new JTextField();
        jLabel2 = new JLabel();
        textFieldPassword = new JPasswordField();
        buttonAceptar = new JButton();
        buttonNuevoUsuario = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");

        jLabel1.setText("Direccion de correo:");

        jLabel2.setText("Contraseña:");

        buttonAceptar.setText("Aceptar");
        buttonAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		buttonAceptarActionPerformed(evt);
           }
        });

        buttonNuevoUsuario.setText("Nuevo usuario");
        buttonNuevoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	buttonNuevoUsuarioActionPerformed(evt);
            }
        });

       GroupLayout layout = new GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                   .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                   .addComponent(textFieldCorreo)
                   .addComponent(textFieldPassword))
               .addContainerGap())
           .addGroup(layout.createSequentialGroup()
               .addGap(81, 81, 81)
               .addComponent(buttonAceptar)
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(buttonNuevoUsuario)
               .addContainerGap(65, Short.MAX_VALUE))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel1)
                   .addComponent(textFieldCorreo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel2)
                   .addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(buttonAceptar)
                   .addComponent(buttonNuevoUsuario))
               .addContainerGap())
       );

       pack();
       this.setLocationRelativeTo(null);
   }                       

	/**
	 * Action performed for "ACEPTAR" button
	 * 
	 * @param evt
	 */
	private void buttonAceptarActionPerformed(ActionEvent evt)
	{
		if(loginController.checkUser(getEmail(), getPass()))
		{
		    this.dispose();
		}
	}

	/**
	 * Action performed for "NUEVO USUARIO" button
	 * 
	 * @param evt
	 */
	private void buttonNuevoUsuarioActionPerformed(ActionEvent evt)
	{
		if(loginController.newUser(getEmail(), getPass()))
		{
		    this.dispose();
		}
	}
	
	/**
	 * Get introduced email
	 * 
	 * @return
	 */
	public String getEmail()
	{
		return textFieldCorreo.getText();
	}
	
	/**
	 * Get introduced password
	 * 
	 * @return
	 */
	public String getPass()
	{
		return new String(textFieldPassword.getPassword());
	}
                   
}
