package com.boya.noticiasdep.service;

import java.util.List;
import java.util.Optional;

import com.boya.noticiasdep.model.Comentario;
import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Usuario;

public interface IComentarioService {
	
	public Comentario save(Comentario comentario);

	public List<Comentario> findAll();

	public List<Comentario> findByNoticia(Noticia noticia);
	
	public List<Comentario> findByUsuario(Usuario usuario); 

	public Optional<Comentario> findById(Integer id);
	
	public void delete(Integer Id);
	
}
