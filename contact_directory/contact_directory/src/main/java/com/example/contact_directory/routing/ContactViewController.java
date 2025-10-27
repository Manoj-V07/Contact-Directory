package com.example.contact_directory.routing;

import com.example.contact_directory.entity.Contact;
import com.example.contact_directory.service.ContactService;
import com.example.contact_directory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactViewController {
    public final ContactService contactService;
    public final UserService userService;

    @GetMapping
    public String listContacts(Model model){
        model.addAttribute("contacts",contactService.getAllContacts());
        return "contact-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("contact",new Contact());
        model.addAttribute("users",userService.getAllUsers());
        return "contact-form";
    }

    @PostMapping
    public String saveContact(@ModelAttribute("contact") Contact contact){
        contactService.addContact(contact,contact.getUser().getUserId());
        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String showWditForm(@PathVariable Long id,Model model){
        model.addAttribute("contact",contactService.getContactById(id));
        model.addAttribute("users",userService.getAllUsers());
        return "contact-form";
    }

    @PostMapping("/update/{id}")
    public String updateContact(@PathVariable Long id,@ModelAttribute("contact") Contact contact){
        contactService.updateContact(id,contact);
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }

    @GetMapping("/search")
    public String searchContacts(@RequestParam String keyword,Model model){
        model.addAttribute("contacts",contactService.searchContacts(keyword));
        model.addAttribute("keyword",keyword);
        return "contact-list";
    }
}
