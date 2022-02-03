package pl.programodawca.teai_pracadomowatydzien2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.programodawca.teai_pracadomowatydzien2.repository.ProductRepository;

@Service
@Profile({"Pro"})
public class ProService implements ShopService {

    @Value("${vat-rate}")
    private String vatRate;

    @Value("${discount-rate}")
    private String discountRate;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void sum() {
        PlusService.plusPrinter(productRepository, vatRate);
        System.out.println("Przysługujący rabat " + discountRate);
        System.out.println("Wartość rabatu " + productRepository.discount(discountRate) + " zł");
        System.out.println("Wartość netto wszystkich produktów po rabacie " + productRepository.discountedNettoSum(discountRate) + " zł");
        System.out.println("Wartość wszystkich produktów po rabacie wraz z podatkiem VAT " + productRepository.getDiscountedBruttoSum(discountRate, vatRate) + " zł");
    }
}
