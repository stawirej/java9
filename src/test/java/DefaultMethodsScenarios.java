import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class DefaultMethodsScenarios {

    @Test public void shouldCalculateEvenSum() {
        //Given
        SumChecker sumChecker = new SumChecker();

        //When
        final boolean isEvenSum = sumChecker.evenSum(1, 2, 3, 4);

        //Then
        then(isEvenSum).isTrue();
    }

    @Test public void shouldCalculateOddSum() {
        //Given
        SumChecker sumChecker = new SumChecker();

        //When
        final boolean isOddSum = sumChecker.oddSum(1, 2, 3, 4, 5);

        //Then
        then(isOddSum).isTrue();
    }

    @Test public void shouldHaveAccessToAdditionalMethodInLegacySumChecker() {
        //Given
        LegacySumChecker legacySumChecker = new LegacySumChecker();

        //When
        final int sum = legacySumChecker.sum(1, 2, 3);

        //Then
        then(sum).isEqualTo(6);
    }
}
