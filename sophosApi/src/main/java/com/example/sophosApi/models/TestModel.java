package com.example.sophosApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;


@Entity
@Table(name = "Test")
public class TestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long testId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

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

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<AppointmentModel> appointments;
    public TestModel(Long testId, @NotEmpty String name, @NotEmpty String description) {
        this.testId = testId;
        this.name = name;
        this.description = description;
    }


    public TestModel(){

    }
}
