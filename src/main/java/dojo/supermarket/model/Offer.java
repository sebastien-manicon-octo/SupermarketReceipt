package dojo.supermarket.model;

import java.util.Optional;

public abstract class Offer {

    protected final Product product;

    public Offer(Product product) {
        this.product = product;
    }

    public abstract Optional<Discount> getDiscount(double unitPrice, double quantity);

    public Product getProduct() {
        return product;
    }
}
