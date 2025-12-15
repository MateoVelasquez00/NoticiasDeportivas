package com.boya.noticiasdep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boya.noticiasdep.model.Comentario;
import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Usuario;
import com.boya.noticiasdep.repository.ComentarioRepository;

@Service
public class ComentarioServiceImp implements IComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Override
	public Comentario save(Comentario comentario) {
		// TODO Auto-generated method stub
		return comentarioRepository.save(comentario);
	}

	@Override
	public List<Comentario> findAll() {
		// TODO Auto-generated method stub
		return comentarioRepository.findAll();
	}

	@Override
	public List<Comentario> findByNoticia(Noticia noticia) {
		// TODO Auto-generated method stub
		return comentarioRepository.findByNoticia(noticia);
	}

	@Override
	public List<Comentario> findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return comentarioRepository.findByUsuario(usuario);
	}

	@Override
	public Optional<Comentario> findById(Integer id) {
		// TODO Auto-generated method stub
		return comentarioRepository.findById(id);
	}

	@Override
	public void delete(Integer Id) {
		// TODO Auto-generated method stub
		comentarioRepository.deleteById(Id);
	}

}
