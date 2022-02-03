package pl.programodawca.teai_pracadomowatydzien2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.programodawca.teai_pracadomowatydzien2.repository.ProductRepository;

@Service
@Profile({"Start"})
public class StartService implements ShopService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void sum() {
        System.out.println("Wartość produktów w koszyku: " + productRepository.nettoSum() + " zł netto");
    }
}