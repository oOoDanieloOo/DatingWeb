package abd.p1.view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import abd.p1.controller.MainWindowController;
import abd.p1.model.Pregunta;
import abd.p1.view.renderer.QuestionCellRenderer;

public class QuestionPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private JButton buttonResponder;
    private JButton buttonPreguntaAleatoria;
    private JLabel jLabel1;
    private JList<Pregunta> listaPreguntas;
    private JScrollPane jScrollPane1;
    
    private List<Pregunta> preguntas;
    private DefaultListModel<Pregunta> modelPreguntas;
	
	private MainWindowController mainWindowController;

    public QuestionPanel(MainWindowController mainWindowController)
    {
    	this.mainWindowController = mainWindowController;
    	initComponents();
    	initView();
    }
                         
    private void initView()
    {
    	this.modelPreguntas.clear();
    	this.preguntas = mainWindowController.getQuestions();
    	if (preguntas != null)
    	{
    		for (Pregunta pregunta : preguntas)
    		{
    			modelPreguntas.addElement(pregunta);
            }
    		listaPreguntas.setModel(modelPreguntas);
    		listaPreguntas.setCellRenderer(new QuestionCellRenderer());
    	}
	}

	private void initComponents()
    {

       jScrollPane1 = new JScrollPane();
       listaPreguntas = new JList<Pregunta>();
       jLabel1 = new JLabel();
       buttonResponder = new JButton();
       buttonPreguntaAleatoria = new JButton();
       modelPreguntas = new DefaultListModel<Pregunta>();

       jScrollPane1.setViewportView(listaPreguntas);

       jLabel1.setText("Mejor valoradas:");

       buttonResponder.setText("Responder");
       buttonResponder.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   buttonResponderActionPerformed(evt);
           }
       });

       buttonPreguntaAleatoria.setText("Pregunta aleatoria");
       buttonPreguntaAleatoria.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   buttonPreguntaAleatoriaActionPerformed(evt);
           }
       });

       GroupLayout layout = new GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
           .addGroup(layout.createSequentialGroup()
               .addComponent(jLabel1)
               .addGap(0, 0, Short.MAX_VALUE))
           .addGroup(layout.createSequentialGroup()
               .addGap(108, 108, 108)
               .addComponent(buttonResponder)
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(buttonPreguntaAleatoria)
               .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addComponent(jLabel1)
               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
               .addGap(18, 18, 18)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(buttonResponder)
                   .addComponent(buttonPreguntaAleatoria))
               .addContainerGap())
       );
    }                       

	/**
     * Action performed for "RESPONDER" button
     * 
     * @param evt
     */
    private void buttonResponderActionPerformed(ActionEvent evt)
    {    
        this.mainWindowController.contestarPregunta(listaPreguntas.getSelectedValue());
        this.initView();
    }

    /**
     * Action performed for "PREGUNTA ALEATORIA" button
     * 
     * @param evt
     */
    private void buttonPreguntaAleatoriaActionPerformed(ActionEvent evt)
    {                                         
    	Pregunta pregunta = this.mainWindowController.getRandomQuestion();
    	
    	this.mainWindowController.contestarPregunta(pregunta);
    	this.initView();
    }
                   
}
