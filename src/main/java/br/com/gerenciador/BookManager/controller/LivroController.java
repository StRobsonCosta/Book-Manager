package br.com.gerenciador.BookManager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciador.BookManager.dto.LivroDto;
import br.com.gerenciador.BookManager.mapper.LivroMapper;
import br.com.gerenciador.BookManager.model.Livro;
import br.com.gerenciador.BookManager.service.LivroService;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
	
	@Autowired
	private LivroService service;
	
	@Autowired
	private LivroMapper mapper;
	
	@GetMapping
	public List<LivroDto> listarTodosLivros() {
		return service.listarTodos().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> pesquisarLivro(@PathVariable Long id) {
		return service.buscarPorId(id)
				.map(mapper::toDto)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound()
						.build());
	}
	
	@PostMapping
	public LivroDto salvarLivro(@RequestBody LivroDto dto) {
		Livro livro = mapper.toEntity(dto);
		
		return mapper.toDto(service.salvar(livro));
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<LivroDto> updateLivro(@PathVariable Long id, @RequestBody LivroDto dto) {
        if (!service.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Livro livro = mapper.toEntity(dto);
        livro.setId(id);
        Livro updatedLivro = service.salvar(livro);
        return ResponseEntity.ok(mapper.toDto(updatedLivro));
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LivroDto> deletar(@PathVariable Long id) {
		if(!service.buscarPorId(id).isPresent())
			return ResponseEntity.notFound().build();
		
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
