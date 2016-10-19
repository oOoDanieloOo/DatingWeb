package abd.p1.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;

@Entity
public class MensajeAmistad extends Mensaje
{

	public MensajeAmistad()
	{
		super();
	}

	public MensajeAmistad(Usuario emisor, Usuario receptor, Calendar hora, boolean leido)
	{
		super(emisor, receptor, hora, leido);
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
    	
		return redColorStart + dateFormat.format(getHora().getTime()) + " Solictud de amistad de:" + redColorEnd 
				+ blueColorStart + "user," + this.getEmisor().getEmail()+ blueColorMiddle + getEmisor().getNombre() + blueColorEnd;
	}
	
}
