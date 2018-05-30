package com.resolution.controller;

import com.resolution.entity.User;
import com.resolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/")
    @GetMapping
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userJSP", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView createUser(@ModelAttribute("create") User user) {
        service.saveUser(user);
        return new ModelAndView("redirect:/view");
    }

    @RequestMapping(value = "/check-user")
    public ModelAndView checkUser(@ModelAttribute("userJSP") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewName");
        modelAndView.addObject("userJSP", user);
        return modelAndView;
    }

    @RequestMapping("/view")
    public ModelAndView view() {
        List<User> list = service.findAllUsers();
        return new ModelAndView("view", "list", list);
    }

//    @GetMapping(value = "find/by/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public UserDTO findUserById(@PathVariable("id") long id) {
//        return service.findUserById(id)
//                .map(mapper::userToUserDTO)
//                .orElseGet(UserDTO::new);
//    }
//
//    @GetMapping(value = "find/all")
//    public List<UserDTO> findAllUsers() {
//        return service.findAllUsers()
//                .stream()
//                .map(mapper::userToUserDTO)
//                .collect(Collectors.toList());
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "update")
    public void updateUser(@RequestBody User user) {
        service.updateUser(user);
    }

}
