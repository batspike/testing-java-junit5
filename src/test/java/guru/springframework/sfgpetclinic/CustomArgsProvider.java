package guru.springframework.sfgpetclinic;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CustomArgsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(
				Arguments.of("CustomFL", 1, 1), 
				Arguments.of("CustomOH", 2, 2), 
				Arguments.of("CustomMI", 3, 3));
	}

}
