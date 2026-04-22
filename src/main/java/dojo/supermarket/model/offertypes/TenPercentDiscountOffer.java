package dojo.supermarket.model.offertypes;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

import java.util.Optional;

public class TenPercentDiscountOffer extends Offer {
    private final double argument;

    public TenPercentDiscountOffer(Product product, double argument) {
        super(product);
        this.argument = argument;
    }

    @Override
    public Optional<Discount> getDiscount(double unitPrice, double quantity) {
        double priceWithoutDiscount = quantity * unitPrice;
        double discountTotal = priceWithoutDiscount * argument / 100.0;
        return Optional.of(new Discount(product, argument + "% off", -discountTotal));
    }
}
