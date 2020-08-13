package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
	
	@Mock
	VisitRepository visitRepository;
	
	@InjectMocks
	VisitSDJpaService service;

	@Test
	void testFindAll() {
		//Given
		Visit visit = new Visit();
		Set<Visit> visits = new HashSet<>();
		visits.add(visit);
		given(visitRepository.findAll()).willReturn(visits);

		//When
		Set<Visit> foundVisits = service.findAll();
		
		//Then
		then(visitRepository).should().findAll();
		assertThat(foundVisits).hasSize(1);
	}

	@Test
	void testFindById() {
		//Given
		Visit visit = new Visit();
		given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

		//When
		Visit foundVisit = service.findById(1L);
		
		//Then
		then(visitRepository).should().findById(anyLong());
		assertThat(foundVisit).isNotNull();
	}

	@Test
	void testSave() {
		//Given
		Visit visit = new Visit();
		given(visitRepository.save(any(Visit.class))).willReturn(visit);
		
		Visit savedVisit = service.save(visit);
		
		//Then
		then(visitRepository).should().save(any(Visit.class));
		assertThat(savedVisit).isNotNull();
	}

	@Test
	void testDelete() {
		//Given
		Visit visit = new Visit();
		
		//When
		service.delete(visit);
		
		//Then
		then(visitRepository).should().delete(any(Visit.class));
	}

	@Test
	void testDeleteById() {
		//Given
		
		//When
		service.deleteById(1L);
		
		//Then
		then(visitRepository).should().deleteById(anyLong());
	}

}
