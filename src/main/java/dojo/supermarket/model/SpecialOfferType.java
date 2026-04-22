package dojo.supermarket.model;

public enum SpecialOfferType {
    THREE_FOR_TWO,
    TEN_PERCENT_DISCOUNT,
    TWO_FOR_AMOUNT,
    FIVE_FOR_AMOUNT,
    ;

    static Discount fiveForAmount(Offer offer, Product p, double unitPrice, double quantity) {
        int x = 5;
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < 5) {
            return null;
        }

        int numberOfXs = quantityAsInt / x;
        double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
        return new Discount(p, x + " for " + offer.argument, -discountTotal);
    }

    static Discount tenPercent(Offer offer, Product p, double unitPrice, double quantity) {
        return new Discount(p, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
    }

    static Discount threeForTwo(Offer offer, Product p, double unitPrice, double quantity) {
        int x = 3;
        int quantityAsInt = (int) quantity;
        if (quantityAsInt <= 2) {
            return null;
        }

        int numberOfXs = quantityAsInt / x;
        double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
        return new Discount(p, "3 for 2", -discountAmount);
    }

    static Discount twoforamount(Offer offer, Product p, double unitPrice, double quantity) {
        int x = 2;
        int quantityAsInt = (int) quantity;
        if (quantityAsInt < 2) {
            return null;
        }
        
        double total = offer.argument * (quantityAsInt / x) + quantityAsInt % 2 * unitPrice;
        double discountN = unitPrice * quantity - total;
        return new Discount(p, "2 for " + offer.argument, -discountN);
    }
}
