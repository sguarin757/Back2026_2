package com.local.back_2026_2.domain.repository.impl;

import com.local.back_2026_2.application.Exceptions.BussinesException;
import com.local.back_2026_2.application.Exceptions.StudentNotFoundException;
import com.local.back_2026_2.domain.models.Student;
import com.local.back_2026_2.domain.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class StudentRepositoryService implements StudentRepository {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final StudentRepository studentRepository;

    protected StudentRepositoryService(StudentRepository studentRepository) {
        this.studentRepository = Objects.requireNonNull(studentRepository, "El repositorio de estudiantes es obligatorio");
    }

    @Override
    public List<Student> findall() {
        return studentRepository.findall();
    }

    @Override
    public Optional<Student> findById(Long id) {
        validateStudentId(id, "El id del estudiante es obligatorio");
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        validateStudent(student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        validateStudentId(id, "El id del estudiante es obligatorio");
        ensureStudentExists(id);
        studentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> update(Student student) {
        validateStudent(student);
        Long studentId = student.getId();
        validateStudentId(studentId, "El id del estudiante es obligatorio");
        ensureStudentExists(studentId);
        return studentRepository.update(student);
    }

    @Override
    public Boolean existsByStudentId(Long studentId) {
        validateStudentId(studentId, "El id del estudiante es obligatorio");
        return studentRepository.existsByStudentId(studentId);
    }

    private void ensureStudentExists(Long studentId) {
        if (!Boolean.TRUE.equals(studentRepository.existsByStudentId(studentId))) {
            throw new StudentNotFoundException("No existe un estudiante con el id " + studentId);
        }
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new BussinesException("El estudiante es obligatorio");
        }
        validateText(student.getFirstNmae(), "El nombre del estudiante es obligatorio");
        validateText(student.getLasName(), "El apellido del estudiante es obligatorio");
        validateEmail(student.getEmail());
        validateBirthDate(student.getBirthDate());
    }

    private void validateStudentId(Long studentId, String message) {
        if (studentId == null || studentId <= 0) {
            throw new BussinesException(message);
        }
    }

    private void validateText(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new BussinesException(message);
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new BussinesException("El correo del estudiante es obligatorio");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new BussinesException("El correo del estudiante no es valido");
        }
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new BussinesException("La fecha de nacimiento del estudiante es obligatoria");
        }
        if (birthDate.isAfter(LocalDate.now())) {
            throw new BussinesException("La fecha de nacimiento del estudiante no puede ser futura");
        }
    }

}
