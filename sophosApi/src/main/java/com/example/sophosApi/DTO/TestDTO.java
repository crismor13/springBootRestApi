package com.example.sophosApi.DTO;

import jakarta.validation.constraints.NotBlank;

public class TestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TestDTO(){

    }
}
