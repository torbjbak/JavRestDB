package fullstack.oving2.web;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long id) {
        super("Cound not find book with ID: "+ id);
    }
}
