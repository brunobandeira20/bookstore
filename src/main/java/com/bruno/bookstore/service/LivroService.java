package com.bruno.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.bookstore.domain.Categoria;
import com.bruno.bookstore.domain.Livro;
import com.bruno.bookstore.exception.ObjectNotFoundException;
import com.bruno.bookstore.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado "+id+ " do tipo: "+ Livro.class.getName()));
	}

	public List<Livro> findAll(Integer id_cat) {
		categoriaService.findById(id_cat);
		List<Livro> obj = livroRepository.findAllByCategoria(id_cat);
		return obj;
	}

	public Livro update(Integer id, Livro obj) {
		Livro newObj = findById(id);
		updateDate(newObj, obj);
		return livroRepository.save(newObj);
	}

	private void updateDate(Livro newObj, Livro obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setNome_autor(obj.getNome_autor());
		newObj.setTexto(obj.getTexto());
	}

	public Livro create(Integer id_cat, Livro obj) {
		obj.setId(null);
		Categoria categoria = categoriaService.findById(id_cat);
		obj.setCategoria(categoria);
		return livroRepository.save(obj);
	}

	public void delete(Integer id) {
		Livro newObj = findById(id);
		livroRepository.delete(newObj);
	}
}
