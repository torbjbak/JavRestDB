package fullstack.oving2.web;

import fullstack.oving2.model.Book;
import fullstack.oving2.repo.AuthorRepo;
import fullstack.oving2.repo.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepo repo;
    private final BookModelAssembler assembler;
    private final AuthorRepo authorRepo;
    Logger logger = LoggerFactory.getLogger(BookController.class);


    public BookController(BookRepo repo, BookModelAssembler assembler,
                          AuthorRepo authorRepo) {
        this.repo = repo;
        this.assembler = assembler;
        this.authorRepo = authorRepo;
    }

    @GetMapping
    public CollectionModel<EntityModel<Book>> all() {
        logMessage("Returning all books.");
        List<EntityModel<Book>> books = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(books,
                linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Book> one(@PathVariable Long id) {
        logMessage("Lookup for book with ID: "+ id);
        Book b = repo.findById(id).orElseThrow(
                () -> new BookNotFoundException(id));
        return assembler.toModel(b);
    }

    @GetMapping("/search/{search}")
    public CollectionModel<EntityModel<Book>> bookSearch(@PathVariable String search) {
        logMessage("Book search for: '"+ search +"'");
        List<EntityModel<Book>> books = repo.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(search.toLowerCase()))
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(books,
                linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        logMessage("Adding book: "+ book.getTitle());

        Book newBook = new Book(book.getTitle(), book.getYear(),
                authorRepo.findAll().stream().filter(a1 ->
                        book.getAuthors().stream().anyMatch(a2 ->
                                a2.equals(a1))).collect(Collectors.toSet()));

        authorRepo.findAll().stream().filter(a1 ->
                book.getAuthors().stream().anyMatch(a2 ->
                        a2.equals(a1))).collect(Collectors.toSet())
                .forEach(a -> a.getBooks().add(newBook));


        EntityModel<Book> em = assembler.toModel(repo.save(book));
        return ResponseEntity
                .created(em.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(em);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        logMessage("Attempt to delete book with ID: "+ id);

        Book b = repo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        authorRepo.findAll().stream().filter(a -> a.getBooks().contains(b))
                .forEach(a -> a.getBooks().remove(b));

        b.setAuthors(null);
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}