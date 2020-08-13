package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

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
		//Given
		Speciality speciality = new Speciality();
		
		//When
		service.delete(speciality);
		
		//Then
		then(specialtyRepository).should().delete(any(Speciality.class));
	}
	
	@Test
	void findByIdBddTest() {
		//Given
		Speciality speciality = new Speciality();
		given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));// using BDD Mockito
		
		//When
		Speciality foundSpeciality = service.findById(1L);
		assertThat(foundSpeciality).isNotNull();
		
		//Then
		then(specialtyRepository).should().findById(anyLong());
		then(specialtyRepository).should(times(1)).findById(anyLong());
		then(specialtyRepository).shouldHaveNoMoreInteractions();
	}
	
	@Test
	void testDeleteById() {
		//Given - none
		
		//When
		service.deleteById(1L);
		service.deleteById(1L); // delete one more time

		//Then
		then(specialtyRepository).should(times(2)).deleteById(1L);
	}

	@Test
	void testDeleteByIdAtLeast() {
		//Given
		
		//When
		service.deleteById(1L);
		service.deleteById(1L);
		
		//Then
		then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
	}
	
	@Test
	void testDeleteByIdAMost() {
		//Given
		
		//When
		service.deleteById(1L);
		service.deleteById(1L);
		
		//Then
		then(specialtyRepository).should(atMost(3)).deleteById(1L);
	}
	
	@Test
	void testDeleteByIdNever() {
		//Given
		
		//When
		service.deleteById(1L);
		service.deleteById(1L);
		
		//Then
		then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
		then(specialtyRepository).should(never()).deleteById(5L);
	}
	
	@Test
	void testDelete() {
		//Given
		
		//When
		service.delete(new Speciality());
		
		//Then
		then(specialtyRepository).should().delete(any(Speciality.class));
	}
	
	@Test
	void testDoThrow() {
		doThrow(new RuntimeException("Boom")).when(specialtyRepository).delete(any());
		assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
		verify(specialtyRepository).delete(any());
	}
	
	@Test
	void testFindyByIdThrows() {
		given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("Boom"));
		assertThrows(RuntimeException.class, () -> specialtyRepository.findById(1L));
		then(specialtyRepository).should().findById(1L);
	}
	
	@Test
	void testDeleteBDD() {
		willThrow(new RuntimeException("Boom")).given(specialtyRepository).delete(any());
		assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
		then(specialtyRepository).should().delete(any());
	}
	
    @Test
    void testSaveLambdaMatch() {
        //Given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        //Only create a mock when the save object argument has a description that matches MATCH_ME
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME))))
        															.willReturn(savedSpecialty);

        //When
        Speciality returnedSpecialty = service.save(speciality);

        //Then
        assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }
    
    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void testSaveLambdaNoMatch() {
    	
    	//Given
    	final String MATCH_ME = "MATCH_ME";
    	Speciality speciality = new Speciality();
    	speciality.setDescription("Not a match");
    	
    	Speciality savedSpecialty = new Speciality();
    	savedSpecialty.setId(1L);
    	
    	//Only create a mock when the save object argument has a description that matches MATCH_ME
    	//Note: must set Mockito strictness level to LENIENT
    	given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME))))
    																.willReturn(savedSpecialty);
    	
    	//When
    	// this will not trigger the mock since speciality.getDescription = "Not a match"; so return null
    	Speciality returnedSpecialty = service.save(speciality);
    	
    	//Then
    	assertNull(returnedSpecialty);
    }
    
    
    
    
    
    
}
