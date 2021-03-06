package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import guru.springframework.sfgpetclinic.CustomArgsProvider;

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
	
	@DisplayName("Method Source Test")
	@ParameterizedTest(name="{displayName} - [{index}] {arguments}")
	@MethodSource("getArgs")
	void testMethodSource(String stateName, int val1, int val2) {
		System.out.println(stateName + " = "+ val1 + ":"+ val2);
	}
	static Stream<Arguments> getArgs() {
		return Stream.of(Arguments.of("FL", 1, 1), Arguments.of("OH", 2, 2), Arguments.of("MI", 3, 3));
	}

	
	@DisplayName("Custom Argument Source Test")
	@ParameterizedTest(name="{displayName} - [{index}] {arguments}")
	@ArgumentsSource(CustomArgsProvider.class)
	void testCustomMethodSource(String stateName, int val1, int val2) {
		System.out.println(stateName + " = "+ val1 + ":"+ val2);
	}
	

}
