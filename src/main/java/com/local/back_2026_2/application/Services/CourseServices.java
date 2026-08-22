package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.application.Exceptions.BussinesException;
import com.local.back_2026_2.application.Exceptions.CourseNotFoundException;
import com.local.back_2026_2.domain.models.Course;
import com.local.back_2026_2.domain.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseServices implements CourseRepository {

    private final CourseRepository courseRepository;

    public CourseServices(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findall() {
        return courseRepository.findall();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        validateCourse(course);
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new BussinesException("El id del curso es obligatorio");
        }
        if (!Boolean.TRUE.equals(courseRepository.existsByStudentId(id))) {
            throw new CourseNotFoundException("No existe un curso con el id " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> update(Course course) {
        Long courseId = course != null ? course.getId() : null;
        if (!Boolean.TRUE.equals(courseRepository.existsByStudentId(courseId))) {
            throw new CourseNotFoundException("No existe un curso con el id " + courseId);
        }
        return courseRepository.update(course);
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        return courseRepository.existsByStudentId(studentId);
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
