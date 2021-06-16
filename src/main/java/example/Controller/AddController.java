package example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.Models.Users;
import example.Repository.UsersRepository;

@Controller
public class AddController {
    @Autowired
    private  UsersRepository usersRepository;

    @GetMapping("/add")
    public String add() {
        return "insert";
    }
    @PostMapping("/add")
    public String adder(  @RequestParam String name,@RequestParam String mail, @RequestParam String work,
                          @RequestParam String kuni, @RequestParam String man, Model model) {
        Users users=new Users(name,mail,work,man,kuni);
        usersRepository.save(users);
        model.addAttribute("id",users.getId());
        return "insert3";
    }
}