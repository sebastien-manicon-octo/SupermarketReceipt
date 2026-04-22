package dojo.supermarket.model;

import dojo.supermarket.ReceiptPrinter;
import dojo.supermarket.model.offertypes.FiveForAmountOffer;
import dojo.supermarket.model.offertypes.TenPercentDiscountOffer;
import dojo.supermarket.model.offertypes.ThreeForTwoOffer;
import dojo.supermarket.model.offertypes.TwoForAmountOffer;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupermarketTest {

    // Todo: test all kinds of discounts are applied properly

    @Test
    void tenPercentDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 1.99);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new TenPercentDiscountOffer(toothbrush, 10.0));

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(4.975, receipt.getTotalPrice(), 0.01);
        assertEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(1.99, receiptItem.getPrice());
        assertEquals(2.5 * 1.99, receiptItem.getTotalPrice());
        assertEquals(2.5, receiptItem.getQuantity());

    }

    private final List<Product> PRODUCTS = Arrays.asList(
            new Product("toothbrush", ProductUnit.EACH),
            new Product("soap", ProductUnit.EACH),
            new Product("pasta", ProductUnit.EACH),
            new Product("apples", ProductUnit.KILO),
            new Product("peach", ProductUnit.KILO),
            new Product("kiwi", ProductUnit.KILO)
    );

    private final List<BiFunction<Product, Double, Offer>> OFFERS = Arrays.asList(
            (p, a) -> new ThreeForTwoOffer(p),
            TenPercentDiscountOffer::new,
            TwoForAmountOffer::new,
            FiveForAmountOffer::new
    );

    @Test
    public void golden_master() {
        Random random = new Random(42);

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < 100; x++) {
            SupermarketCatalog catalog = new FakeCatalog();
            Set<Product> existingProducts = new HashSet<>();
            for (int i = 0; i < random.nextInt(10) + 1; i++) {
                Product product = PRODUCTS.get(random.nextInt(PRODUCTS.size()));
                existingProducts.add(product);
                catalog.addProduct(
                        product,
                        random.nextDouble()
                );
            }

            Teller teller = new Teller(catalog);
            for (int i = 0; i < random.nextInt(10) + 1; i++) {
                //SpecialOfferType[] values = SpecialOfferType.values();
                Product product = PRODUCTS.get(random.nextInt(PRODUCTS.size()));

                if (!existingProducts.contains(product)) {
                    continue;
                }

                Offer offer = OFFERS.get(random.nextInt(OFFERS.size())).apply(product, random.nextDouble() % 5);
                teller.addSpecialOffer(
                        offer
                );
            }

            ShoppingCart cart = new ShoppingCart();
            for (int i = 0; i < random.nextInt(10) + 1; i++) {
                Product product = PRODUCTS.get(random.nextInt(PRODUCTS.size()));

                if (!existingProducts.contains(product)) {
                    continue;
                }

                cart.addItemQuantity(
                        product,
                        random.nextDouble() * 5
                );
            }

            Receipt receipt = teller.checksOutArticlesFrom(cart);

            ReceiptPrinter printer = new ReceiptPrinter();
            sb.append(printer.printReceipt(receipt));
        }

        Approvals.verify(sb.toString());
    }
}
