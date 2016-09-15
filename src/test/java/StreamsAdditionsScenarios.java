import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;

final class StreamsAdditionsScenarios {

    @Test
    void shouldTakeWhilePredicateIsTrue() {
        // Given
        List<Integer> numbers = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        // When
        List<Integer> collect =
                numbers
                        .stream()
                        .takeWhile(value -> value < 6)
                        .collect(toList());

        // Then
        then(collect).containsExactly(0, 1, 2, 3, 4, 5);
    }

    @Test
    void shouldDropWhilePredicateIsFalse() {
        //Given
        List<Integer> numbers = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        //When
        List<Integer> collect = numbers
                .stream()
                .dropWhile(value -> value < 5)
                .collect(toList());

        //Then
        then(collect).containsExactly(5, 6, 7, 8, 9);
    }

    @Test
    void shouldCreateStreamFromNonNullable() {
        //Given
        long count = Stream.ofNullable("43").count();

        //Then
        then(count).isEqualTo(1);
    }

    @Test
    void shouldCreateStreamFromNullable() {
        //Given
        long count = Stream.ofNullable(null).count();

        //Then
        then(count).isEqualTo(0);
    }

}
