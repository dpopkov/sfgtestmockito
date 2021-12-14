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
    * Mockito.doThrow(Throwable).when(mock).methodCall()
    * BDDMockito.given(methodCall).willThrow(Throwable)         -- if methodCall returns something
    * BDDMockito.willThrow(Throwable).given(mock).methodCall()  -- if methodCall returns void
