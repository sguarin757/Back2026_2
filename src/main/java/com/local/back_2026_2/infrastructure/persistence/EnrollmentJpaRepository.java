package com.local.back_2026_2.infrastructure.persistence;

import com.local.back_2026_2.domain.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentJpaRepository extends JpaRepository<Enrollment, Long> {
}
