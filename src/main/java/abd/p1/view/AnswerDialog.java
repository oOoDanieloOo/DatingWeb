package abd.p1.view;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import abd.p1.controller.MainWindowController;
import abd.p1.model.Opcion;
import abd.p1.model.Pregunta;
import abd.p1.view.renderer.AnswerCellRenderer;

public class AnswerDialog extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private JButton buttonResponder;
	private JButton buttonInvitarAmigo;
	private JLabel labelEnunciado;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	private JSlider sliderValoracion;
	private JList<Opcion> listaOpciones;
	
	private Pregunta pregunta;
	private List<Opcion> opciones;
    private DefaultListModel<Opcion> modelOpciones;
	
	private MainWindowController mainWindowController;

	/**
	 * Main constructor
	 * 
	 * @param parent
	 * @param modal
	 */
    public AnswerDialog(Frame parent, boolean modal, MainWindowController mainWindowController, Pregunta pregunta)
    {
        super(parent, modal);
        this.mainWindowController = mainWindowController;
        this.pregunta = pregunta;
        initComponents();
        initView();
    }

    private void initView()
    {
    	this.opciones = mainWindowController.getAnswers(this.pregunta.getId());
    	if (opciones != null)
    	{
    		this.labelEnunciado.setText(this.pregunta.getEnunciado());
    		for (Opcion pregunta : opciones)
    		{
    			modelOpciones.addElement(pregunta);
            }
    		listaOpciones.setModel(modelOpciones);
    		listaOpciones.setCellRenderer(new AnswerCellRenderer());
    	}
	}

	/**
     * Initialize components
     */
    private void initComponents()
    {

        labelEnunciado = new JLabel();
        jScrollPane1 = new JScrollPane();
        listaOpciones = new JList<>();
        jLabel2 = new JLabel();
        sliderValoracion = new JSlider();
        buttonResponder = new JButton();
        buttonInvitarAmigo = new JButton();
        modelOpciones = new DefaultListModel<Opcion>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Responder pregunta");

        labelEnunciado.setFont(new Font("Tahoma", 1, 18));
        labelEnunciado.setText("¿Cuál es tu color favorito?");

        jScrollPane1.setViewportView(listaOpciones);

        jLabel2.setText("Relevancia:");

        sliderValoracion.setMajorTickSpacing(5);
        sliderValoracion.setMaximum(10);
        sliderValoracion.setMinorTickSpacing(1);
        sliderValoracion.setPaintLabels(true);
        sliderValoracion.setPaintTicks(true);
        sliderValoracion.setValue(5);

        buttonResponder.setText("Responder");
        buttonResponder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResponderActionPerformed(evt);
            }
        });

        buttonInvitarAmigo.setText("Invitar a un amigo");
        buttonInvitarAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInvitarAmigoActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelEnunciado)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderValoracion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(buttonResponder)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonInvitarAmigo)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelEnunciado)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(sliderValoracion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buttonResponder)
                    .addComponent(buttonInvitarAmigo))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        this.setLocationRelativeTo(null);
    }
    
    /**
	 * Action performed for "RESPONDER" button
	 * 
	 * @param evt
	 */
    private void buttonResponderActionPerformed(ActionEvent evt)
    {                                                
        if (this.listaOpciones.getSelectedValue() != null)
        {
        	this.dispose();
            this.mainWindowController.commitAnswer(this.listaOpciones.getSelectedValue(), this.sliderValoracion.getValue());
        }
        else
        {
        	JOptionPane.showMessageDialog(null, "Selecciona alguna de las opciones");
        }
    }                                               

    /**
	 * Action performed for "INVITAR A UN AMIGO" button
	 * 
	 * @param evt
	 */
    private void buttonInvitarAmigoActionPerformed(ActionEvent evt)
    {                                                   
        mainWindowController.inviteAnswerQuestion(this.pregunta);
    }

}

