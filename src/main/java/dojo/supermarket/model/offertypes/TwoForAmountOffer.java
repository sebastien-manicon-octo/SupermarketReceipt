package dojo.supermarket.model.offertypes;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

import java.util.Optional;

public class TwoForAmountOffer extends Offer {
    private final double argument;

    public TwoForAmountOffer(Product product, double argument) {
        super(product);
        this.argument = argument;
    }

    @Override
    public Optional<Discount> getDiscount(double unitPrice, double quantity) {
        Product p = getProduct();
        int x = 2;
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < 2) {
            return Optional.empty();
        }

        double total = argument * (quantityAsInt / x) + quantityAsInt % 2 * unitPrice;
        double discountN = unitPrice * quantity - total;
        return Optional.of(new Discount(p, "2 for " + argument, -discountN));
    }
}
