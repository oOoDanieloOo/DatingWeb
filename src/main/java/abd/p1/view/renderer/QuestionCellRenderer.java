package abd.p1.view.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abd.p1.model.Pregunta;
import abd.p1.view.lists.ElementQuestionList;

public class QuestionCellRenderer extends ElementQuestionList implements ListCellRenderer<Pregunta>
{

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public QuestionCellRenderer()
	{
        // Empty
    }
    
	/**
	 * Overrides constructor from ListCellRenderer that renders the question cell
	 */
    @Override
    public Component getListCellRendererComponent(
            JList<? extends Pregunta> list,
            Pregunta value, int index,
            boolean isSelected, boolean cellHasFocus)
    {
        this.setEnunciado(value.getEnunciado());
        this.setNumeroOpciones(value.getOpciones().size());
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