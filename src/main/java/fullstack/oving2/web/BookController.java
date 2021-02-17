package fullstack.oving2.web;

import fullstack.oving2.model.Book;
import fullstack.oving2.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private MyService service;

    @GetMapping("/books")
    public String getBooks() {
        logMessage("Returning all books.");
        return service.getBooks().toString();
    }

    @GetMapping("/books/{id}")
    public String getAuthors(@PathVariable String id) {
        logMessage("Lookup for authors of book with ID: "+ id);
        return service.getAuthorsBook(Integer.parseInt(id)).toString();
    }

    @GetMapping("/books/search/{search}")
    public String bookSearch(@PathVariable String search) {
        logMessage("Book search for: '"+ search +"'");
        return service.bookSearch(search).toString();
    }

    @PostMapping("/books")
    public String addBook(@RequestBody Book book) {
        logMessage("Adding book: "+ book.getName());
        return service.addBook(book).toString();
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