package com.example.contact_directory.service;

import com.example.contact_directory.entity.Contact;
import com.example.contact_directory.entity.Group;
import com.example.contact_directory.entity.ContactGroup;
import com.example.contact_directory.repository.ContactGroupRepository;
import com.example.contact_directory.repository.ContactRepository;
import com.example.contact_directory.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service 
@RequiredArgsConstructor
public class ContactGroupService {
    private final ContactGroupRepository contactGroupRepository;
    private final ContactRepository contactRepository;
    private final GroupRepository groupRepository;

    public ContactGroup assignContactToGroup(Long contactId,Long groupId){
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new RuntimeException("Contact Not Found"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group Not Found"));
        ContactGroup contactGroup = ContactGroup.builder().contact(contact).group(group).build();
        return contactGroupRepository.save(contactGroup);
    }

    public List<Contact> getContactsByGroup(Long groupId){
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group Not Found"));
        List<ContactGroup> contactGroups = contactGroupRepository.findByGroup(group);
        return contactGroups.stream().map(ContactGroup::getContact).toList();
    }

    public List<Group> getGroupsByContact(Long contactId){
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new RuntimeException("Contact Not Found"));
        List<ContactGroup> contactGroups = contactGroupRepository.findByContact(contact);
        return contactGroups.stream().map(ContactGroup::getGroup).toList();
    }
}
