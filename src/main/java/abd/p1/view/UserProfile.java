package abd.p1.view;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import abd.p1.controller.EditUserProfileController;
import abd.p1.controller.UserProfileController;
import abd.p1.model.Usuario;
import abd.p1.view.panels.ChatPanel;
import abd.p1.view.panels.CompatibilityPanel;
import abd.p1.view.panels.DistanceFriendRequestPanel;
import abd.p1.view.panels.UserProfilePanel;

public class UserProfile extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private ChatPanel chatPanel1;
	private CompatibilityPanel compatibilityPanel1;
    private DistanceFriendRequestPanel distanceFriendRequestPanel1;
    private DistanceFriendRequestPanel distanceFriendRequestPanel2;
    private DistanceFriendRequestPanel distanceFriendRequestPanel3;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JTabbedPane jTabbedPane1;
    private UserProfilePanel userProfilePanel1;
    
    private Usuario usuario;
    private Usuario usuarioPrincipal;
    private UserProfileController userProfileController;
    private EditUserProfileController editUserProfileControler;

    /**
     * Main constructor
     * 
     * @param parent
     * @param modal
     */
	public UserProfile(Frame parent, boolean modal, EditUserProfileController editUserProfileControler, UserProfileController userProfileController, Usuario usuario, Usuario usuarioPrincipal)
	{
        super(parent, modal);
        this.usuario = usuario;
        this.usuarioPrincipal = usuarioPrincipal;
        this.editUserProfileControler = editUserProfileControler;
        this.userProfileController = userProfileController;
        initComponents();
    }

	/**
	 * Initialize components
	 */
	private void initComponents()
	{

	   jTabbedPane1 = new JTabbedPane();
       jPanel1 = new JPanel();
       distanceFriendRequestPanel1 = new DistanceFriendRequestPanel(userProfileController);
       jPanel4 = new JPanel();
       userProfilePanel1 = new UserProfilePanel(editUserProfileControler, usuario);
       jPanel2 = new JPanel();
       compatibilityPanel1 = new CompatibilityPanel(userProfileController);
       distanceFriendRequestPanel2 = new DistanceFriendRequestPanel(userProfileController);
       jPanel3 = new JPanel();
       chatPanel1 = new ChatPanel(userProfileController, this.usuarioPrincipal);
       distanceFriendRequestPanel3 = new DistanceFriendRequestPanel(userProfileController);

       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       setTitle("Informacion de perfil");

       jPanel4.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

       userProfilePanel1.setBorder(null);
       userProfilePanel1.setEditable(false);

       GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
       jPanel4.setLayout(jPanel4Layout);
       jPanel4Layout.setHorizontalGroup(
           jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(jPanel4Layout.createSequentialGroup()
               .addComponent(userProfilePanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addContainerGap())
       );
       jPanel4Layout.setVerticalGroup(
           jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
               .addGap(6, 6, Short.MAX_VALUE)
               .addComponent(userProfilePanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       );

       GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
       jPanel1.setLayout(jPanel1Layout);
       jPanel1Layout.setHorizontalGroup(
           jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
               .addGap(0, 0, Short.MAX_VALUE)
               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                   .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(distanceFriendRequestPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
       );
       jPanel1Layout.setVerticalGroup(
           jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(distanceFriendRequestPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );

       jTabbedPane1.addTab("Perfil", jPanel1);

       GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
       jPanel2.setLayout(jPanel2Layout);
       jPanel2Layout.setHorizontalGroup(
    		   jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
               .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                   .addGap(0, 0, Short.MAX_VALUE)
                   .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                       .addComponent(compatibilityPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                       .addComponent(distanceFriendRequestPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
       );
       jPanel2Layout.setVerticalGroup(
    		   jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
               .addGroup(jPanel2Layout.createSequentialGroup()
                   .addComponent(compatibilityPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                   .addComponent(distanceFriendRequestPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );

       jTabbedPane1.addTab("Compatibilidad", jPanel2);

       GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
       jPanel3.setLayout(jPanel3Layout);
       jPanel3Layout.setHorizontalGroup(
               jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
               .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                   .addGap(0, 0, Short.MAX_VALUE)
                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                       .addComponent(chatPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                       .addComponent(distanceFriendRequestPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
           );
           jPanel3Layout.setVerticalGroup(
               jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
               .addGroup(jPanel3Layout.createSequentialGroup()
                   .addComponent(chatPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                   .addComponent(distanceFriendRequestPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                   .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
           );

       jTabbedPane1.addTab("Chat", jPanel3);

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

}
