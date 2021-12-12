package learn.sfg.sfgtestmockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class AnnotationMockTest {

    @Mock
    Map<String, Object> mapMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMock() {
        mapMock.put("key", "foo");
    }
}
