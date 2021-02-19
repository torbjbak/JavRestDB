package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.repo.AuthorRepo;
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
    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    public AuthorController(AuthorRepo repo, AuthorModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
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
                .filter(a -> a.getPersName().contains(search) || a.getFamName().contains(search))
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(authors,
                linkTo(methodOn(AuthorController.class).all()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        logMessage("Adding author: "+ author.getFamName() +", "+ author.getPersName());
        EntityModel<Author> em = assembler.toModel(repo.save(author));
        return ResponseEntity
                .created(em.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(em);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        logMessage("Attempt to delete author with ID: "+ id);
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}