package com.example.contact_directory.service;

import com.example.contact_directory.entity.Contact;
import com.example.contact_directory.entity.User;
import com.example.contact_directory.repository.ContactRepository;
import com.example.contact_directory.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    public Contact addContact(Contact contact,Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found With ID : " + userId));
        contact.setUser(user);
        contact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public Contact getContactById(Long contactId){
        return contactRepository.findById(contactId).orElseThrow(() -> new RuntimeException("Contact Not Found With ID : " + contactId));
    }

    public Contact updateContact(Long id,Contact updatedContact){
        Contact existingContact = getContactById(id);
        existingContact.setName(updatedContact.getName());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhone(updatedContact.getPhone());
        return contactRepository.save(existingContact);
    }

    public void deleteContact(Long contactId){
        contactRepository.deleteById(contactId);
    }

    public List<Contact> searchContacts(String keyword){
        List<Contact> byName = contactRepository.findByNameContainingIgnoreCase(keyword);
        List<Contact> byEmail = contactRepository.findByEmailContainingIgnoreCase(keyword);
        byName.addAll(byEmail);
        return byName.stream().distinct().toList();
    }

    public List<Contact> getContactsByUser(Long userId){
        return contactRepository.findByUserUserId(userId);
    }
}
