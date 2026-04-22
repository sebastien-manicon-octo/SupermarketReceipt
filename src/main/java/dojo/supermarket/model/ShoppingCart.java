package dojo.supermarket.model;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();

    List<ProductQuantity> getItems() {
        return Collections.unmodifiableList(items);
    }

    void addItem(Product product) {
        addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return items.stream().collect(
                Collectors.groupingBy(
                        ProductQuantity::getProduct,
                        Collectors.summingDouble(ProductQuantity::getQuantity)
                )
        );
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        Map<Product, Double> productQuantities = productQuantities();

        for (Product p : productQuantities().keySet()) {
            if (!offers.containsKey(p)) {
                continue;
            }

            double quantity = productQuantities.get(p);
            double unitPrice = catalog.getUnitPrice(p);
            offers.get(p)
                    .getDiscount(unitPrice, quantity)
                    .ifPresent(receipt::addDiscount);
        }
    }

}
