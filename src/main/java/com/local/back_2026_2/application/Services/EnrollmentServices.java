package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.domain.models.Enrollment;
import com.local.back_2026_2.domain.repository.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

public class EnrollmentServices implements EnrollmentRepository {
    @Override
    public List<Enrollment> findall() {
        return List.of();
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Enrollment save(Enrollment student) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Enrollment> update(Enrollment student) {
        return Optional.empty();
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        return null;
    }
}
