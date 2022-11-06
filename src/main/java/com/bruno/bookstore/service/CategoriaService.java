package com.bruno.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.bookstore.domain.Categoria;
import com.bruno.bookstore.dtos.CategoriaDTO;
import com.bruno.bookstore.exception.ObjectNotFoundException;
import com.bruno.bookstore.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + id +", Tipo: " + Categoria.class.getCanonicalName()));
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
}
