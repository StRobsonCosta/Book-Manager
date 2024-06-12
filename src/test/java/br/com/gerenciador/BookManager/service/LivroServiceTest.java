package br.com.gerenciador.BookManager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.gerenciador.BookManager.model.Livro;
import br.com.gerenciador.BookManager.repository.LivroRepository;

class LivroServiceTest {
	
	private static final Long LIVRO_ID_01 = 1L;
	private static final String LIVRO_TITULO_02 = "Livro Teste 02";
	private static final String LIVRO_AUTOR_03 = "Autor Teste 03";
	private static final String LIVRO_ISBN_13 = "13-1234-12345-x";
	
	
	@Mock
	private LivroRepository repository;
	
	@InjectMocks
	private LivroService service;
	
	public LivroServiceTest() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testListarTodos() {
		Livro livro = new Livro();
		
		livro.setId(LIVRO_ID_01);
		livro.setTitulo(LIVRO_TITULO_02);
		livro.setAutor(LIVRO_AUTOR_03);
		livro.setIsbn(LIVRO_ISBN_13);
		
		List<Livro> livros = Arrays.asList(livro);
		
		when(repository.findAll()).thenReturn(livros);
		
		List<Livro> result = service.listarTodos();
		
		assertEquals(LIVRO_ID_01, result.size());
		assertEquals(LIVRO_TITULO_02, result.get(0).getTitulo());
	}
	
	@Test
	void testFindById() {
		Livro livro = new Livro();
		
		livro.setId(LIVRO_ID_01);
		livro.setTitulo(LIVRO_TITULO_02);
		livro.setAutor(LIVRO_AUTOR_03);
		livro.setIsbn(LIVRO_ISBN_13);
		
		when(repository.findById(LIVRO_ID_01)).thenReturn(Optional.of(livro));
		
		Optional<Livro> result = service.buscarPorId(LIVRO_ID_01);
		
		assertEquals(Boolean.TRUE, result.isPresent());
		assertEquals(LIVRO_AUTOR_03, result.get().getAutor());
	}
	
	@Test
	void testSalvar() {
		Livro livro = new Livro();
		
		livro.setId(LIVRO_ID_01);
		livro.setTitulo(LIVRO_TITULO_02);
		livro.setAutor(LIVRO_AUTOR_03);
		livro.setIsbn(LIVRO_ISBN_13);
		
		when(repository.save(livro)).thenReturn(livro);
		
		Livro result = service.salvar(livro);
		
		assertEquals(LIVRO_TITULO_02, result.getTitulo());
	}
	
	@Test
	void testDeletar() {
		service.deletar(LIVRO_ID_01);
		
		verify(repository, times(1)).deleteById(LIVRO_ID_01);
	}

}
