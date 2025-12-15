package com.boya.noticiasdep.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "noticia_id", nullable = false)
    private Noticia noticia;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    //Constructor vacio
	public Comentario() {
		
	}

	public Comentario(Long id, String texto, LocalDate fecha, Noticia noticia, Usuario usuario) {
		super();
		this.id = id;
		this.texto = texto;
		this.fecha = fecha;
		this.noticia = noticia;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", fecha=" + fecha + "]";
	}

}

