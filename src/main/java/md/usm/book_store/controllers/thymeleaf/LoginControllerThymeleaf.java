package md.usm.book_store.controllers.thymeleaf;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.User;
import md.usm.book_store.models.dto.LoginUserDto;
import md.usm.book_store.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static md.usm.book_store.security.jwt.JwtUtil.*;

@RequiredArgsConstructor
@Controller
public class LoginControllerThymeleaf {
    private final UserService userService;

    @GetMapping("/loginAdmin")
    public String openLoginAdminPage(Model model) {
        LoginUserDto loginUser = new LoginUserDto();
        model.addAttribute("loginUser", loginUser);
        return "loginAdmin";
    }

    @PostMapping("/loginAdmin")
    public String searchAdmin(@ModelAttribute("loginUser") LoginUserDto loginUser, HttpServletResponse response) {
        User user = userService.findByUsername(loginUser.getUsername());
        String jwt = generateToken(user);
        Cookie jwtCookie = createJwtCookie(jwt);
        response.addCookie(jwtCookie);
        return "redirect:home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        clearJwtCookie(response);
        return "redirect:loginAdmin";
    }
}
