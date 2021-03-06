package springboot.disney.peliculas.models.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "peliculas")
public class Peliculas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Column(name = "fecha_creacion")
	private String fechaCreacion;
	private int calificacion;
	private String foto;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "peliculas_personajes", joinColumns = @JoinColumn(name = "peliculas_id"),
	inverseJoinColumns = @JoinColumn(name = "personajes_id"), uniqueConstraints = {
	@UniqueConstraint(columnNames = { "peliculas_id", "personajes_id" }) })
	private List<Personajes> personajes;

	public String getFoto() {
		return foto;
	}

	public List<Personajes> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personajes> personajes) {
		this.personajes = personajes;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public Peliculas(String titulo, String fechaCreacion, int calificacion) {
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.calificacion = calificacion;
	}

	public Peliculas() {

	}

}
