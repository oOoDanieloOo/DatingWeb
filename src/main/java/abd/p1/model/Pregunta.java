package abd.p1.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.LinkedList;

@Entity
public class Pregunta
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String enunciado;
	
	@OneToMany(mappedBy = "preguntaMadre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Opcion> opciones;
	
	public Pregunta() {
		opciones = new ArrayList<>();
	}
	
	public Pregunta(Integer id, String enunciado, List<Opcion> opciones) {
		super();
		this.id = id;
		this.enunciado = enunciado;
		this.opciones = opciones;
	}
	
	public Pregunta(Integer id, String enunciado) {
		super();
		this.id = id;
		this.enunciado = enunciado;
		this.opciones = new LinkedList<>();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}
	
	public void addOpcion(Opcion o) {
		o.setPreguntaMadre(this);
		o.setNumeroOrden(opciones.size() + 1);
		opciones.add(o);
	}
	
	public void removeOpcion(Opcion o) {
		int ordenOpcion = o.getNumeroOrden();
		for (int i = ordenOpcion + 1; i <= opciones.size(); i++) {
			opciones.get(i - 1).setNumeroOrden(i - 1);
		}
		o.setPreguntaMadre(null);
		opciones.remove(ordenOpcion - 1);
	}
	
	public int getNumOpciones() {
		return opciones.size();
	}
	
	public Opcion getOpcion(int num) {
		return opciones.get(num - 1);
	}
	
	public void intercambiarOpciones(int i, int j) {
		Opcion opcI = opciones.get(i - 1);
		Opcion opcJ = opciones.get(j - 1);
		opcI.setNumeroOrden(j);
		opcJ.setNumeroOrden(i);
		opciones.set(i - 1, opcJ);
		opciones.set(j - 1, opcI);
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	@Override
	public String toString() {
		return "Pregunta [enunciado=" + enunciado + ", opciones=" + opciones + "]";
	}
}
