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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Visit visit = new Visit(ID);
        when(visitRepository.findAll()).thenReturn(List.of(visit));

        final Set<Visit> found = service.findAll();

        assertThat(found).hasSize(1);
        assertThat(found).contains(visit);
        verify(visitRepository).findAll();
    }

    @DisplayName("Test Find by ID")
    @Test
    void findById() {
        Visit visit = new Visit(ID);
        when(visitRepository.findById(ID)).thenReturn(Optional.of(visit));

        final Visit found = service.findById(ID);

        assertThat(found.getId()).isEqualTo(ID);
        verify(visitRepository).findById(anyLong());
    }

    @DisplayName("Test Save")
    @Test
    void save() {
        Visit visit = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        final Visit saved = service.save(visit);

        assertThat(saved).isNotNull();
        verify(visitRepository).save(any(Visit.class));
    }

    @DisplayName("Test Delete Visit")
    @Test
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
    }

    @DisplayName("Test Delete by ID")
    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(visitRepository).deleteById(anyLong());
    }
}
