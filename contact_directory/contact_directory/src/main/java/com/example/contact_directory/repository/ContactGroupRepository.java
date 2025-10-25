package com.example.contact_directory.repository;

import com.example.contact_directory.entity.ContactGroup;
import com.example.contact_directory.entity.Group;
import com.example.contact_directory.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactGroupRepository extends JpaRepository<ContactGroup,Long> {
    List<ContactGroup> findByGroup(Group group);
    List<ContactGroup> findByContact(Contact contact);
}
