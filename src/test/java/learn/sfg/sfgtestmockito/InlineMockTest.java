package learn.sfg.sfgtestmockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InlineMockTest {

    @Test
    @SuppressWarnings("rawtypes")
    void testInlineMock() {
        Map mapMock = Mockito.mock(Map.class);
        assertEquals(mapMock.size(), 0);
    }
}
