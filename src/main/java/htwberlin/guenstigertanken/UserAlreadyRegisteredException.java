package htwberlin.guenstigertanken;

class UserAlreadyRegisteredException extends RuntimeException {

    UserAlreadyRegisteredException(String username) {
        super("user already registered " + username);
    }
}