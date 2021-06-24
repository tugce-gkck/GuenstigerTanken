package htwberlin.guenstigertanken;

class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String username) {
        super("wrong password or wrong username " + username);
    }
}