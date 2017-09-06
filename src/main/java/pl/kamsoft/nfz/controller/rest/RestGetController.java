package pl.kamsoft.nfz.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Author;
import com.playerony.libraryV2.model.Book;
import com.playerony.libraryV2.model.Role;
import com.playerony.libraryV2.model.User;

import pl.kamsoft.nfz.service.AuthorService;
import pl.kamsoft.nfz.service.BookService;
import pl.kamsoft.nfz.service.RoleService;
import pl.kamsoft.nfz.service.UserService;

@RestController
@RequestMapping("/get")
public class RestGetController {
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public List<User> selectUsers() throws DatabaseException {
		return userService.selectUsers();
	}
	
	@GetMapping("/roles")
	@ResponseStatus(HttpStatus.OK)
	public List<Role> selectRoles() throws DatabaseException {
		return roleService.selectRoles();
	}
	
	@GetMapping("/authors")
	@ResponseStatus(HttpStatus.OK)
	public List<Author> selectAuthors() throws DatabaseException {
		return authorService.selectAuthors();
	}
	
	@GetMapping("/books") 
	@ResponseStatus(HttpStatus.OK)
	public List<Book> selectBooks() throws DatabaseException {
		return bookService.selectBooks();
	}
	
	@GetMapping("/role/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Role selectRole(@PathVariable Long id) throws DatabaseException, InputException {
		return roleService.getRoleByID(id);
	}
	
	@GetMapping("/author/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Author selectAuthor(@PathVariable Long id) throws DatabaseException, InputException {
		return authorService.getAuthorByID(id);
	}
	
	@GetMapping("/book/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book selectBooks(@PathVariable Long id) throws DatabaseException {
		return bookService.getBookByID(id);
	}
	
}
