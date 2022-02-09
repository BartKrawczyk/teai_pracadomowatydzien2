package pl.programodawca.teai_pracadomowatydzien2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.programodawca.teai_pracadomowatydzien2.repository.ProductRepository;

@Service
@Profile({"Plus"})
public class PlusService extends StartService implements ShopService {

    @Value("${vat-rate}")
    private String vatRate;

    ProductRepository productRepository;

    public PlusService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void sum() {
        plusPrinter(productRepository, vatRate);
    }

    static void plusPrinter(ProductRepository productRepository, String vatRate) {
        System.out.println("Wartość netto produktów w koszyku: " + productRepository.nettoSum() + " zł");
        System.out.println("Stawka VAT " + vatRate);
        System.out.println("Wartość podatku VAT: " + productRepository.vat(vatRate) + " zł");
        System.out.println("Wartość produktów z podatkiem VAT: " + productRepository.nettoSum().add(productRepository.vat(vatRate)) + " zł");
    }
}
