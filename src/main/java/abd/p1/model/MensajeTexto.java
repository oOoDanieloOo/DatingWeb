package abd.p1.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MensajeTexto extends Mensaje
{
	@Column(nullable = false)
	private String texto;
	
	public MensajeTexto(){
		super();
		
	}
	
	public MensajeTexto(Usuario emisor, Usuario receptor, Calendar hora, boolean leido, String texto)
	{
		super(emisor, receptor, hora, leido);
		this.texto = texto;
	}

	public MensajeTexto(String texto) {
		super();
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("[dd/MM/yyyy - HH:mm]");
		String redColorStart = "<font color=\"red\">";
		String redColorEnd = "</font><br>";
    	
		return redColorStart + dateFormat.format(getHora().getTime()) + " " + getEmisor().getNombre() + " escribio:" + redColorEnd
				+ this.texto + "<br>";
	}
	
}
