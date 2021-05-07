package htwberlin.guenstigertanken;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE Tanken IF EXISTS CASCADE;");
        jdbcTemplate.execute("CREATE TABLE Tanken(" +
                "id SERIAL,"+
                "date timestamp with time zone NOT NULL,"+
                "name VARCHAR(255)," +
                "city varchar(255) NOT NULL,"+
                "distance DECIMAL(2, 0),"+
                "price DECIMAL(2, 0)," +
                "primary key (id, date));");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpTanken = Arrays.asList("2021-05-17 15:07:00+01 Aral Berlin 4 1.1", "2021-05-17 15:07:00+01 Shell Hamburg 10 1.0", "2021-05-17 15:07:00+01 Aral Frankfurt 30 1.6").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpTanken.forEach(name -> log.info(String.format("Inserting Tanken record for %s %s %s", name[0], name[1], name[2])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO Tanken(date, name,city,distance,price) VALUES (?,?,?,?,?);", splitUpTanken);

        log.info("Querying for Tanken all :");
        jdbcTemplate.query(
                "SELECT * FROM Tanken;",
                (rs, rowNum) -> new Tanken(rs.getLong("id"), rs.getString("date"), rs.getString("name"),rs.getString("city"),rs.getDouble("distance"),
                        rs.getDouble("price"))
        ).forEach(tanken -> log.info(tanken.toString()));
    }



}
