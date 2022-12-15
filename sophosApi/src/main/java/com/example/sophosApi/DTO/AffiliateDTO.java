package com.example.sophosApi.DTO;

import jakarta.validation.constraints.NotBlank;

public class AffiliateDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private int age;
    @NotBlank(message = "Email is mandatory")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AffiliateDTO(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public AffiliateDTO(){

    }
}
