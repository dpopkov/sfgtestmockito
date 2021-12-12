package learn.sfg.sfgtestmockito.services.springdatajpa;

import learn.sfg.sfgtestmockito.model.Vet;
import learn.sfg.sfgtestmockito.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {
    @Mock
    VetRepository vetRepository;
    @InjectMocks
    VetSDJpaService service;

    @Test
    void testSave() {
        final Vet vet = new Vet(1L, "fn", "ln");
        service.save(vet);
        verify(vetRepository).save(vet);
    }

    @Test
    void testDelete() {
        final Vet vet = new Vet(1L, "fn", "ln");
        service.delete(vet);
        verify(vetRepository).delete(vet);
    }

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        verify(vetRepository, times(1)).deleteById(1L);
    }
}
