package com.local.back_2026_2.infrastructure.persistence;

import com.local.back_2026_2.domain.models.Enrollment;
import com.local.back_2026_2.domain.repository.EnrollmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class EnrollmentRepositoryAdapter implements EnrollmentRepository {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public EnrollmentRepositoryAdapter(EnrollmentJpaRepository enrollmentJpaRepository) {
        this.enrollmentJpaRepository = enrollmentJpaRepository;
    }

    @Override
    public List<Enrollment> findall() {
        return enrollmentJpaRepository.findAll();
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return enrollmentJpaRepository.findById(id);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentJpaRepository.save(enrollment);
    }

    @Override
    public void deleteById(Long id) {
        enrollmentJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Enrollment> update(Enrollment enrollment) {
        if (!enrollmentJpaRepository.existsById(enrollment.getId())) {
            return Optional.empty();
        }
        return Optional.of(enrollmentJpaRepository.save(enrollment));
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        return enrollmentJpaRepository.existsById(studentId);
    }
}
