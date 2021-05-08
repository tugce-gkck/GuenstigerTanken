package htwberlin.guenstigertanken;

class TankenNotFoundException extends RuntimeException {

    TankenNotFoundException(Long id) {
        super("Could not find tanken " + id);
    }
}