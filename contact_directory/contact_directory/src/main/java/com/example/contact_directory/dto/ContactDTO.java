package com.example.contact_directory.dto;

import java.time.LocalDateTime;

public class ContactDTO {
    private Long contactId;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private Long userId;

    public Long getContactId(){
        return contactId;
    }
    public void setContactId(Long contactId){
        this.contactId = contactId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
}
