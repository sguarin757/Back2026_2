package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.application.Exceptions.BussinesException;
import com.local.back_2026_2.application.Exceptions.StudentNotFoundException;
import com.local.back_2026_2.domain.models.Student;
import com.local.back_2026_2.domain.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServicesTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentServices studentServices;

    @BeforeEach
    void setUp() {
        studentServices = new StudentServices(studentRepository);
    }

    @Test
    void saveShouldPersistWhenStudentIsValid() {
        Student student = buildStudent();
        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentServices.save(student);

        assertEquals(student, savedStudent);
        verify(studentRepository).save(student);
    }

    @Test
    void saveShouldFailWhenFirstNameIsBlank() {
        Student student = buildStudent();
        student.setFirstNmae(" ");

        assertThrows(BussinesException.class, () -> studentServices.save(student));
        verify(studentRepository, never()).save(student);
    }

    @Test
    void saveShouldFailWhenEmailIsInvalid() {
        Student student = buildStudent();
        student.setEmail("correo-invalido");

        assertThrows(BussinesException.class, () -> studentServices.save(student));
        verify(studentRepository, never()).save(student);
    }

    @Test
    void saveShouldFailWhenBirthDateIsFuture() {
        Student student = buildStudent();
        student.setBirthDate(LocalDate.now().plusDays(1));

        assertThrows(BussinesException.class, () -> studentServices.save(student));
        verify(studentRepository, never()).save(student);
    }

    @Test
    void deleteByIdShouldFailWhenStudentDoesNotExist() {
        when(studentRepository.existsByStudentId(1L)).thenReturn(false);

        assertThrows(StudentNotFoundException.class, () -> studentServices.deleteById(1L));
        verify(studentRepository, never()).deleteById(1L);
    }

    @Test
    void updateShouldPersistWhenStudentExists() {
        Student student = buildStudent();
        when(studentRepository.existsByStudentId(student.getId())).thenReturn(true);
        when(studentRepository.update(student)).thenReturn(Optional.of(student));

        Optional<Student> updatedStudent = studentServices.update(student);

        assertEquals(Optional.of(student), updatedStudent);
        verify(studentRepository).update(student);
    }

    @Test
    void updateShouldFailWhenStudentDoesNotExist() {
        Student student = buildStudent();
        when(studentRepository.existsByStudentId(student.getId())).thenReturn(false);

        assertThrows(StudentNotFoundException.class, () -> studentServices.update(student));
        verify(studentRepository, never()).update(student);
    }

    @Test
    void deleteByIdShouldFailWhenIdIsInvalid() {
        assertThrows(BussinesException.class, () -> studentServices.deleteById(0L));
        verify(studentRepository, never()).existsByStudentId(0L);
        verify(studentRepository, never()).deleteById(0L);
    }

    @Test
    void updateShouldFailWhenStudentIdIsMissing() {
        Student student = buildStudent();
        student.setId(null);

        assertThrows(BussinesException.class, () -> studentServices.update(student));
        verify(studentRepository, never()).existsByStudentId(null);
        verify(studentRepository, never()).update(student);
    }

    private Student buildStudent() {
        return new Student(
                1L,
                "Ana",
                "Perez",
                "ana.perez@mail.com",
                LocalDate.of(2000, 5, 10)
        );
    }
}
