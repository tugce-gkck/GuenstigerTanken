package htwberlin.guenstigertanken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import Java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

class LoadDatabase {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void initDatabase() {


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

    public List<Tanken> readAll(){

        return jdbcTemplate.query(
                "SELECT * FROM Tanken;",
                new TankenRowMapper());
    }
}
