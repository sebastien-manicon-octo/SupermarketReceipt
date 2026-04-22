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
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < 2) {
            return Optional.empty();
        }

        double priceWithoutDiscount = unitPrice * quantity;
        double priceItemDiscounted = argument * (double) (quantityAsInt / 2);
        double priceItemStaying = quantityAsInt % 2 * unitPrice;

        double discountN = priceWithoutDiscount - (priceItemDiscounted + priceItemStaying);
        return Optional.of(new Discount(product, "2 for " + argument, -discountN));
    }
}
