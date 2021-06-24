package htwberlin.guenstigertanken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

interface UserRepository extends JpaRepository<User, String>, QueryByExampleExecutor<User> {
}
