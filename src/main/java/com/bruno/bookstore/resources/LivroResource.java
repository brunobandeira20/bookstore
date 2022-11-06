package com.bruno.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bruno.bookstore.domain.Livro;
import com.bruno.bookstore.dtos.LivroDTO;
import com.bruno.bookstore.service.LivroService;

@RestController
@RequestMapping(value = "livros")
public class LivroResource {
	
	@Autowired
	LivroService livroService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id){
		Livro livro = livroService.findById(id);
		return ResponseEntity.ok().body(livro);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat){
		List<Livro> livro = livroService.findAll(id_cat);
		List<LivroDTO> livroDTO = livro.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(livroDTO);
	}
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro obj){
		Livro newObj = livroService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@PatchMapping(value ="/{id}")
	public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro obj){
		Livro newObj = livroService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat, @RequestBody Livro obj){
		Livro newObj = livroService.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
