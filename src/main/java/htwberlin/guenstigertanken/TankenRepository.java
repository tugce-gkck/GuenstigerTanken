package htwberlin.guenstigertanken;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TankenRepository extends CrudRepository<Tanken, Long> {

    List<Tanken> findByDate(Timestamp ts);
    List<Tanken> findByName(String name);

    Tanken findById(long id);
}