package com.bruno.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.bookstore.domain.Categoria;
import com.bruno.bookstore.domain.Livro;
import com.bruno.bookstore.repositories.CategoriaRepository;
import com.bruno.bookstore.repositories.LivroRepository;

@Service
public class DBService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	LivroRepository livroRepository;
	
	public void instanciaBaseDeDados() {
		
		Categoria cat1 = new Categoria(	null, "INFORMATICA", "TI");
		Categoria cat2 = new Categoria(	null, "FICCAO CIENTIFCA", "SY");
		Categoria cat3 = new Categoria(	null, "CULINARIA", "ALIMENTOS");
		
		Livro livro1 = new Livro(null, "CODE CLEAN", "MARTIN", "LAUREN IPSIM", cat1);
		Livro livro2 = new Livro(null, "ET BINA", "BINA", "LAUREN IPSIM", cat2);
		Livro livro3 = new Livro(null, "CLEAN ARQUICTURE", "JOSE", "LAUREN IPSIM", cat1);
		Livro livro4 = new Livro(null, "100 RECEITAS", "ANA MARIA BRAGA", "LAUREN IPSIM", cat3);
		Livro livro5 = new Livro(null, "I ROBOT", "WILL SMITH", "LAUREN IPSIM", cat2);
		
		cat1.getLivros().addAll(Arrays.asList(livro1, livro3));
		cat2.getLivros().addAll(Arrays.asList(livro2,livro5));
		cat3.getLivros().addAll(Arrays.asList(livro4));
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		
		this.livroRepository.saveAll(Arrays.asList(livro1,livro2,livro3,livro4,livro5));
	}
}
