package abd.p1.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Valoracion
{
	@EmbeddedId
	private ValoracionUsuario id;
	
	@Column(nullable = false)
	private Integer valoracionNumero;
	
	public Valoracion(){
		
	}

	public Valoracion(ValoracionUsuario id, Integer valoracionNumero) {
		super();
		this.id = id;
		this.valoracionNumero = valoracionNumero;
	}

	public ValoracionUsuario getId() {
		return id;
	}

	public void setId(ValoracionUsuario id) {
		this.id = id;
	}

	public Integer getValoracionNumero() {
		return valoracionNumero;
	}

	public void setValoracionNumero(Integer valoracionNumero) {
		this.valoracionNumero = valoracionNumero;
	}
	
}
