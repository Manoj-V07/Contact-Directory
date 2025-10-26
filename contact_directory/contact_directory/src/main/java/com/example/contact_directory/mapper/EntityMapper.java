package com.example.contact_directory.mapper;

import com.example.contact_directory.dto.*;
import com.example.contact_directory.entity.*;

public class EntityMapper {

    public static User toUserEntity(UserDTO dto){
        if(dto == null)  return null;
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserDTO toUserDTO(User entity){
        if(entity == null) return null;
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setJoinDate(entity.getJoinDate());
        return dto;
    }

    public static Group toGroupEntity(GroupDTO dto){
        if(dto == null) return null;
        Group group = new Group();
        group.setGroupId(dto.getGroupId());
        group.setName(dto.getName());
        group.setDescription(dto.getDescription());
        group.setCreatedAt(dto.getCreatedAt());
        return group;
    }

    public static GroupDTO toGroupDTO(Group group){
        if(group == null) return null;
        GroupDTO dto = new GroupDTO();
        dto.setGroupId(group.getGroupId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());
        dto.setCreatedAt(group.getCreatedAt());
        if(group.getUser() != null) dto.setUserId(group.getUser().getUserId());
        return dto;
    }

    public static Contact toContactEntity(ContactDTO dto){
        if(dto == null) return null;
        Contact contact = new Contact();
        contact.setContactId(dto.getContactId());
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        contact.setCreatedAt(dto.getCreatedAt());
        return contact;
    }

    public static ContactDTO toContactDTO(Contact contact){
        if(contact == null) return null;
        ContactDTO dto = new ContactDTO();
        dto.setContactId(contact.getContactId());
        dto.setName(contact.getName());
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhone());
        dto.setCreatedAt(contact.getCreatedAt());
        if(contact.getUser() != null) dto.setUserId(contact.getUser().getUserId());
        return dto;
    }

    public static ContactGroup toContactGroupEntity(ContactGroupDTO dto){
        if (dto == null) return null;
        ContactGroup cg = new ContactGroup();
        cg.setId(dto.getId());
        return cg;
    }

    public static ContactGroupDTO toContactGroupDTO(ContactGroup cg){
        if(cg == null) return null;
        ContactGroupDTO dto = new ContactGroupDTO();
        dto.setId(cg.getId());
        if(cg.getContact() != null) dto.setContactId(cg.getContact().getContactId());
        if(cg.getGroup() != null) dto.setGroupId(cg.getGroup().getGroupId());
        return dto;
    }
}