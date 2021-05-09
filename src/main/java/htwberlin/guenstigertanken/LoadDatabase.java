package htwberlin.guenstigertanken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    CommandLineRunner initDatabase(List<Tanken> newTanken) {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE Tanken;");

        jdbcTemplate.execute("CREATE TABLE Tanken(" +
                "id SERIAL,"+
                "date timestamp with time zone NOT NULL,"+
                "name VARCHAR(255)," +
                "city varchar(255) NOT NULL,"+
                "distance DECIMAL(15,2),"+
                "price DECIMAL(15,2)," +
                "primary key (id, date));");



        ArrayList<Tanken> tanken = new ArrayList<>();
        tanken.add(new Tanken("2021-05-17 15:07:00","Aral", "Berlin", 4,1.1));
        tanken.add(new Tanken("2021-05-17 15:07:00","Shell", "Hamburg", 10.2,1.09));
        tanken.add(new Tanken("2021-05-17 15:07:00","Hem", "Frankfurt", 34,1.3));

        // Use a Java 8 stream to print out each tuple of the list
        tanken.forEach(tank -> log.info("Inserting Tanken record for " + tank + ")"));
        for(Tanken entry: tanken){
            // Uses JdbcTemplate's update
            jdbcTemplate.update("INSERT INTO Tanken(date, name, city, distance, price) VALUES (?,?,?,?,?)", entry.getDate(), entry.getName(), entry.getCity(), entry.getDistance(), entry.getPrice());
        }

/*
        log.info("Querying for Tanken all :");
        jdbcTemplate.query(
                "SELECT * FROM Tanken;",
                (rs, rowNum) -> new Tanken(rs.getLong("id"), rs.getString("date"), rs.getString("name"),rs.getString("city"),rs.getDouble("distance"),
                        rs.getDouble("price"))
        ).forEach(tank -> log.info(tank.toString()));
*/
        return args -> {
            log.info("Preloading " + newTanken.add(new Tanken("2021-05-17 15:07:00", "Aral", "Berlin", 4, 1.1)));
            log.info("Preloading " + newTanken.add(new Tanken("2021-05-17 15:07:00", "Shell", "Potsdam", 6, 1.2)));
        };
    }
}
