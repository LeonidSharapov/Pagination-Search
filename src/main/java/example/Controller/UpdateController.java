	package example.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.Models.Users;
import example.Repository.UsersRepository;

@Controller
public class UpdateController {
    @Autowired
   private UsersRepository usersRepository;

    @PostMapping("/update")
    public String update2(@RequestParam Integer id,Model model) {
            Optional<Users> user= usersRepository.findById(id);
            ArrayList<Users> res =new ArrayList<>();
            user.ifPresent(res::add);
            model.addAttribute("user",res);
            if(res.size()==0) {
            	return"idError";
            }
            return "update2";

    }

    @PostMapping("/update-new")
    public String update3(@RequestParam Integer id,@RequestParam String name,@RequestParam String mail,
                          @RequestParam String work, @RequestParam String man, @RequestParam String from){
        Users users = usersRepository.findById(id).orElseThrow(null);
        users.setName(name);
        users.setMail(mail);
        users.setComName(work);
        users.setChek(man);
        users.setFrom(from);
        usersRepository.save(users);
        return "update3";
    }
}