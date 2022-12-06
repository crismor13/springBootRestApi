package com.example.sophosApi.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Afiliado")
public class AffiliateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long affiliateId;
    private String name;
    private int age;
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "affiliate",cascade=CascadeType.ALL)
    private List<AppointmentModel> appointments;

    public Long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Long affiliateId) {
        this.affiliateId = affiliateId;
    }

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

    public AffiliateModel(Long id, String name, int age, String email) {
        this.affiliateId = affiliateId;
        this.name = name;
        this.age = age;
        this.email = email;
    }

//    public AffiliateModel(Long id) {
//        this.affiliateId= id;
//
//    }
    public AffiliateModel(){

    }
}
