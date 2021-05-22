package htwberlin.guenstigertanken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        logger.info("Called initDatabase CommanLineRunner");
        return args -> {
            logger.info("Preloading " + repository.save(new Tanken("2021-05-17 15:08:00","Aral", "Berlin", 4,1.1)));
        };
    }

    public List<Tanken> readAll(){
        logger.info("Before reading everything!");
        List<Tanken> all = new ArrayList<>();
        try{
            all = jdbcTemplate.query(
                    "SELECT * FROM Tanken",
                    (rs, rowNum) -> new Tanken(rs.getLong("ID"), rs.getTimestamp("DATE"), rs.getString("NAME"), rs.getString("CITY"), rs.getDouble("DISTANCE"), rs.getDouble("PRICE"))
            );
            all.forEach(tanken -> logger.info(tanken.toString()));
        }catch (Exception e){
            logger.error("Error while reading Tanken all!");
            logger.error(e.getMessage());
        }

        return all;
    }
}
