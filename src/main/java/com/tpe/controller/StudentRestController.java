package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController//bu classta restful serviceler geliştirilecek
@RequestMapping("/api")
public class StudentRestController {

    private final IStudentService service;

    @Autowired//sadece 1 constructor va ise @Autowired kullanımı opsiyoneldir yani gerek yoktur.
    public StudentRestController(IStudentService service) {
        this.service = service;
    }
    //JSON formatter konsola renkli güzel yazdırır.
    //http://localhost:8080/SpringgMvc/api/all
    //restapı da konsol olmadığı icin direk döndürebiliyoruz
    //1-tüm öğrencileri listeleme:
    //http://localhost:8080/SpringMvc/api/all + GET
    @GetMapping("/all")
    public List<Student> allStudents(){
        return service.listAllStudents();
    }



}
