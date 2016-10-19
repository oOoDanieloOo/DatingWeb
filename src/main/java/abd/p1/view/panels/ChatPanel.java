package abd.p1.view.panels;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.text.BadLocationException;

import abd.p1.controller.UserProfileController;
import abd.p1.model.Usuario;

public class ChatPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JTextField textFieldSendMessage;
	private JTextPane textPaneChat;

	private Usuario usuario;
	private UserProfileController userProfileController;
	private String chatMessage;
	
	/**
	 * Default constructor
	 */
    public ChatPanel(UserProfileController userProfileController, Usuario usuario)
    {
    	this.userProfileController = userProfileController;
    	this.usuario = usuario;
    	initComponents();
    	initChat();
    }

    /**
     * Initialize all user messages
     */
    private void initChat()
    {
    	String messages = this.userProfileController.getAllTextMessages();
    	
    	
    	if (messages != null)
    	{
    		this.textPaneChat.setText(messages);
    	}
    	
    	this.chatMessage = messages;
	}

	/**
     * Initialize components
     */
    private void initComponents()
    {

       jScrollPane1 = new JScrollPane();
       textPaneChat = new JTextPane();
       jLabel1 = new JLabel();
       textFieldSendMessage = new JTextField();

       textPaneChat.setContentType("text/html");
       
       jScrollPane1.setViewportView(textPaneChat);
       
       textFieldSendMessage.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
				textFieldSendMessageActionPerformed(evt);
           }
       });

       jLabel1.setText("Enviar mensaje:");

       GroupLayout layout = new GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                   .addComponent(jScrollPane1)
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(jLabel1)
                       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(textFieldSendMessage, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel1)
                   .addComponent(textFieldSendMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
       );
    }  
    
    /**
     * Action performed for text field(SEND MESSAGE) when user press enter
     * 
     * @param evt
     * @throws BadLocationException 
     * @throws IOException 
     */
    private void textFieldSendMessageActionPerformed(ActionEvent evt)
    {   
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("[dd/MM/yyyy - HH:mm]"); 
    	String date = dateFormat.format(new Date());

    	String redColorStart = "<font color=\"red\">";
    	String redColorEnd = "</font><br>";
    	
    	String preMessage = this.textFieldSendMessage.getText();
    	String postMessage = redColorStart + date + " "
    						+ this.usuario.getNombre() + " escribió: "
    						+ redColorEnd + preMessage + "<br>";
    	
    	if (!preMessage.equals(""))
    	{
    		this.chatMessage += postMessage;
    		this.userProfileController.sendSingleMessage(preMessage);
            this.textPaneChat.setText(chatMessage);
            this.textFieldSendMessage.setText("");
    	}
    }

}

