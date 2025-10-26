package com.example.contact_directory.controller;

import com.example.contact_directory.entity.Contact;
import com.example.contact_directory.service.ContactService;
import com.example.contact_directory.mapper.EntityMapper;
import com.example.contact_directory.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired 
    private ContactService contactService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ContactDTO> addContact(@PathVariable Long userId,@RequestBody ContactDTO contactDTO){
        Contact contact = EntityMapper.toContactEntity(contactDTO);
        Contact saved = contactService.addContact(contact,userId);
        return ResponseEntity.ok(EntityMapper.toContactDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts(){
        List<ContactDTO> contacts = contactService.getAllContacts().stream().map(EntityMapper::toContactDTO).toList();
        return ResponseEntity.ok(contacts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id){
        return ResponseEntity.ok(EntityMapper.toContactDTO(contactService.getContactById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long id,@RequestBody ContactDTO contactDTO){
        Contact updated = contactService.updateContact(id,EntityMapper.toContactEntity(contactDTO));
        return ResponseEntity.ok(EntityMapper.toContactDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return ResponseEntity.ok("Contact Deleted Successfully !!!");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactDTO>> searchContacts(@RequestParam String keyword){
        List<ContactDTO> results = contactService.searchContacts(keyword).stream().map(EntityMapper::toContactDTO).toList();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ContactDTO>> getContactsByUser(@PathVariable Long userId){
        List<ContactDTO> contacts = contactService.getContactsByUser(userId).stream().map(EntityMapper::toContactDTO).toList();
        return ResponseEntity.ok(contacts);
    }
}
