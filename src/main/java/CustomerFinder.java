import com.google.common.collect.Lists;

final class CustomerFinder {

    static final int NULL_CUSTOMER_ID = 0;
    static final int DEFAULT_CUSTOMER_ID = 1;

    static Customer findCustomerBy(int customerId) {
        return customerId == NULL_CUSTOMER_ID ? null : new Customer(Lists.newArrayList("Order 1", "Order 2", "Order 3"));

    }
}
