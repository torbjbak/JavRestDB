package fullstack.oving2.web;

import fullstack.oving2.model.Book;
import fullstack.oving2.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private MyService service;

    @GetMapping("/books")
    public ArrayList<Book> getBooks() {
        logMessage("Returning all books.");
        return service.getBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable String id) {
        logMessage("Lookup for book with ID: "+ id);
        return service.getBook(Integer.parseInt(id));
    }

    @GetMapping("/books/search/{search}")
    public ArrayList<Book> bookSearch(@PathVariable String search) {
        logMessage("Book search for: '"+ search +"'");
        return service.bookSearch(search);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        logMessage("Adding book: "+ book.getName());
        return service.addBook(book);
    }

    @DeleteMapping("/books/{id}")
    public boolean deleteBook(@PathVariable int id) {
        logMessage("Attempt to delete book with ID: "+ id);
        return service.deleteBook(id);
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}