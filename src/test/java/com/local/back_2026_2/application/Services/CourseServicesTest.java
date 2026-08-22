package com.local.back_2026_2.application.Services;

import com.local.back_2026_2.application.Exceptions.BussinesException;
import com.local.back_2026_2.application.Exceptions.CourseNotFoundException;
import com.local.back_2026_2.domain.models.Course;
import com.local.back_2026_2.domain.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServicesTest {

    @Mock
    private CourseRepository courseRepository;

    private CourseServices courseServices;

    @BeforeEach
    void setUp() {
        courseServices = new CourseServices(courseRepository);
    }

    @Test
    void saveShouldPersistWhenCourseIsValid() {
        Course course = buildCourse();
        when(courseRepository.save(course)).thenReturn(course);

        Course savedCourse = courseServices.save(course);

        assertEquals(course, savedCourse);
        verify(courseRepository).save(course);
    }

    @Test
    void saveShouldFailWhenCodeIsBlank() {
        Course course = buildCourse();
        course.setCode(" ");

        assertThrows(BussinesException.class, () -> courseServices.save(course));
        verify(courseRepository, never()).save(course);
    }

    @Test
    void saveShouldFailWhenDescriptionIsBlank() {
        Course course = buildCourse();
        course.setDescription(" ");

        assertThrows(BussinesException.class, () -> courseServices.save(course));
        verify(courseRepository, never()).save(course);
    }

    @Test
    void saveShouldFailWhenMaxCapacityIsInvalid() {
        Course course = buildCourse();
        course.setMaxCapacity(0);

        assertThrows(BussinesException.class, () -> courseServices.save(course));
        verify(courseRepository, never()).save(course);
    }

    @Test
    void deleteByIdShouldFailWhenCourseDoesNotExist() {
        when(courseRepository.existsByStudentId(1L)).thenReturn(false);

        assertThrows(CourseNotFoundException.class, () -> courseServices.deleteById(1L));
        verify(courseRepository, never()).deleteById(1L);
    }

    @Test
    void updateShouldPersistWhenCourseExists() {
        Course course = buildCourse();
        when(courseRepository.existsByStudentId(course.getId())).thenReturn(true);
        when(courseRepository.update(course)).thenReturn(Optional.of(course));

        Optional<Course> updatedCourse = courseServices.update(course);

        assertEquals(Optional.of(course), updatedCourse);
        verify(courseRepository).update(course);
    }

    @Test
    void updateShouldFailWhenCourseDoesNotExist() {
        Course course = buildCourse();
        when(courseRepository.existsByStudentId(course.getId())).thenReturn(false);

        assertThrows(CourseNotFoundException.class, () -> courseServices.update(course));
        verify(courseRepository, never()).update(course);
    }

    private Course buildCourse() {
        return new Course(
                1L,
                "CS101",
                "Programacion I",
                "Curso introductorio de programacion",
                30
        );
    }
}
