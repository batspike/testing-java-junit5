package guru.springframework;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.Test;

class InLineMockTest {

	@Test
	void testInlineMock() {
		Map mapMock = mock(Map.class); //creating a mock out of a type
		
		assertEquals(0, mapMock.size());
	}

}
