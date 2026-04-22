package dojo.supermarket.model;

import dojo.supermarket.model.offertypes.FiveForAmountOffer;
import dojo.supermarket.model.offertypes.TenPercentDiscountOffer;
import dojo.supermarket.model.offertypes.ThreeForTwoOffer;
import dojo.supermarket.model.offertypes.TwoForAmountOffer;

public class Offer {

    private SpecialOfferType offerType;
    protected final Product product;
    protected double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    public Discount getDiscount(double unitPrice, double quantity) {
        switch (offerType) {
            case TWO_FOR_AMOUNT:
                return new TwoForAmountOffer(offerType, product, argument).getDiscount(unitPrice, quantity);
            case THREE_FOR_TWO:
                return new ThreeForTwoOffer(offerType, product, argument).getDiscount(unitPrice, quantity);
            case TEN_PERCENT_DISCOUNT:
                return new TenPercentDiscountOffer(offerType, product, argument).getDiscount(unitPrice, quantity);
            case FIVE_FOR_AMOUNT:
                return new FiveForAmountOffer(offerType, product, argument).getDiscount(unitPrice, quantity);
            default:
                return null;
        }
    }

    public Product getProduct() {
        return product;
    }
}
