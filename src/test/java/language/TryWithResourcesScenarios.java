package language;

import static org.assertj.core.api.BDDAssertions.then;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class TryWithResourcesScenarios {

    @Test
    public void shouldTryWithResourcesJava8() throws Exception {
        //Given
        AutoClosableResource autoCloseable = new AutoClosableResource();

        //When
        closeResourceJava8Style(autoCloseable);

        //Then
        BDDAssertions.then(autoCloseable.isClosed()).isTrue();
    }

    @Test
    public void shouldTryWithResourcesJava9() throws Exception {
        //Given
        AutoClosableResource autoCloseable = new AutoClosableResource();

        //When
        closeResourceJava9Style(autoCloseable);

        //Then
        BDDAssertions.then(autoCloseable.isClosed()).isTrue();
    }

    private void closeResourceJava8Style(AutoCloseable autoCloseable) throws Exception {
        try (AutoCloseable closeable = autoCloseable) {
            // Intentionally empty.
        }
    }

    private void closeResourceJava9Style(AutoCloseable autoCloseable) throws Exception {
        try (autoCloseable) { // Only on effectively final variable
            // Intentionally empty.
        }
    }
}
