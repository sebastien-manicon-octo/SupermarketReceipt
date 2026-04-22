package dojo.supermarket.model.offertypes;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class ThreeForTwoOffer extends Offer {
    public ThreeForTwoOffer(Product product, double argument) {
        super(product, argument);
    }

    @Override
    public Discount getDiscount(double unitPrice, double quantity) {
        Product p = getProduct();
        int x = 3;
        int quantityAsInt = (int) quantity;
        if (quantityAsInt <= 2) {
            return null;
        }

        int numberOfXs = quantityAsInt / x;
        double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
        return new Discount(p, "3 for 2", -discountAmount);
    }
}
