# Testing Java with Mockito

* Add Maven dependencies
    * mockito-core
    * mockito-junit-jupiter
* Create Mockito Mocks
    * Inline: `Map mapMock = Mockito.mock(Map.class)`
    * Using Annotations:
        * `@Mock Map<String, Object> mapMock;`
        * Before Each: `MockitoAnnotations.openMocks(this);`
    * Using Mockito Extension: `@ExtendWith(MockitoExtension.class)`
* Inject Mocks with Mockito: `@InjectMocks`, example: [SpecialitySDJpaServiceTest](src/test/java/learn/sfg/sfgtestmockito/services/springdatajpa/SpecialitySDJpaServiceTest.java)
* Verify interactions with Mockito Mocks: `Mockito.verify(mock)`
* Return values from Mocks: `when(repository.findById(ID)).thenReturn(Optional.of(speciality))`
* Argument matchers: `verify(specialtyRepository).delete(any(Speciality.class))`
* BDD Mockito
    * given(methodCall).willReturn(value)
    * then(mock).should().methodCall(arg)
* Throwing Exceptions with Mockito
    * Mockito.__doThrow__(Throwable).when(mock).methodCall()
    * BDDMockito.given(methodCall).__willThrow__(Throwable)         -- if methodCall returns something
    * BDDMockito.__willThrow__(Throwable).given(mock).methodCall()  -- if methodCall returns void
* Java Lambda Argument Matchers
    * given(repository.save(__argThat__(arg -> arg.getValue().equals(VALUE)))).willReturn(savedObject);
* Mockito Argument Capture: org.mockito.ArgumentCaptor
* Mockito Answers
    * given(methodCall).willAnswer(invocation -> {...})
* Verify Order of Interactions
    * InOrder inOrder = Mockito.inOrder(mock1, mock2);  -- allows verifying in order
    * inOrder.verify(mock1).methodCall();
    * inOrder.verify(mock2).methodCall();
* Verify Interactions within a specified time
    * then(mock).should(timeout(100)).methodCall();
* Verify Zero or No More interactions with mock
    * verifyNoInteractions(mock)
    * verifyNoMoreInteractions(mock)
* Using Mockito Spy
    * @org.mockito.Spy
    * given(spy.methodCall()).willCallRealMethod()
    * given(spy.methodCall()).willReturn(object)
