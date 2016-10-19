package abd.p1.view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import abd.p1.controller.UserProfileController;

public class DistanceFriendRequestPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private JButton buttonPeticionAmistad;
	private JLabel jLabel1;
	private JLabel labelDistance;
	
	private UserProfileController userProfileController;

	/**
	 * Main constructor
	 */
	public DistanceFriendRequestPanel(UserProfileController userProfileController)
	{
		this.userProfileController = userProfileController;
		initComponents();
		initDistance();
	}

	/**
	 * Initialize components
	 */
	private void initComponents()
	{

       buttonPeticionAmistad = new JButton();
       jLabel1 = new JLabel();
       labelDistance = new JLabel();

       buttonPeticionAmistad.setText("Enviar peticion de amistad");
       buttonPeticionAmistad.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   buttonPeticionAmistadActionPerformed(evt);
           }
       });

       jLabel1.setText("Distancia:");

       GroupLayout layout = new GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        		   .addGap(12, 12, 12)
                   .addComponent(jLabel1)
                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                   .addComponent(labelDistance, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                   .addComponent(buttonPeticionAmistad)
                   .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
               .addGap(0, 0, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(buttonPeticionAmistad)
                   .addComponent(jLabel1)
                   .addComponent(labelDistance)))
       );
	}                       

	/**
     * Action performed for "ENVIAR PETICION DE AMISTAD" button
     * 
     * @param evt
     */
	private void buttonPeticionAmistadActionPerformed(ActionEvent evt)
	{                                         
		if (!this.userProfileController.makeFriend())
		{
			JOptionPane.showMessageDialog(null, "Peticion de amistad enviada", "Peticion de amistad", JOptionPane.INFORMATION_MESSAGE);
			this.userProfileController.sendFriendMessage();
		}
		else
			JOptionPane.showMessageDialog(null, "¡Ya erais amigos!, no seas un pelota...");
	}
	
	/**
	 * Initialize distance between users
	 */
	private void initDistance()
	{
		labelDistance.setText(this.userProfileController.getDistance());
	}
	
}
