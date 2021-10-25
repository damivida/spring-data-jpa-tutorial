package com.example.springdatajpatutotial.repository;

import com.example.springdatajpatutotial.entity.Course;
import com.example.springdatajpatutotial.entity.CourseMaterial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {

        Course course = Course.builder()
                .title("SpringBootTutorials")
                .credit("11")
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.courseMaterial.com")
                .course(course)
                .build();

       CourseMaterial courseMaterialSaved =  courseMaterialRepository.save(courseMaterial);
        Assertions.assertEquals(courseMaterial.getUrl(), courseMaterialSaved.getUrl());
    }

    @Test
    public void getAllCourseMaterials() {
        List<CourseMaterial> courseMaterialList = courseMaterialRepository.findAll();
        courseMaterialList.forEach(courseMaterial -> System.out.println(courseMaterial));
        Assertions.assertEquals("courseMaterial.com", courseMaterialList.get(0).getUrl());
    }
}