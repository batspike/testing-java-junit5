package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OwnerTest implements ModelTests {

	@Test
	void nestedAssertAll() {
		Owner owner = new Owner(1L, "Joe", "Buck");
		owner.setCity("Key West");
		owner.setTelephone("33993908403");
		
		assertAll(	"Properties Test", 
					() -> assertAll("Person Properties", 
									() -> assertEquals("Joe", owner.getFirstName(), "First Name error"),
									() -> assertEquals("Buck", owner.getLastName())
								   ),
					() -> assertAll("Owner Properties", 
									() -> assertEquals("Key West", owner.getCity()),
									() -> assertEquals("33993908403", owner.getTelephone())
								   )
				 );
	}

}
