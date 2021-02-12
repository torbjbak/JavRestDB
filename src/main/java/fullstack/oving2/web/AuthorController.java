package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AuthorController {
    private final ArrayList<Author> authors = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private MyService service;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/authors")
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        author.setId(authors.size());
        authors.add(author);
        return author;
    }

    @PutMapping("/users/{id}")
    public Author changeAuthor(@RequestBody Author newData, @PathVariable int id){
        for (Author a : authors){
            if (a.getId() == id){
                authors.set(authors.indexOf(a), newData);
                return newData;
            }
        }
        return null;
    }

    @DeleteMapping("/authors/{id}")
    public boolean removeAuthor(@PathVariable int id) {
        for(Author a : authors) {
            if(a.getId() == id) {
                authors.set(authors.indexOf(a), authors.get(authors.size() - 1));
                authors.remove(authors.get(authors.size() - 1));
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/authors/log")
    public Author logMessage() {

        logger.trace("Trace!");
        logger.debug("Debug!");
        logger.info("Info!");
        logger.warn("Warning!");
        logger.error("Error!");

        return this.service.authorMessage();
    }
}
