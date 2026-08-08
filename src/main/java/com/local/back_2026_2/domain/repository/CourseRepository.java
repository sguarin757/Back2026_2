package com.local.back_2026_2.domain.repository;
import com.local.back_2026_2.domain.models.Course;
import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findall();
    Optional<Course> findById(Long id);
    Course save(Course student);
    void deleteById(Long id);
    Optional<Course> update (Course student);
    Boolean existsByStudentId(Long studentId);
}
