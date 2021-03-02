package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.repo.AddressRepo;
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
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepo repo;
    private final AuthorModelAssembler assembler;
    private final BookRepo bookRepo;
    private final BookModelAssembler bookAssembler;
    private final AddressRepo addressRepo;
    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    public AuthorController(AuthorRepo r, AuthorModelAssembler ass,
                            BookRepo br, BookModelAssembler bma, AddressRepo ar) {
        this.repo = r;
        this.assembler = ass;
        this.bookRepo = br;
        this.bookAssembler = bma;
        this.addressRepo = ar;
    }

    @GetMapping
    public CollectionModel<EntityModel<Author>> all() {
        logMessage("Returning all authors.");
        List<EntityModel<Author>> authors = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(authors,
                linkTo(methodOn(AuthorController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Author> one(@PathVariable Long id) {
        logMessage("Lookup for author with ID: "+ id);
        Author a = repo.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(id));
        return assembler.toModel(a);
    }

    @GetMapping("/search/{search}")
    public CollectionModel<EntityModel<Author>> authorSearch(@PathVariable String search) {
        logMessage("Author search for: '"+ search +"'");
        List<EntityModel<Author>> authors =  repo.findAll().stream()
                .filter(a -> a.getPersName().toLowerCase().contains(search.toLowerCase())
                        || a.getFamName().toLowerCase().contains(search.toLowerCase()))
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(authors,
                linkTo(methodOn(AuthorController.class).all()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        logMessage("Adding author: "+ author.getFamName() +", "+ author.getPersName());

        Author newAuthor = new Author(author.getPersName(), author.getFamName(),
                addressRepo.findAll().stream().filter(a ->
                        a.equals(author.getAddress())).findFirst().orElse(null),
                bookRepo.findAll().stream().filter(b1 ->
                        author.getBooks().stream().anyMatch(b2 -> b2.equals(b1)))
                        .collect(Collectors.toSet()));

        bookRepo.findAll().stream().filter(b1 -> author.getBooks().stream().anyMatch(b2 -> b2.equals(b1)))
                .collect(Collectors.toSet()).forEach(b -> b.getAuthors().add(newAuthor));

        EntityModel<Author> em = assembler.toModel(repo.save(author));
        return ResponseEntity
                .created(em.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(em);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        logMessage("Attempt to delete author with ID: "+ id);

        Author author = repo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        bookRepo.findAll().stream().filter(b -> b.getAuthors().contains(author))
                .forEach(b -> b.getAuthors().remove(author));

        author.setBooks(null);
        repo.delete(author);
        return ResponseEntity.noContent().build();
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}