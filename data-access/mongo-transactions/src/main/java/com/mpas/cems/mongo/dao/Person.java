
package com.mpas.cems.mongo.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Document(collection="person")
public class Person {

    @Id
    private BigInteger id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime hiringDate;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Person() {
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "Person{" +
                "\n id=" + id +
                "\n, username='" + username + '\'' +
                "\n, firstName='" + firstName + '\'' +
                "\n, lastName='" + lastName + '\'' +
                "\n}";
    }

}
