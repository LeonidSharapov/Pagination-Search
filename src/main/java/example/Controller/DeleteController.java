package example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.Models.Users;
import example.Repository.UsersRepository;
@Controller
public class DeleteController {
	@Autowired
private UsersRepository usersRepository;
	@PostMapping("/delete")
	public String delet1(@RequestParam Integer id, Model model){
		Users users=usersRepository.findById(id).orElseThrow(null);
		model.addAttribute("name",users.getName());
		model.addAttribute("id",users.getId());
	    return "delete1";
	}
	@PostMapping("/delete2")
	public String delet1(@RequestParam Integer id ){
	    usersRepository.deleteById(id);
	    return "delete2";
	}
	}

