package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.dto.LoginAdmin;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class LoginAdminControllerThymeleaf {

    @Persistent
    private Boolean isAdmin;
    @GetMapping("/loginAdmin")
    public String openLoginAdminPage(Model model){
        LoginAdmin loginAdmin = new LoginAdmin();
        model.addAttribute("loginAdmin", loginAdmin);
        model.addAttribute("admin", isAdmin);
        return "loginAdmin";
    }

    @PostMapping("/loginAdmin")
    public String searchAdmin(@ModelAttribute("loginAdmin")LoginAdmin loginAdmin){
        isAdmin = loginAdmin.getUsername() != null && loginAdmin.getUsername().equals("admin") &&
                loginAdmin.getPassword() != null && loginAdmin.getPassword().equals("admin");
        return "redirect:home";
    }
}
