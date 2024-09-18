package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.constants.Roles;
import md.usm.book_store.models.User;
import md.usm.book_store.security.crypto.CryptoPassword;
import md.usm.book_store.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class SignUpControllerThymeleaf {

    private final UserService userService;

    @GetMapping("/sign_up")
    public String openSignUpPage(Model model){
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String saveUserAccount(@ModelAttribute("user") User user){
        user.setRole_id(Roles.USER);
        user.setPassword(CryptoPassword.encryptPassword(user.getPassword()));
        userService.saveOrUpdateUser(user);
        return "redirect:sign_up";
    }
}
