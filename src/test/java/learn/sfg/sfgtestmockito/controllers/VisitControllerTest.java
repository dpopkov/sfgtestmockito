package learn.sfg.sfgtestmockito.controllers;

import learn.sfg.sfgtestmockito.model.Pet;
import learn.sfg.sfgtestmockito.model.Visit;
import learn.sfg.sfgtestmockito.services.VisitService;
import learn.sfg.sfgtestmockito.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;
    @Spy //@Mock
    PetMapService petService;
    @InjectMocks
    VisitController visitController;

    @Test
    void testLoadPetWithVisit() {
        // Given
        Map<String, Object> model = new HashMap<>();
        final Long petId = 12L;
        Pet pet = new Pet(petId);
        Pet pet3 = new Pet(3L);
        petService.save(pet);
        petService.save(pet3);
        given(petService.findById(anyLong())).willCallRealMethod(); //.willReturn(pet);

        // When
        final Visit visit = visitController.loadPetWithVisit(petId, model);

        // Then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(petId);
        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void testLoadPetWithVisitWithStubbing() {
        // Given
        Map<String, Object> model = new HashMap<>();
        final Long petId = 12L;
        Pet pet = new Pet(petId);
        Pet pet3 = new Pet(3L);
        petService.save(pet);
        petService.save(pet3);
        given(petService.findById(anyLong())).willReturn(pet3);

        // When
        final Visit visit = visitController.loadPetWithVisit(petId, model);

        // Then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(3L);
        verify(petService, times(1)).findById(anyLong());
    }
}
