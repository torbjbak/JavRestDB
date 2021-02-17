package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {
    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private MyService service;

    @GetMapping("/authors")
    public String getAuthors() {
        logMessage("Returning all books.");
        return service.getAuthors().toString();
    }

    @GetMapping("/authors/{id}")
    public String getBooks(@PathVariable String id) {
        logMessage("Lookup for books by author with ID: "+ id);
        return service.getBooksAuthor(Integer.parseInt(id)).toString();
    }

    @GetMapping("/authors/search/{search}")
    public String authorSearch(@PathVariable String search) {
        logMessage("Author search for: '"+ search +"'");
        return service.authorSearch(search).toString();
    }

    @PostMapping("/authors")
    public String addAuthor(@RequestBody Author author) {
        logMessage("Adding author: "+ author.getFamName() +", "+ author.getPersName());
        return service.addAuthor(author).toString();
    }

    @DeleteMapping("/authors/{id}")
    public boolean removeAuthor(@PathVariable int id) {
        logMessage("Attempt to delete author with ID: "+ id);
        return service.deleteAuthor(id);
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}