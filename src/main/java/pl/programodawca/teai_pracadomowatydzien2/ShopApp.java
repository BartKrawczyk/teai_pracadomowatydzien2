package pl.programodawca.teai_pracadomowatydzien2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.programodawca.teai_pracadomowatydzien2.model.Product;
import pl.programodawca.teai_pracadomowatydzien2.repository.ProductRepository;
import pl.programodawca.teai_pracadomowatydzien2.service.ShopService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

@Component
public class ShopApp implements CommandLineRunner {

    final ProductRepository productRepository;
    final Product product;
    final ShopService shopService;

    public ShopApp(ProductRepository productRepository, Product product, ShopService shopService) {
        this.productRepository = productRepository;
        this.product = product;
        this.shopService = shopService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Lista aktualnych produktów:");
        productRepository.generateProducts();
        productRepository.showProducts();
        System.out.println("--------------------------------");
        shopService.sum();
        System.out.println();


        String operation;
        do {
            System.out.println("Wybierz opcję:");
            System.out.println("D - Dodaj produkt do koszyka");
            System.out.println("P - Pokaż koszyk");
            System.out.println("W - Wyjście z programu");
            Scanner scanner = new Scanner(System.in);
            scanner.useLocale(Locale.US);
            operation = scanner.nextLine();
            switch (operation.toLowerCase()) {
                case ("d"):
                    System.out.println("Dodawanie nowego produktu do koszyka.");
                    String name = null;
                    BigDecimal price = null;
                    try {
                        System.out.println("Podaj nazwę produktu:");
                        name = scanner.nextLine();
                        System.out.println("Podaj cenę: (W przypadku liczb zmiennoprzecinkowych użyj kropki)");
                        price = scanner.nextBigDecimal().setScale(2, RoundingMode.CEILING);
                    } catch (InputMismatchException e) {
                        System.out.println("Wprowadzono nieprawidłowe dane!");
                    }
                    if (name != null && price != null) {
                        Product product = new Product();
                        product.setName(name);
                        product.setPrice(price);
                        productRepository.addProduct(product);
                        System.out.println("Dodano produkt o nazwie " + name + ", w cenie " + price);
                    } else {
                        System.out.println("Produkt nie został dodany");
                    }
                    System.out.println();
                    break;
                case ("p"):
                    System.out.println("Pokaż produkty");
                    productRepository.showProducts();
                    System.out.println("--------------------------------");
                    shopService.sum();
                    System.out.println();
                    break;
                case ("w"):
                    System.out.println("Nastąpi wyjście z programu");
                    break;
                default:
                    System.out.println("Nieprawidlowy wybór!");
            }
        } while (!operation.equals("w"));
    }
}

