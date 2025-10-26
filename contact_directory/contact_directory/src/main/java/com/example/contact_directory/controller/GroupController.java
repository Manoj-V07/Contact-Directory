package com.example.contact_directory.controller;

import com.example.contact_directory.entity.Group;
import com.example.contact_directory.service.GroupService;
import com.example.contact_directory.mapper.EntityMapper;
import com.example.contact_directory.dto.GroupDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;
    
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<GroupDTO> createGroup(@PathVariable Long userId,@RequestBody GroupDTO groupDTO){
        Group group = EntityMapper.toGroupEntity(groupDTO);
        Group saved = groupService.createGroup(userId,group);
        return ResponseEntity.ok(EntityMapper.toGroupDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups(){
        List<GroupDTO> groups = groupService.getAllGroups().stream().map(EntityMapper::toGroupDTO).toList();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupDTO>> getGroupsByUser(@PathVariable Long userId){
        List<GroupDTO> groups = groupService.getGroupsByUser(userId).stream().map(EntityMapper::toGroupDTO).toList();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id){
        return ResponseEntity.ok(EntityMapper.toGroupDTO(groupService.getGroupById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id,@RequestBody GroupDTO groupDTO){
        Group updated = groupService.updateGroup(id,EntityMapper.toGroupEntity(groupDTO));
        return ResponseEntity.ok(EntityMapper.toGroupDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id){
        groupService.deleteGroup(id);
        return ResponseEntity.ok("Group Deleted Successfully");
    }
}
