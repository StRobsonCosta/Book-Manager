package br.com.gerenciador.BookManager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciador.BookManager.model.Livro;
import br.com.gerenciador.BookManager.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	public List<Livro> listarTodos() {
		return repository.findAll();		
	}
	
	public Optional<Livro> buscarPorId(Long id){
		return repository.findById(id);
	}
	
	public Livro salvar(Livro livro) {
		return repository.save(livro);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}

}
