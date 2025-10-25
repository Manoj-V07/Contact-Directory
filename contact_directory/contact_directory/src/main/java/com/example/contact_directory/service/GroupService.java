package com.example.contact_directory.service;

import com.example.contact_directory.exception.ResourceNotFoundException;
import com.example.contact_directory.entity.Group;
import com.example.contact_directory.entity.User;
import com.example.contact_directory.repository.GroupRepository;
import com.example.contact_directory.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository,UserRepository userRepository){
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Group createGroup(Long userId,Group group){
        User user = userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User Not Found With ID : " + userId));
        group.setUser(user);
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    public List<Group> getGroupsByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found With : " + userId));
        return groupRepository.findByUser(user);
    }

    public Group getGroupById(Long id){
        return groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group Not Found With ID : " + id));
    }

    public Group updateGroup(Long id,Group updatedGroup){
        Group existingGroup = getGroupById(id);
        existingGroup.setName(updatedGroup.getName());
        existingGroup.setDescription(updatedGroup.getDescription());
        return groupRepository.save(existingGroup);
    }

    public void deleteGroup(Long id){
        Group group = getGroupById(id);
        groupRepository.delete(group);
    }
}
