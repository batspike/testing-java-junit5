package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@Tag("controller")
@TestMethodOrder(OrderAnnotation.class)
class IndexControllerTest {
	IndexController controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new IndexController();
	}

	@Test
	@DisplayName("Test Proper View name is returned")
	@Order(1)
	void testIndex() {
		assertEquals("index", controller.index());
	}

	@Test
	@Order(2)
	@DisplayName("Test exception")
	void testOupsHandler() {
		assertThrows(ValueNotFoundException.class, () -> {
			controller.oopsHandler();
		});
	}

	@Test
	@Disabled
	@Order(3)
	@DisplayName("Assert TimeOut")
	void testTimeOut() {
		assertTimeout(Duration.ofMillis(100), () -> { 
			Thread.sleep(5000); 
			System.out.println("I am in the same Thread!");
		});
	}
	
	@Test
	@Disabled
	@Order(4)
	@DisplayName("Assert Preemptive TimeOut")
	void testPreemptiveTimeOut() {
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> { 
			Thread.sleep(5000); 
			System.out.println("I am in different Thread!");
		});
	}
	
	@Test
	@Order(5)
	void testAssumptionTrue() {
		assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
		// even though the above will result in false, JUnit will not treat this as a failure
	}
	
	@Test
	@Order(6)
	void testAssumptionTrueAssumptionIsTrue() {
		assumeTrue("GURU".equalsIgnoreCase("GURU"));
	}
	
	
	@Test
	@Order(7)
	@EnabledOnOs(OS.MAC)
	void testMeOnMacOS() {
		
	}
	
	@Test
	@Order(8)
	@EnabledOnOs(OS.WINDOWS)
	void testMeOnWindows() {
		
	}
	
	@Test
	@Order(9)
	@EnabledOnJre(JRE.JAVA_8)
	void testMeOnJava8() {
		
	}
	
	@Test
	@Order(10)
	@EnabledOnJre(JRE.JAVA_14)
	void testMeOnJava14() {
		
	}
	
	@Test
	@Order(11)
	@EnabledIfEnvironmentVariable(named="USERNAME", matches="jt")
	void testIfUserJT() {
		
	}
	
	@Test
	@Order(12)
	@EnabledIfEnvironmentVariable(named="USERNAME", matches="samlau")
	void testIfUserSamLau() {
		
	}
	
	@Test
	@Disabled
	void printEnvironmentProperties() {
		Map<String,String> env = System.getenv();
		env.forEach( (k,v) -> System.out.println(k + ":" + v) );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
