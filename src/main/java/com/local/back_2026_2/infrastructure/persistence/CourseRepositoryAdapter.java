package com.local.back_2026_2.infrastructure.persistence;

import com.local.back_2026_2.domain.models.Course;
import com.local.back_2026_2.domain.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class CourseRepositoryAdapter implements CourseRepository {

    private final CourseJpaRepository courseJpaRepository;

    public CourseRepositoryAdapter(CourseJpaRepository courseJpaRepository) {
        this.courseJpaRepository = courseJpaRepository;
    }

    @Override
    public List<Course> findall() {
        return courseJpaRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseJpaRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return courseJpaRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        courseJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Course> update(Course course) {
        if (!courseJpaRepository.existsById(course.getId())) {
            return Optional.empty();
        }
        return Optional.of(courseJpaRepository.save(course));
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        return courseJpaRepository.existsById(studentId);
    }
}
