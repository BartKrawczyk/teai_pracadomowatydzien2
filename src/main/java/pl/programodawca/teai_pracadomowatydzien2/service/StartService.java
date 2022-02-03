package pl.programodawca.teai_pracadomowatydzien2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@Profile({"Start", "default"})
public class StartService implements CartValue{

    ShopService shopService;

    @Override
    public void cartSum() {
        BigDecimal sum = shopService.products.stream().filter(Objects::nonNull).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("------------------------------------------");
        System.out.println("Wartość produktów w koszyku: " + sum + " zł");
    }
}

