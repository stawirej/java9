import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

final class OptionalStreamScenarios {

    @Test
    void shouldGetOptionalPresentValuesOldStyleOne() {
        // Given
        List<Optional<Integer>> values = Lists.newArrayList(Optional.of(1), Optional.empty(), Optional.of(3));

        // When
        final List<Integer> numbers = values //
            .stream() //
            .filter(Optional::isPresent) //
            .map(Optional::get) //
            .collect(toList());

        // Then
        then(numbers).containsExactly(1, 3);
    }

    @Test
    void shouldGetOptionalPresentValuesOldStyleTwo() {
        // Given
        List<Optional<Integer>> values = Lists.newArrayList(Optional.of(1), Optional.empty(), Optional.of(3));

        // When
        final List<Integer> numbers = values //
            .stream() //
            .flatMap(value -> value.isPresent() ? Stream.of(value.get()) : Stream.empty()) //
            .collect(toList());

        // Then
        then(numbers).containsExactly(1, 3);
    }

    @Test
    void shouldGetOptionalPresentValuesJava9Style() {
        // Given
        List<Optional<Integer>> values = Lists.newArrayList(Optional.of(1), Optional.empty(), Optional.of(3));

        // When
        List<Integer> numbers = values //
            .stream() //
            .flatMap(Optional::stream) // same as flatMap(value -> value.stream())
            .collect(toList());

        // Then
        then(numbers).containsExactly(1, 3);
    }
}
