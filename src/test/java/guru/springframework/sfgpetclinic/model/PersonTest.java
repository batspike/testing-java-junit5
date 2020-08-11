package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;

@TestMethodOrder(OrderAnnotation.class)
class PersonTest implements ModelTests {

	@Test
	@Order(1)
	void groupedAssertions() {
		Person person = new Person(1L, "Joe", "Buck");
		
		assertAll( "Test Props Set",
					() -> assertEquals("Joe", person.getFirstName()) ,
					() -> assertEquals("Buck", person.getLastName()) 
				);
	}

	@Test
	@Order(2)
	void groupedAssertionsFail() {
		Person person = new Person(1L, "Joe", "Buck");
		
		assertAll( "Test Props Set",
					() -> assertEquals("Joe", person.getFirstName(), "First Name Failed"),
					() -> assertEquals("Buck", person.getLastName(), "Last Name Failed")
				);
	}
	
	@RepeatedTest(value=10, name="{displayName} : {currentRepetition} of {totalRepetitions}") // repeat 10 times
	@DisplayName("My Repeated Test")
	void myRepeatedTest() {
		
	}
	
	@RepeatedTest(5) // repeat 5 times
	@DisplayName("Another Repetition Test")
	void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
	}
	
	@Tag("assignment")
	@RepeatedTest(value=5, name="{displayName} : {currentRepetition} of {totalRepetitions}") // repeat 10 times
	@DisplayName("My Assignment Repeated Test")
	void myAssignmentRepeatedTest() {
		
	}
	
	
}
