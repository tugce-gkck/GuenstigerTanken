package htwberlin.guenstigertanken;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TankenRowMapper implements RowMapper<Tanken> {

    @Override
    public Tanken mapRow(ResultSet rs, int rowNum) throws SQLException {

        Tanken tanken = new Tanken();
        tanken.setId(rs.getLong("id"));
        tanken.setDate(rs.getTimestamp("date"));
        tanken.setName(rs.getString("name"));
        tanken.setCity(rs.getString("city"));
        tanken.setDistance(rs.getDouble("distance"));
        tanken.setPrice(rs.getDouble("price"));

        return tanken;

    }
}
