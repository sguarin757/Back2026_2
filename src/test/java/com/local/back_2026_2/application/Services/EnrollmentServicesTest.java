package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.application.Exceptions.BussinesException;
import com.local.back_2026_2.application.Exceptions.EnrollmentNotFoundException;
import com.local.back_2026_2.domain.models.Course;
import com.local.back_2026_2.domain.models.Enrollment;
import com.local.back_2026_2.domain.models.EnrollmentStatus;
import com.local.back_2026_2.domain.models.Student;
import com.local.back_2026_2.domain.repository.EnrollmentRepository;
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
class EnrollmentServicesTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    private EnrollmentServices enrollmentServices;

    @BeforeEach
    void setUp() {
        enrollmentServices = new EnrollmentServices(enrollmentRepository);
    }

    @Test
    void saveShouldPersistWhenEnrollmentIsValid() {
        Enrollment enrollment = buildEnrollment();
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);

        Enrollment savedEnrollment = enrollmentServices.save(enrollment);

        assertEquals(enrollment, savedEnrollment);
        verify(enrollmentRepository).save(enrollment);
    }

    @Test
    void saveShouldFailWhenStudentIsMissing() {
        Enrollment enrollment = buildEnrollment();
        enrollment.setStudent(null);

        assertThrows(BussinesException.class, () -> enrollmentServices.save(enrollment));
        verify(enrollmentRepository, never()).save(enrollment);
    }

    @Test
    void saveShouldFailWhenEnrollmentDateIsFuture() {
        Enrollment enrollment = buildEnrollment();
        enrollment.setEnrollmentDate(LocalDate.now().plusDays(1));

        assertThrows(BussinesException.class, () -> enrollmentServices.save(enrollment));
        verify(enrollmentRepository, never()).save(enrollment);
    }

    @Test
    void saveShouldFailWhenStatusIsMissing() {
        Enrollment enrollment = buildEnrollment();
        enrollment.setStatus(null);

        assertThrows(BussinesException.class, () -> enrollmentServices.save(enrollment));
        verify(enrollmentRepository, never()).save(enrollment);
    }

    @Test
    void deleteByIdShouldFailWhenEnrollmentDoesNotExist() {
        when(enrollmentRepository.existsByStudentId(1L)).thenReturn(false);

        assertThrows(EnrollmentNotFoundException.class, () -> enrollmentServices.deleteById(1L));
        verify(enrollmentRepository, never()).deleteById(1L);
    }

    @Test
    void updateShouldPersistWhenEnrollmentExists() {
        Enrollment enrollment = buildEnrollment();
        when(enrollmentRepository.existsByStudentId(enrollment.getId())).thenReturn(true);
        when(enrollmentRepository.update(enrollment)).thenReturn(Optional.of(enrollment));

        Optional<Enrollment> updatedEnrollment = enrollmentServices.update(enrollment);

        assertEquals(Optional.of(enrollment), updatedEnrollment);
        verify(enrollmentRepository).update(enrollment);
    }

    @Test
    void updateShouldFailWhenEnrollmentDoesNotExist() {
        Enrollment enrollment = buildEnrollment();
        when(enrollmentRepository.existsByStudentId(enrollment.getId())).thenReturn(false);

        assertThrows(EnrollmentNotFoundException.class, () -> enrollmentServices.update(enrollment));
        verify(enrollmentRepository, never()).update(enrollment);
    }

    @Test
    void deleteByIdShouldFailWhenIdIsInvalid() {
        assertThrows(BussinesException.class, () -> enrollmentServices.deleteById(0L));
        verify(enrollmentRepository, never()).existsByStudentId(0L);
        verify(enrollmentRepository, never()).deleteById(0L);
    }

    @Test
    void findByIdShouldFailWhenIdIsInvalid() {
        assertThrows(BussinesException.class, () -> enrollmentServices.findById(null));
        verify(enrollmentRepository, never()).findById(null);
    }

    @Test
    void updateShouldFailWhenEnrollmentIsInvalid() {
        Enrollment enrollment = buildEnrollment();
        enrollment.setCourse(null);

        assertThrows(BussinesException.class, () -> enrollmentServices.update(enrollment));
        verify(enrollmentRepository, never()).existsByStudentId(enrollment.getId());
        verify(enrollmentRepository, never()).update(enrollment);
    }

    private Enrollment buildEnrollment() {
        return new Enrollment(
                1L,
                new Student(10L, "Ana", "Lopez", "ana@example.com", LocalDate.of(2004, 3, 10)),
                new Course(20L, "CS101", "Programacion I", "Curso introductorio", 30),
                LocalDate.of(2026, 1, 15),
                EnrollmentStatus.ACTIVE
        );
    }
}
