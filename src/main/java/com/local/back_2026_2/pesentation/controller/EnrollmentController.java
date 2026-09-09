package com.local.back_2026_2.pesentation.controller;

import com.local.back_2026_2.application.Exceptions.BussinesException;
import com.local.back_2026_2.application.Exceptions.EnrollmentNotFoundException;
import com.local.back_2026_2.application.Services.EnrollmentServices;
import com.local.back_2026_2.domain.models.Enrollment;
import com.local.back_2026_2.pesentation.dto.ErrorResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentServices enrollmentServices;

    public EnrollmentController(EnrollmentServices enrollmentServices) {
        this.enrollmentServices = enrollmentServices;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            return ResponseEntity.ok(enrollmentServices.findall());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            Optional<Enrollment> enrollment = enrollmentServices.findById(id);
            if (enrollment.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(enrollment.get());
        } catch (BussinesException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Enrollment enrollment) {
        try {
            Enrollment savedEnrollment = enrollmentServices.save(enrollment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEnrollment);
        } catch (BussinesException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody Enrollment enrollment) {
        try {
            enrollment.setId(id);
            Enrollment updatedEnrollment = enrollmentServices.update(enrollment)
                    .orElseThrow(() -> new IllegalStateException("No se pudo actualizar la matricula con id " + id));
            return ResponseEntity.ok(updatedEnrollment);
        } catch (EnrollmentNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (BussinesException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            enrollmentServices.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EnrollmentNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (BussinesException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, null);
        return ResponseEntity.status(status).body(body);
    }
}
