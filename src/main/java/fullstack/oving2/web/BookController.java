package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.model.Book;
import fullstack.oving2.repo.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepo repo;
    Logger logger = LoggerFactory.getLogger(BookController.class);


    public BookController(BookRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Book> all() {
        logMessage("Returning all books.");
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Book one(@PathVariable Long id) {
        logMessage("Lookup for book with ID: "+ id);
        return repo.findById(id).orElseThrow(
                () -> new BookNotFoundException(id));
    }

    @GetMapping("/search/{search}")
    public List<Book> bookSearch(@PathVariable String search) {
        logMessage("Book search for: '"+ search +"'");
        return repo.findAll().stream().filter(b ->
                b.getName().equals(search))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        logMessage("Adding book: "+ book.getName());
        return repo.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        logMessage("Attempt to delete book with ID: "+ id);
        repo.deleteById(id);
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}