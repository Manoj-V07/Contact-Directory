package com.example.contact_directory.routing;

import com.example.contact_directory.entity.User;
import com.example.contact_directory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserViewController {
    private final UserService userService;

    @GetMapping
    public String listUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("user",new User());
        return "user-form";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user){
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "user-form";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,@ModelAttribute("user") User user){
        userService.updateUser(id,user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
