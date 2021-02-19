package fullstack.oving2.web;

public class BookNotFoundException extends RuntimeException{

    BookNotFoundException(Long id) {
        super("Cound not find book with ID: "+ id);
    }
}
