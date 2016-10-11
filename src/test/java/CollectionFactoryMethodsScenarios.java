import static java.util.Map.entry;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

class CollectionFactoryMethodsScenarios {

    @Test
    public void shouldInitialiseList() {
        //When
        List<String> letters = List.of("a", "b", "c");

        //Then
        then(letters).containsExactly("a", "b", "c");
    }

    @Test
    public void shouldInitialiseMap(){
        //When
        Map<String, Integer> nameToValue = Map.of(
            "one", 1,
            "two", 2,
            "three", 3
        );

        //Then
        then(nameToValue)
            .containsKeys("one", "two", "three")
            .containsValues(1, 2, 3);
    }

    @Test
    public void shouldInitialiseMapByEntries(){
        //When
        Map<String, Integer> nameToValue = Map.ofEntries(
            entry("one", 1),
            entry("two", 2),
            entry("three", 3)
        );

        //Then
        then(nameToValue)
            .containsKeys("one", "two", "three")
            .containsValues(1, 2, 3);
    }
}
