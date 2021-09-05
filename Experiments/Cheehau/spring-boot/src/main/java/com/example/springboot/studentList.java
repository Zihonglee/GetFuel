package com.example.springboot;

import java.time.LocalDateTime;

public class studentList {
    private Long id;
    private LocalDateTime timeCreated;
    private String username;
    private String email;
    private String password;

    public studentList(Long id, LocalDateTime timeCreated, String username, String email, String password) {
        this.id = id;
        this.timeCreated = timeCreated;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public studentList(LocalDateTime timeCreated, String username, String email, String password) {
        this.timeCreated = timeCreated;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "studentList{" +
                "id=" + id +
                ", timeCreated=" + timeCreated +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
