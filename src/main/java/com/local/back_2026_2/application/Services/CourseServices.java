package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.domain.models.Course;
import com.local.back_2026_2.domain.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseServices implements CourseRepository {
    @Override
    public List<Course> findall() {
        return List.of();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Course save(Course student) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Course> update(Course student) {
        return Optional.empty();
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        return null;
    }
}
