package language;

import java.util.List;

final class Customer {

    private List<String> orders;

    Customer(List<String> orders) {
        this.orders = orders;
    }

    List<String> getOrders() {
        return orders;
    }

}
