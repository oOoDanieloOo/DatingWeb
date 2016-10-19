package abd.p1.view.lists;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class ElementQuestionList extends JPanel
{

	private static final long serialVersionUID = 1L;
	private String enunciado;
	private int numeroOpciones;
	
	private JLabel labelEnunciado;
	private JLabel labelNumOpciones;

	/**
	 * Main constructor
	 */
    public ElementQuestionList() {
        initComponents();
    }

    /**
     * Question name "getter"
     * 
     * @return
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Question name "setter"
     * 
     * @param enunciado
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
        labelEnunciado.setText(enunciado);
    }

    /**
     * Option number "getter"
     * 
     * @return
     */
    public int getNumeroOpciones() {
        return numeroOpciones;
    }

    /**
     * Option number "setter"
     * 
     * @param numeroOpciones
     */
    public void setNumeroOpciones(int numeroOpciones) {
        this.numeroOpciones = numeroOpciones;
        labelNumOpciones.setText(numeroOpciones + " opciones");
    }

    /**
     * Initialize components
     */
    private void initComponents()
    {

       labelEnunciado = new JLabel();
       labelNumOpciones = new JLabel();

       labelEnunciado.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
       labelEnunciado.setText("Enunciado");

       labelNumOpciones.setText("3 opciones");

       GroupLayout layout = new GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                   .addComponent(labelEnunciado)
                   .addComponent(labelNumOpciones))
               .addContainerGap(100, Short.MAX_VALUE))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(labelEnunciado)
               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(labelNumOpciones)
               .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
    }                      
                  
}