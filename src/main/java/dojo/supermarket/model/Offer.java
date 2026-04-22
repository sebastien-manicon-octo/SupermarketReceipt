package dojo.supermarket.model;

import java.util.Optional;

public abstract class Offer {

    protected final Product product;
    protected double argument;

    public Offer(Product product, double argument) {
        this.argument = argument;
        this.product = product;
    }

    public abstract Optional<Discount> getDiscount(double unitPrice, double quantity);

    public Product getProduct() {
        return product;
    }
}
