package com.example.contact_directory.routing;

import com.example.contact_directory.entity.Group;
import com.example.contact_directory.service.GroupService;
import com.example.contact_directory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupViewController {
    private final GroupService groupService;
    private final UserService userService;

    @GetMapping
    public String listGroups(Model model){
        model.addAttribute("groups",groupService.getAllGroups());
        return "group-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("group",new Group());
        model.addAttribute("users",userService.getAllUsers());
        return "group-form";
    }

    @PostMapping
    public String saveGroup(@ModelAttribute("group") Group group){
        groupService.createGroup(group.getUser().getUserId(),group);
        return "redirect:/groups";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,Model model){
        Group group = groupService.getGroupById(id);
        model.addAttribute("group",group);
        model.addAttribute("users",userService.getAllUsers());
        return "group-form";
    }

    @PostMapping("update/{id}")
    public String updateGroup(@PathVariable Long id,@ModelAttribute("group") Group group){
        groupService.updateGroup(id,group);
        return "redirect:/groups";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable Long id){
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }
}
