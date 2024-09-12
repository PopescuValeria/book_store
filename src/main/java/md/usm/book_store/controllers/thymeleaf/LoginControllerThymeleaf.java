package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.User;
import md.usm.book_store.models.dto.LoginUserDto;
import md.usm.book_store.security.CryptoPassword;
import md.usm.book_store.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class LoginControllerThymeleaf {
    public static final String HOST = "@gmail.com";
    private final UserService userService;
    @GetMapping("/loginAdmin")
    public String openLoginAdminPage(Model model){
        LoginUserDto loginUser = new LoginUserDto();
        model.addAttribute("loginUser", loginUser);
        return "loginAdmin";
    }

    @PostMapping("/loginAdmin")
    public String searchAdmin(@ModelAttribute("loginUser") LoginUserDto loginUser){
        User user = userService.findByUsername((loginUser.getUsername() + HOST));
        Boolean isValidPassword = CryptoPassword.checkPassword(loginUser.getPassword(), user.getPassword());
        return "redirect:loginAdmin";
    }
}
