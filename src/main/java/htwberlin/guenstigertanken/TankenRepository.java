package htwberlin.guenstigertanken;

import org.springframework.data.jpa.repository.JpaRepository;

interface TankenRepository extends JpaRepository<Tanken, Long> {

}