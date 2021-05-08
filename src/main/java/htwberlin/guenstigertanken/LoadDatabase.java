package htwberlin.guenstigertanken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(List<Tanken> tanken) {

        return args -> {
            log.info("Preloading " + tanken.add(new Tanken("2021-05-17 15:07:00", "Aral", "Berlin", 4, 1.1)));
            log.info("Preloading " + tanken.add(new Tanken("2021-05-17 15:07:00", "Shell", "Potsdam", 6, 1.2)));
        };
    }
}
