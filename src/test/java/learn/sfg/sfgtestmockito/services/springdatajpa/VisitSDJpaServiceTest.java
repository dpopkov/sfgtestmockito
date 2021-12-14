package learn.sfg.sfgtestmockito.services.springdatajpa;

import learn.sfg.sfgtestmockito.model.Visit;
import learn.sfg.sfgtestmockito.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
    private static final long ID = 1L;

    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find All")
    @Test
    void testFindAll() {
        // Given
        Visit visit = new Visit(ID);
        given(visitRepository.findAll()).willReturn(List.of(visit));
        // When
        final Set<Visit> found = service.findAll();
        // Then
        then(visitRepository).should().findAll();
        assertThat(found).hasSize(1);
        assertThat(found).contains(visit);
    }

    @DisplayName("Test Find by ID")
    @Test
    void findById() {
        // Given
        Visit visit = new Visit(ID);
        given(visitRepository.findById(ID)).willReturn(Optional.of(visit));
        // When
        final Visit found = service.findById(ID);
        // Then
        then(visitRepository).should().findById(anyLong());
        assertThat(found.getId()).isEqualTo(ID);
    }

    @DisplayName("Test Find by ID using BDD Mockito")
    @Test
    void findByIdBddTest() {
        // Given
        Visit visit = new Visit(ID);
        given(visitRepository.findById(ID)).willReturn(Optional.of(visit));

        // When
        final Visit found = service.findById(ID);

        // Then
        then(visitRepository).should().findById(anyLong());
        then(visitRepository).shouldHaveNoMoreInteractions();
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(ID);
    }

    @DisplayName("Test Save")
    @Test
    void save() {
        // Given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);
        // When
        final Visit saved = service.save(visit);
        // Then
        then(visitRepository).should().save(any(Visit.class));
        assertThat(saved).isNotNull();
    }

    @DisplayName("Test Delete Visit")
    @Test
    void delete() {
        // When
        service.delete(new Visit());
        // Then
        then(visitRepository).should().delete(any(Visit.class));
    }

    @DisplayName("Test Delete by ID")
    @Test
    void deleteById() {
        // When
        service.deleteById(ID);
        // Then
        then(visitRepository).should().deleteById(anyLong());
    }
}
