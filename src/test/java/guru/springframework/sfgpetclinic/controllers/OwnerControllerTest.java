package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
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
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	@Mock
	Model model;

	@BeforeEach
	void setUp() {
		given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
			.willAnswer(invocation -> {
				String name = invocation.getArgument(0);
				List<Owner> owners = new ArrayList<>();
				
				if(name.equals("%Buck%")) {
					owners.add(new Owner(1L, "Joe", "Buck"));
					return owners;
				}
				else if(name.equals("%DontFindMe%")) {
					return owners;
				}
				else if(name.equals("%FindMe%")) {
					owners.add(new Owner(1L, "Joe", "Buck"));
					owners.add(new Owner(2L, "Jane", "Doe"));
					return owners;
				}
				
				throw new RuntimeException("Invalid Argument");
			});
	}
	
	@Test
	void processFindFormWildcardFound() {
		//Given
		Owner owner = new Owner(1L,"Joe","FindMe");

		//define mock objects that need to be verified in the order of their invocation
		InOrder inOrder = inOrder(ownerService, model); 

		//When
		//within processFindForm the ownerService is invoke first, then follow by model
		String viewName = controller.processFindForm(owner, bindingResult, model);
		
		//verify the order of invocation of the ownerService and model is same sequence as listed below
		inOrder.verify(ownerService).findAllByLastNameLike(anyString());
		inOrder.verify(model).addAttribute(anyString(), anyList());
		
		//Then
		assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
		assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
	}
	
	@Test
	void processFindFormWildcardNotFound() {
		//Given
		Owner owner = new Owner(1L,"Joe","DontFindMe");
		
		//When
		String viewName = controller.processFindForm(owner, bindingResult, null);
		
		//Then
		assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
		assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
	}
	
	@Test
	void processFindFormWildcardStringAnnotation() {
		//Given
		Owner owner = new Owner(1L,"Joe","Buck");
		
		//When
		String viewName = controller.processFindForm(owner, bindingResult, null);
		
		//Then
		assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
		assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
	}
	
	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	void processFindFormWildcardString() {
		//Given
		Owner owner = new Owner(1L,"Joe","Buck");
		
		//When
		String viewName = controller.processFindForm(owner, bindingResult, null);
		
		//Then
		assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
	}
	
	
	
	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
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
	@MockitoSettings(strictness = Strictness.LENIENT)
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
