package com.boya.noticiasdep.service;

import java.util.List;
import java.util.Optional;

import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Usuario;

public interface INoticiaService {

	public Noticia save(Noticia noticia);

	public List<Noticia> findAll();

	public List<Noticia> findByUsuario(Usuario usuario);

	public Optional<Noticia> get(Integer id);
	
	public void update(Noticia noticia);
	
	public void delete(Integer Id);

}
