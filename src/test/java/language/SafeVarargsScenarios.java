package language;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import java.util.Optional;

class SafeVarargsScenarios {

    @Test
    public void shouldGetFirstElement() {
        //When
        final Optional<Integer> first = first(1, 2, 3);

        //Then
        then(first.isPresent()).isTrue();
        then(first.get()).isEqualTo(1);
    }

    @Test
    public void shouldGetEmptyOptional() {
        //When
        final Optional<Integer> first = first();

        //Then
        then(first.isPresent()).isFalse();
    }

    //Private, non-final methods can now be annotated.
    @SafeVarargs
    private <T> Optional<T> first(T... arguments) {
        return arguments.length == 0 ? Optional.empty() : Optional.of(arguments[0]);
    }
}
