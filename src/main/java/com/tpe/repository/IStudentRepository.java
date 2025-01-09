package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {

    List<Student> findAll();

    void saveOrUpdate(Student student);

    void delete(Student student);

    Optional<Student> findById(Long id);//NullPointerException almamak icin
                                        //null yerine boş bir optional objesi döner
                                        //DİKKAT! NULL cıkabilir.!!! demek yani
    //optional kullanmamızın sebebi null degeri olabilir ve null pointer exception alabiliriz. bu yüzden optional kullanıyoruz.null değeri alabilme ihtimali olan yerlerde kullanılır.






}
