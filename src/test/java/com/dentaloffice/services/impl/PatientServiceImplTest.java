package com.dentaloffice.services.impl;

import com.dentaloffice.models.Patient;
import com.dentaloffice.repositories.PatientRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {
    @Mock
    private PatientRepository patientRepository;

    private PatientServiceImpl patientService;

    @BeforeEach
    public void setup(){
        patientService = new PatientServiceImpl(patientRepository);
    }

    @Test
    void shouldSavePatient() {
//        given data
        Patient patientFromUserInput = new Patient(
                null, "Dragana",
                "Spasojevic",
                "04.11.1989.",
                "017673831708",
                null);
//data after saving patient
        UUID id = UUID.fromString("eefcbdd4-3cc3-4b93-822c-6226305677cd");
        Patient createdPatient = new Patient(
                id, "Dragana",
                "Spasojevic",
                "04.11.1989.",
                "017673831708",
                null);

//        repository is called with data from user input and repository returned created patient with id
        when(patientRepository.save(any(Patient.class))).thenReturn(createdPatient);

//         service is called with data from user input
        Patient result = patientService.save(patientFromUserInput);

//        check is the repository called with value from user input
        verify(patientRepository).save(patientFromUserInput);

//        compare returned result is equal to created patient
        assertThat(result).isEqualTo(createdPatient);
    }

    @Test
    @Disabled
    void schouldFindAll() {

        patientService.findAll("Dragana", 1,10, "Dragana" );

//        verify(patientRepository).findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase();

    }

    @Test
    void shouldDelete() {

        UUID id = UUID.fromString("eefcbdd4-3cc3-4b93-822c-6226305677cd");
        
        patientService.delete(id);
        verify(patientRepository).deleteById(id);
    }

    @Nested
    class Exists {
        @Test
        void shouldExists() {

            UUID id = UUID.fromString("eefcbdd4-3cc3-4b93-822c-6226305677cd");
            Patient createdPatient = new Patient(
                    id, "Dragana",
                    "Spasojevic",
                    "04.11.1989.",
                    "017673831708",
                    null);

            when(patientRepository.existsById(id)).thenReturn(true);

            boolean result = patientService.exists(createdPatient.getId());

            verify(patientRepository).existsById(createdPatient.getId());

            assertThat(result).isTrue();
        }

        @Test
        void shouldNotExist() {

            UUID id = UUID.fromString("eefcbdd4-3cc3-4b93-822c-6226305677cd");

            when(patientRepository.existsById(id)).thenReturn(false);

            boolean result = patientService.exists(id);

            assertThat(result).isFalse();
        }
    }
}