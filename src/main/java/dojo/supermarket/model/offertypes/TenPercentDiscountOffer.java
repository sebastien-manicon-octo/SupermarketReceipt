package dojo.supermarket.model.offertypes;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class TenPercentDiscountOffer extends Offer {
    public TenPercentDiscountOffer(Product product, double argument) {
        super(product, argument);
    }

    @Override
    public Discount getDiscount(double unitPrice, double quantity) {
        Product p = getProduct();
        return new Discount(p, argument + "% off", -quantity * unitPrice * argument / 100.0);
    }
}
