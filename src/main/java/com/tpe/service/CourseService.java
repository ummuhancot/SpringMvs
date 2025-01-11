package com.tpe.service;

import com.tpe.domain.Course;
import com.tpe.repository.CRepository;
import com.tpe.repository.CourseRepository;
import com.tpe.repository.CRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    @Qualifier("courseRepository")
    private CRepository repository;

    //1-b
    public List<Course> getAllCourse(){
        return repository.findAll();
    }

    //3-b
    public void saveOrUpdateCourse(@Valid Course course) {
        repository.saveOrUpdate(course);
    }


    //4-b
    public Course findCourseById(Long identity) {
        Course course = (Course) repository.findById(identity).get();
        //interfacenin methodu generik bi tip gönderdiği icin objec
        // olduğunu söylüyor o yüzden course diye castink yaptık.
        return course;
    }

    //5-b
    public void deleteCourse(Long id) {
       Course found =findCourseById(id);
       repository.delete(found);
    }


    //6-b
    public List<Course> getCoursesByDuration(int duration) {
        return repository.findAllByDuration(duration);
    }
}