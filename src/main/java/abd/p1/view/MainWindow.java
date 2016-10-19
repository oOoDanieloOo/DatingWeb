package abd.p1.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import abd.p1.controller.MainWindowController;
import abd.p1.view.panels.QuestionPanel;
import abd.p1.view.panels.SearchPanel;
import abd.p1.view.panels.UnreadMessagesPanel;

public class MainWindow extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private JButton buttonModificarPerfil;
	private JButton buttonVerPerfil;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JTabbedPane jTabbedPane1;
    private QuestionPanel questionPanel1;
    private SearchPanel searchPanel1;
    private UnreadMessagesPanel unreadMessagesPanel1;
    
    private MainWindowController mainWindowController;

	/**
	 * Main constructor
	 */
	public MainWindow(Frame parent, boolean modal, MainWindowController mainWindowController)
	{
		super(parent, modal);
		this.mainWindowController = mainWindowController;
		initComponents();
    }
	
	/**
	 * Initialize components
	 */
    private void initComponents()
    {

       jTabbedPane1 = new JTabbedPane();
       jPanel1 = new JPanel();
       searchPanel1 = new SearchPanel(mainWindowController);
       jPanel2 = new JPanel();
       buttonModificarPerfil = new JButton();
       buttonVerPerfil = new JButton();
       jPanel3 = new JPanel();
       questionPanel1 = new QuestionPanel(mainWindowController);
       jPanel4 = new JPanel();
       unreadMessagesPanel1 = new UnreadMessagesPanel(mainWindowController);

       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       setTitle("Ventana principal");

       buttonModificarPerfil.setText("Modificar mi perfil");
       buttonModificarPerfil.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   buttonModificarPerfilActionPerformed(evt);
           }
       });

       buttonVerPerfil.setText("Ver perfil seleccionado");
       buttonVerPerfil.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   buttonVerPerfilActionPerformed(evt);
           }
       });

       GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
       jPanel2.setLayout(jPanel2Layout);
       jPanel2Layout.setHorizontalGroup(
           jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(jPanel2Layout.createSequentialGroup()
               .addGap(85, 85, 85)
               .addComponent(buttonModificarPerfil)
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(buttonVerPerfil)
               .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       jPanel2Layout.setVerticalGroup(
           jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               .addComponent(buttonModificarPerfil)
               .addComponent(buttonVerPerfil))
       );

       GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
       jPanel1.setLayout(jPanel1Layout);
       jPanel1Layout.setHorizontalGroup(
    		   jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                   .addContainerGap()
                   .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                       .addComponent(searchPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                   .addContainerGap())
           );
           jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                   .addContainerGap()
                   .addComponent(searchPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                   .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addContainerGap())
           );

       jTabbedPane1.addTab("Usuarios", jPanel1);

       GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
       jPanel3.setLayout(jPanel3Layout);
       jPanel3Layout.setHorizontalGroup(
           jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
               .addContainerGap(15,15)
               .addComponent(questionPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addContainerGap())
       );
       jPanel3Layout.setVerticalGroup(
           jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(jPanel3Layout.createSequentialGroup()
               .addContainerGap(15,15)
               .addComponent(questionPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );

       jTabbedPane1.addTab("Preguntas", jPanel3);

       GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
       jPanel4.setLayout(jPanel4Layout);
       jPanel4Layout.setHorizontalGroup(
           jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
               .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(unreadMessagesPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addContainerGap())
       );
       jPanel4Layout.setVerticalGroup(
           jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
               .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(unreadMessagesPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addContainerGap())
       );

       jTabbedPane1.addTab("Mensajes no leidos", jPanel4);

       GroupLayout layout = new GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addComponent(jTabbedPane1)
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addComponent(jTabbedPane1)
       );

       pack();
       this.setLocationRelativeTo(null);
    }                        

    /**
     * Action performed for "MODIFICAR MI PERFIL" button
     * 
     * @param evt
     */
    private void buttonModificarPerfilActionPerformed(ActionEvent evt)
    {                                             
	    mainWindowController.modificarPerfil();
    }

    /**
     * Action performed for "VER PERFIL SELECCIONADO" button
     * 
     * @param evt
     */
    private void buttonVerPerfilActionPerformed(ActionEvent evt)
    {                                             
    	mainWindowController.verPerfilSeleccionado(this.searchPanel1.getSelectedUser());
    }
         
}
