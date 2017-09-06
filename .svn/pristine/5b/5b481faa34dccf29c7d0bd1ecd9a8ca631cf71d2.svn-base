package pl.kamsoft.nfz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.User;

import pl.kamsoft.nfz.service.RoleService;
import pl.kamsoft.nfz.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/")
	public String roles(Model model) throws DatabaseException{
		model.addAttribute("users", userService.selectUsers());
		
		return "user";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) throws DatabaseException {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.selectRoles());
		
		return "add/addUser";
	}
	
	@PostMapping(value = "/addUser")
	public String addUser(@Valid User user) throws DatabaseException, InputException{
		userService.saveUser(user);
		
		return "redirect:/";
	}
}
