package com.example.contact_directory.repository;

import com.example.contact_directory.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByUserUserId(Long userId);
    List<Contact> findByNameContainingIgnoreCase(String name);
    List<Contact> findByEmailContainingIgnoreCase(String email);
}
