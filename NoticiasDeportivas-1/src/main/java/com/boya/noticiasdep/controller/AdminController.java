package com.boya.noticiasdep.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boya.noticiasdep.model.Noticia;
import com.boya.noticiasdep.model.Rol;
import com.boya.noticiasdep.model.Usuario;
import com.boya.noticiasdep.repository.RolRepository;
import com.boya.noticiasdep.service.INoticiaService;
import com.boya.noticiasdep.service.IUsuarioService;

@RestController
@RequestMapping("/apiAdmin")
public class AdminController {
	
	@Autowired
	private IUsuarioService userService;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private INoticiaService noticiaService;

	// Endpoint GET para obtener todos los productos
		@GetMapping("/list")
		public List<Usuario> getAllUsers() {
			return userService.findAll();
		}
		
		//Lista Noticias
		@GetMapping("/listNoticias")
		public List<Noticia> getAllNoticias() {
			return noticiaService.findAll();
		}

		// Endpoint GET para obtener un Usuario por ID
		@GetMapping("/user/{id}")
		public ResponseEntity<Usuario> getUserById(@PathVariable Integer id) {
			Optional<Usuario> usuario = userService.get(id);
			return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		}

		// Endpoint POST para crear un nuevo Usuario
		@PostMapping("/create")
		public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
			Rol rolUser = rolRepository.findByNombre("USER").orElse(null);
			
			if (rolUser == null) {
	            // Error si el rol "USER" no existe en la DB
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(null); 
	        }
			
			usuario.setRol(rolUser);
			
			Usuario savedUser = userService.save(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		}

		// Endpoint PUT para actualizar un Usuario
		@PutMapping("/update/{id}")
		public ResponseEntity<Usuario> updateProduct(@PathVariable Integer id, @RequestBody Usuario userDetails) {
			Optional<Usuario> usuario = userService.get(id);
			if (!usuario.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			Usuario existingUser = usuario.get();
			existingUser.setNombre(userDetails.getNombre());
			existingUser.setTelefono(userDetails.getTelefono());
			existingUser.setEmail(userDetails.getEmail());
			existingUser.setUsername(userDetails.getUsername());
			existingUser.setPassword(userDetails.getPassword());
			
			userService.update(existingUser);
			return ResponseEntity.ok(existingUser);
		}
		
		//Actualizar pero el ROL 
		@PutMapping("/{userId}/rol/{NombreRol}")
		public ResponseEntity<Usuario> updateRol(@PathVariable Integer userId, @PathVariable String NombreRol) {
			Usuario usuarioExis = userService.findById(userId)
					.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID:" + userId));
			Rol nuevoRol = rolRepository.findByNombre(NombreRol.toUpperCase())
					.orElse(null);
			
			if (nuevoRol == null) {
				return ResponseEntity.badRequest().body(null);
			}
			
			usuarioExis.setRol(nuevoRol);
			
			Usuario guardarUser = userService.save(usuarioExis);
			
			return ResponseEntity.ok(guardarUser);
		}
		//Endpoint DELETE pata eliminar unproducto
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
			Optional<Usuario> usuario = userService.get(id);
			if (!usuario.isPresent()) {
				return ResponseEntity.notFound().build();	
				}
			userService.delete(id);
			return ResponseEntity.ok().build();
		}
	}

