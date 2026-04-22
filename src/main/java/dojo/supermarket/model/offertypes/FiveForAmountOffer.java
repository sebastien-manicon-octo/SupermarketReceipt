package dojo.supermarket.model.offertypes;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SpecialOfferType;

public class FiveForAmountOffer extends Offer {
    public FiveForAmountOffer(SpecialOfferType offerType, Product product, double argument) {
        super(offerType, product, argument);
    }

    @Override
    public Discount getDiscount(double unitPrice, double quantity) {
        Product p = getProduct();
        int x = 5;
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < 5) {
            return null;
        }

        int numberOfXs = quantityAsInt / x;
        double discountTotal = unitPrice * quantity - (argument * numberOfXs + quantityAsInt % 5 * unitPrice);
        return new Discount(p, x + " for " + argument, -discountTotal);
    }
}
