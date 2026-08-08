package com.local.back_2026_2.domain.repository;
import com.local.back_2026_2.domain.models.Student;

import java.util.List;
import java.util.Optional;


public interface StudentRepository {

    List<Student> findall();
    Optional<Student> findById(Long id);
    Student save(Student student);
    void deleteById(Long id);
    Optional<Student> update (Student student);
    Boolean existsByStudentId(Long studentId);

}
