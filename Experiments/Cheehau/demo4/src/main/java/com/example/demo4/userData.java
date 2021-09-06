package com.example.demo4;

import java.time.LocalDate;

public class userData {
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate timeCreated;

    public userData(String username, String email, String password, LocalDate timeCreated) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.timeCreated = timeCreated;
    }

    public userData(Long id, String username, String email, String password, LocalDate timeCreated) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        return "userData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", timeCreated=" + timeCreated +
                '}';
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
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

    public LocalDate getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDate timeCreated) {
        this.timeCreated = timeCreated;
    }


}
