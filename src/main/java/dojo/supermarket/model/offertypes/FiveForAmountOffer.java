package dojo.supermarket.model.offertypes;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

import java.util.Optional;

public class FiveForAmountOffer extends Offer {
    private final double argument;

    public FiveForAmountOffer(Product product, double argument) {
        super(product);
        this.argument = argument;
    }

    @Override
    public Optional<Discount> getDiscount(double unitPrice, double quantity) {
        Product p = getProduct();
        int x = 5;
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < 5) {
            return Optional.empty();
        }

        int numberOfXs = quantityAsInt / x;
        double discountTotal = unitPrice * quantity - (argument * numberOfXs + quantityAsInt % 5 * unitPrice);
        return Optional.of(new Discount(p, x + " for " + argument, -discountTotal));
    }
}
