package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
	void testDeleteByObject() {
		Speciality speciality = new Speciality();
		
		service.delete(speciality);
		
		verify(specialtyRepository).delete(any(Speciality.class)); //argument matching any of type Speciality
	}
	
	@Test
	void findByIdTest() {
		Speciality speciality = new Speciality();
		
		when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(speciality));
		// when finding any id of type Long, then return a speciality object
		
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
