package htwberlin.guenstigertanken;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, String> {
}
