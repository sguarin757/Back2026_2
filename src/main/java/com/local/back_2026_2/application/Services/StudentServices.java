package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.domain.repository.StudentRepository;
import com.local.back_2026_2.domain.repository.impl.StudentRepositoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentServices extends StudentRepositoryService {

    public StudentServices(@Qualifier("studentRepositoryAdapter") StudentRepository studentRepository) {
        super(studentRepository);
    }
}
