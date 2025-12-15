package com.boya.noticiasdep.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boya.noticiasdep.model.Comentario;
import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Usuario;
import com.boya.noticiasdep.service.IComentarioService;
import com.boya.noticiasdep.service.INoticiaService;
import com.boya.noticiasdep.service.IUsuarioService;

@RestController
@RequestMapping("/apicomentarios")
public class ComentarioController {

	@Autowired
	private IComentarioService comentarioService;
	
	@Autowired
	private INoticiaService noticiaService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping("/noticia/{noticiaId}/create")
	public ResponseEntity<Comentario> createComentario(@PathVariable Integer noticiaId,@RequestBody Comentario comentario){
		Optional<Noticia> noticiaOpt = noticiaService.get(noticiaId);
		
		if (noticiaOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Noticia noticia = noticiaOpt.get();
		Usuario usuario = usuarioService.findById(1).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
		comentario.setNoticia(noticia);
		comentario.setUsuario(usuario);
		
		Comentario savedComentario = comentarioService.save(comentario);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedComentario);
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Integer id, @RequestBody Comentario detalleComentario) {
        Optional<Comentario> comentarioOpt = comentarioService.findById(id);
        
        if (comentarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Comentario existingComentario = comentarioOpt.get();
        
        // 1. Actualizar campos
        existingComentario.setTexto(detalleComentario.getTexto());
        
        // 2. Guardar (UPDATE)
        // Usamos save(), si el objeto tiene ID, JPA lo actualiza.
        Comentario updatedComentario = comentarioService.save(existingComentario); 
        
        return ResponseEntity.ok(updatedComentario);
    }
    
    @GetMapping("/noticia/{noticiaId}")
    public ResponseEntity<List<Comentario>> getComentariosByNoticia(@PathVariable Integer noticiaId) {
        Optional<Noticia> noticiaOpt = noticiaService.get(noticiaId);
        if (noticiaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Asume que tu servicio tiene este método
        List<Comentario> comentarios = comentarioService.findByNoticia(noticiaOpt.get());
        
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/comentario/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id) {
        Optional<Comentario> comentario = comentarioService.findById(id);
        return comentario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComentario(@PathVariable Integer id) {
        Optional<Comentario> comentarioOpt = comentarioService.findById(id);
        
        if (comentarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        comentarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
