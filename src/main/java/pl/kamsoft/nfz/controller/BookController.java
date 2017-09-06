package pl.kamsoft.nfz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Book;

import pl.kamsoft.nfz.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/")
	public String index(Model model) throws DatabaseException{
		model.addAttribute("books", bookService.selectBooks());
		
		return "book";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("book", new Book());
		return "add/addBook";
	}
	
	@PostMapping(value = "/addBook")
	public String addBook(@Valid Book book, BindingResult bindingResult) throws DatabaseException, InputException{
		if(bindingResult.hasErrors()) 
			return "add/addBook";
		
		bookService.saveBook(book);
		return "redirect:/book/";
	}
	
	@PostMapping(value = "/removeBook")
	public String remove(@RequestParam(value="deleteButton") Long id) throws DatabaseException, InputException{
		bookService.removeBook(id);
		return "redirect:/book/";
	}
	
	@GetMapping(value = "/edit")
	public String edit(@RequestParam(value="editButton") Integer id, Model model) throws DatabaseException {
		model.addAttribute("book", bookService.getBookByID((long) id));
		return "edit/editBook";
	}
	
	@PostMapping(value = "/editBook")
	public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) throws DatabaseException, InputException {
		if(bindingResult.hasErrors()) 
			return "edit/editBook";
		
		bookService.updateBook(book.getId(), book);
		return "redirect:/book/";
	}
}
