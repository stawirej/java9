import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

public class StreamIteratorScenarios {

    /**
     * Use IntStream.rangeClosed(0, 5) instead
     */
    @Test
    public void shouldIterateWithStopCondition() {
        // When
        final List<Integer> numbers = //
            Stream //
                .iterate(0, i -> i < 5, i -> i + 1) //
                .collect(toList());

        // Then
        then(numbers).containsExactly(0, 1, 2, 3, 4);
    }

    /**
     * Do not use to iterate through iterable like data as it lose last element.
     */
    @Test
    public void shouldLoseLastElementWhenIterateThroughIteratorLikeData() {
        // Given
        final StringTokenizer tokens = new StringTokenizer("A B C D");
        List<Object> letters = Lists.newArrayList();

        // When
        if (tokens.hasMoreElements()) {
            letters = //
                Stream //
                    .iterate(tokens.nextElement(), //
                        element -> tokens.hasMoreElements(), //
                        element -> tokens.nextElement()) //
                    .collect(toList());
        }

        // Then
        then(letters).containsExactly("A", "B", "C");
    }
}
