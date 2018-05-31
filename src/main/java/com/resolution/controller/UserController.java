package com.resolution.controller;

import com.resolution.domain.dto.UserDTO;
import com.resolution.domain.entity.User;
import com.resolution.infra.transformer.UserMapper;
import com.resolution.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@EnableCaching
@RequestMapping("/user")
public class UserController {

    private UserService service;
    private UserMapper mapper;

    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

    @Cacheable("user")
    @RequestMapping(value = "/findAll")
    public ModelAndView list(ModelAndView model) throws IOException {

        List<UserDTO> userDTOS = service.findAllUsers()
                .stream()
                .map(mapper::userToUserDTO)
                .collect(Collectors.toList());

        model.addObject("userList", userDTOS);
        model.setViewName("home");
        return model;
    }

    @GetMapping("/find/by/{id}")
    @Cacheable("user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView create(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView();

        UserDTO userDTO = service.findUserById(id)
                .map(mapper::userToUserDTO)
                .orElseGet(UserDTO::new);

        model.addObject("user", userDTO);
        model.setViewName("findUser");
        return model;
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView create(ModelAndView model) {
        User user = new User();
        model.addObject("user", user);
        model.setViewName("userForm");
        return model;
    }

    @CachePut("user")
    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute User user) {
        if (user.getId() == 0) {
            service.saveUser(user);
        } else {
            service.updateUser(user);
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView delete(@ModelAttribute User user) {
        service.deleteUser(user);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit")
    @Cacheable("user")
    public ModelAndView edit(HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        Optional<User> user = service.findUserById(id);
        ModelAndView model = new ModelAndView("userForm");
        model.addObject("user", user);
        return model;
    }

    @GetMapping("greet-clear")
    @CacheEvict(cacheNames = "greeting", allEntries = true)
    public void clearCache() {
    }
}
