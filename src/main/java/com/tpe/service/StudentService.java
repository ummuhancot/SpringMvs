package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFountException;
import com.tpe.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service//bu classın bir service oldugunu belirtir ve spring bu classı tarar ve bean olarak tanımlar.
public class StudentService implements IStudentService{

    @Autowired
    private IStudentRepository repository;


    //1-c:
    @Override
    public List<Student> listAllStudents() {
        return repository.findAll();
    }

    //2-c:
    @Override
    public void addOrUpdateStudent(Student student) {
        repository.saveOrUpdate(student);
    }

    //3-b:
    @Override
    public Student findStudentById(Long id) {
        Student student = repository.findById(id).//get():NoSuchElementException fırlatır .opsiyonel boş ise exception fırlatır.
                orElseThrow(()->new StudentNotFountException("Student not found by id :" + id));//supplier interface kullanıldı.fonksiyonel interfacedir.
                //orelsethrow biz bir exception fırlatmak istiyorsak bu şekilde

        //supplier interfaceini implemente eden bir class oluşturup , objesinin
        //get metodunu kullanmak yerine kısaca lambda exp ile get metodunu override ediyoruz.

        //findById metodunun geriye döndürdüğü optional içinde
        //student varsa student değişkenine atar.
        //optional objesi boşsa orElseThrow custom exception fırlatılabilir.
        return student;
    }

    //4-b
    @Override
    public void deleteStudent(Long id) {
        //idsi verilen öğrenciyi bulalım
        Student student=findStudentById(id);
        repository.delete(student);
    }





    /*
      `Optional`, null olmayan nesneleri içermek için kullanılan bir kapsayıcı nesnedir. `Optional`,
    bir değerin mevcut olup olmadığını temsil etmek için kullanılır.
    Java'da null kontrolleri ve `NullPointerException`'dan kaçınmak için bir yol sağlar.

    İşte `Optional`'ın nasıl kullanılabileceğine dair bir örnek:

    ```java
    import java.util.Optional;

    public class OptionalExample {
        public static void main(String[] args) {
            Optional<String> optional = Optional.of("Merhaba, Dünya!");

            // Değerin mevcut olup olmadığını kontrol et
            if (optional.isPresent()) {
                System.out.println(optional.get());
            }

            // ifPresent kullanımı
            optional.ifPresent(value -> System.out.println("Değer: " + value));

            // orElse kullanımı
            String defaultValue = optional.orElse("Varsayılan Değer");
            System.out.println(defaultValue);

            // orElseGet kullanımı
            String value = optional.orElseGet(() -> "Tedarikçiden Varsayılan Değer");
            System.out.println(value);

            // orElseThrow kullanımı
            try {
                String valueOrException = optional.orElseThrow(() -> new IllegalArgumentException("Değer mevcut değil"));
                System.out.println(valueOrException);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Bu örnekte, `Optional`, null olabilecek bir değeri güvenli bir şekilde ele almak için kullanılır ve
    değerin mevcut olması durumunda çeşitli yöntemler sağlar veya değerin yokluğunu zarif bir şekilde ele alır.
    */
    /**`orElseThrow` metodu, `Optional` sınıfından bir değeri almak için kullanılır. Eğer `Optional` boşsa, belirtilen istisnayı fırlatır.
     Bu durumda, `StudentNotFountException` istisnası fırlatılır ve öğrenci bulunamadığında özel bir hata mesajı gösterilir.

     orElseThrow(() -> new StudentNotFountException("Student not found by id :" + id));

     - `orElseThrow`: `Optional` nesnesi boşsa, belirtilen istisnayı fırlatır.
     - `() -> new StudentNotFountException("Student not found by id :" + id)`:
     Lambda ifadesi, özel bir hata mesajı ile `StudentNotFountException` oluşturur.
     Mesajda, bulunamayan öğrenci kimliği belirtilir.

     Özetle, `repository.findById(id)` tarafından döndürülen `Optional<Student>` boşsa,
     bu satır `StudentNotFountException` fırlatır ve öğrenci kimliğini içeren bir mesaj gösterir.*/

}
