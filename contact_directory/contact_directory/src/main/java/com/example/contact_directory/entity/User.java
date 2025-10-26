package com.example.contact_directory.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    private LocalDateTime joinDate = LocalDateTime.now();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonIgnoreProperties("user")
    private List<Group> groups;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonIgnoreProperties("user")
    private List<Contact> contacts;
}
