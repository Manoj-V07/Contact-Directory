package com.example.contact_directory.repository;

import com.example.contact_directory.entity.User;
import com.example.contact_directory.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {
    List<Group> findByUser(User user);    
}
