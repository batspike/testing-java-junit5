package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
	@Order(3)
	@DisplayName("Assert TimeOut")
	void testTimeOut() {
		assertTimeout(Duration.ofMillis(100), () -> { 
			Thread.sleep(5000); 
			System.out.println("I am in the same Thread!");
		});
	}
	
	@Test
	@Order(4)
	@DisplayName("Assert Preemptive TimeOut")
	void testPreemptiveTimeOut() {
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> { 
			Thread.sleep(5000); 
			System.out.println("I am in different Thread!");
		});
	}
	
}
