package pl.kamsoft.nfz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Author;

import pl.kamsoft.nfz.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/")
	public String index(Model model) throws DatabaseException{
		model.addAttribute("authors", authorService.selectAuthors());
		return "author";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("author", new Author());
		return "add/addAuthor";
	}
	
	@PostMapping(value = "/addAuthor")
	public String addAuthor(@Valid Author author, BindingResult bindingResult) throws DatabaseException, InputException{
		if(bindingResult.hasErrors()) 
			return "add/addAuthor";
		
		authorService.saveAuthor(author);
		return "redirect:/author/";
	}
	
	@PostMapping(value = "/removeAuthor")
	public String remove(@RequestParam(value="deleteButton") Long id) throws DatabaseException, InputException{
		authorService.removeAuthor(id);
		return "redirect:/author/";
	}
	
	@GetMapping(value = "/edit")
	public String edit(@RequestParam(value="editButton") Long id, Model model) throws DatabaseException, InputException {
		model.addAttribute("author", authorService.getAuthorByID(id));
		return "edit/editAuthor";
	}
	
	@PostMapping(value = "/editAuthor")
	public String editBook(@Valid Author author, BindingResult bindingResult) throws DatabaseException, InputException {
		if(bindingResult.hasErrors()) 
			return "edir/editAuthor";
		
		authorService.updateAuthor(author.getId(), author);
		return "redirect:/author/";
	}
}
