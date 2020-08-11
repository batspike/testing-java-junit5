package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;

@Tag("model")
public interface ModelTests {
// test interface is useful to define common properties, e.g. @Tag
	
	@BeforeEach
	default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		System.out.println("Running Test - "+ testInfo.getDisplayName() + " - "+ 
							repetitionInfo.getCurrentRepetition() + " | " +
							repetitionInfo.getTotalRepetitions());
	}
}
