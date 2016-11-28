package language;

import static java.util.Map.entry;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.expectThrows;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

class CollectionFactoryMethodsScenarios {

    @Test
    public void shouldInitializeList() {
        //When
        List<String> letters = List.of("a", "b", "c");

        //Then
        then(letters).containsExactly("a", "b", "c");
    }

    @Test
    public void shouldInitializationBeImmutable(){
        //Given
        List<String> letters = List.of("a", "b", "c");

        //When
        expectThrows(UnsupportedOperationException.class, () -> letters.add("d"));
    }

    @Test
    public void shouldNotAllowNulls(){
        //When
        expectThrows(NullPointerException.class, () -> List.of("a", "b", null));
    }

    @Test
    public void shouldInitializeMap(){
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
    public void shouldInitializeMapByEntries(){
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
