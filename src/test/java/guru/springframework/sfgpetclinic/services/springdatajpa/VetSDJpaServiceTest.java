package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.repositories.VetRepository;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {
	
	@Mock
	VetRepository vetRepository; //the mock object
	
	@InjectMocks
	VetSDJpaService service; //object which will receive the mock object above
	
	@Test
	void testDeleteById() {
		service.deleteById(1L);
		verify(vetRepository).deleteById(1L);//verify the deleteById(1L) was deleted once (default)
		
		service.deleteById(1L); // delete one more time
		verify(vetRepository, times(2)).deleteById(1L);//verify the deleteById(1L) was deleted twice
		verify(vetRepository, atLeastOnce()).deleteById(1L);//verify the deleteById(1L) was deleted at least once
		verify(vetRepository, atMost(4)).deleteById(1L);//verify the deleteById(1L) was deleted at most 4 times

		verify(vetRepository, never()).deleteById(4L);//verify the deleteById(4L) was never called
	}

}
