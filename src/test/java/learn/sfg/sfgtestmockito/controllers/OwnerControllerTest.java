package learn.sfg.sfgtestmockito.controllers;

import learn.sfg.sfgtestmockito.fauxspring.BindingResult;
import learn.sfg.sfgtestmockito.model.Owner;
import learn.sfg.sfgtestmockito.services.OwnerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static learn.sfg.sfgtestmockito.controllers.OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("Owner Controller - ")
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController controller;
    @Mock
    BindingResult bindingResult;

    @DisplayName("Process Creation Form when binding result has errors")
    @Test
    void testProcessCreationFormWithErrors() {
        // Given
        given(bindingResult.hasErrors()).willReturn(true);
        Owner owner = new Owner(1L, "fn", "ln");
        // When
        String viewName = controller.processCreationForm(owner, bindingResult);
        // Then
        assertThat(viewName).isEqualTo(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
    }

    @DisplayName("Process Creation Form when binding result has no errors")
    @Test
    void testProcessCreationFormOk() {
        // Given
        given(bindingResult.hasErrors()).willReturn(false);
        final long id = 5L;
        Owner owner = new Owner(id, "fn", "ln");
        given(ownerService.save(any(Owner.class))).willReturn(new Owner(id, "fn", "ln"));
        // When
        String viewName = controller.processCreationForm(owner, bindingResult);
        // Then
        then(ownerService).should().save(any(Owner.class));
        assertThat(viewName).isEqualTo("redirect:/owners/" + id);
    }
}
