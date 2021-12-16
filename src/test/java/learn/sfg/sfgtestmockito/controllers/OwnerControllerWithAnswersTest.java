package learn.sfg.sfgtestmockito.controllers;

import learn.sfg.sfgtestmockito.fauxspring.BindingResult;
import learn.sfg.sfgtestmockito.fauxspring.Model;
import learn.sfg.sfgtestmockito.model.Owner;
import learn.sfg.sfgtestmockito.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("Owner Controller with Answers - ")
@ExtendWith(MockitoExtension.class)
class OwnerControllerWithAnswersTest {

    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController controller;
    @Mock
    BindingResult bindingResult;
    @Mock
    Model model;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    String name = invocation.getArgument(0);
                    switch (name) {
                        case "%Doe%":
                            return List.of(new Owner(1L, "Jane", "Doe"));
                        case "%FindMe%":
                            return List.of(new Owner(1L, "Jane", "Doe"),
                                    new Owner(2L, "Jane", "Smith"));
                        case "%DontFindMe%":
                            return List.of();   // nothing found
                    }
                    throw new RuntimeException("Invalid Argument");
                });
    }

    @Test
    void testProcessFindFormWhenOneOwnerFound() {
        // Given
        final String lastName = "Doe";
        Owner owner = new Owner(1L, "Jane", lastName);
        // When
        final String viewName = controller.processFindForm(owner, bindingResult, null);
        // Then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%" + lastName + "%");
        assertThat(viewName).isEqualTo("redirect:/owners/1");
    }

    @Test
    void testProcessFindFormWhenNotFound() {
        // Given
        final String lastName = "DontFindMe";
        Owner owner = new Owner(1L, "Jane", lastName);
        // When
        final String viewName = controller.processFindForm(owner, bindingResult, null);
        // Then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%" + lastName + "%");
        assertThat(viewName).isEqualTo("owners/findOwners");
    }

    @Test
    void testProcessFindFormWhenFound() {
        // Given
        final String lastName = "FindMe";
        Owner owner = new Owner(1L, "Jane", lastName);
        InOrder inOrder = inOrder(ownerService, model);
        // When
        final String viewName = controller.processFindForm(owner, bindingResult, model);
        // Then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%" + lastName + "%");
        assertThat(viewName).isEqualTo("owners/ownersList");
        // InOrder asserts
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());
    }
}
