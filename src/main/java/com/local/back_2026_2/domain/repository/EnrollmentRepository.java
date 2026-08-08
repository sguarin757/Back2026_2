package com.local.back_2026_2.domain.repository;

import com.local.back_2026_2.domain.models.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    List<Enrollment> findall();
    Optional<Enrollment> findById(Long id);
    Enrollment save(Enrollment student);
    void deleteById(Long id);
    Optional<Enrollment> update (Enrollment student);
    Boolean existsByStudentId(Long studentId);
}
