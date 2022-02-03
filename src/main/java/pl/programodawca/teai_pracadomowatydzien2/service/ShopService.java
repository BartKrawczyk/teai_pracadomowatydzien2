package pl.programodawca.teai_pracadomowatydzien2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class ShopService {

    private Product product;

    private static final int minPrice = 50;
    private static final int maxPrice = 300;

    List<Product> products = new ArrayList<>();

    public List<Product> generateProducts() {
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setName("Product"+(i+1));
            product.setPrice(generatePrice());
            products.add(product);
        }
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void showProducts() {
        products.stream().forEach(System.out::println);
    }
    
    private static BigDecimal generatePrice() {
        Random generator = new Random();
        return BigDecimal.valueOf(generator.nextDouble() * (maxPrice - minPrice) + minPrice).setScale(2, RoundingMode.CEILING);
    }

    public void sum(){  BigDecimal sum = products.stream().filter(Objects::nonNull).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("------------------------------------------");
        System.out.println("Wartość produktów w koszyku: " + sum + " zł");}



}