package example.Controller;

import javax.servlet.http.HttpSession;

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
public class SearchController {
    @Autowired
	private UsersRepository usersRepository;
  @GetMapping("/search")
  public String searchs(@PageableDefault(value=20)Pageable pageable,Model model,HttpSession session) {
    String searchName=String.valueOf(session.getAttribute("search"));
    System.out.println(searchName);
	Page<Users> page = usersRepository.findIdUser(searchName, pageable);
  model.addAttribute("page",page);
  model.addAttribute("number",page.getNumber());
  model.addAttribute("totalpage",page.getTotalPages()-1);
  model.addAttribute("fullsize",page.getTotalElements());
  System.out.print(page.getNumber());
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
  		model.addAttribute("lastsize",size*page.getSize()+1);
  		model.addAttribute("startsize",size*page.getSize()+page.getNumber()-page.getSize());
  	}else {
  		model.addAttribute("lastsize",page.getTotalElements());
  		model.addAttribute("startsize",size*page.getSize()-page.getSize()+1);;
  	}

  }
return "index2";
}
    @PostMapping("/searchr")
    public String search(HttpSession session, @RequestParam String name,Model model,Pageable pageable) {
    	Page<Users> page = usersRepository.findIdUser(name, pageable);
    	if(page.getTotalElements()==0) {
    	  	return"NameError";
    	  }
    session.setAttribute("search", name);
    return "redirect:/search";
}
    @PostMapping("/indexsearch")
    public String index(@RequestParam int index) {
    	index-=1;
    return "redirect:/search?page="+index ;
    }
}