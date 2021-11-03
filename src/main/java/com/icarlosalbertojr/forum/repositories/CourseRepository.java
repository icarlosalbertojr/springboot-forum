package com.icarlosalbertojr.forum.repositories;

import com.icarlosalbertojr.forum.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseName(String courseName);

}
