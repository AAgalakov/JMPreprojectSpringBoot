package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.dto.UserDto;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

//    @Autowired
//    private RoleServiceImpl roleServiceImpl;

    private UserService userService;

//    private final UserService userService;
//
//    private RoleService roleService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userService.allUsers()) {
            userDtoList.add(new UserDto(user));
        }
        return new ModelAndView("table", "userList", userDtoList);
    }
//    @GetMapping
//    public String getAllUsers(Map<String, Object> model) {
//        List<UserDto> userDtoList = new ArrayList<>();
//        for (User user : userService.allUsers()) {
//            userDtoList.add(new UserDto(user));
//        }
//        model.put("userList", userDtoList);
//        return "table";
//    }

//    @GetMapping(path = "/tableRole")
//    public ModelAndView getAllRoles() {
//        List<Role> roleList = roleRepo.findAll();
//        return new ModelAndView("roleTable", "roleList", roleList);
//    }

    @PostMapping("/userAdd")
    public ModelAndView addUser(UserDto userDto) {
        if (userService.addUser(userDto)) {
            return new ModelAndView("redirect:/admin");
        } else {
            return new ModelAndView("wrongName");
        }
    }

    @GetMapping("/delete")
    public ModelAndView deleteUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/updateUserForm")
    public ModelAndView updateUser(@RequestParam("id") long id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", new UserDto(userService.getUserById(id)));
        return new ModelAndView("updateUserForm", "user", new UserDto(userService.getUserById(id)));
    }

    @PostMapping("/editUser")
    public ModelAndView editUser(@ModelAttribute("user") UserDto userDto) {
        if (userService.updateUser(userDto)) {
            return new ModelAndView("redirect:/admin", "userList", userService.allUsers());
        } else {
            return new ModelAndView("wrongName");
        }
    }
}
