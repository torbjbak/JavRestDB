package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.model.Book;
import fullstack.oving2.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AuthorController {
    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private MyService service;

    @GetMapping("/authors")
    public ArrayList<Author> getAuthors() {
        logMessage("Returning all books.");
        return service.getAuthors();
    }

    @GetMapping("/authors/{id}")
    public ArrayList<Book> getBooks(@PathVariable String id) {
        logMessage("Lookup for books by author with ID: "+ id);
        return service.getBooksAuthor(Integer.parseInt(id));
    }

    @GetMapping("/authors/search/{search}")
    public ArrayList<Author> authorSearch(@PathVariable String search) {
        logMessage("Author search for: '"+ search +"'");
        return service.authorSearch(search);
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        logMessage("Adding author: "+ author.getFamName() +", "+ author.getPersName());
        return service.addAuthor(author);
    }

    @DeleteMapping("/authors/{id}")
    public boolean removeAuthor(@PathVariable int id) {
        logMessage("Attempt to delete author with ID: "+ id);
        return service.removeAuthor(id);
    }



    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}