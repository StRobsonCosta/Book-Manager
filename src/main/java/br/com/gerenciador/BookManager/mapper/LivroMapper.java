package br.com.gerenciador.BookManager.mapper;

import org.springframework.stereotype.Component;

import br.com.gerenciador.BookManager.dto.LivroDto;
import br.com.gerenciador.BookManager.model.Livro;

@Component
public class LivroMapper {

	public LivroDto toDto(Livro livro) {
		LivroDto dto = new LivroDto();
		
		dto.setId(livro.getId());	
		dto.setTitulo(livro.getTitulo());
		dto.setAutor(livro.getAutor());
		dto.setIsbn(livro.getIsbn());
		
		return dto;
	}
	
	public Livro toEntity(LivroDto dto) {
		Livro livro = new Livro();
		
		livro.setId(dto.getId());
		livro.setTitulo(dto.getTitulo());
		livro.setAutor(dto.getAutor());
		livro.setIsbn(dto.getIsbn());
		
		return livro;
	}
}
