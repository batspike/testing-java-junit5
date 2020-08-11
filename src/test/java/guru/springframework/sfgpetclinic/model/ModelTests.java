package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

@Tag("model")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface ModelTests {
// test interface is useful to define common properties, e.g. @Tag
	
	@BeforeAll
	default void beforeAll() {
		System.out.println("Do something at the start of class!");
	}
}
