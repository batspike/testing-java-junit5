package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

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

	@DisplayName("Value Source Test")
	@ParameterizedTest(name="{displayName} - [{index}] {arguments}")
	@ValueSource(strings= {"Spring","Framework","Guru"})
	void testValueSource(String val) {
		System.out.println(val);
	}
	
	@DisplayName("Enum Source Test")
	@ParameterizedTest(name="{displayName} - [{index}] {arguments}")
	@EnumSource(OwnerType.class)
	void testEnumSource(OwnerType ownerType) {
		System.out.println(ownerType);
	}
	
	@DisplayName("CSV Source Test")
	@ParameterizedTest(name="{displayName} - [{index}] {arguments}")
	@CsvSource({
		"FL, 1, 1",
		"OH, 2, 2",
		"MI, 3, 3"
	})
	void testCsvSource(String stateName, int val1, int val2) {
		System.out.println(stateName + " = "+ val1 + ":"+ val2);
	}
	
	@DisplayName("CSV File Source Test")
	@ParameterizedTest(name="{displayName} - [{index}] {arguments}")
	@CsvFileSource(resources="/csvfile.txt", numLinesToSkip=1)
	void testCsvFileSource(String stateName, int val1, int val2) {
		System.out.println(stateName + " = "+ val1 + ":"+ val2);
	}
	
	
	
}
