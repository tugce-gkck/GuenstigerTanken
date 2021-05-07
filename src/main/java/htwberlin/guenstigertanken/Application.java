package htwberlin.guenstigertanken;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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

        //jdbcTemplate.execute("DROP TABLE Tanken IF EXISTS CASCADE;");
        /*
           jdbcTemplate.execute("CREATE TABLE Tanken(" +
                "id SERIAL,"+
                "date timestamp with time zone NOT NULL,"+
                "name VARCHAR(255)," +
                "city varchar(255) NOT NULL,"+
                "distance DECIMAL(2, 0),"+
                "price DECIMAL(2, 0)," +
                "primary key (id, date));");

         */

        ArrayList<Tanken> tanken = new ArrayList<>();
        tanken.add(new Tanken("2021-05-17 15:07:00+01","Aral", "Berlin", 4,1.1));
        tanken.add(new Tanken("2021-05-17 15:07:00+01","Shell", "Hamburg", 10,1.09));
        tanken.add(new Tanken("2021-05-17 15:07:00+01","Aral", "Frankfurt", 34,1.3));

        // Use a Java 8 stream to print out each tuple of the list
        tanken.forEach(tank -> log.info("Inserting Tanken record for " + tank + ")"));
        for(Tanken entry: tanken){
            // Uses JdbcTemplate's update
            jdbcTemplate.update("INSERT INTO Tanken(date, name, city, distance, price) VALUES (?,?,?,?,?)", entry.getDate(), entry.getName(), entry.getCity(), entry.getDistance(), entry.getPrice());
        }


        log.info("Querying for Tanken all :");
        jdbcTemplate.query(
                "SELECT * FROM Tanken;",
                (rs, rowNum) -> new Tanken(rs.getLong("id"), rs.getString("date"), rs.getString("name"),rs.getString("city"),rs.getDouble("distance"),
                        rs.getDouble("price"))
        ).forEach(tank -> log.info(tank.toString()));
    }



}
