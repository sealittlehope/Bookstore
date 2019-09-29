package hh.swd20.Kirjakauppa.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import hh.swd20.Kirjakauppa.domain.Book;
import hh.swd20.Kirjakauppa.domain.BookRepository;
import hh.swd20.Kirjakauppa.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository ctgrRepository;
	
		@RequestMapping(value = "/index", method = RequestMethod.GET)
		public String getHello() {
					return "index"; 
		}
		
		// kirjojen listaus
		@RequestMapping(value = "/booklist", method = RequestMethod.GET)
		public String getBooks(Model title) {
				List<Book> books =  (List<Book>) bookRepository.findAll();
				title.addAttribute("books", books);
				return "booklist"; 
		}
		
		// REST service which returns all books
	    @RequestMapping(value="/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> bookListRest() {	
	        return (List<Book>) bookRepository.findAll();
	    }  
	    
		// REST service which returns books by id
	    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
	    	return bookRepository.findById(bookId);
	    } 
		
		// tyhjän kirjalomakkeen muodostaminen
		@RequestMapping(value = "/newbook", method = RequestMethod.GET)
		public String getNewBookForm(Model title, Model model) {
				title.addAttribute("book", new Book()); 
				model.addAttribute("categories", ctgrRepository.findAll());
				return "addbook";
		}
				
		// lomakkeella syötettyjen tietojen vastaanotto ja tallennus
		@RequestMapping(value = "/newbook", method = RequestMethod.POST)
		public String saveBook(@ModelAttribute Book book) {
			// talletetaan yhden kirjan tiedot tietokantaan
				bookRepository.save(book);
				return "redirect:/booklist";
		}
				
		// kirjan poisto
		@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long bookId) {
				bookRepository.deleteById(bookId);
				return "redirect:../booklist";
		}
				
		// kirjan muokkaus
		@RequestMapping(value ="/editbook/{id}", method = RequestMethod.GET)
		public String editBook(@PathVariable("id") Long bookId, Model title) {
				title.addAttribute("book", bookRepository.findById(bookId));
				return "editbook";
		}
				
		@RequestMapping(value = "/editbook", method = RequestMethod.POST)
		public String updateBook(@ModelAttribute Book book) {
				// talletetaan yhden kirjan tiedot tietokantaan
					bookRepository.save(book);
					return "redirect:/booklist";
		}		

} 
							
				

