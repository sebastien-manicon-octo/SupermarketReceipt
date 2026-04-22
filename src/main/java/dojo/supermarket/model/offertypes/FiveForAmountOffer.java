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
        int quantityAsInt = (int) quantity;

        if (quantityAsInt < 5) {
            return Optional.empty();
        }

        int numberOfXs = quantityAsInt / 5;

        double priceWithoutDiscount = unitPrice * quantity;
        double priceItemDiscounted = argument * numberOfXs;
        double priceItemStaying = quantityAsInt % 5 * unitPrice;

        double discountTotal = priceWithoutDiscount - (priceItemDiscounted + priceItemStaying);
        return Optional.of(new Discount(product, "5 for " + argument, -discountTotal));
    }
}
