package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



//@Component
@Repository//bu classın bir repository oldugunu belirtir ve spring bu classı tarar ve bean olarak tanımlar.
public class StudentRepository implements IStudentRepository{

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    //1-b:tablodan tüm satırları getirme
    @Override
    public List<Student> findAll() {
        Session session=sessionFactory.openSession();
        List<Student> studentList = session.createQuery("From Student",Student.class).getResultList();
        session.close();
        return studentList;
    }

    //2-d:tabloda kayıt etme veya güncelleme methodu
    @Override
    public void saveOrUpdate(Student student) {

        session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(student);
        transaction.commit();
        session.close();
    }

    //4-c:tablodan kayıt silme
    @Override
    public void delete(Student student) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(student);
        transaction.commit();
        session.close();
    }


    //3-a:
    //ögrenciyi bulma methodu
    @Override
    public Optional<Student> findById(Long id) {
        session=sessionFactory.openSession();
        Student student = session.get(Student.class,id);
        Optional<Student> optional = Optional.ofNullable(student);//Attention!!! dikkat null değer gelebilir.
        session.close();
        return optional;
    }



}
