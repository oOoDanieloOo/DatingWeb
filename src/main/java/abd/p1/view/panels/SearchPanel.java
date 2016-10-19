package abd.p1.view.panels;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import abd.p1.controller.MainWindowController;
import abd.p1.model.Usuario;
import abd.p1.view.renderer.UserCellRenderer;

public class SearchPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private JCheckBox checkBoxFiltrarPorNombre;
	private JCheckBox checkBoxMostrarSoloAmigos;
	private JList<Usuario> listaUsuarios;
	private JScrollPane jScrollPane1;
	private JTextField textFieldFiltrar;
	private DefaultListModel<Usuario> modelUsuarios;
	
	private MainWindowController mainWindowController;

	/**
	 * Default constructor
	 */
    public SearchPanel(MainWindowController mainWindowController)
    {
    	
    	this.mainWindowController = mainWindowController;
        initComponents();
    }

    /**
	 * Initialize components
	 */
    private void initComponents()
    {

       jScrollPane1 = new JScrollPane();
       listaUsuarios = new JList<>();
       checkBoxFiltrarPorNombre = new JCheckBox();
       textFieldFiltrar = new JTextField();
       checkBoxMostrarSoloAmigos = new JCheckBox();
       modelUsuarios = new DefaultListModel<>();
       
       textFieldFiltrar.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               textFieldFiltrarActionPerformed(evt);
           }
       });

       jScrollPane1.setViewportView(listaUsuarios);

       checkBoxFiltrarPorNombre.setText("Filtrar por nombre:");

       checkBoxMostrarSoloAmigos.setText("Mostrar solo amigos");

       GroupLayout layout = new GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
           .addGroup(layout.createSequentialGroup()
               .addComponent(checkBoxMostrarSoloAmigos)
               .addContainerGap(304, Short.MAX_VALUE))
           .addGroup(layout.createSequentialGroup()
               .addComponent(checkBoxFiltrarPorNombre)
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(textFieldFiltrar))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(checkBoxFiltrarPorNombre)
                   .addComponent(textFieldFiltrar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(checkBoxMostrarSoloAmigos)
               .addContainerGap())
       );
    }
	
	/**
	 * Show users that match with filter text
	 * 
	 * @param evt
	 */
	private void textFieldFiltrarActionPerformed(ActionEvent evt)
	{            
		List<Usuario> usuarios = null;
		this.modelUsuarios.clear();
		
		if (checkBoxFiltrarPorNombre.isSelected() && !checkBoxMostrarSoloAmigos.isSelected())
		{
			usuarios = this.mainWindowController.filterByNameAndDistance(this.textFieldFiltrar.getText());
		}
		
		if (checkBoxMostrarSoloAmigos.isSelected() && !checkBoxFiltrarPorNombre.isSelected())
		{
			usuarios = this.mainWindowController.filterByFriendsOnly();
		}
		
		if (checkBoxFiltrarPorNombre.isSelected() && checkBoxMostrarSoloAmigos.isSelected())
		{
			usuarios = this.mainWindowController.filterByNameDistanceAndFriends(this.textFieldFiltrar.getText());
		}
		
		if (!checkBoxFiltrarPorNombre.isSelected() && !checkBoxMostrarSoloAmigos.isSelected())
		{
			usuarios = this.mainWindowController.filterByDistanceOnly();
		}
		
		if (usuarios != null)
    	{
    		for (Usuario usuario : usuarios)
    		{
            	this.modelUsuarios.addElement(usuario);
            }
        	listaUsuarios.setModel(modelUsuarios);
	        listaUsuarios.setCellRenderer(new UserCellRenderer());
    	}
    }
	
	/**
	 * Get selected user profile 
	 */
	public Usuario getSelectedUser()
	{
		return this.listaUsuarios.getSelectedValue();
	}
	
}