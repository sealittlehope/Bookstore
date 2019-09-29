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
import hh.swd20.Kirjakauppa.domain.Category;
import hh.swd20.Kirjakauppa.domain.CategoryRepository;

@SpringBootApplication
public class KirjakauppaApplication {

	private static final Logger log = LoggerFactory.getLogger(KirjakauppaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(KirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository ctgrRepository) { 
		return (args) -> {
			log.info("Save some books");
		
			ctgrRepository.save(new Category("Biography"));
			ctgrRepository.save(new Category("Encyclopedia"));
			ctgrRepository.save(new Category("Fantasy"));
			ctgrRepository.save(new Category("Romance"));
			ctgrRepository.save(new Category("Science fiction"));
			
			
			bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "9780141040349", 24, ctgrRepository.findByName("Romance").get(0)));
			bookRepository.save(new Book("The Colour of Magic ", "Terry Pratchett ", 1983, "9781473205321", 20, ctgrRepository.findByName("Fantasy").get(0)));
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
