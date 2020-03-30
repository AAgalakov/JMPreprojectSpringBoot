package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dto.UserDto;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userService.allUsers()) {
            userDtoList.add(new UserDto(user));
        }
        model.addAttribute("userList", userDtoList);
        return "table";
    }

    @PostMapping("/userAdd")
    public String addUser(UserDto userDto) {
        if (userService.addUser(userDto)) {
            return "redirect:/admin";
        } else {
            return "wrongName";
        }
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        return "redirect:/admin";
    }

    @GetMapping("/updateUserForm")
    public String updateUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", new UserDto(userService.getUserById(id)));
        return "updateUserForm";
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("user") UserDto userDto, Model model) {
        if (userService.updateUser(userDto)) {
            model.addAttribute("userList", userService.allUsers());
            return "redirect:/admin";
        } else {
            return "wrongName";
        }
    }
}
