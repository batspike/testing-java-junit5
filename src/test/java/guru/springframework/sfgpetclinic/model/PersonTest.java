package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
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
	
}
