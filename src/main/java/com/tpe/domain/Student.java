package com.tpe.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide lastname!")//null,"","  " boşluguda kabul etmez.
    @Column(nullable = false)//gerek kalmaz bunu yazmaya uygulama icersinde değişim yaparken null a dönüştürmüş oluruz
    private String firstName;

    @NotEmpty(message = "Please provide lastname!")//null,"" kabul etmez fakat boşluk bırakırsak kabul eder.
    @Column(nullable = false)//database kayıt ederken kontrol eder
    private String lastName;

    //<!-- Validator Dependency --> hibernote validator kütüphanesinde var
    @NotNull(message = "Please provide grade!")//kullanıcı aşamasında kısıtlar frontend kontrolü yani//null kabul etmez yani boş gecince
    @Column(nullable = false)
    private Integer grade;

    private LocalDateTime createDate=LocalDateTime.now();


    //getter - setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

//    public void setCreateDate(LocalDateTime createDate) {
//        this.createDate = createDate;
//    }
}
