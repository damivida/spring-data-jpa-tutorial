package com.example.springdatajpatutotial.repository;

import com.example.springdatajpatutotial.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course>findCourseByTitleContaining(String title, Pageable pageRequest);
}
