package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.domain.repository.CourseRepository;
import com.local.back_2026_2.domain.repository.impl.CourseRepositoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CourseServices extends CourseRepositoryService {

    public CourseServices(@Qualifier("courseRepositoryAdapter") CourseRepository courseRepository) {
        super(courseRepository);
    }
}
