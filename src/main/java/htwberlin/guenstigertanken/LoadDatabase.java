package htwberlin.guenstigertanken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
class LoadDatabase {

    Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void reCreate() {


        jdbcTemplate.execute("DROP TABLE Tanken;");

        jdbcTemplate.execute("CREATE TABLE Tanken(" +
                "id SERIAL,"+
                "date timestamp with time zone NOT NULL,"+
                "name VARCHAR(255)," +
                "city varchar(255) NOT NULL,"+
                "distance DECIMAL(15,2),"+
                "price DECIMAL(15,2)," +
                "primary key (id, date));");

        List<Tanken> tanken = new ArrayList<>();
        tanken.add(new Tanken("2021-05-17 15:07:00","Aral", "Berlin", 4,1.1));
        tanken.add(new Tanken("2021-05-17 15:07:00","Shell", "Hamburg", 10.2,1.09));
        tanken.add(new Tanken("2021-05-17 15:07:00","Hem", "Frankfurt", 34,1.3));

        for(Tanken entry: tanken){
            // Uses JdbcTemplate's update
            jdbcTemplate.update("INSERT INTO Tanken(date, name, city, distance, price) VALUES (?,?,?,?,?)", entry.getDate(), entry.getName(), entry.getCity(), entry.getDistance(), entry.getPrice());
        }

    }
    @Bean
    CommandLineRunner initDatabase(TankenRepository repository) {
        repository.deleteAll();
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        repository.save(new Tanken(ts,"Total", "Berlin", 4,1.01));
        repository.save(new Tanken(ts,"Shell", "Berlin", 5,1.50));
        repository.save(new Tanken(ts,"Esso", "Berlin", 4.7,1.18));
        repository.save(new Tanken(ts,"Total", "Hamburg", 52,1.22));
        repository.save(new Tanken(ts,"Aral", "Hamburg", 53.9,1.11));
        repository.save(new Tanken(ts,"Agip", "Hamburg", 52.6,1.32));
        repository.save(new Tanken(ts,"Total", "Dresden", 75.3,1.91));
        repository.save(new Tanken(ts,"Aral", "Dresden", 74.2,1.64));
        repository.save(new Tanken(ts,"Aral", "Frankfurt", 135.7,1.71));
        repository.save(new Tanken(ts,"Esso", "München", 130.5,1.01));
        repository.save(new Tanken(ts,"Total", "München", 132.6,1.12));
        repository.save(new Tanken(ts,"Aral", "München", 133.9,1.00));
        repository.save(new Tanken(ts,"Shell", "München", 140,1.80));
        return args -> {
        };
    }

}
