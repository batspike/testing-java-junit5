package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

@Disabled("Disabled until we learn mocking")
class OwnerSDJpaServiceTest {
	OwnerSDJpaService service;

	@BeforeEach
	void setUp() throws Exception {
		service = new OwnerSDJpaService(null,null,null);
	}

	@Test
	@Disabled
	void testFindByLastName() {
		Owner foundOwner = service.findByLastName("Buck");
		
	}

	@Test
	void testFindAllByLastNameLike() {
		fail("not implemented");
	}

	@Test
	void testFindAll() {
		
	}

	@Test
	void testFindById() {
		
	}

	@Test
	void testSave() {
		
	}

	@Test
	void testDelete() {
		
	}

	@Test
	void testDeleteById() {
		
	}

}
