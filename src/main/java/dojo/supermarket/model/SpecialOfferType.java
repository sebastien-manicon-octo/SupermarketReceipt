package dojo.supermarket.model;

import dojo.supermarket.model.offertypes.FiveForAmountOffer;
import dojo.supermarket.model.offertypes.TenPercentDiscountOffer;
import dojo.supermarket.model.offertypes.ThreeForTwoOffer;
import dojo.supermarket.model.offertypes.TwoForAmountOffer;

import java.util.function.BiFunction;

public enum SpecialOfferType {
    THREE_FOR_TWO(ThreeForTwoOffer::new),
    TEN_PERCENT_DISCOUNT(TenPercentDiscountOffer::new),
    TWO_FOR_AMOUNT(TwoForAmountOffer::new),
    FIVE_FOR_AMOUNT(FiveForAmountOffer::new);

    private final BiFunction<Product, Double, Offer> offerSupplier;

    SpecialOfferType(BiFunction<Product, Double, Offer> offerSupplier) {
        this.offerSupplier = offerSupplier;
    }

    public Offer createOffer(Product product, double argument) {
        return offerSupplier.apply(product, argument);
    }
}
