package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;

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
	void testOupsHandler() {
		assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "Expensive Message");
	}

}
