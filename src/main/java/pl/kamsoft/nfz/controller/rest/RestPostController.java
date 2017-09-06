package pl.kamsoft.nfz.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Author;
import com.playerony.libraryV2.model.Book;
import com.playerony.libraryV2.model.User;

import pl.kamsoft.nfz.XMLGeneratror.xml.dom4j.impl.XMLImport;
import pl.kamsoft.nfz.XMLGeneratror.xml.dom4j.impl.XMLParser;
import pl.kamsoft.nfz.XMLGeneratror.xml.exception.XMLException;
import pl.kamsoft.nfz.service.AuthorService;
import pl.kamsoft.nfz.service.BookService;
import pl.kamsoft.nfz.service.RoleService;
import pl.kamsoft.nfz.service.UserService;

@RestController
@RequestMapping("/post")
public class RestPostController {
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user) throws DatabaseException, InputException{
		System.out.println(user.toString());
		
		userService.saveUser(user);
	}
	
	@PostMapping(value = "/addAuthor", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveAuthor(@RequestBody Author author) throws DatabaseException, InputException{
		System.out.println(author.toString());
		
		authorService.saveAuthor(author);
	}
	
	@PostMapping(value = "/addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveBook(@RequestBody Book book) throws DatabaseException, InputException{
		bookService.saveBook(book);
	}
	
	@PostMapping(value = "/addUsers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUsers(@RequestBody List<User> users) throws DatabaseException, InputException{
		for(User u : users)
			userService.saveUser(u);
	}
	
	@PostMapping(value = "/addAuthors", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveAuthors(@RequestBody List<Author> authors) throws DatabaseException, InputException{
		for(Author a : authors) {
			System.out.println(a.getFirstName());
			authorService.saveAuthor(a);
		}
	}
	
	@PostMapping(value = "/addBooks", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveBooks(@RequestBody List<Book> books) throws DatabaseException, InputException{
		for(Book b : books)
			bookService.saveBook(b);
	}
	
	@PutMapping(value = "/deleteUser/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable Long id) throws DatabaseException, InputException{
		userService.deleteUser(id);
	}
	
	@PutMapping(value = "/deleteAuthor/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAuthor(@PathVariable Long id) throws DatabaseException, InputException{
		authorService.removeAuthor(id);
	}
	
	@PostMapping(value = "/deleteBook/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBook(@PathVariable Long id) throws DatabaseException, InputException{
		bookService.removeBook(id);
	}
	
	@PostMapping(value = "/export")
	public void exportXML(@RequestBody String path) throws DatabaseException, XMLException {
		XMLParser xmlParser = new XMLParser();
		
		xmlParser.exportBooksAndAuthorsWithResultSet(path);
	}
	
	@PostMapping(value = "/import")
	public void importXML(@RequestBody String path) throws DatabaseException, XMLException {
		XMLImport xmlImport = new XMLImport();
		
		xmlImport.importBooksAndAuthors(path);
	}
}
