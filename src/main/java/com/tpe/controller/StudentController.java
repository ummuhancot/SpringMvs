package com.tpe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//requestler bu classta karşılanacak ve ilgili metodlarla maplenecek
//obje spring tarafından üretilecek,rukuestelr kullanıcıdan gelen istekler burda karşılanacak ve ilgil controllere yönlendirilecek
@RequestMapping("/students")//http:localhost:8080/SpringMvc/students/....
//rukuestler yani isteklerin map edilmesini sağlayacak
//uygulamaya hangi istekler gelecek bellidir
//class:tüm metodlar için geçerli olur
//method:sadece ilgili metodu requestle mapler
public class StudentController {

    //NOT:controllerda metodlar geriye mav veya String data tipi döndürebilir.

    //http://localhost:8080/SpringMvc/statics/css/edu.jpeg
    //http://localhost:8080/SpringMvc/statics/css/style.css


    /*
    ///bir rukuest nasıl karşılanır onu anlıyoruz
    //http:localhost:8080/SpringMvc/students/hi + GET--okuma
    //http:localhost:8080/SpringMvc/students/hi + POST--kayıt
    //http:localhost:8080/SpringMvc/students/hi + PUT--ekleme
    //http:localhost:8080/SpringMvc/students/hi + DELETE--silme gibi mapler var.
    //@RequestMapping("/students")//bu işaretle isteği rukuesti işaretle demek
    // class seviyesinde yaptık burda method icin yaptık
    @GetMapping("/hi")//get methoduyla eşleştirdik hi verdiği icin icinde belirttik.
    public sayHi(){
        //response u hazırlayacak yani cevabı hazırlanacak
    }
    */

    ///rukuest gönderme
    //http:localhost:8080/SpringMvc/students/hi + GET--okuma
    //http:localhost:8080/SpringMvc/students/hi + POST--kayıt
    //@RequestMapping("/students")
    @GetMapping("/hi")
    public ModelAndView sayHi(){
        //response u hazırlayacak
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi,");//hi.jsp kısmının <h2>${message} burayı tanımladık
        mav.addObject("messagebody","I'm a Student Management System");//hi.jsp kısmının ${messagebody}<h2/> bu kısmı belirledik
        mav.setViewName("hi");
        return mav;
    }

    //view resolver : /WEB-INF/views/hi.jsp dosyasını bulur ve mav içindeki modelı
    //dosyaya bind eder ve clienta gönderir.


    ///resolver karşılama
    //1-tüm öğrencileri listeleme:
    //http://localhost:8080/SpringMvc/students + GET
    @GetMapping
    public ModelAndView getStudents(){
        ModelAndView mav=new ModelAndView();
        //todo:dbden liste gelecek...
        mav.setViewName("students");
        return mav;
    }




}
