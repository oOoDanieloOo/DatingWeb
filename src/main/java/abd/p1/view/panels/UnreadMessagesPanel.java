package abd.p1.view.panels;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import abd.p1.controller.MainWindowController;

public class UnreadMessagesPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private JButton buttonMarkAsRead;
	private JScrollPane jScrollPane1;
	private JTextPane textPaneChat;
	
	private MainWindowController mainWindowController;

    public UnreadMessagesPanel(MainWindowController mainWindowController)
    {
    	this.mainWindowController = mainWindowController;
    	initComponents();
    	initChat();
    }
            
    /**
     * Initialize chat with ALL received messages not read
     */
    private void initChat()
    {
    	String messages = this.mainWindowController.getAllMessagesNotRead();
    	
    	if (messages != null)
    	{
    		this.textPaneChat.setText(messages);
    	}
    	
	}

	private void initComponents()
    {

       jScrollPane1 = new JScrollPane();
       textPaneChat = new JTextPane();
       buttonMarkAsRead = new JButton();
       
       textPaneChat.setEditable(false);
       textPaneChat.setContentType("text/html");
       textPaneChat.addHyperlinkListener(new HyperlinkListener() {
           public void hyperlinkUpdate(HyperlinkEvent evt) {
               jTextPane1HyperlinkUpdate(evt);
           }
       });
       
       jScrollPane1.setViewportView(textPaneChat);

       buttonMarkAsRead.setText("Marcar todos como leidos");
       buttonMarkAsRead.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               buttonMarkAsReadActionPerformed(evt);
           }
       });

       GroupLayout layout = new GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addComponent(jScrollPane1)
           .addGroup(layout.createSequentialGroup()
               .addGap(136, 136, 136)
               .addComponent(buttonMarkAsRead)
               .addContainerGap(136, Short.MAX_VALUE))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
               .addComponent(buttonMarkAsRead)
               .addContainerGap())
       );
    }
    
    /**
     * Event needed for hyper link usage 
     * 
     * @param evt
     */
    private void jTextPane1HyperlinkUpdate(HyperlinkEvent evt)
    {             
    	String entry = evt.getDescription();
    	String[] parts = entry.split(",");
    	
    	if(evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
    	{
    		if (parts[0].equals("pregunta"))
    		{
    			this.mainWindowController.hyperLinkOpenQuestion(parts[1]);
    		}
    		
    		if (parts[0].equals("user"))
    		{
    			this.mainWindowController.hyperLinkOpenUser(parts[1]);
    		}
    		
        }
    }
    
    /**
     * Mark all user messages as read
     * 
     * @param evt
     */
    private void buttonMarkAsReadActionPerformed(ActionEvent evt)
    {                                                 
        this.mainWindowController.markAsReadAllMessagesReceived();
    } 
    
}
