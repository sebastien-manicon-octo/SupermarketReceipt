package dojo.supermarket.model.offertypes;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

import java.util.Optional;

public class ThreeForTwoOffer extends Offer {
    public ThreeForTwoOffer(Product product) {
        super(product);
    }

    @Override
    public Optional<Discount> getDiscount(double unitPrice, double quantity) {
        int quantityAsInt = (int) quantity;
        if (quantityAsInt <= 2) {
            return Optional.empty();
        }

        int numberOfXs = quantityAsInt / 3;
        double priceWithoutDiscount = quantity * unitPrice;
        double priceItemDiscounted = numberOfXs * 2 * unitPrice;
        double priceItemStaying = quantityAsInt % 3 * unitPrice;

        double discountAmount = priceWithoutDiscount - (priceItemDiscounted + priceItemStaying);
        return Optional.of(new Discount(product, "3 for 2", -discountAmount));
    }
}
