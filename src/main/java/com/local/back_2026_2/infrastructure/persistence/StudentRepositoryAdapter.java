package com.local.back_2026_2.infrastructure.persistence;

import com.local.back_2026_2.domain.models.Student;
import com.local.back_2026_2.domain.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class StudentRepositoryAdapter implements StudentRepository {

    private final StudentJpaRepository studentJpaRepository;

    public StudentRepositoryAdapter(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public List<Student> findall() {
        return studentJpaRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentJpaRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentJpaRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Student> update(Student student) {
        if (!studentJpaRepository.existsById(student.getId())) {
            return Optional.empty();
        }
        return Optional.of(studentJpaRepository.save(student));
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        return studentJpaRepository.existsById(studentId);
    }
}
