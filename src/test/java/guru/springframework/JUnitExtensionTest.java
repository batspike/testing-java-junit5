package guru.springframework;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) //JUnit5 extension to mockito (include initialization)
class JUnitExtensionTest {

	@Mock
	Map<String,Object> mapMock; //create a mock using annotation
	
	@Test
	void testMock() {
		mapMock.put("keyvalue", "foo");
	}

}
