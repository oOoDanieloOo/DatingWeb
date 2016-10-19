package abd.p1.view.panels;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import abd.p1.controller.UserProfileController;

public class CompatibilityPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private JLabel jLabel1;
    private JLabel labelPercent;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JList<String> listAficiones;
    
    private UserProfileController userProfileController;
    
    private List<String> aficiones;
    private DefaultListModel<String> modelAficiones;

    public CompatibilityPanel(UserProfileController userProfileController)
    {
    	this.userProfileController = userProfileController;
        initComponents();
        initView();
    }

    /**
     * Initialize view
     */
    private void initView()
    {
    	initCompatibleHobbies();
    	initCompatibilityPercent();
	}
    
    /**
     * Initialize compatible hobbies
     */
    private void initCompatibleHobbies()
    {
    	this.aficiones = userProfileController.getCompatibleHobbies();
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
     * Initialize compatibility percent
     */
    private void initCompatibilityPercent()
    {
    	double percent = this.userProfileController.getCompatibilityPercent();
    	
    	
    	if (percent >= 0)
    	{
    		DecimalFormat decimales = new DecimalFormat("0");
    		this.labelPercent.setText(decimales.format(percent) + "%");
    	}
    	else
    	{
    		this.labelPercent.setText("0%");
    	}
    }

    /**
     * Initialize components
     */
	private void initComponents()
    {

	   jLabel1 = new JLabel();
       labelPercent = new JLabel();
       jLabel3 = new JLabel();
       jScrollPane1 = new JScrollPane();
       listAficiones = new JList<>();
       modelAficiones = new DefaultListModel<String>();

       jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); 
       jLabel1.setText("Tu nivel de compatibilidad es de:");

       labelPercent.setFont(new java.awt.Font("Tahoma", 1, 36)); 
       labelPercent.setText("60%");

       jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); 
       jLabel3.setText("Intereses comunes:");

       jScrollPane1.setViewportView(listAficiones);

       GroupLayout layout = new GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                   .addComponent(jScrollPane1)
                   .addGroup(layout.createSequentialGroup()
                       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                               .addGap(204, 204, 204)
                               .addComponent(labelPercent))
                           .addComponent(jLabel3)
                           .addComponent(jLabel1))
                       .addGap(0, 210, Short.MAX_VALUE)))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jLabel1)
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(labelPercent, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(jLabel3)
               .addGap(24,24,24)
               .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
       );
    }                     

}
