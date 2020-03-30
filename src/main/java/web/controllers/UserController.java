package web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.dto.UserDto;
import web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getUserPage(Authentication authentication) {
        UserDto userDto = new UserDto(userService.getUserByName(authentication.getName()));
        return new ModelAndView("userPage", "user", userDto);
    }
}
