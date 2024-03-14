package md.usm.book_store.controllers.thymeleaf;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.services.UserService;
import org.springframework.stereotype.Controller;
@RequiredArgsConstructor
@Controller
public class UserControllerThymeleaf {
    private final UserService userService;


}
