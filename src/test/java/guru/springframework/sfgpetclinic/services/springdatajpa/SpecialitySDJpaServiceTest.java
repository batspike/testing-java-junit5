package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
	void findByIdTest() {
		Speciality speciality = new Speciality();
		
		when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
		
		Speciality foundSpecialty = service.findById(1L);
		
		assertNotNull(foundSpecialty);
		
		verify(specialtyRepository).findById(1L);
	}
	
	@Test
	void testDeleteById() {
		service.deleteById(1L);
		verify(specialtyRepository).deleteById(1L);//verify the deleteById(1L) was deleted once (default)
		
		service.deleteById(1L); // delete one more time
		verify(specialtyRepository, times(2)).deleteById(1L);//verify the deleteById(1L) was deleted twice
		verify(specialtyRepository, atLeastOnce()).deleteById(1L);//verify the deleteById(1L) was deleted at least once
		verify(specialtyRepository, atMost(4)).deleteById(1L);//verify the deleteById(1L) was deleted at most 4 times

		verify(specialtyRepository, never()).deleteById(4L);//verify the deleteById(4L) was never called
		
	}

	@Test
	void testDelete() {
		service.delete(new Speciality());
	}
	
}
