package htwberlin.guenstigertanken;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TankenRepository extends CrudRepository<Tanken, Long> {

    List<Tanken> findByLastName(String lastName);

    Tanken findById(long id);
}