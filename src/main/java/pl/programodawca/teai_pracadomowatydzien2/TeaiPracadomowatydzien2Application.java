package pl.programodawca.teai_pracadomowatydzien2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeaiPracadomowatydzien2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TeaiPracadomowatydzien2Application.class, args);
        ShopApp shopApp = null;
        assert false;
        shopApp.run();
    }

    @Override
    public void run(String... args) {
    }
}
