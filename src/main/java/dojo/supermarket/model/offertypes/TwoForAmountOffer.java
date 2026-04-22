package dojo.supermarket.model.offertypes;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;
import dojo.supermarket.model.SpecialOfferType;

public class TwoForAmountOffer extends Offer {
    public TwoForAmountOffer(SpecialOfferType offerType, Product product, double argument) {
        super(offerType, product, argument);
    }

    @Override
    public Discount getDiscount(double unitPrice, double quantity) {
        Product p = getProduct();
        int x = 2;
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < 2) {
            return null;
        }

        double total = argument * (quantityAsInt / x) + quantityAsInt % 2 * unitPrice;
        double discountN = unitPrice * quantity - total;
        return new Discount(p, "2 for " + argument, -discountN);
    }
}
