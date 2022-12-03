package com.example.sophosApi.models;

import jakarta.persistence.*;


@Entity
@Table(name = "Test")
public class TestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long testId;
    private String name;
    private String description;

    @OneToOne(mappedBy = "test", cascade=CascadeType.ALL)
//    @JoinColumn(name = "id_test", referencedColumnName = "id")
    private AppointmentModel appointment;


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

//    public TestModel(Long id, String name, String description) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//    }
//
    public TestModel(Long id) {
        this.testId = id;
    }

    public TestModel(){

    }
}
