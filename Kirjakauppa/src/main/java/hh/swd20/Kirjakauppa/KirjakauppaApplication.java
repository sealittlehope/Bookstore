package hh.swd20.Kirjakauppa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Kirjakauppa.KirjakauppaApplication;
import hh.swd20.Kirjakauppa.domain.Book;
import hh.swd20.Kirjakauppa.domain.BookRepository;

@SpringBootApplication
public class KirjakauppaApplication {

	private static final Logger log = LoggerFactory.getLogger(KirjakauppaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(KirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) { 
		return (args) -> {
			log.info("Save some books");
			/*	private Long id;
				private String title;
				private String author;
				private int year;
				private String isbn;
				private int price;*/
			bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "9780141040349", 24));
			bookRepository.save(new Book("The Colour of Magic ", "Terry Pratchett ", 1983, "9781473205321", 20));	
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
