import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
    void shouldDropWhilePredicateIsTrue() {
        //Given
        List<Integer> numbers = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        //When
        List<Integer> collect =
                numbers
                        .stream()
                        .dropWhile(value -> value < 5)
                        .collect(toList());

        //Then
        then(collect).containsExactly(5, 6, 7, 8, 9);
    }

    @Test
    void shouldGetMiddleValues() {
        //Given
        List<Integer> numbers = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        //When
        List<Integer> middleValues =
                numbers
                        .stream()
                        .dropWhile(value -> value < 3)
                        .takeWhile(value -> value < 7)
                        .collect(toList());

        //Then
        then(middleValues).containsExactly(3, 4, 5, 6);
    }

    @Test
    void shouldCreateStreamFromNonNullable() {
        //Given
        long count = Stream.ofNullable("43").count();

        //Then
        then(count).isEqualTo(1);
    }

    @Test
    void shouldCreateEmptyStreamFromNullable() {
        //Given
        long count = Stream.ofNullable(null).count();

        //Then
        then(count).isEqualTo(0);
    }

    @Test
    void shouldGetEmptyOrdersStreamFromNullableCustomerJava8Style() {
        //Given
        Customer customer = CustomerFinder.findCustomerBy(CustomerFinder.NULL_CUSTOMER_ID);

        //When
        Stream<String> orders = customer == null ? Stream.empty() : customer.getOrders().stream();

        //Then
        then(orders).isEmpty();
    }

    @Test
    void shouldGetEmptyOrdersStreamFromNullableCustomerWithOptionalJava8Style() {
        //Given
        Customer customer = CustomerFinder.findCustomerBy(CustomerFinder.NULL_CUSTOMER_ID);

        //When
        List<String> orders =
                Optional
                        .ofNullable(customer)
                        .map(Customer::getOrders)
                        .orElse(Lists.emptyList());

        //Then
        then(orders).isEmpty();
    }

    @Test
    void shouldGetEmptyOrdersListFromNullableCustomer() {
        //Given
        Customer customer = CustomerFinder.findCustomerBy(CustomerFinder.NULL_CUSTOMER_ID);

        //When
        List<String> orders =
                Stream
                        .ofNullable(customer)
                        .map(Customer::getOrders)
                        .flatMap(Collection::stream)
                        .collect(toList());

        //Then
        then(orders).isEmpty();
    }

    @Test
    void shouldGetOrdersListFromCustomer() {
        //Given
        Customer customer = CustomerFinder.findCustomerBy(CustomerFinder.DEFAULT_CUSTOMER_ID);

        //When
        List<String> orders =
                Stream
                        .ofNullable(customer)
                        .map(Customer::getOrders)
                        .flatMap(Collection::stream)
                        .collect(toList());

        //Then
        then(orders).containsExactly("Order 1", "Order 2", "Order 3");
    }

}
