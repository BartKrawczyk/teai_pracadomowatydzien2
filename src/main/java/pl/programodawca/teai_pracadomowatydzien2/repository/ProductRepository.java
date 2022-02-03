package pl.programodawca.teai_pracadomowatydzien2.repository;

import org.springframework.stereotype.Repository;
import pl.programodawca.teai_pracadomowatydzien2.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Repository
public class ProductRepository {
    private Product product;

    private static final int minPrice = 50;
    private static final int maxPrice = 300;

    public List<Product> products = new ArrayList<>();

    public void generateProducts() {
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setName("Product" + (i + 1));
            product.setPrice(generatePrice());
            products.add(product);
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void showProducts() {
        products.forEach(System.out::println);
    }

    private static BigDecimal generatePrice() {
        Random generator = new Random();
        return BigDecimal.valueOf(generator.nextDouble() * (maxPrice - minPrice) + minPrice).setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal percentConverter(String string) {
        return new BigDecimal(string.trim().replace("%", "")).divide(BigDecimal.valueOf(100));
    }

    public BigDecimal nettoSum() {
        return products.stream().filter(Objects::nonNull).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal vat(String vatRate) {
        return nettoSum().multiply(percentConverter(vatRate)).setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal discount(String discountRate) {
        return percentConverter(discountRate).multiply(nettoSum()).setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal discountedNettoSum(String discountRate) {
        return nettoSum().subtract(discount(discountRate)).setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getDiscountedBruttoSum(String discountRate, String vatRate) {
        return discountedNettoSum(discountRate).add(vat(vatRate)).setScale(2, RoundingMode.CEILING);
    }
}
