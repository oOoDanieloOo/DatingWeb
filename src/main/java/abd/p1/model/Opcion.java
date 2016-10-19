package abd.p1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Opcion
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Pregunta preguntaMadre;
	
	@Column(nullable = false)
	private String texto;
	
	@Column(nullable = false)
	private int numeroOrden;
	
	public Opcion() {
		
	}

	public Pregunta getPreguntaMadre() {
		return preguntaMadre;
	}

	public Opcion(Integer id, Pregunta preguntaMadre, String texto, int numeroOrden) {
		super();
		this.id = id;
		this.preguntaMadre = preguntaMadre;
		this.texto = texto;
		this.numeroOrden = numeroOrden;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPreguntaMadre(Pregunta preguntaMadre) {
		this.preguntaMadre = preguntaMadre;
	}

	public int getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + numeroOrden + ") " + texto;
	}
}
