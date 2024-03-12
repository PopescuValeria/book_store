package md.usm.book_store.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerThymeleaf {

    @GetMapping({"/","/home"})
    public String getHomePage(){
        return "home";
    }
}
