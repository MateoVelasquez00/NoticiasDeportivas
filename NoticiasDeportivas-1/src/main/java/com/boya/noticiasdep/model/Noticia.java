package com.boya.noticiasdep.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Noticia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String contenido;
	private String imagen;
	private LocalDate fechaPublicacion;

	@OneToMany(mappedBy = "noticia", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comentario> comentario;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	// Constructor vacio
	public Noticia() {

	}

	public Noticia(Integer id, String titulo, String contenido, String imagen, LocalDate fechaPublicacion,
			List<Comentario> comentario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.contenido = contenido;
		this.imagen = imagen;
		this.fechaPublicacion = fechaPublicacion;
		this.comentario = comentario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public List<Comentario> getComentario() {
		return comentario;
	}

	public void setComentario(List<Comentario> comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + ", imagen=" + imagen
				+ ", fechaPublicacion=" + fechaPublicacion + "]";
	}

}
