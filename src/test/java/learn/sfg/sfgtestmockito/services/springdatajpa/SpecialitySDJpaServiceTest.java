package learn.sfg.sfgtestmockito.services.springdatajpa;

import learn.sfg.sfgtestmockito.model.Speciality;
import learn.sfg.sfgtestmockito.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {
    @Mock
    SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialitySDJpaService service;

    private static final long ID = 1L;

    @Test
    void testDelete() {
        Speciality speciality = new Speciality();
        service.delete(speciality);
        verify(specialtyRepository).delete(speciality);
    }

    @Test
    void testDeleteById() {
        service.deleteById(ID);
        service.deleteById(ID);
        verify(specialtyRepository, times(2)).deleteById(ID);
    }

    @Test
    void testDeleteByIdAtLeast() {
        service.deleteById(ID);
        service.deleteById(ID);
        verify(specialtyRepository, atLeastOnce()).deleteById(ID);
    }

    @Test
    void testDeleteByIdAtMost() {
        service.deleteById(ID);
        service.deleteById(ID);
        verify(specialtyRepository, atMost(5)).deleteById(ID);
    }

    @Test
    void testDeleteByIdNever() {
        service.deleteById(ID);
        service.deleteById(ID);
        verify(specialtyRepository, atLeastOnce()).deleteById(ID);
        verify(specialtyRepository, never()).deleteById(5L);
    }
}
