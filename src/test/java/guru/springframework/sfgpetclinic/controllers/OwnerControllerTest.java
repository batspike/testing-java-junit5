package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
	private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";

	@Mock
	OwnerService ownerService;
	
	@InjectMocks
	OwnerController controller;
	
	@Mock
	BindingResult bindingResult;

	@Test
	void testProcessCreationFormHasErrors() {
		//Given
		Owner owner = new Owner(1L,"Jim","Bob");
		given(bindingResult.hasErrors()).willReturn(true);
		
		//When
		String viewName = controller.processCreationForm(owner, bindingResult);
		
		//Then
		assertThat(viewName.equalsIgnoreCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM));
		
	}

	@Test
	void testProcessCreationFormNoErrors() {
		//Given
		Owner owner = new Owner(5L,"Jim","Bob");
		given(bindingResult.hasErrors()).willReturn(false);
		given(ownerService.save(any())).willReturn(owner);
		
		//When
		String viewName = controller.processCreationForm(owner, bindingResult);
		
		
		//Then
		assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
		
		
	}
	
}
