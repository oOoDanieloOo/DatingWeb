package abd.p1.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MensajePregunta extends Mensaje
{
	@ManyToOne
	private Pregunta pregunta;
	
	public MensajePregunta(){
		super();
	}
	

	public MensajePregunta(Usuario emisor, Usuario receptor, Calendar hora, boolean leido, Pregunta pregunta)
	{
		super(emisor, receptor, hora, leido);
		this.pregunta = pregunta;
	}

	public MensajePregunta(Pregunta pregunta) {
		super();
		this.pregunta = pregunta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	@Override
	public String toString()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("[dd/MM/yyyy - HH:mm]");
		String redColorStart = "<font color=\"red\">";
		String redColorEnd = "</font><br>";
		String blueColorStart = "<a href=\"";
		String blueColorMiddle = "\">";
    	String blueColorEnd = "</a><br>";
    	
		return redColorStart + dateFormat.format(getHora().getTime()) + " " + getEmisor().getNombre() + " te ha invitado a responder la pregunta:" + redColorEnd 
				+ blueColorStart + "pregunta," + this.pregunta.getId() + blueColorMiddle + this.pregunta.getEnunciado() + blueColorEnd;
	}

}
