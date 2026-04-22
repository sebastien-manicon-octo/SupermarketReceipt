package dojo.supermarket.model;

public class Offer {

    SpecialOfferType offerType;
    private final Product product;
    double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    Discount getDiscount(double unitPrice, double quantity) {
        switch (offerType) {
            case TWO_FOR_AMOUNT:
                return SpecialOfferType.twoforamount(this, product, unitPrice, quantity);
            case THREE_FOR_TWO:
                return SpecialOfferType.threeForTwo(this, product, unitPrice, quantity);
            case TEN_PERCENT_DISCOUNT:
                return SpecialOfferType.tenPercent(this, product, unitPrice, quantity);
            case FIVE_FOR_AMOUNT:
                return SpecialOfferType.fiveForAmount(this, product, unitPrice, quantity);
            default:
                return null;
        }
    }

    Product getProduct() {
        return product;
    }
}
