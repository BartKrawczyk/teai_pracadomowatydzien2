package pl.programodawca.teai_pracadomowatydzien2.model;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name +
                ", cena = " + price + " zł";
    }
}

