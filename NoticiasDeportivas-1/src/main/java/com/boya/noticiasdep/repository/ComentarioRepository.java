package com.boya.noticiasdep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boya.noticiasdep.model.Comentario;
import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Usuario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{

	List<Comentario> findByNoticia(Noticia noticia);

	List<Comentario> findByUsuario(Usuario usuario);

}
