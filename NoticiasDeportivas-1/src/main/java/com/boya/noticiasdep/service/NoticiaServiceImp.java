package com.boya.noticiasdep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Usuario;
import com.boya.noticiasdep.repository.NoticiaRepository;

@Service
public class NoticiaServiceImp implements INoticiaService {

	@Autowired
	private NoticiaRepository noticiaRepository;
	
	@Override
	public Noticia save(Noticia noticia) {
		// TODO Auto-generated method stub
		return noticiaRepository.save(noticia);
	}

	@Override
	public List<Noticia> findAll() {
		// TODO Auto-generated method stub
		return noticiaRepository.findAll();
	}

	@Override
	public Optional<Noticia> get(Integer id) {
		// TODO Auto-generated method stub
		return noticiaRepository.findById(id);
	}

	@Override
	public void delete(Integer Id) {
		// TODO Auto-generated method stub
		noticiaRepository.deleteById(Id);
	}

	@Override
	public void update(Noticia noticia) {
		// TODO Auto-generated method stub
		noticiaRepository.save(noticia);
	}

	@Override
	public List<Noticia> findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return noticiaRepository.findByUsuario(usuario);
	}

}
