package com.example.springdatajpatutotial.repository;

import com.example.springdatajpatutotial.entity.Course;
import com.example.springdatajpatutotial.entity.Teacher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @Disabled
    public void saveTeacher() {

        Course course1 = Course.builder()
                .title("SpringBootTutorials")
                .credit("11")
                .build();

        Course course2 = Course.builder()
                .title("JavaTutorials")
                .credit("10")
                .build();

        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);

        Teacher teacher = Teacher.builder()
                .firstName("JavaTeacherFirstName")
                .lastName("JavaTeacherLastName")
              //.course(courseList)
                .build();

        Teacher teacherSaved = teacherRepository.save(teacher);
       /* Assertions.assertEquals("JavaTutorials",
                teacherSaved.getCourse().get(1).getTitle());*/
    }

}