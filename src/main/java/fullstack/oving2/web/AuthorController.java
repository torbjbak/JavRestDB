package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.repo.AuthorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepo repo;
    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    public AuthorController(AuthorRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Author> all() {
        logMessage("Returning all authors.");
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Author> one(@PathVariable Long id) {
        logMessage("Lookup for author with ID: "+ id);
        Author a = repo.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(id));
        return
    }

    @GetMapping("/search/{search}")
    public List<Author> authorSearch(@PathVariable String search) {
        logMessage("Author search for: '"+ search +"'");
        return repo.findAll().stream().filter(a ->
                a.getPersName().equals(search) || a.getFamName().equals(search))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        logMessage("Adding author: "+ author.getFamName() +", "+ author.getPersName());
        return repo.save(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        logMessage("Attempt to delete author with ID: "+ id);
        repo.deleteById(id);
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}