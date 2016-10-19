package abd.p1.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Mensaje 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Usuario emisor;
	
	@ManyToOne
	private Usuario receptor;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar hora;
	
	@Column(nullable = false)
	private boolean leido;
	
	public Mensaje() {
		
	}

	public Mensaje(Integer id, Usuario emisor, Usuario receptor, Calendar hora, boolean leido)
	{
		super();
		this.id = id;
		this.emisor = emisor;
		this.receptor = receptor;
		this.hora = hora;
		this.leido = leido;
	}
	
	public Mensaje(Usuario emisor, Usuario receptor, Calendar hora, boolean leido)
	{
		super();
		this.emisor = emisor;
		this.receptor = receptor;
		this.hora = hora;
		this.leido = leido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}

	public Calendar getHora() {
		return hora;
	}

	public void setHora(Calendar hora) {
		this.hora = hora;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", emisor=" + emisor + ", receptor=" + receptor + ", hora=" + hora + ", leido="
				+ leido + "]";
	}

}
