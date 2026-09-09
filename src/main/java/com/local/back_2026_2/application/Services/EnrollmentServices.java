package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.domain.repository.EnrollmentRepository;
import com.local.back_2026_2.domain.repository.impl.EnrollmentRepositoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServices extends EnrollmentRepositoryService {

    public EnrollmentServices(@Qualifier("enrollmentRepositoryAdapter") EnrollmentRepository enrollmentRepository) {
        super(enrollmentRepository);
    }
}
