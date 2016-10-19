package abd.p1.view.lists;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class ElementAnswerList extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private String opcion;
    private int numeroOrden;
    private JLabel labelNumeroOrden;
    private JLabel labelOpcion;
    
    /**
	 * Main constructor
	 */
    public ElementAnswerList() {
        initComponents();
    }

    /**
     * Option name "getter"
     * 
     * @return
     */
    public String getOpcion() {
        return opcion;
    }

    /**
     * Option name "setter"
     * 
     * @return
     */
    public void setOpcion(String opcion) {
        this.opcion = opcion;
        labelOpcion.setText(opcion);
    }

    /**
     * Option order number "getter"
     * 
     * @return
     */
    public int getNumeroOrden() {
        return numeroOrden;
    }

    /**
     * Option order number "setter"
     * 
     * @return
     */
    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
        labelNumeroOrden.setText(numeroOrden + ".");
    }

    /**
     * Initialize components
     */
    private void initComponents()
    {

        labelNumeroOrden = new JLabel();
        labelOpcion = new JLabel();

        labelNumeroOrden.setText("");

        labelOpcion.setText("");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            		.addGap(3, 3, 3)
                    .addComponent(labelNumeroOrden)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(labelOpcion, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addContainerGap())
            );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            		.addGap(3, 3, 3)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNumeroOrden)
                        .addComponent(labelOpcion))
                    .addGap(3, 3, 3))
        );
    }                                          
                  
}

