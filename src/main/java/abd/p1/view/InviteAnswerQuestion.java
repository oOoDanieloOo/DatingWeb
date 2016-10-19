package abd.p1.view;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import abd.p1.controller.MainWindowController;
import abd.p1.model.Pregunta;
import abd.p1.view.panels.SearchPanel;

public class InviteAnswerQuestion extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private JButton buttonAceptar;
    private JButton buttonCancelar;
    private JLabel jLabel1;
    private SearchPanel searchPanel1;
    
    private MainWindowController mainWindowController;
    private Pregunta pregunta;

    /**
     * Main constructor
     * 
     * @param parent
     * @param modal
     */
	public InviteAnswerQuestion(Frame parent, boolean modal, MainWindowController mainWindowController, Pregunta pregunta)
	{
        super(parent, modal);
        this.pregunta = pregunta;
        this.mainWindowController = mainWindowController;
        initComponents();
    }

	/**
	 * Initializes components
	 */
    private void initComponents()
    {

        jLabel1 = new JLabel();
        searchPanel1 = new SearchPanel(mainWindowController);
        buttonAceptar = new JButton();
        buttonCancelar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Invitacion a contestar una pregunta");

        jLabel1.setFont(new Font("Tahoma", 0, 12));
        jLabel1.setText("Selecciona el usuario al que deseas invitar:");

        buttonAceptar.setText("Aceptar");
        buttonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAceptarActionPerformed(evt);
            }
        });

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(buttonAceptar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAceptar)
                    .addComponent(buttonCancelar))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        dispose();
        this.mainWindowController.sendQuestionMessage(this.searchPanel1.getSelectedUser(), this.pregunta);
    }                                             

    /**
     * Action performed for "CANCELAR" button
     * 
     * @param evt
     */
    private void buttonCancelarActionPerformed(ActionEvent evt)
    {                                               
        dispose();
    }
                  
}