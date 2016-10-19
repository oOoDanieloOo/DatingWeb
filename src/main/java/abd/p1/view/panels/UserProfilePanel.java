package abd.p1.view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;

import com.toedter.calendar.JDateChooser;

import abd.p1.controller.EditUserProfileController;
import abd.p1.model.Preferencia;
import abd.p1.model.Sexo;
import abd.p1.model.Usuario;

public class UserProfilePanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	public static final String[] sexo = { "Masculino", "Femenino" };
	public static final String[] preferencia = { "Hombres", "Mujeres", "Ambos" };
	
	private AvatarPanel avatarPanel1;
    private JButton buttonAvatar;
    private JButton buttonAñadirSeleccionada;
    private JButton buttonEditarSeleccionada;
    private JButton buttonEliminarSeleccionada;
    private JButton buttonFechaNacimiento;
    private JButton buttonNombre;
    private JButton buttonPreferencia;
    private JButton buttonSexo;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel labelEdad;
    private JLabel labelNombre;
    private JLabel labelPreferencia;
    private JLabel labelSexo;
    private JScrollPane scrollPaneAficiones;
    private JScrollPane scrollPaneDescripcion;
    private JList<String> listAficiones;
    private JTextPane textPaneDescripcion;
	
    private boolean editable = true;
    
    private DefaultListModel<String> modelAficiones;
    private List<String> aficiones;
    
    private EditUserProfileController editUserProfileController;
    private Usuario usuario;

    /**
     * Main constructor
     */
    public UserProfilePanel(EditUserProfileController editUserProfileController, Usuario usuario)
    {
    	this.usuario = usuario;
    	this.editUserProfileController = editUserProfileController;
        initComponents();
        initView();
        
    }
    
    private void initView()
    {
    	if (this.usuario.getNombre() != null)
    	{
    		this.labelPreferencia.setText(Preferencia.getStringFromPreferencia(usuario.getPreferencia()));
        	this.labelSexo.setText(Sexo.getStringFromSexo(usuario.getSexo()));
        	this.textPaneDescripcion.setText(usuario.getDescripcion());
        	this.labelEdad.setText(getAge(usuario.getFecha_nacimimento()) + " años");
        	this.labelNombre.setText(usuario.getNombre());
        	if (usuario.getImagen() != null)
        	{
        		this.avatarPanel1.setIcon(new ImageIcon(usuario.getImagen()));
        	}
        	this.initHobbiesList();
    	}
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable)
    {
        this.editable = editable;
        if(!editable){
        	textPaneDescripcion.setBackground(this.getBackground());
        }
        textPaneDescripcion.setEditable(editable);
        
        buttonNombre.setVisible(editable);
        buttonFechaNacimiento.setVisible(editable);
        buttonAvatar.setVisible(editable);
        buttonAñadirSeleccionada.setVisible(editable);
        buttonEliminarSeleccionada.setVisible(editable);
        buttonEditarSeleccionada.setVisible(editable);
        buttonSexo.setVisible(editable);
        buttonPreferencia.setVisible(editable);
    }
    
    /**
     * Set hobbies list 
     * 
     * @param aficiones
     */
    private void initHobbiesList()
    {
    	this.aficiones = editUserProfileController.getAficiones();
    	if (aficiones != null)
    	{
    		for (String aficion : aficiones)
    		{
            	modelAficiones.addElement(aficion);
            }
        	listAficiones.setModel(modelAficiones);
    	}
    }
    
    /**
	 * Initialize components
	 */
    private void initComponents()
    {

        avatarPanel1 = new AvatarPanel();
        labelNombre = new JLabel();
        labelEdad = new JLabel();
        buttonNombre = new JButton();
        buttonFechaNacimiento = new JButton();
        buttonAvatar = new JButton();
        jLabel3 = new JLabel();
        scrollPaneDescripcion = new JScrollPane();
        textPaneDescripcion = new JTextPane();
        jLabel4 = new JLabel();
        scrollPaneAficiones = new JScrollPane();
        listAficiones = new JList<>();
        buttonAñadirSeleccionada = new JButton();
        buttonEliminarSeleccionada = new JButton();
        buttonEditarSeleccionada = new JButton();
        buttonPreferencia = new JButton();
        buttonSexo = new JButton();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        labelSexo = new JLabel();
        labelPreferencia = new JLabel();
        modelAficiones = new DefaultListModel<String>();

        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        GroupLayout avatarPanel1Layout = new GroupLayout(avatarPanel1);
        avatarPanel1.setLayout(avatarPanel1Layout);
        avatarPanel1Layout.setHorizontalGroup(
            avatarPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        avatarPanel1Layout.setVerticalGroup(
            avatarPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        labelNombre.setText("Nombre");
        labelNombre.setFont(new Font("Tekton Pro Ext", 1, 20));

        labelEdad.setText("Edad");

        buttonNombre.setText("Cambiar nombre");
        buttonNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonNombreActionPerformed(evt);
            }
        });

        buttonFechaNacimiento.setText("Cambiar fecha de nacimiento");
        buttonFechaNacimiento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	buttonFechaNacimientoActionPerformed(evt);
            }
        });

        buttonAvatar.setText("Cambiar avatar");
        buttonAvatar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	try {
					buttonAvatarActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });

        jLabel3.setText("Descripcion:");

        scrollPaneDescripcion.setViewportView(textPaneDescripcion);

        jLabel4.setText("Aficiones:");

        scrollPaneAficiones.setViewportView(listAficiones);

        buttonAñadirSeleccionada.setText("Añadir seleccion");
        buttonAñadirSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAñadirSeleccionadaActionPerformed(evt);
            }
        });

        buttonEliminarSeleccionada.setText("Eliminar seleccionada");
        buttonEliminarSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarSeleccionadaActionPerformed(evt);
            }
        });

        buttonEditarSeleccionada.setText("Editar seleccionada");
        buttonEditarSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarSeleccionadaActionPerformed(evt);
            }
        });

        buttonPreferencia.setText("Cambiar preferencia");
        buttonPreferencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	buttonPreferenciaActionPerformed(evt);
            }
        });

        buttonSexo.setText("Cambiar sexo");
        buttonSexo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	buttonSexoActionPerformed(evt);
            }
        });

        jLabel5.setText("Sexo:");

        jLabel6.setText("Busca:");

        labelSexo.setText("????");

        labelPreferencia.setText("????");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneDescripcion)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(avatarPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonFechaNacimiento, 200, 200, 200)
                                    .addComponent(buttonAvatar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelEdad,100,100,100)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNombre, 100,100,100)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                                .addComponent(buttonNombre, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPaneAficiones)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelPreferencia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelSexo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonPreferencia, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonSexo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonEditarSeleccionada, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonEliminarSeleccionada, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(buttonAñadirSeleccionada, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(avatarPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombre)
                            .addComponent(buttonNombre))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelEdad)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonFechaNacimiento)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAvatar)
                    .addComponent(jLabel3))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAñadirSeleccionada)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEliminarSeleccionada)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEditarSeleccionada))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(scrollPaneAficiones, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSexo)
                    .addComponent(jLabel5)
                    .addComponent(labelSexo))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonPreferencia)
                    .addComponent(jLabel6)
                    .addComponent(labelPreferencia))
                .addContainerGap())
        );

    }            
    
    /**
	 * Action performed for "CAMBIAR NOMBRE" button
	 * 
	 * @param evt
	 */
    private void buttonNombreActionPerformed(ActionEvent evt)
    {                                             
    	String nombre = (String) JOptionPane.showInputDialog(null, "Introduzca un nombre:",
    			"Cambiar nombre", JOptionPane.QUESTION_MESSAGE,null,null,labelNombre.getText());
        if(nombre != null)
        {
        	labelNombre.setText(nombre);
        	this.editUserProfileController.updateName(nombre);
        }
        
    }
    
    /**
	 * Action performed for "CAMBIAR FECHA DE NACIMIENTO" button
	 * 
	 * @param evt
	 */
    private void buttonFechaNacimientoActionPerformed(ActionEvent evt)
    {                                               
    	JDateChooser jd = new JDateChooser();
    	String message ="Introduzca su fecha de nacimiento:";
    	Object[] params = {message,jd};
    	JOptionPane.showConfirmDialog(null,params,"Fecha de nacimiento", JOptionPane.PLAIN_MESSAGE);
    	
    	Date fecha = jd.getDate();
    	if(fecha != null)
    	{
    		Integer años = getAge(fecha);
        	labelEdad.setText(años + " años");
        	this.editUserProfileController.updateBirth(fecha);
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Introduce una fecha valida");
    	}
    	
    }
    
    /**
	 * Action performed for "CAMBIAR SEXO" button
	 * 
	 * @param evt
	 */
    private void buttonSexoActionPerformed(ActionEvent evt)
    {                                             
    	String genero = (String) JOptionPane.showInputDialog(null, "Introduzca su sexo", "Cambiar sexo",
    			JOptionPane.QUESTION_MESSAGE, null, sexo, sexo[0]);
    	
    	if(genero != null)
    	{
    		labelSexo.setText(genero);
    		this.editUserProfileController.updateGender(Sexo.getSexoFromString(genero));
    	}
    		
    }

    /**
	 * Action performed for "CAMBIAR PREFERENCIA" button
	 * 
	 * @param evt
	 */
    private void buttonPreferenciaActionPerformed(ActionEvent evt)
    {                                             
    	String inclinacion = (String) JOptionPane.showInputDialog(null, "Introduzca su preferencia",
    			"Cambiar preferencia", JOptionPane.QUESTION_MESSAGE, null, preferencia, preferencia[0]);
    	if(inclinacion != null)
    	{
    		labelPreferencia.setText(inclinacion);
    		this.editUserProfileController.updatePreference(Preferencia.getPreferenciaFromString(inclinacion));
    	}
    		
    }
    
    /**
	 * Action performed for "CAMBIAR AVATAR" button
	 * 
	 * @param evt
     * @throws IOException 
	 */
    private void buttonAvatarActionPerformed(ActionEvent evt) throws IOException
    {
    	JFileChooser elegir = new JFileChooser();
        int opcion = elegir.showOpenDialog(buttonAvatar);

        if (opcion == JFileChooser.APPROVE_OPTION)
        {
            String path = elegir.getSelectedFile().getAbsolutePath(); 
            this.avatarPanel1.setIcon(new ImageIcon(path));
            this.editUserProfileController.updateAvatar(path);
        }
    }
    
    /**
     * Converts birth date into age
     * 
     * @param fecha
     * @return Year´s birth
     */
    private Integer getAge(Date fecha)
	{
		Calendar birth = Calendar.getInstance();
		birth.setTime(fecha);
		Calendar today = Calendar.getInstance();

		int year = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE) - birth.get(Calendar.DATE);

        if (month < 0 || (month == 0 && day < 0)){
        	year--;
        }
		
		return year;
	}
    
    /**
	 * Action performed for "AÑADIR SELECCIONADA" button
	 * 
	 * @param evt
	 */
    private void buttonAñadirSeleccionadaActionPerformed(ActionEvent evt)
    {                                                         
    	String aficion = (String) JOptionPane.showInputDialog(null, "Introduzca una aficion:",
    			"Nueva aficion", JOptionPane.QUESTION_MESSAGE);
        if(aficion != null)
        {
        	modelAficiones.addElement(aficion);
        	listAficiones.setModel(modelAficiones);
	    	this.editUserProfileController.updateHobbies(Collections.list(modelAficiones.elements()));
        }
    }
    
    /**
	 * Action performed for "ELIMINAR SELECCIONADA" button
	 * 
	 * @param evt
	 */
    private void buttonEliminarSeleccionadaActionPerformed(ActionEvent evt)
    {                                                           
    	int selectedIndex = listAficiones.getSelectedIndex();
    	
    	if(selectedIndex != -1)
    	{
	    	if (selectedIndex != -1) {
	    		modelAficiones.remove(selectedIndex);
	    		listAficiones.setModel(modelAficiones);
	    	}
	    	this.editUserProfileController.updateHobbies(Collections.list(modelAficiones.elements()));
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Selecciona una aficion para eliminar");
    	}
    }
    
    /**
	 * Action performed for "EDITAR SELECCIONADA" button
	 * 
	 * @param evt
	 */
    private void buttonEditarSeleccionadaActionPerformed(ActionEvent evt)
    {
    	int selectedIndex = listAficiones.getSelectedIndex();
    	
    	if(selectedIndex != -1)
    	{
    		String aficion = (String) JOptionPane.showInputDialog(null, "Introduzca una aficion:",
        			"Editar aficion", JOptionPane.QUESTION_MESSAGE,null,null,listAficiones.getSelectedValue());
            if(aficion != null)
            {
            	modelAficiones.remove(selectedIndex);
            	modelAficiones.add(selectedIndex, aficion);
            	listAficiones.setModel(modelAficiones);
    	    	this.editUserProfileController.updateHobbies(Collections.list(modelAficiones.elements()));
            }
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Selecciona una aficion para editar");
    	}
    }

    /**
     * Get description text
     * 
     * @return
     */
	public String getTextPaneDescripcion()
	{
		return textPaneDescripcion.getText();
	}
                 
}
