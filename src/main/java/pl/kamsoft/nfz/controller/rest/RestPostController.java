package pl.kamsoft.nfz.controller.rest;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public void saveUser(@RequestBody User user) throws DatabaseException, InputException {
		System.out.println(user.toString());

		userService.saveUser(user);
	}

	@PostMapping(value = "/addAuthor", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveAuthor(@RequestBody Author author) throws DatabaseException, InputException {
		authorService.saveAuthor(author);
	}

	@PostMapping(value = "/addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveBook(@RequestBody Book book) throws DatabaseException, InputException {
		bookService.saveBook(book);
	}

	@PostMapping(value = "/addUsers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUsers(@RequestBody List<User> users) throws DatabaseException, InputException {
		for (User u : users)
			userService.saveUser(u);
	}

	@PostMapping(value = "/addAuthors", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveAuthors(@RequestBody List<Author> authors) throws DatabaseException, InputException {
		for (Author a : authors) {
			authorService.saveAuthor(a);
		}
	}

	@PostMapping(value = "/addBooks", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveBooks(@RequestBody List<Book> books) throws DatabaseException, InputException {
		for (Book b : books)
			bookService.saveBook(b);
	}

	@PutMapping(value = "/deleteUser/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable Long id) throws DatabaseException, InputException {
		userService.deleteUser(id);
	}

	@PutMapping(value = "/deleteAuthor/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAuthor(@PathVariable Long id) throws DatabaseException, InputException {
		authorService.removeAuthor(id);
	}

	@PostMapping(value = "/deleteBook/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBook(@PathVariable Long id) throws DatabaseException, InputException {
		bookService.removeBook(id);
	}

	@PostMapping(value = "/export")
	public void exportXML(@RequestBody String path, HttpServletRequest request) throws DatabaseException, XMLException {
		XMLParser xmlParser = new XMLParser();
		String realPath = request.getServletContext().getRealPath("/");

		xmlParser.exportBooksAndAuthorsWithResultSet(realPath + "/" + "test.xml");
	}

	@PostMapping(value = "/import")
	public void importXML(@RequestBody String path, HttpServletRequest request) throws DatabaseException, XMLException {
		XMLImport xmlImport = new XMLImport();
		String realPath = request.getServletContext().getRealPath("/");

		xmlImport.importBooksAndAuthors(realPath + "/" + "test.xml");
	}

	@PostMapping(value = "/uploadxml", consumes = "multipart/form-data")
	public ResponseEntity<File> upload(@RequestParam("file") MultipartFile inputFile, HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		XMLImport xmlImport = new XMLImport();
		
		if (!inputFile.isEmpty()) {
			try {
				String originalFilename = inputFile.getOriginalFilename();
				ServletContext context = request.getServletContext();
				String appPath = context.getRealPath("");
				String fullPath = appPath + inputFile.getOriginalFilename();
				File destinationFile = new File(fullPath);
				inputFile.transferTo(destinationFile);
				try {
					xmlImport.importBooksAndAuthors(fullPath);
				} catch (Exception e) {
					e.printStackTrace();
				}
				headers.add("File Uploaded Successfully - ", originalFilename);
				return new ResponseEntity<File>(destinationFile, headers, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<File>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<File>(HttpStatus.BAD_REQUEST);
		}
	}
}
