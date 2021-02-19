package fullstack.oving2.web;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long id) {
        super("Cound not find author with ID: "+ id);
    }
}
