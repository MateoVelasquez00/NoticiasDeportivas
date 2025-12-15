package com.boya.noticiasdep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Usuario;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

	List<Noticia> findByUsuario(Usuario usuario);

}
