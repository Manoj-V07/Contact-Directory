package com.example.contact_directory.routing;

import com.example.contact_directory.service.ContactService;
import com.example.contact_directory.service.GroupService;
import com.example.contact_directory.service.ContactGroupService;
import com.example.contact_directory.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/contact-group")
@RequiredArgsConstructor
public class ContactGroupViewController {
    private final ContactGroupService contactGroupService;
    private final ContactService contactService;
    private final GroupService groupService;

    @GetMapping
    public String viewAllGroupMappings(Model model){
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups",groups);
        return "contact-group-list";
    }

    @GetMapping("/assign")
    public String showAssignForm(Model model){
        model.addAttribute("contacts",contactService.getAllContacts());
        model.addAttribute("groups",groupService.getAllGroups());
        return "assign-contact-group";
    }

    @PostMapping("/assign")
    public String assignContactToGroup(@RequestParam Long contactId,@RequestParam Long groupId){
        contactGroupService.assignContactToGroup(contactId,groupId);
        return "redirect:/contact-group";
    }
}
