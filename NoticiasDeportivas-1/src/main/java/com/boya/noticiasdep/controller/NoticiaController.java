package com.boya.noticiasdep.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Usuario;
import com.boya.noticiasdep.service.INoticiaService;
import com.boya.noticiasdep.service.IUsuarioService;

@RestController
@RequestMapping("/apinoticias")
public class NoticiaController {

		@Autowired
		private INoticiaService noticiaService;

		@Autowired
		private IUsuarioService usuarioService;

		// Endpoint GET para obtener todos los productos
		@GetMapping("/list")
		public List<Noticia> getAllNoticias() {
			return noticiaService.findAll();
		}

		// Endpoint GET para obtener un producto por ID
		@GetMapping("/noticia/{id}")
		public ResponseEntity<Noticia> getNoticiaById(@PathVariable Integer id) {
			Optional<Noticia> noticia = noticiaService.get(id);
			return noticia.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		}

		// Endpoint POST para crear un nuevo producto
		@PostMapping("/create")
		public ResponseEntity<Noticia> createNoticia(@RequestBody Noticia noticia) {
			Usuario u = usuarioService.findById(1).get();
			noticia.setUsuario(u);
			noticia.setFechaPublicacion(LocalDate.now());
			if (noticia.getImagen() == null) {
				noticia.setImagen("default.jpg");
			}
			
			Noticia savedNoticia = noticiaService.save(noticia);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedNoticia);
		}

		// Endpoint PUT para actualizar un producto
		@PutMapping("/update/{id}")
		public ResponseEntity<Noticia> updateNoticia(@PathVariable Integer id, @RequestBody Noticia noticiaDetalle) {
			Optional<Noticia> noticia = noticiaService.get(id);
			if (!noticia.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			Noticia existingNoticia = noticia.get();
			existingNoticia.setTitulo(noticiaDetalle.getTitulo());
			existingNoticia.setContenido(noticiaDetalle.getContenido());
			existingNoticia.setFechaPublicacion(noticiaDetalle.getFechaPublicacion());
			//Mantener la imagen existente a menos que se envie una nueva
			if(noticiaDetalle.getImagen() != null) {
				existingNoticia.setImagen(noticiaDetalle.getImagen());
			} 
			
			noticiaService.update(existingNoticia);
			return ResponseEntity.ok(existingNoticia);
		}
		//Endpoint DELETE pata eliminar unproducto
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteNoticia(@PathVariable Integer id){
			Optional<Noticia> noticia = noticiaService.get(id);
			if (!noticia.isPresent()) {
				return ResponseEntity.notFound().build();	
				}
			
			Noticia n = noticia.get();
			if(!n.getImagen().equals("default.jpg")) {
			}
			
			noticiaService.delete(id);
			return ResponseEntity.ok().build();
		}
}
