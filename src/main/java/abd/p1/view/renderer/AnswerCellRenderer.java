package abd.p1.view.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abd.p1.model.Opcion;
import abd.p1.view.lists.ElementAnswerList;

public class AnswerCellRenderer extends ElementAnswerList implements ListCellRenderer<Opcion>
{

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public AnswerCellRenderer()
	{
        // Empty
    }
    
	/**
	 * Overrides constructor from ListCellRenderer that renders the answer cell
	 */
	@Override
    public Component getListCellRendererComponent(
            JList<? extends Opcion> list,
            Opcion value, int index,
            boolean isSelected, boolean cellHasFocus)
    {
        this.setNumeroOrden(value.getNumeroOrden());
        this.setOpcion(value.getTexto());
        this.setOpaque(true);
        if(isSelected)
        {
            this.setBackground(Color.ORANGE);
        } else
        {
            this.setBackground(Color.WHITE);
        }
        return this;
    }
    
}

