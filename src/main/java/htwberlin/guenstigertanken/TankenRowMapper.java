package htwberlin.guenstigertanken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TankenRowMapper implements RowMapper<Tanken> {
    Logger logger = LoggerFactory.getLogger(TankenRowMapper.class);
    @Override
    public Tanken mapRow(ResultSet rs, int rowNum) throws SQLException {
        logger.info("Creating new Entry");
        Tanken tanken = new Tanken();
        /*tanken.setId(rs.getLong("id"));

        tanken.setDate(rs.getTimestamp("date"));
        tanken.setName(rs.getString("name"));
        tanken.setCity(rs.getString("city"));
        tanken.setDistance(rs.getDouble("distance"));
        tanken.setPrice(rs.getDouble("price"));

         */

        return tanken;

    }
}
