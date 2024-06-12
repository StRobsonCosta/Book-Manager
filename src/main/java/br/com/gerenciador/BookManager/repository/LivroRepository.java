package br.com.gerenciador.BookManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciador.BookManager.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
