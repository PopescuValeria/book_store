package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.User;
import md.usm.book_store.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class UserControllerThymeleaf {
    private final UserService userService;

    @PostMapping("/user")
    public String saveOrUpdateUserAccount(@ModelAttribute("user")User user){
        userService.saveOrUpdateUser(user);
        return "redirect:users";
    }

    @GetMapping("/user")
    public ModelAndView getUserById(@RequestParam UUID userId){
        ModelAndView modelAndView = new ModelAndView("users");
        User user = userService.findUserById(userId);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("userList", userService.findAllUsers());
        return "users";
    }

    @DeleteMapping("/users/deleteUser")
    public String deleteUserById(@RequestParam UUID userID){
        userService.deleteUserById(userID);
        return "redirect:users";
    }
}
