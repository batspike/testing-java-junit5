package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

	@Mock
	SpecialtyRepository specialtyRepository; //the mock object
	
	@InjectMocks
	SpecialitySDJpaService service; //object which will receive the mock object above
	
	// each of the tests below will get its own mock object which is injeccted
	// into its own service object.
	@Test
	void testDeleteById() {
		service.deleteById(1L);
	}

	@Test
	void testDelete() {
		service.delete(new Speciality());
	}
	
}
