package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.application.Exceptions.BussinesException;
import com.local.back_2026_2.application.Exceptions.EnrollmentNotFoundException;
import com.local.back_2026_2.domain.models.Course;
import com.local.back_2026_2.domain.models.Enrollment;
import com.local.back_2026_2.domain.models.EnrollmentStatus;
import com.local.back_2026_2.domain.models.Student;
import com.local.back_2026_2.domain.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServices implements EnrollmentRepository {

private final EnrollmentRepository enrollmentRepository;

public EnrollmentServices(@Qualifier("enrollmentRepositoryAdapter") EnrollmentRepository enrollmentRepository) {
    this.enrollmentRepository = enrollmentRepository;
}

    @Override
    public List<Enrollment> findall() {
        return enrollmentRepository.findall();
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        validateEnrollment(enrollment);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new BussinesException("El id de la matricula es obligatorio");
        }
        if (!Boolean.TRUE.equals(enrollmentRepository.existsByStudentId(id))) {
            throw new EnrollmentNotFoundException("No existe una matricula con el id " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    @Override
    public Optional<Enrollment> update(Enrollment enrollment) {
        Long enrollmentId = enrollment != null ? enrollment.getId() : null;
        if (!Boolean.TRUE.equals(enrollmentRepository.existsByStudentId(enrollmentId))) {
            throw new EnrollmentNotFoundException("No existe una matricula con el id " + enrollmentId);
        }
        return enrollmentRepository.update(enrollment);
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        return enrollmentRepository.existsByStudentId(studentId);
    }

    private void validateEnrollment(Enrollment enrollment) {
        if (enrollment == null) {
            throw new BussinesException("La matricula es obligatoria");
        }
        validateStudent(enrollment.getStudent());
        validateCourse(enrollment.getCourse());
        validateEnrollmentDate(enrollment.getEnrollmentDate());
        validateStatus(enrollment.getStatus());
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new BussinesException("El estudiante es obligatorio");
        }
        validateId(student.getId(), "El id del estudiante es obligatorio");
    }

    private void validateCourse(Course course) {
        if (course == null) {
            throw new BussinesException("El curso es obligatorio");
        }
        validateId(course.getId(), "El id del curso es obligatorio");
    }

    private void validateId(Long value, String message) {
        if (value == null || value <= 0) {
            throw new BussinesException(message);
        }
    }

    private void validateEnrollmentDate(LocalDate enrollmentDate) {
        if (enrollmentDate == null) {
            throw new BussinesException("La fecha de matricula es obligatoria");
        }
        if (enrollmentDate.isAfter(LocalDate.now())) {
            throw new BussinesException("La fecha de matricula no puede ser futura");
        }
    }

    private void validateStatus(EnrollmentStatus status) {
        if (status == null) {
            throw new BussinesException("El estado de la matricula es obligatorio");
        }
    }
}
