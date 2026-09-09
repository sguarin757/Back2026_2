package com.local.back_2026_2.domain.repository.impl;

import com.local.back_2026_2.application.Exceptions.BussinesException;
import com.local.back_2026_2.application.Exceptions.CourseNotFoundException;
import com.local.back_2026_2.domain.models.Course;
import com.local.back_2026_2.domain.repository.CourseRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CourseRepositoryService implements CourseRepository {

    private final CourseRepository courseRepository;

    protected CourseRepositoryService(CourseRepository courseRepository) {
        this.courseRepository = Objects.requireNonNull(courseRepository, "El repositorio de cursos es obligatorio");
    }

    @Override
    public List<Course> findall() {
        return courseRepository.findall();
    }

    @Override
    public Optional<Course> findById(Long id) {
        validateCourseId(id, "El id del curso es obligatorio");
        return courseRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        validateCourse(course);
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        validateCourseId(id, "El id del curso es obligatorio");
        ensureCourseExists(id);
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> update(Course course) {
        validateCourse(course);
        Long courseId = course.getId();
        validateCourseId(courseId, "El id del curso es obligatorio");
        ensureCourseExists(courseId);
        return courseRepository.update(course);
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        validateCourseId(studentId, "El id del curso es obligatorio");
        return courseRepository.existsByStudentId(studentId);
    }

    private void ensureCourseExists(Long courseId) {
        if (!Boolean.TRUE.equals(courseRepository.existsByStudentId(courseId))) {
            throw new CourseNotFoundException("No existe un curso con el id " + courseId);
        }
    }

    private void validateCourse(Course course) {
        if (course == null) {
            throw new BussinesException("El curso es obligatorio");
        }
        validateText(course.getCode(), "El codigo del curso es obligatorio");
        validateText(course.getName(), "El nombre del curso es obligatorio");
        validateText(course.getDescription(), "La descripcion del curso es obligatoria");
        validateMaxCapacity(course.getMaxCapacity());
    }

    private void validateCourseId(Long courseId, String message) {
        if (courseId == null || courseId <= 0) {
            throw new BussinesException(message);
        }
    }

    private void validateText(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new BussinesException(message);
        }
    }

    private void validateMaxCapacity(Integer maxCapacity) {
        if (maxCapacity == null) {
            throw new BussinesException("La capacidad maxima del curso es obligatoria");
        }
        if (maxCapacity <= 0) {
            throw new BussinesException("La capacidad maxima del curso debe ser mayor a cero");
        }
    }
}
