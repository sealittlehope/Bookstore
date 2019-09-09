package hh.swd20.Kirjakauppa.domain;

import org.springframework.data.repository.CrudRepository;

import hh.swd20.Kirjakauppa.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	

}
