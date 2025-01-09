package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFountException;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller//requestler bu classta karşılanacak ve ilgili metodlarla maplenecek
//obje spring tarafından üretilecek,rukuestelr kullanıcıdan gelen istekler burda karşılanacak ve ilgil controllere yönlendirilecek
@RequestMapping("/students")//http:localhost:8080/SpringMvc/students/....
//rukuestler yani isteklerin map edilmesini sağlayacak
//uygulamaya hangi istekler gelecek bellidir
//class:tüm metodlar için geçerli olur
//method:sadece ilgili metodu requestle mapler
public class StudentController {

    @Autowired
    private IStudentService Sservice;
    //NOT:controllerda metodlar geriye mav veya String data tipi döndürebilir.

    //http://localhost:8080/SpringMvc/statics/css/edu.jpeg
    //http://localhost:8080/SpringMvc/statics/css/style.css


    /*
    ///bir rukuest nasıl karşılanır onu anlıyoruz
    //http:localhost:8080/SpringMvc/students/hi + GET--okuma
    //http:localhost:8080/SpringMvc/students/hi + POST--kayıt
    //http:localhost:8080/SpringMvc/students/hi + PUT--ekleme
    //http:localhost:8080/SpringMvc/students/hi + DELETE--silme gibi mapler var.
    @RequestMapping("/students")//bu işaretle isteği rukuesti işaretle demek
    // class seviyesinde yaptık burda method icin yaptık
    @GetMapping("/hi")//get methoduyla eşleştirdik hi verdiği icin icinde belirttik.
    public sayHi(){
        //response u hazırlayacak yani cevabı hazırlanacak
    }
    */

    //http:localhost:8080/SpringMvc/students/hi + GET--okuma
    //http:localhost:8080/SpringMvc/students/hi + POST--kayıt
    //@RequestMapping("/students")
    @GetMapping("/hi")
    public ModelAndView sayHi(){
        //response u hazırlayacak
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi,");//hi.jsp kısmının <h2>${message} burayı tanımladık
        mav.addObject("messagebody","I'm a Student Management System");//hi.jsp kısmının ${messagebody}<h2/> bu kısmı belirledik
        mav.setViewName("hi");//dosya ismi
        return mav;
    }

    //view resolver : /WEB-INF/views/hi.jsp dosyasını bulur ve mav içindeki modelı
    //dosyaya bind eder ve clienta gönderir.


    //1-tüm öğrencileri listeleme:
    //http://localhost:8080/SpringMvc/students + GET
    @GetMapping
    public ModelAndView getStudents(){
        List<Student> allStudents=Sservice.listAllStudents();
        ModelAndView mav=new ModelAndView();
        mav.addObject("studentList",allStudents);
        mav.setViewName("students");//students.jsp sayfanın ismi
        return mav;
    }


    //2-öğrenci ekleme formu ögrenciyi kayıt etme
    //request: http://localhost:8080/SpringMvc/students/new + GET
    //response: form göstermek
    //NOT:controllerda metodlar geriye mav veya String data tipi döndürebilir.
    @GetMapping("/new")
    public String sentForm(@ModelAttribute("student") Student student){//modelattribute viev katmanı ile controller katmanı arasında bilgiyi transfer etmeyi saglar.

       return "studentForm";

    }
    //işlem sonunda: studentın firstname,lastname ve grade değerleri set edilmiş halde
    //controller classında yer alır

    //studentFrom.jsp sayfasında yer alır

    //ModelAttribute anatasyonu view katmanı ile controller arasında
    //modelın transferini sağlar.


    //2-b:öğrenciyi ekleme formu kayıt etme işlemi- formun icindeki öğrenciyi kayıt etme
    //repuet: http://localhost:8080/SpringMvc/students/saveStudent + POST
    //repuet: http://localhost:8080/SpringMvc/students/new + POST--> sorun olmazdı yukarı GET ile yapılmış burada POST ile yapıldı
    //response: ögrenciyi kayıt etme işlemi-ögrenciyi tabloya ekleyeceğiz ve liste göndereceğiz
    @PostMapping("/saveStudent")
    public String addStudent(@Valid @ModelAttribute("student")Student student, BindingResult bindingResult){// burda student'i alip asagida kaydetmeyi yaptik
                //BindingResult bindingResult ile olusabilecek hatalari burdan aliyorum
        //valid anatasyonu ile hataları alıyorum ve bindingresult ile hataları alıyorum
        if (bindingResult.hasErrors()){// eger hata var ise
            return "studentForm";
        }//hata mesajı geldiğinde yeni sayfaya yönlendirme yapma burayı tekrar gösetr diyoruz
        //böylece hata mesajını gösterimiş oluyoruz

        Sservice.addOrUpdateStudent(student);

        return "redirect:/students";//tüm ögrencileri listeleme sayfasına yönlendirme
         //redirect gelen bir istegi baska bir istege yonlendiriri yani http://localhost:8080/SpringMvc/students istegine yonlendirir
        //tüm ögrencileri listeleme sayfasına yönlendirme
        //redirect:bir sayfadan diğer sayfaya yönlendirme işlemi yapar yani 1 kısmındaki yere yönlendirdik.
        //http://localhost:8080/SpringMvc/students + GET buraya yönlendirir

    }

    /*
    Bu kod parçası, bir HTTP POST isteği ile gelen öğrenci verilerini alır, doğrular ve veritabanına kaydeder veya günceller. İşte adım adım açıklaması:

    1. `@PostMapping("/saveStudent")`: Bu anotasyon, `/saveStudent` URL'sine yapılan POST isteklerini bu metoda yönlendirir.
    2. `public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult)`: Bu metod, `student` nesnesini ve doğrulama hatalarını içeren `bindingResult` nesnesini parametre olarak alır.
    3. `@Valid`: Bu anotasyon, `student` nesnesinin doğrulanmasını sağlar.
    4. `@ModelAttribute("student")`: Bu anotasyon, `student` nesnesinin form verilerinden alınmasını sağlar.
    5. `BindingResult bindingResult`: Bu nesne, doğrulama hatalarını tutar.
    6. `if (bindingResult.hasErrors())`: Eğer doğrulama hataları varsa, `studentForm` sayfasına geri döner.
    7. `Sservice.addOrUpdateStudent(student)`: Eğer doğrulama hatası yoksa, `student` nesnesi veritabanına kaydedilir veya güncellenir.
    8. `return "redirect:/students"`: İşlem tamamlandıktan sonra, kullanıcıyı tüm öğrencilerin listelendiği sayfaya yönlendirir.
    */

    //3-öğrenci güncelleme formu ögrenciyi güncelleme
    //request:http://localhost:8080/SpringMvc/students/update?id=3 + GET
    //response:update için id si verilen öğrencinin bilgileri ile formu gösterme    @GetMapping("/update")
    //idsi verilen ögrenciyi bulmamız gerekir...
    @GetMapping("/update")
    public ModelAndView sendFormUpdate(@RequestParam("id") Long identity){//request:http://localhost:8080/SpringMvc/students/update?id=3 + GET

        //? işareti ile olunca reqesetpram ı kullandık ve id yi aldık ve identitye atadık

        Student foundStudent=Sservice.findStudentById(identity);

        ModelAndView mav=new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");
        return mav;

    }

    //--2.adım sprig bootta olacak
    //kullanıcıdan bilgi nasıl alınır
    //1-form/body(JSON)
    //2-query param : /query?id=3
    //3-path param : /3
    //query param ve path param sadece 1 tane ise isim belirtmek opsiyonel



    //4-bir öğrenciyi silme
    //request : http://localhost:8080/SpringMvc/students/delete/4 + GET
    //response :öğrenci silinir ve kalan öğrenciler gösterilir
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long identity) {
        Student foundStudent = Sservice.findStudentById(identity);
        Sservice.deleteStudent(identity);
        return "redirect:/students";
    }


    //bir sayfa ve data gerekliyse modelandview kullanılır.
    //Exceptionları yakalamak icin bir metod yazcaz
    //@ExceptionHandler:try-catch bloğunun mantığıyla benzer çalışır
    @ExceptionHandler(StudentNotFountException.class)
    public ModelAndView handleException(Exception exception){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("message",exception.getMessage());
        modelAndView.setViewName("notFound");
        return modelAndView;
    }
    //http://localhost:8080/SpringMvc/students/delete/99

}

