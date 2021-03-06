package learn.sfg.sfgtestmockito.services.springdatajpa;

import learn.sfg.sfgtestmockito.model.Speciality;
import learn.sfg.sfgtestmockito.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {
    @Mock
    SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialitySDJpaService service;

    private static final long ID = 1L;
    private static final int TIMEOUT_MILLIS = 10;

    @Test
    void testFindById() {
        // Given
        Speciality speciality = new Speciality(ID, "test");
        given(specialtyRepository.findById(ID)).willReturn(Optional.of(speciality));
        // When
        Speciality found = service.findById(ID);
        // Then
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(ID);
        assertThat(found.getDescription()).isEqualTo("test");
        then(specialtyRepository).should(timeout(TIMEOUT_MILLIS)).findById(ID);
    }

    @Test
    void testDelete() {
        // Given
        Speciality speciality = new Speciality();
        // When
        service.delete(speciality);
        // Then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void testDeleteById() {
        // When
        service.deleteById(ID);
        service.deleteById(ID);
        // Then
        then(specialtyRepository).should(timeout(TIMEOUT_MILLIS).times(2)).deleteById(ID);
    }

    @Test
    void testDeleteByIdAtLeast() {
        // When
        service.deleteById(ID);
        service.deleteById(ID);
        // Then
        then(specialtyRepository).should(timeout(TIMEOUT_MILLIS).atLeastOnce()).deleteById(ID);
    }

    @Test
    void testDeleteByIdAtMost() {
        // When
        service.deleteById(ID);
        service.deleteById(ID);
        // Then
        then(specialtyRepository).should(atMost(5)).deleteById(ID);
    }

    @Test
    void testDeleteByIdNever() {
        // When
        service.deleteById(ID);
        service.deleteById(ID);
        // Then
        then(specialtyRepository).should(timeout(TIMEOUT_MILLIS).atLeastOnce()).deleteById(ID);
        then(specialtyRepository).should(never()).deleteById(5L);
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("Boom")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));

        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void testFindByIdThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("Boom"));

        assertThrows(RuntimeException.class, () -> service.findById(1L));

        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBdd() {
        willThrow(new RuntimeException("Boom")).given(specialtyRepository).delete(any(Speciality.class));

        assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));

        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void testSaveLambda() {
        // Given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality(MATCH_ME);

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);
        // need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(arg -> arg.getDescription().equals(MATCH_ME))))
                .willReturn(savedSpeciality);

        // When
        Speciality returnedSpeciality = service.save(speciality);

        // Then
        assertThat(returnedSpeciality.getId()).isEqualTo(1L);
    }
}
