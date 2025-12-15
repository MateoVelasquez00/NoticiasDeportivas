package com.boya.noticiasdep.model;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "usuarios")
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    
    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;
    private String telefono;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol") // <-- Asume que la columna FK en 'usuarios' se llama id_rol
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Noticia> noticias;
    
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Comentario> comentarios;
    
    //Contructor Vacio
    @JsonCreator
	public Usuario() {
    	
	}

	public Usuario(Integer id, String nombre, String email, String username, String password, String telefono,
			Rol rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.username = username;
		this.password = password;
		this.telefono = telefono;
		this.rol = rol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", telefono=" + telefono + ", rol=" + rol + "]";
	}

	

	
	
}