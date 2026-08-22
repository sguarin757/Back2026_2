package com.local.back_2026_2.infrastructure.persistence;

import com.local.back_2026_2.domain.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {
}
