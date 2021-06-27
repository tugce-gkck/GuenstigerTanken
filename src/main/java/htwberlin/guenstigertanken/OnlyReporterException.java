package htwberlin.guenstigertanken;

class OnlyReporterException extends RuntimeException {

    OnlyReporterException(String username) {
        super("Only reporter " + username + " is authorized");
    }
}