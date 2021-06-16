package example.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.Models.Users;
import example.Repository.UsersRepository;

@Controller
public class MainController {
    @Autowired
    private UsersRepository usersRepository;
    @GetMapping("/")
    public String start(@PageableDefault(value=20)Pageable pageable,Model model){
            Page<Users> page = usersRepository.findAll(pageable);
            model.addAttribute("page",page);
            model.addAttribute("number",page.getNumber());
            model.addAttribute("totalpage",page.getTotalPages()-1);
            model.addAttribute("fullsize",page.getTotalElements());
            int size=page.getNumber()+1;
            if(page.getNumber()==0) {
            	if(size*page.getSize()+page.getNumber()<=page.getTotalElements()) {
            		model.addAttribute("lastsize",size*page.getSize()+page.getNumber());
            	}else {
            		model.addAttribute("lastsize",page.getTotalElements());
            	}
            	model.addAttribute("startsize","1");
            }else {
            	if(size*page.getSize()+page.getNumber()<=page.getTotalElements()) {
            		model.addAttribute("lastsize",size*page.getSize());
            		model.addAttribute("startsize",size*page.getSize()+-page.getSize()+1);
            	}else {
            		model.addAttribute("lastsize",page.getTotalElements());
            		model.addAttribute("startsize",size*page.getSize()-page.getSize()+1);;
            	}

            }
        return "index";
    }
    @PostMapping("/index")
    public String index(@RequestParam int index,HttpServletRequest request ) {
    	index-=1;
    return "redirect:/?page="+index ;
    }
}

