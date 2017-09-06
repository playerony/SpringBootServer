package pl.kamsoft.nfz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playerony.libraryV2.exception.DatabaseException;

import pl.kamsoft.nfz.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/")
	public String roles(Model model) throws DatabaseException{
		model.addAttribute("roles", roleService.selectRoles());
		
		return "role";
	}
}
