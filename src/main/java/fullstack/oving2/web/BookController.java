package fullstack.oving2.web;

import fullstack.oving2.model.Book;
import fullstack.oving2.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    private final BookModelAssembler assembler;
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService service, BookModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Book>> all() {
        logMessage("Returning all books.");

        return CollectionModel.of(service
                        .getBooks()
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(BookController.class).all())
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Book> one(@PathVariable Long id) {
        logMessage("Lookup for book with ID: "+ id);

        return assembler.toModel(service.getBook(id));
    }

    @GetMapping("/search/{search}")
    public CollectionModel<EntityModel<Book>> bookSearch(@PathVariable String search) {
        logMessage("Book search for: '"+ search +"'");

        return CollectionModel.of(service
                        .bookSearch(search)
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(BookController.class).all())
                        .withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        logMessage("Adding book: "+ book.getTitle());

        EntityModel<Book> em = assembler.toModel(service.addBook(book));

        return ResponseEntity
                .created(em.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(em);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        logMessage("Attempt to delete book with ID: "+ id);

        service.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}