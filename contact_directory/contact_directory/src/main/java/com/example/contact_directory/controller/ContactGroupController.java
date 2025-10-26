package com.example.contact_directory.controller;

import com.example.contact_directory.entity.ContactGroup;
import com.example.contact_directory.dto.ContactGroupDTO;
import com.example.contact_directory.dto.GroupDTO;
import com.example.contact_directory.dto.ContactDTO;
import com.example.contact_directory.mapper.EntityMapper;
import com.example.contact_directory.service.ContactGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contact-group")
@RequiredArgsConstructor
public class ContactGroupController {
    private final ContactGroupService contactGroupService;

    @PostMapping("/contact/{contactId}/groups/{groupId}")
    public ResponseEntity<ContactGroupDTO> assignContactToGroup(@PathVariable Long contactId,@PathVariable Long groupId){
        ContactGroup saved = contactGroupService.assignContactToGroup(contactId,groupId);
        return ResponseEntity.ok(EntityMapper.toContactGroupDTO(saved));
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<List<ContactDTO>> getContactsByGroup(@PathVariable Long groupId){
        List<ContactDTO> contacts = contactGroupService.getContactsByGroup(groupId).stream().map(EntityMapper::toContactDTO).toList();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/contacts/{contactId}")
    public ResponseEntity<List<GroupDTO>> getGroupsByContact(@PathVariable Long contactId){
        List<GroupDTO> groups = contactGroupService.getGroupsByContact(contactId).stream().map(EntityMapper::toGroupDTO).toList();
        return ResponseEntity.ok(groups);
    }
}
