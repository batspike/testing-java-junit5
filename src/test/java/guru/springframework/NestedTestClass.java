package guru.springframework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Testing Mathematical Operation includes ")
class NestedTestClass {

	@Nested
	@DisplayName("testing of addtions for ")
	class AddtionTestClass {
		@Test
		@DisplayName("Positive Numbers")
		void testPositiveNumberAddtion() {
			assertEquals( 5, (3 + 2), "should result in larger positive numbers.");
		}
		
		@Test
		@DisplayName("Negative Numbers")
		void testNegativeNumberAddition() {
			assertEquals( -6, ((-3) + (-3)), "should result in smaller negative numbers.");
		}
	}

}
