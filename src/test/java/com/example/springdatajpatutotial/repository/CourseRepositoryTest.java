package com.example.springdatajpatutotial.repository;

import com.example.springdatajpatutotial.entity.Course;
import com.example.springdatajpatutotial.entity.Student;
import com.example.springdatajpatutotial.entity.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getAllCourses() {

        List<Course> courseList = courseRepository.findAll();
        courseList.forEach(course -> System.out.println(course.getCourseMaterial().getUrl()));
        Assertions.assertEquals("JPATutorials", courseList.get(0).getTitle());
    }

    @Test
    public void saveCourseWithTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("SQLTeacherFirstName")
                .lastName("SQLTeacherLastName")
                .build();

        Course course = Course.builder()
                .title("SQLCurse")
                .credit("15")
                .teacher(teacher)
                .build();

        Course courseSaved = courseRepository.save(course);
        System.out.println(courseSaved);
        Assertions.assertEquals("SQLTeacherFirstName", courseSaved.getTeacher()
                .getFirstName());

    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 3);

        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();
        Long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();
        int totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        courses.forEach(course -> System.out.println(course));

        System.out.println(totalElements);
        System.out.println(totalPages);
    }

    @Test
    public void findAllWithSorting() {
        Pageable sortByTitle = PageRequest.of(0, 3, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0, 3, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 3,
                Sort.by("title").descending().and(Sort.by("credit")));


        List<Course> courseList = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();

        courseList.forEach(course -> System.out.println(course));
    }

    @Test
    public void findByTitleContaining() {
        Pageable firstPageWithTenRecords = PageRequest.of(0, 10);

        List<Course> courses = courseRepository.findCourseByTitleContaining("O", firstPageWithTenRecords).getContent();
        courses.forEach(course -> System.out.println(course));
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("PythonTeacherFirstName")
                .lastName("PythonTeacherLastName")
                .build();

        Student student = Student.builder()
                .firstName("PythonStudentFirstName")
                .lastName("PythonStudentLastName")
                .emailId("studentPython@mail.com")
                .build();

        Course course = Course.builder()
                .title("PythonCourse")
                .teacher(teacher)
                .credit("17")
                .build();

        course.addStudents(student);

       Course savedCourse =  courseRepository.save(course);

       Assertions.assertEquals("PythonStudentFirstName", savedCourse.getStudents()
                                                                            .get(0).getFirstName());

    }
}