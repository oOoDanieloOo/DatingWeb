package abd.p1.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Preferencia preferencia;
	
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@Column(nullable = false, length = 50)
	private String pass;
	
	@Column(nullable = true, length = 255)
	private String descripcion;
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(nullable = true)
	@Lob
	private byte[] imagen;
	
	@Column(nullable = false)
	private double longitud;
	
	@Column(nullable = false)
	private double latitud;
	
	@Column(nullable = true)
	@ElementCollection
	private List<String> aficiones;
	
	@Column
	@OneToMany(mappedBy = "id.usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Valoracion> valoraciones;
	
	@Column
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Usuario> contactos;
	
	@Column
	@OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Mensaje> mensajesEnviados;
	
	@Column
	@OneToMany(mappedBy = "receptor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Mensaje> mensajesRecibidos;
	
	public Usuario()
	{
		aficiones = new LinkedList<String>();
		valoraciones = new LinkedList<Valoracion>();
		contactos = new LinkedList<Usuario>();
		mensajesEnviados = new LinkedList<Mensaje>();
		mensajesRecibidos = new LinkedList<Mensaje>();
	}
	
	public Usuario(String email, String pass)
	{
		this.email = email;
		this.pass = pass;
		this.latitud = this.setRandomLatitude();
		this.longitud = this.setRandomLongitude();
		this.fechaNacimiento = new Date();
		this.aficiones = new LinkedList<String>();
		this.contactos = new LinkedList<Usuario>();
	}
	
	public Usuario(byte[] imagen, String nombre, Date fechaNacimiento)
	{
		this.imagen = imagen;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Usuario(Integer id, Sexo sexo, Preferencia preferencia,
			String email, String nombre, String pass, String descripcion,
			Date fecha_nacimimento, byte[] imagen, double longitud,
			double latitud, List<String> aficiones,
			List<Valoracion> valoraciones, List<Usuario> contactos,
			List<Mensaje> mensajesEnviados, List<Mensaje> mensajesRecibidos) {
		super();
		this.id = id;
		this.sexo = sexo;
		this.preferencia = preferencia;
		this.email = email;
		this.nombre = nombre;
		this.pass = pass;
		this.descripcion = descripcion;
		this.fechaNacimiento = fecha_nacimimento;
		this.imagen = imagen;
		this.longitud = longitud;
		this.latitud = latitud;
		this.aficiones = aficiones;
		this.valoraciones = valoraciones;
		this.contactos = contactos;
		this.mensajesEnviados = mensajesEnviados;
		this.mensajesRecibidos = mensajesRecibidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Preferencia getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(Preferencia preferencia) {
		this.preferencia = preferencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_nacimimento() {
		return fechaNacimiento;
	}

	public void setFecha_nacimimento(Date fecha_nacimimento) {
		this.fechaNacimiento = fecha_nacimimento;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public List<String> getAficiones() {
		return aficiones;
	}

	public void setAficiones(List<String> aficiones) {
		this.aficiones = aficiones;
	}

	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public List<Usuario> getContactos() {
		return contactos;
	}

	public void setContactos(List<Usuario> contactos) {
		this.contactos = contactos;
	}

	public List<Mensaje> getMensajesEnviados() {
		return mensajesEnviados;
	}

	public void setMensajesEnviados(List<Mensaje> mensajesEnviados) {
		this.mensajesEnviados = mensajesEnviados;
	}

	public List<Mensaje> getMensajesRecibidos() {
		return mensajesRecibidos;
	}

	public void setMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
		this.mensajesRecibidos = mensajesRecibidos;
	}
	
	/**
	 * Sets random latitude position
	 * 
	 * @return
	 */
	private double setRandomLatitude()
	{
		
		Random latitude = new Random();
		
		double minimo = 40.000000;// North
		double maximo = 41.033333;// West

		return latitude.nextDouble() * (maximo - minimo) + minimo;
	}

	/**
	 * Sets random longitude position
	 * 
	 * @return
	 */
	private double setRandomLongitude()
	{
		
		Random longitude = new Random();
		
		double minimo = -4.083333;// North
		double maximo = -3.000000;// West
		
		return longitude.nextDouble() * (maximo - minimo) + minimo;
	}

}
