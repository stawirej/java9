package language;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.setAllowComparingPrivateFields;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

final class StreamIteratorScenarios {

    /**
     * Use IntStream.rangeClosed(0, 5) instead
     */
    @Test
    void shouldIterateWithStopCondition() {
        // When
        List<Integer> numbers =
                Stream
                        .iterate(0, i -> i < 5, i -> i + 1)
                        .collect(toList());

        // Then
        then(numbers).containsExactly(0, 1, 2, 3, 4);
    }

    /**
     * Do not use to iterate through iterable like data as it lose last element.
     */
    @Test
    void shouldLoseLastElementWhenIterateThroughIteratorLikeData() {
        // Given
        StringTokenizer tokens = new StringTokenizer("A B C D");
        List<Object> letters = Lists.newArrayList();

        // When
        if (tokens.hasMoreElements()) {
            letters =
                    Stream
                            .iterate(tokens.nextElement(),
                                    element -> tokens.hasMoreElements(),
                                    element -> tokens.nextElement())
                            .collect(toList());
        }

        // Then
        then(letters).containsExactly("A", "B", "C");
    }

    @Test
    void shouldLoseLastElementWhenPopStackByIterate(){
        //Given
        LogStack logStack = new LogStack();
        logStack.push(1);
        logStack.push(2);
        logStack.push(3);
        logStack.push(4);

        //When
        List<Integer> popped =
                Stream.iterate(logStack.popSeed(),              // popSeed() just to distinguish in logging.
                                                                // Predicate is not checked for seed pop.
                                                                // For empty stack there will be exception.
                                element -> !logStack.isEmpty(), // Predicate is consulted before pushing to stream.
                                element -> logStack.pop())      // This goes before !logStack.isEmpty() predicate.
                    .collect(toList());

        //Then
        then(popped).containsExactlyElementsOf(List.of(4, 3, 2));
    }
}
